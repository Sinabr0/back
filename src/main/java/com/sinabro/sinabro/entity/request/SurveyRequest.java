package com.sinabro.sinabro.entity.request;

import java.util.List;

public record SurveyRequest(
	String creatorName,
	List<QuestionAnswerPair> answers
) {
	public static SurveyRequest of(String creatorName, List<QuestionAnswerPair> answers) {
		return new SurveyRequest(creatorName, answers);
	}
	public record QuestionAnswerPair(
		Integer questionId,
		Integer answerId
	) {
		public static QuestionAnswerPair of(Integer questionId, Integer answerId) {
			return new QuestionAnswerPair(questionId, answerId);
		}
	}
}