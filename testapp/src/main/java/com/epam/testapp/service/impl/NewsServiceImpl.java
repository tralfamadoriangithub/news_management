package com.epam.testapp.service.impl;

import java.util.List;

import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;
import com.epam.testapp.service.INewsService;
import com.epam.testapp.service.UIHelper;

public class NewsServiceImpl implements INewsService{
	
	private INewsDao newsDao;
	
	@Override
	public List<News> getNewsList() {
		
		List<News> newsList = newsDao.getList();
		
		return newsList;
	}

	@Override
	public News saveNews(News news) {
		
		News savedNews = newsDao.save(news);
		
		return savedNews;
	}

	@Override
	public List<News> removeNews(List<Integer> newsId, List<News> newsList) {
		
		newsDao.remove(newsId);
		List<News> updatedList = UIHelper.removeNewsFromList(newsList, newsId);
		
		return updatedList;
	}

	@Override
	public News getSelectedNews(List<News> newsList, int newsId) {
		
		News selectedNews = UIHelper.getNewsById(newsList, newsId);
		
		return selectedNews;
	}

	@Override
	public void setNewsDao(INewsDao newsDao) {
		
		this.newsDao = newsDao;
		
	}

}
