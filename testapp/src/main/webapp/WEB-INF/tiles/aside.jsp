<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<h3 id="aside_list_title">News</h3>
<ul id="aside_list">
	<li><html:link action="/List">
			<bean:message key="label.news_list" />
		</html:link></li>
	<li><html:link action="/Add">
			<bean:message key="label.add_news" />
		</html:link></li>
</ul> 