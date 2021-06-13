package org.jedy.system_core.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.jedy.system_core.support.EnumType;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumType {

    /*
    * 시스템적인 예외처리는 GlobalExceptionHandler 여기서
    * 비즈니스 예외에 대한 선언은 이 아래에서
    *
    * 400 : 클라에러
    * 401 : 인증필요
    * 403 : 권한부족
    * 404 : 요청 리소스 없음 or 권한부족할때 리소스존재여부 숨기고 싶을때
    * 500 : 서버에러
     * */

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    TOKEN_EXPIRE(400, "C007", "token expire"),





    // Member
    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),
    LOGIN_ID_DUPLICATION(400, "M003", "LoginId is Duplication"),

    // Coupon
    COUPON_ALREADY_USE(400, "COUPON_00001", "Coupon was already used"),
    COUPON_EXPIRE(400, "COUPON_00002", "Coupon was already expired"),

    // Operator
    ADD_AUTH_OWNER_NOT_EQUAL(400, "OPERATOR_00001", "owner of added auth not equal"),
    INVALID_AUTHORITY_ASSIGN(403, "OPERATOR_00002", "권한이 제대로 부여되지 않았습니다. 관리자에게 문의해주세요"),

    // Oembed
    NOT_SUPPORT_PROVIDER_TYPE(405, "OEMBED_00001", "Not support provider type")
    ;



    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }


}
