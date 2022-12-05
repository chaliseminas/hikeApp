package np.com.chalise.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class AddObservationActivity extends AppCompatActivity {

    ImageView imageView;

    EditText observation, timeObs, additionalComment;
    AppCompatButton saveBtn;
    DbHelper dbHelper;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_observation);

        dbHelper = new DbHelper(this);

        imageView = findViewById(R.id.obsBack);
        observation = findViewById(R.id.observation);
        timeObs = findViewById(R.id.observationTime);
        additionalComment = findViewById(R.id.additionalComment);
        saveBtn = findViewById(R.id.obSave);

        id = getIntent().getStringExtra("id");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {
        String obs = observation.getText().toString();
        String timeObservation = timeObs.getText().toString();
        String addComment = additionalComment.getText().toString();

        if (obs.isEmpty()) {
            observation.setError("Please enter Observation.");
        } else if (timeObservation.isEmpty()) {
            timeObs.setError("Please enter Times of Observation.");
        } else {
            try {
                dbHelper.addObservation(id, obs, timeObservation, addComment);
                Toast.makeText(this, "Observation added successfully.", Toast.LENGTH_SHORT).show();
                emptyFields();
            } catch (Exception e) {
                Toast.makeText(this, "Something went wrong, please try again later.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void emptyFields() {
        observation.getText().clear();
        timeObs.getText().clear();
        additionalComment.getText().clear();
    }
}