import javax.swing.*;

/**
 * This class stores all the JOptionPane pop-up dialogs used in this game.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
public class MessageDialog {
	private GamePanel p;
	
	/**
	 * This is a constructor method to let the class recognize the game panel. 
	 * @param panel A variable referencing the current GamePanel
	 */
	public MessageDialog(GamePanel panel) {
		this.p = panel;
	}
	
	/**
	 * This method calls the JOptionPane to show a pop-up message 
	 * that the player has won the round.
	 */
	public void winMsg() {
		JOptionPane.showMessageDialog(p, "Congratulations! You win this round!");
	}
	
	/**
	 * This method calls the JOptionPane to show a pop-up message 
	 * that the player has lost the round.
	 */
	public void loseMsg() {
		JOptionPane.showMessageDialog(p, "Sorry! The dealer wins this round!");
	}
	
	/**
	 * This method calls the JOptionPane to show a pop-up message 
	 * that the player has lost all the money so that he/she has to start
	 * a new game. 
	 */
	public void gameOverMsg() {
		JOptionPane.showMessageDialog(p, "Game over!\nYou have no more money!\nPlease start a new game!");
	}
	
	/**
	 * This method calls the JOptionPane to show a pop-up message 
	 * that the player has inputed an illegal bet.
	 */
	public void invalidInputMsg() {
		JOptionPane.showMessageDialog(p, "Invalid input. Please try again.");
	}
	
	/**
	 * This method calls the JOptionPane to show a pop-up message 
	 * that the player does not have enough money to have 
	 * his/her desired amount of bet.
	 */
	public void notEnoughMoneyMsg() {
		JOptionPane.showMessageDialog(p, "Not enough money!\nPlease try again.");
	}
}
