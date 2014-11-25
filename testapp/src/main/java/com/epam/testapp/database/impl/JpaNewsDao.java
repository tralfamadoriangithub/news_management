package com.epam.testapp.database.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.epam.testapp.database.DaoTestappException;
import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;

public class JpaNewsDao implements INewsDao {

	private EntityManagerFactory entityManagerFactory;

	@SuppressWarnings( "unchecked" )
	@Override
	public List<News> getList() throws DaoTestappException {

		EntityManager em = null;
		List<News> newsList = null;
		try {
			em = entityManagerFactory.createEntityManager();
			Query q = em.createNamedQuery( "News.getNewsList" );
			newsList = q.getResultList();
		} catch ( Exception e ) {
			throw new DaoTestappException(
					"NewsDao exception in getList method", e );
		} finally {
			if ( em != null ) {
				em.close();
			}
		}
		return newsList;
	}

	@Override
	public News save( News news ) throws DaoTestappException {

		EntityManager em = null;
		News addedNews = null;
		try {
			em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();
			addedNews = em.merge( news );
			em.getTransaction().commit();
		} catch ( Exception e ) {
			throw new DaoTestappException( "NewsDao exception in save method",
					e );
		} finally {
			if ( em != null ) {
				em.close();
			}
		}
		return addedNews;
	}

	@Override
	public void remove( List<Integer> newsId ) throws DaoTestappException {

		EntityManager em = null;
		try {
			em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();
			News news;
			for ( Integer id : newsId ) {
				news = em.find( News.class, id );
				em.remove( news );
			}
			em.getTransaction().commit();
		} catch ( Exception e ) {
			throw new DaoTestappException(
					"NewsDao exception in remove method", e );
		} finally {
			if ( em != null ) {
				em.close();
			}
		}
	}

	@Override
	public News fetchById( int id ) throws DaoTestappException {

		EntityManager em = null;
		News news = null;
		try {
			em = entityManagerFactory.createEntityManager();
			news = em.find( News.class, id );
		} catch ( Exception e ) {
			throw new DaoTestappException(
					"NewsDao exception in fetchById method", e );
		} finally {
			if ( em != null ) {
				em.close();
			}
		}
		return news;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory ) {
		this.entityManagerFactory = entityManagerFactory;
	}
}
