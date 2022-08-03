package Admin.Fees.BCAFees.SixthSem;

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
import com.example.relianceinternationalcollege.databinding.ShowFeesBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Admin.Fees.FeesModel;

public class BCASixthSemFeesAdapter extends FirebaseRecyclerAdapter<FeesModel, BCASixthSemFeesAdapter.BCASixthSemFeesViewHolder> {

    String Role;

    public BCASixthSemFeesAdapter(@NonNull FirebaseRecyclerOptions<FeesModel> options, String role) {
        super(options);
        this.Role = role;
    }

    @Override
    protected void onBindViewHolder(@NonNull BCASixthSemFeesViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull FeesModel model) {
        holder.feesBinding.setFees(model);

        if(!Objects.equals(Role,"TeacherStudent")) {

            holder.feesBinding.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.getWindow().setContentView(R.layout.fees_dialog);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();

                    EditText StudentName = dialog.findViewById(R.id.studentName);
                    EditText TotalFees = dialog.findViewById(R.id.TotalFees);
                    EditText TotalPaid = dialog.findViewById(R.id.TotalPaid);
                    EditText OutStanding = dialog.findViewById(R.id.outStandingFees);
                    Button doneButton = dialog.findViewById(R.id.FeesDone);

                    StudentName.setText(model.getStudentName());
                    TotalFees.setText(model.getTotalFees());
                    TotalPaid.setText(model.getTotalPaid());
                    OutStanding.setText(model.getOutstanding());

                    doneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Student = StudentName.getText().toString();
                            String Total = TotalFees.getText().toString();
                            String Paid = TotalPaid.getText().toString();
                            String Dues = OutStanding.getText().toString();

                            Map<String, Object> feesMap = new HashMap<>();
                            feesMap.put("studentName", Student);
                            feesMap.put("totalFees", Total);
                            feesMap.put("totalPaid", Paid);
                            feesMap.put("outstanding", Dues);

                            FirebaseDatabase.getInstance().getReference().child("Fees")
                                    .child("BCAFees").child("Sixth_Sem").child(getRef(position).getKey())
                                    .updateChildren(feesMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(v.getContext(), "Fees Updated", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(v.getContext(), "Fees Update Failed!", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });

                        }
                    });
                    return false;
                }
            });

            holder.feesBinding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase.getInstance().getReference().child("Fees")
                            .child("BCAFees").child("Sixth_Sem").child(getRef(position).getKey())
                            .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(v.getContext(), "Fees Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(v.getContext(), "Fees Delete Failed !", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }
    }

    @NonNull
    @Override
    public BCASixthSemFeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ShowFeesBinding showFeesBinding = ShowFeesBinding.inflate(inflater);
        return new BCASixthSemFeesViewHolder(showFeesBinding,Role);
    }

    public static class BCASixthSemFeesViewHolder extends RecyclerView.ViewHolder{
        ShowFeesBinding feesBinding;
        String Role;
        public BCASixthSemFeesViewHolder(ShowFeesBinding showFeesBinding, String role) {
            super(showFeesBinding.getRoot());
            this.feesBinding = showFeesBinding;
            this.Role = role;

            if(Role.equals("TeacherStudent")){
                feesBinding.delete.setVisibility(View.GONE);
            }
        }
    }

}
