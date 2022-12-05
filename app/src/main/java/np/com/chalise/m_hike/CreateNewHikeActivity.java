package np.com.chalise.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateNewHikeActivity extends AppCompatActivity {

    ImageView imageView;
    EditText etName, etLocation, etLength, etDifficulty, etParkingAvailable, etAccommodation, etLimitation, etDescription;
    TextView tvDate;
    AppCompatButton saveButton;
    SimpleDateFormat simpleDateFormat;
    private DatePickerDialog.OnDateSetListener dateSetLister;
    SQLiteDatabase sqLiteDatabaseObj;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_hike);

        dbHelper = new DbHelper(this);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        imageView = findViewById(R.id.createHikeBackBtn);
        etName = findViewById(R.id.hikeName);
        etLocation = findViewById(R.id.hikeLocation);
        etLength = findViewById(R.id.hikeLength);
        etDifficulty = findViewById(R.id.difficultyLevel);
        etParkingAvailable = findViewById(R.id.parkingAvailable);
        etAccommodation = findViewById(R.id.accommodationAvailable);
        etLimitation = findViewById(R.id.personLimitation);
        etDescription = findViewById(R.id.description);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvDate = findViewById(R.id.hikeDate);
        saveButton = findViewById(R.id.createHikeButton);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pickerDialog = new DatePickerDialog(
                        CreateNewHikeActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetLister,
                        year, month, day);
                pickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                pickerDialog.show();
            }
        });
        dateSetLister = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                tvDate.setText(date);
            }
        };

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateNewHikeActivity.this, MainActivity.class));
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

    }

    private void saveData() {

        String name = etName.getText().toString();
        String location = etLocation.getText().toString();
        String date = tvDate.getText().toString();
        String length = etLength.getText().toString();
        String parkingAvailable = etParkingAvailable.getText().toString();
        String difficultyLevel = etDifficulty.getText().toString();
        String accommodationAvailable = etAccommodation.getText().toString();
        String personLimit = etLimitation.getText().toString();
        String description = etDescription.getText().toString();

        if (name.isEmpty()) {
            etName.setError("Please enter Hike Name.");
        } else if (location.isEmpty()) {
            etLocation.setError("Please enter Hike Location.");
        } else if (date.isEmpty()) {
            tvDate.setError("Please select Hike date");
        } else if (parkingAvailable.isEmpty()) {
            etParkingAvailable.setError("Please enter Parking Available");
        } else if (length.isEmpty()) {
            etLength.setError("Please enter Hike Length.");
        } else if (difficultyLevel.isEmpty()) {
            etDifficulty.setError("Please enter Hike Difficulty.");
        } else if (accommodationAvailable.isEmpty()) {
            etAccommodation.setError("Please enter Accommodation Available.");
        } else if (personLimit.isEmpty()) {
            etLimitation.setError("Please enter Person limitation.");
        } else {
            try {
                dbHelper.addNewHike(name, location, date, parkingAvailable, length, difficultyLevel, description,
                        accommodationAvailable, personLimit);
                Toast.makeText(this, "Hike details added successfully.", Toast.LENGTH_SHORT).show();
                emptyFields();
            } catch (Exception e) {
                Toast.makeText(this, "Something went wrong, please try again later.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void emptyFields() {
        etName.getText().clear();
        etLocation.getText().clear();
        tvDate.setText("");
        etParkingAvailable.getText().clear();
        etLength.getText().clear();
        etDifficulty.getText().clear();
        etAccommodation.getText().clear();
        etLimitation.getText().clear();
        etDescription.getText().clear();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CreateNewHikeActivity.this, MainActivity.class));
        finish();
    }
}