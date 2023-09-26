import java.io.*;
public class Sample {

	public static void main(String[] args) throws IOException {
		BufferedReader inData = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("n: ");
		int n = Integer.parseInt(inData.readLine());
		
		System.out.print("m: ");
		int m = Integer.parseInt(inData.readLine());
		
		int total = n;
		
		while (n >= m) {
			n = n - m;
			n = n + 1;
			total++;
		}
		
		System.out.println("You can have at most: " + total);
	}

}
