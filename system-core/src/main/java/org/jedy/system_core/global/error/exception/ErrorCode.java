package org.jedy.system_core.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.jedy.system_core.support.EnumType;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumType {

    /*
    * 시스템적인 예외처리는 GlobalExceptionHandler 여기서
    * 비즈니스 예외에 대한 선언은 이 아래에서
    *
    * 당연히 예외처리는 자세할수록 좋지만 번거로운것도 사실이다.
    * 할수있으면 포괄적인 의미로 던지고 기획단에서 특별한 요구가 있다던가 특별한 이유가 있을때 더 세부적으로 정의해서 던지자
    * 예를들면 이메일인증같은건 따로 정의하는게 맞겠네
     * */

    // Common
    INVALID_INPUT_VALUE( "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED("C002", "Method Not Allowed"),
    ENTITY_NOT_FOUND("C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR("C004", "Server Error"),
    INVALID_TYPE_VALUE("C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED("C006", "Access is Denied"),
    TOKEN_EXPIRE("C007", "token expire"),



    // Member
    EMAIL_DUPLICATION("M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID("M002", "Login input is invalid"),
    LOGIN_ID_DUPLICATION("M003", "LoginId is Duplication"),

    // Coupon
    COUPON_ALREADY_USE("COUPON_00001", "Coupon was already used"),
    COUPON_EXPIRE("COUPON_00002", "Coupon was already expired"),

    // Operator
    ADD_AUTH_OWNER_NOT_EQUAL("OPERATOR_00001", "owner of added auth not equal"),
    INVALID_AUTHORITY_ASSIGN("OPERATOR_00002", "권한이 제대로 부여되지 않았습니다. 관리자에게 문의해주세요"),

    // Oembed
    NOT_SUPPORT_PROVIDER_TYPE("OEMBED_00001", "Not support provider type")
    ;



    private final String code;
    private final String message;

    ErrorCode(final String code, final String message) {
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

}
