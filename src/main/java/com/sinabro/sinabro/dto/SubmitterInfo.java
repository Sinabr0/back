package com.sinabro.sinabro.dto;

import com.sinabro.sinabro.entity.Submitter;

public record SubmitterInfo(
	String name,
	Integer score
) {
	public static SubmitterInfo of(Submitter submitter) {
		return new SubmitterInfo(
			submitter.getName(),
			submitter.getScore()
		);
	}
}
