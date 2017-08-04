package com.project.uwm.mydiabitiestracker.Objects;

/**
 * Created by Anitha on 7/13/2017.
 */

public class GlucoseReadingObject {
    private int glucose_id;
    private String username;
    private int glucose_level;
    private String reading_taken;
    private String gdate;
    private String gtime;

    public GlucoseReadingObject(int id,String username, int t_glucose_level, String t_reading_taken, String t_gdate , String t_gtime){
        setGlucose_id(id);
        setUsername(username);
        setGlucose_level(t_glucose_level);
        setReading_taken(t_reading_taken);
        setGdate(t_gdate);
        setGtime(t_gtime);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGlucose_id(int glucose_id) {
        this.glucose_id = glucose_id;
    }

    public String getReading_taken() {
        return reading_taken;
    }
    public int getGlucose_level() {
        return glucose_level;
    }
    public int getGlucose_id() {
        return glucose_id;
    }
    public void setGlucose_level(int glucose_level) {
        this.glucose_level = glucose_level;
    }
    public void setReading_taken(String reading_taken) {
        this.reading_taken = reading_taken;
    }

    public void setGdate(String gdate) {
        this.gdate = gdate;
    }

    public void setGtime(String gtime) {
        this.gtime = gtime;
    }

    public String getGdate() {
        return gdate;
    }

    public String getGtime() {
        return gtime;
    }

}