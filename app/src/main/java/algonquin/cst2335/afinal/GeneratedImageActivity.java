package algonquin.cst2335.afinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        if (getIntent() != null && getIntent().hasExtra("image_info")) {
            ImageInfo imageInfo = getIntent().getParcelableExtra("image_info");

            // Use the imageInfo properties as needed
            String imageUrl = imageInfo.getUrl();
            int width = imageInfo.getWidth();
            int height = imageInfo.getHeight();
            String path = imageInfo.getPath();

            // Load the image using Picasso or Glide
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
                showSaveConfirmationDialog();
            }
        });

        // Handle the "Show" button click to show the list of saved images
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement the logic to show the list of saved images
            }
        });
    }

    private void showSaveConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save Image");
        builder.setMessage("Do you want to save the image?");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Save the image here
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
