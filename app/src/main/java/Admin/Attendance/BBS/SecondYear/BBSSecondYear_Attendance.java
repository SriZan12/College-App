package Admin.Attendance.BBS.SecondYear;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBBSSecondYearAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BBS.FourthYear.BBSFourthYearAttendanceAdapter;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BBSSecondYear_Attendance extends Fragment {

    FragmentBBSSecondYearAttendanceBinding attendanceBinding;
    BBSSecondYearAttendanceAdapter StudentAdapter;
    String Role;

    public BBSSecondYear_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        attendanceBinding =  FragmentBBSSecondYearAttendanceBinding.inflate(inflater, container, false);
        attendanceBinding.bbsSecondYearRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BBS").child("Second_Year").child("SecondYearStudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new BBSSecondYearAttendanceAdapter(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.bbsSecondYearRecycler.addItemDecoration(dividerItemDecoration);
        attendanceBinding.bbsSecondYearRecycler.setAdapter(StudentAdapter);
        StudentAdapter.notifyDataSetChanged();
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