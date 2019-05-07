<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>
	<title>Mysite</title>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import><!-- header -->

		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import><!-- navigation -->
		
		
		<div id="content">
			<div id="c_box">
				<div id="user">
					<h2>회원가입</h2>
					
					<form class="form-box"  method="POST" action="${pageContext.request.contextPath }/user/join" >
						
						<div class="form-group">
							<label class="block-label" for="name">이름</label>
							<input id="name" type="text" name="name"  value="" >
						</div>
						
						<div class="form-group">
							<label class="block-label" for="email">이메일</label>
							<input id="email" type="text" name="email"  value="" >
							<input id="btnCheck" type="button" value="id 중복체크">
							<p id="emailResult"><p>
						</div>
						
						<div class="form-group">
							<label class="block-label" for="password">패스워드</label>
							<input name="password" type="password" value="" >
						</div>
						
						<fieldset>
							<legend>성별</legend>
							<label for="rf">여</label> <input id="rf" type="radio" name="gender" value="female" checked="checked">
							<label for="rm">남</label> <input id="rm" type="radio" name="gender" value="male">
						</fieldset>
						
						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label for="agree-prov">서비스 약관에 동의합니다.</label>
						</fieldset>
						
						<input type="submit" value="가입하기">
						
					</form>
				</div><!-- /user -->
			</div><!-- /c_box -->
		</div><!-- /content -->
			
			
	<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	<!-- footer -->
	</div><!-- /container -->
</body>

<script type="text/javascript">
	$("#btnCheck").on("click", function(){
		console.log("button clicked");
		var $email = $("#email").val();
		console.log($email);
		
		//ajax라는 javascript의 객체
		$.ajax({
			url : "${pageContext.request.contextPath }/user/emailcheck",	
			// 화면을 변경시키지 않기 때문에 url도 바꾸지 않아서 앵간하면 post방식으로 보낸다 .
			// get은 url을 변경시키기 때문 
			type : "post",
			/*contentType : "application/json",*/
			// 객체(ajax) 안의 객체($email) 
			data : {email: $email},
			
			//json 형식으로 받겠다.
			dataType : "json",
			//result 안에 담아서
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				console.log(result);
				
				if(result == true){
					$("#emailResult").text("사용할 수 있는 이메일입니다.");
					$("#emailResult").css("color", "green");
				}else{
					$("#emailResult").text("사용중인 이메일입니다.");
					$("#emailResult").css("color", "red");
				}
				
				/*
				if(result == true)
					alert("사용할 수 있는 이메일 입니다");
				else 
					alert("사용할 수 없는 이메일입니다.");
				*/
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
</script>

</html>