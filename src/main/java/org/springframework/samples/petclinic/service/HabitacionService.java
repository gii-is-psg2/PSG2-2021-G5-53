package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Habitacion;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.repository.HabitacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HabitacionService {
	
	private HabitacionRepository habitacionRepository;
	
	@Autowired
	public HabitacionService(HabitacionRepository habitacionRepository) {
		this.habitacionRepository = habitacionRepository;
	}	
	
	@Transactional(readOnly = true)	
	public List<Habitacion> findAll() throws DataAccessException {
		return habitacionRepository.findAll();
	
	}
	
	@Transactional(readOnly = true)
	public Habitacion findById(int habitacionId) throws DataAccessException {
		return habitacionRepository.findById(habitacionId);
	}
	
	@Transactional
    public void saveHabitacion(Habitacion habitacion) throws DataAccessException {
        habitacionRepository.save(habitacion);
	}
	
	

}
