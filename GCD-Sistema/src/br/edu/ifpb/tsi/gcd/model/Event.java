package br.edu.ifpb.tsi.gcd.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="evento")
@Getter @Setter
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToMany(mappedBy="evento", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Comentario> comentarios;
	@ManyToOne
	private Clube clube;
	@Transient
	private Boolean situacao;
	private String tipo;
	private String nivel;
	private String descricao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	@ManyToOne
	private Pessoa pessoa;
	
	public Event() {
		this.comentarios = new ArrayList<Comentario>();
	}

	public Event(String tipo, String nivel, String descricao, Date data) {
		super();
		this.tipo = tipo;
		this.nivel = nivel;
		this.descricao = descricao;
		this.data = data;
		this.comentarios = new ArrayList<Comentario>();
	}
	
	public void addComentario(Comentario comentario){
		comentario.setEvento(this);
		this.comentarios.add(comentario);
	}
	
	public void deleteComentario(Comentario comentario){
		comentario.setEvento(null);
		this.comentarios.remove(comentario);
	}
	
	public Comentario getComentario(Comentario comentario){
		return this.comentarios.get(this.comentarios.indexOf(comentario));
	}
}
