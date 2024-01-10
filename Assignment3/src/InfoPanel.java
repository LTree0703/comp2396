import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class inherits from JPanel, and it is used to display the text 
 * at the bottom of the game.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
class InfoPanel extends JPanel {
		JLabel infoMsgLabel;
		JLabel remMoneyMsgLabel;
		final String remMoneyMsg = "Amount of money you have: $";
		
		/**
		 * This constructor method accepts one integer representing 
		 * the amount of money the player currently has. It would also instantiate
		 * two JLabels used to show messages on the screen. 
		 * @param money An integer representing the amount of money the player currently has.
		 */
		public InfoPanel(int money) {
			infoMsgLabel = new JLabel("Please place your bet!");
			remMoneyMsgLabel = new JLabel(remMoneyMsg + money);
			this.add(infoMsgLabel, BorderLayout.SOUTH);
			this.add(remMoneyMsgLabel, BorderLayout.SOUTH);
		}
		
		/**
		 * This setter method updates the infoMsgLabel with the input argument msg.
		 * @param msg A String representing the new message to be shown on the screen.
		 */
		public void updateInfoMsg(String msg) {
			infoMsgLabel.setText(msg);
		}
		
		/**
		 * This setter method accepts no argument, and it is used only when the player
		 * has lost all the money (i.e. the game is over) where the remMoneyMsgLabel
		 * should not show any texts. 
		 */
		public void updateRemMoneyMsg() {
			remMoneyMsgLabel.setText("");
		}
		
		/**
		 * This setter method updates the remMoneyMsgLabel with the updated amount of 
		 * money the player has and show it to the screen. 
		 * @param money An integer representing the updated amount of money. 
		 */
		public void updateRemMoneyMsg(int money) {
			remMoneyMsgLabel.setText(remMoneyMsg + money);
		}
	}
