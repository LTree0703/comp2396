import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * This class inherits the JButton class, and it defines the appearance
 * of the button and the event after clicking the button.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
class ReplaceButton extends JButton {
	private PlayerPanel playerPanel;
	private RpCardBtnPanel rpPanel;
	
	/**
	 * This constructor method accepts three reference variables which are used to 
	 * handle the ActionEvents, and it also sets the appearance of the button.
	 * @param refCard An integer representing the index of the card that the would be 
	 * replaced if this button is clicked.
	 * @param playerPanel A variable referencing the current PlayerPanel.
	 * @param rpPanel A variable referencing the current RpCardBtnPanel.
	 */
	public ReplaceButton(int refCard, PlayerPanel playerPanel, RpCardBtnPanel rpPanel) {
		super("Replace Card " + (refCard + 1));
		this.playerPanel = playerPanel;
		this.rpPanel = rpPanel;
		this.setFocusable(false);
		this.setBackground(Color.green);
		this.setEnabled(false);
		this.addActionListener(new ReplaceButtonListener(refCard, this));
	}
	
	/**
	 * This inner class implements the ActionListener interface, and 
	 * it serves as the Listener class for each ReplaceButton.
	 */
	class ReplaceButtonListener implements ActionListener {
		private int refCard;
		private JButton button;
		/**
		 * This constructor method would store the reference variables (refCard, button)
		 * for handling events when the button is clicked. 
		 * @param refCard An integer representing the index of the card that the would be 
		 * replaced if this button is clicked.
		 * @param button The ReplaceButton the Listener class belongs to. 
		 */
		public ReplaceButtonListener(int refCard, JButton button) {
			this.refCard = refCard;
			this.button = button;
		}
		
		/**
		 * This method specifies the action after the button is clicked, namely calling
		 * the playerPanel to have the Card redrawn, disabling the button clicked, and 
		 * call the RpCardBtnPanel to increment the number of replacements by 1;
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			playerPanel.redrawCard(refCard);
			button.setEnabled(false);
			rpPanel.addNumOfReplacements();
		}
	}
}