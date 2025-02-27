package com.s22010388.gardenguardianapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginScreen extends AppCompatActivity {

    EditText username, password;
    Button loginButton;
    TextView signupText2;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signupText2 = findViewById(R.id.signupText2);
        DB = new DBHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                // Check if username and password fields are empty
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginScreen.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPassword = DB.checkUsernamePassword(user, pass);
                    if(checkUserPassword ==true){
                        Toast.makeText(LoginScreen.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), PlantCategoryScreen.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginScreen.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterScreen.class);
                startActivity(intent);
            }
        });
    }
}