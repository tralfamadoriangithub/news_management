<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<ul id="aside_list">
	<li><html:link action="/NewsList">
			<bean:message key="label.news_list" />
		</html:link></li>
	<li><html:link action="/AddNews">
			<bean:message key="label.add_news" />
		</html:link></li>
</ul> 