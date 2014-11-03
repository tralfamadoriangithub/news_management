<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insert template="/WEB-INF/layout/base_layout.jsp">
	<tiles:put name="header" value="/WEB-INF/tiles/header.jsp" />
	<tiles:put name="aside" value="/WEB-INF/tiles/aside.jsp" />
	<tiles:put name="main_content" value="/WEB-INF/news_list.jsp" />
	<tiles:put name="footer" value="/WEB-INF/footer.jsp" />
</tiles:insert>