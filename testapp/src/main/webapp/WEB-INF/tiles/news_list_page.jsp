<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<c:choose>
	<c:when test="${ not empty sessionScope.language }">
		<c:set var="language" value="${ sessionScope.language }" />
	</c:when>
	<c:otherwise>
		<c:set var="language" value="${ pageContext.request.locale }"
			scope="session" />
		<c:set target="${ newsForm }" property="localeName"
			value="${ language.language }" />
	</c:otherwise>
</c:choose>

<section class="headLink">
	<html:link action="/List">
		<bean:message key="label.news" />
	</html:link>
	&gt;&gt;
	<bean:message key="label.news_list" />
</section>

<logic:notEmpty name="newsForm" property="newsList">
	<html:form action="/Delete" onsubmit="return confirmDelete();">
		<logic:iterate id="news" name="newsForm" property="newsList">
			<article class="news">

				<header class="news_header">
					<section class="news_title">
						<bean:write name="news" property="title" />
					</section>
					<section class="news_date">
						<bean:write name="news" property="date" formatKey="format.date" />
					</section>
				</header>

				<section class="news_brief">
					<bean:write name="news" property="brief" />
				</section>

				<footer class="news_footer">

					<html:link action="/View" paramName="news" paramProperty="id"
						paramId="news.id">
						<bean:message key="label.view" />
					</html:link>
					&nbsp;

					<html:link action="/Edit" paramName="news" paramProperty="id"
						paramId="news.id">
						<bean:message key="label.edit" />
					</html:link>
					&nbsp;

					<html:multibox property="selectedNewsId">
						<bean:write name="news" property="id" />
					</html:multibox>
				</footer>

			</article>
		</logic:iterate>
		<html:submit>
			<bean:message key="button.delete" />
		</html:submit>
	</html:form>
</logic:notEmpty>

<logic:empty name="newsForm" property="newsList">
	<h1>
		<bean:message key="msg.no_news" />
	</h1>
</logic:empty>

<%-- <c:choose>
	<c:when test="${ not empty newsForm.newsList }">
		<html:form action="/Delete" onsubmit="return confirmDelete();">
			<c:forEach var="news" items="${ newsForm.newsList }">

				<article class="news">

					<header class="news_header">
						<section class="news_title">
							<c:out value="${ news.title }" />
						</section>
						<section class="news_date">
							<fmt:message bundle="${ bundle }" key="format.date" var="format" />
							<fmt:formatDate value="${ news.date }" pattern="${ format }" />
						</section>
					</header>

					<section class="news_brief">
						<c:out value="${ news.brief }" />
					</section>

					<footer class="news_footer">
						<a href="/testapp/View.do?newsId=<c:out value='${ news.id }' />">
							<bean:message key="label.view" />
						</a> &nbsp; <a
							href="/testapp/Edit.do?newsId=<c:out value='${ news.id }' />"><bean:message
								key="label.edit" /></a>&nbsp;
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
		<h1>
			<bean:message key="msg.no_news" />
		</h1>
	</c:otherwise>
</c:choose> --%>
