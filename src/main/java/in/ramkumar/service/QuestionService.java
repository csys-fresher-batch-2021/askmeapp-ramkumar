package in.ramkumar.service;

import java.util.List;

import in.ramkumar.dao.QuestionDAO;
import in.ramkumar.exception.DBException;
import in.ramkumar.exception.ServiceException;
import in.ramkumar.exception.UtilException;
import in.ramkumar.exception.ValidationException;
import in.ramkumar.model.Question;
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
	 */
	public void addQuestion(Question question) {
		Question questionObject = null;
		String description = question.getDescription();
		try {
			QuestionValidator.validateQuestion(question);
			questionObject = getQuestion(question.getQuestionName());
			if (questionObject != null) {
				throw new ServiceException(UNABLE_TO_ADD_QUESTION);
			}
			if (description.length() <= 0) {
				question.setDescription(null);
			}
			if (description != null && !description.equals("")) {
				QuestionValidator.validateDescription(question);
			}
			questionDAO.addQuestion(question);
		} catch (DBException | ValidationException | UtilException e) {
			e.printStackTrace();
			throw new ServiceException(UNABLE_TO_ADD_QUESTION);
		}
	}

	/**
	 * @return Returns list of questions.
	 */
	public List<Question> getAllQuestions() {
		List<Question> questionsList = null;
		try {
			questionsList = questionDAO.getAllQuestions();
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to get questions");
		}
		return questionsList;
	}

	/**
	 * @return Returns number of questions in the question list.
	 */
	public int getNumberOfQuestion(){
		try {
			return getAllQuestions().size();
		} catch (ServiceException e) {
			e.printStackTrace();
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
			question = questionDAO.getQuestion(questionName);
		} catch (ValidationException | DBException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to get question");
		}
		return question;
	}

}
