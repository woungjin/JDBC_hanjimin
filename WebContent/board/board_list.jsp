<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
<
title>Insert title here </title>.btn {
	border: 0;
	border-radius: 0; /*윤곽 0*/
	padding: 5px 10px;
	margin: 20px 0px;
}
</style>

<%@ include file="../include/header.jsp"%>
<div class="container">
	<h3>게시판 목록들</h3>
	
	<!-- onchange : 상자를 클릭했을때 
		 change(this) : 각 상자의 값 
		 value의 값을 넘겨 받아야한다
	-->
	
	<select onchange="change(this)">
		<option value="10" ${pageVO.amount == 10 ? 'selected' : '' }>10개씩 보기</option>
		<option value="20" ${pageVO.amount == 20 ? 'selected' : '' }>20개씩 보기</option>
		<option value="50" ${pageVO.amount == 50 ? 'selected' : '' }>50개씩 보기</option>
		<option value="100"${pageVO.amount == 100 ? 'selected' : '' }>100개씩 보기</option>
	</select>
	

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</thead>
		<c:forEach var="vo" items="${list }">

			<tbody>
				<tr>
					<td>${vo.bno }</td>
					<td>${vo.writer }</td>
					<td><a href="content.board?bno=${vo.bno }">${vo.title } </a></td>
					<td><fmt:formatDate value="${vo.regdate }"
							pattern="yyyy년MM월dd일" /></td>
					<td>${vo.hit }</td>
				</tr>
			</tbody>
		</c:forEach>
		<tbody>
			<tr>
				<td colspan="5" align="center">
					<ul class="pagination pagination-sm">
					
						<c:if test="${pageVO.prev }">
						<li><a href="list.board?pageNum=${pageVO.startPage -1 }&amount=${pageVO.amount}">이전</a></li>
						</c:if>
						
						<!--  1. 페이지 번호 처리 ,  li 엑티브 처리 -->
					<c:forEach var="num" begin="${pageVO.startPage }" end="${pageVO.endPage }">
						<li class="${num eq pageVO.pageNum ? 'active' : '' }"><a href="list.board?pageNum=${num}&amount=${pageVO.amount}">${num }</a></li>
					</c:forEach>
					
						<!-- 3. 다음 버튼 활성화 (이전이랑 차이 없음) -->
						<c:if test="${pageVO.next }">
						<li><a href="list.board?pageNum=${pageVO.endPage +1 }&amount=${pageVO.amount}">다음</a></li>
						</c:if>
					</ul> <input type="button" value="글 작성"
					class="btn btn-default pull-right"
					onclick="location.href='write.board'">

				</td>
			</tr>
		</tbody>

	</table>
</div>
	<script>
		function change(a) {
			//console.log(a)
			//console.log(a.value)
			location.href="list.board?pageNum=1&amount=" + a.value;
		}
		
	
	</script>


<%@ include file="../include/footer.jsp"%>







