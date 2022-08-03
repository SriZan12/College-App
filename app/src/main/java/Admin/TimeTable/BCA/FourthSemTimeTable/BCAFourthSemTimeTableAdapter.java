package Admin.TimeTable.BCA.FourthSemTimeTable;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.TimetableDesignBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Admin.TimeTable.TimeTableModel;

public class BCAFourthSemTimeTableAdapter extends FirebaseRecyclerAdapter<TimeTableModel, BCAFourthSemTimeTableAdapter.BCAFourthSemTimeTableViewHolder> {

    String Role;

    public BCAFourthSemTimeTableAdapter(@NonNull FirebaseRecyclerOptions<TimeTableModel> options, String role) {
        super(options);
        this.Role = role;
    }

    @Override
    protected void onBindViewHolder(@NonNull BCAFourthSemTimeTableViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull TimeTableModel model) {
        holder.timetableDesignBinding.setTimeTable(model);

        if(!Objects.equals(Role,"TeacherStudent")) {


            holder.timetableDesignBinding.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.getWindow().setContentView(R.layout.add_timetable);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();

                    EditText SubjectName = dialog.findViewById(R.id.TimeSubjectName);
                    EditText time = dialog.findViewById(R.id.time);
                    EditText period = dialog.findViewById(R.id.Period);
                    EditText teacher = dialog.findViewById(R.id.teacher);
                    Button doneButton = dialog.findViewById(R.id.TimeTableDone);

                    SubjectName.setText(model.getSubject());
                    time.setText(model.getTime());
                    period.setText(model.getPeriod());
                    teacher.setText(model.getTeacher());

                    doneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String subject = SubjectName.getText().toString();
                            String Time = time.getText().toString();
                            String Period = period.getText().toString();
                            String Teacher = teacher.getText().toString();

                            Map<String, Object> timeTableMap = new HashMap<>();
                            timeTableMap.put("subject", subject);
                            timeTableMap.put("time", Time);
                            timeTableMap.put("period", Period);
                            timeTableMap.put("teacher", Teacher);

                            FirebaseDatabase.getInstance().getReference().child("TimeTable")
                                    .child("BCATimeTable").child("Fourth_Sem").child(getRef(position).getKey())
                                    .updateChildren(timeTableMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(v.getContext(), "Time Table Updated", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(v.getContext(), "Time Table Update Failed!", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });

                        }
                    });
                    return false;
                }
            });

            holder.timetableDesignBinding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase.getInstance().getReference().child("TimeTable")
                            .child("BCATimeTable").child("Fourth_Sem").child(getRef(position).getKey())
                            .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(v.getContext(), "Time Table Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(v.getContext(), "Time Table Delete Failed !", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }
    }

    @NonNull
    @Override
    public BCAFourthSemTimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TimetableDesignBinding timetableDesignBinding = TimetableDesignBinding.inflate(inflater);
        return new BCAFourthSemTimeTableViewHolder(timetableDesignBinding,Role);
    }

    public static class BCAFourthSemTimeTableViewHolder extends RecyclerView.ViewHolder{
        TimetableDesignBinding timetableDesignBinding;
        String Role;
        public BCAFourthSemTimeTableViewHolder(TimetableDesignBinding timeTable, String role) {
            super(timeTable.getRoot());
            this.timetableDesignBinding = timeTable;
            this.Role = role;

            if(Role.equals("TeacherStudent")){
                timetableDesignBinding.delete.setVisibility(View.GONE);
            }
        }
    }
}
