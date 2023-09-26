import java.io.*;
public class example2 {
	
	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader inData = new BufferedReader(isr);
		
		System.out.print("Enter your name: ");
		String name;
		try {
			name = inData.readLine();
		}
		catch (IOException e) {
			name = "";
		}
		System.out.println("Welcome " + name);
	}

}
