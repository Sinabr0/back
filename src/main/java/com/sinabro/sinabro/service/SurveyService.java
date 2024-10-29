package com.sinabro.sinabro.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinabro.sinabro.dto.SubmitterInfo;
import com.sinabro.sinabro.entity.Creator;
import com.sinabro.sinabro.entity.Submitter;
import com.sinabro.sinabro.entity.Survey;
import com.sinabro.sinabro.entity.request.SurveyRequest;
import com.sinabro.sinabro.entity.response.SubmitterResultResponse;
import com.sinabro.sinabro.entity.response.SubmitterScoreResponse;
import com.sinabro.sinabro.entity.response.SurveyResponse;
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
	private static final String baseUrl = "https://front-psi-two-39.vercel.app/";

	public SurveyResponse createSurvey(SurveyRequest surveyRequest) {
		Survey survey = Survey.builder()
			.creatorName(surveyRequest.creatorName())
			.url(generateSurveyUUID())
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
		return SurveyResponse.from(generateSurveyUrl(survey.getUrl()));
	}

	private String generateSurveyUrl(String uuid) {
		return baseUrl + uuid;
	}

	private String generateSurveyUUID() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 5);
	}
	public SurveyRequest getSurveyAnswers(String uuid) {
		Survey survey = surveyRepository.findSurveyByUrl(uuid)
			.orElseThrow(() -> new IllegalArgumentException("등록되지 않은 설문지 입니다."));

		List<SurveyRequest.QuestionAnswerPair> answers = creatorRepository.findBySurvey(survey).stream()
			.map(creator -> SurveyRequest.QuestionAnswerPair.of(creator.getQuestionNumber(), creator.getAnswerNumber()))
			.toList();

		return SurveyRequest.of(survey.getCreatorName(), answers);
	}

	public SubmitterResultResponse getSubmitterResults(String uuid) {
		Survey survey = surveyRepository.findSurveyByUrl(uuid)
			.orElseThrow(() -> new IllegalArgumentException("등록되지 않은 설문지 입니다."));

		List<SubmitterInfo> submitterInfoList = submitterRepository.findBySurveyOrderByScoreDesc(survey).stream()
			.map(SubmitterInfo::of)
			.toList();

		return SubmitterResultResponse.of(survey.getCreatorName(), submitterInfoList);
	}

	public SubmitterScoreResponse createSubmission(SurveyRequest request, String uuid) {
		Survey survey = surveyRepository.findSurveyByUrl(uuid)
			.orElseThrow(() -> new IllegalArgumentException("등록되지 않은 설문지 입니다."));

		List<Creator> creatorAnswers = creatorRepository.findBySurvey(survey);
		Map<Integer, Integer> correctAnswers = creatorAnswers.stream()
			.collect(Collectors.toMap(Creator::getQuestionNumber, Creator::getAnswerNumber));

		// 100점 만점 기준
		int totalQuestions = correctAnswers.size();
		long correctCount = request.answers().stream()
			.filter(answer -> correctAnswers.get(answer.questionId()) != null &&
				correctAnswers.get(answer.questionId()).equals(answer.answerId()))
			.count();

		int score = (int) ((double) correctCount / totalQuestions * 100);

		Submitter submitter = Submitter.builder()
			.survey(survey)
			.name(request.creatorName())
			.score(score)
			.build();

		submitterRepository.save(submitter);

		return SubmitterScoreResponse.from(submitter.getScore());
	}
}
