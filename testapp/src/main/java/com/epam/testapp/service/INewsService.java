package com.epam.testapp.service;

import java.util.List;

import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;

public interface INewsService {
	
	public List<News> getNewsList();

	public News saveNews(News news);

	public List<News> removeNews(List<Integer> newsId, List<News> newsList );

	public News getSelectedNews(List<News> newsList, int newsId);
	
	public void setNewsDao( INewsDao newsDao );
	
}
