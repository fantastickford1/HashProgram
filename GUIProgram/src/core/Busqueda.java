package core;

/**
 * Created by Karlos on 2/17/2016.
 */
public class Busqueda {

    private int[] arreglo;
    private int[][] arreglo_anidado;
    private Funciones_Hash fh;
    private ListaSimple lista;
    public int contador;
    private int crash;
    public int clave;

    public Busqueda(int[] arreglo, Funciones_Hash fh){
        this.arreglo = arreglo;
        this.fh = fh;
        this.arreglo_anidado = null;
    }

    public int prueba_lineal(int option, int id){
        contador = 0;
        int d;
        int dx;
        d = indexGenerator(option,id);
        while (d >= arreglo.length){
            d-= arreglo.length;
            if (d < 0)
                d = 0;
        }
        if (arreglo[d] == id){
            return d;
        }else{
            dx = d + 1;
            while ((dx <= arreglo.length) && (arreglo[dx] != id) && (dx != d) && (arreglo[dx] != 0)){
                dx = dx + 1;
                contador++;
                if (dx == arreglo.length){
                    dx = 0;
                }
            }
            if (arreglo[dx] == id){
                return dx;
            }
        }
        return -1;
    }

    public int prueba_cuadratica(int option, int id) {
        contador = 0;
        int d;
        int dx;
        int i;
        d = indexGenerator(option,id);
        while (d >= arreglo.length){
            d-= arreglo.length;
            if (d < 0)
                d = 0;
        }
        if (arreglo[d] == id){
            return d;
        }else{
            i = 1;
            dx = d + (int) Math.pow(i,2);
            while (dx >= arreglo.length){
                dx-= arreglo.length;
                if (dx < 0)
                    dx = 0;
            }
            while ((arreglo[dx] != id) && (arreglo[dx] != 0)){
                contador++;
                i++;
                dx = d + (int) Math.pow(i,2);
                if (dx >= arreglo.length){
                    i = 0;
                    dx = 0;
                    d = 1;
                }
                crash++;
                if (crash > 30){
                    return -1;
                }
            }
            if (arreglo[dx] == id){
                return dx;
            }
        }
        return -1;
    }

    public int dobleDireccion(int option, int id){
        contador = 0;
        int d;
        int dx;
        d = indexGenerator(option,id);
        while (d >= arreglo.length){
            d-= arreglo.length;
            if (d < 0)
                d = 0;
        }
        if (arreglo[d] == id){
            return d;
        }else{
            dx = indexGenerator(option,d);
            while (dx >= arreglo.length){
                dx-= arreglo.length;
                if (dx < 0)
                    dx = 0;
            }
            while ((dx <= arreglo.length) && (arreglo[dx] != id) && (arreglo[dx] != 0) && (dx != d) ){
                contador++;
                dx = indexGenerator(option,dx);
                while (dx >= arreglo.length){
                    dx-= arreglo.length;
                    if (dx < 0)
                        dx = 0;
                }
                crash++;
                if (crash > 30){
                    return -1;
                }
            }
            if (arreglo[dx] == id){
                return dx;
            }
        }
        return -1;
    }

    public String arreglosAnidados(int option, int id){
        contador = 0;
        int d;
        int second = 0;
        d = indexGenerator(option,id);
        while (d >= arreglo.length){
            d-= arreglo.length;
            if (d < 0)
                d = 0;
        }
        if(arreglo_anidado[d][second] == id){
            return d + "," + second;
        }else{
            while(arreglo_anidado[d][second] != id){
                second++;
                contador++;
                if (arreglo_anidado[d][second] == id)
                    return d + "," + second;
            }
        }
        return "";
    }

    public void setBidimensionalArray(int[][]arreglo_anidado){
        this.arreglo_anidado = arreglo_anidado;
    }

    public String encadenamiento(int dato , int option , ListaSimple lista){

        String datafound ="";

        clave = indexGenerator(option,dato);
        while (clave > arreglo.length){
            clave-= arreglo.length;
            if (clave < 1)
                clave = 1;
        }

        NodeS<NodeS> result = lista.get(clave);
        String stringaux =""+result.getData();
        if (stringaux.compareTo(""+dato)==0){
            datafound =stringaux;
            return datafound;
        }else {
            if (result.getCollision() != null){
                NodeS<NodeS> aux = result.getCollision();
                while (aux != null){
                    contador++;

                    stringaux =""+aux.getData();
                    if (stringaux.compareTo(""+dato)==0){
                        System.out.println("Encotrado: "+result.getData());
                        datafound = ""+result.getData();
                        return datafound;
                    }
                    aux = aux.getCollision();
                }
            }
        }
        return datafound;
    }

    public int getContador(){
        return contador;
    }

    public int getClave() {
        return clave;
    }

    private int indexGenerator(int option,int id){
        int d;
        switch (option){
            case 1:
                d = fh.func_Modulo(id,arreglo.length);
                return d;
            case 2:
                d = fh.func_Cuadrado(id,arreglo.length);
                return d;
            case 3:
                d = fh.func_Truncamiento(id,arreglo.length);
                return d;
            case 4:
                d = fh.func_Plegamiento(id,arreglo.length);
                return d;
            default:
                return 0;
        }
    }
}
