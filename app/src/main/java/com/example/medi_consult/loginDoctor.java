package com.example.medi_consult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class loginDoctor extends AppCompatActivity {

    Button my_profile, view_appointments, sign_out;
    TextView welcome_text, textview_name, textview_email, textview_address, textview_contact, textview_usertype;
    ProgressBar progressBar;
    EditText edittext_date, edittext_time, edittext_allergies;

    DatabaseReference reference;
    FirebaseUser user;
    String uid, doctorId;

    //Doctor's List
    ArrayList<Doctor> appointments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doctor);
    }
};

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.buttonMyProfile:
//                if(layout_myprofile.getVisibility()!=View.VISIBLE){
//                    layout_myprofile.setVisibility(View.VISIBLE);
//                    //Setting other one gone
//                    layout_bookappointment.setVisibility(View.GONE);
//                }
//                else{
//                    layout_myprofile.setVisibility(View.GONE);
//                }
//                break;
//
//            case R.id.buttonViewDoctors:
//                progressBar.setVisibility(View.VISIBLE);
//                Intent intent=new Intent(loginPatient.this,ViewDoctorsList.class);
//                startActivity(intent);
//                progressBar.setVisibility(View.GONE);
//                break;
//
//            case R.id.buttonScheduleAppointment:
//                scheduleAppointment();
//                break;
//
//            case R.id.loginActivity_textview_doctorName:
//                textview_fees.setText("");
//                break;
//
//            case R.id.buttonLoadFees:
//                loadFees();
//                break;
//
//            case R.id.buttonViewSubmitAppointmentForm:
//                submitAppointment();
//                break;
//
//            case R.id.buttonViewResetAppointmentForm:
//                resetValues();
//                break;
//
//            case R.id.buttonViewSignOut:
//                signOutUser();
//        }
//
//    }