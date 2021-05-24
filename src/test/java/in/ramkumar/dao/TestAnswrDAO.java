package in.ramkumar.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.Answer;
import in.ramkumar.model.Question;

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
			answerDAO.addAnswer("What is Javacript?", answer);
		} catch (DBException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}

	}

}
