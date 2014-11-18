<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<c:choose>
	<c:when test="${ not empty sessionScope.language }">
		<c:set var="language" value="${ sessionScope.language }" />
	</c:when>
	<c:otherwise>
		<c:set var="language" value="${ pageContext.request.locale }" />
	</c:otherwise>
</c:choose>

<fmt:setLocale value="${ language }" />
<fmt:setBundle
	basename="com.epam.testapp.properties.ApplicationResources"
	var="bundle" />

<article class="view_news">

	<header class="news_header">
		<section class="news_title">
			<label><bean:message key="label.news_title" /></label>
			<c:out value="${ newsForm.news.title }" />
		</section>

		<section class="news_date">
			<label><bean:message key="label.news_date" /></label>
			<fmt:message bundle="${ bundle }" key="format.date" var="format" />
			<fmt:formatDate value="${ newsForm.news.date }"
				pattern="${ format }" />
		</section>

	</header>

	<setion class="news_body">
	<section class="news_brief">
		<label><bean:message key="label.brief" /></label>
		<c:out value="${ newsForm.news.brief }" />
	</section>
	<section class="news_content">
		<label><bean:message key="label.content" /></label>
		<c:out value="${ newsForm.news.content }" />
	</section>
	</setion>

	<footer class="news_footer">

		<%-- <bean:define id="news" name="newsForm" property="news"/> --%>

		<form action="/testapp/Delete.do" method="post" onsubmit="return confirmDialog();">
		
			<input type="hidden" name="selectedNewsId"
				value="<c:out value='${ newsForm.news.id }' />">
				 <input type="submit" value="<bean:message key="button.delete" />">
		</form>

		<form action="/testapp/Edit.do" method="post">
			<input type="hidden" name="newsId"
				value="<c:out value='${ newsForm.news.id }' />"> <input
				type="submit" value="<bean:message key="button.edit" />">
		</form>

	</footer>
</article>