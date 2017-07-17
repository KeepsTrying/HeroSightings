<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DB Trial</title>
    </head>
    <body>
        <h1>Database Trial</h1>




        <c:forEach var="address" items="${addresses}">


            <p><c:out value="${address.addressId}"/></p>
            <p><c:out value="${address.streetAddress}"/></p>
            <p><c:out value="${address.city}"/></p>
            <p><c:out value="${address.state}"/></p>
            <p><c:out value="${address.zipCode}"/></p>
            <p><c:out value="${address.latitude}"/></p>
            <p><c:out value="${address.longitude}"/></p>


        </c:forEach>





    </body>
</html>
