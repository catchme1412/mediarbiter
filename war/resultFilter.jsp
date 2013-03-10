
<div style="width: 300px">
	${videoFeed.categories}
	<c:forEach var="category" value="${videoFeed.categories}">
   Item <c:out value="${category.labelString}"/><p>
</c:forEach>
</div>
