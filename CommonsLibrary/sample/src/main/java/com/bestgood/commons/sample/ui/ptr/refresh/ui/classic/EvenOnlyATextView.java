package com.bestgood.commons.sample.ui.ptr.refresh.ui.classic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bestgood.commons.sample.R;
import com.bestgood.commons.ui.ptr.PtrClassicFrameLayout;
import com.bestgood.commons.ui.ptr.PtrDefaultHandler;
import com.bestgood.commons.ui.ptr.PtrFrameLayout;

import in.srain.cube.mints.base.TitleBaseFragment;

public class EvenOnlyATextView extends TitleBaseFragment {

    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.ptr_demo_block_only_text_view);

        final View contentView = inflater.inflate(R.layout.fragment_classic_header_with_textview, container, false);

        final PtrClassicFrameLayout ptrFrame = (PtrClassicFrameLayout) contentView.findViewById(R.id.fragment_rotate_header_with_text_view_frame);
        ptrFrame.setLastUpdateTimeRelateObject(this);
        ptrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrame.refreshComplete();
                    }
                }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });
        return contentView;
    }

}
