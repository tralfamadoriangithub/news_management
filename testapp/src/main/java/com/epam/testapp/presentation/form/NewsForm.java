package com.epam.testapp.presentation.form;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.epam.testapp.entity.News;
import com.epam.testapp.util.DateUtil;

public class NewsForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private News news;
	private String dateString;
	private String localeName;
	private List<News> newsList;
	private Integer[] selectedNewsId;

	public NewsForm() {
		news = new News();
	}

	public NewsForm( List<News> newsList, News news, Locale locale,
			String dateString, String localeName, Integer[] selectedNewsId ) {
		this.newsList = newsList;
		this.news = news;
		this.dateString = dateString;
		this.localeName = localeName;
		this.selectedNewsId = selectedNewsId;
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

	public Integer[] getSelectedNewsId() {
		return selectedNewsId;
	}

	public void setSelectedNewsId( Integer[] selectedNewsId ) {
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

		if ( "".equals( news.getTitle() ) ) {
			actionErrors
					.add( "title", new ActionMessage( "msg.title_required" ) );
		}else if( news.getTitle().length() > 100 ){
			actionErrors
			.add( "title", new ActionMessage( "msg.title_length" ) );
		}

		if ( "".equals( news.getBrief() ) ) {
			actionErrors
					.add( "brief", new ActionMessage( "msg.brief_required" ) );
		}else if( news.getBrief().length() > 500 ){
			actionErrors
			.add( "brief", new ActionMessage( "msg.brief_length" ) );
		}

		if ( "".equals( news.getContent() ) ) {
			actionErrors.add( "content", new ActionMessage(
					"msg.content_required" ) );
		}else if( news.getContent().length() > 2000 ){
			actionErrors
			.add( "content", new ActionMessage( "msg.content_length" ) );
		}

		if ( localeName != null && !localeName.equals( "" ) ) {
			if ( !DateUtil.isDateCorrect( dateString, localeName ) ) {
				actionErrors
						.add( "date", new ActionMessage( "msg.date_format" ) );
			}
		}

		return actionErrors;
	}

}
