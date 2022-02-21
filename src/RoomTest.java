import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test for Room class.
 */

/**
 * @author Nordine
 *
 */
class RoomTest {
	
	//class object to be tested
	private Room myRoom;
	
	/**
	 * setup a class room to be tested for each test case, will create a custom
	 * in some cases
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() {
		int x = 2;
		int y = 2;
		myRoom = new Room(x, y);
	}

	/**
	 * Test method for {@link Room#Room(int, int)}.
	 */
	@Test
	void testRoom() {
		//checking if the object I created is not null
		assertNotNull("The object is null", myRoom);
	}

	/**
	 * Test method for {@link Room#isLocked()}.
	 */
	@Test
	void testIsLockedTrue() {
		myRoom.lockRoom();
		assertEquals("Room failed to lock room", myRoom.isLocked(), true);
	}
	
	/**
	 * Test method for {@link Room#isLocked()}.
	 */
	@Test
	void testIsLockedFalse() {
		//room at default should be false
		assertFalse("Room field by default is not unlocked", myRoom.isLocked());
	}

	/**
	 * Test method for {@link Room#lockRoom()}.
	 */
	@Test
	void testLockRoom() {
		myRoom.lockRoom();
		assertTrue("lockRoom function failed to lock", myRoom.isLocked());
	}

	/**
	 * Test method for {@link Room#getVisit()}.
	 */
	@Test
	void testGetVisitFalse() {
		assertFalse("myVisit field not corrected properly", myRoom.getVisit());
	}
	
	/**
	 * Test method for {@link Room#getVisit()}.
	 */
	@Test
	void testGetVisitTrue() {
		myRoom.setVisit(true);
		assertTrue("myVisit field not corrected properly", myRoom.getVisit());
	}

	/**
	 * Test method for {@link Room#setVisit(boolean)}.
	 */
	@Test
	void testSetVisit() {
		myRoom.setVisit(true);
		assertTrue("myVisit field not corrected properly", myRoom.getVisit());
		myRoom.setVisit(false);
		assertFalse("myVisit field not corrected properly", myRoom.getVisit());
	}

	/**
	 * Test method for {@link Room#getX()}.
	 */
	@Test
	void testGetX() {
		Room myRoom2 = new Room(3,4);
		//testing the setup object
		assertEquals("myRoom x-value not correct", myRoom.getX(), 2);
		//testing the new object created here
		assertEquals("myRoom x-value not correct", myRoom2.getX(), 3);
	}

	/**
	 * Test method for {@link Room#getY()}.
	 */
	@Test
	void testGetY() {
		Room myRoom2 = new Room(3,4);
		//testing the setup object
		assertEquals("myRoom y-value not correct", myRoom.getY(), 2);
		//testing the new object created here
		assertEquals("myRoom y-value not correct", myRoom2.getY(), 4);
	}

}
