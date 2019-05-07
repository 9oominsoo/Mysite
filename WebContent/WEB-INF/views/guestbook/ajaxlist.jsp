<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- bootstrap -->
<link
	href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">


<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>

<!-- bootstrap -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>

<title>Mysite</title>
</head>

<!-- 삭제팝업(모달)창 -->
<div class="modal fade" id="del-pop">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">방명록삭제</h4>
			</div>
			<div class="modal-body">
				<label>비밀번호</label> <br> <input type="hidden" name="modalNo"
					value="" id="modalNo"> <br> <input type="password"
					name="modalPassword" value="" id="modalPassword"><br>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

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

					<form id="addform">
						<!-- action="" method="" -->
						<table>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" id="name"></td>
								<td>비밀번호</td>
								<td><input type="password" name="pass" id="password"></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="contentText"
										cols="75" rows="8"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><button id="btn_sub"
										type="button">확인</button></td>
							</tr>
						</table>
					</form>

					<div id="gbList"></div>

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

<script type="text/javascript">
	/*  
	 기본적으로 화면을 로딩할때 반드시 같이 그려주어야 하는 것들은 ready 이벤트 메소드를 사용해서 브라우저가 넘어온 html파일을 객체화해서 뿌려줄 때 깥이 그려줄 수 있도록 해준다.
	 하지만 버튼을 누른다거나 필수적인 요소가 아닌 경우에는 on 이벤트 메소드등을 사용해서, 그 버튼이 눌릴땨만 처리될 수 있로독, 즉, 화면을 그리고 난 후에 처리해준다.
	 이렇게 자바스크립트로 화면을 controll하는 방법은 두가지로 존재한다(화면을 그리기 전, 화면을 그리고 난 후)
	 */
	// ready : 페이지를 그리기 직전, 뛰우기 직전에 준비하는 시간동안 처리
	$("document").ready(function() {
		//ajax라는 javascript의 객체
		$.ajax({
			url : "${pageContext.request.contextPath }/api/gb/list",
			// 화면을 변경시키지 않기 때문에 url도 바꾸지 않아서 앵간하면 post방식으로 보낸다 .
			// get은 url을 변경시키기 때문 
			type : "post",
			/*contentType : "application/json",*/
			// 객체(ajax) 안의 객체($email) 
			//data : {email: $email},
			//json 형식으로 받겠다.
			dataType : "json",
			//guestBookList 안에 알아서 넘어오는 데이터를 담아준다.
			success : function(guestBookList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestBookList);

				for (var i = 0; i < guestBookList.length; i++) {
					render(guestBookList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});

	//나중에 그려진거라 btnDel을 감지할 수 없다.
	//따라서 부모가 먼저 받아서 자식인 btnDel에게 상속해 주어야 한다.
	//$(".btnDel").on("click", function(){})으로는 클릭을 잡을 수 없다. >> 나중에 추가적으로 그려진거라
	$("#gbList").on("click", ".btnDel", function() {
		console.log("delete button clicked");
		var $this = $(this);
		var no = $this.data("no");
		console.log(no);
		$("#del-pop").modal();
		$("#modalNo").val(no);
	});

	//스크롤이 화면 제일 아래에 왔을때
	$(window).scroll(
			function() {
				if ($(window).scrollTop() == $(document).height()
						- $(window).height()) {
					fetchList();
				}
			});


	$("#btn_sub").on("click",function() {
		console.log("pressed submit button");
		/* 
		var name = $("#name").val();
		var password = $("#password").val();
		var content = $("#contentText").val();
		 */
		 guestbookdto={
				 name : $("#name").val(),
				 password : $("#password").val(),
				 content : $("#contentText").val()
		 }

		$.ajax({
			//url : "${pageContext.request.contextPath }/api/gb/insertList?name="+ name+"&password="+ password+"&content="+ content,
			url : "${pageContext.request.contextPath}/api/gb/insertList", // json형식으로 넘기기
			// 화면을 변경시키지 않기 때문에 url도 바꾸지 않아서 앵간하면 post방식으로 보낸다 .
			// get은 url을 변경시키기 때문 
			type : "post",
			contentType : "application/json",
			// 객체(ajax) 안의 객체($email) 
			// json은 객체로 넘겨주어야 한다. 변수로 넘길 수 없음
			data : JSON.stringify(guestbookdto),
			//json 형식으로 받겠다.
			dataType : "json",
			//guestBookList 안에 알아서 넘어오는 데이터를 담아준다.
			success : function(guestbookdto) {
		    /*성공시 처리해야될 코드 작성*/
			
			/* 
		   console.log(guestbookdto);
		   render(guestbookdto, "down");
	     	 */
				render(guestbookdto, "down");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});

	$("#btn_del").on("click", function() {
		console.log("pressed confirm button"); 
		var no = $("#modalNo").val();
		var password = $("#modalPassword").val();
		console.log(no);
		console.log(password);

		$.ajax({
			url : "${pageContext.request.contextPath }/api/gb/deleteList?no="+ no + "&password=" + password,
		    // 화면을 변경시키지 않기 때문에 url도 바꾸지 않아서 앵간하면 post방식으로 보낸다 .
			// get은 url을 변경시키기 때문 
			type : "post",
			/*contentType : "application/json",*/
	     	// 객체(ajax) 안의 객체($email) 
			//data : {email: $email},
			//json 형식으로 받겠다.
			dataType : "json",
	    	//guestBookList 안에 알아서 넘어오는 데이터를 담아준다.
			success : function(result) {
			/*성공시 처리해야될 코드 작성*/
			$("#" + result).remove();
										$("#del-pop").modal("hide");
										$("#modalNo").val(" ");
										if (result == 0)
											alert("비밀번호가 일치하지 않습니다.");
									},
									error : function(XHR, status, error) {
										console.error(status + " : " + error);
									}
								});
						$("#modalPassword").val("");
					});

	//updown은 내가 지정한 변수
	//함수를 익명함수로 만들어 var render 변수안에 넣어줄 수도 있다.
	//자바스크립트는 객체개념으로 주소값을 반환한다. 따라서 GuestBookDto라는 자료형을 명시해주지 않아도 된다.
	//ex) GuestBookDto guestBookDto
	//주소값으로 알아서 찾앗가서 가져온다.
	function render(guestBookDto, updown) {
		var str = "";
		str += "<div class=dataTable id="+guestBookDto.no+">"
		str += "<table width=510 border=1";
		str += "	<tr>";
		str += "		<td>" + guestBookDto.no + "</td>";
		str += "		<td>" + guestBookDto.name + "</td>";
		str += "		<td>" + guestBookDto.regDate + "</td>";
		// data-NO 처럼 대문자는 사용 불가!!!!
		// data- : 값을 쉽게 가지고 올 수 있게 도와주는 역활 , 변수명이 아님
		str += "		<td><input class='btnDel' type='button' value='삭제' data-no="+guestBookDto.no+"></td>";
		str += "	</tr>";
		str += "	<tr>";
		str += "		<td colspan=4>" + guestBookDto.content + "</td>"
		str += "	</tr>";
		str += "</table>";
		str += "<br/>";
		str += "</div>"

		if (updown == "up") {
			$("#gbList").prepend(str);
		} else if (updown == "down") {
			$("#gbList").append(str);
		} else {
			console.log("updown 오류");
		}

	}
</script>

</html>
