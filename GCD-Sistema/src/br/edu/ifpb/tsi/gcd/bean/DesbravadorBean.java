package br.edu.ifpb.tsi.gcd.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.tsi.gcd.dao.DesbravadorDAO;
import br.edu.ifpb.tsi.gcd.model.Desbravador;
import br.edu.ifpb.tsi.gcd.model.Diretor;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name="DesbravadorBean")
@ViewScoped
@Getter @Setter
public class DesbravadorBean {
	private String nome;
	private String email;
	private String telefone;
	private Date dataNascimento;
	private Boolean sexo;
	private String nomePai;
	private String nomeMae;
	private String cargo;
	private String endereco;
	private String unidade;
	private Desbravador desbravador;
	private Boolean editando;
	private DesbravadorDAO dao;
	
	public DesbravadorBean(){}
	
	public void addDesbravador(){
	
		this.dao = new DesbravadorDAO();
		this.desbravador = new Desbravador(this.nome, this.email, this.telefone, this.dataNascimento, this.sexo, this.nomePai, this.nomeMae, this.cargo, this.endereco, this.unidade);
		this.dao.beginTransaction();
		this.dao.insert(desbravador);
		this.dao.commit();
	}
	public void reset(){
		this.desbravador = new Desbravador();
		this.editando = false;
	}
	public void prepararEdicao(){
		this.editando = true;
		this.desbravador = new Desbravador(this.nome, this.email, this.telefone, this.dataNascimento, this.sexo, this.nomePai, this.nomeMae, this.cargo, this.endereco, this.unidade);
	}
	public void removerDesbravador(int Id){
		this.dao = new DesbravadorDAO();
		this.dao.beginTransaction();
		Desbravador desbravador = this.dao.read(Id);
		this.dao.delete(desbravador);
		this.dao.commit();
	}
	
	
}
