package dpp.wordbreaktest.base;

import dpp.wordbreaktest.enums.ResponseCodeEnum;

public class BaseResponseBody {
    private String code;

    private String msg;

    private Object data;

    public BaseResponseBody(ResponseCodeEnum tempCode) {
        this(tempCode, tempCode.getMsg(), null);
    }

    public BaseResponseBody(ResponseCodeEnum tempCode, String tempMsg) {
        this(tempCode, tempMsg, null);
    }

    public BaseResponseBody(ResponseCodeEnum tempCode, Object tempData) {
        this(tempCode, tempCode.getMsg(), tempData);
    }

    public BaseResponseBody(ResponseCodeEnum tempCode, String tempMsg, Object tempData) {
        this.code = tempCode.getCode();
        this.msg = tempMsg;
        this.data = tempData;
    }

    public BaseResponseBody() {
    }

    public static BaseResponseBody success() {
        return new BaseResponseBody(ResponseCodeEnum.SUCCESS);
    }

    public static BaseResponseBody success(String tempMsg) {
        return new BaseResponseBody(ResponseCodeEnum.SUCCESS, tempMsg);
    }

    public static BaseResponseBody success(Object tempData) {
        return new BaseResponseBody(ResponseCodeEnum.SUCCESS, tempData);
    }

    public static BaseResponseBody success(String tempMsg, Object tempData) {
        return new BaseResponseBody(ResponseCodeEnum.SUCCESS, tempMsg, tempData);
    }

    public static BaseResponseBody fail() {
        return new BaseResponseBody(ResponseCodeEnum.FAIL);
    }

    public static BaseResponseBody fail(String tempMsg) {
        return new BaseResponseBody(ResponseCodeEnum.FAIL, tempMsg);
    }

    public static BaseResponseBody fail(Object tempData) {
        return new BaseResponseBody(ResponseCodeEnum.FAIL, tempData);
    }

    public static BaseResponseBody fail(String tempMsg, Object tempData) {
        return new BaseResponseBody(ResponseCodeEnum.FAIL, tempMsg, tempData);
    }

    public static BaseResponseBody error() {
        return new BaseResponseBody(ResponseCodeEnum.INTERNAL_SERVER_ERROR);
    }

    public static BaseResponseBody error(String tempMsg) {
        return new BaseResponseBody(ResponseCodeEnum.INTERNAL_SERVER_ERROR, tempMsg);
    }

    public static BaseResponseBody error(Object tempData) {
        return new BaseResponseBody(ResponseCodeEnum.INTERNAL_SERVER_ERROR, tempData);
    }

    public static BaseResponseBody error(String tempMsg, Object tempData) {
        return new BaseResponseBody(ResponseCodeEnum.INTERNAL_SERVER_ERROR, tempMsg, tempData);
    }

    public String getCode() {
        return this.code;
    }

    public BaseResponseBody setCode(String tempCode) {
        this.code = tempCode;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public BaseResponseBody setMsg(String tempMsg) {
        this.msg = tempMsg;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public BaseResponseBody setData(Object tempData) {
        this.data = tempData;
        return this;
    }
}