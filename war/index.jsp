
<%@taglib prefix="t" tagdir="/WEB-INF/tags/template/"%>

<t:mainlayout>
	<label>Language:</label>
	<select name="language">
		<option selected="selected" value="search-alias=aps">All Language</option>
		<option value="language=malayalam">Malayalam</option>
		<option value="language=hindi">Hindi</option>
		<option value="language=english">English</option>
	</select>

	<form action="/youtube" method="get">
		<label>Search:</label>
		<input type="text" id="q" />
		<input type="submit">
	</form>
</t:mainlayout>