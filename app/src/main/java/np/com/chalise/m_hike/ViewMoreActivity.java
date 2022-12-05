package np.com.chalise.m_hike;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class ViewMoreActivity extends AppCompatActivity {
    
    DbHelper dbHelper;
    String id;
    public TextView date, name, location, parking, length, difficulty, description, accommodation, limitation;

    ImageView imageView;
    MaterialButton addObservation, viewObservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);
        
        dbHelper = new DbHelper(this);
        id = getIntent().getStringExtra("id");

        date = findViewById(R.id.hikeItemDate);
        name = findViewById(R.id.hikeTitle);
        location = findViewById(R.id.hikeItemLocation);
        parking = findViewById(R.id.hikeItemParking);
        length = findViewById(R.id.hikeItemLength);
        difficulty = findViewById(R.id.hikeItemDifficulty);
        description = findViewById(R.id.hikeItemDescription);
        accommodation = findViewById(R.id.hikeItemAccommodation);
        limitation = findViewById(R.id.hikeItemLimitation);
        imageView = findViewById(R.id.viewMoreBackBtn);

        addObservation = findViewById(R.id.addObservation);
        viewObservation = findViewById(R.id.viewObservation);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewMoreActivity.this, AddObservationActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        viewObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewMoreActivity.this, ViewObservationActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        name.setText(getIntent().getStringExtra("name"));
        date.setText(getIntent().getStringExtra("date"));
        location.setText(getIntent().getStringExtra("location"));
        parking.setText(getIntent().getStringExtra("parking"));
        length.setText(getIntent().getStringExtra("length"));
        difficulty.setText(getIntent().getStringExtra("difficulty"));
        description.setText(getIntent().getStringExtra("description"));
        accommodation.setText(getIntent().getStringExtra("accommodation"));
        limitation.setText(getIntent().getStringExtra("limitation"));

    }
}