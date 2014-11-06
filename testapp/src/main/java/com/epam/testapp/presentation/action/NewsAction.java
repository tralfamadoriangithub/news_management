package com.epam.testapp.presentation.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.epam.testapp.database.NewsDao;
import com.epam.testapp.entity.News;
import com.epam.testapp.presentation.form.NewsForm;

public class NewsAction extends MappingDispatchAction {

	private NewsDao dao;

	public ActionForward newsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NewsForm newsForm = (NewsForm) form;

		return mapping.findForward("news_list");
	}

	public ActionForward addNews(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NewsForm newsForm = (NewsForm) form;
		News news = new News();
		news.setDate(new Date(System.currentTimeMillis()));
		newsForm.setNews(news);
		return mapping.findForward("add_news");
	}

	public ActionForward saveNews(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NewsForm newsForm = (NewsForm) form;
		
		return mapping.findForward("news_list");
	}

	public NewsDao getDao() {
		return dao;
	}

	public void setDao(NewsDao dao) {
		this.dao = dao;
	}
}
