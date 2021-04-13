package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.AdoptionRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/shelter/animals/adoption")
public class AdoptionController {

	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private PetController petController;
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private AdoptionService adoptionService;
	
	@Autowired
	private AdoptionRepository adoptionRepository;

	@Autowired
	public AdoptionController(PetService petService, OwnerService ownerService, AdoptionService adoptionService, AdoptionRepository adoptionRepository) {

		this.ownerService = ownerService;
		this.petService = petService;
		this.adoptionService = adoptionService;
		this.adoptionRepository = adoptionRepository;

	}

//	La ruta por defecto muestra un listado con las mascotas disponibles para adoptar
	@GetMapping(path = "/")
	public String listadoAdopciones(ModelMap modelmap) {
		List<Adoption> adopciones = (List<Adoption>) adoptionService.findAll();
		
		if(adopciones.iterator().hasNext()) {
			modelmap.addAttribute("adopciones", adopciones);
			return "adoption/listadoAdopciones";
		} else {
			modelmap.addAttribute("message","We are sorry, no pets for adoption...");
			return "adoption/listadoAdopciones";
		}
	}
	
//	
	@GetMapping(value = "/{petId}/new")
    public String initAdoptionForm(@PathVariable("petId") int petId, ModelMap model) {
		if(ownerService.isOwner()) {
			
	        Pet pet = this.petService.findPetById(petId);
	        Adoption adoption = new Adoption();
	        adoption.setPet(pet);
	        
	        model.put("pet", pet);
	        model.put("petId", pet.getId());
	        model.put("adoption", adoption);
	        
	        return "adoption/newAdoption";
	        
		} else {
			model.put("message", "Para realizar una adopcion haga login como owner");
			return "shelter/listadoAnimales";
		}
    }

	@PostMapping(value = "/{petId}/new")
    public String processUpdateForm(Pet pet, @PathVariable("petId") Integer petId, BindingResult result, ModelMap model, @Valid Adoption adoption) {
		
		String nombre = pet.getName();
	
		if (result.hasErrors()) {
			
            model.put("pet", pet);
            return "adoption/newAdoption";
            
        } else {
        	
        	if(!StringUtils.hasLength(nombre) || nombre.length()>50 || nombre.length()<3) {
        		model.addAttribute("message", "name is required and between 3 and 50 characters");
        		return "adoption/newAdoption";
        	}
        	
//        	Configuramos el owner y la mascota que van a participar en la relacion de adopcion
        	Integer ownerId = ownerService.devolverOwnerId();
    	    Owner owner = ownerService.findOwnerById(ownerId);
        	Pet petToAdopt = this.petService.findPetById(petId);
        	
//        	Configuramos la adopcion
        	adoption.setPet(petToAdopt);
        	adoption.setOwner(pet.getOwner());
        	
        	
        	try{
//        		Guardamos la mascota en el servicio para el owner que ha realizado la adopcion
            	log.info("El owner "+owner.getFirstName()+"ha adoptado a la mascota con nombre "+petToAdopt.getName());
            	this.petService.savePet(pet);
            	
            }catch(Exception e){
                e.printStackTrace();
                return "adoption/newAdoption";
                
            }
        	
        	adoption.setPet(petService.findPetById(pet.getId()));
        	petToAdopt.setState("Not avaible");
        	
        	this.petService.savePet(petToAdopt);
        	this.adoptionService.save(adoption);
        	
//        	TODO
        	return petController.listadoAnimales(model);
        }
    }



}
