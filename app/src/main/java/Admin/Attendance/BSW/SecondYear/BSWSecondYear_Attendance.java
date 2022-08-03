package Admin.Attendance.BSW.SecondYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBSWSecondYearAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BSW.FourthYear.BSWFourthYearAttendanceAdapter;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BSWSecondYear_Attendance extends Fragment {

    FragmentBSWSecondYearAttendanceBinding attendanceBinding;
    BSWSecondYearAttendanceAdapter StudentAdapter;
    String Role;

    public BSWSecondYear_Attendance(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        attendanceBinding =  FragmentBSWSecondYearAttendanceBinding.inflate(inflater, container, false);
        attendanceBinding.bswSecondYear.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BSW").child("Second_Year").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new BSWSecondYearAttendanceAdapter(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.bswSecondYear.addItemDecoration(dividerItemDecoration);
        attendanceBinding.bswSecondYear.setAdapter(StudentAdapter);
        return attendanceBinding.getRoot();
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