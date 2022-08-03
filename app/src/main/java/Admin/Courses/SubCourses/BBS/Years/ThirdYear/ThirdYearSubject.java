package Admin.Courses.SubCourses.BBS.Years.ThirdYear;

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
import com.example.relianceinternationalcollege.databinding.FragmentThirdYearSubjectBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import Admin.Courses.SubCourses.BBS.SubjectModel;


public class ThirdYearSubject extends Fragment {

    FragmentThirdYearSubjectBinding thirdYearSubjectBinding;
    ThirdYearSubjectAdapter thirdYearSubjectAdapter;
    String Role;

    public ThirdYearSubject(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thirdYearSubjectBinding =  FragmentThirdYearSubjectBinding.inflate(inflater, container, false);

        thirdYearSubjectBinding.bbsThirdSubRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<SubjectModel> options =
                new FirebaseRecyclerOptions.Builder<SubjectModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("subDetails").
                                child("BBS").child("Third_Year").child("SubjectList"), SubjectModel.class)
                        .build();

        thirdYearSubjectAdapter = new ThirdYearSubjectAdapter(options,Role);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        thirdYearSubjectBinding.bbsThirdSubRecycler.addItemDecoration(dividerItemDecoration);
        thirdYearSubjectBinding.bbsThirdSubRecycler.setAdapter(thirdYearSubjectAdapter);

        if(Role.equals("TeacherStudent")){
            thirdYearSubjectBinding.bbsThirdSub.setVisibility(View.GONE);
        }

        thirdYearSubjectBinding.bbsThirdSub.setOnClickListener(new View.OnClickListener() {
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

                        Map<String,Object> subDetails = new HashMap<>();
                        subDetails.put("SubjectName",Name);
                        subDetails.put("Teacher",Teacher);
                        subDetails.put("SubjectCode",code);

                        FirebaseDatabase.getInstance().getReference().child("subDetails").
                                child("BBS").child("Third_Year").child("SubjectList")
                                .push()
                                .setValue(subDetails)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getContext(), "Subject Added !", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(), "Failed !", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        return thirdYearSubjectBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        thirdYearSubjectAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        thirdYearSubjectAdapter.stopListening();
    }
}