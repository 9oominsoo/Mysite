<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="/mysiteLayout/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="/mysiteLayout/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>Mysite</title>
</head>
<body>
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	<!-- header -->

	<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
	<!-- navigation -->

		<div id="content">
			<div id="c_box">
				<div id="guestbook" class="deleteform">
					<h2>방명록삭제</h2>

					<form class="form-box" method="" action="">
						<div class="form-group">
							<label class="block-label">비밀번호</label>
							<input type="password" name="password" value="">
						</div>

						<input type="submit" value="확인">
					</form>
					<a href="">방명록 리스트</a>

				</div><!-- /guestbook -->
			</div><!-- /c_box -->
		</div><!-- /content -->



		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- footer -->
	</div><!-- /container -->
</body>
</html>
