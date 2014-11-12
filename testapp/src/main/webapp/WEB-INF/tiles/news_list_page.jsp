<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${ not empty newsForm.newsList }">
		<html:form action="/Delete">
			<c:forEach var="news" items="${ newsForm.newsList }">
				<article class="news">
					<header class="news_header">
						<section class="news_title">
							<label><bean:message key="label.news_title" /></label>
							<c:out value="${ news.title }" />

						</section>
						<section class="news_date">
							<label><bean:message key="label.news_date" /></label>
							<%-- <c:out value="${ news.dateString }"></c:out> --%>
						</section>
					</header>
					<setion class="news_body">
					<section class="news_brief">
						<label><bean:message key="label.brief" /></label>
						<c:out value="${ news.brief }" />
					</section>
					</setion>

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
