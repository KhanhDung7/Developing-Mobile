package com.example.modern_art_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //create variable
    static private final String URL = "http://www.MoMA.org";
    SeekBar seekBar = null;
    TextView boxTop_column1;
    TextView boxBottom_column1;
    TextView boxTop_column2;
    TextView boxMiddle_column2;
    TextView boxBottom_column2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        seekBar = findViewById(R.id.slider);
        seekBar.setMax(100);

        boxTop_column1 = findViewById(R.id.box_top_column_1);
        boxBottom_column1 = findViewById(R.id.box_bottom_column_1);
        boxTop_column2 = findViewById(R.id.box_top_column_2);
        boxMiddle_column2 = findViewById(R.id.box_middle_column_2);
        boxBottom_column2 = findViewById(R.id.box_bottom_column_2);

        //manage seekBar slide behavior
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int progChange = i;
                int[] blueArray = {100,149,237};
                int[] pinkArray = {255,105,180};
                int[] redArray = {139,0,0};
                int[] whiteArray = {245,255,250};
                int[] blackArray = {25,25,112};

                blueArray[0] = blueArray[0] - (100 / 100) * progChange;
                blueArray[1] = blueArray[1] + (149 / 100) * progChange;
                blueArray[2] = blueArray[2] + (237 / 100) * progChange;
                pinkArray[0] = pinkArray[0] + (255 / 100) * progChange;
                pinkArray[1] = pinkArray[1] + (105 / 100) * progChange;
                pinkArray[2] = pinkArray[2] - (180 / 100) * progChange;
                redArray[0] = redArray[0] - (139 / 100) * progChange;
                redArray[1] = redArray[1] - (0 / 100) * progChange;
                redArray[2] = redArray[2] + (0 / 100) * progChange;
                whiteArray[0] = whiteArray[0] + (245 / 100) * progChange;
                whiteArray[1] = whiteArray[1] + (255 / 100) * progChange;
                whiteArray[2] = whiteArray[2] - (250 / 100) * progChange;
                blackArray[0] = blackArray[0] - (25 / 100) * progChange;
                blackArray[1] = blackArray[1] - (25 / 100) * progChange;
                blackArray[2] = blackArray[2] + (112 / 100) * progChange;

                boxTop_column1.setBackgroundColor(Color.rgb(blueArray[0], blueArray[1], blueArray[2]));
                boxBottom_column1.setBackgroundColor(Color.rgb(pinkArray[0], pinkArray[1], pinkArray[2]));
                boxTop_column2.setBackgroundColor(Color.rgb(redArray[0], redArray[1], redArray[2]));
                boxMiddle_column2.setBackgroundColor(Color.rgb(whiteArray[0], whiteArray[1], whiteArray[2]));
                boxBottom_column2.setBackgroundColor(Color.rgb(blackArray[0], blackArray[1], blackArray[2]));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modern_art_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.more_information) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            TextView dialog_title = new TextView(this);
            dialog_title.setText(R.string.dialog_title);
            dialog_title.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog_title.setPadding(100, 30, 100, 30);
            dialog_title.setTextSize(17);

            TextView not_now = new TextView(this);
            not_now.setGravity(Gravity.LEFT);
            not_now.setTextSize(17);

            TextView visit_moma = new TextView(this);
            visit_moma.setGravity(Gravity.LEFT);
            visit_moma.setTextSize(17);

            builder.setCustomTitle(dialog_title);
            builder.setMessage(R.string.dialog_message);
            builder.setPositiveButton(R.string.not_now, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setNegativeButton(R.string.visit_moma, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent momaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    startActivity(momaIntent);
                }
            });
            AlertDialog dialog = builder.show();
            TextView dialog_msg = (TextView) dialog.findViewById(android.R.id.message);
            dialog_msg.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog_msg.setTextSize(17);
            dialog_msg.setPadding(10, 60, 10, 0);
            dialog_msg.setTextColor(Color.WHITE);
        }
        return super.onOptionsItemSelected(item);
    }
}