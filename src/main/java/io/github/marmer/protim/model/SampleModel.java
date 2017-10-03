package io.github.marmer.protim.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SAMPLE_MODEL")
public class SampleModel {
	@Id
	@SequenceGenerator(name = "SAMPLE_MODEL_SEQ", sequenceName = "SAMPLE_MODEL_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAMPLE_MODEL_SEQ")
	private Long id;
	@Column(name = "NICE_PROPERTY")
	private String niceProperty;
	@Version
	private Long version;
}