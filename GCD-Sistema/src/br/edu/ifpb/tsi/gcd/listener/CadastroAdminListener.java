package br.edu.ifpb.tsi.gcd.listener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.edu.ifpb.tsi.gcd.dao.PersistenceUtil;
import br.edu.ifpb.tsi.gcd.model.Distrital;

@ManagedBean(eager=true)
@ApplicationScoped
public class CadastroAdminListener{
	private final String LOGIN = "distrital";
	private final String SENHA = "distrital";
	
	
	@PostConstruct
	public void init(){
		Distrital d = new Distrital(LOGIN, SENHA);
		EntityManager em = PersistenceUtil.getEntityManager();		
		em.getTransaction().begin();
			if(em.createQuery("FROM Distrital c").getResultList().size() == 0){
				em.persist(d);
			}
		em.getTransaction().commit();
	}
	
	@PreDestroy
	public void destroy(){}
	
}
