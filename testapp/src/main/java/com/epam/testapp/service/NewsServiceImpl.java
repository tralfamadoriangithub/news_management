package com.epam.testapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.testapp.database.DaoTestappException;
import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;
import com.epam.testapp.service.AttributeName;
import com.epam.testapp.service.INewsService;
import com.epam.testapp.service.ServiceTestappException;
import com.epam.testapp.util.DateUtil;

public class NewsServiceImpl implements INewsService {

	private INewsDao newsDao;
	
	
	@Override
	public List<News> getNewsList() throws ServiceTestappException {

		List<News> newsList = null;
		try {
			newsList = newsDao.getList();
		} catch ( DaoTestappException e ) {
			throw new ServiceTestappException( e.getMessage(), e );
		}
		return newsList;
	}

	@Override
	public News saveNews( News news, String dateString, HttpServletRequest request ) throws ServiceTestappException {

		HttpSession session = request.getSession();
		Locale locale = (Locale) session.getAttribute( AttributeName.LANGUAGE );
		Date date = DateUtil.getDateFromString( dateString, locale );
		news.setDate( date );
		News savedNews = null;
		try {
			savedNews = newsDao.save( news );
		} catch ( DaoTestappException e ) {
			throw new ServiceTestappException( e.getMessage(), e );
		}

		return savedNews;
	}

	@Override
	public void removeNews( String[] stringNewsId  ) throws ServiceTestappException {

		if ( stringNewsId != null ) {
			List<Integer> intNewsId = new ArrayList<>( stringNewsId.length );

			for ( int i = 0; i < stringNewsId.length; i++ ) {
				intNewsId.add( Integer.parseInt( stringNewsId[i] ) );
			}

			try {
				newsDao.remove( intNewsId );
			} catch ( DaoTestappException e ) {
				throw new ServiceTestappException( e.getMessage(), e );
			}
		}
	}

	@Override
	public News getSelectedNews( int newsId  ) throws ServiceTestappException {

		News selectedNews;
		try {
			selectedNews = newsDao.fetchById( newsId );
		} catch ( DaoTestappException e ) {
			throw new ServiceTestappException( e.getMessage(), e );
		}
		return selectedNews;
	}

	@Override
	public void setNewsDao( INewsDao newsDao ) {

		this.newsDao = newsDao;

	}

	@Override
	public Locale changeLocale( String localeName, HttpServletRequest request ) {

		Locale locale = new Locale( localeName );
		HttpSession session = request.getSession();
		session.setAttribute( AttributeName.LANGUAGE, locale );
		return locale;
	}

	@Override
	public void setCurrentPage( HttpServletRequest request, String pageName ) {
		HttpSession session = request.getSession();
		session.setAttribute( AttributeName.CURRENT_PAGE, pageName );
	}

	@Override
	public String getCurrentPage( HttpServletRequest request ) {
		return (String) request.getSession().getAttribute( AttributeName.CURRENT_PAGE );
	}

	@Override
	public void setPreviousPage( HttpServletRequest request, String pageName ) {
		HttpSession session = request.getSession();
		session.setAttribute( AttributeName.PREVIOUS_PAGE, pageName );
	}

	@Override
	public String getPreviousPage( HttpServletRequest request ) {
		return (String) request.getSession().getAttribute( AttributeName.PREVIOUS_PAGE );
	}

}
