package kr.co.kimjubong.android.design.support7;

import android.app.Application;
import android.content.Context;

/**
 * Created by xmfow_000 on 2016-01-18.
 */
public class GirlsApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate(){
        super.onCreate();
        mContext = this;
    }
    public static Context getGirlsContext(){
        return mContext;
    }
}
