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
		<c:out value="${title}" />
	</h1>
	<table>
		<tr>
			<td>Filter</td>
		</tr>
		<c:forEach var="entry" items="${entries}">
			<tr>
				<td>
				<div class="thumbnail">
					<img src="${entry.thumbnail}" />
				</div>
				<div class="duration"><f:duration time="${entry.duration}" /></div>
				<div class="rating">${entry.rating}</div>
				<div>
					<a href="http://www.youtube.com/watch?v=${entry.youtubeVideoId}">${entry.title}</a>
				</div>
				<div>${entry.description}</div>
				<div>${entry.categoryList}</div>
				</td>
				<td>
					<div>${entry.wikipediaUrl}</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	</jsp:body>
</t:mainlayout>