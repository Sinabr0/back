package com.sinabro.sinabro.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "creator_name", nullable = false, length = 20)
	private String creatorName;

	@Column(nullable = false, length = 200)
	private String url;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "survey")
	private List<Submitter> submitters;
}