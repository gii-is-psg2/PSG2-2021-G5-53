package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.ReservaService;
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

@Controller
public class ReservaController {
	Logger logger = Logger.getLogger(ReservaController.class.getName());
	private static final String RESERVA = "reserva";
	private  ReservaService reservaService;
	private OwnerService ownerService;
	private PetService petService;
	
	@Autowired
	public ReservaController(ReservaService reservaService, OwnerService ownerService,
			PetService petService) {
		this.reservaService = reservaService;
		this.ownerService = ownerService;
		this.petService = petService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("reserva")
	public void initReservaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ReservaValidator());
	}
	
	
	private Owner getOwnerActivo() {
		
		UserDetails userDetails = null;
		try {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
          userDetails = (UserDetails) principal;
        }
	}catch(Exception e){
		  e.printStackTrace();
	}
        String userName = userDetails.getUsername();
        return  this.ownerService.findByUsername(userName);
	}
	
	@GetMapping(value = "/reservas/{petId}/new")
	public String initCreationForm(Map<String, Object> model, @PathVariable("petId") Integer petId) {
		var reserva = new Reserva();
		System.out.println("El id de la reserva es " + reserva.getId());
		model.put(RESERVA, reserva);
		return "reservas/createOrUpdateReservaForm";
	}
	
	@PostMapping(value = "/reservas/{petId}/new")
	public String processCreationForm(@Valid Reserva reserva, BindingResult result,ModelMap model, @PathVariable("petId") Integer petId) {
		if (result.hasErrors()) {
			model.put(RESERVA,reserva);
			return "reservas/createOrUpdateReservaForm";
		}
		else {
			System.out.println("El id de la reserva es " + reserva.getId());
			List<Reserva> reservasByPet = this.reservaService.findReservasByPetId(petId);
			int solapamiento = this.reservaService.reservasSolapadas(reserva, reservasByPet);
			if(solapamiento!=-1) {
				logger.log(Level.ALL,"Solapamiento = " + solapamiento);
				
				var reservaEncontrada = this.reservaService.findById(solapamiento);
				logger.log(Level.ALL,"Reserva encontrada =" + reservaEncontrada.getId());
				model.put(RESERVA, reservaEncontrada);
				model.put("petId", petId);
				return "reservas/reservasSolapadas";
			} else {
			var pet = this.petService.findPetById(petId);
			var owner = getOwnerActivo();
			reserva.setOwner(owner);
			reserva.setPet(pet);
			System.out.println("El id de la reserva antes de guardar es " + reserva.getId());
			this.reservaService.saveReserva(reserva);
			System.out.println("El id de la reserva después de guardar es " + reserva.getId());
			return "welcome";
		}
		}
	}
	

	@GetMapping(value = "/reservas/choosePet")
	public String elegirPet(ModelMap model) {
		var owner = getOwnerActivo();
		List<Pet> pets = owner.getPets();
		model.put("pets", pets);
		return "reservas/elegirPet";
	}
	
	
	
	

}