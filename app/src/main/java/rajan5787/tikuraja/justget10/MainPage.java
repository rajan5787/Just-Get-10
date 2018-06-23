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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.concurrent.TimeUnit;

public class MainPage extends AppCompatActivity {


    public LinearLayout yes, no,llDialog, mainLL;
    public TextView btndes,txtScore,txtNumber,txtNo,txtYes;

    int flag_dialog = 0;
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
    int curr_number;
    int number = 0;
    Animation animFadein, animationDown, animationUp;
    UserInformation userInformation;
    InterstitialAd mInterstitialAd;
    AdRequest adRequest;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);



        define();
       showAds();

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flag_dialog = 1;
                showDialogg();
            }
        });

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

    public void showDialogg(){

        llDialog.startAnimation(animationUp);
        mainLL.setClickable(false);
        if(flag_dialog==1) {
            btndes.setText("Do you want to start new Game?");
            txtScore.setVisibility(View.GONE);
            txtNumber.setVisibility(View.GONE);
            txtYes.setText("Yes");
            txtNo.setText("No");
            no.setVisibility(View.VISIBLE);
        }
        else if(flag_dialog==2) {
            btndes.setText("No more move..");
            txtScore.setText(String.valueOf(curr_score));
            txtNumber.setText(String.valueOf(curr_number));
            tctNumberBackGround();
            txtScore.setVisibility(View.VISIBLE);
            txtNumber.setVisibility(View.VISIBLE);
            txtYes.setText("Try Again");
            no.setVisibility(View.GONE);
        }
        else if(flag_dialog==3){
            btndes.setText("Do you want to leave?");
            txtScore.setVisibility(View.GONE);
            txtNumber.setVisibility(View.GONE);
            txtYes.setText("Yes");
            txtNo.setText("No");

            no.setVisibility(View.VISIBLE);
        }

        llDialog.setVisibility(View.VISIBLE);

    }

    private void tctNumberBackGround() {

        switch (curr_number){
            case 1:
                txtNumber.setBackgroundResource(R.drawable.bg_tile_one);

                break;
            case 2:
                Log.d("dfdf","fuck");
                txtNumber.setBackgroundResource(R.drawable.bg_tile_two);
                break;
            case 3:
                txtNumber.setBackgroundResource(R.drawable.bg_tile_three);
                break;
            case 4:
                txtNumber.setBackgroundResource(R.drawable.bg_tile_four);
                break;
            case 5:
                txtNumber.setBackgroundResource(R.drawable.bg_tile_five);
                break;
            case 6:
                txtNumber.setBackgroundResource(R.drawable.bg_tile_six);
                break;
            case 7:
                txtNumber.setBackgroundResource(R.drawable.bg_tile_seven);
                break;
            case 8:
                txtNumber.setBackgroundResource(R.drawable.bg_tile_eight);
                break;
            case 9:
                txtNumber.setBackgroundResource(R.drawable.bg_tile_nine);
                break;
            case 10:
                txtNumber.setBackgroundResource(R.drawable.bg_tile_ten);
                break;
        }
    }

    public boolean chekkk(){

        for(int i = 1;i<=5;i++){
            for(int j = 1;j<5;j++){
                if(board[i][j]==board[i-1][j]|| board[i][j]==board[i+1][j]|| board[i][j]==board[i][j-1]|| board[i][j]==board[i][j+1]){
                    return false;
                }
            }
        }
        return true;
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
                    if(chekkk()){
                        flag_dialog = 2;
                        showDialogg();
                    }
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
        if(curr_number>max_number)
            userInformation.setMax_number(curr_number);

       // showInterstitial();
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
        curr_number = 0;
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
        if(val>curr_number)
            curr_number = val;
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

        mainLL = findViewById(R.id.main_ll);
        llDialog = findViewById(R.id.ll_dialog);
        llDialog.setVisibility(View.GONE);
        txtScore = findViewById(R.id.txt_max_score_dialog);
        txtNumber = findViewById(R.id.txt_max_number_dialog);
        btndes = findViewById(R.id.btndis);
        yes =  findViewById(R.id.postive_feedback_layout);
        no =  findViewById(R.id.negative_feedback_layout);
        txtYes =  findViewById(R.id.positive_feedback_text);
        txtNo =  findViewById(R.id.negative_feedback_text);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        adRequest = new AdRequest.Builder()
                .build();
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        animationDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_down);
        animationUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_up);

        board = new int[9][9];
        selected = new boolean[9][9];
        textViews = new TextView[9][9];
        userInformation = new UserInformation(getApplicationContext());

        btnRestart = findViewById(R.id.btn_restart_game);
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

    private void showInterstitial() {
        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });

    }

    private void showAds() {

        mAdView =  findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                  .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                 .addTestDevice("E94309F6B1155D24023F2474FB1F0E9D")
                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {

                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {

        flag_dialog = 3;
        showDialogg();

    }

    public void txt_dialog(View v){

        switch (v.getId()) {
            case R.id.positive_feedback_text:
                if (flag_dialog == 1){
                    llDialog.startAnimation(animationDown);
                    newGame();
                }
                else if (flag_dialog == 2){
                    llDialog.startAnimation(animationDown);

                    newGame();
                }
                if (flag_dialog == 3){
                    llDialog.startAnimation(animationDown);

                    finish();
                }
                mainLL.setClickable(true);

                break;
            case R.id.negative_feedback_text:
                llDialog.startAnimation(animationDown);

                mainLL.setClickable(true);
                if(flag_dialog==2)
                    newGame();

                break;
            default:
                mainLL.setClickable(true);

                break;
        }


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