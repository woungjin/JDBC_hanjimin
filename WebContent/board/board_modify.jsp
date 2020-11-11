<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<div align="center" class="div_center">
	<h3>게시판 글 수정 페이지</h3>
	<hr>
	
	<!-- form안에서 화면에 보이지는 않지만 , 반드시 넘겨주어야 할 값이 있을때 hidden을 사용한다 -->
	<!-- └> disabled는 bno의 값을 넘기지 못해서 hidden으로 bno를 넘겨준다	 -->
	<form action="update.board" method="post">
		
		<table border="1" width="500">
			<input type="hidden" name="bno" value="${vo.bno }">
			<input type="hidden" name="writer" value="${vo.writer }">
			<tr>
				<td>글 번호</td>
				<td><input type=="text" name="bno" value="${vo.bno }" disabled/></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer"value="${vo.writer }" size="10" required readonly/></td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td>
					<input type="text" name="title" value="${vo.title }" requried>
				</td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>
					<textarea rows="10" style="width: 95%;" name="content">
						${vo.content}
					</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정 하기" >&nbsp;&nbsp;
					<input type="button" value="목록" onclick="location.href='list.board'">        
				</td>
			</tr>
			
		</table>
	</form>
	
</div>
<%@ include file="../include/footer.jsp" %>


