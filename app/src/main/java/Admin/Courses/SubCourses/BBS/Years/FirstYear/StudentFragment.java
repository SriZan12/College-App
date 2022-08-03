package Admin.Courses.SubCourses.BBS.Years.FirstYear;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentStudentBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import Admin.Courses.SubCourses.BBS.StudentModel;


public class StudentFragment extends Fragment {

   FragmentStudentBinding studentBinding;
   String Sex;
   StudentAdapter studentAdapter;
   String Role;

    public StudentFragment(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        studentBinding =  FragmentStudentBinding.inflate(inflater, container, false);

        studentBinding.bbsFirsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BBS").child("First_Year").child("StudentList"), StudentModel.class)
                        .build();

        studentAdapter = new StudentAdapter(options,Role);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        studentBinding.bbsFirsRecycler.addItemDecoration(dividerItemDecoration);
        studentBinding.bbsFirsRecycler.setAdapter(studentAdapter);

        if(Role != null && Role.equals("TeacherStudent")){
            studentBinding.bbsFirstStudent.setVisibility(View.GONE);
        }

        studentBinding.bbsFirstStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.getWindow().setContentView(R.layout.student_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                EditText StudentName = dialog.findViewById(R.id.studentName);
                EditText StudentCourse = dialog.findViewById(R.id.studentCourse);
                EditText StudentAge = dialog.findViewById(R.id.studentAge);
                EditText StudentAddress = dialog.findViewById(R.id.student_Address);
                EditText StudentRollNo = dialog.findViewById(R.id.studentRollNo);
                EditText StudentNumber = dialog.findViewById(R.id.studentNumber);
                Button doneButton = dialog.findViewById(R.id.done);

                Spinner spinner = dialog.findViewById(R.id.studentSex);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.sex, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinner.setAdapter(adapter);


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Sex = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Name = StudentName.getText().toString();
                        String Course = StudentCourse.getText().toString();
                        String Age = StudentAge.getText().toString();
                        String Address = StudentAddress.getText().toString();
                        String Number = StudentNumber.getText().toString();
                        String RollNo = StudentRollNo.getText().toString();

                        Map<String,Object> StudentDetails = new HashMap<>();
                        StudentDetails.put("StudentName",Name);
                        StudentDetails.put("StudentCourse",Course);
                        StudentDetails.put("StudentAge",Age);
                        StudentDetails.put("StudentSex",Sex);
                        StudentDetails.put("StudentAddress",Address);
                        StudentDetails.put("StudentRollNo",RollNo);
                        StudentDetails.put("StudentNumber",Number);

                        FirebaseDatabase.getInstance().getReference("StudentDetails").child("BBS")
                                .child("First_Year")
                                .child("StudentList")
                                .push()
                                .setValue(StudentDetails)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getContext(), "Student Added", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }else{
                                            Toast.makeText(getContext(), "Couldn't Add !", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }
                                });
                    }
                });


            }
        });

        return studentBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        studentAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        studentAdapter.stopListening();
    }

}