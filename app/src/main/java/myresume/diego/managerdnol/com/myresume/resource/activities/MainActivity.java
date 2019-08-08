package myresume.diego.managerdnol.com.myresume.resource.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

import myresume.diego.managerdnol.com.myresume.R;
import myresume.diego.managerdnol.com.myresume.resource.util.adapter.AdapterRecyclerViewMainActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView gridRecyclerView;
    private AdapterRecyclerViewMainActivity adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridRecyclerView = findViewById(R.id.gridRecyclerView);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new AdapterRecyclerViewMainActivity(this,
                getResources().obtainTypedArray(R.array.imagesMainActivity),
                Arrays.asList(getResources().getStringArray(R.array.descriptionMainActivity)));
        gridRecyclerView.setAdapter(adapter);
    }
}
