<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
		
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
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
				<div id="gallery">
					<h2>갤러리</h2>
					<input id="btnImgPop" class="btn btn-primary" type="button" value="이미지등록">
						<ul class = "view">
								
						</ul>
				</div>
			</div>
		</div>
		<%-- 
		<div id="content">
			<div id="c_box">
				<div id="gallery">
					<h2>갤러리</h2>
					
					<input id="btnImgPop" class="btn btn-primary" type="button" value="이미지등록">
					<ul>
						<c:forEach items="${galleryList }" var="list">
						<li>
							<div class="view" >
								<img src="${pageContext.request.contextPath }/upload/${list.saveName}">
								<input id="no" type="hidden" name="no" value="${list.no }">
							</div>
						</li>
						</c:forEach>
					</ul> 
				</div><!-- /gallery -->
			</div><!-- /c_box -->
		</div><!-- /content -->
		 --%>	
		<!-- footer -->	
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
	
	
	
	
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="delPop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				<form method="post" action="${pageContext.request.contextPath }/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="formgroup">
							<label>코멘트작성</label><br>
							<input type="hidden" name="userNo" id="userNo" value = "${authUser.no }"><br>
							<input type="text" name="comments" id="comments"><br>	
						</div>
						<div class="formgroup">
							<label>이미지선택</label><br>	
							<input type="file" name="file" value="" id="file"> <br>	
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button type="submit" class="btn btn-primary" id="btnImgAdd">등록</button>
					</div>
				</form>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	
	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewPop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup">
						<img id="detailImg" src="" width="300px">
					</div>
					
					<div class="formgroup">
						<label>코멘트</label><br>
						 <input type="hidden" id="detailNo" value="">
						 <div id="detailComment"></div>
					</div>
					
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
</body>

<script type="text/javascript">

$("document").ready(function(){
	$.ajax({
		url: "${pageContext.request.contextPath}/gallery/print",
		type: "post",
		dataType: "json",
		success : function(GalleryList) {
			/*성공시 처리해야될 코드 작성*/
			console.log(GalleryList);

			for (var i = 0; i < GalleryList.length; i++) {
				showGallery(GalleryList[i]);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	})
});
/* 이미지등록 팝업(모달)창 띄우기*/ 
$("#btnImgPop").on("click", function() {
    $("#delPop").modal();
    
});

/* 이미지보기 팝업(모달)창 띄우기*/ 	
$(".view").on("click", "#viewFile" ,function() {
	console.log("aaa");
	$("#viewPop").modal();
	var $this = $(this)
	
	gallerydto={
			 no : $(this).find("#no").val()
	 };
	
	$.ajax({
		url : "${pageContext.request.contextPath}/gallery/detail", // json형식으로 넘기기
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(gallerydto),

		dataType : "json",
		success : function(galleryDto) {
			$("#detailImg").attr('src', '${pageContext.request.contextPath }/upload/'+galleryDto.saveName);
			$("#detailComment").text(galleryDto.comments);
			$("#detailNo").val(galleryDto.no)
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
 });
 
 $("#btnDel").on("click", function(){
	 console.log("pressed delete button");
	 gallerydto={
			 no : $("#detailNo").val()
	 };
	 
	 /*
	 $.ajax({
			url : "${pageContext.request.contextPath}/gallery/delete", // json형식으로 넘기기
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(gallerydto),

			dataType : "json",
			success : function(result) {
				$("[no ="+result+"]").remove();
				if(result == 0)
					alert("삭제하는데 실패하였습니다.");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	 */
  });

 function showGallery(GalleryDto){
	 var str="";
	 str += "<li id='viewFile'>"
	 str +=	"		<img src='${pageContext.request.contextPath }/upload/"+ GalleryDto.saveName + "'>";
	 str +=	"		<input id='no' type='hidden' name='no' value="+ GalleryDto.no +">";
	 str += "</li>"
	 
	$(".view").prepend(str);

 }

</script>







</html>