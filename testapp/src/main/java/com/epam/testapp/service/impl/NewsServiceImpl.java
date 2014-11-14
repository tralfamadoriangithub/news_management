package com.epam.testapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;
import com.epam.testapp.service.INewsService;
import com.epam.testapp.service.UIHelper;
import com.epam.testapp.util.DateUtil;

public class NewsServiceImpl implements INewsService {

	private INewsDao newsDao;

	@Override
	public List<News> getNewsList() {

		List<News> newsList = newsDao.getList();

		return newsList;
	}

	@Override
	public News saveNews( News news, String dateString, Locale locale ) {

		Date date = DateUtil.getDateFromString( dateString, locale );
		news.setDate( date );
		News savedNews = newsDao.save( news );

		return savedNews;
	}

	@Override
	public List<News> removeNews( String[] stringNewsId, List<News> newsList ) {

		List<News> updatedList = null;

		if ( stringNewsId != null ) {
			List<Integer> intNewsId = new ArrayList<>( stringNewsId.length );

			for ( int i = 0; i < stringNewsId.length; i++ ) {
				intNewsId.add( Integer.parseInt( stringNewsId[i] ) );
			}

			newsDao.remove( intNewsId );
			updatedList = UIHelper.removeNewsFromList( newsList, intNewsId );
		}
		return updatedList;
	}

	@Override
	public News getSelectedNews( List<News> newsList, int newsId ) {

		News selectedNews = UIHelper.getNewsById( newsList, newsId );

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
		session.setAttribute( "language", locale );
		return locale;
	}

	@Override
	public void setCurrentPage( HttpServletRequest request, String pageName ) {
		HttpSession session = request.getSession();
		session.setAttribute( "current_page", pageName );
	}

	@Override
	public String getCurrentPage( HttpServletRequest request ) {
		return (String) request.getSession().getAttribute( "current_page" );
	}

}
