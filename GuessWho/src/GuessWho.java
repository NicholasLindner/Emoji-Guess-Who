
public class GuessWho {

	/*
	 * This methods is the main method of the program.
	 * It starts the program's execution. It also 
	 * creates the Face images and the Questions.
	 */
	public static void main(String[] args) {
		
		Face[] faces = {
				new Face("Images/z_green_face.jpg", "Images/zx_green_face.jpg"),
				new Face("Images/z_embarassed.jpg", "Images/zx_embarassed_face.jpg"),
				new Face("Images/z_crazy_face.jpg", "Images/zx_crazy_face.jpg"),
				new Face("Images/z_tear.jpg", "Images/zx_tear.jpg"),
				new Face("Images/z_teeth_smile.png", "Images/zx_teeth_smile.png"),
				new Face("Images/z_red_horned_devil.jpg", "Images/zx_red_horned_devil.jpg"),
				new Face("Images/z_smilelaugh.jpg", "Images/zx_smilelaugh.jpg"),
				new Face("Images/z_devil.jpg", "Images/zx_devil.jpg"),
				new Face("Images/z_crying_face.jpg", "Images/zx_crying_face.jpg"),
		};
		Question[] theQuestions = {
				new Question("IS YELLOW?", new boolean[] {false, true, true, true, true, false, true, false, true}),
				new Question("SHOWS TEETH?", new boolean[] {false, false, true, false, true, false, true, false, true}),
				new Question("HAS EYEBROWS?", new boolean[] {true, true, false, true, false, true, true, true, true}),
				new Question("LOOKS UNHAPPY?", new boolean[] {true, true, false, true, false, true, false, true, true}),
				new Question("CLOSING EYES?", new boolean[] {false, false, false, false, true, false, true, false, true}),
				new Question("HAS TEARS?", new boolean[] {false, false, false, true, false, false, true, false, true}),
				new Question("HAS PUPILS?", new boolean[] {false, true, true, false, false, false, false, false, false}),
				new Question("HAS HORNS?", new boolean[] {false, false, false, false, false, true, false, true, false}),
				new Question("IS RED/ORANGE?", new boolean[] {false, false, false, false, false, true, false, false, false}),
				new Question("IS PURPLE?", new boolean[] {false, false, false, false, false, false, false, true, false}),
		};
		
		GuessWhoGame game = new GuessWhoGame(faces, theQuestions);
		GuiFrame display = new GuiFrame(game);
	}

}
