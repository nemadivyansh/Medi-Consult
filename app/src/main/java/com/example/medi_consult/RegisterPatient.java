package com.example.medi_consult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RegisterPatient extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextMobile, editTextAddress, editTextSymptoms;
    private ProgressBar progressBar;
    private Button register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);


        editTextFirstName = (EditText) findViewById(R.id.firstName_patient);
        editTextLastName = (EditText) findViewById(R.id.lastName_patient);
        editTextEmail = (EditText) findViewById(R.id.email_patient);
        editTextPassword = (EditText) findViewById(R.id.password_patient);
        editTextAddress = (EditText) findViewById(R.id.address_patient);
        editTextMobile = (EditText) findViewById(R.id.mobile_patient);
        editTextSymptoms=(EditText)  findViewById(R.id.symptomps_patient);

        progressBar = (ProgressBar)  findViewById(R.id.registerPatientIndeterminateProgressbar);
        register = (Button)  findViewById(R.id.buttonRegisterPatient);

        mAuth= FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerPatient();
            }
        });
    }

    void registerPatient() {

        List<String> symp=new ArrayList<>();

        //Inputs
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String mobile = editTextMobile.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String symptoms=editTextSymptoms.getText().toString().trim();

        //Validations
        if (firstName.isEmpty()) {
            editTextFirstName.setError("First Name is required");
            editTextFirstName.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            // A person may or may not have his last name.
            editTextLastName.setText("");
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email address");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<8){
            editTextPassword.setError("Password should be 8 characters long");
            editTextPassword.requestFocus();
            return;
        }

        if (mobile.isEmpty()) {
            editTextMobile.setError("Contact number is required");
            editTextMobile.requestFocus();
            return;
        }

        if (!Patterns.PHONE.matcher(mobile).matches() || mobile.length()!=10) {
            editTextMobile.setError("Invalid contact no");
            editTextMobile.requestFocus();
            return;
        }

        if(symptoms.isEmpty()){
            editTextSymptoms.setText("");
        }
        else{
            String[] arr=symptoms.split(",");

            for(int i=0;i< arr.length;i++){
                symp.add(arr[i].trim());
            }
        }

        if (address.isEmpty()) {
            editTextAddress.setError("Address is required");
            editTextAddress.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            Patient patient=new Patient(uid,firstName,editTextLastName.getText().toString(),email,password,mobile,address,symp);

//                            FirebaseDatabase.getInstance().getReferenceFromUrl("https://medi-consult-1bad8-default-rtdb.firebaseio.com/Patients/")
                            FirebaseDatabase.getInstance().getReferenceFromUrl("https://medi-consult-project-default-rtdb.firebaseio.com/Patients/")
                                    .child(uid)
                                    .setValue(patient)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressBar.setVisibility(View.GONE);
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegisterPatient.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(RegisterPatient.this, "Something wrong happened, Try Again!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterPatient.this,"Something wrong happened, Try Again!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}