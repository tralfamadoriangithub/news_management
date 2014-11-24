package com.epam.testapp.database.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.epam.testapp.database.DaoTestappException;
import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;

public class HibernateNewsDao implements INewsDao{

	private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	
	@SuppressWarnings( "unchecked" )
	@Override
	public List<News> getList() throws DaoTestappException {
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery( "News.getNewsList" );
		List<News> newsList = query.list();
		session.close();
		return newsList;
	}

	@Override
	public News save( News news ) throws DaoTestappException {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		News addedNews = (News) session.merge( news );
		session.getTransaction().commit();
		session.close();
		return addedNews;
	}

	@Override
	public void remove( List<Integer> newsId ) throws DaoTestappException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		News news;
		for( Integer id : newsId ){
			news = (News) session.get( News.class, id );
			session.delete( news );
		}
		transaction.commit();
		session.close();
	}

	@Override
	public News fetchById( int id ) throws DaoTestappException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		News news = (News) session.get( News.class, id );
		transaction.commit();
		session.close();
		return news;
	}

}
