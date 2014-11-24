package com.epam.testapp.database.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.epam.testapp.database.DaoTestappException;
import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;

public class JpaNewsDao implements INewsDao {

	private EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory( "NEWS_PERSISTENCE" );

	@SuppressWarnings( "unchecked" )
	@Override
	public List<News> getList() throws DaoTestappException {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNamedQuery( "News.getNewsList" );
		List<News> newsList = q.getResultList();
		em.close();
		return newsList;
	}

	@Override
	public News save( News news ) throws DaoTestappException {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		News addedNews = em.merge( news );
		em.getTransaction().commit();
		em.close();
		return addedNews;
	}

	@Override
	public void remove( List<Integer> newsId ) throws DaoTestappException {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		
		News news;
		for ( Integer id : newsId ) {
			news = em.find( News.class, id );	
			em.remove( news );
		}
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public News fetchById( int id ) throws DaoTestappException {
		EntityManager em = entityManagerFactory.createEntityManager();
		News news = em.find( News.class, id );
		em.close();
		return news;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory( EntityManagerFactory entityManagerFactory ) {
		this.entityManagerFactory = entityManagerFactory;
	}

}
