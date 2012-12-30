<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:out value="${videoFeed.title}" />
<table>
<c:forEach var="entry" items="${videoFeed.entries}">
<tr>
	<td><img src="${entry.youtubeVideoEntry.mediaGroup.thumbnails[0].url}" /></td>
	<td><a href="http://www.youtube.com/watch?v=${entry.youtubeVideoId}">${entry.title}</a>
		<p>Year:${entry.year} ${entry.categoryList}</p>
		<p>${entry.description}</p>
	</td>
</tr>
</c:forEach>
</table>