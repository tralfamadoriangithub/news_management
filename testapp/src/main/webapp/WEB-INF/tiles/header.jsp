<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<h1><bean:message key="label.title"/></h1>
<section id="language_selection">
<html:link action="ChangeLocale"><bean:message key="label.english"/></html:link>
<a href="#"><bean:message key="label.russian"/></a>

</section>