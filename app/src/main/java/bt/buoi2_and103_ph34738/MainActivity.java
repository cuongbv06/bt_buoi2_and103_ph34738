package bt.buoi2_and103_ph34738;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<City> cityList;
    private CityAdapter cityAdapter;

    private FirebaseFirestore db;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        addButton = findViewById(R.id.addButton);

        cityList = new ArrayList<>();
        cityAdapter = new CityAdapter(this, cityList);
        listView.setAdapter(cityAdapter);

        db = FirebaseFirestore.getInstance();
        loadDataFromFirestore();

        addButton.setOnClickListener(v -> {
            // Open AddCityActivity when the "Add" button is clicked
            Intent intent = new Intent(MainActivity.this, AddCityActivity.class);
            startActivity(intent);
        });
    }

    private void loadDataFromFirestore() {
        db.collection("cities")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        cityList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            City city = document.toObject(City.class);
                            cityList.add(city);
                        }
                        cityAdapter.notifyDataSetChanged();
                    } else {
                        Log.e("Firestore", "Error getting documents: ", task.getException());
                    }
                });
    }
}