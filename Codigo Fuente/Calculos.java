import java.math.BigInteger;
import java.math.BigDecimal;
public class Calculos{
	public static long factorial(int f){
		if(f==0||f==1)
			return 1;
		else return f*(factorial(f-1));
	}
	public static long combinacion(int n,int r){
		long c;
		c=factorial(n)/((factorial(r))*(factorial(n-r)));
		return c;
	}
	public static BigInteger Factorial(int n){
		BigInteger resultado = BigInteger.ONE;
		int i;
		for(i=1;i<=n;i++){
			resultado=resultado.multiply(new BigInteger(i+""));
		}
		return resultado;
	}
	public static BigInteger Combinacion(int n, int r){
		BigInteger resultado = BigInteger.ONE;
		BigInteger Numerador = Factorial(n);
		BigInteger Denominador = Factorial(n-r).multiply(Factorial(r));
		resultado=Numerador.divide(Denominador);
		return resultado;
	}
	public static int max(int a,int b){
		if(a<b)
			return b;
		else return a;
	}
	public static int min(int a,int b){
		if(a<b)
			return a;
		else return b;
	}
	public static int[] DecimalFraccion(double a){
		int numeros[] = new int[2];
		String valor =""+a;
		int i=0,j;
		valor.replace("-","");
		for(i=0;i<valor.length();i++){
			if(valor.charAt(i)=='.'){
				break;
			}
		}
		if((valor.length()-i-1)==1&&valor.charAt(i+1)=='0'){
			numeros[0]=Double.valueOf(a).intValue();
			numeros[1]=1;
		}else{
			j=valor.length()-i-1;
			numeros[0]=Double.valueOf(a*(Math.pow(10,j))).intValue();
			numeros[1]=Double.valueOf(Math.pow(10,j)).intValue();
		}
		return numeros;
	}
}