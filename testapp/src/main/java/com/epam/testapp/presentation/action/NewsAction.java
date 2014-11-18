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
import com.epam.testapp.service.AttributeName;
import com.epam.testapp.service.INewsService;
import com.epam.testapp.service.ProjectPages;
import com.epam.testapp.service.ServiceTestappException;
import com.epam.testapp.util.DateUtil;

public class NewsAction extends MappingDispatchAction {

	private INewsService newsService;

	public ActionForward list( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;

		List<News> newsList = null;
		try {
			newsList = newsService.getNewsList();
		} catch ( ServiceTestappException e ) {
			
		}
		newsForm.setNewsList( newsList );

		String pageName = ProjectPages.NEWS_LIST_PAGE;
		newsService.setCurrentPage( request, pageName );
		newsService.setPreviousPage( request, pageName );

		return mapping.findForward( pageName );
	}

	public ActionForward add( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;
		News news = new News();
		Date date = new Date( System.currentTimeMillis() );
		news.setDate( date );
		newsForm.setNews( news );

		String pageName = ProjectPages.ADD_NEWS_PAGE;
		newsService.setCurrentPage( request, pageName );

		return mapping.findForward( pageName );
	}

	public ActionForward save( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;
		News newNews = newsForm.getNews();

		try {
			newNews = newsService.saveNews( newNews, newsForm.getDateString(),
					request );
		} catch ( ServiceTestappException e ) {
			
		}

		return mapping.findForward( ProjectPages.MAIN_PAGE );
	}

	public ActionForward delete( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;

		String[] stringNewsId = newsForm.getSelectedNewsId();
		List<News> newsList = newsForm.getNewsList();

		try {
			newsService.removeNews( stringNewsId, newsList );
		} catch ( ServiceTestappException e ) {
			
		}

		return mapping.findForward( ProjectPages.MAIN_PAGE );
	}

	public ActionForward view( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		Integer newsId = Integer.parseInt( request.getParameter( AttributeName.NEWS_ID ) );
		NewsForm newsForm = (NewsForm) form;
		News news = newsService
				.getSelectedNews( newsForm.getNewsList(), newsId );
		newsForm.setNews( news );

		String pageName = ProjectPages.VIEW_NEWS_PAGE;
		newsService.setCurrentPage( request, pageName );
		newsService.setPreviousPage( request, pageName );

		return mapping.findForward( pageName );
	}

	public ActionForward edit( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		Integer newsId = Integer.parseInt( request.getParameter( AttributeName.NEWS_ID ) );
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
		return mapping.findForward( newsService.getPreviousPage( request ) );
	}

	public ActionForward changeLocale( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {
		
		NewsForm newsForm = (NewsForm) form;
		Locale locale = newsService.changeLocale( newsForm.getLocaleName(),
				request );
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
