package de.lmu.ifi.session1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tierd.pemsession1.R;

public class MainActivity extends AppCompatActivity {

    EditText firstNameEditText;
    EditText lastNameEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();

                // show a toast message with the first and last name
                // see https://developer.android.com/guide/topics/ui/notifiers/toasts.html
                Toast toast = Toast.makeText(MainActivity.this, "Vorname: " + firstName + ", Nachname: " + lastName, Toast.LENGTH_LONG);
                toast.show();

                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);

                startActivity(intent);
            }
        });


    }
}
