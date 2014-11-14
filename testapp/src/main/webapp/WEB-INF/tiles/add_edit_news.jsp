<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
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

<font color="red"> <html:errors />
</font>
<br>

<html:form action="/Save">

	<label><bean:message key="label.news_title" /></label>
	<html:text property="news.title">
	</html:text>
	<br>

	<fmt:message bundle="${ bundle }" key="format.date" var="format" />
	<fmt:formatDate var="formattedDate" value="${ newsForm.news.date }"
		pattern="${ format }" />
	<label><bean:message key="label.news_date" /></label>
	<input type="text" name="dateString"
		value="<c:out value='${ formattedDate }'/>" />
	<%-- <html:text property="dateString">
	</html:text> --%>
	<br>

	<label><bean:message key="label.brief" /></label>
	<html:textarea property="news.brief">
	</html:textarea>
	<br>

	<label><bean:message key="label.content" /></label>
	<html:textarea property="news.content">
	</html:textarea>
	<br>

	<html:submit>
		<bean:message key="button.save" />
	</html:submit>
	<html:cancel>
		<bean:message key="button.cancel" />
	</html:cancel>

</html:form>