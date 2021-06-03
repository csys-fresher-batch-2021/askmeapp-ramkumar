package in.ramkumar.service;

import java.util.List;

import in.ramkumar.dao.AnswerDAO;
import in.ramkumar.exception.UtilException;
import in.ramkumar.exception.DBException;
import in.ramkumar.exception.ServiceException;
import in.ramkumar.exception.ValidationException;
import in.ramkumar.model.Answer;
import in.ramkumar.util.StringUtil;
import in.ramkumar.validator.NumberValidator;

public class AnswerService {

	private final AnswerDAO answerDAO = new AnswerDAO();

	/**
	 * Add answer. Answer should not be null, empty. If it is a valid answer it will
	 * be added to answerList.
	 * 
	 * @param questionId
	 * @param userId
	 * @param answerObject
	 */
	public void addAnswer(Integer questionId, Integer userId, Answer answerObject) {
		try {
			NumberValidator.checkingForNullAndEmpty(questionId);
			NumberValidator.checkingForNullAndEmpty(userId);
			int answerLength = StringUtil.getLength(answerObject.getAnswerName());
			if (answerLength > 0) {
				answerDAO.addAnswer(questionId, userId, answerObject);
			}
		} catch (DBException e) {
			throw new ServiceException("Unable to add answer");
		} catch (UtilException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * @param questionId
	 * @return Returns list of answers for the given question.
	 */
	public List<Answer> getAllAnswers(Integer questionId) {
		List<Answer> answerList = null;
		try {
			NumberValidator.checkingForNullAndEmpty(questionId);
			answerList = answerDAO.getAllAnswers(questionId);
		} catch (ValidationException | DBException e) {
			throw new ServiceException("Unable to get anwers");
		}
		return answerList;
	}

}
