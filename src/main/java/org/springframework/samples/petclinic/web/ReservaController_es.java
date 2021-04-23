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
public class ReservaController_es {

	private  ReservaService reservaService;
	private OwnerService ownerService;
	private PetService petService;

	@Autowired
	public ReservaController_es(ReservaService reservaService, OwnerService ownerService,
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
	
	@GetMapping(value = "/reservas/nueva")
	public String initCreationForm(Map<String, Object> model) {
		Reserva reserva = new Reserva();
		model.put("reserva", reserva);
		return "reservas/createOrUpdateReservaForm_es";
	}
	
	@PostMapping(value = "/reservas/nueva")
	public String processCreationForm(@Valid Reserva reserva, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			model.put("reserva",reserva);
			return "reservas/createOrUpdateReservaForm_es";
		}
		else {
			Owner owner = getOwnerActivo();
			reserva.setOwner(owner);
			this.reservaService.saveReserva(reserva);
			return "redirect:/habitaciones/" + String.valueOf(reserva.getId()) + "/todasLasHabitaciones";
		}
	}

	
//	@GetMapping(value = "/reservas/{reservaId}/todasLasHabitacionesDisponibles/{habitacionId}")
//	public String anadirHabitacionAReserva(@PathVariable("reservaId") int reservaId, @PathVariable("habitacionId") int habitacionId, ModelMap model) {
//		Reserva reserva = this.reservaService.findById(reservaId);
//		Habitacion hab = this.habitacionService.findById(habitacionId);
//		reserva.setHabitacion(hab);
//		this.reservaService.saveReserva(reserva);
//		return "redirect:/reservas/{reservaId}/todasLasHabitacionesDisponibles/{habitacionId}/elegirMascota";
//	}

	@GetMapping(value = "/reservas/{reservaId}/todasLasHabitacionesDisponibles/{habitacionId}/elegirMascota")
	public String elegirPet(@PathVariable("reservaId") int reservaId, @PathVariable("habitacionId") int habitacionId,
			ModelMap model) {
		Reserva reserva = this.reservaService.findById(reservaId);
		Owner owner = reserva.getOwner();
		List<Pet> pets = owner.getPets();
		model.put("reservaId", reservaId);
		model.put("habitacionId", habitacionId);
		model.put("pets", pets);
		return "reservas/elegirPet_es";
	}

	
//	@GetMapping(value = "/reservas/{reservaId}/todasLasHabitacionesDisponibles/{habitacionId}/{petId}")
//	public String asignarPetAHabitacion(@PathVariable("reservaId") int reservaId, @PathVariable("habitacionId") int habitacionId,
//			@PathVariable("petId") int petId, ModelMap model) {
//		Habitacion hab = this.habitacionService.findById(habitacionId);
//		hab.setPet(this.petService.findPetById(petId));
//		this.habitacionService.saveHabitacion(hab);
//		return "bienvenido";
//	}



}