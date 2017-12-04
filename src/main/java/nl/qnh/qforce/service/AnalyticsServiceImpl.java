package nl.qnh.qforce.service;

import java.time.Instant;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import nl.qnh.qforce.model.AnalyticLog;

@Service
public class AnalyticsServiceImpl implements AnalyticsService{

	private static final String Q_FORCE_DB = "qforcedb";
	
	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		entityManager = Persistence.createEntityManagerFactory(Q_FORCE_DB).createEntityManager();
	}

	public void register(String method, Object... args) {
		persist(new AnalyticLog(Instant.now(), method, args));
	}

	private boolean persist(Object o){
		entityManager.getTransaction().begin();
		entityManager.persist(o);
		entityManager.getTransaction().commit();
		return true;
	}

}
