package com.example.omnia.ta3ala_2ma_2a2olk_client.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;

import java.util.Locale;

public class SliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context= context;
    }
public  int[] slideImages={
      //  android.support.v4.R.drawable.
        R.drawable.cake,
        R.drawable.hold,
        R.drawable.call_center,
        R.drawable.group

};
    public String[] slide_headings ={
            "مرحبا بك",
            "شارك خبراتك",
            "اطرح تساؤلاتك",
            "خدمة عملاء الشركات"

    };
    public String[] slide_desc ={
            "أهلا بك في تطبيق «تعالي أما أقولك » لتبادل الخبرات والرد علي جميع الاستفسارات في مختلف المجالات",
            "يمكنك الان ان تطرح أسئلتك في مختلف المجالات والتخصصات وسيشاركك الجميع خبراته وآراؤه",
            "احصل الان علي العديد من الاوسمة عند الرد باجابات صحيحة علي تساؤلات واستفسارات الاعضاء ",
            "يمكنك التواصل مباشرة مع ممثلي خدمات العملاء لمختلف الشركات المصرية للرد علي استفساراتك"

    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       layoutInflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView =(ImageView)view.findViewById(R.id.slidImageView);
        TextView slideHeading =(TextView)view.findViewById(R.id.slidHeader);
        TextView slideDescription =(TextView)view.findViewById(R.id.slideBody);

        //        Typeface custom_font_Hedder = Typeface.createFromAsset(getAssets(),  "fonts/jazeera.ttf");
//        Typeface custom_font_Body = Typeface.createFromAsset(getAssets(),  "fonts/jazeera.ttf");
//

        AssetManager am = context.getApplicationContext().getAssets();
        Typeface  custom_font_Hedder = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "jazeera.ttf"));
        Typeface  custom_font_Body = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "al_jazeera_arabic_regular.ttf"));



        slideHeading.setTypeface(custom_font_Hedder);
        slideDescription.setTypeface(custom_font_Body);

        slideImageView.setImageResource(slideImages[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
