package de.lmu.ifi.session1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tierd.pemsession1.R;

public class WelcomeActivity extends AppCompatActivity {

    TextView firstNameTextView, lastNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        firstNameTextView = findViewById(R.id.firstName);
        lastNameTextView = findViewById(R.id.lastName);

        Bundle extras = getIntent().getExtras();

        String firstName = extras.getString("firstName");
        String lastName = extras.getString("lastName");

        firstNameTextView.setText(firstName);
        lastNameTextView.setText(lastName);
    }
}
