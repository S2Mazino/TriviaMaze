
import java.util.Scanner;

/**
 * @author Nordine
 *
 */
public class Main {

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
		
//		maze.lockRoom(2, 1);
//		maze.lockRoom(2, 2);
//		maze.lockRoom(2, 3);
//		maze.lockRoom(2, 4);
		
		maze.displayMaze();
		System.out.println(maze.availableRoom());
		//ask for which direction
		//can move?
		//ask(display) the question
		//ask for answer input
		//correct answer?
		//move
		//if not, tell them wrong answer lock the room and ask for another direction
		//if no more room, lost the game
		System.out.println("move? " + maze.move("S"));
		maze.displayMaze();
		System.out.println(maze.availableRoom());

		
		myInput.close();
	}

}