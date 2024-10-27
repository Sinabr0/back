package com.sinabro.sinabro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinabro.sinabro.entity.Submitter;

public interface SubmitterRepository extends JpaRepository<Submitter, Long> {
}
