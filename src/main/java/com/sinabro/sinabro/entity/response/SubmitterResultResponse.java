package com.sinabro.sinabro.entity.response;

import java.util.List;

import com.sinabro.sinabro.dto.SubmitterInfo;

public record SubmitterResultResponse(
	String creatorname,
	List<SubmitterInfo> submitterInfoList
) {
	public static SubmitterResultResponse of (String creatorname, List<SubmitterInfo> submitterInfoList) {
		return new SubmitterResultResponse(
			creatorname,
			submitterInfoList
		);
	}
}
