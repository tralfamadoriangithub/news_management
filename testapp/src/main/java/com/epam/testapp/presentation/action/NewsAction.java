package com.epam.testapp.presentation.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.epam.testapp.entity.News;
import com.epam.testapp.presentation.form.NewsForm;
import com.epam.testapp.service.INewsService;
import com.epam.testapp.service.ProjectPages;
import com.epam.testapp.util.DateUtil;

public class NewsAction extends MappingDispatchAction {

	private INewsService newsService;

	public ActionForward list( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;

		if ( newsForm.getNewsList().isEmpty() ) {
			List<News> newsList = newsService.getNewsList();
			newsForm.getNewsList().addAll( newsList );
		}
		String pageName = ProjectPages.NEWS_LIST_PAGE;

		newsService.setCurrentPage( request, pageName );

		return mapping.findForward( pageName );
	}

	public ActionForward add( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;
		
		News news = new News();
		Date date = new Date( System.currentTimeMillis() );
		news.setDate( date );
		newsForm.setNews( news );
		String dateString = DateUtil.getStringFromDate( date, newsForm.getLocale() );
		newsForm.setDateString( dateString );
		String pageName = ProjectPages.ADD_NEWS_PAGE;

		newsService.setCurrentPage( request, pageName );

		return mapping.findForward( pageName );
	}

	public ActionForward save( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;
		News newNews = newsForm.getNews();

		newNews = newsService.saveNews( newNews, newsForm.getDateString(),
				newsForm.getLocale() );
		newsForm.getNewsList().add( newNews );

		return mapping.findForward( ProjectPages.MAIN_PAGE );
	}

	public ActionForward delete( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;

		String[] stringNewsId = newsForm.getSelectedNewsId();
		List<News> newsList = newsForm.getNewsList();

		List<News> updatedNewsList = newsService.removeNews( stringNewsId,
				newsList );

		if ( updatedNewsList != null ) {
			newsForm.setNewsList( updatedNewsList );
		}

		return mapping.findForward( ProjectPages.MAIN_PAGE );
	}

	public ActionForward view( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		Integer newsId = Integer.parseInt( request.getParameter( "newsId" ) );
		NewsForm newsForm = (NewsForm) form;
		News news = newsService
				.getSelectedNews( newsForm.getNewsList(), newsId );
		newsForm.setNews( news );

		String pageName = ProjectPages.VIEW_NEWS_PAGE;
		newsService.setCurrentPage( request, pageName );

		return mapping.findForward( pageName );
	}

	public ActionForward edit( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		Integer newsId = Integer.parseInt( request.getParameter( "newsId" ) );
		NewsForm newsForm = (NewsForm) form;
		News news = newsService
				.getSelectedNews( newsForm.getNewsList(), newsId );
		newsForm.setNews( news );

		String pageName = ProjectPages.ADD_NEWS_PAGE;
		newsService.setCurrentPage( request, pageName );

		return mapping.findForward( pageName );
	}

	public ActionForward cancel( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		return null;
	}

	public ActionForward changeLocale( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;
		Locale locale = newsService.changeLocale( newsForm.getLocaleName(),
				request );
		newsForm.setLocale( locale );
		setLocale( request, locale );

		return mapping.findForward( newsService.getCurrentPage( request ) );
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService( INewsService newsService ) {

		this.newsService = newsService;
	}

}
