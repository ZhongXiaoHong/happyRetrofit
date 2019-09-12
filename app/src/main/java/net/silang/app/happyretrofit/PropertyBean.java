package net.silang.app.happyretrofit;

public class PropertyBean {
    private String userName;
    private String properId;
    private long time;
    private String desc;



    public PropertyBean(String userName, String properId, long time, String desc) {
        super();
        this.userName = userName;
        this.properId = properId;
        this.time = time;
        this.desc = desc;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getProperId() {
        return properId;
    }
    public void setProperId(String properId) {
        this.properId = properId;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
