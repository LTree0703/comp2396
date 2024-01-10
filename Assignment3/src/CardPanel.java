import javax.swing.*;

/**
 * This abstract class inherits from the JPanel class to store the hand
 * as well as to display the image of the card on the screen.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
abstract class CardPanel extends JPanel {
	protected Card[] hand;
	/**
	 * This constructor method initializes the protected Card array by drawing
	 * three Cards from CardDeck. Then the Cards will be added into the JPanel.
	 */
	public CardPanel() {
		hand = new Card[]{
			CardDeck.drawCard(),
			CardDeck.drawCard(),
			CardDeck.drawCard(),
		};
		
		for (int i = 0; i < 3; i++) {
			this.add(hand[i]);
		}
	}
	
	/**
	 * This getter method returns the hand of somebody.
	 * @return Card[] An array of Cards, representing somebody's hand.
	 */
	public Card[] getHand() {
		return hand;
	}
	
	/**
	 * This method flips all the cards in hand such that the face of the card
	 * instead of the back would be shown.
	 */
	public void revealCards() {
		for (int i = 0; i < 3; i++) {
			hand[i].flipCard();
		}
	}
	
	/**
	 * This method returns the number of special cards (i.e., J, Q, K) 
	 * that is in somebody's hand, which is used to check for rule 1.
	 * @return The number of special cards.
	 */
	public int getNumOfSpecialCards() {
		int num = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].getRank() >= 10) {
				num++;
			}
		}
		return num;
	}
	
	/**
	 * This method returns the remainder of the face values, other than the special
	 * cards, after dividing by 10, which is used to check for rule 2.
	 * @return The remainder of the face values, other than the special
	 * cards, after dividing by 10.
	 */
	public int getRemainderOfFaceValuesAfterDividedBy10() {
		int num = 0;
		int rank;
		for (int i = 0; i < hand.length; i++) {
			rank = hand[i].getRank();
			if (rank < 10) {
				num += rank;
			}
		}
		return num % 10;
	}
}
