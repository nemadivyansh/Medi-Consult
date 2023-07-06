package com.example.medi_consult;

import java.util.ArrayList;

public class Appointment {
    private String patientId;
    private String doctorId;
    private String date;
    private String time;
    private ArrayList<String> allergies=new ArrayList<>();


    public ArrayList<String> getAllergies() {
        return this.allergies;
    }

    public void setAllergies(String allergies) {
        String[] allergy = allergies.split(",");

        for(int i=0;i<allergy.length;i++){
            this.allergies.add(allergy[i].trim());
        }
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Appointment(String  patientId, String  doctorId, String date, String time,String allergies) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.setAllergies(allergies);
    }
}
