package Admin.Attendance.BCA.ThirdSem;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBCAThirdSemAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BCA.SixthSem.SixthSemAdapterAttendance;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BCAThirdSem_Attendance extends Fragment {

    FragmentBCAThirdSemAttendanceBinding attendanceBinding;
    ThirdSemAdapterAttendance StudentAdapter;
    String Role;

    public BCAThirdSem_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        attendanceBinding =  FragmentBCAThirdSemAttendanceBinding.inflate(inflater, container, false);

        attendanceBinding.ThirdSemAttendance.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BCA").child("Third_Sem").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new ThirdSemAdapterAttendance(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.ThirdSemAttendance.addItemDecoration(dividerItemDecoration);
        attendanceBinding.ThirdSemAttendance.setAdapter(StudentAdapter);
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