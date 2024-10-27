package com.sinabro.sinabro.entity.response;

import com.sinabro.sinabro.entity.Survey;

public record SurveyResponse(
	String url
) {
	public static SurveyResponse from(Survey survey) {
		return new SurveyResponse(
			survey.getUrl()
		);
	}
}
