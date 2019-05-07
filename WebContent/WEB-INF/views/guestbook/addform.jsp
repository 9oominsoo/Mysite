<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<title>Mysite</title>
</head>
<body>
<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	<!-- header -->

	<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
	<!-- navigation -->

	<div id="content">
		<div id="c_box">
			<div id="guestbook">
				<h2>방명록</h2>

				<form id="addform" action="" method="">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" cols="75" rows="8"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>

				<c:forEach items="${list}" var="dto">
				<table width=510 border=1>
					<tr>
						<td>${dto.no}</td>
						<td>${dto.name}</td>
						<td>${dto.regDate}</td>
						<td><a href="${pageContext.request.contextPath}/deleteform">삭제</a></td>
					</tr>
					<tr>
						<td colspan=4>${dto.content}</td>
					</tr>
				</table>
				<br>
				</c:forEach>
			</div>
			<!-- /guestbook -->
		</div>
		<!-- /c_box -->
	</div>
	<!-- /content -->


	<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	<!-- footer -->
	</div>
	<!-- /container -->
</body>
</html>
