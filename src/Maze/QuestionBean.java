/**
 * @author David, Nordine, Boda, Brianna
 *
 */

package Maze;

public class QuestionBean {
	private String myQuestion = "";
	private String myCorrectAnswer = "";
	private String myUserAnswer = "";
	private String[] myChoices = new String[4];
	private boolean myAsked = false;
	
	/**
	 * Constructor to create a new QuestionBean object using the given data. 
	 * @param thequestion
	 * @param theChoices
	 * @param theAnswer
	 */
	public QuestionBean(final String thequestion, final String theChoices, final String theAnswer) {
		myQuestion = thequestion;
		String answerChoices = theChoices;
		myChoices = answerChoices.split("[,]", 0);
		myCorrectAnswer = theAnswer.toUpperCase();
	}
	
	/**
	 * Returns myCorrectAnswer for testing purposes. 
	 * @return myCorrectAnswer
	 */
	public String getCorrect() {
		return myCorrectAnswer;
	}
	
	/**
	 * Returns myUserAnswer for testing purposes. 
	 * @return myUserAnswer
	 */
	public String getUserEnterd() {
		return myUserAnswer;
	}

	/**
	 * Returns myAsked. 
	 * @return
	 */
	public boolean isAsked() {
		return myAsked;
	}
	/**
	 * Sets a boolean for if it has been asked. 
	 */
	public void setAsked() {
		myAsked = true;
	}


	/**
	 * gets myQuestion. 
	 * 
	 * @return the question to be displayed. 
	 */
	public String getQuestion() {
		return myQuestion;
	}
	/**
	 * gets myChoices. 
	 * 
	 * @return the answer choices to be displayed. 
	 */
	public String[] getChoices() {
		return myChoices;
	}

	/**
	 * This prints out the choices. This does not follow the MVC design pattern and is only here for testing purposes. 
	 */
	public void printChoices() {
		int num = 1;
		for(int i = 0; i < myChoices.length; i++) {
			System.out.println(num + ". " + myChoices[i]);
			num++;
		}
	}

	/**
	 * Sets the answer variable with the users answer. 
	 * 
	 * @param theChoice
	 */
	public void setChoice(final String theChoice) {
		myUserAnswer = theChoice.toUpperCase();;
	}

	/**
	 * Checks to see if the user provided answer is correct. 
	 * 
	 * @return Boolean representing if the answer is correct. 
	 */
	public boolean isCorrect() {
		if(myCorrectAnswer != null && !myCorrectAnswer.isEmpty() && myUserAnswer != null && !myUserAnswer.isEmpty()){
			if(myUserAnswer.equals(myCorrectAnswer)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Generates a String that will be used to display the question and the choices. This does not follow the MVC design pattern and is only here for testing purposes. 
	 * 
	 * @return A String representing the question display. 
	 *
	 */
	public String display() {
		String display = "";
		display += (myQuestion + "\n");
		for(int i = 0; i < myChoices.length; i++) {
			display += (myChoices[i] + "\n");
		}
		return display;
	}

}