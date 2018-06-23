package rajan5787.tikuraja.justget10;

/**
 * Created by rajanpipaliya on 23/06/18.
 */

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

/**
 * Created by rajanpipaliya on 26/03/18.
 */

public class Ads  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the AdMob app
        MobileAds.initialize(this, getString(R.string.admob_app_id));
    }
}