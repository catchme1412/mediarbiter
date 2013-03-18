<%@tag description="Main Page template based on Base Layout" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/template/"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/html/"%>
<%@taglib prefix="render" tagdir="/WEB-INF/tags/render/"%>
<%@taglib prefix="c" uri="/WEB-INF/tlds/c.tld"%>
<%@attribute name="northWest" fragment="true"%>
<%@attribute name="north" fragment="true"%>
<%@attribute name="northEast" fragment="true"%>
<%@attribute name="west" fragment="true"%>
<%@attribute name="main" fragment="true"%>
<%@attribute name="east" fragment="true"%>
<%@attribute name="southWest" fragment="true"%>
<%@attribute name="south" fragment="true"%>
<%@attribute name="southEast" fragment="true"%>
<%@attribute name="pageSpecificLinks" fragment="true"%>
<%@attribute name="pageSpecificScripts" fragment="true"%>
<%@attribute name="pageSpecificMetaTags" fragment="true"%>
<%@attribute name="pageTitle" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="keywords" rtexprvalue="true" type="java.lang.String"%>
<html>
<head>
<title>${pageTitle}</title>
<link rel="stylesheet" type="text/css" href="/global/main.css" />
<link rel="stylesheet" type="text/css" href="/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/global/bootstrap.overrides.css" />
<link rel="stylesheet" type="text/css" href="/global/bootstrap-responsive.min.css" />
<link rel="stylesheet" type="text/css" href="/global/styles/layout.css" />
<jsp:invoke fragment="pageSpecificMetaTags" />
<jsp:invoke fragment="pageSpecificLinks" />
<script type="text/javascript">
	<jsp:invoke fragment="pageSpecificScripts" />
</script>
</head>
<body>
	<t:baselayout>
		<jsp:attribute name="northWest">
		<div id="row">
		<div id="northWest">
			<c:choose>
				<c:when test="${not empty northWest}">
					<jsp:invoke fragment="northWest" />
				</c:when>
				<c:otherwise>
					<jsp:include page="/WEB-INF/layout/northWest.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		
		</jsp:attribute>
		<jsp:attribute name="north">
		<div id="north">
			<c:choose>
				<c:when test="${not empty north}">
					<jsp:invoke fragment="north" />
				</c:when>
				<c:otherwise>
					<jsp:include page="/WEB-INF/layout/north.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		</jsp:attribute>
		<jsp:attribute name="northEast">
		<div id="northEast">
			<c:choose>
				<c:when test="${not empty northEast}">
					<jsp:invoke fragment="northEast" />
				</c:when>
				<c:otherwise>
					<jsp:include page="/WEB-INF/layout/northEast.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		</div>
		</jsp:attribute>

		<jsp:attribute name="west">
			<div id="row">
			<div id="west">
				<c:choose>
					<c:when test="${not empty west}">
						<jsp:invoke fragment="west" />
					</c:when>
					<c:otherwise>
						<jsp:include page="/WEB-INF/layout/west.jsp" />
					</c:otherwise>
				</c:choose>
			</div>
		</jsp:attribute>
		<jsp:attribute name="east">
		<div id="east">
			<c:choose>
				<c:when test="${not empty east}">
					<jsp:invoke fragment="east" />
				</c:when>
				<c:otherwise>
					<jsp:include page="/WEB-INF/layout/east.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		</div>
		</jsp:attribute>

		<jsp:attribute name="southWest">
		<div id="row">
		<div id="southWest">
			<c:choose>
				<c:when test="${not empty southWest}">
					<jsp:invoke fragment="southWest" />
				</c:when>
				<c:otherwise>
					<jsp:include page="/WEB-INF/layout/southWest.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		
		</jsp:attribute>
		<jsp:attribute name="south">
		<div id="south">
			<c:choose>
				<c:when test="${not empty south}">
					<jsp:invoke fragment="south" />
				</c:when>
				<c:otherwise>
					<jsp:include page="/WEB-INF/layout/south.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		</jsp:attribute>
		<jsp:attribute name="southEast">
		<div id="southEast">
			<c:choose>
				<c:when test="${not empty southEast}">
					<jsp:invoke fragment="southEast" />
				</c:when>
				<c:otherwise>
					<jsp:include page="/WEB-INF/layout/southEast.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		</div>
		</jsp:attribute>
		<jsp:body>
			<div id="main">
			<section>
	        	<jsp:doBody />
			</section>
			</div>
   		</jsp:body>

	</t:baselayout>
</body>
</html>