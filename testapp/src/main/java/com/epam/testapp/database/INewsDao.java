package com.epam.testapp.database;

import java.util.List;

import com.epam.testapp.entity.News;

public interface INewsDao {
	public List<News> getList();
	public News save( News news );
	public void remove( List<Integer> newsId );
	public News fetchById(int id );
}
