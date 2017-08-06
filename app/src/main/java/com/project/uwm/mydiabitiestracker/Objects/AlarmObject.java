package com.project.uwm.mydiabitiestracker.Objects;

/**
 * Created by Anitha on 8/5/2017.
 */

public class AlarmObject {
    private int AID;
    private String time;
    private String label;
    private boolean repeat;
    AlarmObject(int aid, String time, String lbl, boolean rpt){
        setAID(aid);
        setRepeat(rpt);
        setLabel(lbl);
        setTime(time);
    }
    public void setAID(int AID) {
        this.AID = AID;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getAID() {
        return AID;
    }
    public String getLabel() {
        return label;
    }
    public String getTime() {
        return time;
    }
    public boolean getRepeat(){
        return repeat;
    }
}
