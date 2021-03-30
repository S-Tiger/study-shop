// 유효성 체크
function validation() {
	var memberId = $('#memberId').val();
	var password = $('#password').val();
	var confirmPassword = $('#confirmPassword').val();

	var idExp = /^(?=.*[A-Za-z0-9])(?=.*[A-za-z]).{4,12}$/;
    var passExp = /^(?=.*[a-zA-Z])((?=.*[0-9])|(?=.*\W)).{6,20}$/;

    if(!idExp.test(memberId)){
        alert("아이디는 영어와 숫자로 이루어진 4~12자만 가능합니다.");
        $("#memberId").focus();
        return false;
    }else if(!passExp.test(password)){
        alert("비밀번호는 최소 1개의 숫자 혹은 특수 문자를 포함한 6~20 글자여야 합니다.");
        $("#password").focus();
        return false;
    }else if(confirmPassword != password){
        alert("입력하신 비밀번호가 서로 다릅니다.")
        $("#password").focus();
        return false;
    }
    return true;
};

// 회원 가입
function signup() {
	if(validation()){
		if(confirm('등록하시겠습니까?')) {
			$('#signupform').submit();
		}
	}
}