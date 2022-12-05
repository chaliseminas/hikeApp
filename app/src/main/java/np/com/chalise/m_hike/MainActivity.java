package np.com.chalise.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MaterialButton btnCreate, createNewHikeButton;
    DbHelper db;
    List<HikeDataModel> hikeDataModelList;
    RelativeLayout relativeLayout;
    RecyclerView recyclerView;
    SearchView searchBar;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbHelper(this);

        btnCreate = findViewById(R.id.createHikeButton);
        relativeLayout = findViewById(R.id.noHikeListLayout);
        recyclerView = findViewById(R.id.hikesList);
        createNewHikeButton = findViewById(R.id.createNewHikeButton);
        searchBar = findViewById(R.id.searchBar);

        hikeDataModelList = db.getAllContacts();
        adapter = new MyAdapter(hikeDataModelList, MainActivity.this);

        if (hikeDataModelList.isEmpty()) {
            relativeLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            searchBar.setVisibility(View.GONE);
            createNewHikeButton.setVisibility(View.GONE);
        } else {
            relativeLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            searchBar.setVisibility(View.VISIBLE);
            createNewHikeButton.setVisibility(View.VISIBLE);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateNewHikeActivity.class));
                finish();
            }
        });

        createNewHikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateNewHikeActivity.class));
                finish();
            }
        });

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    void filter(String text){
        List<HikeDataModel> temp = new ArrayList();
        for(HikeDataModel d: hikeDataModelList){
            if(d.getName().contains(text)){
                temp.add(d);
            }
        }
        adapter.updateList(temp);
    }
}