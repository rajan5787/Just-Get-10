package rajan5787.tikuraja.justget10;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Developer extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
    }

    public void onClick(View view) {

        int id = view.getId();
        String url = null;
        Intent i;
        switch (id) {
            case R.id.ll_linedin:
                url = "https://www.linkedin.com/in/rajan-pipaliya-688a9bb0/";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.ll_facebook:
                url = "https://www.facebook.com/profile.php?id=100008223704136";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.ll_google:
                i = new Intent(Intent.ACTION_SEND);
                i.setType("plain/text");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"rajandp36@gmail.com"});
                startActivity(i);
                break;
        }
    }
}
