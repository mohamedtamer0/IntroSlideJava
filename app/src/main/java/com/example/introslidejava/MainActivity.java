package com.example.introslidejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SlideAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboarding);
        materialButton = findViewById(R.id.btnAction);

        setupOnboardingItems();


        final ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicators(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });


            materialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                        onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                    }else{
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                    }
                }
            });



    }


    private void setupOnboardingItems() {
        List<SlideItem> slideItem = new ArrayList<>();


        SlideItem itemOne = new SlideItem();
        itemOne.setTitle("Lorem Ipsum One");
        itemOne.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        itemOne.setImage(R.drawable.one);


        SlideItem itemTwo = new SlideItem();
        itemTwo.setTitle("Lorem Ipsum One");
        itemTwo.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        itemTwo.setImage(R.drawable.two);

        SlideItem itemThree = new SlideItem();
        itemThree.setTitle("Lorem Ipsum One");
        itemThree.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        itemThree.setImage(R.drawable.three);

        slideItem.add(itemOne);
        slideItem.add(itemTwo);
        slideItem.add(itemThree);

        onboardingAdapter = new SlideAdapter(slideItem);


    }


    private void setupOnboardingIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }


    private void setCurrentOnboardingIndicators(int index) {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if (index == onboardingAdapter.getItemCount() - 1) {
            materialButton.setText("Start");
        } else {
            materialButton.setText("Next");
        }


    }
}