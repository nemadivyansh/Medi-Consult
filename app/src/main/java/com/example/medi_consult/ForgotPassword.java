package com.example.medi_consult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailEdittext;
    private Button resetPasswordButton;
    private FirebaseAuth mAuth;
    private TextView msg;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEdittext=findViewById(R.id.email);
        resetPasswordButton=findViewById(R.id.buttonResetPassword);
        msg=findViewById(R.id.resetPassword);
        progressBar=findViewById(R.id.forgotPasswordIndeterminateProgressbar);

        mAuth=FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword(){
        String email=emailEdittext.getText().toString().trim();

        if(email.isEmpty()){
            emailEdittext.setError("Email is required");
            emailEdittext.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdittext.setText("");
            emailEdittext.setError("Enter valid e-mail address");
            emailEdittext.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            msg.setVisibility(View.VISIBLE);
                        }
                        else{
                            Toast.makeText(ForgotPassword.this, "Something wrong happened, Try Again!", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}