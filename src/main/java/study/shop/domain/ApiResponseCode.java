package study.shop.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseCode {
    OK("요청이 성공하였습니다."),
    FAIL("요청이 실패했습니다."),
    BAD_REQUEST_PARAMETER("요청 파라미터가 잘못 되었습니다."),
    NOT_FOUND("리소스를 찾을수 없습니다."),
    NOT_UPDATE("수정에 실패했습니다."),
    NOT_DELETE("삭제에 실패했습니다."),
    UNAUTHORIZED_RESOURCE("인증받지 못한 리소스입니다."),
    UNAUTHORIZED("인증에 실패하였습니다."),
    SERVER_ERROR("서버 에러입니다.");
    private final String message;
    public String getId() {
        return name();
    }
    public String getText() {
        return message;
    }
}

