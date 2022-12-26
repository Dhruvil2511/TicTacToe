package com.tictactoe.dhruvil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText player1Name;
    EditText player2Name;
    Button playButton;
    public static final String EXTRA1 ="com.tictactoe.dhruvil.extra.NAME1";
    public static final String EXTRA2 ="com.tictactoe.dhruvil.extra.NAME2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        player1Name =findViewById(R.id.player1Name);
        player2Name=findViewById(R.id.player2Name);
        playButton=findViewById(R.id.playButton);



        playButton.setOnClickListener(view -> {

            String p1Name= player1Name.getText().toString();
            String p2Name=player2Name.getText().toString();
            if(TextUtils.isEmpty(p1Name) && TextUtils.isEmpty(p2Name)){
               Toast.makeText(MainActivity.this, "Please enter name!", Toast.LENGTH_SHORT).show();

            }
            else if (TextUtils.isEmpty(p1Name) ){
                Toast.makeText(MainActivity.this, "Please enter name for Player 1", Toast.LENGTH_SHORT).show();

            }
            else if (TextUtils.isEmpty(p2Name)){
                Toast.makeText(MainActivity.this, "Please enter name for Player 2", Toast.LENGTH_SHORT).show();

            }
            else{
               Intent intent= new Intent(view.getContext(),playActivity.class);
                intent.putExtra(EXTRA1, p1Name);
                intent.putExtra(EXTRA2,  p2Name);
                startActivity(intent);
            }


        });
    }
}