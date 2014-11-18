package com.epam.testapp.service;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;

public interface INewsService {
	
	public List<News> getNewsList() throws ServiceTestappException;

	public News saveNews(News news, String dateString, HttpServletRequest request) throws ServiceTestappException;

	public List<News> removeNews(String[] stringNewsId, List<News> newsList ) throws ServiceTestappException;

	public News getSelectedNews(List<News> newsList, int newsId);
	
	public void setNewsDao( INewsDao newsDao );
	
	public Locale changeLocale ( String localeName, HttpServletRequest request );
	
	public void setCurrentPage( HttpServletRequest request, String pageName );
	
	public String getCurrentPage( HttpServletRequest request );
	
	public void setPreviousPage( HttpServletRequest request, String pageName );
	
	public String getPreviousPage( HttpServletRequest request );
	
}
