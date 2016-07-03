package br.edu.ifpb.tsi.gcd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.tsi.gcd.model.Distrital;


public class DistritalDAO extends GenericDAOJPAImpl<Distrital, Long>{
	private static Logger logger = Logger.getLogger(DistritalDAO.class);
	
	public DistritalDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public DistritalDAO(EntityManager em) {
		super(em);
	}
	
	public Distrital read(Distrital d){
		try{
			Query q = this.getEntityManager().createQuery("select d from distrital d where d.id ='"+d.getId()+"' ");
			return (Distrital) q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public boolean findAll() throws DAOException{
		List<Distrital> distritalis = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from distrital d");
			distritalis = (List<Distrital>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar distritais", e);
		}
		return (distritalis.size() > 0);
	}
	
	public Distrital validaDistrital(String login, String senha){
		try{
			Query q = this.getEntityManager().createQuery("select p from Distrital p where p.login ='"+login+"' and p.senha='"+senha+"'");
			Distrital d = (Distrital) q.getSingleResult();
			if(d != null){
			return d;
			}
		}catch(NoResultException e){
			return null;
		}
		return null;
	}
	
	public void alterarSenha(Distrital d, String novaSenha){

		d.setSenha(novaSenha);
		this.beginTransaction();
		this.update(d);
		this.commit();
		System.out.println("dao merge");
	}
}
