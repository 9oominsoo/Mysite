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
					<h2>게시판-리스트</h2>

					<form action="" method="post">
						<input type="text" id="kwd" name="kwd" value=""> <input
							type="submit" value="찾기">
					</form>

					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
							<th>조회수</th>
							<th>&nbsp;</th>
						</tr>
						<c:forEach items="${list }" var="dto">
							<tr>
								<td>${dto.no}</td>
								<td><a href="${pageContext.request.contextPath}/board/read?no=${dto.no}">${dto.title}</a></td>
								<td>${dto.userName}</td>
								<td>${dto.regDate}</td>
								<td>${dto.hit }</td>
								<td>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
					<div class="count">
						<li>게시글 수 : ${count}</li>
					</div>
					<div class="pager">
						<ul class="pagination">
							<c:if test="${currentPage - 3 >= 1 }">
								<a href="${pageContext.request.contextPath }/board/list?pageNum=${currentPage -5}">◀◀</a>
							</c:if>
							<c:if test="${currentPage > 1 }">
								<a href="${pageContext.request.contextPath }/board/list?pageNum=${currentPage -1}">이전</a>
							</c:if>
							
							<c:choose> 
   								<c:when test="${currentPage <= 2 }">
   									<c:forEach begin="1" end="5" var="index">
										<a href="${pageContext.request.contextPath }/board/list?pageNum=${index }">${index }</a>
									</c:forEach>
   								</c:when>
    							<c:when test="${currentPage >= finalPage - 2 }">
    								<c:forEach begin="${finalPage - 4}" end="${finalPage }" var="index">
										<a href="${pageContext.request.contextPath }/board/list?pageNum=${index }">${index }</a>
									</c:forEach>	
    							</c:when>
    							<c:otherwise> 
    								<c:forEach begin="${currentPage - 2}" end="${currentPage + 2}" var="index">
										<a href="${pageContext.request.contextPath }/board/list?pageNum=${index }">${index }</a>
									</c:forEach>
    							</c:otherwise>
							</c:choose>
							
							<c:if test="${currentPage < finalPage }">
								<a href="${pageContext.request.contextPath }/board/list?pageNum=${currentPage +1}">다음</a>
							</c:if>
							<c:if test="${currentPage + 3 <= finalPage }">
								<a href="${pageContext.request.contextPath }/board/list?pageNum=${currentPage +5}">▶▶</a>
							</c:if>
						</ul>
					</div>
					<div class="bottom">
						<c:if test="${sessionScope.authUser != null}">
							<a href="${pageContext.request.contextPath}/board/writeform"
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
</html>
