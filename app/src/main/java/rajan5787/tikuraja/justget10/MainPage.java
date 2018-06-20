package rajan5787.tikuraja.justget10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainPage extends AppCompatActivity {


    int[][] board;
    boolean[][] selected;

    int w = 5;
    int h = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        board = new int[9][9];
        selected = new boolean[9][9];

    }

    public void newGame(){

        double val = 0;
        for(int i = 1;i<=h;i++){
            for(int j = 1;j<=w;j++){

                val = Math.floor(Math.random()*10+1);

                if(val==1){
                    board[i][j] = 4;
                }
                else if(val<=3){
                    board[i][j] = 3;
                }
                else if(val<=6){
                    board[i][j] = 2;
                }
                else{
                    board[i][j] = 1;
                }
            }
        }

        updateBoard();
    }

    public void updateBoard(){
        for(int i=1; i<=h; i++) {
            for (int j = 1; j <= w; j++) {


            }
        }

        colorBoard();
    }

    public void colorBoard(){

    }
}
