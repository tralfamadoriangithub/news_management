<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html:form action="/Save">

	<label><bean:message key="label.news_title" /></label>
	<html:text property="news.title">
		<c:out value="${ newsForm.news.title }" default=""/>
	</html:text>
	<br>

	<label><bean:message key="label.news_date" /></label>
	<html:text property="dateString">

	</html:text>
	<br>

	<label><bean:message key="label.brief" /></label>
	<html:textarea property="news.brief">
		<c:out value="${ newsForm.news.brief }" default="" />
	</html:textarea>
	<br>

	<label><bean:message key="label.content" /></label>
	<html:textarea property="news.content">
		<c:out value="${ newsForm.news.content }" default="" />
	</html:textarea>
	<br>

	<html:submit>
		<bean:message key="button.save" />
	</html:submit>
	<html:cancel>
		<bean:message key="button.cancel" />
	</html:cancel>

</html:form>