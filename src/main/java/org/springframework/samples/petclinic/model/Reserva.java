package org.springframework.samples.petclinic.model;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "Reservas")
public class Reserva extends BaseEntity{
	
	@Column(name = "fecha_inicio") 
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaInicio;
	
	@Column(name = "fecha_fin") 
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaFin;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "reservaowner")
	private Owner owner;

	
	@ManyToOne(cascade = CascadeType.MERGE)
	
	@JoinColumn(name = "petreserva")
	private Pet pet;

}
=======
package org.springframework.samples.petclinic.model;


import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Reservas")
public class Reserva extends BaseEntity{
	
	@Column(name = "fecha_inicio") 
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaInicio;
	
	@Column(name = "fecha_fin") 
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaFin;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "reservaowner")
	private Owner owner;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "reservahabitacion")
	private Habitacion habitacion;

}

