package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Request;
import org.springframework.samples.petclinic.repository.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
	
	private RequestRepository requestRepository;
	
	@Autowired
	public RequestService(RequestRepository requestRepository) {
		this.requestRepository = requestRepository;
	}
	
	@Transactional
	public void saveRequest(Request request) throws DataAccessException {
		requestRepository.save(request);
	}

	public Request findRequestById(int requestId) {
		return requestRepository.findRequestById(requestId);
	}

	public Request findAllRequest() {
		return requestRepository.findAllRequest();
	}

}
