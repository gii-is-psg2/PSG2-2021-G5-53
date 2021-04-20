package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;



public interface CauseRepository extends Repository<Cause,Integer>{
	
	void save(Cause cause);
    
    @Query("SELECT c FROM Cause c where c.id=:causeId")
	Cause findByCauseId(@Param(value = "causeId") int causeId);
	
	@Query("SELECT sum(d.amount) FROM Donation d where d.cause.id=:causeId")
    Double totalBudget(@Param(value = "causeId") int causeId);
    
    @Query("SELECT d FROM Donation d where d.cause.id=:causeId")
    Collection<Donation> findDonations(@Param(value = "causeId") int causeId);
    
    @Query("SELECT c FROM Cause c")
    Collection<Cause> findAll();

}
