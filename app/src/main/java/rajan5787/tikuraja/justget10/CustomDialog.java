package rajan5787.tikuraja.justget10;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by rajanpipaliya on 23/06/18.
 */

public class CustomDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public LinearLayout yes, no;
    public int flag = 0;
    public TextView btndes,txtScore,txtNumber,txtNo,txtYes;
    public int max_number;
    public int max_score;
    Activity activity;

    public CustomDialog(Activity a,int flag,int max_number,int max_score) {
        super(a);
        this.activity = a;
        this.flag = flag;
        this.max_number = max_number;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialogbox);


        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.postive_feedback_layout:
                    dismiss();
                    break;
                case R.id.negative_feedback_layout:
                    c.finish();
                    break;
                default:
                    break;
            }
        }
}
