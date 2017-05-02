package com.sales.repositories;

/*
 * Ian Burke - G00307742
 */

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.sales.models.Product;

@Repository //indicates an interface is a repository
public interface ProductInterface extends CrudRepository<Product, Long> 
{
	//CRUDRepository provides Create Read Update Delete functionality for the entity (Product) that is being managed..
	//The ProductInterface has abstract methods (used for Abstraction)
	//Spring provides the implementation by implementing the above repository interface methods at runtime.
	//These abstract methods can be used to carry out queries
}
