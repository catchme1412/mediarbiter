<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@attribute name="time" rtexprvalue="true" type="java.lang.Long"%>
<fmt:formatNumber minIntegerDigits="2" pattern="##" value="${time div 3600}" />
:
<fmt:formatNumber minIntegerDigits="2" pattern="##" value="${time%60}" />
:
<fmt:formatNumber minIntegerDigits="2" pattern="##" value="${time%60}" />
