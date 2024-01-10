import java.util.Arrays;
import java.util.Random;

/**
 * This class stores a static array of integers, representing the card deck drawn
 * without replacement. The static methods defined allows other classes to draw
 * cards from the card deck. 
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
class CardDeck {
	static int[] cardDeck = new int[52];
	private static int deckTop = 0;
	/**
	 * This method initializes the cardDeck, which would be called
	 * at the beginning of the game and after each round.
	 */
	public static void initCardDeck() {
		Random rand = new Random();
		for (int i = 0; i < cardDeck.length; i++) {
			cardDeck[i] = i;
		}
		
		for (int i = 0; i < cardDeck.length; i++) {
			int randomIndexToSwap = rand.nextInt(cardDeck.length);
			int temp = cardDeck[randomIndexToSwap];
			cardDeck[randomIndexToSwap] = cardDeck[i];
			cardDeck[i] = temp;
		}
	}
	/**
	 * This static method draws a card from cardDeck, increment the index representing
	 * the top of the deck by 1, and then returns a Card object instantiated according
	 * to the digit drawn
	 * .
	 * and  
	 * @return A reference variable to a new Card object.
	 */
	public static Card drawCard() {
		int digit = cardDeck[deckTop];
		deckTop++;
		return new Card(digit / 4 + 1, digit % 4 + 1);
	}
}