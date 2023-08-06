package algonquin.cst2335.afinal;




import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class GeneratedImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_image);
        ImageView generatedImageView = findViewById(R.id.generatedImageView);
        Button backButton = findViewById(R.id.backButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button showButton = findViewById(R.id.showButton);

        if (getIntent() != null && getIntent().hasExtra("image_url")) {
            String imageUrl = getIntent().getStringExtra("image_url");

            // Use Picasso to load the image into the ImageView
            Picasso.get().load(imageUrl).into(generatedImageView);
        }

        // Handle the "Back" button click to go back to MainActivity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the GeneratedImageActivity and go back to MainActivity
            }
        });

        // Handle the "Save" button click to save the generated image
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the logic to save the generated image
                // Save the image to the local database or external storage
            }
        });

        // Handle the "Show" button click to show the list of saved images
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the logic to show the list of saved images
            }
        });
    }
}