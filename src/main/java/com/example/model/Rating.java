package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {
		public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getValeur() {
		return valeur;
	}

	public void setValeur(Long valeur) {
		this.valeur = valeur;
	}

		public Rating() {
		super();
	}

		public Rating(String email, Long valeur) {
			super();
			this.email = email;
			this.valeur = valeur;
		}

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@Column(name = "email")
	    private String email;
	    
		@Column(name = "valeur")
		private Long valeur;

}
