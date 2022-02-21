import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test for Maze class.
 */

/**
 * @author Nordine
 *
 */
class MazeTest {
	
	//class object to be tested
	private Maze myMaze;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		final int row = 4;
		final int col = 4;
		myMaze = new Maze(row, col);
	}

	/**
	 * Test method for {@link Maze#Maze(int, int)}.
	 */
	@Test
	void testMaze() {
		//checking if the object I created is not null
		assertNotNull("The object is null", myMaze);
	}

	/**
	 * Test method for {@link Maze#displayMaze()}.
	 */
	@Test
	void testDisplayMaze() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Tests to make sure that at creation, the maze's room are all unlocked
	 * Test method for {@link Maze#lockRoom(int, int)}.
	 */
	@Test
	void testLockRoomDefault() {
		Room[][] rooms = myMaze.getRooms();
		for(int i = 1; i < rooms.length-1; i++) {
			for(int j = 1; j < rooms[0].length-1; j++) {
				assertFalse("Maze's room are locked at " + i + ",  " + j + " creation", rooms[i][j].isLocked());
			}
		}
	}
	
	/**
	 * Tests to make sure that at creation, the maze's room are all unlocked
	 * Test method for {@link Maze#lockRoom(int, int)}.
	 */
	@Test
	void testLockAllRoom() {
		Room[][] rooms = myMaze.getRooms();
		for(int i = 1; i < rooms.length-1; i++) {
			for(int j = 1; j < rooms[0].length-1; j++) {
				//make sure creation of maze is working fine, then lock that room
				assertFalse("Maze's room are locked at " + i + ",  " + j + " creation", rooms[i][j].isLocked());
				myMaze.lockRoom(i, j);
			}
		}
		//get updated rooms
		rooms = myMaze.getRooms();
		
		//go through each room
		for(int i = 1; i < rooms.length-1; i++) {
			for(int j = 1; j < rooms[0].length-1; j++) {
				assertTrue("Maze's room are not locked at " + i + ",  " + j, rooms[i][j].isLocked());
			}
		}
		
	}

	/**
	 * Test method for {@link Maze#win()}.
	 */
	@Test
	void testWinFail() {
		assertFalse("Win function fail to lose", myMaze.win());
	}
	
	/**
	 * Test method for {@link Maze#win()}.
	 */
	@Test
	void testWinTrue() {
		//position the "player" on the exit room's position
		myMaze.move("E");
		myMaze.move("E");
		myMaze.move("E");
		myMaze.move("S");
		myMaze.move("S");
		myMaze.move("S");
		assertTrue("Win function fail to win", myMaze.win());
	}

	/**
	 * Test method for {@link Maze#availableRoom()}.
	 */
	@Test
	void testAvailableRoom() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Maze#hasPath()}.
	 */
	@Test
	void testHasPath() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Maze#move(java.lang.String)}.
	 */
	@Test
	void testMoveSouth() {
		assertEquals("x- position not at top-left of maze", myMaze.getMyX(), 1);
		assertEquals("y- position not at top-left of maze", myMaze.getMyY(), 1);
		myMaze.move("S");
		assertEquals("Function failed to move south", myMaze.getMyX(), 2);
	}
	
	/**
	 * Test method for {@link Maze#move(java.lang.String)}.
	 */
	@Test
	void testMoveNorth() {
		myMaze.move("S");
		myMaze.move("E");
		assertEquals("x- position not at 2", myMaze.getMyX(), 2);
		assertEquals("y- position not at 2", myMaze.getMyY(), 2);
		myMaze.move("N");
		assertEquals("Function failed to move north", myMaze.getMyX(), 1);
	}
	
	/**
	 * Test method for {@link Maze#move(java.lang.String)}.
	 */
	@Test
	void testMoveEast() {
		assertEquals("x- position not at top-left of maze", myMaze.getMyX(), 1);
		assertEquals("y- position not at top-left of maze", myMaze.getMyY(), 1);
		myMaze.move("E");
		assertEquals("Function failed to move east", myMaze.getMyY(), 2);
	}
	
	/**
	 * Test method for {@link Maze#move(java.lang.String)}.
	 */
	@Test
	void testMoveWest() {
		myMaze.move("S");
		myMaze.move("E");
		assertEquals("x- position not at 2", myMaze.getMyX(), 2);
		assertEquals("y- position not at 2", myMaze.getMyY(), 2);
		myMaze.move("W");
		assertEquals("Function failed to move west", myMaze.getMyY(), 1);
	}

}
