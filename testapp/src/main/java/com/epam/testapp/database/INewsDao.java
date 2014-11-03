package com.epam.testapp.database;

import java.util.List;

import com.epam.testapp.entity.News;

public interface INewsDao {
	public List<News> getList();
	public void save( News news );
	public void remove( int ... id );
	public News fetchById( int ... id );
}
