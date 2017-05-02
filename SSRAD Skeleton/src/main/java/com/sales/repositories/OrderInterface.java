package com.sales.repositories;

/*
 * Ian Burke - G00307742
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sales.models.Order;

@Repository //indicates an interface is a repository
public interface OrderInterface extends CrudRepository<Order, Long>{
	//CRUDRepository provides Create Read Update Delete functionality for the entity (Order) that is being managed..
			//The ProductInterface has abstract methods (used for Abstraction)
			//Spring provides the implementation by implementing the above repository interface methods at runtime.
			//These abstract methods can be used to carry out queries
}
