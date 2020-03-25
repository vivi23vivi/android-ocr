package com.mpaas.demo.ocr;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mpaas.ocr.api.DetectAreaInfo;
import com.mpaas.ocr.api.IDetectViewProvider;
import com.mpaas.ocr.api.IFlash;
import com.mpaas.ocr.view.CaptureMaskView;
import com.mpaas.ocr.view.CommonTitleBar;

/**
 * Created by mengfei on 2019/3/4.
 */

public class MaskView extends RelativeLayout implements IDetectViewProvider {
    private Activity mActivity;
    private CaptureMaskView mMaskView;
    private com.mpaas.ocr.view.CommonTitleBar mTitlebar;
    private ImageView mBackImageView;
    private TextView mTipView;
    private IFlash mFlash;

    public MaskView(Context ctx) {
        super(ctx);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.activity_detect_maskarea, this);
        mMaskView = (CaptureMaskView) findViewById(R.id.capture_mask);
        mTitlebar = (CommonTitleBar) findViewById(R.id.title_ly);
        mTitlebar.setBackgroundColor(0x00000000);
        mBackImageView = mTitlebar.getLeftIcon();
        mTipView = (TextView) findViewById(R.id.tv_rect_tip);
        mBackImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
        mTitlebar.setRightIconClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlash.toggleFlash();
            }
        });
    }

    @Override
    public void onDetectActivityPause() {
        //mTitlebar.setRightIcon(R.drawable.ic_flash_off);
    }

    @Override
    public void attachDetectContext(Activity activity, IFlash flash) {
        mActivity = activity;
        mFlash = flash;
    }

    @Override
    public View getMaskView() {
        return this;
    }

    @Override
    public void updateFlashMode(boolean isOn) {
        //mTitlebar.setRightIcon(isOn ? R.drawable.ic_flash_on : R.drawable.ic_flash_off);
    }

    @Override
    public void updateDetectType(int detectType) {
        if (detectType == DETECT_TYPE_BANK) {
            mTitlebar.setLeftText(getResources().getString(R.string.scan_card));
            mTipView.setText(R.string.capture_rect_tip);
        } else if (detectType == DETECT_TYPE_IDCARD_BACK) {
            mTipView.setText(R.string.scan_id_card_back);
            mTitlebar.setLeftText(getResources().getString(R.string.scan_id_card_back));
        } else if (detectType == DETECT_TYPE_IDCARD_FRONT) {
            mTipView.setText(R.string.scan_id_card_front);
            mTitlebar.setLeftText(getResources().getString(R.string.scan_id_card_front));
        }
    }


    @Override
    public DetectAreaInfo getDetectAreaInfo() {
        return mMaskView.getDetectInfo();
    }
}
