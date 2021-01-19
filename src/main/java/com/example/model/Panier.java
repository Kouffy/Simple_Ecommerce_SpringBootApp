package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "panier")
public class Panier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne
    @JoinColumn(name ="id_article")
    private Article article;
    @ManyToOne
    @JoinColumn(name ="id_client")
    private User user;
    
	@Column(name = "qte_cmd")
	private Long qte_cmd;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getQte_cmd() {
		return qte_cmd;
	}
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setQte_cmd(Long qte_cmd) {
		this.qte_cmd = qte_cmd;
	}
	public Panier(Article article, User user, Long qte_cmd) {
		super();
		this.article = article;
		this.user = user;
		this.qte_cmd = qte_cmd;
	}
	public Panier() {
		super();
	}

}
