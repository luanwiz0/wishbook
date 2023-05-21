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
<h2>��ٱ���</h2>
<c:forEach var="item" items="${itemList}">
	${item.cartId}, ${item.bookId}, ${item.title}, ${item.quantity}, ${item.price}, ${item.imageUrl} <br>
</c:forEach>
<br>
<form action="/cart/add" method="post">
	<input type="hidden" name="bookId" value="6" />
	<input type="hidden" name="quantity" value="5" />
	<input type="hidden" name="price" value="100" />
	<input type="submit" value="å �߰�" />
</form>
<br>
<a href="/order/payment"> ���� ������ </a>

</body>
</html>