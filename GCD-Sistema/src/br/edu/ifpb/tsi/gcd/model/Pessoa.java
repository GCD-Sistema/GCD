package br.edu.ifpb.tsi.gcd.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpb.tsi.gcd.interfaces.PessoaInterface;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public abstract class Pessoa implements PessoaInterface{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String email;
	private String telefone;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	private Boolean sexo;
	private String nomePai;
	private String nomeMae;
	
	public Pessoa(){}
	
	public Pessoa(String nome, String email, String telefone, Date dataNasc, Boolean sexo, String nomePai, String nomeMae){
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNasc;
		this.sexo = sexo;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
	}
	
	
	@Override
	public String getSenha() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLogin() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setSenha(String senha) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLogin(String login) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
