package com.example.relianceinternationalcollege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.relianceinternationalcollege.databinding.ActivityCategoryBinding;

import Admin.AdminActivity;
import Student.StudentActivity;
import Teacher.TeacherActivity;

public class CategoryActivity extends AppCompatActivity {

    ActivityCategoryBinding categoryBinding;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryBinding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(categoryBinding.getRoot());
        role = "TeacherStudent";

        categoryBinding.admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this,AdminActivity.class);
                intent.putExtra("role","Admin");
                startActivity(intent);
            }
        });

        categoryBinding.teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, TeacherActivity.class);
                intent.putExtra("role",role);
                startActivity(intent);
            }
        });

        categoryBinding.student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, StudentActivity.class);
                intent.putExtra("role",role);
                startActivity(intent);
            }
        });


    }
}