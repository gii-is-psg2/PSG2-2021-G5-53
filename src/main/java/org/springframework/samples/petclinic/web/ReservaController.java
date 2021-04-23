package org.springframework.samples.petclinic.web;
import java.util.List;
import java.util.Map;
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
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
          userDetails = (UserDetails) principal;
        }
        String userName = userDetails.getUsername();
        Owner owner = this.ownerService.findByUsername(userName);
        return  owner;
	}

	
	@GetMapping(value = "/reservas/{petId}/new")
	public String initCreationForm(Map<String, Object> model, @PathVariable("petId") Integer petId) {
		Reserva reserva = new Reserva();
		model.put("reserva", reserva);
		return "reservas/createOrUpdateReservaForm";
	}

	
	@PostMapping(value = "/reservas/{petId}/new")
	public String processCreationForm(@Valid Reserva reserva, BindingResult result,ModelMap model, @PathVariable("petId") Integer petId) {
		if (result.hasErrors()) {
			model.put("reserva",reserva);
			return "reservas/createOrUpdateReservaForm";
		}
		else {
			List<Reserva> reservasByPet = this.reservaService.findReservasByPetId(petId);
			int solapamiento = this.reservaService.reservasSolapadas(reserva, reservasByPet);
			if(solapamiento!=-1) {
				System.out.println("Solapamiento = " + solapamiento);
				Reserva reservaEncontrada = this.reservaService.findById(solapamiento);
				System.out.println("Reserva encontrada =" + reservaEncontrada.getId());
				model.put("reserva", reservaEncontrada);
				model.put("petId", petId);
				return "reservas/reservasSolapadas";
			} else {
			Pet pet = this.petService.findPetById(petId);
			Owner owner = getOwnerActivo();
			reserva.setOwner(owner);
			reserva.setPet(pet);
			this.reservaService.saveReserva(reserva);
			return "welcome";
		}
		}
	}

	


	
	@GetMapping(value = "/reservas/choosePet")
	public String elegirPet(ModelMap model) {
		Owner owner = getOwnerActivo();
		List<Pet> pets = owner.getPets();
		model.put("pets", pets);
		return "reservas/elegirPet";
	}

	


}