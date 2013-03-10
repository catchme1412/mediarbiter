<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<b>Year wise:</b>
<div style="width: 300px">
<c:forEach var="i" begin="1950" end="2013" step="1">
   <a href="#"><c:out value="${i}"/></a> 
</c:forEach>
</div>
</br>
</br>
<b>Alphabetical:</b>
<div style="width: 300px">
<c:forEach var="i" items="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Z" >
   <a href="#"><c:out value="${i}"/></a> 
</c:forEach>
</div>

</br>
</br>
