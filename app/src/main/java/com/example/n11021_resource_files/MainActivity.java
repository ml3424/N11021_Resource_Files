package com.example.n11021_resource_files;

/**
 * @author	Maya Leibovich mayaLeibovich3@gmail.com
 * @version	 1.2
 * @since	02/03/2025
 * Resource files practice
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private final String FILENAME = "textfile.txt";
    TextView tVText;
    EditText eTText;
    Button btnText, btnFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tVText = findViewById(R.id.tVText);
        eTText = findViewById(R.id.eTText);

        btnText = findViewById(R.id.btnText);
        btnFile = findViewById(R.id.btnFile);

    }

    /**
     * onclick that reads from file and presents it on the text view
     * @param	view View
     * @return	none
     */
    public void clickRawFile(View view) {
        String fileName = FILENAME.substring(0, FILENAME.length() - 4);
        int resourceId = this.getResources().getIdentifier(fileName, "raw", this.getPackageName());

        try {
            InputStream iS = this.getResources().openRawResource(resourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null)
            {
                sB.append(line + '\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            tVText.setText(sB.toString());
        }
        catch (IOException e) {
            Log.e("clickRawFile", "Error reading file: " + fileName, e);
        }
    }

    /**
     * onclick that reads from edit text and presents it on the text view
     * @param	view View
     * @return	none
     */
    public void clickText(View view) {
        tVText.setText(eTText.getText().toString());
    }

    /**
     * creats menu
     * @param	menu Menu
     * @return	none
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * handles menu item selection
     * @param	item MenuItem
     * @return	none
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        String st = item.getTitle().toString();
        if(st.equals("Credits"))
        {
            Intent creditesIntent = new Intent(this, Credits.class);
            startActivity(creditesIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}