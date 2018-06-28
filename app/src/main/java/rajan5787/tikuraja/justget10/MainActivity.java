package rajan5787.tikuraja.justget10;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    Button btnStartGame, btnClassic, btnGravity;
    public LinearLayout llGuide, llDeveloper, llOtherApp, llRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartGame = findViewById(R.id.btn_start_game);
        btnClassic = findViewById(R.id.btn_classic_game);
        btnGravity = findViewById(R.id.btn_gravity_game);

        llGuide = findViewById(R.id.ll_game_rule);
        llDeveloper = findViewById(R.id.ll_about_developer);
        llRating = findViewById(R.id.ll_give_rating);
        llOtherApp = findViewById(R.id.ll_more_game);



        llOtherApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://play.google.com/store/apps/collection/cluster?clp=igNBChkKEzY2ODAwMjE3NDMxNTc0OTIyOTcQCBgDEiIKHHJhamFuNTc4Ny50aWt1cmFqYS5qdXN0Z2V0MTAQARgDGAE%3D:S:ANO1ljJSwVk";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        llRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://play.google.com/store/apps/details?id=rajan5787.tikuraja.justget10";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        llOtherApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://play.google.com/store/apps/collection/cluster?clp=igNBChkKEzY2ODAwMjE3NDMxNTc0OTIyOTcQCBgDEiIKHHJhamFuNTc4Ny50aWt1cmFqYS5qdXN0Z2V0MTAQARgDGAE%3D:S:ANO1ljJSwVk";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        llDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Developer.class);
                startActivity(i);
            }
        });

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnStartGame.setVisibility(View.GONE);
                btnClassic.setVisibility(View.VISIBLE);
                btnGravity.setVisibility(View.VISIBLE);
            }
        });
        llGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),UserGuide.class);
                startActivity(i);
            }
        });

        btnClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainPage.class);
                i.putExtra("gravity_flag",false);
                startActivity(i);
            }
        });

        btnGravity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),MainPage.class);
                i.putExtra("gravtiy_flag",true);
                startActivity(i);
            }
        });
    }
}
