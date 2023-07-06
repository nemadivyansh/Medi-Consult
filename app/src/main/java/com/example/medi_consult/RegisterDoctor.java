
package com.example.medi_consult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterDoctor extends AppCompatActivity {

    private String[] degrees,specialization;

    private AutoCompleteTextView editTextDegree;
    private AutoCompleteTextView editTextSpecialization;

    private EditText editTextFirstName,editTextLastName,editTextEmail,editTextPassword,editTextMobile,editTextAddress,editTextFees;
    private ProgressBar progressBar;
    private Button register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_doctor);

        degrees=getResources().getStringArray(R.array.degrees);
        specialization=getResources().getStringArray(R.array.specialisation);

        ArrayAdapter<String> degreeAdapter=new ArrayAdapter(RegisterDoctor.this,R.layout.dropdown_item,degrees);
        ArrayAdapter<String> specializationAdapter=new ArrayAdapter(RegisterDoctor.this,R.layout.dropdown_item,specialization);

        editTextDegree=(AutoCompleteTextView) findViewById(R.id.doctor_degree);
        editTextSpecialization=(AutoCompleteTextView) findViewById(R.id.doctor_specialisation);

        editTextDegree.setAdapter(degreeAdapter);
        editTextSpecialization.setAdapter(specializationAdapter);

        editTextFirstName=(EditText) findViewById(R.id.firstName_doctor);
        editTextLastName=(EditText)findViewById(R.id.lastName_doctor);
        editTextEmail=(EditText) findViewById(R.id.email_doctor) ;
        editTextPassword=(EditText) findViewById(R.id.password_doctor);
        editTextAddress=(EditText) findViewById(R.id.address_doctor);
        editTextFees=(EditText) findViewById(R.id.doctor_fees);
        editTextMobile=(EditText) findViewById(R.id.mobile_doctor);

        progressBar=(ProgressBar) findViewById(R.id.registerDoctorIndeterminateProgressbar);
        register=(Button) findViewById(R.id.buttonRegisterDoctor);

        mAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerDoctor();
            }
        });
    }

    void registerDoctor(){

        //Inputs
        String firstName=editTextFirstName.getText().toString().trim();
        String lastName=editTextLastName.getText().toString().trim();
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String mobile=editTextMobile.getText().toString().trim();
        String address=editTextAddress.getText().toString().trim();
        String degree=editTextDegree.toString().trim();
        String specialization=editTextSpecialization.toString().trim();
        String fees=editTextFees.getText().toString().trim();


        //Validations
        if(firstName.isEmpty()){
            editTextFirstName.setError("First Name is required");
            editTextFirstName.requestFocus();
            return;
        }

        if(lastName.isEmpty()){
            // A person may or may not have his last name.
            editTextLastName.setText("");
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email address");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(mobile.isEmpty()){
            editTextMobile.setError("Contact number is required");
            editTextMobile.requestFocus();
            return;
        }

        if(!Patterns.PHONE.matcher(mobile).matches()){
            editTextMobile.setError("Invalid contact no");
            editTextMobile.requestFocus();
            return;
        }

        if(address.isEmpty()){
            editTextAddress.setError("Address is required");
            editTextAddress.requestFocus();
            return;
        }

        if(degree.isEmpty()){
            editTextDegree.setError("Enter a degree");
            editTextDegree.requestFocus();
            return;
        }

        if(specialization.equals("")){
            editTextSpecialization.setText("General Physician");
        }

        if(fees.isEmpty()){
            editTextFees.setText("");
        }

        if(Integer.valueOf(fees)>1000){
            editTextFees.setError("Fees should be less than 1000 Rs");
            editTextFees.requestFocus();
            return;
        }

        if(Integer.valueOf(fees)<0){
            editTextFees.setError("Fees should be greater than or equal to zero");
            editTextFees.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();

                            Doctor doctor=new Doctor(uid,firstName,lastName,email,password,mobile,address,editTextFees.getText().toString(),editTextDegree.getText().toString(),editTextSpecialization.getText().toString());
                            FirebaseDatabase.getInstance().getReferenceFromUrl("https://medi-consult-project-default-rtdb.firebaseio.com/Doctors/")
                                    .child(uid)
                                    .setValue(doctor)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressBar.setVisibility(View.GONE);
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegisterDoctor.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(RegisterDoctor.this, "Something wrong happened, Try Again!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterDoctor.this,"Something wrong happened, Try Again!",Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }
}