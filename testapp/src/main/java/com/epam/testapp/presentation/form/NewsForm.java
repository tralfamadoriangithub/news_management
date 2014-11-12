package com.epam.testapp.presentation.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.struts.action.ActionForm;

import com.epam.testapp.entity.News;

public class NewsForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private News news;
	private Locale locale;
	private String dateString;
	private List<News> newsList;
	private String[] selectedNewsId;

	{
		news = new News();
		newsList = new ArrayList<>();
		locale = Locale.getDefault();
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public News getNewsList( int id ) {
		return newsList.get(id);
	}

	public void setNewsList(News news) {
		newsList.add(news);
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String[] getSelectedNewsId() {
		return selectedNewsId;
	}

	public void setSelectedNewsId(String[] selectedNewsId) {
		this.selectedNewsId = selectedNewsId;
	}

	
}
