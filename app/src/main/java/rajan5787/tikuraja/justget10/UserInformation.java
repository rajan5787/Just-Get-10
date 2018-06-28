package rajan5787.tikuraja.justget10;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by rajanpipaliya on 23/06/18.
 */


public class UserInformation {

    private static String TAG = UserInformation.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "USER_DATA";

    int max_score;
    int max_number;
    int current_score;
    int[][] current_state;
    boolean flag = false;
    int count = 0;
    boolean gravity = false;

    int max_score_gravity;
    int max_number_gravity;
    int current_score_gravity;
    int[][] current_state_gravity;
    int gravity_value = 1;
    boolean flag_gravity = false;

    public int getGravity_value() {
        return pref.getInt("gravity_value", 1);
    }

    public void setGravity_value(int gravity_value) {
        this.gravity_value = gravity_value;
        editor.putInt("gravity_value",gravity_value);
        editor.commit();
    }

    public int getCount(boolean gravity) {

            return pref.getInt("count", 0);
    }

    public void setCount(int count) {
        this.count = count;
        editor.putInt("count",count);
        editor.commit();
    }

    public int getMax_score(boolean gravity) {
        if(gravity){
            return pref.getInt("max_score_gravity", 0);

        }
        else {
            return pref.getInt("max_score", 0);
        }
    }

    public void setMax_score(int max_score,boolean gravity) {
        if(gravity){
            this.max_score_gravity = max_score;
            editor.putInt("max_score_gravity", max_score);
            editor.commit();
        }
        else {
            this.max_score = max_score;
            editor.putInt("max_score", max_score);
            editor.commit();
        }
    }

    public int getMax_number(boolean gravity) {
        if(gravity){
            return pref.getInt("max_number_gravity", 0);
        }
        else {
            return pref.getInt("max_number", 0);
        }
    }

    public void setMax_number(int max_number,boolean gravity) {
        if(gravity){
            this.max_number_gravity = max_number;
            editor.putInt("max_number_gravity", max_number);
            editor.commit();
        }
        else {
            this.max_number = max_number;
            editor.putInt("max_number", max_number);
            editor.commit();
        }
    }

    public int getCurrent_score(boolean gravity) {
        if(gravity){
            return pref.getInt("current_score_gravity", 0);
        }
        else {
            return pref.getInt("current_score", 0);
        }
    }

    public void setCurrent_score(int current_score,boolean gravity) {
        if(gravity){
            this.current_score_gravity = current_score;
            editor.putInt("current_score_gravity", current_score);
            editor.commit();
        }
        else {
            this.current_score = current_score;
            editor.putInt("current_score", current_score);
            editor.commit();
        }
    }

    public int[][] getCurrent_state(boolean gravity) {
        int[][] temp = new int[9][9];
        if(gravity){
            String s = pref.getString("current_state_gravity", null);
            Log.d("array", s);
            if (s != null) {
                String[] ss = s.split(" ");
                int a = 0;
                for (int i = 1; i <= 5; i++) {
                    for (int j = 1; j <= 5; j++) {
                        temp[i][j] = Integer.parseInt(ss[a]);
                        a++;
                    }
                }
            }
        }
        else {
            String s = pref.getString("current_state", null);
            Log.d("array", s);
            if (s != null) {
                String[] ss = s.split(" ");
                int a = 0;
                for (int i = 1; i <= 5; i++) {
                    for (int j = 1; j <= 5; j++) {
                        temp[i][j] = Integer.parseInt(ss[a]);
                        a++;
                    }
                }
            }
        }
        return temp;
    }

    public void setCurrent_state(int[][] current_state,boolean gravity) {
        if(gravity){
            this.current_state_gravity = current_state;
            String s = "";
            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    s += current_state[i][j] + " ";
                }
            }

            editor.putString("current_state_gravity", s);
            editor.commit();
        }
        else {
            this.current_state = current_state;
            String s = "";
            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    s += current_state[i][j] + " ";
                }
            }

            editor.putString("current_state", s);
            editor.commit();
        }
    }

    public boolean isFlag(boolean gravity) {
        if(gravity){
            return pref.getBoolean("game_state_gravity", false);

        }
        else {
            return pref.getBoolean("game_state", false);
        }
    }

    public void setFlag(boolean flag,boolean gravity) {
        if(gravity){
            this.flag_gravity = flag;
            editor.putBoolean("game_state_gravity", flag);
            editor.commit();
        }
        else {
            this.flag = flag;
            editor.putBoolean("game_state", flag);
            editor.commit();
        }
    }

    boolean SECTION_ALERT_STATUS = false;

    public UserInformation(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
}