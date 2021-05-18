package in.ramkumar.service;

import java.util.ArrayList;
import java.util.List;

import in.ramkumar.model.Answer;
import in.ramkumar.model.Question;
import in.ramkumar.util.StringUtil;

public class AnswerService {

	private AnswerService() {
		// Default constructor
	}

	/**
	 * Add answer. Answer should not be null, empty. If it is a valid answer it will
	 * be added to answerList.
	 * 
	 * @param answer
	 */
	public static void addAnswer(String questionName, Answer answerObject) {
		int answerLength = StringUtil.getLength(answerObject.getAnswerName());
		if (answerLength > 0) {
			Question question = QuestionService.getQuestion(questionName);
			List<Answer> answer = question.getAnswerList();
			if (answer == null) {
				answer = new ArrayList<>();
			}
			answer.add(answerObject);
			question.setAnswerList(answer);
		}
	}

}
