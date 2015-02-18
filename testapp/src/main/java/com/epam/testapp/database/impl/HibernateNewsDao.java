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

public final class HibernateNewsDao implements INewsDao {

	private SessionFactory sessionFactory;
	private final String Q_GET_NEWS_LIST = "getNewsList";
	private final String Q_DELETE_NEWS = "deleteNews";
	
	@SuppressWarnings( "unchecked" )
	@Override
	public List<News> getList() throws DaoTestappException {

		Session session = null;
		Transaction transaction = null;
		List<News> newsList = null;
		System.out.println("Hib get list");
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.getNamedQuery( Q_GET_NEWS_LIST );
			newsList = query.list();
			transaction.commit();
		} catch ( HibernateException e ) {
			e.printStackTrace();
			throw new DaoTestappException(
					"NewsDao Hibernate exception in getList method", e );
		} 
		return newsList;
	}

	@Override
	public News save( News news ) throws DaoTestappException {

		Session session = null;
		Transaction transaction = null;
		News addedNews = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			addedNews = (News) session.merge( news );
			transaction.commit();
		} catch ( HibernateException e ) {
			if ( transaction != null ) {
				transaction.rollback();
			}
			throw new DaoTestappException(
					"NewsDao Hibernate exception in save method", e );
		} 
		return addedNews;
	}

	@Override
	public void remove( List<Integer> newsId ) throws DaoTestappException {

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Query q = session.getNamedQuery( Q_DELETE_NEWS );
			q.setParameterList( "id", newsId );
			q.executeUpdate();
			transaction.commit();
		} catch ( HibernateException e ) {
			if ( transaction != null ) {
				transaction.rollback();
			}
			throw new DaoTestappException(
					"NewsDao Hibernate exception in remove method", e );
		}
	}

	@Override
	public News fetchById( int id ) throws DaoTestappException {

		Session session = null;
		Transaction transaction = null;
		News news = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			news = (News) session.get( News.class, id );
			transaction.commit();
		} catch ( HibernateException e ) {
			throw new DaoTestappException(
					"NewsDao Hibernate exception in fetchById method", e );
		} 
		return news;
	}

	public void setSessionFactory( SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	protected void finalize() throws Throwable {
		if(sessionFactory != null){
			sessionFactory.close();
		}
		super.finalize();
	}
	
	
}
