package com.epam.testapp.service;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;

public interface INewsService {
	
	/**
	 * Method gets list of news from data source.
	 * 
	 * @return List of currently available news.
	 * @throws ServiceTestappException If something wrong.
	 */
	public List<News> getNewsList() throws ServiceTestappException;

	/**
	 * Method saves news in data source. Current version is far from excellence.
	 * 
	 * @param news New News object to save.
	 * @param dateString Containing date string.
	 * @param request Current request.
	 * @return Saved news.
	 * @throws ServiceTestappException If something wrong.
	 */
	public News saveNews(News news, String dateString, HttpServletRequest request) throws ServiceTestappException;

	/**
	 * Method removes selected news from data source. 
	 * 
	 * @param stringNewsId String array with news id to delete. Temporary parameter, must be replaced by Integer array/
	 * @throws ServiceTestappException If something wrong.
	 */
	public void removeNews(String[] stringNewsId  ) throws ServiceTestappException;

	/**
	 * Method returns news from data source by id.
	 * @param newsId News id to return.
	 * @return News object from data source.
	 * @throws ServiceTestappException If something wrong.
	 */
	public News getSelectedNews(int newsId ) throws ServiceTestappException;
	
	/**
	 * Method changes application locale.
	 * 
	 * @param localeName String name of locale. Sould be replaced by Locale object.
	 * @param request Current request.
	 * @return New Locale object
	 */
	public Locale changeLocale ( String localeName, HttpServletRequest request );
	
	public void setCurrentPage( HttpServletRequest request, String pageName );
	
	public String getCurrentPage( HttpServletRequest request );
	
	public void setPreviousPage( HttpServletRequest request, String pageName );
	
	public String getPreviousPage( HttpServletRequest request );
	
	public void setNewsDao( INewsDao newsDao );
	
}
