import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader inData = new BufferedReader(isr);

		System.out.println("Enter your name: ");

		String name = inData.readLine();

		System.out.println("Hi " + name);
	}
}
