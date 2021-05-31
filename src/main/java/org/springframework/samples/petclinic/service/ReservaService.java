package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.var;

@Service
public class ReservaService {
	
	private ReservaRepository reservaRepository;
	
	@Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;

    }
	
	@Transactional
    public void saveReserva(Reserva reserva) throws DataAccessException {
        reservaRepository.save(reserva);
	}
	
	
	public Reserva findById(int reservaId) throws DataAccessException {
		return reservaRepository.findById(reservaId);
	}
	
	public List<Reserva> findReservasByPetId(int petId) throws DataAccessException{
		return reservaRepository.findReservasByPetId(petId);
	}
	
	//Devuelve -1 si no hay solapamiento. 
	//Si hay solapamiento, devuelve un entero que representa el id 
	//de la primera reserva que produce solapamiento
	public Integer reservasSolapadas(Reserva r1, List<Reserva> reservas) {
		int indice = -1;
		var i = 0;
		if(reservas.isEmpty()) {
			return indice;
		} else {
		while(i<reservas.size() && indice == -1) {
			var reservaEscogida = reservas.get(i);
			if((r1.getFechaInicio().isBefore(reservaEscogida.getFechaFin()) ||
					r1.getFechaInicio().isEqual((reservaEscogida.getFechaFin()))) &&
					(reservaEscogida.getFechaInicio().isBefore(r1.getFechaFin()) ||
							reservaEscogida.getFechaInicio().isEqual(r1.getFechaFin()))){
				indice = reservaEscogida.getId();
			}
		}
		}
		
		return indice;
	}
}

