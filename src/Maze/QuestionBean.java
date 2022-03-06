package Maze;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nordine, David, Boda, Brianna
 *
 */
public class QuestionBean {
	private String myQuestion = "";
	private String myCorrectAnswer = "";
	private String myUserAnswer = "";
	private String[] myChoices = new String[4];
	private QuestionDatabaseService util = new QuestionDatabaseService();
	private boolean asked = false;

	public QuestionBean(String thequestion, String choices, String theAnswer) throws SQLException{
		myQuestion = thequestion;
		String answerChoices = choices;
		myChoices = answerChoices.split("[,]", 0);
		myCorrectAnswer = theAnswer.toUpperCase();
	}


	public boolean isAsked() {
		return asked;
	}
	public void setAsked() {
		asked = true;
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

	public void printChoices() {
		for(int i = 0; i < myChoices.length; i++) {
			System.out.println(myChoices[i]);
		}
	}

	/**
	 * Sets the answer variable with the users answer. 
	 * 
	 * @param theChoice
	 */
	public void setChoice(String theChoice) {
		myUserAnswer = theChoice.toUpperCase();;
	}

	/**
	 * checks to see if the user provided answer is correct. 
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
	 * Generates a String that will be used to display the question and the choices. 
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