package vttp.batch5.ssf.noticeboard.models;

public class FailureRestNotice {
    
    private String message;
    private Number timestamp;

    public FailureRestNotice(){

    }

    public FailureRestNotice(String message, Number timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Number getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Number timestamp) {
        this.timestamp = timestamp;
    }

    
}
