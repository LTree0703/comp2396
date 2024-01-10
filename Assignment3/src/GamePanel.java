import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * This class inherits from JPanel, and it stores all the panels, being the 
 * main panel for the game.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
public class GamePanel extends JPanel {
	DealerPanel dealerPanel;
	PlayerPanel playerPanel;
	RpCardBtnPanel rpCardBtnPanel;
	BetPanel betPanel;
	InfoPanel infoPanel;
	MessageDialog msgDialog;
	
	private int money;
	private int bet;
	/**
	 * This constructor method instantiates all the panels and dialogs (DealerPanel, 
	 * PlayerPanel, RpCardBtnPanel, BetPanel, InfoPanel, and MessageDialog), and set
	 * the amount of money according to the argument given.
	 * @param money An integer representing the amount of money the player should have. 
	 * @param app A variable referencing the App object, which is used by the current 
	 * BetPanel object. 
	 */
	public GamePanel(int money, App app) {
		msgDialog = new MessageDialog(this);
		dealerPanel = new DealerPanel();
		playerPanel = new PlayerPanel();
		rpCardBtnPanel = new RpCardBtnPanel(playerPanel);
		betPanel = new BetPanel(rpCardBtnPanel.getRpButtons(), this, msgDialog, app);
		infoPanel = new InfoPanel(money);
		
		this.money = money;
	
		this.setLayout(new GridLayout(5, 1));
		this.setSize(getPreferredSize());
		this.add(dealerPanel);
		this.add(playerPanel);
		this.add(rpCardBtnPanel);
		this.add(betPanel);
		this.add(infoPanel);
		
		dealerPanel.setBackground(Color.green);
		playerPanel.setBackground(Color.green);
		rpCardBtnPanel.setBackground(Color.green);
	}
	
	/**
	 * This method calls the two CardPanels to give the numbers of special cards and
	 * the remainder of face values after divided by 10, and then deduce whether the 
	 * player has won the round.
	 * @return A boolean value representing whether the player has won this round. 
	 */
	public boolean playerCanWin() {
		int playerNumOfSpecialCards = playerPanel.getNumOfSpecialCards();
		int dealerNumOfSpecialCards = dealerPanel.getNumOfSpecialCards();
		if (playerNumOfSpecialCards > dealerNumOfSpecialCards) {
			return true;
		}
		else if (playerNumOfSpecialCards < dealerNumOfSpecialCards) {
			return false;
		}
		else {
			int playerFaceValue = playerPanel.getRemainderOfFaceValuesAfterDividedBy10();
			int dealerFaceValue = dealerPanel.getRemainderOfFaceValuesAfterDividedBy10();
			if (playerFaceValue > dealerFaceValue) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	/**
	 * This method checks whether the player has lost all is money. 
	 * @return A boolean value determining whether the game is over. 
	 */
	public boolean isGameOver() {
		return money <= 0;
	}
	
	/**
	 * This getter method returns the money the player currently has.
	 * @return An integer representing the money the player currently has. 
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * This setter method sets the value of bet according to the input argument.
	 * @param bet
	 */
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	/**
	 * This method is called when the player has won a round, then the player would 
	 * have his/her money increased by the value of bet. After that, it would reset 
	 * the bet to 0 and display the new amount of money the player has.
	 */
	public void winMoney() {
		money += bet;
		bet = 0;
		infoPanel.updateRemMoneyMsg(money);
	}
	
	/**
	 * This method is called when the player has won a round, then the player would 
	 * have his/her money decreased by the value of bet. After that, it would reset 
	 * the bet to 0 and display the new amount of money the player has.
	 */
	public void loseMoney() {
		money -= bet;
		bet = 0;
		infoPanel.updateRemMoneyMsg(money);
	}
}
