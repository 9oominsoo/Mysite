<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
<title>Mysite</title>
 <script language="javascript">
 	 function showPopup() { window.open("/mysite/reply.jsp", "a", "width=400, height=300, left=100, top=50"); }
 </script>
</head>
<body>
	<div id="container">
		<!-- header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<!-- navigation -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="content">
			<div id="c_box">
				<div id="board">
					<h2>계층형 게시판-리스트</h2>

					<form action="" method="post">
						<input type="text" id="kwd" name="kwd" value=""> <input
							type="submit" value="찾기">
					</form>

					<table class="tbl-ex">
						<tr>
							<!-- <th>번호</th> -->
							<th>제목</th>
							<th>등록일</th>
							<th>조회수</th>
							<th>답글달기</th>
							<th>삭제</th>
							<th>&nbsp;</th>
						</tr>
						<c:forEach items="${list }" var="dto">
							<tr>
								<!--<td>${dto.no }</td> -->
								<td style="text-align:left">
									<c:forEach begin="1" end="${dto.depth }">
										&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
									<c:if test="${dto.depth > 0}">
										<img src="${pageContext.request.contextPath }/assets/images/reply.png">
									</c:if>
									<c:choose>
										<c:when test="${dto.status == 'del'}">
											<del>${dto.title }</del>
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath}/hierarchy/read">${dto.title }</a>
										</c:otherwise>
									</c:choose>
								</td>
								<td>${dto.regDate }</td>
								<td>${dto.hit }</td>
								<td><a href="${pageContext.request.contextPath }/hierarchy/replyform?groupNo=${dto.groupNo}
										&orderNo=${dto.orderNo }&parentDepth=${dto.depth}">[답글 달기]</a></td>
								<td>
									<c:choose>
										<c:when test="${dto.status == 'del'}">
											삭제 된 답글
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath }/hierarchy/deleteReply?number=${dto.no}">[답글 삭제]</a>
										</c:otherwise>
									</c:choose>
								</td>
								<td>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
					<div class="bottom">
						<c:if test="${sessionScope.authUser != null}">
							<a href="${pageContext.request.contextPath}/hierarchy/writeform"
								id="new-book">글쓰기</a>
						</c:if>
					</div>
				</div>
				<!-- /board -->
			</div>
			<!-- /c_box -->
		</div>
		<!-- /content -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
	<!-- /container -->

</body>
	<!-- Button  -->
	<script type="text/javascript">
  		$(".btn1").on("click", function(){
   			$(".btn1").hide();
  		})
	</script>

</html>
