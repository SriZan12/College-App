package Admin.Attendance.BBS.ThirdYear;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBBSThirdYearAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BBS.SecondYear.BBSSecondYearAttendanceAdapter;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BBSThirdYear_Attendance extends Fragment {

   FragmentBBSThirdYearAttendanceBinding attendanceBinding;
   BBSThirdYearAttendanceAdapter StudentAdapter;
   String Role;

    public BBSThirdYear_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        attendanceBinding =  FragmentBBSThirdYearAttendanceBinding.inflate(inflater, container, false);
        attendanceBinding.bbsThirdYearRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BBS").child("Third_Year").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new BBSThirdYearAttendanceAdapter(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.bbsThirdYearRecycler.addItemDecoration(dividerItemDecoration);
        attendanceBinding.bbsThirdYearRecycler.setAdapter(StudentAdapter);
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