
<<<<<<< Updated upstream
import java.util.Scanner;

=======
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.sqlite.SQLiteDataSource;
>>>>>>> Stashed changes
/**
 * @author Nordine
 *
 */
public class Main implements Serializable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Scanner myInput = new Scanner(System.in);
		Maze maze = null;
		int myRows;
		int myCols;
		
		//will add valid input condition later
		System.out.print("Input amount of rows(between 4 and 10): ");
		myRows = myInput.nextInt();
		System.out.print("Input amount of cols(between 4 and 10): ");
		myCols = myInput.nextInt();
		
		maze = new Maze(myRows, myCols);
		
		maze.lockRoom(2, 1);
//		maze.lockRoom(2, 2);
//		maze.lockRoom(2, 3);
//		maze.lockRoom(2, 4);
		
		maze.displayMaze();
		System.out.println(maze.availableRoom());
		
		System.out.println("move? " + maze.move("S"));
		maze.displayMaze();
		System.out.println(maze.availableRoom());


		Main serilizedObjectOfmainClass=new Main();
		//serilization of an object
		try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("serilizedObjectOfmainClass.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
              
            // Method for serialization of object
            out.writeObject(serilizedObjectOfmainClass);
              
            out.close();
            file.close();
              
            System.out.println("Object has been serialized");
  
        }
          
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
		myInput.close();
	}

}