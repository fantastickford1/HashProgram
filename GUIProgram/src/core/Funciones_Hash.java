package core;
public class Funciones_Hash{

	//////RETORNA EL VALOR CALCULADO CON LA FUNCION MODULO
	public int func_Modulo(int id, int size){

		return (id%size)+1;
	}

	//RETORNA EL VALOR CALCULADO CON LA FUNCION CUADRADO
	public int func_Cuadrado(int id, int size){

		if(id<=3 && id>=0){//numeros con cuadrado menores a 10 no tiene caso evaluar digitos centrales
			double cuadrado = Math.pow(id,2);
			int numero = (int) cuadrado;
			return numero+1;
		}

		double cuadrado = Math.pow(id,2); //obtiene cuadrado del numero
		int numero = (int) cuadrado;// pasa entero la variable cuadrado
		int digitos ; //digitos a tomar del centro
		String c = size+"";
		char[] cosa = c.toCharArray();
		digitos = cosa.length-1;

		String auxiliar = numero+"";
		char[] digits = auxiliar.toCharArray();//paso a un arreglo los digitos del numero cuadratico
		int val_central = (digits.length-1)/2;

		if(digitos>digits.length){//Si la longitud de un indice supera a la longitud de digitos del numero cuadratico, regreso el cuadradro+1 directamente
			return numero+1;
		}

		int aux = 0;
		int begin = val_central;
		int end = val_central;
		boolean desicion = true;
		while(aux < digitos-1){

			if(desicion){
				end = val_central+1;
				desicion = false;
			}
			else{
				begin = val_central-1;
				desicion = true;
			}
			aux++;
		}

		String value = "";
		for(int i = begin; i<=end; i++){
			value += digits[i];
		}

		int valor_final = Integer.parseInt(value);
		return valor_final+1;
		
	}

	public int func_Plegamiento(int id, int size){

		if(id<9){
			return id+1;
		}
		String digit1="",digit2="";
		int num1, num2;
		//NECESITO DETERMINAR LA CANTIDAD DE DIGITOS DEL TAMAÑO DEL ARRAY
		int determina = getNumDigits(size)-1;//numero de digitos menos significativos a tomar
        if(determina==0) {
            determina=1;
        }

		String aux = id+"";//pasa el numero a string
		char[] digits = aux.toCharArray();//separo el numero por sus caracteres
		int tamano = digits.length-1;
		int x=0;

		for(int i=0;i<=tamano;i++){// separo los digitos en dos partes
			if(i<=tamano/2) {
				digit1 += digits[i];
				System.out.println(digit1);
			}else
				digit2 += digits[i];
		}

		num1 = Integer.parseInt(digit1);
		num2 = Integer.parseInt(digit2);

		int sum = num1+num2;//YA REALIZADA LA SUMA, SE PROCEDE A EXTRAER LOS DIGITOS MENOS SIGNIFICATIVOS
		aux = sum+"";
		digits = aux.toCharArray();//parto el resultado de la suma en caracteres
		digit1 = "";
        if (digits.length==1){
            digit1+=digits[0];
            return Integer.parseInt(digit1)+1;
        }
		
		for(int i=0;i<digits.length;i++){
			if(i>=(digits.length-determina))
				digit1 += digits[i];
		}
		sum = Integer.parseInt(digit1);
		return sum+1;
	}

	public int func_Truncamiento(int id,int size){

		String aux = id+"";
		int limit = getNumDigits(size)-1;//numero de digitos a tomar 
		char[] digits = aux.toCharArray();
		int tamano = digits.length-1;
		String num ="";

		int iter = 0;
		for(int i=0;i<=tamano && iter<=limit ;i++){
			if(i%2==0){
				num +=digits[i];
			}
			iter++;
		}
		double resultado = Double.parseDouble(num);
		int valor_final = (int) resultado;
		return valor_final+1;
	}

	private int getNumDigits(int num){
		String aux = num+"";
		char[] aux2 = aux.toCharArray();
		return aux2.length;
	}

}