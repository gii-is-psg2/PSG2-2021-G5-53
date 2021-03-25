package org.springframework.samples.petclinic.repository;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Habitacion;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends CrudRepository<Habitacion, Integer>{
	
	List<Habitacion> findAll() throws DataAccessException;
	
	
	Habitacion findById(int habitacionId) throws DataAccessException;
	
	

}
