package com.sales.repositories;

/*
 * Ian Burke - G00307742
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sales.models.Customer;

@Repository //indicates an interface is a repository
public interface CustomerInterface extends CrudRepository<Customer, Long> 
{
	//CRUDRepository provides Create Read Update Delete functionality for the entity (Customer) that is being managed..
		//The ProductInterface has abstract methods (used for Abstraction)
		//Spring provides the implementation by implementing the above repository interface methods at runtime.
		//These abstract methods can be used to carry out queries

}
