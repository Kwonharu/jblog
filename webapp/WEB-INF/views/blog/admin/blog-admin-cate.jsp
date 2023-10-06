<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js" type="text/javascript"></script>

</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      			<c:forEach items="${requestScope.blogmap.cateList}" var="cateVo">
							<tr id="catelist${cateVo.cateNo}">
								<td>${cateVo.cateRowNum}</td>
								<td>${cateVo.cateName}</td>
								<td>${cateVo.postCount}</td>
								<td>${cateVo.description}</td>
						    	<td class='text-center'>
						    		<img class="btnCateDel" data-no="${cateVo.cateNo}" data-post="${cateVo.postCount}" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
						    	</td>
							</tr>
						</c:forEach>
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="input-cateName" type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="input-cateDesc" type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
</body>


<script type="text/javascript">

//카테고리 추가 버튼 클릭 시
$("#btnAddCate").on("click", ()=>{
	
	let cateName = $("#input-cateName").val();
	let cateDesc = $("#input-cateDesc").val();
	
	if(cateName == null || cateName == ""){
		alert("카테고리 이름을 입력하세요.");
		return false;
	};
	
	if(cateDesc == null){
		cateDesc = " ";
	}
	
	let categoryVo = {
		cateName: cateName,
		description: cateDesc
	};
	
	
	$.ajax({
		url : "${pageContext.request.contextPath}/${blogmap.blogVo.id}/admin/categoryInsert",
		type : "post",
		//보낼 때
		/* contentType : "application/json", */
		data : categoryVo,

		//받을 때
		dataType : "json",
		success : function(categoryVo){
			console.log(categoryVo);
			render(categoryVo);
			$("#input-cateName").val("");
			$("#input-cateDesc").val("");
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
	
});



//삭제 이미지 클릭 시
$("#cateList").on("click", ".btnCateDel", function(){
	console.log("삭제 버튼 클릭");
	
	let no = parseInt($(this).data("no"));
	
	let post = parseInt($(this).data("post"));
	
	if(post > 0){
		alert("포스트가 있는 카테고리는 삭제 불가.");
		return false;
	}
	console.log(no, post);
	
	$.ajax({
		url : "${pageContext.request.contextPath}/${blogmap.blogVo.id}/admin/categoryDelete",
		type : "post",
		//보낼 때
		/* contentType : "application/json", */
		data : {cateNo: no},

		//받을 때
		dataType : "json",
		success : function(count){
			console.log(count);

			if(count != -1){
				$("#catelist"+no).remove();
			}else{
				alert("오류");
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});



function render(cateVo){
	
	let str = "";
	str += "<tr id='catelist"+cateVo.cateNo+"'>";
	str += "	<td>"+cateVo.cateRowNum+"</td>";
	str += "	<td>"+cateVo.cateName+"</td>";
	str += "	<td>"+cateVo.postCount+"</td>";
	str += "	<td>"+cateVo.description+"</td>";
	str += "	<td class='text-center'>";
	str += "		<img class='btnCateDel' data-no='"+cateVo.cateNo+"' data-no='"+cateVo.postCount+"' src='${pageContext.request.contextPath}/assets/images/delete.jpg'>";
	str += "	</td>";
	str += "</tr>";
	
	$("#cateList").prepend(str);
};


</script>


</html>




