<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html:form action="/SaveNews">

	<label><bean:message key="label.news_title" /></label>
	<html:text property="news.title" /><br>
	
	<label><bean:message key="label.news_date"/></label>
	<html:text property="news.date" /><br>
	
	<label><bean:message key="label.brief"/></label>
	<html:textarea property="news.brief"></html:textarea><br>
	
	<label><bean:message key="label.content"/></label>
	<html:textarea property="news.content"></html:textarea><br>
	
	<html:submit><bean:message key="button.save"/></html:submit>
	<html:cancel><bean:message key="button.cancel"/></html:cancel>
	
</html:form>