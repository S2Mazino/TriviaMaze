package Maze;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;


public class QuestionDatabaseService {

	private List<Integer> myUsedQuestions = new ArrayList<Integer>();
	private List<String[]> myQuestions = new ArrayList<String[]>();
	private Integer myQuestionNumber = 0;
	private Integer num;
	private SQLiteDataSource ds = null;
	private Connection conn = null;

	enum ColumnName {
		COL1("QUESTION"),
		COL2("CHOICES"),
		COL3("ANSWER");
		String name;
		ColumnName(String name){
			this.name = name;
		}
	}

	public QuestionDatabaseService() {
		try {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:triviaMaze.db");
			conn = ds.getConnection();
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String[] getQuestion() {
		String[] q = new String[3];
		for(int i = 0; i < myQuestions.size(); i++) {
			int num2 = num;
			if(!myQuestions.isEmpty()) {
				if(myUsedQuestions.contains(num)) {
					myUsedQuestions.remove(num);
					q = myQuestions.remove(num2);
					num = num - 1;
					System.out.println("=================");
					return q;
				}
			}
		}
		q[0] = "No more questions in the database. ";
		q[1] = "No more questions in the database. ";
		q[2] = "No more questions in the database. ";
		return q;
	}
	/**
	 * Grabs all the questions from the question database and stores them in an ArrayList. 
	 * 
	 * @param theTableName String representing the table name. 
	 * @param theDs the SQLiteDataSource. 
	 */
	public void getQuestionDataFromDatabase() {
		String query = "SELECT * FROM QuestionTable";
		try ( Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement(); ) {			
			ResultSet rs = stmt.executeQuery(query);
			while ( rs.next() ) { 
				String[] tempArr = new String[3];
				myUsedQuestions.add(rs.getInt("QUESTIONNUMBER"));
				int i = 0;
				for (ColumnName col : ColumnName.values()) {
					tempArr[i] = rs.getString(col.name);
		            i++;
		        }
				myQuestions.add(tempArr);
			}
			num = myQuestions.size() - 1;
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public void gameStartUp() {
		createTable("QuestionTable");
 		addQuestion("QuestionTable", "True or false, all programming languages become assemly code when compiled.", "True,False", "False");
 		addQuestion("QuestionTable", "What was the first commercially available computer programming language?", "FORTRAN,Java,C,LISP", "FORTRAN");
 		addQuestion("QuestionTable", "True or false, Python was released before Java.", "True,False", "True");
 		addQuestion("QuestionTable", "Who first coined the term Computer scientist?", "Steve Jobs,George Forsythe,Howard Aiken,Charles Bachman", "George Forsythe");
 		addQuestion("QuestionTable", "When was Java initially released?", "2004,1991,2000,1995", "1995");
 		addQuestion("QuestionTable", "What was the first computer ever built?", "The ENIAC (Electronic Numerical Integrator and Computer)", "The ENIAC (Electronic Numerical Integrator and Computer)");
	}
	/**
	 * Creates a Question table with the given table name. 
	 * 
	 * @param theTableName is a String representing the table name. 
	 * @param theDs is the data source. 
	 */
	public void createTable(String theTableName) {
		String query = "CREATE TABLE IF NOT EXISTS " + theTableName + "( " + "QUESTIONNUMBER INTEGER NOT NULL, " + ColumnName.COL1.name + " TEXT NOT NULL, " + ColumnName.COL2.name + " TEXT NOT NULL, " +  ColumnName.COL3.name + " TEXT NOT NULL )";
		try ( Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement(); ) {
			stmt.executeUpdate( query );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Adds a question to the table with the given table name. 
	 * 
	 * @param theTableName is a String representing the table name. 
	 * @param theQuestion is a String representing the question. 
	 * @param theChoices is a comma separates String of answer choices. 
	 * @param theAnswer is a String representing the answer. 
	 * @param theDs is the data source. 
	 */
	public void addQuestion(String theTableName, String theQuestion, String theChoices, String theAnswer) {
		String query = "INSERT INTO " + theTableName + " ( QUESTIONNUMBER, " + ColumnName.COL1.name + ", " + ColumnName.COL2.name + ", " + ColumnName.COL3.name + " ) VALUES ( '" + myQuestionNumber + "', '" + theQuestion + "', '" + theChoices + "', '" + theAnswer + "' )";
		myQuestionNumber++;
		try ( Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement(); ) {
			stmt.executeUpdate( query );
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
