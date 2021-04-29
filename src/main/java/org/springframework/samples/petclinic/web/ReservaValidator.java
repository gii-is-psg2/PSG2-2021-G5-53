package org.springframework.samples.petclinic.web;



import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReservaValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Reserva.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		var reserva = (Reserva) obj;
		LocalDate fechaInicio = reserva.getFechaInicio();
		LocalDate fechaFin = reserva.getFechaFin();
		var fechaActual = LocalDate.now();
		
		if (fechaInicio==null) {
			errors.rejectValue("fechaInicio", "Mandatory field", "Please, choose an initial date for your booking");
		} 
		else if(fechaInicio.isBefore(fechaActual)) {
			errors.rejectValue("fechaInicio", "Initial date can't be before today!", 
					"Initial date can't be before today!");
			
	}
		
		if (fechaFin==null) {
			errors.rejectValue("fechaFin", "Mandatory field", "Please, choose an end date for your booking");
		} 
		else if(fechaFin.isBefore(fechaActual) || fechaFin.isBefore(fechaInicio)) {
			errors.rejectValue("fechaFin", "End date can't be before today or initial date!", 
					"End date can't be before today or initial date!");
		}
	
	
}
	
}
