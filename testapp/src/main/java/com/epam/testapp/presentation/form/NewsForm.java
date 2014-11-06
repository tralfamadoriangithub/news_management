package com.epam.testapp.presentation.form;

import org.apache.struts.action.ActionForm;

import com.epam.testapp.entity.News;

public class NewsForm extends ActionForm{
	
	private News news;
	
	{
		news = new News();
	} 

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
	
	
}
