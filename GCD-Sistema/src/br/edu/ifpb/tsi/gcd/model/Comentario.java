package br.edu.ifpb.tsi.gcd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comentario")
@Getter @Setter
public class Comentario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String texto;
	@ManyToOne
	private Evento evento;
	
	public Comentario() {}
	public Comentario(Evento evento, String texto){
		this.evento = evento;
		this.texto = texto;
	}

	
}
