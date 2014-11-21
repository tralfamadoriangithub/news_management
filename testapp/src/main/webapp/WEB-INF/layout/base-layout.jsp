<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
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

<title><tiles:getAsString name="title" /></title>
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