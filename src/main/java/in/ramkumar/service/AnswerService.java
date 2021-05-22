package in.ramkumar.service;

import java.util.List;

import in.ramkumar.dao.AnswerDAO;
import in.ramkumar.exception.UtilException;
import in.ramkumar.exception.DBException;
import in.ramkumar.exception.ServiceException;
import in.ramkumar.exception.ValidationException;
import in.ramkumar.model.Answer;
import in.ramkumar.util.StringUtil;
import in.ramkumar.validator.StringValidator;

public class AnswerService {

	private final AnswerDAO answerDAO = new AnswerDAO();

	/**
	 * Add answer. Answer should not be null, empty. If it is a valid answer it will
	 * be added to answerList.
	 * 
	 * @param answer
	 */
	public void addAnswer(String questionName, Answer answerObject) {
		try {
			int answerLength = StringUtil.getLength(answerObject.getAnswerName());
			if (answerLength > 0) {
				answerDAO.addAnswer(questionName, answerObject);
			}
		} catch (DBException | UtilException e) {
			throw new ServiceException("Unable to add answer");
		}
	}

	/**
	 * @param questionName
	 * @return Returns list of answers for the given question.
	 */
	public List<Answer> getAllAnswers(String questionName) {
		List<Answer> answerList = null;
		try {
			StringValidator.checkingForNullAndEmpty(questionName);
			 answerList = answerDAO.getAllAnswers(questionName);
		} catch (ValidationException | DBException e) {
			throw new ServiceException("Unable to get anwers");
		}
		return answerList;
	}

}
