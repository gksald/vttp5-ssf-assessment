package vttp.batch5.ssf.noticeboard.models;

public class SuccessRestNotice {
    private String id;
    private Number timestamp;

    public SuccessRestNotice() {
        
    }

    public SuccessRestNotice(String id, Number timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Number getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Number timestamp) {
        this.timestamp = timestamp;
    }

    

    
}
