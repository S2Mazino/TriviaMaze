package Test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Maze.Maze;
import Maze.Room;

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
		myMaze = new Maze();
	}

	/**
	 * Testing successful object creation.
	 * Test method for {@link Maze#Maze(int, int)}.
	 */
	@Test
	void testMaze() {
		//checking if the object I created is not null
		assertNotNull("The object is null", myMaze);
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
	 * Should return false because user's position is not on exit's position.
	 * Test method for {@link Maze#win()}.
	 */
	@Test
	void testWinFail() {
		assertFalse("Win function fail to return false", myMaze.win());
	}
	
	/**
	 * Should return true because user's position is on exit's position.
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
	 * Testing default starting path, open path would be E, S
	 * Test method for {@link Maze#availableRoom()}.
	 */
	@Test
	void testAvailableRoomStart() {
		String paths = myMaze.availableRoom();
		assertTrue("availableRoom did not return correct rooms", paths.contains("E"));
		assertTrue("availableRoom did not return correct rooms", paths.contains("S"));
	}
	
	/**
	 * Testing paths from (2,2), open path would be N, E, S, W
	 * Test method for {@link Maze#availableRoom()}.
	 */
	@Test
	void testAvailableRoom() {
		myMaze.move("E");
		myMaze.move("S");
		String paths = myMaze.availableRoom();
		assertTrue("availableRoom did not return correct rooms", paths.contains("E"));
		assertTrue("availableRoom did not return correct rooms", paths.contains("S"));
		assertTrue("availableRoom did not return correct rooms", paths.contains("N"));
		assertTrue("availableRoom did not return correct rooms", paths.contains("W"));
	}
	
	/**
	 * Testing available paths when all room are blocked
	 * Test method for {@link Maze#availableRoom()}.
	 */
	@Test
	void testNoAvailableRoom() {
		myMaze.lockRoom(myMaze.getMyCol(), myMaze.getMyRow()+1);
		myMaze.lockRoom(myMaze.getMyCol()+1, myMaze.getMyRow());
		String paths = myMaze.availableRoom();
		assertEquals("availableRoom did not return correct rooms", paths.length(), 0);
	}

	/**
	 * Default path testing from start to finish with no room locked. True output test.
	 * Test method for {@link Maze#hasPath()}.
	 */
	@Test
	void testHasPathDefault() {
		assertTrue("Path aglorithm not working properly", myMaze.hasPath());
	}
	
	/**
	 * Default path testing from start to finish with no room locked. True output test.
	 * Test method for {@link Maze#hasPath()}.
	 */
	@Test
	void testHasPathTrue() {
		myMaze.move("S");
		myMaze.move("E");
		myMaze.lockRoom(myMaze.getMyCol()-1, myMaze.getMyRow());
		myMaze.lockRoom(myMaze.getMyCol(), myMaze.getMyRow()+1);
		assertTrue("Path aglorithm not working properly", myMaze.hasPath());
	}
	
	/**
	 * Blocked all path (blocked east and south path) from the starting point.
	 * Test method for {@link Maze#hasPath()}.
	 */
	@Test
	void testHasNoPath() {
		myMaze.lockRoom(myMaze.getMyCol(), myMaze.getMyRow()+1);
		myMaze.lockRoom(myMaze.getMyCol()+1, myMaze.getMyRow());
		assertFalse("Path aglorithm not working properly", myMaze.hasPath());
	}
	
	

	/**
	 * Testing move south.
	 * Test method for {@link Maze#move(java.lang.String)}.
	 */
	@Test
	void testMoveSouth() {
		assertEquals("col- position not at top-left of maze", myMaze.getMyCol(), 1);
		assertEquals("row- position not at top-left of maze", myMaze.getMyRow(), 1);
		myMaze.move("S");
		assertEquals("Function failed to move south", myMaze.getMyRow(), 2);
	}
	
	/**
	 * Testing move north.
	 * Test method for {@link Maze#move(java.lang.String)}.
	 */
	@Test
	void testMoveNorth() {
		myMaze.move("S");
		myMaze.move("E");
		assertEquals("col- position not at 2", myMaze.getMyCol(), 2);
		assertEquals("row- position not at 2", myMaze.getMyRow(), 2);
		myMaze.move("N");
		assertEquals("Function failed to move north", myMaze.getMyRow(), 1);
	}
	
	/**
	 * Testing move east.
	 * Test method for {@link Maze#move(java.lang.String)}.
	 */
	@Test
	void testMoveEast() {
		assertEquals("col- position not at top-left of maze", myMaze.getMyCol(), 1);
		assertEquals("row- position not at top-left of maze", myMaze.getMyRow(), 1);
		myMaze.move("E");
		assertEquals("Function failed to move east", myMaze.getMyCol(), 2);
	}
	
	/**
	 * Testing move west.
	 * Test method for {@link Maze#move(java.lang.String)}.
	 */
	@Test
	void testMoveWest() {
		myMaze.move("S");
		myMaze.move("E");
		assertEquals("col- position not at 2", myMaze.getMyCol(), 2);
		assertEquals("row- position not at 2", myMaze.getMyRow(), 2);
		myMaze.move("W");
		assertEquals("Function failed to move west", myMaze.getMyCol(), 1);
	}

}
