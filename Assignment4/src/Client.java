import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;


/**
 * The client-side class which launches the GUI and communication between other client and the server
 * 
 * @author Pang Tin Hei
 * @version 1.0
 * @since 2024-12-04
 */
public class Client {
	Socket sock;
	PrintWriter writer;
	JFrame frame;
	JLabel info;
	GameBoard game;
	private int playerId = 1;
	private boolean isMyTurn = true;
	
	/**
	 * This method instantiates a new client, launch the GUI interface, and establish a connection to the server. 
	 * @param args Default value
	 */
	public static void main(String args[]) {
		Client client = new Client();
		client.launchGame();
		client.connectServer();
	}
	
	private void launchGame() {
		
		String title = "Tic Tac Toe";
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		info = new JLabel("Enter your player name...");
		game = new GameBoard();
		frame.setJMenuBar(new TopMenu());
		frame.add(info, BorderLayout.NORTH);
		frame.getContentPane().add(game);
		frame.add(new BottomPanel(frame), BorderLayout.SOUTH);
		
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

	}
	
	private void connectServer() {
		try {
			sock = new Socket("127.0.0.1", 5050);

			writer = new PrintWriter(sock.getOutputStream(), true);

			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);

			String command;
			while ((command = reader.readLine()) != null) {
				CommandHandler(command);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Server is not reachable!", "Message", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void switchTurn(int prevPlayer) {
		if (playerId != prevPlayer) {
			isMyTurn = true;
		}
		else {
			isMyTurn = false;
		}
	}
	
	private void CommandHandler(String command) {
		if (command.startsWith("P2")) {
			playerId = 2;
			isMyTurn = false;
		}
		if (command.startsWith("M")) {
			int movedPlayer = Integer.parseInt(command.substring(1,2));
			switchTurn(movedPlayer);
			game.setMove(Integer.parseInt(command.substring(2,3)), movedPlayer);
		}
		if (command.startsWith("E1")) {
			JOptionPane.showMessageDialog(frame, "Game Ends. One of the players left.", "Message", JOptionPane.INFORMATION_MESSAGE);
			game.enableBtns(false);
			info.setText("Game Ends. One of the players left.");
		}
		if (command.startsWith("E2")) {
			JOptionPane.showMessageDialog(frame, "Server full! Exiting game...", "Message", JOptionPane.WARNING_MESSAGE);
			System.exit(1);
		}
		if (command.startsWith("BR")) {
			game.setGameStart(true);
			if(playerId == 1) {
				info.setText("Please make your move.");
			}
			else {
				info.setText("Please wait for your opponents.");
			}
		}
		if (command.startsWith("RR")) {
			reset();
		}
	}
	
	private void reset() {
		frame.getContentPane().remove(game);
		game = new GameBoard();
		frame.getContentPane().add(game);
		frame.revalidate();
		if (playerId == 1) {
			isMyTurn = true;
			info.setText("Please make your move.");
		}
		else {
			isMyTurn = false;
			info.setText("Please wait for your opponents.");
		}
		game.setGameStart(true);
	}
	
	/**
	 * This inner class contains the GUI of the tic-tac-toe board and related methods.
	 */
	class GameBoard extends JPanel implements ActionListener {
		
		class BoardBtn extends JButton {
			public int num; 
			public BoardBtn(int num) {
				this.num = num; 
			}
		}
		
		private boolean isStarted = false;
		private BoardBtn[] buttons = new BoardBtn[9];
		private int[] moveList = new int[9];
		
		/**
		 * This constructor method instantiates the game board as well as the buttons.
		 */
		public GameBoard() {
			GridLayout layout = new GridLayout(3, 3);
			this.setLayout(layout);
			for (int i = 0; i < 9; i++) {
				moveList[i] = -1;
				buttons[i] = new BoardBtn(i);
				buttons[i].setPreferredSize(new Dimension(100, 100));
				buttons[i].setFont(new Font("SansSerif", Font.BOLD, 32));
				buttons[i].setBackground(Color.white);
				buttons[i].setOpaque(true);
				buttons[i].setBorder(new LineBorder(Color.black, 1));
				buttons[i].addActionListener(this);
				this.add(buttons[i]);
			}
		}
		
		/**
		 * This method is overridden from the ActionListener interface, which is used for the buttons to handle click events.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			BoardBtn targetBtn = (BoardBtn) e.getSource();
			int idx = targetBtn.num;
			if (isStarted && isMyTurn) {
				if (makeMove(idx)) {
					writer.println("M"+playerId+idx);
				}
			}
		}
		
		/**
		 * This method checks if the player's move is invalid .
		 * @param target the index of the target button the player has clicked.
		 * @return boolean value indicating the validity of the move.
		 */
		public boolean makeMove(int target) {
			if (moveList[target] != -1) {
				info.setText("INVALID MOVE!");
				info.setForeground(Color.RED);
				return false;
			} 
			return true;
		}
		
		/** 
		 * After receiving a message from the server, this method will be called to display the outcome of the player's movement on screen.
		 */
		public void setMove(int target, int movedPlayer) {
			if (movedPlayer == 1) {
				buttons[target].setText("X");
				buttons[target].setForeground(Color.GREEN);
			} 
			else {
				buttons[target].setText("O");
				buttons[target].setForeground(Color.RED);
			}
			if (movedPlayer != playerId) {
				info.setText("Your opponent has moved, now is your turn.");
			}
			else {
				info.setText("Valid move, wait for your opponent.");
			}
			info.setForeground(Color.BLACK);
			moveList[target] = movedPlayer;	
			
			String endMsg = null; boolean gameEnded = false;
			if (checkWin(movedPlayer)) { // someone has won the game
				enableBtns(false);
				gameEnded = true;
				if (playerId == movedPlayer) {
					endMsg = "Congratulations, you win. Do you want to play again?";
				}
				else {
					endMsg = "You lose. Do you want to play again?";
				}
			}
			else if (allFilledIn()) { // draw
				enableBtns(false);
				gameEnded = true;
				endMsg = "Draw. Do you want to play again?";
			}
			
			if (gameEnded) {
				int response = JOptionPane.showConfirmDialog(frame, endMsg, "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
				    writer.println("R");
				} 
				else if (response == JOptionPane.NO_OPTION) {
					System.exit(0);
				} 
			}
			
		}
		
		/**
		 * This method checks if the player who has moved just now satisfies the winning condition. 
		 * @param movedPlayer the index of the player.
		 * @return true if the player can win and false otherwise. 
		 */
		public boolean checkWin(int movedPlayer) {
			boolean canWin;
			int[][] winPatterns = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, 
									{1, 4, 7}, {2, 5, 8}, {3, 6, 9},
									{1, 5, 9}, {3, 5, 7}};
			for (int i = 0; i < 8; i++) {
				canWin = true;
				for (int j = 0; j < 3; j++) {
					if (moveList[winPatterns[i][j]-1] != movedPlayer) {
						canWin = false;
					}
				}
				if (canWin) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * This method checks if all the 9 boxes have been filled in.
		 * @return true if no empty boxes, false otherwise. 
		 */
		public boolean allFilledIn() {
			for (int i = 0; i < 9; i++) {
				if (moveList[i] == -1) {
					return false;
				}
			}
			return true;
		}
		
		/**
		 * This setter method initiates the game by setting the isStarted boolean value to true. 
		 * @param start boolean value to start the game.
		 */
		public void setGameStart(boolean start) {
			isStarted = start;
		}
		
		private void enableBtns(boolean isEnabled) {
			for (int i = 0; i < 9; i++) {
				buttons[i].setEnabled(isEnabled);
			}
		}
	}

	/**
	 * This inner class contains the GUI for the MenuBar at the top.
	 */
	class TopMenu extends JMenuBar {
		/**
		 * This constructor method build up the MenuItems and their corresponding functions onto the MenuBar.
		 */
		public TopMenu() {
			JMenu control = new JMenu("Control");
			JMenuItem exitItem = new JMenuItem(new AbstractAction("Exit") {
				@Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
			});
			control.add(exitItem);
			
			JMenu help = new JMenu("Help");
			JMenuItem instructionItem = new JMenuItem (new AbstractAction("Instructions") {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(frame, "Some information about the game: \n"
							+ "Criteria for a valid move: \n "
							+ "- The move is not occupied by any mark. \n"
							+ "- The move is made in the player's turn. \n"
							+ "- The move is made in the 3x3 board. \n"
							+ "The game would continue and switch the opposite player until it reaches either one of the following conditions: \n"
							+ "- Player 1 wins. \n"
							+ "- Player 2 wins. \n"
							+ "- Draw.", 
							"Message", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			help.add(instructionItem);
			this.add(control);
			this.add(help);
		}
	}

	/**
	 * This inner class contains the text field and the submit button put on the bottom.
	 */
	class BottomPanel extends JPanel {
		/**
		 * This constructor method instantiates the JPanel and build up the JTextField and JButton. 
		 * @param frame The current JFrame of the GUI.
		 */
		public BottomPanel(JFrame frame) {
			JTextField inputTextField = new JTextField(10);
			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = inputTextField.getText();
					if (!name.equals("")) {
						info.setText("WELCOME " + name);
						frame.setTitle("Tic Tac Toe -Player: " + name);
						inputTextField.setEnabled(false);
						submitButton.setEnabled(false);
						writer.println("PR");
					}
				}
			});
			
			this.add(inputTextField);
			this.add(submitButton);
		}
	}
}
