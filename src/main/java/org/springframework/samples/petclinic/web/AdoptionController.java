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
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.UserService;
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
	
	@Autowired
	public AdoptionController(AdoptionService adoptionService,OwnerService ownerService, UserService userService, AuthoritiesService authoritiesService) {
		this.adoptionService = adoptionService;
		this.ownerService = ownerService;
	}

	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping("/adoption")
	public String petsForAdoption(ModelMap model) {
		List<Pet> adoptionList = (List<Pet>) adoptionService.findPetsForAdoption();
		Integer ownerId = getOwnerActivo().getId();
		
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
    public String initCreationForm(@PathVariable ("petId") int petId, Map<String, Object> model) {
        Adoption adoption = new Adoption();
        model.put("adoption", adoption);
        return "adoption/adoptionApplication";
    }
    

    @PostMapping(value = "/adoption/application/{petId}/new")
    public String processCreationForm(@Valid Adoption adoption,BindingResult result,ModelMap model,@PathVariable ("petId") int petId) {
    	if (result.hasErrors()) {
    		
    		model.put("errores",result.getAllErrors());
            model.put("adoption",adoption);
            return "adoption/adoptionApplication";
        }else {
        	 int ownerId = getOwnerActivo().getId();
           	adoptionService.saveApplication(petId, ownerId);
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

}
