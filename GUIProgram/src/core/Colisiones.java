package core;

public class Colisiones {

  int[] arreglo;
  Funciones_Hash fh;

  /**
   * Constructor que recibe dos parametros para la construccion de un objeto Colisiones.
   * @param arreglo arreglo donde se acomodara los datos dependiento los indices creados por las funciones de esta clase.
   * @param fh Objeto de tipo Funciones_Hash para la acomodacion de los datos dependiendo los indices creados por las funciones de esta clase.
     */
  public Colisiones(int[] arreglo, Funciones_Hash fh){
    this.arreglo = arreglo;
    this.fh = fh;
  }

  /**
   * Metodo que resuelve la colision mediante la prueba lineal
   * @param option Funcion Hash a usar, 1 - Modulo, 2 - Cuadratica, 3 - Truncamiento, 4 - Plegamiento.
   * @param id Identificador o clave del dato a guardar.
   * @return Regresa la posicion disponible para agregar el dato con id especifica o 0 si algo sale mal.
   */
  public int prueba_lineal(int option, int id){
    int d;
    int dx;
    d = indexGenerator(option,id);
    if (arreglo[d] == 0){
      return d;
    }else{
      dx = d + 1;
      while ((dx <= arreglo.length) && (arreglo[dx] != id) && (dx != d) && (arreglo[dx] != 0)){
        dx = dx + 1;
        if (dx == arreglo.length){
          dx = 0;
        }
      }
      if (arreglo[dx] == 0){
        return dx;
      }
    }
    return 0;
  }

  /**
   * Metodo que resuelve la colision mediante la prueba cuadratica
   * @param option Funcion Hash a usar, 1 - Modulo, 2 - Cuadratica, 3 - Truncamiento, 4 - Plegamiento.
   * @param id Identificador o clave del dato a guardar.
   * @return Regresa la posicion disponible para agregar el dato con id especifica o 0 si algo sale mal.
   */

  public int prueba_cuadratica(int option, int id) {
    int d;
    int dx;
    int i;
    d = indexGenerator(option,id);
    if (arreglo[d] == 0){
      return d;
    }else{
      i = 1;
      dx = d + (int) Math.pow(i,2);
      while ((arreglo[dx] != id) && (arreglo[dx] != 0)){
        i++;
        dx = d + (int) Math.pow(i,2);
        if (dx > arreglo.length){
          i = 0;
          dx = 0;
          d = 1;
        }
      }
      if (arreglo[dx] == 0){
        return dx;
      }
    }
    return 0;
  }

  /**
   * Metodo que resuelve la colision mediante la doble direccion
   * @param option Funcion Hash a usar, 1 - Modulo, 2 - Cuadratica, 3 - Truncamiento, 4 - Plegamiento.
   * @param id Identificador o clave del dato a guardar.
   * @return Regresa la posicion disponible para agregar el dato con id especifica o 0 si algo sale mal.
   */

  public int dobleDireccion(int option, int id){
    int d;
    int dx;
    d = indexGenerator(option,id);
    if (arreglo[d] == 0){
      return d;
    }else{
      dx = indexGenerator(option,d);
      while ((dx <= arreglo.length) && (arreglo[dx] != id) && (arreglo[dx] != 0) && (dx != d) ){
        dx = indexGenerator(option,dx);
      }
      if (arreglo[dx] == 0){
        return dx;
      }
    }
    return 0;
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
