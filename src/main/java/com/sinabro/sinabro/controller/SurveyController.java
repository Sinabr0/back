package com.sinabro.sinabro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinabro.sinabro.entity.request.SurveyRequest;
import com.sinabro.sinabro.entity.response.SubmitterResultResponse;
import com.sinabro.sinabro.entity.response.SubmitterScoreResponse;
import com.sinabro.sinabro.entity.response.SurveyResponse;
import com.sinabro.sinabro.service.SurveyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "1. [설문조사]", description = "설문조사 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sinabro/api/v1/surveys")
public class SurveyController {

	private final SurveyService surveyService;
	@Operation(summary = "설문지 생성", description = "첫 설문지를 생성합니다.")
	@PostMapping("")
	public SurveyResponse createSurvey(@Valid @RequestBody SurveyRequest request) {
		return surveyService.createSurvey(request);
	}

	@Operation(summary = "특정 설문지 정답 요청", description = "특정 설문지의 정답을 받아옵니다.")
	@GetMapping("/{uuid}/answers")
	public SurveyRequest getSurveyAnswers(@PathVariable String uuid) {
		return surveyService.getSurveyAnswers(uuid);
	}

	@Operation(summary = "설문지 제출자 결과 리스트 요청", description = "제출자들의 결과 정보를 가져옵니다.")
	@GetMapping("/{uuid}/results")
	public SubmitterResultResponse getSubmitterResults(@PathVariable String uuid) {
		return surveyService.getSubmitterResults(uuid);
	}

	@Operation(summary = "설문지에 대한 응답 제출", description = "설문지에 대한 응답 제출")
	@PostMapping("/{uuid}/submission")
	public SubmitterScoreResponse createSubmission(@Valid @RequestBody SurveyRequest request, @PathVariable String uuid ) {
		return surveyService.createSubmission(request, uuid);
	}
}
