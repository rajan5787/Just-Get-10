package rajan5787.tikuraja.justget10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainPage extends AppCompatActivity {


    Button btnRestart;
    TextView txtMaxScore, txtMaxNumber, txtCurrScore;
    TextView[][] textViews;

    int[][] board;
    boolean[][] selected;

    int W = 5;
    int H = 5;
    int curr_score =0;
    int max_score;
    int max_number;
    int number = 0;
    Animation animFadein;

    UserInformation userInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);

        board = new int[9][9];
        selected = new boolean[9][9];
        textViews = new TextView[9][9];
        define();
        btnRestart = findViewById(R.id.btn_restart_game);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

        userInformation = new UserInformation(getApplicationContext());
        if(userInformation.isFlag()){
            board = userInformation.getCurrent_state();
            curr_score = userInformation.getCurrent_score();
            max_score = userInformation.getMax_score();
            max_number = userInformation.getMax_number();
            txtMaxScore.setText(String.valueOf(max_score));
            txtMaxNumber.setText(String.valueOf(max_number));
            txtCurrScore.setText(String.valueOf(curr_score));

            updateBoard();
        }
        else {
            newGame();
        }
    }


    public void check(int h,int w) {


        checkNext(h,w);
        if(selected[h][w]==true) {
            combine(h, w);
            for (int i = 1; i <= H; i++) {
                for (int j = 1; j <= W; j++) {
                    selected[i][j] = false;
                }
            }
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    try {
                        fall();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    fillNumbers();
                    colorBoard();
                    updateBoard();
                    userInformation.setCurrent_state(board);
                    userInformation.setCurrent_score(curr_score);
                    userInformation.setFlag(true);
                }
            }, 10);

        }
    }

    public void txt_click(View view){

        int id = view.getId();
        Log.d("MainPage","txtClick " +id);
        switch (id){

            case R.id.txt_11:
                check(1,1);
                break;
            case R.id.txt_12:
                check(1,2);
                break;
            case R.id.txt_13:
                check(1,3);
                break;
            case R.id.txt_14:
                check(1,4);
                break;
            case R.id.txt_15:
                check(1,5);
                break;
            case R.id.txt_21:
                check(2,1);
                break;
            case R.id.txt_22:
                check(2,2);
                break;
            case R.id.txt_23:
                check(2,3);
                break;
            case R.id.txt_24:
                check(2,4);
                break;
            case R.id.txt_25:
                check(2,5);
                break;
            case R.id.txt_31:
                check(3,1);
                break;
            case R.id.txt_32:
                check(3,2);
                break;
            case R.id.txt_33:
                check(3,3);
                break;
            case R.id.txt_34:
                check(3,4);
                break;
            case R.id.txt_35:
                check(3,5);
                break;
            case R.id.txt_41:
                check(4,1);
                break;
            case R.id.txt_42:
                check(4,2);
                break;
            case R.id.txt_43:
                check(4,3);
                break;
            case R.id.txt_44:
                check(4,4);
                break;
            case R.id.txt_45:
                check(4,5);
                break;
            case R.id.txt_51:
                check(5,1);
                break;
            case R.id.txt_52:
                check(5,2);
                break;
            case R.id.txt_53:
                check(5,3);
                break;
            case R.id.txt_54:
                check(5,4);
                break;
            case R.id.txt_55:
                check(5,5);
                break;


        }

    }

    public void newGame(){

        if(curr_score>max_score)
            userInformation.setMax_score(curr_score);

        userInformation.setFlag(false);
        double val = 0;
        for(int i = 1;i<=H;i++){
            for(int j = 1;j<=W;j++){

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
        curr_score = 0;
        userInformation.setCurrent_state(board);
        userInformation.setCurrent_score(curr_score);
        txtMaxScore.setText(String.valueOf(userInformation.getMax_score()));
        txtMaxNumber.setText(String.valueOf(userInformation.getMax_number()));
        txtCurrScore.setText(String.valueOf(userInformation.getCurrent_score()));
        userInformation.setFlag(true);

    }

    public void updateBoard(){
        for(int i=1; i<=H; i++) {
            for (int j = 1; j <= W; j++) {

                textViews[i][j].setText(String.valueOf(board[i][j]));
            }
        }

        colorBoard();
    }

    @SuppressLint("ResourceAsColor")
    public void colorBoard(){

        for(int i=1;i<=H;i++){
            for(int j = 1;j<=W;j++){

                switch (board[i][j]){
                    case 1:
                        textViews[i][j].setBackgroundResource(R.drawable.bg_tile_one);

                        break;
                    case 2:
                        Log.d("dfdf","fuck");
                        textViews[i][j].setBackgroundResource(R.drawable.bg_tile_two);
                        break;
                    case 3:
                        textViews[i][j].setBackgroundResource(R.drawable.bg_tile_three);
                        break;
                    case 4:
                        textViews[i][j].setBackgroundResource(R.drawable.bg_tile_four);
                        break;
                    case 5:
                        textViews[i][j].setBackgroundResource(R.drawable.bg_tile_five);
                        break;
                    case 6:
                        textViews[i][j].setBackgroundResource(R.drawable.bg_tile_six);
                        break;
                    case 7:
                        textViews[i][j].setBackgroundResource(R.drawable.bg_tile_seven);
                        break;
                    case 8:
                        textViews[i][j].setBackgroundResource(R.drawable.bg_tile_eight);
                        break;
                    case 9:
                        textViews[i][j].setBackgroundResource(R.drawable.bg_tile_nine);
                        break;
                    case 10:
                        textViews[i][j].setBackgroundResource(R.drawable.bg_tile_ten);
                        break;
                }
            }
        }
    }

    public void checkNext(int h,int w){

        if(selected[h-1][w]==false&&board[h][w]==board[h-1][w]){
            selected[h][w] = true;
            selected[h-1][w] = true;
            checkNext(h-1,w);
        }
        if(selected[h+1][w]==false&&board[h][w]==board[h+1][w]){
            selected[h][w] = true;
            selected[h+1][w] = true;
            checkNext(h+1,w);
        }
        if(selected[h][w-1]==false&&board[h][w]==board[h][w-1]){
            selected[h][w] = true;
            selected[h][w-1] = true;
            checkNext(h,w-1);
        }
        if(selected[h][w+1]==false&&board[h][w]==board[h][w+1]){
            selected[h][w] = true;
            selected[h][w+1] = true;
            checkNext(h,w+1);
        }
    }

    public void combine(int h,int w) {

        number = board[h][w];
        int val = board[h][w] + 1;
        if(val>max_number)
            userInformation.setMax_number(val);
        curr_score+=number;
        board[h][w] = val;
        selected[h][w] = false;
        for(int i=1; i<=H; i++) {
            for(int j=1; j<=W; j++) {
                if(selected[i][j] == true) {
                    curr_score+=number;
                    board[i][j] = 0;
                    textViews[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
        txtCurrScore.setText(String.valueOf(curr_score));
    }

    public void fall() throws InterruptedException {

        for (int n = 1; n <= H; n++) {
            for (int i = 1; i <= H; i++) {
                for (int j = 1; j <= W; j++) {
                    if (board[i][j] == 0) {
                        System.out.println(" h " + i + " w " + j);
                        board[i][j] = board[i - 1][j];
                        board[i - 1][j] = 0;
                        if(i!=1)
                            textViews[i-1][j].setVisibility(View.INVISIBLE);
                        textViews[i][j].setText(String.valueOf(board[i][j]));
                        textViews[i][j].setVisibility(View.VISIBLE);
                    }
                }
            }
            TimeUnit.MILLISECONDS.sleep((n-1)*2*10);
        }

    }

    public void fillNumbers() {
        double val;
        for(int i=1; i<=H; i++) {
            for(int j=1; j<=W; j++) {
                if(board[i][j] == 0) {
                    val = Math.floor(Math.random() * 10 + 1);
                    if(val == 1) {
                        board[i][j] = 4;
                    }
                    else if(val <= 3) {
                        board[i][j] = 3;
                    }
                    else if(val <= 6) {
                        board[i][j] = 2;
                    }
                    else {
                        board[i][j] = 1;
                    }
                    // $("#" + rc).css("display", "none").delay(2).fadeIn("fast");
                    textViews[i][j].setText(String.valueOf(board[i][j]));
                    textViews[i][j].setVisibility(View.VISIBLE);
                    textViews[i][j].startAnimation(animFadein);

                }

            }
        }
    }

    public void define(){

        txtMaxNumber = findViewById(R.id.txt_max_number);
        txtMaxScore = findViewById(R.id.txt_max_score);
        txtCurrScore = findViewById(R.id.txt_curr_score);
        textViews[1][1] = findViewById(R.id.txt_11);
        textViews[1][2] = findViewById(R.id.txt_12);
        textViews[1][3] = findViewById(R.id.txt_13);
        textViews[1][4] = findViewById(R.id.txt_14);
        textViews[1][5] = findViewById(R.id.txt_15);
        textViews[2][1] = findViewById(R.id.txt_21);
        textViews[2][2] = findViewById(R.id.txt_22);
        textViews[2][3] = findViewById(R.id.txt_23);
        textViews[2][4] = findViewById(R.id.txt_24);
        textViews[2][5] = findViewById(R.id.txt_25);
        textViews[3][1] = findViewById(R.id.txt_31);
        textViews[3][2] = findViewById(R.id.txt_32);
        textViews[3][3] = findViewById(R.id.txt_33);
        textViews[3][4] = findViewById(R.id.txt_34);
        textViews[3][5] = findViewById(R.id.txt_35);
        textViews[4][1] = findViewById(R.id.txt_41);
        textViews[4][2] = findViewById(R.id.txt_42);
        textViews[4][3] = findViewById(R.id.txt_43);
        textViews[4][4] = findViewById(R.id.txt_44);
        textViews[4][5] = findViewById(R.id.txt_45);
        textViews[5][1] = findViewById(R.id.txt_51);
        textViews[5][2] = findViewById(R.id.txt_52);
        textViews[5][3] = findViewById(R.id.txt_53);
        textViews[5][4] = findViewById(R.id.txt_54);
        textViews[5][5] = findViewById(R.id.txt_55);
    }

}
//
//
//switch (board[i][j]){
//        case 1:
//        textViews[i][j].setBackgroundResource(R.color.cover);
//
//        break;
//        case 2:
//        Log.d("dfdf","fuck");
//        textViews[i][j].setBackgroundResource(R.color.terracotta);
//        break;
//        case 3:
//        textViews[i][j].setBackgroundResource(R.color.puce);
//        break;
//        case 4:
//        textViews[i][j].setBackgroundResource(R.color.shutter_blue);
//        break;
//        case 5:
//        textViews[i][j].setBackgroundResource(R.color.lavendet);
//        break;
//        case 6:
//        textViews[i][j].setBackgroundResource(R.color.marina);
//        break;
//        case 7:
//        textViews[i][j].setBackgroundResource(R.color.bittersweet);
//        break;
//        case 8:
//        textViews[i][j].setBackgroundResource(R.color.azul_verde);
//        break;
//        case 9:
//        textViews[i][j].setBackgroundResource(R.color.fresh);
//        break;
//        case 10:
//        textViews[i][j].setBackgroundResource(R.color.creosote);
//        break;