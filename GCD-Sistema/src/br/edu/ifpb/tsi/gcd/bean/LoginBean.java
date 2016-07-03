package br.edu.ifpb.tsi.gcd.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.tsi.gcd.dao.PessoaDAO;
import br.edu.ifpb.tsi.gcd.model.Diretor;
import br.edu.ifpb.tsi.gcd.model.Distrital;
import br.edu.ifpb.tsi.gcd.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name="LoginBean")
@SessionScoped
@Getter @Setter
public class LoginBean extends GenericBean{
	
	private String login;
	private String senha;
	private String novaSenha;
	private String reSenha;
	private Pessoa pessoaLogado;
	
	@PostConstruct
	public void init() {
	}
	
	public void reset(){
		this.login = null;
		this.senha = null;
		this.novaSenha = null;
		this.reSenha = null;
	}
	
	public String verificarLogin(){
		  PessoaDAO pDAO = new PessoaDAO();
		  Pessoa p = pDAO.verificarLogin(this.login,this.senha);
		  if(p != null){
			  this.pessoaLogado = p;
			  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", "Login Efetuado com Sucesso");
			  FacesContext.getCurrentInstance().addMessage("Login Efetuado com Sucesso", message);
			  reset();
			  if(verificarTipoDiretor()){
				  return "/painel-diretor.xhtml";
			  }
			  return "/painel.xhtml";
		  }else
			  return "/index.xhtml?faces-redirect=true";

	}
	
	public String logout(){
		this.pessoaLogado = null;
		return "/index.xhtml?faces-redirect=true";
	}
	
	public void alterarSenha(){
	
		if(!this.senha.equals(this.pessoaLogado.getSenha())){
	        this.addErrorMessage("Senha inexistente!");
		}else if(this.novaSenha.equals(this.reSenha)){
			PessoaDAO pDAO = new PessoaDAO();
			pDAO.beginTransaction();
				this.pessoaLogado.setSenha(this.novaSenha);
				pDAO.update(this.pessoaLogado);
				this.addSuccessMessage("Senha alterada com sucesso!");
				pDAO.commit();
		}else{
			this.addErrorMessage("A confirmação da nova senha não confere!");
		}
	}
	
	// métodos para verificações de permissões(rendered - componentes)
	
	public boolean verificarTipoDiretor(){
		return (this.pessoaLogado instanceof Diretor);
	}
	
	public boolean verificarTipoDistrital(){
		return (this.pessoaLogado instanceof Distrital);
	}

	
	
}
