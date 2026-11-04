package response;

public class ApiResponse<T> {
    private String status;
    private int code;
    private T data;
    private Meta meta;

    public ApiResponse() {
    }

    public ApiResponse(String status, int code, T data, Meta meta) {
        this.status = status;
        this.code = code;
        this.data = data;
        this.meta = meta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
