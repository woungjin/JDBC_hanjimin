<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<section>
	<div align="center">
		<h2>회원정보 수정</h2>
		<hr style="color: red" />
		<form name="regForm" action="updateForm.user" method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" value="${sessionScope.user.id }" readonly></td>
					<!-- disabled : id 자체의 값을 넘길수가 없다
				     readonly : 수정 불가로 많이쓰임 -->
				</tr>
				<tr>
					<td>비번</td>
					<td><input type="password" name="pw"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${sessionScope.user.name }"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" value="${sessionScope.user.email }"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" value="${sessionScope.user.address }"></td>
				</tr>
			</table>
			<input type="submit" value="정보 수정" class="btn btn-default"
				onclick="check()"> <input type="button" value="취소"
				class="btn btn-primary" onclick="history.go(-1)">
		</form>
	</div>
</section>

<script>
	function check() {
		// form 태그는 유일하게 document.form이름.이름...으로 접근이 가능하다 
		// form name="regForm"이라 지정하면  f12 -> Console에서 "가입" 을 누르면  값이 뜨는것이 확인된다
		// form 에서만 유일하게 가능하다
		console.log( document.regForm.id);			// id의 전체 내용이 출력
		console.log( document.regForm.id.name);		// id의 이름만 출력
		console.log( document.regForm.id.value);	// id의 값을 출력
		
		if(document.regForm.id.value.length < 4 ) {	// 4글자 이하라면 
			alert("아이디는 4글자 이상 입니다");
			return;									// 종료
		} else if(document.regForm.pw.value.length < 4) {
			alert("비밀번호는 4자리 이상입니다");
			return;
		} else if(document.regForm.pw.value != document.regForm.pwCheck.value) {	// 비밀번호가 일치하지 않다면 
			alert("비밀번호가 일치하지 않습니다");
			return;
		} else if(document.regForm.name.value =='') {// 공백이라면 
			 alert("이름은 필수 입니다");
		} else {
			document.regForm.submigt();
		}
	}
	 
</script>

<%@ include file="../include/footer.jsp" %>

