import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Taxi {
    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader inData = new BufferedReader(isr);

        int fee = Integer.parseInt(inData.readLine());
        int baggage = 0;
        int distance = 2000;
        fee -= 27;
        if (fee % 3 == 1) {
            baggage = 1;
            distance += ((fee - 7) / 3) * 200;
        }
        else if (fee % 3 == 2) {
            baggage = 2;
            distance += ((fee - 14) / 3) * 200;
        }
        else {
            distance += (fee / 3) * 200;
        }
        
        System.out.println("The distance (in m) and number of baggage is " + distance + " and " + baggage + " respectively.");
    }
}
