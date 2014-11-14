package com.epam.testapp.presentation.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.epam.testapp.entity.News;
import com.epam.testapp.util.DateUtil;

public class NewsForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private News news;
	private Locale locale;
	private String dateString;
	private String localeName;
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

	public void setNews( News news ) {
		this.news = news;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString( String dateString ) {
		this.dateString = dateString;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList( List<News> newsList ) {
		this.newsList = newsList;
	}

	public News getNewsList( int id ) {
		return newsList.get( id );
	}

	public void setNewsList( News news ) {
		newsList.add( news );
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale( Locale locale ) {
		this.locale = locale;
	}

	public String[] getSelectedNewsId() {
		return selectedNewsId;
	}

	public void setSelectedNewsId( String[] selectedNewsId ) {
		this.selectedNewsId = selectedNewsId;
	}

	public String getLocaleName() {
		return localeName;
	}

	public void setLocaleName( String localeName ) {
		this.localeName = localeName;
	}

	@Override
	public ActionErrors validate( ActionMapping mapping,
			HttpServletRequest request ) {
		ActionErrors actionErrors = new ActionErrors();

		if ( "".equals( news.getTitle() ) || news.getTitle().length() < 10 ) {
			actionErrors
					.add( "title", new ActionMessage( "msg.title.required" ) );
		}

		if ( "".equals( news.getBrief() ) ) {
			actionErrors
					.add( "title", new ActionMessage( "msg.brief.required" ) );
		}

		if ( "".equals( news.getContent() ) ) {
			actionErrors.add( "title", new ActionMessage(
					"msg.content.required" ) );
		}
		if ( !DateUtil.isDateCorrect( dateString, locale ) ) {
			actionErrors
					.add( "title", new ActionMessage( "msg.date.format" ) );
		}
		return actionErrors;
	}

}
