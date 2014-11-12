package com.epam.testapp.service;

import java.util.List;

import com.epam.testapp.entity.News;

public class UIHelper {

	public static List<News> removeNewsFromList(List<News> newsList,
			List<Integer> newsId) {

		while (!newsId.isEmpty()) {
			for( News news : newsList ){
				if( newsId.contains(news.getId())){
					newsList.remove(news);
					newsId.remove((Integer)news.getId());
					break;
				}
			}
		}
		return newsList;
	}
	
	public static News getNewsById( List<News> newsList, int newsId ){
		
		News selectedNews = null;
		
		for(News news : newsList ){
			if( news.getId() == newsId){
				selectedNews = news;
				break;
			}
		}
		return selectedNews;
	}
}
