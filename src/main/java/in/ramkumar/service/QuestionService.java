package in.ramkumar.service;

import java.util.List;

import in.ramkumar.dao.QuestionDAO;
import in.ramkumar.exception.DBException;
import in.ramkumar.exception.ServiceException;
import in.ramkumar.exception.UtilException;
import in.ramkumar.exception.ValidationException;
import in.ramkumar.model.Question;
import in.ramkumar.validator.NumberValidator;
import in.ramkumar.validator.QuestionValidator;
import in.ramkumar.validator.StringValidator;

public class QuestionService {

	private static final String UNABLE_TO_ADD_QUESTION = "Unable to add question";
	private final QuestionDAO questionDAO = new QuestionDAO();

	/**
	 * Add Question. This method validates the question, if it is a valid question
	 * then it will be added to questionList.
	 * 
	 * @param question
	 * @param userId
	 * @return Returns the question if the question already exist.
	 */
	public Question addQuestion(Question question, Integer userId) {
		Question questionObject = null;
		String description = question.getDescription();
		try {
			QuestionValidator.validateQuestion(question);
			questionObject = getQuestion(question.getQuestionName());
			if (description != null && !description.trim().equals("")) {
				QuestionValidator.validateDescription(question);
				questionDAO.addQuestion(question, userId);
			} else if(questionObject == null){
				question.setDescription(null);
				questionDAO.addQuestion(question, userId);
			}
		} catch (DBException e) {
			throw new ServiceException(UNABLE_TO_ADD_QUESTION);
		} catch (ValidationException | UtilException | ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
		return questionObject;
	}

	/**
	 * @return Returns list of questions.
	 */
	public List<Question> getAllQuestions() {
		List<Question> questionsList = null;
		try {
			questionsList = questionDAO.getAllQuestions();
		} catch (DBException e) {
			throw new ServiceException("Unable to get questions");
		}
		return questionsList;
	}

	/**
	 * @return Returns number of questions in the question list.
	 */
	public int getNumberOfQuestion() {
		try {
			return getAllQuestions().size();
		} catch (ServiceException e) {
			throw new ServiceException("Unable to get number of questions");
		}
	}

	/**
	 * @param questionName
	 * @return Returns the Question object for the given question.
	 */
	public Question getQuestion(String questionName) {
		Question question = null;
		try {
			StringValidator.checkingForNullAndEmpty(questionName);
			question = questionDAO.getQuestionByKeywords(questionName);
		} catch (DBException e) {
			throw new ServiceException("Unable to get question");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		return question;
	}

	/**
	 * 
	 * @param questionId
	 * @return Returns the answers count for the given questionId.
	 */
	public Integer getAnswersCountByQuestionId(Integer questionId) {
		Integer answersCount = 0;
		try {
			NumberValidator.checkingForNullAndEmpty(questionId);
			answersCount = questionDAO.getAnswersCountByQuestionId(questionId);
		} catch (DBException e) {
			throw new ServiceException("Unable to get answers count");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		return answersCount;
	}

	/**
	 * @param questionId
	 * @return Returns the Question object for the given questionId.
	 */
	public Question getQuestionById(Integer questionId) {
		Question question = null;
		try {
			NumberValidator.checkingForNullAndEmpty(questionId);
			question = questionDAO.getQuestionById(questionId);
		} catch (DBException e) {
			throw new ServiceException("Unable to get question");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		return question;
	}

	/**
	 * @return Returns list of related questions.
	 */
	public List<Question> getRelatedQuestions(String question) {
		List<Question> questionsList = null;
		try {
			StringValidator.checkingForNullAndEmpty(question);
			questionsList = questionDAO.getRelatedQuestions(question);
		} catch (DBException e) {
			throw new ServiceException("Unable to get questions");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		return questionsList;
	}
}
