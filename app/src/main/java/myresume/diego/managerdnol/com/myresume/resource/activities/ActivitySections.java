package myresume.diego.managerdnol.com.myresume.resource.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import myresume.diego.managerdnol.com.myresume.R;
import myresume.diego.managerdnol.com.myresume.resource.util.listener.ParseLoadListener;
import myresume.diego.managerdnol.com.myresume.resource.util.adapter.AdapterRecyclerViewSections;
import myresume.diego.managerdnol.com.myresume.resource.util.asynctask.InfoLoadingTask;
import myresume.diego.managerdnol.com.myresume.resource.util.constants.Constants;

public class ActivitySections extends AppCompatActivity {

    private RecyclerView recyclerViewSections;
    private AdapterRecyclerViewSections adapterRecyclerViewSections;
    private InfoLoadingTask asyncTask;
    private ParseLoadListener parseLoadListener;
    private Context context;
    private int intentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);
        recyclerViewSections = findViewById(R.id.recyclerSections);
        recyclerViewSections.setLayoutManager(new LinearLayoutManager(this));
        context = getApplicationContext();
        asyncTask = new InfoLoadingTask(this);

        parseLoadListener = new ParseLoadListener() {
            @Override
            public void processFinish(Object output) {
                adapterRecyclerViewSections = new AdapterRecyclerViewSections((ArrayList<Object>) output,context);
                recyclerViewSections.setAdapter(adapterRecyclerViewSections);
            }

        };

        intentData = getIntent().getExtras().getInt(Constants.EXTRAS_SECTION);
        asyncTask.parseLoadListener = parseLoadListener;
        asyncTask.execute(intentData);


    }
}
