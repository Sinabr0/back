package com.sinabro.sinabro.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinabro.sinabro.entity.Survey;
import com.sinabro.sinabro.entity.request.SurveyRequest;
import com.sinabro.sinabro.service.SurveyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/surveys")
public class SurveyController {

	private final SurveyService surveyService;
	@PostMapping("/")
	public Survey createSurvey(@Valid @RequestBody SurveyRequest request) {
		return surveyService.createSurvey(request);
	}

	// @PostMapping("/{uuid}/answers")
	// public SurveyRequest getSurveyAnswers(@PathVariable String uuid) {
	// 	return surveyService.getSurveyAnswers(uuid);
	// }
}
