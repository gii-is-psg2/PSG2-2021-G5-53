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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.validation.Valid;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VeterinarioController {

	private final VetService vetService;
	private static final String VIEWS_VET_CREATE_FORM = "vets/createForm_es";
	private static final String VIEWS_VET_UPDATE_FORM = "vets/updateForm_es";

	@Autowired
	public VeterinarioController(VetService clinicService) {
		this.vetService = clinicService;
	}

	@GetMapping(value = { "/veterinarios" })
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		return "vets/veterinariosList";
	}
	
	@GetMapping(value = "/veterinarios/nuevo")
	public String initCreationForm(Map<String,Object> model) {
		
		Vet vet = new Vet();
		model.put("vet",vet);
		
		return VIEWS_VET_CREATE_FORM;
	}
	
	@PostMapping(value = "/veterinarios/nuevo")
	public String processCreationForm(@Valid Vet vet,BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_VET_CREATE_FORM;
		}
		else {
			this.vetService.save(vet);
			return "redirect:/veterinarios";
		}
	}

	@GetMapping(value = { "/veterinarios.xml"})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}

	@GetMapping(value = "/veterinarios/{vetId}/editar")
	public String initUpdateOwnerForm(@PathVariable("vetId") int vetId, Model model) {
		Vet vet = this.vetService.findById(vetId);
		model.addAttribute(vet);
		return VIEWS_VET_UPDATE_FORM;
	}

	@PostMapping(value = "/veterinarios/{vetId}/editar")
	public String processUpdateOwnerForm(@Valid Vet vet, BindingResult result,
			@PathVariable("vetId") int vetId) {
		if (result.hasErrors()) {
			return VIEWS_VET_UPDATE_FORM;
		}
		else {
			vet.setId(vetId);
			this.vetService.save(vet);
		
			return "redirect:/veterinarios";
		}
	}
	
	
	
//	@ModelAttribute("types")
//	public Collection<Specialty> populateVetTypes() {
//		return this.vetService.findSpecialtyTypes();
//	}
	
	
	@GetMapping("/veterinarios/{vetId}/eliminar")
	public String deleteVet(@PathVariable ("vetId") int vetId,ModelMap model) {
			this.vetService.removeVet(vetId);
			return "redirect:/veterinarios";
	}




}
