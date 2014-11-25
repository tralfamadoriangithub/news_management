<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value='/js/messages.jsp'/>" type="text/javascript"></script>
<script src="<c:url value='/js/script.js'/>" type="text/javascript"></script>

<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />

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

<c:set var="pageTitle" scope="page">	
	<tiles:getAsString name="title" />
</c:set>

<title><fmt:message key="${ pageTitle }" bundle="${ bundle }"/></title>
</head>

<body>

	<section id="main_wrapper">
		<section id="inner_wrapper">
			<header id="header">
				<tiles:insert attribute="header" />
			</header>
			<section id="main_content_wrapper">
				<section id="main_content">
					<aside id="aside">
						<tiles:insert attribute="aside" />
					</aside>
					<section id="page_content">
						<tiles:insert attribute="page_content" />
					</section>
				</section>
			</section>
			<p style="clear: both;">
			<footer id="footer">
				<tiles:insert attribute="footer" />
			</footer>
		</section>
	</section>
</body>
</html>