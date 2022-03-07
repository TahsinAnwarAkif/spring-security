<%@ tag pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="path" required="true" %>
<%@ attribute name="type" %>
<%@ attribute name="message" required="true" %>
<%@ attribute name="placeholder" required="false" %>

<div class="form-group" style="padding-bottom: 50px">
    <form:label path="${path}" cssClass="col-sm-3"><fmt:message key="${message}"></form:label>

    <div class="col-sm-9">
        <form:input type="${type}" cssClass="form-control" path="${path}"
                    placeholder='${placeholder}'/>
    </div>
</div>
