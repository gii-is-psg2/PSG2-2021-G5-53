package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.stereotype.Repository;
@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Integer>{

	 Reserva findById(int reservaId) throws DataAccessException;

	 @Query(value = "SELECT * FROM RESERVAS WHERE PETRESERVA = ?1", 
				nativeQuery = true)
	 List<Reserva> findReservasByPetId(int petId) throws DataAccessException;



}