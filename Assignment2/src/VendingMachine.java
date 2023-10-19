
import java.util.ArrayList;

public class VendingMachine {
	// ArrayList of Integers represents inserted coins in Coin Slot
	private ArrayList<Integer> insertedCoins;
	
	// ArrayList of Product represents inventories of products
	private ArrayList<Product> products;

	public VendingMachine() {
		insertedCoins = new ArrayList<Integer>();
		products = new ArrayList<Product>();
	}

	public void addProduct(Product p) {
		products.add(p);
	}
	
	public void insertCoin(Integer c) {
		insertedCoins.add(c);
		insertedCoins.sort(null);
	}

	public ArrayList<Integer> rejectCoins() {
		ArrayList<Integer> rejectedCoins = insertedCoins;
		insertedCoins = new ArrayList<Integer>();
		return rejectedCoins;
	}
	
	public int getNumOfInsertedCoins() {
		return insertedCoins.size();
	}
	
	public int getTotalInsertedCoins() {
		int total = 0;
		for (int coin: insertedCoins) {
			total += coin;
		}
		return total;
	}

	public int getProduct(String product) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().equalsIgnoreCase(product) && products.get(i).getQuantity() > 0) {
				return i;
			}
		}
		return -1;
	}

	public int getProductPrice(int productIndex) {
		return products.get(productIndex).getPrice();
	}
	
	public boolean coinsEnoughToBuy(int productIndex) {
		return getTotalInsertedCoins() >= products.get(productIndex).getPrice();
	}

	public int buy(int productIndex) {
		Product p = products.get(productIndex);
		int change = getTotalInsertedCoins() - p.getPrice();
		p.sell();
		insertedCoins = new ArrayList<Integer>();
		return change;
	}

	public ArrayList<Integer> changeCoins(int change) {
		ArrayList<Integer> c = new ArrayList<Integer>();
		while (change >= 10) {
			c.add(10);
			change -= 10;
		}
		while (change >= 5) {
			c.add(5);
			change -= 5;
		}
		while (change >= 2) {
			c.add(2);
			change -= 2;
		}
		while (change >= 1) {
			c.add(1);
			change -= 1;
		}
		return c;
	}

}
