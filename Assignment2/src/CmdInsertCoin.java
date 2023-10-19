
public class CmdInsertCoin implements Command {
	
	@Override
	public String execute(VendingMachine v, String[] cmdParts) {
		Integer c = Integer.valueOf(cmdParts[1]);
		// Add the coin to Coin Slot
		v.insertCoin(c);
		return "Inserted a $" + c + " coin. $" + v.getTotalInsertedCoins() + " in total.";
	}
}
