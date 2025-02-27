package com.s22010388.gardenguardianapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterScreen extends AppCompatActivity {
    EditText username, password, confirmPassword;
    Button registerButton;
    TextView loginText2;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerButton= findViewById(R.id.registerButton);
        loginText2 = findViewById(R.id.loginText2);
        confirmPassword = findViewById(R.id.confirmPassword);
        DB = new DBHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text from the EditText fields
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = confirmPassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(RegisterScreen.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        // Check if the username already exists in the database
                        Boolean checkUser = DB.checkUsername(user);
                        if(checkUser==false){
                            // Insert the new user's data into the database
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(RegisterScreen.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),PlantCategoryScreen.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterScreen.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterScreen.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterScreen.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        loginText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(intent);
            }
        });
    }
}