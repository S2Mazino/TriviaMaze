/**
 * 
 * @author David, Nordine, Boda, Brianna
 *
 */
package Maze;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.sqlite.SQLiteDataSource;


public class QuestionDatabaseService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, QuestionBean> myQuestions = new HashMap<Integer, QuestionBean>();
	private Integer myQuestionNumber = 0;
	private SQLiteDataSource myDs = null;
	private Connection myConn = null;

	enum ColumnName {
		QUESTION,
		CHOICES,
		ANSWER;
	}

	/**
	 *  Creates an iterator that can be used. 
	 * @return An Iterator<QuestionBean>
	 */
	public Iterator<QuestionBean> iterator(){
		return myQuestions.values().iterator();
	}

	/**
	 * Chooses A question from the myQuestions map and returns it. 
	 * @return A chosen QuestionBean
	 * @throws SQLException
	 */
	public QuestionBean getQuestionBean() throws SQLException {
		QuestionBean q = null;
		for(int i = 0; i < myQuestions.size(); i++) {
			int num = (int) ((Math.random() * (myQuestions.size() - 0)) + 0);
			q = myQuestions.get(num);
			//q = myQuestions.get(i);
			if(!q.isAsked()) {
				q.setAsked();
				return q;
			}
		}
		q = new QuestionBean("No more questions in the database. ", "No more questions in the database. ", "No more questions in the database. ");
		return q;
	}
	/**
	 * Grabs all the questions from the question database and stores them in the map myQuestions. 
	 * 
	 * @param theTableName String representing the table name. 
	 * @param theDs the SQLiteDataSource. 
	 * @throws SQLException 
	 */
	public void getQuestionDataFromDatabase(final Connection conn) throws SQLException {
		String query = "SELECT * FROM QuestionTable";
		Statement stmt = conn.createStatement();// ) {			
		ResultSet rs = stmt.executeQuery(query);
		while ( rs.next() ) { 
			Integer questionNumber = rs.getInt("QUESTIONNUMBER");
			QuestionBean q = new QuestionBean(rs.getString(ColumnName.QUESTION.name()), rs.getString(ColumnName.CHOICES.name()),rs.getString(ColumnName.ANSWER.name()));
			myQuestions.put(questionNumber, q);
		}
	}
	/**
	 * Creates a table and populates it with question rows. It sets up the game for the user. 
	 */
	public void gameStartUp() {
		try {
			myDs = new SQLiteDataSource();
			myDs.setUrl("jdbc:sqlite:triviaMaze.db");
			myConn = myDs.getConnection();

			createTable("QuestionTable", myConn);
			addQuestion("QuestionTable", "True or False: All programming languages become assemly code when compiled.", "True,False", "False", myConn);
			addQuestion("QuestionTable", "What was the first commercially available computer programming language?", "FORTRAN,Java,C,LISP", "FORTRAN", myConn);
			addQuestion("QuestionTable", "True or False: Python was released before Java.", "True,False", "True", myConn);
			addQuestion("QuestionTable", "Who first coined the term Computer scientist?", "Steve Jobs,George Forsythe,Howard Aiken,Charles Bachman", "George Forsythe", myConn);
			addQuestion("QuestionTable", "When was Java initially released?", "2004,1991,2000,1995", "1995", myConn);
			addQuestion("QuestionTable", "What was the first computer ever built?", "The ENIAC,The Whirlwind,The IBM 702,The BESK", "The ENIAC", myConn);
			addQuestion("QuestionTable", "Is Java pass by reference or pass by value?", "Pass by reference,Pass by value", "Pass by value", myConn);
			addQuestion("QuestionTable", "When was the earliest sucessful AI program developed?", "1994,1983,1951,2004", "1951", myConn);
			addQuestion("QuestionTable", "What was the first computer system that used color display?", "IBM 5100,Apple 1,Altair 8800,TRS-80", "Apple 1", myConn);
			//addQuestion("QuestionTable", "True or False: Tennis for Two was the world's first video game made for entertainment. It was produced on a analog computer and it was shown in exhibits in 1958.", "True,False", "True");
			addQuestion("QuestionTable", "What programming language does the Unix line of operating systems use in order to operate on numerous platforms and maintain a modular design?", "C,Java,Scala,Python", "C", myConn);
			addQuestion("QuestionTable", "\"Bertie the Brain\" was a game made for the 1950 Canadian National Exhibition, and one of the earliest known computer games. Hosted on a thirteen foot tall computer, this machine allowed exhibition attendees to play a game of?", "Rock-Paper-Scissors,Tic-Tac-Toe,Pong,Sudoku", "Tic-Tac-Toe", myConn);
			addQuestion("QuestionTable", "Which programming language listed is not a high-level programming language?", "Ruby,Java,Python,Perl", "Java", myConn);
			addQuestion("QuestionTable", "True or False: The layers of a GUI, the graphical user interface, are based upon something called a revolving door system, and it contains the user, graphical interface, display server, kernel, hardware, and door manager as a part of itself.", "True,False", "False", myConn);
			addQuestion("QuestionTable", "True or False: Morton Heilig built a prototype of the first \"Experience Theater\" called the Sensorama in 1962. Five short films were to be displayed in it while engaging multiple senses (sight, sound, smell, and touch). Predating digital computing, it was a mechanical device. This is considered to be one of the early examples of Virtual Reality technology.", "True,False", "True", myConn);
			addQuestion("QuestionTable", "How many generations of computers have been invented?", "8 generations,3 generations,5 generations,6 generations", "5 generations", myConn);
			addQuestion("QuestionTable", "What was the name of the first computer programmer?", "Charles Babbage,Ada Lovelace,Alan Turing,George Forsythe", "Ada Lovelace", myConn);
			addQuestion("QuestionTable", "Grace Hopper found a moth stuck  in a relay responsible for a malfunction, and removed it,  calling it _______.", "cleaning,operating,debugging,hacking", "debugging", myConn);
			//	 		addQuestion("QuestionTable", "This device actually ran on electricity.  It was invented in time for the census of 1890 which only took six weeks to calculate the US population which at the time was 63 million.", "Analytical Engine,Mark 1,Difference Engine,Hollerith's Tabulating Machine,", "Hollerith's Tabulating Machine");
			addQuestion("QuestionTable", "This important invention began the development of the second generation computers, which no longer required vacuum tubes, and were much more efficient and less expensive.", "CPU,Silicon chip,Transistor,Integrated circuit", "Transistor", myConn);
			getQuestionDataFromDatabase(myConn);
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			if(myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Creates a Question table with the given table name. 
	 * 
	 * @param theTableName is a String representing the table name. 
	 * @param theDs is the data source. 
	 * @throws SQLException 
	 */
	public void createTable(final String theTableName, final Connection myConn) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS " + theTableName + "( " + "QUESTIONNUMBER INTEGER NOT NULL, " + ColumnName.QUESTION.name() + " TEXT NOT NULL, " + ColumnName.CHOICES.name() + " TEXT NOT NULL, " +  ColumnName.ANSWER.name() + " TEXT NOT NULL )";
		Statement stmt = myConn.createStatement();
		stmt.executeUpdate( query );
	}
	/**
	 * Adds a question to the table with the given table name. 
	 * 
	 * @param theTableName is a String representing the table name. 
	 * @param theQuestion is a String representing the question. 
	 * @param theChoices is a comma separates String of answer choices. 
	 * @param theAnswer is a String representing the answer. 
	 * @param theDs is the data source. 
	 * @throws SQLException 
	 */
	public void addQuestion(final String theTableName, final String theQuestion, final String theChoices, final String theAnswer,final Connection myConn) throws SQLException {
		String query = "INSERT INTO " + theTableName + " ( QUESTIONNUMBER, " + ColumnName.QUESTION.name() + ", " + ColumnName.CHOICES.name() + ", " + ColumnName.ANSWER.name() + " ) VALUES ( '" + myQuestionNumber + "', '" + theQuestion + "', '" + theChoices + "', '" + theAnswer + "' )";
		myQuestionNumber++;
		Statement stmt = myConn.createStatement();// ) {
		stmt.executeUpdate( query );
	}
}