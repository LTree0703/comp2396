import java.io.*;
public class Question {

	public static void main(String[] args) throws IOException {
		BufferedReader inData = new BufferedReader(new InputStreamReader(System.in));
		int n, m;
		
		System.out.print("n: ");
		n = Integer.parseInt(inData.readLine());
		
		System.out.print("m: ");
		m = Integer.parseInt(inData.readLine());
		
		int total = n + exchange(n, m);
		System.out.println("You can have at most: " + total);
		
	}
	
	static int exchange(int n, int m) {
		int more = n / m;
		if (more + n % m < m) { // cannot exchange again
			return more;
		}
		else {
			return more + exchange(more + n % m, m);
		}
	}

}
