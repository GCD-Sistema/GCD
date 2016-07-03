package br.edu.ifpb.tsi.gcd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.tsi.gcd.model.Diretor;


public class DiretorDAO extends GenericDAOJPAImpl<Diretor, Long>{
	private static Logger logger = Logger.getLogger(DiretorDAO.class);
	
	public DiretorDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public DiretorDAO(EntityManager em) {
		super(em);
	}
	
	public Diretor read(Diretor d){
		try{
			Query q = this.getEntityManager().createQuery("select d from Diretor d where d.id ='"+d.getId()+"' ");
			return (Diretor) q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Diretor read(Integer id){
		try{
			Query q = this.getEntityManager().createQuery("select d from Diretor d where d.id ='"+id+"' ");
			return (Diretor) q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Diretor> findAll() throws DAOException{
		List<Diretor> Diretors = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Diretor d");
			Diretors = (List<Diretor>) q.getResultList();
		} catch (HibernateException e) {}
		return Diretors;
	}
}
