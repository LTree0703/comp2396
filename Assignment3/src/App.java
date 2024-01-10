import javax.swing.*;

/**
 * The class serves as the entry point of the whole game.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
public class App {
	private JFrame frame;
	private GamePanel gamePanel;
	
	/**
	 * This is the main method which instantiates the App class and initialize the game.
	 * @param args
	 */
	public static void main(String[] args) {
		App app = new App();
		app.start();
	}
	
	/**
	 * This method instantiates the frame and add the menu bar and the game panel
	 * into the frame, then set the frame as visible.
	 */
	public void start() {
		String title = "A Simple Card Game";
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardDeck.initCardDeck();
		gamePanel = new GamePanel(100, this);
		frame.setJMenuBar(new TopMenu(this));
		frame.getContentPane().add(gamePanel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * This method accepts no argument, and it is used to reset the whole game panel
	 * after the player has lost all the money.
	 */
	public void reset() {
		frame.getContentPane().remove(gamePanel);
		gamePanel = new GamePanel(100, this);
		frame.getContentPane().add(gamePanel);
		frame.revalidate();
		CardDeck.initCardDeck();
	}
	
	/**
	 * This method is an overloaded method which accepts one argument, and it is used to
	 * start a new round.
	 * @param remMoney An integer storing the remaining money that the player has.
	 */
	public void reset(int remMoney) {
		frame.getContentPane().remove(gamePanel);
		gamePanel = new GamePanel(remMoney, this);
		frame.getContentPane().add(gamePanel);
		frame.revalidate();
		CardDeck.initCardDeck();
	}
}

