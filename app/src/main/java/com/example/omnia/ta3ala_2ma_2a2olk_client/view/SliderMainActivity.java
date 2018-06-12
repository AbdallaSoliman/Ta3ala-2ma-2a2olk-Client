package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adapters.SliderAdapter;

import java.util.Locale;

public class SliderMainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button btnStartApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_main);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout =(LinearLayout) findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDostIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        btnStartApp =(Button)findViewById(R.id.btnStart);


        AssetManager am = this.getApplicationContext().getAssets();
        Typeface  custom_font_btn = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "al_jazeera_arabic_regular.ttf"));

        btnStartApp.setTypeface(custom_font_btn);


        btnStartApp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SliderMainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    public void addDostIndicator(int position){
        mDots = new TextView[4];
    mDotLayout.removeAllViews();
        for(int i =0;i <mDots.length ; i++){
        mDots[i]= new TextView(this);
        //#bbbbbb
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.slideDotdisActive));
            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.slideDotActive));
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDostIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
