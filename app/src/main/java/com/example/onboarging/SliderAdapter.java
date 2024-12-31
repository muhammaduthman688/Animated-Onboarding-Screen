package com.example.onboarging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private int[] slideImages = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
    };

    private String[] slideHeadings = {
            "Find  your lost \n" +
                    "Bluetooth Devices ",
            "Pin Your Favorite \n" +
                    "Bluetooth Devices",
            "Accurate Compass in \n " + "Outdoor Activities",
            "Charge Detector \n" +
                    "Battery Level"
    };

    private String[] onboardingDetails = {
            "Just turning on will  helps you finding those Bluetooth devices.",
            "Searching and navigating you can reach to your Bluetooth device easily",
            "Accurate Compass in Outdoor \n " + "Activities",
            "Now  charge Detector Battery \n" +
                    "Level will help you "
    };

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.imageView);
        TextView slideHeading = view.findViewById(R.id.heading);
        TextView slideText = view.findViewById(R.id.textPage);

        slideImageView.setImageResource(slideImages[position]);
        slideHeading.setText(slideHeadings[position]);
        slideText.setText(onboardingDetails[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
}