package de.lmu.ifi.session3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timo.session3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirebaseActivity extends AppCompatActivity {

    @BindView(R.id.firstNameEditText)
    EditText firstNameEditText;
    @BindView(R.id.lastNameEditText)
    EditText lastNameEditText;
    @BindView(R.id.dbFirstname)
    TextView dbFirstnameTextView;
    @BindView(R.id.dbLastname)
    TextView dbLastnameTextView;

    FirebaseAuth mAuth;
    DatabaseReference userRef;
    ValueEventListener userListener;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getUid();
        userRef = FirebaseDatabase.getInstance().getReference("user");

        userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    dbFirstnameTextView.setText(user.getFirstName());
                    dbLastnameTextView.setText(user.getLastName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        userRef.child(userId).addValueEventListener(userListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        userRef.child(userId).removeEventListener(userListener);
    }

    @OnClick(R.id.storeButton)
    public void storeInDb() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        User user = new User(firstName, lastName);
        userRef.child(userId).setValue(user);
    }

    @OnClick(R.id.logoutButton)
    public void logout() {
        mAuth.signOut();
        Intent intent = new Intent(FirebaseActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
