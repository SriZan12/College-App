package Admin.Courses;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ActivityCoursesList2Binding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import Admin.Courses.SubCourses.BBS.SubBBS_Activity;
import Admin.Courses.SubCourses.BCA.SubBCA_Activity;
import Admin.Courses.SubCourses.BSW.SubBSW_Activity;

public class CoursesListActivity extends AppCompatActivity {

    private static final String TAG = "This";
    ActivityCoursesList2Binding coursesListBinding;
    CourseListAdapter courseListAdapter;
    FirebaseAuth firebaseAuth;
    String role;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coursesListBinding = ActivityCoursesList2Binding.inflate(getLayoutInflater());
        setContentView(coursesListBinding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        role = getIntent().getStringExtra("role");
        Log.d(TAG, "onCreate: " + role);

        coursesListBinding.recycler.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        coursesListBinding.recycler.addItemDecoration(dividerItemDecoration);


        FirebaseRecyclerOptions<CourseModel> options =
                new FirebaseRecyclerOptions.Builder<CourseModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Courses"), CourseModel.class)
                        .build();

         courseListAdapter = new CourseListAdapter(options,clickListener);
        coursesListBinding.recycler.setAdapter(courseListAdapter);

        coursesListBinding.courseAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(CoursesListActivity.this);
                dialog.getWindow().setContentView(R.layout.course_add);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                EditText courseName = dialog.findViewById(R.id.courseName);
                EditText Year = dialog.findViewById(R.id.year);
                Button doneButton = dialog.findViewById(R.id.courseDone);

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String CourseName = courseName.getText().toString();
                        String year = Year.getText().toString();

                        Map<String,Object> map = new HashMap<>();
                        map.put("CourseName",CourseName);
                        map.put("Year",year);

                        FirebaseDatabase.getInstance().getReference().child("Courses")
                                .push()
                                .setValue(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(CoursesListActivity.this, "Course Added", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();

                                        }else{
                                            Toast.makeText(CoursesListActivity.this, "Unable to add Course !", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }
                                });

                    }
                });
                dialog.show();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onStart() {
        super.onStart();
        courseListAdapter.notifyDataSetChanged();
        courseListAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        courseListAdapter.stopListening();
    }

    private final onClickListener clickListener = new onClickListener() {
        @Override
        public void onClick(int position) {
            switch (position){
                case 0 :
                   intent = new Intent(CoursesListActivity.this,SubBCA_Activity.class);
                   intent.putExtra("role",role);
                   startActivity(intent);
                    break;

                case 1 :
                    intent = new Intent(CoursesListActivity.this,SubBBS_Activity.class);
                    intent.putExtra("role",role);
                    startActivity(intent);
                    break;

                case 2 :
                    intent = new Intent(CoursesListActivity.this,SubBSW_Activity.class);
                    intent.putExtra("role",role);
                    startActivity(intent);
                    break;

            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}