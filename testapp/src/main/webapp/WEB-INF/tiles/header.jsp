<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<section id="header_title">
	<h1>
		<bean:message key="label.title" />
	</h1>
</section>
<section id="language_selection">
	<html:link action="ChangeLocale?localeName=en">
		<bean:message key="label.english" />
	</html:link>&nbsp;&nbsp;
	<html:link action="ChangeLocale?localeName=ru">
		<bean:message key="label.russian" />
	</html:link>

</section>