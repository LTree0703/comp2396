import java.io.*;
public class example {
	public static void main(String args[]) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader inData = new BufferedReader(isr);
		
		String str = inData.readLine();
		Double n = Double.parseDouble(str);
		
		double sum = 0;
		while (n >= 0) {
			sum += n;
			str = inData.readLine();
			n = Double.parseDouble(str);
		}
		System.out.println("Sum of the numbers: " + sum);
	}
}
