public class Colisiones {

  Object[] arreglo;

  public Colisiones(Object[] arreglo){
    this.arreglo = arreglo;
  }

    Object[][] arregloAnidado;
    Funciones_Hash fh = new Funciones_Hash();
    public void arreglosAnidados(int opcion){
      arregloAnidado = new Object[arreglo.length][arreglo.length];
      int y=0;
      int Hk=0;
      //funcion hash
      do{
        switch (opcion) {
          case 1:
          try{
            Hk = fh.func_Modulo((int)arreglo[y],arregloAnidado.length);
            break;
          }catch (Exception e) {}
          case 2:
          case 3:
          case 4:
        }
        try{
          for (int x=0; x<arregloAnidado.length; x++) {
            if (arregloAnidado[Hk][x] == null) {
              arregloAnidado[Hk][x] = (int)arreglo[y];
              String ps = hk+","+ x;
              return
            }
          }
        }catch (Exception e) {}
        y++;
      }while (y <arregloAnidado.length);

      /*System.out.println("\n");
      for (int i=0; i<10; i++) {
        System.out.println("Valor: "+arregloAnidado[4][i]);
      }*/
  }
  ListaSimple listaPrincipal = new ListaSimple();
  public void encadenamiento(int opcion){
    int Hk=0;
    for (int x=0; x<arreglo.length; x++) {
      listaPrincipal.add(new ListaSimple());
    }
      for (int i=0; i<10; i++){
        switch (opcion) {
              case 1:
              try{
                Hk = fh.func_Modulo((int)arreglo[i],10);
                ListaSimple listaSecundaria = (ListaSimple)listaPrincipal.get(Hk).getData();
                listaSecundaria.add(arreglo[i]);
                //System.out.println("Valor: "+listaPrincipal.get(Hk).getData()+"Posicion: "+Hk);
                //System.out.println("Salida: "+listaSecundaria.toString());
                break;
              }catch (Exception e) {}
              //default: break;
              case 2:
              case 3:
              case 4:
        }
      }
  }


}
