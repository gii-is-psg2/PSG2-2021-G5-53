package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "adoptions")
public class Adoption extends BaseEntity {
	 
	    @OneToOne
		@JoinColumn(name = "pet_id") //Adopted Pet
		private Pet pet;
	    
	    @ManyToOne
	    @JoinColumn(name = "owner_id") //Owner who makes the adoption
	    private Owner owner;
	   

//	    Getters & Setters
		public Pet getPet() {
			return pet;
		}
		
		public Owner getOwner() {
			return owner;
		}

		public void setPet(Pet pet) {
			this.pet = pet;
		}

		public void setOwner(Owner owner) {
			this.owner = owner;
		}   
	    
	    
}