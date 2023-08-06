package algonquin.cst2335.afinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_NAME = "UserPreferences";
    private static final String KEY_WIDTH = "width";
    private static final String KEY_HEIGHT = "height";

    private EditText etWidth;
    private EditText etHeight;
    private Button btnGenerate;
    private Button btnShowSavedImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWidth = findViewById(R.id.etWidth);
        etHeight = findViewById(R.id.etHeight);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnShowSavedImages = findViewById(R.id.btnShowSavedImages);

        // Set input filters to allow only positive whole numbers (integers)
        etWidth.setFilters(new InputFilter[]{new PositiveNumberInputFilter()});
        etHeight.setFilters(new InputFilter[]{new PositiveNumberInputFilter()});

        // Add text watchers to clear leading zeros and negative numbers
        etWidth.addTextChangedListener(new PositiveNumberTextWatcher(etWidth));
        etHeight.addTextChangedListener(new PositiveNumberTextWatcher(etHeight));

        // Restore the user's input from SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        int savedWidth = preferences.getInt(KEY_WIDTH, 0);
        int savedHeight = preferences.getInt(KEY_HEIGHT, 0);

        // Set the saved values to the EditText fields
        etWidth.setText(String.valueOf(savedWidth));
        etHeight.setText(String.valueOf(savedHeight));

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateImage();
            }
        });

        btnShowSavedImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSavedImages();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Get the user's input from EditText fields
        String widthInput = etWidth.getText().toString();
        String heightInput = etHeight.getText().toString();

        // Convert the input to integers (if not empty)
        int width = TextUtils.isEmpty(widthInput) ? 0 : Integer.parseInt(widthInput);
        int height = TextUtils.isEmpty(heightInput) ? 0 : Integer.parseInt(heightInput);

        // Save the user's input in SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_WIDTH, width);
        editor.putInt(KEY_HEIGHT, height);
        editor.apply();
    }

    private void generateImage() {
        String widthInput = etWidth.getText().toString();
        String heightInput = etHeight.getText().toString();

        if (TextUtils.isEmpty(widthInput) || TextUtils.isEmpty(heightInput)) {
            Toast.makeText(this, "Please enter both width and height.", Toast.LENGTH_SHORT).show();
            return;
        }

        int width = Integer.parseInt(widthInput);
        int height = Integer.parseInt(heightInput);
        // Construct the URL for generating the image using the Placebear API
        String imageUrl = "https://placebear.com/" + width + "/" + height;
        // Create an intent to start the GeneratedImageActivity
        Intent intent = new Intent(MainActivity.this, GeneratedImageActivity.class);
        intent.putExtra("image_url", imageUrl); // Pass the image URL as an extra
        startActivity(intent);
    }


    private void showSavedImages() {
        // Implement the logic to show the list of saved images
        Toast.makeText(this, "Showing saved images...", Toast.LENGTH_SHORT).show();
    }

    // Inner class to allow only positive numbers in EditText
    private static class PositiveNumberInputFilter implements InputFilter {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            StringBuilder filteredStringBuilder = new StringBuilder();
            for (int i = start; i < end; i++) {
                char currentChar = source.charAt(i);
                if (Character.isDigit(currentChar)) {
                    filteredStringBuilder.append(currentChar);
                }
            }
            return filteredStringBuilder.toString();
        }
    }

    // Inner class to clear leading zeros and negative numbers
    private static class PositiveNumberTextWatcher implements TextWatcher {
        private EditText editText;

        public PositiveNumberTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String input = s.toString();
            if (input.matches("0") || input.matches("-0")) {
                s.clear(); // Clear leading zero
            } else {
                try {
                    int value = Integer.parseInt(input);
                    if (value < 0) {
                        s.clear(); // Clear negative numbers
                    }
                } catch (NumberFormatException e) {
                    s.clear(); // Clear non-numeric input
                }
            }
        }
    }
}
