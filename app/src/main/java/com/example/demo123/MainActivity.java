/**Main class of the application that manages user interactions and actions.
        */
package com.example.demo123;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
/**Class representing the main activity of the application.*/
public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder button1, button2, button3, button4, button5;
    int[] color = new int[3];
    ConstraintLayout linlay;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linlay = findViewById(R.id.linlay);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linlay), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    final String[] colors = {"Red", "Green", "Blue"};
    /** Handles the click event for the first button. Displays a dialog for selecting a single color.*/
    public void click1(View view) {
        color = new int[]{0, 0, 0};
        button1 = new AlertDialog.Builder(this);
        button1.setTitle("List of colors - one choice");
        button1.setItems(colors, (dialogInterface, which) -> {
            color[0] = 0;
            color[1] = 0;
            color[2] = 0;
            color[which] = 255;
            linlay.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));

        });
        button1.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        button1.setCancelable(false);
        AlertDialog mg = button1.create();
        mg.show();
    }
    /** Handles the click event for the second button. Allows multiple color selections.*/
    public void click2(View view) {
        button2 = new AlertDialog.Builder(this);
        button2.setTitle("List of colors - multi choice");

        final int[] tempRed = {color[0]};
        final int[] tempGreen = {color[1]};
        final int[] tempBlue = {color[2]};

        button2.setMultiChoiceItems(colors, new boolean[]{color[0] == 255, color[1] == 255, color[2] == 255},
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        if (isChecked) {
                            if (which == 0) tempRed[0] = 255;
                            if (which == 1) tempGreen[0] = 255;
                            if (which == 2) tempBlue[0] = 255;
                        } else {
                            if (which == 0) tempRed[0] = 0;
                            if (which == 1) tempGreen[0] = 0;
                            if (which == 2) tempBlue[0] = 0;
                        }
                    }
                });

        button2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                color[0] = tempRed[0];
                color[1] = tempGreen[0];
                color[2] = tempBlue[0];
                linlay.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
            }
        });

        button2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        button2.setCancelable(false);
        AlertDialog mg2 = button2.create();
        mg2.show();
    }
    /** Handles the click event for the third button. Resets the background color to white.*/
    public void click3(View view) {
        linlay.setBackgroundColor(Color.WHITE);
    }
    /** Handles the click event for the fourth button. Displays a message with a confirmation button.*/
    public void click4(View view) {
        AlertDialog.Builder button4 = new AlertDialog.Builder(this);
        button4.setTitle("Text+2 buttons");
        button4.setMessage("בשביל לראות את הטקסט לחץ אישור, אחרת הטקסט לא יופיע");
        final TextView tV = new TextView(this);
        button4.setView(tV);
        button4.setCancelable(false);

        button4.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(MainActivity.this, "תהיה נחמד בציון :)", Toast.LENGTH_SHORT).show();
            }
        });
        button4.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                button4.setCancelable(false);
            }
        });
        button4.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.MenuCredits) {
            Intent si = new Intent (this, MainActivity2.class);
            startActivity(si);

        }
        return true;
    }
}