import javax.swing.*;

/**
 * This class inherits from JLabel, and it stores the rank, the status of whether
 * it has been shown, and the image of its card face.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
class Card extends JLabel {
	private int rank;
	private boolean hasFlipped;
	private ImageIcon cardFace;

	/**
	 * This constructor method accepts two arguments, and initialize the object
	 * according to the arguments and find the corresponding image.
	 * @param rank An integer representing the rank of the Card.
	 * @param suit An integer representing the suit of the Card. 
	 */
	public Card(int rank, int suit) {
		this.rank = rank;
		this.hasFlipped = false;
		this.cardFace = new ImageIcon("src/Images/card_" + suit + rank + ".gif");
		this.setIcon();
	}
	
	/**
	 * This method negates its hasFlipped status, and regenerates the icon. 
	 */
	public void flipCard() {
		hasFlipped = !hasFlipped;
		this.setIcon();
	}
	
	/**
	 * This method sets the image to be displayed on screen, according to 
	 * its hasFlipped status and the path to the image file. 
	 */
	public void setIcon() {
		if (!hasFlipped) {
			super.setIcon(new ImageIcon("src/Images/card_back.gif"));
		}
		else {
			super.setIcon(cardFace);
		}
	}
	
	/**
	 * This getter method returns the rank of the Card.
	 * @return An integer representing the rank of the Card.
	 */
	public int getRank() {
		return rank;
	}
	
}
