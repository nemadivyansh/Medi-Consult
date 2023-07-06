package com.example.medi_consult;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person{

    private List<String> symptoms;

    Patient(){}

    Patient(String id,String firstname,String lastName,String emailAddress,String password,String mobile,String Address,List<String> symptoms){
        this.setId(id);
        this.setUsertype(Macros.PATIENT);
        this.setFirstName(firstname);
        this.setLastName(lastName);
        this.setEmailAddress(emailAddress);
        this.setPassword(password);
        this.setMobileNumber(mobile);
        this.setAddress(Address);
        this.setsymptoms(symptoms);
    }
    public List<String> getsymptoms() {
        return symptoms;
    }

    public void setsymptoms(List<String> symptoms) {
        this.symptoms=symptoms;
    }
}
