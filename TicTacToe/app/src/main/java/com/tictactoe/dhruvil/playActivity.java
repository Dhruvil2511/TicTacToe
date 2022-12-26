package com.tictactoe.dhruvil;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class playActivity extends AppCompatActivity {
    TextView player1Name,refreshText;
    TextView player2Name;
    ImageView newGame;
    Button reset;
    TextView turnText;
    TextView c1;
    TextView c3;
    TextView draw;
    private int countPlayer1=0;
    private int countPlayer2=0;
    private int drawCount=0;
    private int totalMoves=9;
    private int count=0,m=0;
    private int flag=0;
    private String ans;
    private String prevWinner;
    private boolean winner=false;
    public String name1;
    public String name2;
    public Button [][] buttons;
    public String [][] valButtons;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);
        String newGameText = "Press Reload button for next round OR\nPress Reset button to start New Game.";
        player1Name=findViewById(R.id.gamePlayer1);
        player2Name=findViewById(R.id.gamePlayer2);
        newGame=findViewById(R.id.refresh);
        reset=findViewById(R.id.resetScore);
        buttons=new Button[3][3];
        valButtons=new String[3][3];
        c1=findViewById(R.id.c1);
        draw=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        refreshText=findViewById(R.id.refreshText);
        refreshText.setText(newGameText);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonid = "button" + (i) + (j);
                int resId=getResources().getIdentifier(buttonid,"id",getPackageName());
                buttons[i][j] = ( findViewById(resId));

            }
        }

        turnText=findViewById(R.id.turnText);
        Intent intent= getIntent();
         name1=intent.getStringExtra(MainActivity.EXTRA1);
        name1=name1+":";
         name2=intent.getStringExtra(MainActivity.EXTRA2);
        name2=name2+":";
        player1Name.setText(name1);
        player2Name.setText(name2);
        if(m==0){
            turnText.setText(name1.substring(0,name1.length()-1));
            turnText.setText(turnText.getText()+"'s turn");
            m=1;
        }

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalMoves==9){
                    return;
                }
                totalMoves=9;

                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){

                      buttons[i][j].setText("");
                        winner=false;
                        if(Objects.equals(ans, "X")){
                            turnText.setText(name1.substring(0,name1.length()-1));
                            turnText.setText(turnText.getText()+"'s turn");
                            m=1;
                        }
                        else if (Objects.equals(ans, "O")){
                            turnText.setText(name2.substring(0,name2.length()-1));
                            turnText.setText(turnText.getText()+"'s turn");
                            m=1;
                        }
                        else{
                            if(Objects.equals(prevWinner, "X")){
                                turnText.setText(name1.substring(0,name1.length()-1));
                                turnText.setText(turnText.getText()+"'s turn");
                                m=1;
                            }
                            else{
                                turnText.setText(name2.substring(0,name2.length()-1));
                                turnText.setText(turnText.getText()+"'s turn");
                                m=1;
                            }
                        }

                    }
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalMoves=9;
                countPlayer1=0;
                countPlayer2=0;
                drawCount=0;
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        Log.d(TAG, "onClick: "+ buttons[i][j].getText());
                        buttons[i][j].setText("");
                        winner=false;
                        c1.setText("0");
                        c3.setText("0");
                        draw.setText("0");
                        turnText.setText(name1.substring(0,name1.length()-1));
                        turnText.setText(turnText.getText()+"'s turn");
                        flag=0;
                    }
                }
            }
        });

    }


    public void onClickButton(View view){
        Button currentButton=(Button) view;
        if(currentButton.getText()!="X" && currentButton.getText()!="O" && !winner) {
            count++;
            totalMoves--;
            if (flag == 0) {
                currentButton.setTextColor(getResources().getColor(R.color.red));
                turnText.setText(name2.substring(0,name2.length()-1));
                turnText.setText(turnText.getText() + "'s turn");
                ((Button) view).setText("X");
                flag = 1;

            } else if (flag == 1) {
                currentButton.setTextColor(getResources().getColor(R.color.blue));
                turnText.setText(name1.substring(0,name1.length()-1));
                turnText.setText(turnText.getText() + "'s turn");
                ((Button) view).setText("O");
                flag = 0;
                m++;
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    valButtons[i][j] = buttons[i][j].getText().toString();
                }
            }


            if (count > 4) {

                String displayWinner;
                if (validateRow() || validateCol() || validateDiag()) {
                    if (Objects.equals(ans, "X")) {
                        countPlayer1++;
                        String displayCount1 = Integer.toString(countPlayer1);
                        displayWinner = name1.substring(0,name1.length()-1) + " won this Round!";
                        prevWinner=ans;
                        winner=true;
                        c1.setText(displayCount1);
                        turnText.setText(displayWinner);
                        flag=0;
                    } else if (Objects.equals(ans, "O")) {
                        countPlayer2++;
                        String displayCount2 = Integer.toString(countPlayer2);
                        c3.setText(displayCount2);
                        displayWinner = name2.substring(0,name2.length()-1) + " won this Round!";
                        prevWinner=ans;
                        winner=true;
                        turnText.setText(displayWinner);
                        flag=1;
                    }
                } else if (Objects.equals(ans, "Draw!") && totalMoves == 0) {
                    drawCount++;
                    String drawString = Integer.toString(drawCount);
                    draw.setText(drawString);
                    turnText.setText(ans);

                    if(Objects.equals(prevWinner, "X")){
                        flag=0;
                    }else {
                        flag=1;
                    }
                }


            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currentButton.setTypeface(getResources().getFont(R.font.cherry_cream_soda));
        }
    }
    public boolean validateRow(){

        for(int i=0;i<3;i++){
            if(Objects.equals(valButtons[i][0], valButtons[i][1]) && Objects.equals(valButtons[i][1], valButtons[i][2]) && !Objects.equals(valButtons[i][0], "")){
                ans=valButtons[i][1];
                return true;
            }
        }
        ans="Draw!";
        return false;
    }
    public boolean validateCol(){
        for(int i=0;i<3;i++){
            if(Objects.equals(valButtons[0][i], valButtons[1][i]) && Objects.equals(valButtons[1][i], valButtons[2][i]) && !Objects.equals(valButtons[0][i], "")){
                ans=valButtons[1][i];
                return true;
            }
        }
        ans="Draw!";
        return false;
    }
    public boolean validateDiag(){

        for(int i=0;i<3;i++){
            if(Objects.equals(valButtons[0][0], valButtons[1][1]) && Objects.equals(valButtons[1][1], valButtons[2][2]) && !Objects.equals(valButtons[0][0], "")){
                ans=valButtons[1][1];
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(Objects.equals(valButtons[0][2], valButtons[1][1]) && Objects.equals(valButtons[1][1], valButtons[2][0]) && !Objects.equals(valButtons[0][2], "")){
                ans=valButtons[1][1];
                return true;
            }
        }
        ans="Draw!";
        return false;
    }
    

}
