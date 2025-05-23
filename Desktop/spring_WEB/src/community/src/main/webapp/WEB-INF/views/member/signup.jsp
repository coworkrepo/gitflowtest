<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<style>
body {
	font-family: sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 40px 0;
	display: flex;
	justify-content: center;
	align-items: flex-start;
}

#signUpForm {
	background-color: #fff;
	padding: 30px 40px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 500px;
}

#signUp td {
	padding: 10px 0;
	vertical-align: top;
}

#signUp label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
}

#signUp input[type="text"], #signUp input[type="password"], #signUp input[type="email"],
	#signUp select {
	width: calc(100% - 20px);
	padding: 8px 10px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

#signUp input[readonly] {
	background-color: #e9ecef;
	cursor: not-allowed;
}

/* 버튼 2개 같은 줄에 배치 + 가운데 정렬 */
.button-group {
	display: flex;
	gap: 10px;
	margin-top: 5px;
	justify-content: center; /* 가운데 정렬 */
}

#signUp button[type="submit"] {
	padding: 10px 16px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s ease;
	margin-top: 5px;
}

#signUp button[type="submit"]:hover {
	background-color: #0056b3;
}

#cancelBtn {
	padding: 10px 16px;
	background-color: #6c757d; /* 회색 */
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s ease;
	margin-top: 5px;
}

#cancelBtn:hover {
	background-color: #5a6268; /* 어두운 회색 */
}

#signUp button[type="button"] {
	padding: 10px 16px;
	font-size: 14px;
	border-radius: 4px;
	border: none;
	cursor: pointer;
	transition: background-color 0.3s ease;
	margin-top: 5px;
}

/* 중복체크 버튼 스타일 */
#idCheckBtn {
	padding: 8px 14px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s ease;
	margin-top: 0;
	white-space: nowrap;
	min-width: 90px;
}

#idCheckBtn:hover {
	background-color: #0056b3;
}

#signUp span {
	padding: 0 5px;
	font-weight: bold;
}

.hidden-field {
	display: none;
}

/* 이메일 입력 개선 */
.email-group {
	display: flex;
	align-items: center;
	flex-wrap: wrap;
	gap: 5px;
}

.email-group input[type="text"], .email-group select {
	width: auto;
	min-width: 120px;
	flex: 1;
}

.email-group span {
	font-weight: bold;
	padding: 0 5px;
}

/* 아이디 입력 + 중복체크 버튼 컨테이너 */
.id-check-container {
	display: flex;
	align-items: center;
	gap: 10px;
}

#signUp button.zipcode-btn {
	padding: 10px 16px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s ease;
	margin-left: 10px;
}

#signUp button.zipcode-btn:hover {
	background-color: #0056b3;
}

</style>

<script>
function searchZipcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            var fullAddress = data.address;
            var extraAddress = '';

            if (data.addressType === 'R') {
                if (data.bname !== '') {
                    extraAddress += data.bname;
                }
                if (data.buildingName !== '') {
                    extraAddress += (extraAddress !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if (extraAddress !== '') {
                    fullAddress += ' (' + extraAddress + ')';
                }
            }

            document.getElementById('zipCode').value = data.zonecode;
            document.getElementById('address').value = fullAddress;
            document.getElementById('detailAddress').focus();
            document.getElementById('fullAddress').value = fullAddress + ' ' + document.getElementById('detailAddress').value;
        }
    }).open();
}

window.addEventListener('DOMContentLoaded', () => {
    // 상세 주소 입력 시 fullAddress 갱신
    document.getElementById('detailAddress').addEventListener('input', function() {
        const full = document.getElementById('address').value + ' ' + this.value;
        document.getElementById('fullAddress').value = full.trim();
    });

    // 이메일 조합
    const emailId = document.getElementById('userEmail');
    const emailDomain = document.getElementById('email_domain');
    const fullEmail = document.getElementById('fullEmail');

    function updateEmail() {
        fullEmail.value = emailId.value.trim() + '@' + emailDomain.value.trim();
    }

    emailId.addEventListener('input', updateEmail);
    emailDomain.addEventListener('change', updateEmail);

    // 초기 이메일 fullEmail 셋팅
    updateEmail();
});

