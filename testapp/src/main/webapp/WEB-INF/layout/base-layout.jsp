<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<title><tiles:getAsString name="title"/></title>
</head>
<body>

	<section id="main_wrapper">
		<header id="header">
			<tiles:insert attribute="header" />
		</header>

		<section id="main_content">
			<aside id="aside">
				<tiles:insert attribute="aside" />
			</aside>
			<section id="page_content">
				<tiles:insert attribute="main_content" />
			</section>
		</section>

		<footer id="footer">
			<tiles:insert attribute="footer" />
		</footer>
	</section>
	
</body>
</html>