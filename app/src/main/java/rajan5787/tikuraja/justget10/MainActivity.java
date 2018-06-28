package rajan5787.tikuraja.justget10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnStartGame, btnClassic, btnGravity;
    public LinearLayout llGuide, llDeveloper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartGame = findViewById(R.id.btn_start_game);
        btnClassic = findViewById(R.id.btn_classic_game);
        btnGravity = findViewById(R.id.btn_gravity_game);

        llGuide = findViewById(R.id.ll_game_rule);
        llDeveloper = findViewById(R.id.ll_about_developer);

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
