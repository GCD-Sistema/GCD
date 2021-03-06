package br.edu.ifpb.tsi.gcd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.tsi.gcd.model.Pessoa;


public class PessoaDAO extends GenericDAOJPAImpl<Pessoa, Long>{
	private static Logger logger = Logger.getLogger(PessoaDAO.class);
	
	public PessoaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public PessoaDAO(EntityManager em) {
		super(em);
	}
	
	public Pessoa read(Pessoa p){
		try{
			Query q = this.getEntityManager().createQuery("select p from Pessoa p where p.id ='"+p.getId()+"' ");
			return (Pessoa) q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Pessoa> findAll() throws DAOException{
		List<Pessoa> pessoas = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Pessoa p");
			pessoas = (List<Pessoa>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar distritais", e);
		}
		return pessoas;
	}
	
	public Pessoa verificarLogin(String login, String senha){
		Pessoa p = null;
		try{
			Query q = this.getEntityManager().createQuery("select p from Pessoa p where p.login ='"+login+"' and p.senha='"+senha+"'");
			p = (Pessoa) q.getSingleResult();
		}catch(NoResultException e){}
		return p;
	}
}
