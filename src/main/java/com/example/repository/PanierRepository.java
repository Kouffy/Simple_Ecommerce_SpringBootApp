package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.model.Panier;

@Repository
public interface PanierRepository {
	Panier findById(long id);
}
