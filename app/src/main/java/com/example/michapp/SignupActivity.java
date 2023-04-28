package com.example.michapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupUsername, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateSignupName() | !validateSignupEmail() | !validateSignupUsername() | !validatePassword()) {


                } else {

                    database = FirebaseDatabase.getInstance(); // Aquí mandamos llamar la base de datos en Firebase que creamos
                    reference = database.getReference("users");

                    String name = signupName.getText().toString();
                    String email = signupEmail.getText().toString();
                    String username = signupUsername.getText().toString();
                    String password = signupPassword.getText().toString();

                    HelperClass helperClass = new HelperClass(name, email, username, password);
                    reference.child(name).setValue(helperClass);

                    Toast.makeText(SignupActivity.this, "¡Has ingresado correctamente!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);

                startActivity(intent);

            }
        });

    }

    public Boolean validateSignupName(){
        String val = signupName.getText().toString();

        if(val.isEmpty()){

            signupName.setError("¡Tu nombre no puede quedar vacío!");
            return false;

        }else {

            signupName.setError(null);
            return true;

        }
    }

    public Boolean validateSignupEmail(){
        String val = signupEmail.getText().toString();

        if(val.isEmpty()){

            signupEmail.setError("¡Tu correo no puede quedar vacío!");
            return false;

        }else {

            signupEmail.setError(null);
            return true;

        }
    }

    public Boolean validateSignupUsername(){
        String val = signupUsername.getText().toString();

        if(val.isEmpty()){

            signupUsername.setError("¡Tu nombre de usuario no puede quedar vacío!");
            return false;

        }else {

            signupUsername.setError(null);
            return true;

        }
    }

    public Boolean validatePassword(){
        String val = signupPassword.getText().toString();

        if(val.isEmpty()){

            signupPassword.setError("¡Tu contraseña no puede quedar vacía!");
            return false;

        }else {

            signupPassword.setError(null);
            return true;

        }
    }
}