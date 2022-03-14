package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Maze.QuestionBean;

class QuestionBeanJunits {
	private QuestionBean myQuestion;
	private void setUp(String q, String c, String a) {
		myQuestion = new QuestionBean(q, c, a);
	}

	@Test
	void testConstructorQuestion1() {
		setUp("Question", "c1,c2,c3", "Answer");
		String expected = "Question";
		String actual = myQuestion.getQuestion();
		assertEquals(expected, actual);
	}
	@Test
	void testConstructorAnswer1() {
		setUp("Question", "c1,c2,c3", "Answer");
		String expected = "ANSWER";
		String actual = myQuestion.getCorrect();
		assertEquals(expected, actual);
	}
	@Test
	void testConstructorChoices1() {
		setUp("Question", "c1,c2,c3", "answer");
		String[] expected = {"c1", "c2", "c3"};
		String[] actual = myQuestion.getChoices();
		assertArrayEquals(expected, actual);
	}
	@Test
	void testConstructorChoices2() {
		setUp("Question", "c1", "answer");
		String[] expected = {"c1"};
		String[] actual = myQuestion.getChoices();
		assertArrayEquals(expected, actual);
	}
	@Test
	void testIsTrueTrue() {
		setUp("Question", "c1,c2,c3", "answer");
		myQuestion.setChoice("answer");
		assertTrue(myQuestion.isCorrect());
	}
	@Test
	void testIsTrueFalse() {
		setUp("Question", "c1,c2,c3", "answer");
		myQuestion.setChoice("wrong answer");
		assertFalse(myQuestion.isCorrect());
	}
	@Test
	void testSetChoice() {
		setUp("Question", "c1,c2,c3", "answer");
		String expected = "C1";
		myQuestion.setChoice("c1");
		String actual = myQuestion.getUserEnterd();
		assertEquals(expected, actual);
	}
	

}
