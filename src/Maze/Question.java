
package Maze;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nordine, David, Boda, Brianna
 *
 */
public class Question {
	private String myQuestion = "";
	private String myCorrectAnswer = "";
	private String myUserAnswer = "";
	private String[] myChoices = new String[4];
	private QuestionDatabaseService util = new QuestionDatabaseService();

	public Question() throws SQLException{
		//gets all of the rows from the database and puts them into an arraylist of arrays where each array represents a question.
		util.getQuestionDataFromDatabase();
		//gets one array from the arrayList and asigns each of the values in the array to the corresponding question fields. This way I dont have any public set methods 
		//setQuestionData();
	}



	/**
	 * gets myQuestion. 
	 * 
	 * @return the question to be displayed. 
	 */
	public String getQuestion() {
//		setQuestionData();
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
	 * Picks a question and sets the data for that question. 
	 */
	public void setQuestionData() {
		String[] question = util.getQuestion();
		myQuestion = question[0];
		String answerChoices = question[1];
		myChoices = answerChoices.split("[,]", 0);
		myCorrectAnswer = question[2];
		return;
	}
	/**
	 * Sets the answer variable with the users answer. 
	 * 
	 * @param theChoice
	 */
	public void setChoice(String theChoice) {
		myUserAnswer = theChoice;
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