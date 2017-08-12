package com.project.uwm.mydiabitiestracker.Objects;

public class PrescriptionReadingObject {
    private int prescription_id;
    private String username;
    private String drug_name;
    private int dosage;
    private String date;
    private String time;

    public PrescriptionReadingObject(int id, String username, String drugname, int dosage, String date, String time){
        setPrescriptionId(id);
        setDosage(dosage);
        setDrugName(drugname);
        setDate(date);
        setTime(time);
        setUsername(username);
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getPrescription_id() {return prescription_id;}

    public void setPrescriptionId(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getDrugName() {
        return drug_name;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public void setDrugName(String drug_type) {
        this.drug_name = drug_type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
