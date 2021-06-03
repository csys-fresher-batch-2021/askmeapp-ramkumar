package in.ramkumar.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.Answer;

public class TestAnswrDAO {

	/**
	 * Testing with valid add answer.
	 */
	@Test
	public void testAddAnswerDAO() {
		try {
			Answer answer = new Answer();
			answer.setAnswerName("Javascript is a scripting language");
			AnswerDAO answerDAO = new AnswerDAO();
			answerDAO.addAnswer(Integer.valueOf(1), Integer.valueOf(1), answer);
		} catch (DBException e) {
			fail("Not yet implemented");
		}
	}

}
