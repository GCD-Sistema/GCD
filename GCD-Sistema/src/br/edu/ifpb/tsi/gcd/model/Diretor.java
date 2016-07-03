package br.edu.ifpb.tsi.gcd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="diretor")
@Getter @Setter
public class Diretor extends Pessoa{
	
	private String login;
	private String senha;
	@OneToMany(mappedBy="diretor", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Clube> clubes;
	@OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Evento> eventos;
	
	public Diretor(){
		this.eventos = new ArrayList<Evento>();
	}
	
	public Diretor(String login, String senha){
		super();
		this.login = login;
		this.senha = senha;
		this.eventos = new ArrayList<Evento>();
	}
	
}
