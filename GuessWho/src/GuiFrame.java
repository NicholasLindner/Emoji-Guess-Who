import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class GuiFrame extends JFrame {
	
	//INSTANCE VRIABLES
	private static final long serialVersionUID = 7462992397950068538L;
	private List<JLabel> faces = new ArrayList<JLabel>(9);
	private JButton question[] = new JButton[10];
	private JTextField rowGuess;
	private JTextField colGuess;
	private JButton guess;
	private JButton quit;
	private GuessWhoGame game;
	
	/*
	 * CONSTRUCTOR: This constructor initializes the game
	 * instance variable and sets up the entire GUI frame
	 * using absolute positioning to put everything in the
	 * correct place (it also uses some forloops to help 
	 * put certain things in a specific order).
	 */
	public GuiFrame(GuessWhoGame game) {
	
		this.game = game;
		
		setLayout(null);
		
		for(int i = 0; i<9; i++) {
			faces.add(new JLabel(loadImage(game.getImagePath(i))));
		}

		
		for (int i = 0; i < 10; i++) {
			final int idx = i;
			question[i] = new JButton(game.getQText(i));
			question[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					onQuestionClicked(idx);
				}
			});
		}
		
		rowGuess = new JTextField(1);
		colGuess = new JTextField(1);
		
		guess = new JButton("Guess");
		guess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onGuessClicked();
			}
		});
		quit = new JButton("Quit");
		quit.setForeground(Color.RED);
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onQuitClicked();
			}
		});
		
		final int xStart = 65;
		int x = xStart;
		int y = 20;
		int w = 100;
		int h = 100;
		int imgIdx = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				faces.get(imgIdx).setBounds(x, y, w, h);
				add(faces.get(imgIdx));
				x += (w + 10);
				imgIdx++;
			}
			x = xStart;
			y += (h + 10);
		}
		
		x = 20;
		y = 350;
		w = 200;
		h = 30;
		for (int i = 0; i < 10; i += 2) {
			question[i].setBounds(x, y, w, h);
			add(question[i]);
			question[i+1].setBounds(x + w + 10, y, w, h);
			add(question[i+1]);
			
			y += h + 5;
		}
		
		x = 20;
		y = 600;
		JLabel rowLabel = new JLabel("Row guess (1 to 3):");
		rowLabel.setBounds(x, y, 150, 30);
		add(rowLabel);
		x += 180;
		rowGuess.setBounds(x, y, 50, 30);
		add(rowGuess);

		x = 20;
		y += 40;
		JLabel colLabel = new JLabel("Column guess (1 to 3):");
		colLabel.setBounds(x, y, 150, 30);
		add(colLabel);
		x += 180;
		colGuess.setBounds(x, y, 50, 30);
		add(colGuess);
		
		x = 280;
		y = 610;
		guess.setBounds(x, y, 100, 50);
		add(guess);
		
		x = 350;
		y = 700;
		quit.setBounds(x, y, 80, 30);
		add(quit);
		
		setSize(480, 800);
		setVisible(true);
		setTitle("Guess Who");
		
		JOptionPane.showMessageDialog(null, "This is a guessing game. You have to pick 3 questions out of 10 to help you find out which emoji - yes, emoji - was randomly picked"
				+ " by   \n the game. When you are ready to make your guess, type in the row number and the column number that corresponds to your guess.   ");
		JOptionPane.showMessageDialog(null, "Good Luck!");
	}
	
	/*
	 * This method loads each image into their spot in the game.
	 */
	private ImageIcon loadImage(String imagePath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	
		return new ImageIcon(scaled);
	}
	
	/*
	 * This method gets called when the user clicks a question.
	 * This method will check if the questionCounter is
	 * less than or equal to 2 and if it is, the faces affected by the question
	 * get X'ed out, that question gets disabled, and the game updates.
	 * Otherwise a message that tells the user that they ran out of questions
	 * shows up.
	 */
	private void onQuestionClicked(int questionIndex) {
		
		if(game.getQuestionCounter() <= 2) {
			game.clickedQuestion(questionIndex);
			question[questionIndex].setEnabled(false);
			update();
		}
		else {
			JOptionPane.showMessageDialog(null, "You ran out of questions! Now you must guess.");
		}
	}

	/*
	 * This method makes the row and column guess into an int,
	 * checks if that guess is correct, and gives the user the 
	 * corresponding message. It also resets the game and 
	 * makes sure that the game does not have an error if 
	 * something other than a number is typed into the guess box.
	 */
	private void onGuessClicked() {
		
		try{
			int row = Integer.parseInt(rowGuess.getText()); 
			int column = Integer.parseInt(colGuess.getText());
			if(game.checkGuess(row, column) == true) {
				game.xOutAllFacesExceptCorrect();
				update();
				JOptionPane.showMessageDialog(null, "Congratulations! Your guess was correct.");
			}
			else {
				JOptionPane.showMessageDialog(null, "Too bad! You did not guess correctly.");
				JOptionPane.showMessageDialog(null, "After clicking 'OK' you will see the all the wrong emojis X'ed out, leaving the correct one showing.");
				game.xOutAllFacesExceptCorrect();
				update();
				JOptionPane.showMessageDialog(null, "The correct emoji has now been shown.");

			}
			JOptionPane.showMessageDialog(null, "Restarting Game...");
			game.reset();
			enableQuestions();
			rowGuess.setText("");
			colGuess.setText("");
			update();
		}
		catch(java.lang.NumberFormatException e){
			rowGuess.setText("");
			colGuess.setText("");
			JOptionPane.showMessageDialog(null, "Please type in a number from one to three in each box!");
		}
		
	}
	
	/*
	 * This method exits the game when the quit button is clicked.
	 */
	private void onQuitClicked() {
		
		System.exit(0);
		
	}
	
	/*
	 * This method updates the game by setting the
	 * appropriate icon to the correct space.
	 */
	private void  update() {	
		for(int i = 0; i < faces.size(); i++) {
			faces.get(i).setIcon(loadImage(game.getImagePath(i)));
		}
	}
	
	/*
	 * This method enables each question.
	 *  in the game.
	 */
	private void enableQuestions() {
		for(int i = 0; i < question.length; i++) {
			question[i].setEnabled(true);
		}
	}
	
}


