var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            var loginId = $('#loginId').val();
            var password = $('#password').val();
            var idExp = /^(?=.*[A-Za-z0-9])(?=.*[A-za-z]).{4,12}$/;
            var passExp = /^(?=.*[a-zA-Z])((?=.*[0-9])|(?=.*\W)).{6,20}$/;
            if(!idExp.test(loginId)){
                alert("아이디는 영어와 숫자로 이루어진 4~12자만 가능합니다.");
                $("#loginId").focus();
                return;
            }else if(!passExp.test(password)){
                alert("비밀번호는 최소 1개의 숫자 혹은 특수 문자를 포함한 6~20 글자여야 합니다.");
                $("#password").focus();
                return;
            }
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },

    save : function () {
        var data = {
            loginId: $('#loginId').val(),
            password: $('#password').val(),
            nickName: $('#nickName').val(),
            age: $('#age').val(),
            sex: $('#sex').val()

        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/member',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
                if(data == true){
                alert('가입이 완료되었습니다.');
                window.location.href = '/';
                }else{
                alert('중복된 아이디 입니다.');
                }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            password: $('#password').val(),
            nickName: $('#nickName').val(),
            age: $('#age').val(),
            sex: $('#sex').val()
        };
        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/member/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/member/'+id,
            dateType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

main.init();
