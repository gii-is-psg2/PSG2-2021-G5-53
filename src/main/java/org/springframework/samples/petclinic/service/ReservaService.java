package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
