import javax.swing.*;
import java.awt.event.*;

/**
 * This class inherits from JMenuBar, showing some operations of the game,
 * namely reset and/or exit the game.
 * 
 * @author Pang Tin Hei (3036100179)
 * @since 2023-11-13
 */
public class TopMenu extends JMenuBar {
	/**
	 * This constructor method instantiates the JMenu and two JMenuItems (resetItem, exitItem)
	 * and add them into the MenuBar.
	 * @param app This variable is referencing the App class so that when
	 * the player presses Reset, the reset function could be called and the
	 * game could be restarted.
	 */
    public TopMenu(App app) {
        JMenu menu = new JMenu("Control");
        JMenuItem resetItem = new JMenuItem(new AbstractAction("Reset") {
			@Override
			public void actionPerformed(ActionEvent e) {
				app.reset();
			}
        });
        JMenuItem exitItem = new JMenuItem(new AbstractAction("Exit") {
        	@Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(resetItem);
        menu.add(exitItem);
        this.add(menu);
    }
}
