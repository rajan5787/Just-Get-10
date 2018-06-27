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

    public int getCount() {
        return pref.getInt("count", 0);

    }

    public void setCount(int count) {
        this.count = count;
        editor.putInt("count",count);
        editor.commit();
    }

    public int getMax_score() {
        return pref.getInt("max_score", 0);

    }

    public void setMax_score(int max_score) {
        this.max_score = max_score;
        editor.putInt("max_score",max_score);
        editor.commit();
    }

    public int getMax_number() {
        return pref.getInt("max_number", 0);
    }

    public void setMax_number(int max_number) {
        this.max_number = max_number;
        editor.putInt("max_number",max_number);
        editor.commit();
    }

    public int getCurrent_score() {
        return pref.getInt("current_score", 0);
    }

    public void setCurrent_score(int current_score) {
        this.current_score = current_score;
        editor.putInt("current_score",current_score);
        editor.commit();
    }

    public int[][] getCurrent_state() {
        int[][] temp = new int[9][9];
        String s =  pref.getString("current_state", null);
        Log.d("array",s);
        if(s!=null){
            String[] ss = s.split(" ");
            int a = 0;
            for(int i = 1;i<=5;i++){
                for(int j = 1;j<=5;j++){
                    temp[i][j] = Integer.parseInt(ss[a]);
                    a++;
                }
            }
        }

        return temp;
    }

    public void setCurrent_state(int[][] current_state) {
        this.current_state = current_state;
        String s = "";
        for(int i = 1;i<=5;i++){
            for(int j = 1;j<=5;j++){
             s += current_state[i][j]+" ";
            }
        }

        editor.putString("current_state",s);
        editor.commit();
    }

    public boolean isFlag() {
        return pref.getBoolean("game_state", false);
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
        editor.putBoolean("game_state",flag);
        editor.commit();
    }

    boolean SECTION_ALERT_STATUS = false;

    public UserInformation(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
}