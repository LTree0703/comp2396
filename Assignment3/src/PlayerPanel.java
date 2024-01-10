/**
 * This class inherits the CardPanel class to store the hand of the player.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
class PlayerPanel extends CardPanel {
	/**
	 * This method allows the player to have their card redrawn
	 * by clicking the corresponding button.
	 * @param index This is the index of the card that the player wants to replace.
	 */
	public void redrawCard(int index) {
		if (index > 3 || index < 0) return;
		this.remove(hand[index]);
		hand[index] = CardDeck.drawCard();
		hand[index].flipCard();
		this.add(hand[index], index);
		this.revalidate();
	}
}