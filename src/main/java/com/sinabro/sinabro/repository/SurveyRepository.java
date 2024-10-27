package com.sinabro.sinabro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinabro.sinabro.entity.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
	Optional<Survey> findSurveyByUrl(String url);
}
