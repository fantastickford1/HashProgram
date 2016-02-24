package core;

public class Colisiones {

    private int[] arreglo;
    private int[][] arreglo_anidado;
    private Funciones_Hash fh;
    private ListaSimple lista = new ListaSimple();
    public int contador;
    private int crash = 0;

  /**
   * Constructor que recibe dos parametros para la construccion de un objeto Colisiones.
   * @param arreglo arreglo donde se acomodara los datos dependiento los indices creados por las funciones de esta clase.
   * @param fh Objeto de tipo Funciones_Hash para la acomodacion de los datos dependiendo los indices creados por las funciones de esta clase.
     */
    public Colisiones(int[] arreglo, Funciones_Hash fh){
        this.arreglo = arreglo;
        this.fh = fh;
        this.arreglo_anidado = null;
    }

  /**
   * Metodo que resuelve la colision mediante la prueba lineal
   * @param option Funcion Hash a usar, 1 - Modulo, 2 - Cuadratica, 3 - Truncamiento, 4 - Plegamiento.
   * @param id Identificador o clave del dato a guardar.
   * @return Regresa la posicion disponible para agregar el dato con id especifica o 0 si algo sale mal.
   */
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
        if (arreglo[d] == 0){
          return d;
        }else{
            dx = d + 1;
            while (dx >= arreglo.length){
                dx-= arreglo.length;
                if (dx < 0)
                    dx = 0;
            }
            while ((dx <= arreglo.length) && (arreglo[dx] != id) && (dx != d) && (arreglo[dx] != 0)){
              dx = dx + 1;
              contador++;
                if (dx == arreglo.length){
                    dx = 0;
                }
            }
            if (arreglo[dx] == 0){
                return dx;
            }
        }
        return -1;
    }

  /**
   * Metodo que resuelve la colision mediante la prueba cuadratica
   * @param option Funcion Hash a usar, 1 - Modulo, 2 - Cuadratica, 3 - Truncamiento, 4 - Plegamiento.
   * @param id Identificador o clave del dato a guardar.
   * @return Regresa la posicion disponible para agregar el dato con id especifica o 0 si algo sale mal.
   */
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
        if (arreglo[d] == 0){
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
            if (arreglo[dx] == 0){
                return dx;
            }
        }
        return -1;
    }

  /**
   * Metodo que resuelve la colision mediante la doble direccion
   * @param option Funcion Hash a usar, 1 - Modulo, 2 - Cuadratica, 3 - Truncamiento, 4 - Plegamiento.
   * @param id Identificador o clave del dato a guardar.
   * @return Regresa la posicion disponible para agregar el dato con id especifica o 0 si algo sale mal.
   */
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
        if (arreglo[d] == 0){
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
            if (arreglo[dx] == 0){
                return dx;
            }
        }
        return -1;
    }

    /**
     * Metodo que resuelve la colision mediante arreglos anidados
     * @param option Funcion Hash a usar, 1 - Modulo, 2 - Cuadratica, 3 - Truncamiento, 4 - Plegamiento.
     * @param id Identificador o clave del dato a guardar.
     * @return Regresa las coordenadas de una posicion vacia del arreglo bidimensional.
     */
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
        if(arreglo_anidado[d][second] == 0){
            return d + "," + second;
        }else{
            while(arreglo_anidado[d][second] != 0){
                second++;
                contador++;
                if (arreglo_anidado[d][second] == 0)
                    return d + "," + second;
            }
        }
        return "";
    }

    /**
     * Metodo para setear el arreglo anidado a usar para el metodo de arreglosAnidados
     * @param arreglo_anidado arreglo bidimensional a usar en el metodo de arreglosAnidados
     */
    public void setBidimensionalArray(int[][]arreglo_anidado){
        this.arreglo_anidado = arreglo_anidado;
    }

    /** Metodo para encadenar en una lista las claves que colisionan con otras claves
     * @param tamaño Numero de datos
     * @param datos Arreglo de datos para almacenar
     * @param option Opsion para generar su indice
     */
    public String[] Encadenarminto(int tamaño,int[] datos,int option){
        String[] Out = new String[tamaño];
        for (int i = 1;i <= tamaño;i++){
            lista.add("");
            Out[i-1] = i+" ->";
        }
        for (int i = 1;i <= tamaño;i++){
            int indexDato = indexGenerator(option,datos[i-1]);
            while (indexDato > arreglo.length){
                indexDato-= arreglo.length;
                if (indexDato < 1)
                    indexDato = 1;
            }
            lista.modificarDato(indexDato,datos[i-1]);
        }
        for (int i = 1; i <= tamaño;i++){
            NodeS<NodeS> aux = lista.get(i);
            Out[i-1] += aux.getData();
            while (aux.getCollision() != null){
                aux = aux.getCollision();
                Out[i-1] +=" -> "+ aux.getData() ;
            }
        }
        return Out;
    }

    public ListaSimple getLista(){
        return this.lista;
    }

    /**
   * Metodo que se usa como auxiliar para el uso correcto de la funcion hash dependiendo la opcion elegida
   * @param option Funcion Hash a usar, 1 - Modulo, 2 - Cuadratica, 3 - Truncamiento, 4 - Plegamiento.
   * @param id Identificador o clave del dato a guardar.
   * @return Resultado de la funcion hash elegida.
     */
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