// 중복체크 함수 예시
function checkId() {
    const userId = document.getElementById("userId").value;

    if(userId === ""){
    	alert("아이디를 입력하세요");
    	document.getElementById("userId").focus();
    	return;
    }
    
    let id = $("#userId").val()     
    $.ajax({
    	url: "${pageContext.request.contextPath}/userIdCheck",
    	type: "post",
    	data: {'id': id},
    	success: function(res){
    		if(res === "success"){
    			${"#checkResult"}.text("사용 가능한 아이디입니다")
    			
    		}else{
    			$("#checkResult").text("사용 불가능한 아이디입니다.")
    		}
    	}
    	
    })
    
    // TODO: 비동기 중복 체크 코드 작성
}
</script>
</head>

<body>
	<form action="${pageContext.request.contextPath}/join" method="post"
		id="signUpForm">
		<table id="signUp">
			<tr>
				<td><label for="userId">아이디</label>
					<div class="id-check-container">
						<input type="text" name="userId" id="userId"
							placeholder="아이디를 입력하세요" onfocus="this.placeholder=''"
							onblur="this.placeholder='아이디를 입력하세요'" required>
						<button type="button" id="idCheckBtn" onclick="checkId()">중복체크</button>
						<span id = "checkResult"></span>
					</div></td>
			</tr>
			<tr>
				<td><label for="userPassword">비밀번호</label> <input
					type="password" name="userPassword" id="userPassword"
					placeholder="비밀번호를 입력하세요" onfocus="this.placeholder=''"
					onblur="this.placeholder='비밀번호를 입력하세요'" required></td>
			</tr>
			<tr>
				<td><label for="userPasswordConfirm">비밀번호 확인</label> <input
					type="password" name="userPasswordConfirm" id="userPasswordConfirm"
					placeholder="비밀번호를 한 번 더 입력하세요" onfocus="this.placeholder=''"
					onblur="this.placeholder='비밀번호를 한 번 더 입력하세요'" required></td>
			</tr>
			<tr>
				<td><label for="userName">이름</label> <input type="text"
					name="userName" id="userName" placeholder="이름을 입력하세요"
					onfocus="this.placeholder=''" onblur="this.placeholder='이름을 입력하세요'"
					required></td>
			</tr>
			<tr>
				<td><label for="zipCode">우편번호</label> <input type="text"
					name="zipCode" id="zipCode" placeholder="우편번호" readonly required>
					<button type="button" class="zipcode-btn" onclick="searchZipcode()">우편번호 검색</button>
</td>
			</tr>
			<tr>
				<td><label for="address">주소</label> <input type="text"
					id="address" placeholder="주소를 입력하세요" readonly required> <input
					type="text" id="detailAddress" placeholder="상세 주소를 입력하세요" required>
					<input type="hidden" name="address" id="fullAddress"></td>
			</tr>

			<!-- 이메일 입력란 시작 -->
			<tr>
				<td><label for="userEmail">이메일</label>
					<div class="email-group">
						<input type="text" name="emailId" id="userEmail"
							placeholder="이메일 아이디" required> <span>@</span> <select
							name="emailDomain" id="email_domain" required>
							<option value="naver.com" selected>naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="daum.net">daum.net</option>
						</select>
					</div> <input type="hidden" name="userEmail" id="fullEmail" /></td>
			</tr>
			<!-- 이메일 입력란 끝 -->
			<tr>
				<td><label for="phoneNum">휴대폰번호</label> <input type="text"
					id="phoneNum" name="userPhoneNum" maxlength="11"
					placeholder="숫자만 입력" required></td>
			</tr>

			<tr>
				<td>
					<div class="button-group">
						<button type="submit">가입</button>
						<button type="reset" id="cancelBtn">취소</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
