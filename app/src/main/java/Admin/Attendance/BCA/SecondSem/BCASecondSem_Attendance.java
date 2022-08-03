package Admin.Attendance.BCA.SecondSem;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBCASecondSemAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BCA.FourthSem.FourthSemAdapterAttendance;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BCASecondSem_Attendance extends Fragment {

    FragmentBCASecondSemAttendanceBinding attendanceBinding;
    SecondSemAdapterAttendance StudentAdapter;
    String Role;

    public BCASecondSem_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        attendanceBinding =  FragmentBCASecondSemAttendanceBinding.inflate(inflater, container, false);
        attendanceBinding.SecondSemAttendance.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BCA").child("Second_Sem").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new SecondSemAdapterAttendance(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.SecondSemAttendance.addItemDecoration(dividerItemDecoration);
        attendanceBinding.SecondSemAttendance.setAdapter(StudentAdapter);
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