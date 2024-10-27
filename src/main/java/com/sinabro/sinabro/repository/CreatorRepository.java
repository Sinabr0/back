package com.sinabro.sinabro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinabro.sinabro.entity.Creator;

public interface CreatorRepository extends JpaRepository<Creator, Long> {
}
