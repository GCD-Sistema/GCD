package br.edu.ifpb.tsi.gcd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="clube")
@Getter @Setter
public class Clube {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String distrito;
	@ManyToOne
	private Diretor diretor;
	private Boolean situacao;
	@OneToMany(mappedBy="clube", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Event> eventos;
	//private List<Pessoa> membros;
	//private List<Unidade> unidades;
	
	public Clube(){
		this.situacao = true;
		this.eventos = new ArrayList<Event>();
	}
	
	public Clube(String nome, String distrito){
		this.nome = nome;
		this.distrito = distrito;
		this.situacao = true;
		this.eventos = new ArrayList<Event>();
		//this.membros = new ArrayList<Pessoa>();
	}
	
	public void addEvento(Event evento){
		evento.setClube(this);
		this.eventos.add(evento);
	}
	
	public void removerEvento(Event evento){
		evento.setClube(null);
		this.eventos.remove(evento);
	}
	
	public void addMebro(Pessoa membro){
		//this.membros.add(membro);
	}
	
	public void removerMembro(Pessoa membro){
		//this.membros.remove(membro);
	}


	
}
