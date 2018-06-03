package com.angehack.sharedparking.api.response;


/**
 * Created by chenzhenxing on 17/6/28.
 */

public class HttpResult<T>{
    private String data;

    private String message;
    private int status;
    private String error_code;
    private String url;
    private int load_more;
    private T result;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLoad_more() {
        return load_more;
    }

    public void setLoad_more(int load_more) {
        this.load_more = load_more;
    }
}
