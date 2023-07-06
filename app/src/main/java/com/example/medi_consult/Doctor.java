package com.example.medi_consult;

public class Doctor extends  Person {
    private int doctorFees;
    private String degree;
    private String specialization;

    Doctor(){}

    Doctor(String id,String firstname,String lastName,String emailAddress,String password,String mobile,String Address,String doctorFees,String degree,String specialization){
        this.setId(id);
        this.setUsertype(Macros.DOCTOR);
        this.firstName=firstname;
        this.lastName=lastName;
        this.emailAddress=emailAddress;
        this.password=password;
        this.mobileNumber=mobile;
        this.address=emailAddress;
        this.doctorFees=Integer.valueOf(doctorFees);
        this.degree=degree;
        this.specialization=specialization;
    }

    public int getDoctorFees() {
        return doctorFees;
    }

    public void setDoctorFees(int doctorFees) {
        this.doctorFees = doctorFees;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
