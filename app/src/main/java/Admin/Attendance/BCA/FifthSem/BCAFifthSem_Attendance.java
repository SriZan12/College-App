package Admin.Attendance.BCA.FifthSem;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBCAFifthSemAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BCA.EightSem.EightSemAdapterAttendance;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BCAFifthSem_Attendance extends Fragment {

    FragmentBCAFifthSemAttendanceBinding attendanceBinding;
    FifthSemAdapterAttendance StudentAdapter;
    String Role;

    public BCAFifthSem_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        attendanceBinding =  FragmentBCAFifthSemAttendanceBinding.inflate(inflater, container, false);

        attendanceBinding.fifthSemAttendance.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BCA").child("Fifth_Sem").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new FifthSemAdapterAttendance(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.fifthSemAttendance.addItemDecoration(dividerItemDecoration);
        attendanceBinding.fifthSemAttendance.setAdapter(StudentAdapter);
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