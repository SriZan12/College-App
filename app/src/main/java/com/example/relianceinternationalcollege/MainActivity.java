package com.example.relianceinternationalcollege;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.relianceinternationalcollege.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    FirebaseAuth firebaseAuth;
    String Status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();


        mainBinding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mainBinding.email.getText().toString();
                String Username = mainBinding.username.getText().toString();
                String password = mainBinding.password.getText().toString();
                String confirmPassword = mainBinding.confirmPassword.getText().toString();

                if (Username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                    mainBinding.email.setFocusable(true);
                    mainBinding.email.setError("Required");

                } else if (!password.equals(confirmPassword)) {
                    mainBinding.password.setFocusable(true);
                    mainBinding.confirmPassword.setFocusable(true);
                    mainBinding.confirmPassword.setError("Password didn't match");
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(Username
                                ).build();
                                assert user != null;
                                user.updateProfile(profileUpdates);
                                Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Registration Failed !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }

}