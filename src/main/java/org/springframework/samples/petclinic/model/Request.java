package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "request")
public class Request extends BaseEntity{
	
	@Column(name = "title")
	@NotEmpty
	@Size(min = 4, max = 100)
	private String title;

	@Column(name = "text")
	@Size(min = 4, max = 2000)
	@NotEmpty
	private String text;

}
