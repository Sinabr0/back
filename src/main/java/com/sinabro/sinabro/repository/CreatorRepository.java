package com.sinabro.sinabro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinabro.sinabro.entity.Creator;
import com.sinabro.sinabro.entity.Survey;

public interface CreatorRepository extends JpaRepository<Creator, Long> {
	List<Creator> findBySurvey(Survey survey);
}
