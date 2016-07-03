package br.edu.ifpb.tsi.gcd.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.tsi.gcd.dao.ClubeDAO;
import br.edu.ifpb.tsi.gcd.dao.EventoDAO;
import br.edu.ifpb.tsi.gcd.model.Clube;
import br.edu.ifpb.tsi.gcd.model.Comentario;
import br.edu.ifpb.tsi.gcd.model.Event;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name="EventoBean")
@ViewScoped
@Getter @Setter
public class EventBean {
	
	private Event evento;
	private List<Clube> clubes;
	private ClubeDAO clubeDAO;
	private List<Event> eventos;
	private EventoDAO eventoDAO;
	private boolean editando;
	private Boolean situacao;
	private Integer clubeSelecionado;
	private Comentario comentario;
	
	@ManagedProperty(value="#{LoginBean}")
	private LoginBean loginBean;
	
	@PostConstruct
	public void init() {
		this.clubeDAO = new ClubeDAO();
		this.eventoDAO = new EventoDAO();
		reset();
	}
	
	public void reset(){
		this.evento = new Event();
		this.editando = false;
		this.clubeSelecionado = null;
		this.clubes = this.clubeDAO.findAll();
		this.eventos = this.eventoDAO.findAll();
		this.situacao = null;
		this.comentario = new Comentario();
	}
	
	public void prepararEdicao(Event evento){
		this.editando = true;
		this.evento = evento;
	}
	
	public void prepararParaSalvar(){
		this.evento.setClube(this.clubeDAO.read(this.clubeSelecionado));
		this.evento.setPessoa(this.loginBean.getPessoaLogado());
		salvar();
	}
	
	public boolean permissaoEvento(Event evento){
		return evento.getPessoa().equals(this.loginBean.getPessoaLogado());
	}
	
	public void adicionarComentario(){
		this.evento.addComentario(comentario);
		salvar();
	}
		
	public void salvar(){
		this.eventoDAO.beginTransaction();
		if(editando){
			this.eventoDAO.update(this.evento);
		}else{
			this.eventoDAO.insert(this.evento);
		}
		this.eventoDAO.commit();
		reset();
	}
	
	public void buscarEventos(){
		if(this.clubeSelecionado != null)
			this.eventos = this.eventoDAO.findAll(this.clubeSelecionado);
		else
			reset();
	}
	
	public void excluir(Event evento){
		this.eventoDAO.beginTransaction();
			evento.setClube(null);
			this.eventoDAO.delete(evento);
		this.eventoDAO.commit();
		reset();
	}
	
	public void deletarComentario(Comentario comentario){
		comentario.setEvento(null);
		this.evento.getComentarios().remove(comentario);
		salvar();
	}
}
