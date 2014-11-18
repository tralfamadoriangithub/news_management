package com.epam.testapp.database;

import java.util.List;

import com.epam.testapp.entity.News;

public interface INewsDao {
	public List<News> getList() throws DaoTestappException;
	public News save( News news ) throws DaoTestappException;
	public void remove( List<Integer> newsId ) throws DaoTestappException;
	public News fetchById(int id ) throws DaoTestappException;
}
