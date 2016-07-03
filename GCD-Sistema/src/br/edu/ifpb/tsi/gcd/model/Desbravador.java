package br.edu.ifpb.tsi.gcd.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Desbravador")
@Getter @Setter
public class Desbravador extends Pessoa {
	
	private String cargo;
	private String endereco;
	private String unidade;
	//classe atual
	//lista de classes
	//especialidades
	
	public Desbravador(String nome, String email, String telefone, Date dataNasc, Boolean sexo, String nomePai, String nomeMae, String cargo, String endereco, String unidade){
		super(nome, email, telefone, dataNasc, sexo, nomePai, nomeMae);
		this.cargo = cargo;
		this.endereco = endereco;
		this.unidade = unidade;
	}
	
	public Desbravador(){}
	
	
	

}
