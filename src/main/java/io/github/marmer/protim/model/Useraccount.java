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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERACCOUNT")
public class Useraccount {
	@Id
	@SequenceGenerator(name = "USERACCOUNT_SEQ", sequenceName = "USERACCOUNT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERACCOUNT_SEQ")
	private Long id;
	@Column(name = "username")
	private String username;
	// TODO should not be a cleartext password!
	@Column(name = "password")
	private String password;
	@Version
	private Long version;
}