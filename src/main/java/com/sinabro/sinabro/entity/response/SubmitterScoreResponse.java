package com.sinabro.sinabro.entity.response;

public record SubmitterScoreResponse(
	Integer score
) {
	public static SubmitterScoreResponse from(Integer score) {
		return new SubmitterScoreResponse(score);
	}
}
