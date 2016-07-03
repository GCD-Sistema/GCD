package br.edu.ifpb.tsi.gcd.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.tsi.gcd.dao.DiretorDAO;
import br.edu.ifpb.tsi.gcd.model.Diretor;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name="DiretorBean")
@ViewScoped
@Getter @Setter
public class DiretorBean {
	
	private Diretor diretor;
	private DiretorDAO diretorDAO;
	private boolean editando;
	
	@ManagedProperty(value="#{LoginBean}")
	private LoginBean loginBean;
	
	@PostConstruct
	public void init() {
		this.diretorDAO = new DiretorDAO();
		reset();
	}
	
	public void reset(){
		this.diretor = new Diretor();
		this.editando = false;
	}
	
	public void prepararEdicao(){
		this.editando = true;
		this.diretor = (Diretor) this.loginBean.getPessoaLogado();
	}
	
	public void salvar(){
		this.diretorDAO.beginTransaction();
		if(editando){
			this.diretorDAO.update(this.diretor);
		}else{
			this.diretorDAO.insert(this.diretor);
		}
		this.diretorDAO.commit();
		reset();
	}
	
}
