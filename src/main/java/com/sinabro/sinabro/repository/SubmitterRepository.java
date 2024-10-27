package com.sinabro.sinabro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinabro.sinabro.entity.Submitter;
import com.sinabro.sinabro.entity.Survey;

public interface SubmitterRepository extends JpaRepository<Submitter, Long> {
	List<Submitter> findBySurveyOrderByScoreDesc(Survey survey);
}
