package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "article")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "libelle")
	private String libelle;
	@Column(name = "prix")
	private Double prix;
	@Column(name = "qte_stock")
	private Long qte_stock;
	public Article() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public Long getQte_stock() {
		return qte_stock;
	}
	public void setQte_stock(Long qte_stock) {
		this.qte_stock = qte_stock;
	}
	public Article(Long id, String libelle, Double prix, Long qte_stock) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.prix = prix;
		this.qte_stock = qte_stock;
	}
	

	


}
