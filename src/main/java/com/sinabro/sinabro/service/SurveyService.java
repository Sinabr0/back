package com.sinabro.sinabro.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinabro.sinabro.entity.Creator;
import com.sinabro.sinabro.entity.Survey;
import com.sinabro.sinabro.entity.request.SurveyRequest;
import com.sinabro.sinabro.repository.CreatorRepository;
import com.sinabro.sinabro.repository.SubmitterRepository;
import com.sinabro.sinabro.repository.SurveyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SurveyService {

	private final SurveyRepository surveyRepository;
	private final SubmitterRepository submitterRepository;
	private final CreatorRepository creatorRepository;

	public Survey createSurvey(SurveyRequest surveyRequest) {
		Survey survey = Survey.builder()
			.creatorName(surveyRequest.creatorName())
			.url(generateSurveyUrl())
			.createdAt(LocalDateTime.now())
			.build();
		survey = surveyRepository.save(survey);

		List<SurveyRequest.QuestionAnswerPair> answers = surveyRequest.answers();
		for (SurveyRequest.QuestionAnswerPair answer : answers) {
			Creator creator = Creator.builder()
				.survey(survey)
				.questionNumber(answer.questionId())
				.answerNumber(answer.answerId())
				.build();
			creatorRepository.save(creator);
		}
		return survey;
	}

	private String generateSurveyUrl() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 5); // 5자리 문자열
	}

}
