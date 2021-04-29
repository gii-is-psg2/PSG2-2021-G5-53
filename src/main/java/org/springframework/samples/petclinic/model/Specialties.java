package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Specialties {

	private List<Specialty> especialidades;

	@XmlElement
	public List<Specialty> getSpecialtyList() {
		if (especialidades == null) {
			especialidades = new ArrayList<>();
		}
		return especialidades;
	}
}
