package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "adoption")
public class Adoption extends BaseEntity{
	
	@NotNull
	@Column(name = "info")
	private String applicationInfo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
   
	@NotNull
    @ManyToOne
    @JoinColumn(name = "application_owner")
    private Owner applicationOwner;
	

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Owner getApplicationOwner() {
		return applicationOwner;
	}

	public void setApplicationOwner(Owner applicationOwner) {
		this.applicationOwner = applicationOwner;
	}

	public String getApplicationInfo() {
		return applicationInfo;
	}

	public void setApplicationInfo(String applicationInfo) {
		this.applicationInfo = applicationInfo;
	}	
	
}
