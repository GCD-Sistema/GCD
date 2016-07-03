package br.edu.ifpb.tsi.gcd.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.tsi.gcd.dao.ClubeDAO;
import br.edu.ifpb.tsi.gcd.dao.DiretorDAO;
import br.edu.ifpb.tsi.gcd.model.Clube;
import br.edu.ifpb.tsi.gcd.model.Diretor;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name="ClubeBean")
@ViewScoped
@Getter @Setter
public class ClubeBean {
	
	private Clube clube;
	private List<Clube> clubes;
	private ClubeDAO clubeDAO;
	private List<Diretor> diretores;
	private DiretorDAO diretorDAO;
	private boolean editando;
	private Boolean situacao;
	private Integer diretorSelecionado;
	
	@PostConstruct
	public void init() {
		this.clubeDAO = new ClubeDAO();
		this.diretorDAO = new DiretorDAO();
		reset();
	}
	
	public void reset(){
		this.editando = false;
		this.clube = new Clube();
		this.diretores = this.diretorDAO.findAll();
		this.diretorSelecionado = null;
		this.clubes = this.clubeDAO.findAll();
		this.situacao = null;
	}
	
	public void prepararEdicao(Clube clube, boolean editando){
		this.editando = editando;
		this.clube = clube;
		this.situacao = clube.getSituacao();
		this.diretorSelecionado = this.clube.getDiretor().getId();
	}
	
	public void prepararParaSalvar(){
		this.clube.setDiretor(this.diretorDAO.read(this.diretorSelecionado));
		this.clube.setSituacao(this.situacao);
		salvar();
	}
		
	public void salvar(){
		this.clubeDAO.beginTransaction();
		if(editando){
			this.clubeDAO.update(this.clube);
		}else{
			this.clubeDAO.insert(this.clube);
		}
		this.clubeDAO.commit();
		reset();
	}
	
	public void excluir(Clube clube){
		this.clubeDAO.beginTransaction();
			clube.setDiretor(null);
			this.clubeDAO.delete(clube);
		this.clubeDAO.commit();
		reset();
	}


	
}
