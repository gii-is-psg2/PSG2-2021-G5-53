package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Request;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request, Integer>{
	@Query("SELECT request FROM Request request WHERE request.id =:id")
	public Request findRequestById(@Param("id") int id);

	@Query("SELECT request FROM Request request")
	public Request findAllRequest();
	
	
	
}
