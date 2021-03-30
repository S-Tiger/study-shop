// 세자리 단위 마다 Comma
function addComma(num) {
    var regexp = /\B(?=(\d{3})+(?!\d))/g;
  	return num.toString().replace(regexp, ',');
}

//백분율 소숫점 1번째 자리 반환
function pct(num) {
	var result =  (num * 100).toFixed(1);
	if(isNaN(result)){
		result = 0;
	}
	result +=  "%";
	return result;
}

//백분율 소숫점 2번쨰 자리 반환
function pct2(num) {
	var result =  (num * 100).toFixed(2);
	if(isNaN(result)){
		result=0;
	}
	result += "%";
	return result;
}

// 현재 일시 (12h)
function crtTime() {
	var today = new Date();

	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var date = today.getDate();
	var hour12h = "";
	if (today.getHours() < 12) {
		hour12h = '오전 ' + today.getHours();
	} else if (today.getHours() == 12) {
		hour12h = '오후 ' + today.getHours();
	} else if (today.getHours() > 12) {
		hour12h = '오후 ' + (today.getHours()-12);
	}
	var minute = ("0" + today.getMinutes()).slice(-2);

	return year + ". " + month + ". " + date + ". " + hour12h + ":" + minute;
}

//현재 일시 (요일 포함)
function realTime() {
	var today = new Date();

	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var date = today.getDate();
	var hour = today.getHours();
	var minute = ("0" + today.getMinutes()).slice(-2);
	var second = ("0" + today.getSeconds()).slice(-2);
	var day = today.getDay();
	var week = ['일', '월', '화', '수', '목', '금', '토']

	return year + "/" + month + "/" + date + "(" + week[day] + ") "  + hour + ":" + minute + ":" + second;
}

//nan 이면 0 반환
function nanToNum(num) {
	if (isNaN(num)) {
		num = 0;
	}
	return num;
}

//1000원 단위로 변환
function thousand(num) {
	return Math.floor(num / 1000);
}

//첨부파일 체크
$("#ex_filename").change(function(){
	if($(this).val() != ""){
		//확장자 체크
		var ext = $(this).val().split(".").pop().toLowerCase();
		if($.inArray(ext , ["xls","xlsx","ppt","pptx","txt","hwp","xml","jpg","png","gif",
							"zip","pdf","doc","docx"]) == -1)	{
			alert("가능한 확장자가 아닙니다, 업로드 파일을 확인해주세요");
			$(this).val("");
			return;
		}
		// 용량 체크
		for (var i = 0; i < this.files.length; i++){
			var fileSize = this.files[i].size;
			var maxSize = 1024 * 1024 * 292;
			if(fileSize > maxSize){
				alert(this.files[i].name + "의 용량이 292MB을 초과 합니다." );
				$(this).val("");
			}
		}
	}
})