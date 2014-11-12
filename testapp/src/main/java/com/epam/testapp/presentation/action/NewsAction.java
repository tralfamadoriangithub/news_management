package com.epam.testapp.presentation.action;

import java.util.ArrayList;
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

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NewsForm newsForm = (NewsForm) form;

		if (newsForm.getNewsList().isEmpty()) {
			List<News> newsList = newsService.getNewsList();
			newsForm.getNewsList().addAll(newsList);
		}
		return mapping.findForward(ProjectPages.NEWS_LIST_PAGE);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NewsForm newsForm = (NewsForm) form;

		newsForm.setNews(new News());
		String dateString = DateUtil.getStringFromDate(
				new Date(System.currentTimeMillis()), newsForm.getLocale());
		newsForm.setDateString(dateString);

		return mapping.findForward(ProjectPages.ADD_NEWS_PAGE);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NewsForm newsForm = (NewsForm) form;

		News newNews = newsForm.getNews();
		Date date = DateUtil.getDateFromString(newsForm.getDateString(),
				newsForm.getLocale());
		newNews.setDate(date);

		newNews = newsService.saveNews(newNews);
		newsForm.getNewsList().add(newNews);

		ActionForward forward = mapping.findForward(ProjectPages.MAIN_PAGE);
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NewsForm newsForm = (NewsForm) form;

		String[] stringNewsId = newsForm.getSelectedNewsId();

		if (stringNewsId != null) {
			List<Integer> intNewsId = new ArrayList<>(stringNewsId.length);
			for (int i = 0; i < stringNewsId.length; i++) {
				intNewsId.add(Integer.parseInt(stringNewsId[i]));
			}

			List<News> newsList = newsForm.getNewsList();
			List<News> updatedNewsList = newsService.removeNews(intNewsId,
					newsList);
			newsForm.setNewsList(updatedNewsList);
		}
		return mapping.findForward(ProjectPages.MAIN_PAGE);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Integer newsId = Integer.parseInt(request.getParameter("newsId"));
		NewsForm newsForm = (NewsForm) form;
		News news = newsService.getSelectedNews(newsForm.getNewsList(), newsId);
		newsForm.setNews(news);

		return mapping.findForward(ProjectPages.VIEW_NEWS_PAGE);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Integer newsId = Integer.parseInt(request.getParameter("newsId"));
		NewsForm newsForm = (NewsForm) form;
		News news = newsService.getSelectedNews(newsForm.getNewsList(), newsId);
		newsForm.setNews(news);

		return mapping.findForward(ProjectPages.ADD_NEWS_PAGE);
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return null;
	}
	
	public ActionForward changeLocale(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println(getLocale(request));
		Locale locale = new Locale("ru", "RU");
		System.out.println(locale);
		setLocale(request, locale);
		return mapping.findForward(ProjectPages.MAIN_PAGE);
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
}
