import java.util.ArrayList;
public class CmdRejectCoins implements Command {

	@Override
	public String execute(VendingMachine v, String[] cmdParts) {
		int sum = v.getTotalInsertedCoins();
		ArrayList<Integer> rejectedCoins = v.rejectCoins();
		String result;
		if (rejectedCoins.size() == 0) {
			result = "Rejected no coin!";
		}
		else {
			result = "Rejected ";
			for (int i = 0; i < rejectedCoins.size(); i++) {
				result += "$" + rejectedCoins.get(i);
				if (i == rejectedCoins.size() - 1) {
					result += ". ";
				}
				else {
					result += ", ";
				}
			}
			result += "$" + sum + " in total.";
		}
		return result;
	}
}
