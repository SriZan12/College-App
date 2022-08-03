package Admin.Library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ActivityLibraryBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class LibraryActivity extends AppCompatActivity {

    ActivityLibraryBinding libraryBinding;
    BooksListAdapter booksListAdapter;
    String Role;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        libraryBinding = ActivityLibraryBinding.inflate(getLayoutInflater());
        setContentView(libraryBinding.getRoot());

        Toast.makeText(this, "Welcome to The Library !", Toast.LENGTH_SHORT).show();
        Role = getIntent().getStringExtra("role");

        if(Role != null && Role.equals("TeacherStudent")){
            libraryBinding.addBooks.setVisibility(View.GONE);
        }

        libraryBinding.booksListRecycler.setLayoutManager(new LinearLayoutManager(LibraryActivity.this));

        FirebaseRecyclerOptions<BookModel> options =
                new FirebaseRecyclerOptions.Builder<BookModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child("Books"), BookModel.class)
                        .build();

         booksListAdapter = new BooksListAdapter(options,Role);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(LibraryActivity.this,
                DividerItemDecoration.VERTICAL);
        libraryBinding.booksListRecycler.addItemDecoration(dividerItemDecoration);
        libraryBinding.booksListRecycler.setAdapter(booksListAdapter);
        booksListAdapter.notifyDataSetChanged();

        libraryBinding.searchBooks.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchBook(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchBook(newText);
                return false;
            }
        });

        libraryBinding.addBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(LibraryActivity.this);
                dialog.getWindow().setContentView(R.layout.add_book);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                EditText BookName = dialog.findViewById(R.id.BookName);
                EditText BookAuthor = dialog.findViewById(R.id.BookAuthor);
                EditText BookCategory = dialog.findViewById(R.id.BookCategory);
                Button doneButton = dialog.findViewById(R.id.bookDone);

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Book = BookName.getText().toString();
                        String BookLowercase = BookName.getText().toString().toLowerCase();
                        String Author = BookAuthor.getText().toString();
                        String Category = BookCategory.getText().toString();

                        BookModel bookModel = new BookModel(Book,Author,Category,BookLowercase);

                        FirebaseDatabase.getInstance().getReference().child("Books").push()
                                .setValue(bookModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(LibraryActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LibraryActivity.this, "Books Add Failed !", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                    }
                });
            }
        });
    }

    private void searchBook(String query) {
        FirebaseRecyclerOptions<BookModel> options = new FirebaseRecyclerOptions.Builder<BookModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().
                        child("Books").orderByChild("bookLowerCase").startAt(query.toLowerCase()).endAt(query+"\uff8ff"), BookModel.class)
                .build();

        booksListAdapter = new BooksListAdapter(options, Role);
        booksListAdapter.startListening();
        libraryBinding.booksListRecycler.setAdapter(booksListAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        booksListAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        booksListAdapter.stopListening();
    }
}