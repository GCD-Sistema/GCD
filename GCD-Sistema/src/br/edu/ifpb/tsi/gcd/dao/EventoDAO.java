package br.edu.ifpb.tsi.gcd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.tsi.gcd.model.Evento;


public class EventoDAO extends GenericDAOJPAImpl<Evento, Long>{
	private static Logger logger = Logger.getLogger(EventoDAO.class);
	
	public EventoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public EventoDAO(EntityManager em) {
		super(em);
	}
	
	public Evento read(Evento e){
		try{
			Query q = this.getEntityManager().createQuery("select e from Evento e where e.id ='"+e.getId()+"' ");
			return (Evento) q.getSingleResult();
		}catch(NoResultException e1){
			return null;
		}
	}
	
	public List<Evento> findAll() throws DAOException{
		List<Evento> eventos = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Evento e");
			eventos = (List<Evento>) q.getResultList();
		} catch (HibernateException e) {}
		return eventos;
	}
	
	public List<Evento> findAll(Integer idClube) throws DAOException{
		List<Evento> eventos = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Evento e where e.clube.id = '"+idClube+"' ");
			eventos = (List<Evento>) q.getResultList();
		} catch (HibernateException e) {}
		return eventos;
	}
	
}
