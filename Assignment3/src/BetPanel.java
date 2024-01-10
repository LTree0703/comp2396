import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class inherits from JPanel, and it stores a JLabel, a JTextField, and two 
 * JButtons. Also, there are other reference variables used to handle events including
 * making bet, starting the game, and getting the result.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
class BetPanel extends JPanel {
	App app;
	private JLabel label;
	private JTextField inputTextField;
	private JButton startButton;
	private JButton resultButton;
	private ReplaceButton[] rpButtons;
	/**
	 * This constructor method instantiates a JLabel, a JTextField, 2 JButtons, and all the 
	 * reference variables and Listeners that are needed for event handling. 
	 * @param rpButtons An array of three ReplaceButtons.
	 * @param gamePanel A variable referencing the current GamePanel.
	 * @param msgDialog A variable referencing the current MessageDialog.
	 * @param app A variable referencing the current App class.
	 */
	public BetPanel(ReplaceButton[] rpButtons, GamePanel gamePanel, MessageDialog msgDialog, App app) {
		label = new JLabel("Bet: $");
		inputTextField = new JTextField(10);
		startButton = new JButton("Start");
		resultButton = new JButton("Result");
		this.rpButtons = rpButtons;
		this.add(label);
		this.add(inputTextField);
		this.add(startButton);
		this.add(resultButton);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int bet = 0;
				try {
					bet = Integer.parseInt(inputTextField.getText());
				}
				catch (NumberFormatException nfe) {
					msgDialog.invalidInputMsg();
					inputTextField.setText("");
					return;
				}
				if (bet < 0) {
					inputTextField.setText("");
					return;
				}
				if (gamePanel.getMoney() < bet) {
					msgDialog.notEnoughMoneyMsg();
					inputTextField.setText("");
					return;
				}
				gamePanel.setBet(bet);
				gamePanel.infoPanel.updateInfoMsg("Your current bet is: $" + bet + ".");
				gamePanel.playerPanel.revealCards();
				startButton.setEnabled(false);
				setRpButtons(true);
			}
		});
		
		resultButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				disableAll();
				gamePanel.dealerPanel.revealCards();
				if (gamePanel.playerCanWin()) {
					msgDialog.winMsg();
					gamePanel.winMoney();
				}
				else {
					msgDialog.loseMsg();
					gamePanel.loseMoney();
				}
				
				if (gamePanel.isGameOver()) {
					gamePanel.infoPanel.updateInfoMsg("You have no more money! Please start a new game!");
					gamePanel.infoPanel.updateRemMoneyMsg();
					msgDialog.gameOverMsg();
				}
				else {
					app.reset(gamePanel.getMoney());
				}
				
			}
		});
	}
	
	/**
	 * This method set the status all three ReplaceButtons, according to
	 * argument given.
	 * @param enabled A boolean value that determines whether the three
	 * ReplaceButtons should be enabled.  
	 */
	public void setRpButtons(boolean enabled) {
		for (int i = 0; i < 3; i++) {
			rpButtons[i].setEnabled(enabled);
		}
	}
	
	/**
	 * This method would disable all JButtons and the inputTextField, called
	 * when the resultButton is clicked.
	 */
	public void disableAll() {
		setRpButtons(false);
		inputTextField.setEnabled(false);
		startButton.setEnabled(false);
		resultButton.setEnabled(false);
	}
}