<%@ tag pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="name" required="true" %>
<%@ attribute name="value" required="true" %>

<a class="btn btn-default" href="${value}">
    ${name}
</a>
