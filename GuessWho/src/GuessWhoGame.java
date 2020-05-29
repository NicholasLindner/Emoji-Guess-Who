public class GuessWhoGame {
	
	//INSTANCE VARIABLES
	Face[] faces = new Face[9];
	Question[] questions = new Question[10];
	int questionCounter = 0;
	int winningFace = -1;
	
	//GETTERS
	public String getQText(int i) {return questions[i].getText();}
	public String getImagePath(int i) {return faces[i].getUsedImage();}
	public int getQuestionCounter() {return questionCounter;}
	
	/*
	 * CONSTRUCTOR: This constructor creates a random number between 0 and 8 (inclusive)
	 * which will be used to identify the winning face. It also initializes two other
	 * instance variables, which is what a normal constructor would do.
	 */
	public GuessWhoGame(Face[] faces, Question[] questions) {
		this.faces = faces;
		this.questions = questions;
		
		winningFace = (int)(Math.random() * 8);
	}
	
	/*
	 * This method gets called when the user clicks a question.
	 * This will change the affected questions into their
	 * X-version and adds one to the question counter.
	 */
	public void clickedQuestion(int questionIndex) {
		Question q = questions[questionIndex];
		
		for(int i = 0; i < faces.length; i++) {
			
			if(q.isAffecting(i) != q.isAffecting(winningFace)) {
				faces[i].changeToXVersion();
			}
			
		}
		questionCounter += 1;
	}
	
	/*
	 * This method goes through the faces array and
	 * changes each element that is NOT the winning face 
	 * to the X-version of it.
	 */
	public void xOutAllFacesExceptCorrect() {
		for(int i = 0; i < faces.length; i++) {
			if(i != winningFace) {
				faces[i].changeToXVersion();
			}
			
		}
	}
	
	/*
	 * This method checks if the guess that user
	 * did was correct. If it is it returns true.
	 * Otherwise it returns false.
	 */
	public boolean checkGuess(int row, int col) {
		
		int faceIndex = (row - 1) * 3 + (col - 1);
		return faceIndex == winningFace;
		
	}
	
	/*
	 * This method resets the game.
	 */
	public void reset() {
		
		winningFace = (int)(Math.random() * 8);
		questionCounter = 0;
		
		for(int i = 0; i < faces.length; i++) {
			faces[i].reset();
		}
		
	}
	
}
