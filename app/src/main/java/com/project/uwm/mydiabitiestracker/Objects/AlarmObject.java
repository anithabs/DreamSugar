package com.project.uwm.mydiabitiestracker.Objects;

/**
 * Created by Anitha on 8/5/2017.
 */

public class AlarmObject {
    private int[] time;
    private String[] label;;
    AlarmObject(  String[] lbl,int[] time){
        setLabel(lbl);
        setTime(time);
    }
    public void setLabel(String[] label) {
        this.label = label;
    }
    public void setTime(int[] time) {
        this.time = time;
    }
    public String[] getLabel() {
        return label;
    }
    public int[] getTime() {
        return time;
    }

}
