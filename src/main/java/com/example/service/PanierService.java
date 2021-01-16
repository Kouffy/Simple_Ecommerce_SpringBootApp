package com.example.service;

import com.example.model.Panier;
import com.example.web.dto.AjoutPanierDto;

public interface PanierService {
	Panier save(AjoutPanierDto ajoutPanierDto);
}
