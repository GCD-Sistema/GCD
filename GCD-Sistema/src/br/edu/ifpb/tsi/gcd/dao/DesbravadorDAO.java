package br.edu.ifpb.tsi.gcd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import br.edu.ifpb.tsi.gcd.model.Desbravador;

public class DesbravadorDAO extends GenericDAOJPAImpl<Desbravador, Long>{

	public DesbravadorDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public DesbravadorDAO(EntityManager em) {
		super(em);
	}
	
	public Desbravador read(Integer id){
		try{
			Query q = this.getEntityManager().createQuery("select d from Desbravador d where d.id ='"+id+"' ");
			return (Desbravador) q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	
	
	public List<Desbravador> findAll() throws DAOException{
		List<Desbravador> desbravadores = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Desbravador d");
			desbravadores = (List<Desbravador>) q.getResultList();
		} catch (HibernateException e) {}
		return desbravadores;
	}
}
