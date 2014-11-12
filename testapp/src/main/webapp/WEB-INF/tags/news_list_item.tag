<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="com.epam.testapp.entity.News" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ attribute name="news" type="com.epam.testapp.entity.News" required="true"%>


<article class="news">
	<header class="news_header">
		<section class="news_title">${ news.title }</section>
		<section class="news_date"></section>
	</header>
	<section class="news_brief">${ news.brief }</section>
	<footer class="news_footer">${ news.content }</footer>
</article>
