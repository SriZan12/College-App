package Admin.Fees.BSWFees.FourthYear;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBSWFeesFourthYearBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Fees.FeesModel;


public class BSWFeesFourthYear extends Fragment {

    FragmentBSWFeesFourthYearBinding feesFourthYearBinding;
    BSWFourthYearFeesAdapter feesAdapter;
    String Role;

    public BSWFeesFourthYear(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        feesFourthYearBinding =  FragmentBSWFeesFourthYearBinding.inflate(inflater, container, false);
        feesFourthYearBinding.bswFourthYearFees.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<FeesModel> options =
                new FirebaseRecyclerOptions.Builder<FeesModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Fees")
                                .child("BSWFees").child("Fourth_Year"), FeesModel.class)
                        .build();

        feesAdapter = new BSWFourthYearFeesAdapter(options,Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        feesFourthYearBinding.bswFourthYearFees.addItemDecoration(dividerItemDecoration);
        feesFourthYearBinding.bswFourthYearFees.setAdapter(feesAdapter);

        feesFourthYearBinding.searchStudent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchNote(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchNote(newText);
                return false;
            }
        });

        feesFourthYearBinding.bswAddFourthYearFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.getWindow().setContentView(R.layout.fees_dialog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                EditText StudentName = dialog.findViewById(R.id.studentName);
                EditText TotalFees = dialog.findViewById(R.id.TotalFees);
                EditText TotalPaid = dialog.findViewById(R.id.TotalPaid);
                EditText OutStanding = dialog.findViewById(R.id.outStandingFees);
                Button doneButton = dialog.findViewById(R.id.FeesDone);

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Student = StudentName.getText().toString();
                        String Total = TotalFees.getText().toString();
                        String Paid = TotalPaid.getText().toString();
                        String Dues = OutStanding.getText().toString();
                        String lowercaseStudent = StudentName.getText().toString().toLowerCase();

                        FeesModel feesModel = new FeesModel(Student,Total,Paid,Dues,lowercaseStudent);

                        FirebaseDatabase.getInstance().getReference().child("Fees")
                                .child("BSWFees").child("Fourth_Year").push().setValue(feesModel)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getContext(), "Fees Added !", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(), "Fees Addition Failed !", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                    }
                });


            }
        });
        return feesFourthYearBinding.getRoot();
    }

    private void searchNote(String query) {
        FirebaseRecyclerOptions<FeesModel> options = new FirebaseRecyclerOptions.Builder<FeesModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Fees").
                        child("BSWFees").child("Fourth_Year").orderByChild("lowercaseStudentName").startAt(query.toLowerCase()).endAt(query+"\uff8ff"), FeesModel.class)
                .build();

        feesAdapter = new BSWFourthYearFeesAdapter(options, Role);
        feesAdapter.startListening();
        feesFourthYearBinding.bswFourthYearFees.setAdapter(feesAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        feesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        feesAdapter.stopListening();
    }
}