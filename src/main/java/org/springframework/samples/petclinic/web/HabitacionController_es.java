package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Habitacion;
import org.springframework.samples.petclinic.service.HabitacionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HabitacionController_es {
	
	private final HabitacionService habitacionService;
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@Autowired
	public HabitacionController_es(HabitacionService habitacionService) {
		this.habitacionService = habitacionService;
	}
	
	@GetMapping(value = { "/habitaciones/{reservaId}/todasLasHabitaciones" })
	public String showHabitacionesList(@PathVariable("reservaId") int reservaId, Map<String, Object> model) {
		model.put("reservaId", reservaId);
		List<Habitacion> habs = this.habitacionService.findAll();
		model.put("habitaciones", habs);
		return "habitaciones/habitacionesList_es";
	}
	
	
}
