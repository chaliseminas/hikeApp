package np.com.chalise.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewObservationActivity extends AppCompatActivity {

    DbHelper dbHelper;

    ObservationAdapter adapter;
    RecyclerView recyclerView;
    List<Observations> observations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_observation);

        dbHelper = new DbHelper(this);
        recyclerView = findViewById(R.id.observationList);

        observations = dbHelper.getAllObservation(getIntent().getStringExtra("id"));

        adapter = new ObservationAdapter(observations);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}