package Admin.Courses.SubCourses.BBS.Years.SecondYear;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentSecondYearSubjectBinding;
import com.example.relianceinternationalcollege.databinding.FragmentSubjectsBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import Admin.Courses.SubCourses.BBS.SubjectModel;


public class SecondYearSubject extends Fragment {

    FragmentSecondYearSubjectBinding secondYearSubjectBinding;
    SecondYearSubjectAdapter secondYearSubjectAdapter;
    String Sex;
    String Role;

    public SecondYearSubject(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        secondYearSubjectBinding = FragmentSecondYearSubjectBinding.inflate(inflater, container, false);

        secondYearSubjectBinding.bbsSecondSubRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<SubjectModel> options =
                new FirebaseRecyclerOptions.Builder<SubjectModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("subDetails").
                                child("BBS").child("Second_Year").child("StudentList"), SubjectModel.class)
                        .build();

        secondYearSubjectAdapter = new SecondYearSubjectAdapter(options,Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        secondYearSubjectBinding.bbsSecondSubRecycler.addItemDecoration(dividerItemDecoration);
        secondYearSubjectBinding.bbsSecondSubRecycler.setAdapter(secondYearSubjectAdapter);

        if(Role != null && Role.equals("TeacherStudent")){
            secondYearSubjectBinding.bbsSecondSubAdd.setVisibility(View.GONE);
        }

        secondYearSubjectBinding.bbsSecondSubAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.getWindow().setContentView(R.layout.subject_add);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                EditText SubjectName = dialog.findViewById(R.id.subjectName);
                EditText SubjectTeacher = dialog.findViewById(R.id.subjectTeacher);
                EditText SubjectCode = dialog.findViewById(R.id.SubjectCode);
                Button done = dialog.findViewById(R.id.subjectDone);

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Name = SubjectName.getText().toString();
                        String Teacher = SubjectTeacher.getText().toString();
                        String code = SubjectCode.getText().toString();

                        Map<String, Object> subDetails = new HashMap<>();
                        subDetails.put("SubjectName", Name);
                        subDetails.put("Teacher", Teacher);
                        subDetails.put("SubjectCode", code);

                        FirebaseDatabase.getInstance().getReference().child("subDetails").
                                child("BBS").child("Second_Year").child("StudentList")
                                .push()
                                .setValue(subDetails)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getContext(), "Subject Added", Toast.LENGTH_SHORT).show();
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
        return secondYearSubjectBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        secondYearSubjectAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        secondYearSubjectAdapter.stopListening();
    }

}