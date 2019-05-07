<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
	<title>Mysite</title>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="content">
			<div id="c_box">
				<div id="board">
					<h2>답글 등록</h2>

					<form class="board-form" method="get" action="${pageContext.request.contextPath}/hierarchy/reply">
						<input type ="hidden" name = "groupNo" value="${param.groupNo }">
						<input type ="hidden" name = "orderNo" value="${param.orderNo }">
						<input type ="hidden" name = "parentDepth" value="${param.parentDepth }">
						<input type ="hidden" name = "userNo" value="${sessionScope.authUser.no}">
						<input type ="hidden" name = "userName" value="${sessionScope.authUser.name}">
						<table class="tbl-ex">
							<tr>
								<th colspan="2">답글쓰기</th>
							</tr>
							<tr>
								<td class="label">제목</td>
								<td><input type="text" name="title" value=""></td>
							</tr>
							<tr>
								<td class="label">내용</td>
								<td>
									<textarea id="content" name="content"></textarea>
								</td>
							</tr>
						</table>
						<div class="bottom">
							<a href="${pageContext.request.contextPath }/hierarchy/list">취소</a>
							<input type="submit" value="등록">
						</div>
					</form>

				</div><!-- /board -->
			</div><!-- /c_box -->
		</div><!-- /content -->



		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div><!-- /container -->
</body>
</html>
