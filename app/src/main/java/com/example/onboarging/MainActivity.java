package com.example.onboarging;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.example.onboarging.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private ViewPager slideViewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private SliderAdapter sliderAdapter;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        slideViewPager = findViewById(R.id.slideViewPager);
        dotsLayout = findViewById(R.id.dots);

        sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);
        binding.Skip.setOnClickListener(view -> Toast.makeText(MainActivity.this, "MainScreen", Toast.LENGTH_SHORT).show());



        binding.nextButton.setOnClickListener(view -> slideViewPager.setCurrentItem(currentPage + 1));


        final Handler handler = new Handler();
        final Runnable updateRunnable = new Runnable() {
            public void run() {
                int nextPage = (currentPage + 1) % sliderAdapter.getCount();
                slideViewPager.setCurrentItem(nextPage);
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(updateRunnable, 2500);
    }
//Dot Indicator
    private void addDotsIndicator(int position) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.purple_500));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    private ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;
            if (currentPage == 0) {
                binding.nextButton.setEnabled(true);
            } else if (position == dots.length - 1) {
                binding.nextButton.setEnabled(false);
            } else {
                binding.nextButton.setEnabled(true);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state)
        {

        }
    };
}
