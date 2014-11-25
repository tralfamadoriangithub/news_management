package com.epam.testapp.database.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.epam.testapp.database.DaoTestappException;
import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;

public class HibernateNewsDao implements INewsDao {

	private SessionFactory sessionFactory;

	@SuppressWarnings( "unchecked" )
	@Override
	public List<News> getList() throws DaoTestappException {

		Session session = null;
		List<News> newsList = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery( "getNewsList" );
			newsList = query.list();
		} catch ( HibernateException e ) {
			throw new DaoTestappException(
					"NewsDao Hibernate exception in getList method", e );
		} finally {
			if ( session != null ) {
				session.close();
			}
		}
		return newsList;
	}

	@Override
	public News save( News news ) throws DaoTestappException {

		Session session = null;
		Transaction transaction = null;
		News addedNews = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			addedNews = (News) session.merge( news );
			transaction.commit();
		} catch ( HibernateException e ) {
			if ( transaction != null ) {
				transaction.rollback();
			}
			throw new DaoTestappException(
					"NewsDao Hibernate exception in save method", e );
		} finally {
			if ( session != null ) {
				session.close();
			}
		}
		return addedNews;
	}

	@Override
	public void remove( List<Integer> newsId ) throws DaoTestappException {

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query q = session.getNamedQuery( "deleteNews" );
			q.setParameterList( "id", newsId );
			q.executeUpdate();
			transaction.commit();
		} catch ( HibernateException e ) {
			if ( transaction != null ) {
				transaction.rollback();
			}
			throw new DaoTestappException(
					"NewsDao Hibernate exception in remove method", e );
		} finally {
			if ( session != null ) {
				session.close();
			}
		}
	}

	@Override
	public News fetchById( int id ) throws DaoTestappException {

		Session session = null;
		News news = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			news = (News) session.get( News.class, id );
			transaction.commit();
		} catch ( HibernateException e ) {
			throw new DaoTestappException(
					"NewsDao Hibernate exception in fetchById method", e );
		} finally {
			if ( session != null ) {
				session.close();
			}
		}
		return news;
	}

	public void setSessionFactory( SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}
}
