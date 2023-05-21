<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="item" items="${orderInfo.itemList}">
	${item.cartId}, ${item.bookId}, ${item.title}, ${item.quantity}, ${item.price}, ${item.imageUrl} <br>
</c:forEach>
<form:form modelAttribute="orderInfo" action="/order/add" method="post">
	전화번호: <form:input type="text" path="userTel"/>
	<br>
	주소: <form:input type="text" path="address" />
	<br>
	카드사: <form:select path="cardType">
		<form:options items="${orderInfo.cardTypes}" />
	</form:select> 
	<br>
	카드번호: <form:input type="text" path="cardNum" />
	<input type="submit" value="확인">
</form:form>

</body>
</html>