
import java.util.ArrayList;

public class CmdPurchase implements Command {

	@Override
	public String execute(VendingMachine v, String[] cmdParts) {
		String product = cmdParts[1];
		int productIndex = v.getProduct(product);
		int insertedValue = v.getTotalInsertedCoins();
		// Check if product is still in stock and if the coins inserted are enough to buy the product
		if (productIndex == -1) {
			return product + " is out of stock!";
		}
		else if (!v.coinsEnoughToBuy(productIndex)) {
			return "Not enough credit to buy " + product + "! Inserted $" + insertedValue + " but needs $" + v.getProductPrice(productIndex) + ".";
		}

		int change = v.buy(productIndex);
		String result = "Dropped " + product + ". Paid $"  + insertedValue + ".";
		if (change == 0) {
			result += " No change.";
		}
		else {
			ArrayList<Integer> changeCoins = v.changeCoins(change);
			changeCoins.sort(null);
			result += " Your change: ";
			for (int i = 0; i < changeCoins.size(); i++) {
				result += "$" + changeCoins.get(i);
				if (i == changeCoins.size() - 1) {
					result += ".";
				}
				else {
					result += ", ";
				}
			}
		}
		return result;
	}
	
}
