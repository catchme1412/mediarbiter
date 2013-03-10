<%@tag description="Base page template" pageEncoding="UTF-8"%>
<%@attribute name="northWest" fragment="true"%>
<%@attribute name="north" fragment="true"%>
<%@attribute name="northEast" fragment="true"%>
<%@attribute name="west" fragment="true"%>
<%@attribute name="body" fragment="true"%>
<%@attribute name="east" fragment="true"%>
<%@attribute name="southWest" fragment="true"%>
<%@attribute name="south" fragment="true"%>
<%@attribute name="southEast" fragment="true"%>
<div id="container">
	<jsp:invoke fragment="northWest" />
	<jsp:invoke fragment="north" />
	<jsp:invoke fragment="northEast" />
	<jsp:invoke fragment="west" />
	<jsp:doBody />
	<jsp:invoke fragment="east" />
	<jsp:invoke fragment="southWest" />
	<jsp:invoke fragment="south" />
	<jsp:invoke fragment="southEast" />
</div>


