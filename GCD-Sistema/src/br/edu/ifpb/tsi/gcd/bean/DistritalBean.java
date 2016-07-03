package br.edu.ifpb.tsi.gcd.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.tsi.gcd.dao.DesbravadorDAO;
import br.edu.ifpb.tsi.gcd.dao.DiretorDAO;
import br.edu.ifpb.tsi.gcd.dao.DistritalDAO;
import br.edu.ifpb.tsi.gcd.model.Desbravador;
import br.edu.ifpb.tsi.gcd.model.Diretor;
import br.edu.ifpb.tsi.gcd.model.Distrital;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name="DistritalBean")
@ViewScoped
@Getter @Setter
public class DistritalBean {
	
	private Distrital distrital;
	private DistritalDAO distritalDAO;
	private DesbravadorDAO desbravadorDAO;
	private List<Diretor> diretores;
	private List<Desbravador> desbravadores;
	private DiretorDAO diretorDAO;
	private boolean editando;
	
	@PostConstruct
	public void init() {
		this.distritalDAO = new DistritalDAO();
		this.diretorDAO = new DiretorDAO();
		this.desbravadorDAO = new DesbravadorDAO();
		reset();
	}
	
	public void reset(){
		this.distrital = new Distrital();
		this.diretores = this.diretorDAO.findAll();
		this.desbravadores = this.desbravadorDAO.findAll();
	}
	
	public void salvar(){
		this.distritalDAO.beginTransaction();
		if(editando){
			this.distritalDAO.update(this.distrital);
		}else{
			this.distritalDAO.insert(this.distrital);
		}
		this.distritalDAO.commit();
		reset();
	}
	
	public void excluirDiretor(Diretor diretor){
		this.diretorDAO.beginTransaction();
			diretor.setClubes(null);
			this.diretorDAO.delete(diretor);
		this.diretorDAO.commit();
		reset();
	}
	
	public void excluirDesbravador(Desbravador desbravador){
		this.desbravadorDAO.beginTransaction();
			this.desbravadorDAO.delete(desbravador);
		this.desbravadorDAO.commit();
		reset();
	}

	
}
