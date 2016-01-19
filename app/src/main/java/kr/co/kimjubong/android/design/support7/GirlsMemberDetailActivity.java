package kr.co.kimjubong.android.design.support7;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by xmfow_000 on 2016-01-18.
 */
public class GirlsMemberDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_member_detail);

        final ImageView memberImage = (ImageView)findViewById(R.id.member_image);

        Intent intent = getIntent();
        final Integer imageResValue = intent.getIntExtra("memberImage", -1);
        final String memberName = intent.getStringExtra("memberImage");

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            Transition exitTrans = new Explode();
            //Transition exitTrans = new Fade();
            //Transition exitTrans = new Slide();

            Transition reenterTrans = new Explode();
            //Transition reenterTrans = new Fade();
            //Transition reenterTrans = new Slide();

            window.setExitTransition(exitTrans);
            window.setReenterTransition(reenterTrans);
            window.setEnterTransition(reenterTrans);
            //window.setTransitionBackgroundFadeDuration(2000);
        }
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(memberName);
        memberImage.setImageResource(imageResValue);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.my_pet_navi_menu,menu);
        return true;
    }
}
