/**
 * 
 */

/**
 * @author Nordine, David, Boda, Brianna
 *
 */
public class Question {
	private String question = "";
	private String correctAnswer = "";
	private String userAnswer = "";
	private String[] choices = new String[4];

	/**
	 * 
	 * Grabs a question from the database and assigns it to the question variable. 
	 *
	 */
	public void getQuestion() {
		while(question != "temporary condition" /*there is a question in the database*/) {
			String question = "get a question from the database";
			if(question != "temporary condition" /* question is not already been used*/) {
				question = "get a question from the database";
				this.question = question;
			}
		}
		question = "There is no more questions in the database. ";
	}
	/**
	 * 
	 * Gets the question options from the database and stores them in the choices array. 
	 * 
	 */
	public void getOptions() {
		
	}
	
	public void getCorrect() {
		correctAnswer = "get the correct answer from the database. ";
	}
	/**
	 * 
	 * Sets the answer variable with the users answer. 
	 * @param choice
	 */
	public void setChoice(String choice) {
		userAnswer = choice;
	}
	/**
	 * 
	 * checks to see if the user provided answer is correct. 
	 * @return Boolean representing if the answer is correct. 
	 */
	public boolean isCorrect() {
		if(correctAnswer != null && !correctAnswer.isEmpty() && userAnswer != null && !userAnswer.isEmpty() && userAnswer.equals(correctAnswer)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 
	 * Generates a String that will be used to display the question and the choices. 
	 * 
	 * @return A String representing the question display. 
	 *
	 */
	public String Display() {
		String display = "";
		display += (question + "\n");
		for(int i = 0; i < choices.length; i++) {
			display += (choices[i] + "\n");
		}
		return display;
	}
}