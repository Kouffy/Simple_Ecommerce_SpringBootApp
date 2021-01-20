package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Article;
import com.example.model.Panier;
import com.example.model.User;
import com.example.repository.PanierRepository;

@Service
public class PanierServiceImpl implements PanierService {

	@Autowired
	PanierRepository panierRepository;
	@Override
	public Panier savePanier(Panier panier) {
		
		return panierRepository.save(panier);
	}
	@Override
	public List<Panier> getPanierByUser(User user) {
		List<Panier> panierUse = (List<Panier>)panierRepository.findAll();
		List<Panier> panierUser= new ArrayList<Panier>();
		for (Panier panier : panierUse) {
			if(panier.getUser()==user)
			{
				panierUser.add(panier);
			}
		}
		return panierUser;
	}
	@Override
	public void deletePanierByUser(User user) {
		
		List<Panier> panierUse = (List<Panier>)panierRepository.findAll();
		List<Panier> panierUser= new ArrayList<Panier>();
		for (Panier panier : panierUse) {
			if(panier.getUser()==user)
			{
				panierUser.add(panier);
			}
		}
		for (Panier panier : panierUser) {
			
			panierRepository.delete(panier);
		}
	}
	@Override
	public List<Panier> getAllPanier() {
		
		return  (List<Panier>) panierRepository.findAll();
	}
	

}
