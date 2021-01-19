package com.example.service;

import java.util.List;

import com.example.model.Panier;
import com.example.model.User;

public interface PanierService {
	Panier savePanier(Panier panier);
	List<Panier> getPanierByUser(User user);
	void deletePanierByUser(User user);
}
