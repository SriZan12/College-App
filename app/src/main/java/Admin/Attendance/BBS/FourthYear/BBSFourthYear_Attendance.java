package Admin.Attendance.BBS.FourthYear;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBBSFourthYearAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BBS.FirstYear.BBSFirstYearAttendanceAdapter;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BBSFourthYear_Attendance extends Fragment {

    FragmentBBSFourthYearAttendanceBinding attendanceBinding;
    BBSFourthYearAttendanceAdapter StudentAdapter;
    String Role;

    public BBSFourthYear_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        attendanceBinding =  FragmentBBSFourthYearAttendanceBinding.inflate(inflater, container, false);
        attendanceBinding.bbsFourthYearRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BBS").child("Fourth_Year").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new BBSFourthYearAttendanceAdapter(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.bbsFourthYearRecycler.addItemDecoration(dividerItemDecoration);
        attendanceBinding.bbsFourthYearRecycler.setAdapter(StudentAdapter);
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