package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
	
@Getter
@Setter
@Entity
	@Table(name = "Habitaciones")
	public class Habitacion extends BaseEntity {
	
	@OneToOne(optional=true)
	@JoinColumn(name = "pethabitacion")
	private Pet pet;
		
		
	}
