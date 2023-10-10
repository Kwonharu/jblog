<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
			
			<!-- 일반 페이지 제목 기본값 -->
			<h1><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}">${requestScope.blogmap.blogVo.blogTitle}</a></h1>
			
			<ul class="clearfix">
			
				<!-- 로그인 전 메뉴 -->
				<c:if test="${sessionScope.authUser == null}">
					<li><a class="btn_s" href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
				</c:if>
				
				<!-- 로그인 후 메뉴 -->
				<c:if test="${sessionScope.authUser != null}">
					<!-- 자신의 블로그일때만 관리 메뉴가 보인다. -->
					<c:if test="${sessionScope.authUser.id == requestScope.blogmap.userVo.id}">
						<li><a class="btn_s" href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic">내블로그 관리</a></li>
					</c:if>
					
					<li><a class="btn_s" href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				</c:if>
				
			</ul>
		</div>
		<!-- //header -->
		