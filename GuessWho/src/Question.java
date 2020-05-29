public class Question {

	//INSTANCE VARIABLES
	private String qText;
	private boolean[] affectsImage = new boolean[9];
	
	//GETTERS
	public String getText() {return qText;}
	
	//CONSTRUCTOR
	public Question (String qText, boolean[] affectsImage) {
		this.qText = qText;
		this.affectsImage = affectsImage;
	}

	/*
	 * This method returns true if the image at the 
	 * given index of the affectsImage array is 
	 * affected by the question. Otherwise it returns false.
	 */
	public boolean isAffecting (int imageIndex) {	
		return affectsImage[imageIndex];
	}
	
}
