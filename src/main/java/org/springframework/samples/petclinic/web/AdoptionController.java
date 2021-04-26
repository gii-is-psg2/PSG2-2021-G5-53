/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class AdoptionController {

	

	private final AdoptionService adoptionService;
	private final OwnerService ownerService;
	private final PetService petService;
	private final UserService userService;
	private final AuthoritiesService authoritiesService;
	
	@Autowired
	public AdoptionController(AdoptionService adoptionService,OwnerService ownerService, PetService petService, UserService userService, AuthoritiesService authoritiesService) {
		this.adoptionService = adoptionService;
		this.ownerService = ownerService;
		this.petService = petService;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
		
	}

	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping("/adoption")
	public String petsForAdoption(ModelMap model) {
		
		Integer ownerId = getOwnerActivo().getId();
		List<Pet> adoptionList = (List<Pet>) petService.findPetsForAdoption(ownerId);
		
			if(adoptionList.iterator().hasNext()) {
				model.put("adoptionList", adoptionList);
				model.put("ownerId", ownerId);
				return "adoption/adoptionList";
			} else {
				model.put("message", "No hay adopciones disponibles en este momento");
				return "adoption/adoptionList";
			}
		
	}
    
    
    @GetMapping("/adoption/save/{ownerId}/{petId}")
	public String saveAdoption(@PathVariable ("ownerId") int ownerId,@PathVariable ("petId") int petId, ModelMap model) {
    	adoptionService.saveAdoption(ownerId, petId);
		return "welcome";
	}
    
  
    @GetMapping("/adoption/application/{petId}")
    public String adoptionApplication(@PathVariable ("petId") int petId,ModelMap model) {
		model.put("petId", petId);
		return "redirect:/adoption/application/{petId}/new";
    }
    
    @GetMapping(value = "/adoption/application/{petId}/new")
    public String initCreationForm(Map<String, Object> model, @PathVariable ("petId") int petId) {
        Adoption adoption = new Adoption();
        model.put("adoption", adoption);
        return "adoption/adoptionApplication";
    }

    @PostMapping(value = "/adoption/application/{petId}/new")
    public String processCreationForm(Adoption adoption,BindingResult result,ModelMap model,@PathVariable ("petId") int petId) {
    	if (result.hasErrors()) {
    		
    		//model.put("errores",result.getAllErrors());
            model.put("adoption",adoption);
            return "adoption/adoptionApplication";
        }else {
        	Pet pet = this.petService.findPetById(petId);
        	 int ownerId = getOwnerActivo().getId();
        	 adoption.setApplicationOwner(this.ownerService.findOwnerById(ownerId));
        	 adoption.setPet(pet);
        	 adoption.setPetOwner(pet.getOwner());
        	 this.adoptionService.saveAdoption(adoption);
             return "welcome";
        }
    
    }

    private Owner getOwnerActivo() {

    	UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (principal instanceof UserDetails) {
	     userDetails = (UserDetails) principal;
	    }
	    	
	    String userName = userDetails.getUsername();
	    Owner owner = this.ownerService.findByUsername(userName);
	
	    return owner;
	 }
    
    
    
    //Show All
  	@GetMapping("/adoption/requests")
  	public String showAllRequest(ModelMap model) {
  		Owner owner = getOwnerActivo();
  		Integer ownerId = owner.getId();
  		
  		List<Adoption> requestAdoption = this.adoptionService.findAllRequestsByOwnerId(ownerId); 
  		
  		if(requestAdoption.iterator().hasNext()) {
			model.put("requestAdoption", requestAdoption);
			model.put("ownerId", ownerId);
			return "request/requestList";
		} else {
			model.put("message", "No hay adopciones disponibles en este momento");
			return "request/requestList";
		}
  	}
  	
  	
  	
  //Nueva solicitud del owner
  	@GetMapping("/adoption/request/{ownerId}/{petId}/new")
  	public String newRequest(ModelMap model, @PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId) {
  		Owner owner = ownerService.findOwnerById(ownerId);
  		Pet pet = petService.findPetById(petId);
  		model.put("owner", owner);
  		model.put("pet", pet);
  		return "request/createOrUpdateRequestForm";
  	}
  	
  	@PostMapping("/adoption/request/{ownerId}/{petId}/new")
  	public String processCreationForm( @PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, ModelMap model) throws DataAccessException, DuplicatedPetNameException {
  		
  		Pet pet = petService.findPetById(petId);
  		pet.setOnAdoption(true);
  		
  		this.petService.savePet(pet);
  		return "redirect:/adoption";
  	}
  	
  	
  	
  	//Solicitudes de adopcion de la mascota de otro owner  	
	@GetMapping(value="/adoption/request/new/{petId}/{oldOwnerId}/{newOwnerId}")
	public String newOwner(@PathVariable("newOwnerId") int newOwnerId, @PathVariable("oldOwnerId") int oldOwnerId, @PathVariable("petId") int petId) throws DataAccessException, DuplicatedPetNameException {

		Pet pet = this.petService.findPetById(petId);
		
		Owner oldOwner = this.ownerService.findOwnerById(oldOwnerId);
		Owner newOwner = this.ownerService.findOwnerById(newOwnerId);
		
		pet.setOnAdoption(false);
		oldOwner.removePet(pet);
		newOwner.addPet(pet);
		
		this.petService.savePet(pet);
		this.ownerService.saveOwner(oldOwner);
		this.ownerService.saveOwner(newOwner);
		
		return "welcome";
	}
  	    
}
