import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * This class inherits from JPanel, and it stores three ReplaceButtons and
 * use BoxLayout to align those buttons. It also handles the number of replacements.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
class RpCardBtnPanel extends JPanel {
	private ReplaceButton[] rpButtons;
	private int numOfReplacements = 0;
	
	/**
	 * This constructor method initializes three ReplaceButtons and store them 
	 * in an array. 
	 * @param playerPanel A variable referencing the current PlayerPanel, which is then passed
	 * into the ReplaceButton class.
	 */
	public RpCardBtnPanel(PlayerPanel playerPanel) {
		rpButtons = new ReplaceButton[]{
			new ReplaceButton(0, playerPanel, this),
			new ReplaceButton(1, playerPanel, this),
			new ReplaceButton(2, playerPanel, this),
		};
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(rpButtons[0]);
		this.add(rpButtons[1]);
		this.add(rpButtons[2]);
	}
	
	/**
	 * This getter method return the array storing the three ReplaceButtons.
	 * @return The array storing the three ReplaceButtons. 
	 */
	public ReplaceButton[] getRpButtons() {
		return rpButtons;
	}
	
	/**
	 * This method increments the number of replacements by 1, and if this number 
	 * exceed 2, all ReplaceButtons will be disabled.
	 */
	public void addNumOfReplacements() {
		numOfReplacements++;
		if (numOfReplacements >= 2) {
			disableAll();
		}
	}
	
	/**
	 * This method would disable all the ReplaceButtons. 
	 */
	public void disableAll() {
		for (int i = 0; i < rpButtons.length; i++) {
			rpButtons[i].setEnabled(false);
		}
	}
}