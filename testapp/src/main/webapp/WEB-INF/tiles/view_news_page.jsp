<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%-- <c:choose>
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
	var="bundle" /> --%>

<section class="headLink">
	<html:link action="/List">
		<bean:message key="label.news" />
	</html:link>
	&gt;&gt;
	<bean:message key="label.news_view" />
</section>

<section id="viewNewsSection">

	<bean:define id="newsItem" name="newsForm" property="news" />
	<p>
		<label><bean:message key="label.news_title" /></label>
		<div class="content">
			<bean:write name="newsItem" property="title"/>
		</div>
	<p>
		<label><bean:message key="label.news_date" /></label>
		<div class="content">
			<%-- <fmt:message bundle="${ bundle }" key="format.date" var="format" /> --%>
			<%-- <fmt:formatDate value="${ newsForm.news.date }" pattern="${ format }" /> --%>
			<bean:write name="newsItem" property="date" formatKey="format.date"/>
		</div>
	<p>
		<label><bean:message key="label.brief" /></label>
		<div class="content">
			<bean:write name="newsItem" property="brief"/>
		</div>
	<p>
		<label><bean:message key="label.content" /></label>
		<div class="content">
			<bean:write name="newsItem" property="content"/>
		</div>

		<section id="viewNewsButtons">
			
			<%-- <html:form action="/Delete">
				<html:hidden property="id" name="newsItem" />
				<html:submit>
					<bean:message key="button.delete" />
				</html:submit>
			</html:form> --%>
			
			<form action="/testapp/Delete.do" method="post"
				onsubmit="return confirmDialog();">
				<input type="hidden" name="selectedNewsId"
					value="<c:out value='${ newsForm.news.id }' />"> <input
					type="submit" value="<bean:message key="button.delete" />">
			</form>
			
			<html:form action="/Edit">
				<html:hidden property="id" name="newsItem" />
				<html:submit>
					<bean:message key="button.edit" />
				</html:submit>
			</html:form>
			

			<%-- <form action="/testapp/Edit.do" method="post">
				<input type="hidden" name="newsId"
					value="<c:out value='${ newsForm.news.id }' />"> <input
					type="submit" value="<bean:message key="button.edit" />">
			</form> --%>
		</section>
</section>