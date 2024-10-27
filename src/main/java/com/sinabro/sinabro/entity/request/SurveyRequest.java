package com.sinabro.sinabro.entity.request;

import java.util.List;

public record SurveyRequest(
	String creatorName,
	List<QuestionAnswerPair> answers
) {
	public record QuestionAnswerPair(
		Integer questionId,
		Integer answerId
	) {}
}