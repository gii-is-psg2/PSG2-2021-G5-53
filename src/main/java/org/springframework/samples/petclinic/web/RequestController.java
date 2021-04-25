package org.springframework.samples.petclinic.web;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Request;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.service.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestController {
	
	RequestService requestService;
	OwnerRepository ownerRepostiory;
	PetRepository petRepository;
	
	@Autowired
	public RequestController(RequestService requestService, OwnerRepository ownerRepostiory, PetRepository petRepository) {
		this.requestService = requestService;
		this.ownerRepostiory = ownerRepostiory;
		this.petRepository = petRepository;
	}
	
	//Show All
	@GetMapping("/requests")
	public String showAllRequest(ModelMap model) {
		Request request = this.requestService.findAllRequest();
		model.put("request", request);
		return "request/requestList";
	}
	
	//Show
	@GetMapping("/request/{requestId}")
	public String showRequest(@PathVariable("requestId") int requestId, ModelMap model) {
		Request request = this.requestService.findRequestById(requestId);
		model.put("request", request);
		return "request/requestDetails";
	}
	
	
	//Nueva solicitud
	@GetMapping("/adoption/request/{ownerId}/{petId}/new")
	public String newRequest(ModelMap model, @PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId) {
		Owner owner = ownerRepostiory.findById(ownerId);
		Pet pet = petRepository.findById(petId);
		model.put("owner", owner);
		model.put("pet", pet);
		return "request/createOrUpdateRequestForm";
	}
	
	@PostMapping("/adoption/request/{ownerId}/{petId}/new")
	public String processCreationForm(@Valid Request request, BindingResult result, @PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, ModelMap model) {
		
		Pet pet = petRepository.findById(petId);
		pet.setOnAdoption(true);
		
		this.petRepository.save(pet);
		return "redirect:/adoption";
	}
}
