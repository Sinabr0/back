package com.sinabro.sinabro.entity.response;

public record SurveyResponse(
	String url
) {
	public static SurveyResponse from(String url) {
		return new SurveyResponse(
			url
		);
	}
}
