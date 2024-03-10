package bt.buoi2_and103_ph34738;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddCityActivity extends AppCompatActivity {
    private EditText cityNameEditText;
    private Button addButton;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        cityNameEditText = findViewById(R.id.cityNameEditText);
        addButton = findViewById(R.id.addButton);

        db = FirebaseFirestore.getInstance();

//        addButton.setOnClickListener(v -> addCityToFirestore());
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCityToFirestore();
                startActivity(new Intent(AddCityActivity.this, MainActivity.class));
            }
        });
    }

    private void addCityToFirestore() {
        String cityName = cityNameEditText.getText().toString().trim();

        if (!TextUtils.isEmpty(cityName)) {
            City city = new City(cityName);

            db.collection("cities")
                    .add(city)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(AddCityActivity.this, "City added successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Firestore", "Error adding document", e);
                        Toast.makeText(AddCityActivity.this, "Failed to add city", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show();
        }
    }


}