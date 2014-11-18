<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<c:choose>
	<c:when test="${ not empty sessionScope.language }">
		<c:set var="language" value="${ sessionScope.language }"/>
		<c:out value="Locale from session"/>
	</c:when>
	<c:otherwise>
		<c:set var="language" value="${ pageContext.request.locale }" scope="session"/>
		<c:out value="${ language.language }"/>
		<c:set var="${ newsForm.localeName }" value="${ language.language }"/>
		<c:out value="Locale from request"/>
		<c:out value="${ newsForm.localeName }"/>
	</c:otherwise>
</c:choose>

<fmt:setLocale value="${ language }" />
<fmt:setBundle
	basename="com.epam.testapp.properties.ApplicationResources"
	var="bundle" />

<c:out value="${ newsForm.localeName }"></c:out>
<c:choose>
	<c:when test="${ not empty newsForm.newsList }">
	
		<html:form action="/Delete" onsubmit="return confirmDelete();">
			<c:forEach var="news" items="${ newsForm.newsList }">
				<article class="news">
				
					<header class="news_header">
						<section class="news_title">
							<label><bean:message key="label.news_title" /></label>
							<c:out value="${ news.title }" />
						</section>
						<section class="news_date">
							<%-- <label> <bean:message key="label.news_date" /></label> --%>
							<fmt:message bundle="${ bundle }" key="format.date"
								var="format" />
							<fmt:formatDate value="${ news.date }" pattern="${ format }" />
						</section>
					</header>
					
					<section class="news_brief">
						<label><bean:message key="label.brief" /></label>
						<c:out value="${ news.brief }" />
					</section>

					<footer class="news_footer">
						<a href="/testapp/View.do?newsId=<c:out value='${ news.id }' />">
							<bean:message key="label.view" />
						</a> <a href="/testapp/Edit.do?newsId=<c:out value='${ news.id }' />"><bean:message
								key="label.edit" /></a>
						<html:multibox property="selectedNewsId">
							<c:out value="${ news.id }" />
						</html:multibox>	
					</footer>
					
				</article>
			</c:forEach>
			<html:submit>
				<bean:message key="button.delete" />
			</html:submit>
		</html:form>
	</c:when>
	<c:otherwise>
		<h1>Ther is no news</h1>
	</c:otherwise>
</c:choose>
