package com.epam.testapp.presentation.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.epam.testapp.entity.News;
import com.epam.testapp.presentation.form.NewsForm;
import com.epam.testapp.service.INewsService;
import com.epam.testapp.service.ProjectPages;
import com.epam.testapp.service.ServiceTestappException;

public class NewsAction extends MappingDispatchAction {

	private final Logger logger = Logger
			.getLogger( com.epam.testapp.presentation.action.NewsAction.class );
	private INewsService newsService;

	/**
	 * Method forward to news_list_page.
	 * 
	 * @param mapping
	 *            ActionMapping object for action
	 * @param form
	 *            ActionForm object for action
	 * @param request
	 *            HttpServletRequest request
	 * @param response
	 *            HttpServletResponse response
	 * @return ActionForward to news_list_page or error page if something wrong
	 */
	public ActionForward list( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;

		List<News> newsList = null;
		try {
			newsList = newsService.getNewsList();
		} catch ( ServiceTestappException e ) {
			logger.error( e.getMessage() );
			return mapping.findForward( ProjectPages.ERROR_PAGE );
		}
		newsForm.setNewsList( newsList );

		String pageName = ProjectPages.NEWS_LIST_PAGE;
		newsService.setCurrentPage( request, pageName );
		newsService.setPreviousPage( request, pageName );

		return mapping.findForward( pageName );
	}

	/**
	 * Method forward to add_edit_news_page.
	 * 
	 * @param mapping
	 *            ActionMapping object for action
	 * @param form
	 *            ActionForm object for action
	 * @param request
	 *            HttpServletRequest request
	 * @param response
	 *            HttpServletResponse response
	 * @return ActionForward to add_edit_news_page or error page if something
	 *         wrong
	 */
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

	/**
	 * Method forward to add_edit_news_page.
	 * 
	 * @param mapping
	 *            ActionMapping object for action
	 * @param form
	 *            ActionForm object for action
	 * @param request
	 *            HttpServletRequest request
	 * @param response
	 *            HttpServletResponse response
	 * @return ActionForward to add_edit_news_page or error page if something
	 *         wrong
	 */
	public ActionForward edit( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;

		News news = null;
		try {
			news = newsService.getSelectedNews( newsForm.getNews().getId() );
		} catch ( ServiceTestappException e ) {
			logger.error( e.getMessage() );
			return mapping.findForward( ProjectPages.ERROR_PAGE );
		}
		newsForm.setNews( news );

		String pageName = ProjectPages.ADD_NEWS_PAGE;
		newsService.setCurrentPage( request, pageName );

		return mapping.findForward( pageName );
	}

	/**
	 * Method save news and forward to view_news_page.
	 * 
	 * @param mapping
	 *            ActionMapping object for action
	 * @param form
	 *            ActionForm object for action
	 * @param request
	 *            HttpServletRequest request
	 * @param response
	 *            HttpServletResponse response
	 * @return ActionForward to view_news_page or error page if something wrong
	 */
	public ActionForward save( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;
		News newNews = newsForm.getNews();

		try {
			newNews = newsService.saveNews( newNews, newsForm.getDateString(),
					request );
		} catch ( ServiceTestappException e ) {
			logger.error( e.getMessage() );
			return mapping.findForward( ProjectPages.ERROR_PAGE );
		}
		newsForm.setNews( newNews );
		return mapping.findForward( ProjectPages.VIEW_NEWS_PAGE );
	}

	/**
	 * Method delete news and forward to news_list_page.
	 * 
	 * @param mapping
	 *            ActionMapping object for action
	 * @param form
	 *            ActionForm object for action
	 * @param request
	 *            HttpServletRequest request
	 * @param response
	 *            HttpServletResponse response
	 * @return ActionForward to view_news_page or error page if something wrong
	 */
	public ActionForward delete( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;

		try {
			newsService.removeNews( newsForm.getSelectedNewsId() );
		} catch ( ServiceTestappException e ) {
			logger.error( e.getMessage() );
			return mapping.findForward( ProjectPages.ERROR_PAGE );
		}

		return mapping.findForward( ProjectPages.MAIN_PAGE );
	}

	/**
	 * Method forward to view_news_page.
	 * 
	 * @param mapping
	 *            ActionMapping object for action
	 * @param form
	 *            ActionForm object for action
	 * @param request
	 *            HttpServletRequest request
	 * @param response
	 *            HttpServletResponse response
	 * @return ActionForward to view_news_page or error page if something wrong
	 */
	public ActionForward view( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {

		NewsForm newsForm = (NewsForm) form;
		News news = null;
		try {
			news = newsService.getSelectedNews( newsForm.getNews().getId() );
		} catch ( ServiceTestappException e ) {
			logger.error( e.getMessage() );
			return mapping.findForward( ProjectPages.ERROR_PAGE );
		}

		newsForm.setNews( news );

		String pageName = ProjectPages.VIEW_NEWS_PAGE;
		newsService.setCurrentPage( request, pageName );
		newsService.setPreviousPage( request, pageName );

		return mapping.findForward( pageName );
	}

	/**
	 * Method cancels current operation and forward to previous page.
	 * 
	 * @param mapping
	 *            ActionMapping object for action
	 * @param form
	 *            ActionForm object for action
	 * @param request
	 *            HttpServletRequest request
	 * @param response
	 *            HttpServletResponse response
	 * @return ActionForward to previous page or error page if something wrong
	 */
	public ActionForward cancel( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response ) {
		NewsForm newsForm = (NewsForm) form;
		if ( newsForm.getNews().getId() != 0 ) {
			try {
				News news = newsService.getSelectedNews( newsForm.getNews()
						.getId() );
				newsForm.setNews( news );
			} catch ( ServiceTestappException e ) {
				logger.error( e.getMessage() );
				return mapping.findForward( ProjectPages.ERROR_PAGE );
			}
		}
		String pageName = newsService.getPreviousPage( request );
		newsService.setCurrentPage( request, pageName );
		return mapping.findForward( pageName );
	}

	/**
	 * Method changes locale and forward to current page.
	 * 
	 * @param mapping
	 *            ActionMapping object for action
	 * @param form
	 *            ActionForm object for action
	 * @param request
	 *            HttpServletRequest request
	 * @param response
	 *            HttpServletResponse response
	 * @return ActionForward to current or error page if something wrong
	 */
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
