package Admin.Attendance.BSW.FourthYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBSWFourthYearAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BSW.FirstYear.BSWFirstYearAttendanceAdapter;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BSWFourthYear_Attendance extends Fragment {

    FragmentBSWFourthYearAttendanceBinding attendanceBinding;
    BSWFourthYearAttendanceAdapter StudentAdapter;
    String Role;

    public BSWFourthYear_Attendance(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        attendanceBinding =  FragmentBSWFourthYearAttendanceBinding.inflate(inflater, container, false);
        attendanceBinding.bswFourthYear.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BSW").child("Fourth_Year").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new BSWFourthYearAttendanceAdapter(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.bswFourthYear.addItemDecoration(dividerItemDecoration);
        attendanceBinding.bswFourthYear.setAdapter(StudentAdapter);
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