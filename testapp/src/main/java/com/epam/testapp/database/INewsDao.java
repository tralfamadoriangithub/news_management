package com.epam.testapp.database;

import java.util.List;

import com.epam.testapp.entity.News;

public interface INewsDao {
	/**
	 * Method returns news list from database.
	 * 
	 * @return List of news.
	 * @throws DaoTestappException
	 *             If Something wrong.
	 */
	public List<News> getList() throws DaoTestappException;

	/**
	 * Method saves news in database.
	 * 
	 * @param news
	 *            News item to save.
	 * @return Saved news item.
	 * @throws DaoTestappException
	 *             If something wrong.
	 */
	public News save( News news ) throws DaoTestappException;

	/**
	 * Method removes news from database.
	 * 
	 * @param newsId
	 *            List of news ID to delete.
	 * @throws DaoTestappException
	 *             If something wrong.
	 */
	public void remove( List<Integer> newsId ) throws DaoTestappException;

	/**
	 * Method fetches news by specified ID.
	 * 
	 * @param id
	 *            News ID.
	 * @return News item.
	 * @throws DaoTestappException
	 *             If something wrong.
	 */
	public News fetchById( int id ) throws DaoTestappException;
}
