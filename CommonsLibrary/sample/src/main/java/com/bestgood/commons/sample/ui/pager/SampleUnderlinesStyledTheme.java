package com.bestgood.commons.sample.ui.pager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.bestgood.commons.sample.R;
import com.bestgood.commons.ui.pager.UnderlinePageIndicator;

public class SampleUnderlinesStyledTheme extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //The look of this sample is set via a style in the manifest
        setContentView(R.layout.simple_underlines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}