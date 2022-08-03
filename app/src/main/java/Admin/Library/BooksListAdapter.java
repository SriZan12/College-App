package Admin.Library;

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
import com.example.relianceinternationalcollege.databinding.LibrarybooksListBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BooksListAdapter extends FirebaseRecyclerAdapter<BookModel, BooksListAdapter.BooksListViewHolder> {

    DatabaseReference databaseReference;
    String IsBookAvailable;
    String Role;

    public BooksListAdapter(@NonNull FirebaseRecyclerOptions<BookModel> options, String role) {
        super(options);
        this.Role = role;
    }

    @Override
    protected void onBindViewHolder(@NonNull BooksListViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull BookModel model) {
        holder.librarybooksListBinding.setBook(model);

        if(!Objects.equals(Role,"TeacherStudent")) {
            holder.librarybooksListBinding.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Dialog dialog = new Dialog(v.getContext());
                    dialog.getWindow().setContentView(R.layout.add_book);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();

                    EditText BookName = dialog.findViewById(R.id.BookName);
                    EditText BookAuthor = dialog.findViewById(R.id.BookAuthor);
                    EditText BookCategory = dialog.findViewById(R.id.BookCategory);
                    Button doneButton = dialog.findViewById(R.id.bookDone);

                    BookName.setText(model.getBookName());
                    BookAuthor.setText(model.getBookAuthor());
                    BookCategory.setText(model.getBookCategory());

                    doneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Book = BookName.getText().toString();
                            String BookLowercase = BookName.getText().toString().toLowerCase();
                            String Author = BookAuthor.getText().toString();
                            String Category = BookCategory.getText().toString();

                            Map<String, Object> bookMap = new HashMap<>();
                            bookMap.put("bookName", Book);
                            bookMap.put("bookAuthor", Author);
                            bookMap.put("bookCategory", Category);
                            bookMap.put("bookLowerCase", BookLowercase);

                            FirebaseDatabase.getInstance().getReference().child("Books").child(getRef(position).getKey())
                                    .updateChildren(bookMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(v.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(v.getContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });
                        }
                    });

                    return false;
                }
            });

            holder.librarybooksListBinding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase.getInstance().getReference().child("Books").child(getRef(position).getKey())
                            .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(v.getContext(), "Can't Delete", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }

        try {
            databaseReference = FirebaseDatabase.getInstance().getReference("BookStatus");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (int i = 0; i < getSnapshots().size(); i++) {
                        IsBookAvailable = String.valueOf(snapshot.child(getRef(position).getKey()).getValue());

                        try {
                            holder.librarybooksListBinding.isAvailable.setChecked(IsBookAvailable.equals("1"));
                            holder.librarybooksListBinding.notify();
                        }catch (Exception e){
                           e.getMessage();
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){
            e.getMessage();
        }

        holder.librarybooksListBinding.isAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = model.getBookName();
                if(holder.librarybooksListBinding.isAvailable.isChecked()){
                    databaseReference.child(getRef(position).getKey()).setValue("1");

                }else{
                    databaseReference.child(getRef(position).getKey()).setValue("0");
                }
            }
        });
    }

    @NonNull
    @Override
    public BooksListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LibrarybooksListBinding librarybooksListBinding = LibrarybooksListBinding.inflate(inflater);
        return new BooksListViewHolder(librarybooksListBinding,Role);
    }

    public static class BooksListViewHolder extends RecyclerView.ViewHolder{
        LibrarybooksListBinding librarybooksListBinding;
        String Role;

        public BooksListViewHolder(LibrarybooksListBinding listBinding, String role) {
            super(listBinding.getRoot());
            this.Role = role;
            this.librarybooksListBinding = listBinding;

            if(Role.equals("TeacherStudent")){
                librarybooksListBinding.delete.setVisibility(View.GONE);
            }
        }
    }
}
