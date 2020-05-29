public class Face {
	
	//INSTANCE VARIABLES
	private String normalFace;
	private String xFace;
	private boolean isXed = false;
	
	//CONSTRUCTOR
	public Face(String normalFace, String xFace){
		
		this.normalFace = normalFace;
		this.xFace = xFace;
	}
	
	/*
	 * This method returns the version of an image that
	 * is being used.
	 */
	public String getUsedImage() {
		
		if(isXed) {
			return xFace;
		}
		return normalFace;
		
	}
	
	/*
	 * This method changes the image to the x'ed
	 * version by changing isXed to true.
	 */
	public void changeToXVersion() {
		isXed = true;
	}
	
	/*
	 * This method changes the image to the non-x'ed
	 * version by changing isXed to false.
	 */
	public void reset() {
		
		isXed = false;
		
	}
	
}
