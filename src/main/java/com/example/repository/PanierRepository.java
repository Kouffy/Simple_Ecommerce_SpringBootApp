package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.model.Panier;


@Repository
public interface PanierRepository extends CrudRepository <Panier,Long>{
	
}
