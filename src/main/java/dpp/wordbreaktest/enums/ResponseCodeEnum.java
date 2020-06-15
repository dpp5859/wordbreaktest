package dpp.wordbreaktest.enums;

public enum ResponseCodeEnum {
    /**
     * 操作成功
     */
    SUCCESS("200", "操作成功"),

    /**
     * 操作失败
     */
    FAIL("400", "操作失败"),

    /**
     * 请求参数错误
     */
    BAD_REQUEST_ILLEGAL_PARAM("400", "请求参数错误"),

    /**
     * 认证失败
     */
    BAD_REQUEST_AUTH("401", "认证失败"),

    /**
     * 资源无权限
     */
    BAD_REQUEST_FORBIDDEN("403", "资源无权限"),

    /**
     * 资源不存在
     */
    BAD_REQUEST_NOTFOUND("404", "资源不存在"),

    /**
     * 服务器错误
     */
    INTERNAL_SERVER_ERROR("500", "服务器错误"),

    /**
     * 无数据
     */
    NO_DATA("400", "暂无数据！");

    private ResponseCodeEnum (String tempCode, String tempMsg) {
        this.code = tempCode;
        this.msg = tempMsg;
    }

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
