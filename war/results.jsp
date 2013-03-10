
<%@taglib prefix="t" tagdir="/WEB-INF/tags/template/"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/format/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<t:mainlayout>
	<jsp:attribute name="west">
		<jsp:include page="resultFilter.jsp" />
	</jsp:attribute>
	<jsp:body>
	<h1>
		<c:out value="${videoFeed.title}" />
	</h1>
	<table>
		<tr>
			<td>Filter</td>
		</tr>
		<c:forEach var="entry" items="${videoFeed.entries}">
			<tr>
				<td>
				<div style="float: left;">
					<img src="${entry.youtubeVideoEntry.mediaGroup.thumbnails[0].url}" />
				</div>
				<div>
					<a href="http://www.youtube.com/watch?v=${entry.youtubeVideoId}">${entry.title}</a>
				</div>
				<div>${entry.description}</div>
				<div>${entry.rating}</div>
				<div>${entry.categoryList}</div>
				<f:duration time="${entry.duration}" />
				</td>
			</tr>
		</c:forEach>
	</table>
	</jsp:body>
</t:mainlayout>