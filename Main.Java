package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
//    player representation
//    0-x
//    1-0
//    2-Null
    int activePlayer=0;
    int [] gameState={2,2,2,2,2,2,2,2,2};

    int [] [] winningPos={ {0,1,2,},{3,4,5},{6,7,8},
                           {0,3,6},{1,4,7},{2,5,8},
                                  {0,4,8},{2,4,6}};
    public void playTap(View view ){

        ImageView img=(ImageView) view;
int tapedImage=Integer.parseInt( img.getTag().toString());

if(!gameActive){
    gameReset(view);
}

if(gameState[tapedImage]==2 && gameActive){
    gameState[tapedImage]=activePlayer;
    img.setTranslationY(-1000f);

    if(activePlayer==1){
        img.setImageResource(R.drawable.x);
        activePlayer=0;
        TextView status=findViewById(R.id.status);
        status.setText("O's turn.Tap to play");
    }
    else{
        img.setImageResource(R.drawable.image0);
        activePlayer=1;
        TextView status=findViewById(R.id.status);
        status.setText("X's turn.Tap to play");
    }

    img.animate().translationYBy(1000f).setDuration(300);

}
// check if any player won
        for(int [] winPositions : winningPos ){
            if( gameState[winPositions[0]]==gameState[winPositions[1]] &&
                    gameState[winPositions[1]]==gameState[winPositions[2]] &&
                    gameState[winPositions[0] ]!=2){
                //someone won
                String winnerStr;
                gameActive=false;
                if(gameState[winPositions[0]]==0){
                    winnerStr="o has won";

                }
                else{
                    winnerStr="x has won";
                }
//                update the status bar for winner announcement
                TextView status=findViewById(R.id.status);
                status.setText(winnerStr);
            }

            }
        }



    public void gameReset(View view){
        gameActive=true;
        activePlayer=0;
        for(int i =0 ;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);

        TextView status=findViewById(R.id.status);
        status.setText("tap to play");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
