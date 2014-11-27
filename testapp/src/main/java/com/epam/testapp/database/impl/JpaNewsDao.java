package com.epam.testapp.database.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.epam.testapp.database.DaoTestappException;
import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;

public class JpaNewsDao implements INewsDao {

	private EntityManagerFactory entityManagerFactory;
	private final String Q_GET_NEWS_LIST = "News.getNewsList";
	private final String Q_DELETE_NEWS = "News.deleteNews";

	@SuppressWarnings( "unchecked" )
	@Override
	public List<News> getList() throws DaoTestappException {

		EntityManager em = null;
		List<News> newsList = null;
		try {
			em = entityManagerFactory.createEntityManager();
			Query q = em.createNamedQuery( Q_GET_NEWS_LIST );
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
		EntityTransaction transaction = null;
		News addedNews = null;
		try {
			em = entityManagerFactory.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			addedNews = em.merge( news );
			transaction.commit();
		} catch ( Exception e ) {
			if ( transaction != null ) {
				transaction.rollback();
			}
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
		EntityTransaction transaction = null;
		try {
			em = entityManagerFactory.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			Query query = em.createNamedQuery( Q_DELETE_NEWS );
			query.setParameter( "id", newsId );
			query.executeUpdate();
			transaction.commit();
		} catch ( Exception e ) {
			if ( transaction != null ) {
				transaction.rollback();
			}
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
