<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>주문 목록</h2>
<c:forEach var="item" items="${orderList}">
	${item.bookId}, ${item.title}, ${item.quantity}, ${item.price}, ${item.imageUrl} <br>
</c:forEach>
</body>
</html>