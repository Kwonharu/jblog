<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<!-- 기본이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/jblog_file/${requestScope.blogmap.blogVo.logoFile}">
					
					<!-- 사용자업로드 이미지 -->
					<%-- <img id="proImg" src=""> --%>
					
					<div id="nick">${requestScope.blogmap.userVo.userName}(${requestScope.blogmap.userVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<li><a href="$}">카테고리5</a></li>
						<li><a href="$}">카테고리4</a></li>
						<li><a href="$}">카테고리3</a></li>
						<li><a href="$}">카테고리2</a></li>
						<li><a href="$}">카테고리1</a></li>
						<li><a href="$}">미분류</a></li>
						
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
				<div id="postBox" class="clearfix">
						<div id="postTitle" class="text-left"><strong>08.페이징</strong></div>
						<div id="postDate" class="text-left"><strong>2020/07/23</strong></div>
						<div id="postNick">${requestScope.blogmap.userVo.userName}(${requestScope.blogmap.userVo.id})님</div>
				</div>
				<!-- //postBox -->
			
				<div id="post" style="white-space:pre">
					청명한 달빛 그득한 죽림 어딘가
					오색 빛 나비 안내한 오솔길 따라
					나는 가만히 침소에 든 그댈 보아요
					꿈결에 아득한 꿈결에
					이른 서리 아침에 잠에서 깨어
					문득 목 놓아 울고 말았죠
					사무치게 보고 싶은 그대 그리며
					
					계속 모른척할 건가요
					결국 난 못 참고 토라져
					소녀는 원래 복잡 한 거죠
					수줍어도 대담할 땐 꺄~~~?!
					
					부디 이 마음 알아채지 말아 주세요
					진짜 진짜 정말 정말 보고팠어요
					밝은 달 아래서 정화수 떠 놓고 빌었다죠
					그댈 감쪽같이 속여요 전부
					여우처럼 숨길 거예요
					욕심 많고 거짓뿐인 소녀의 꿈은
					다 그런 법이죠
					모른 체해줘요
				</div>
				<!-- //post -->
				
				<!-- 글이 없는 경우 -->
				<!-- 
				<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
							<div id="postDate" class="text-left"><strong></strong></div>
							<div id="postNick"></div>
				</div>
			    
				<div id="post" >
				</div>
				-->
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						
						<tr>
							<td class="text-left"><a href="">08.페이징</a></td>
							<td class="text-right">2020/07/23</td>
						</tr>
						<tr>
							<td class="text-left"><a href="">07.첨부파일_MultipartResolver</a></td>
							<td class="text-right">2020/07/23</td>
						</tr>
						<tr>
							<td class="text-left"><a href="">06.jquery_ajax</a></td>
							<td class="text-right">2020/07/23</td>
						</tr>
						<tr>
							<td class="text-left"><a href="">05.javaScript</a></td>
							<td class="text-right">2020/07/23</td>
						</tr>
						<tr>
							<td class="text-left"><a href="">04.spring_어플리케이션_아키텍쳐</a></td>
							<td class="text-right">2020/07/23</td>
						</tr>
						
						
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>
</html>