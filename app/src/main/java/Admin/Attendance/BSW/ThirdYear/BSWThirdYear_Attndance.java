package Admin.Attendance.BSW.ThirdYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBSWThirdYearAttndanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BSW.SecondYear.BSWSecondYearAttendanceAdapter;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BSWThirdYear_Attndance extends Fragment {

    FragmentBSWThirdYearAttndanceBinding attndanceBinding;
    BSWThirdYearAttendanceAdapter StudentAdapter;
    String Role;

    public BSWThirdYear_Attndance(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        attndanceBinding =  FragmentBSWThirdYearAttndanceBinding.inflate(inflater, container, false);
        attndanceBinding.bswThirdYear.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BSW").child("Third_Year").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new BSWThirdYearAttendanceAdapter(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attndanceBinding.bswThirdYear.addItemDecoration(dividerItemDecoration);
        attndanceBinding.bswThirdYear.setAdapter(StudentAdapter);
        return attndanceBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        StudentAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        StudentAdapter.stopListening();
    }
}