package com.mpaas.demo.ocr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mpaas.ocr.Constant;
import com.mpaas.ocr.api.DetectAreaInfo;
import com.mpaas.ocr.api.IDetectCallback;
import com.mpaas.ocr.api.IDetectViewProvider;
import com.mpaas.ocr.api.IFlash;
import com.mpaas.ocr.api.MPOCR;
import com.mpaas.ocr.api.OCRResult;


/**
 * Created by ciwei on 18/6/17.
 */
public class MainActivity extends Activity implements IDetectCallback, IDetectViewProvider, View.OnClickListener {

    private TextView mResult;
    private Button mFrontIdCard, mBankCard, mBackIdCard;
    private ImageView mFullImage, mDetectImage;
    MaskView mDetectMaskView;
    private int mDetectType = Constant.BIZ_TYPE_BANK_CARD_RECOGNIZE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mResult = findViewById(R.id.result);
        mFullImage = findViewById(R.id.full_img);
        mDetectImage = findViewById(R.id.detect_img);
        mBackIdCard = findViewById(R.id.id_back);
        mBackIdCard.setOnClickListener(this);
        mFrontIdCard = findViewById(R.id.id_front);
        mFrontIdCard.setOnClickListener(this);
        mBankCard = findViewById(R.id.bankcard);
        mBankCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.id_front:
                mDetectType = Constant.DETECT_RESULT_TYPE_ID_CARD_FRONT;
                clearResult();
                MPOCR.startDetectIDCardFront(MainActivity.this, MainActivity.this, MainActivity.this);
                break;
            case R.id.id_back:
                clearResult();
                mDetectType = Constant.DETECT_RESULT_TYPE_ID_CARD_BACK;
                MPOCR.startDetectIDCardBack(MainActivity.this, MainActivity.this, MainActivity.this);
                break;
            case R.id.bankcard:
                clearResult();
                mDetectType = Constant.DETECT_RESULT_TYPE_BANCK;
                MPOCR.startDetectBankCard(MainActivity.this, MainActivity.this, MainActivity.this);
                break;
        }
    }

    private void clearResult() {
        mFullImage.setImageBitmap(null);
        mDetectImage.setImageBitmap(null);
        mResult.setText(null);
    }

    @Override
    public void onResult(OCRResult ocrResult) {
        if (ocrResult.code == OCRResult.RESULT_CODE_OK) {
            mFullImage.setImageBitmap(ocrResult.fullBitmap);
            mDetectImage.setImageBitmap(ocrResult.detectBitmap);
            String info = "";
            for (OCRResult.LabelInfo txt : ocrResult.labels) {
                info = info + txt.mLabel + '\n';
            }
            mResult.setText(info);
        } else {
            mResult.setText("ErrorCode:" + ocrResult.code + " msg:" + ocrResult.errMsg);
        }
    }

    @Override
    public void attachDetectContext(Activity activity, IFlash iFlash) {
        mDetectMaskView = new MaskView(activity);
        mDetectMaskView.attachDetectContext(activity, iFlash);
    }

    @Override
    public void updateDetectType(int i) {
        mDetectMaskView.updateDetectType(i);
    }

    @Override
    public void updateFlashMode(boolean b) {
        mDetectMaskView.updateFlashMode(b);
    }

    @Override
    public DetectAreaInfo getDetectAreaInfo() {
        return mDetectMaskView.getDetectAreaInfo();
    }

    @Override
    public void onDetectActivityPause() {
        mDetectMaskView.onDetectActivityPause();
    }

    @Override
    public View getMaskView() {
        return mDetectMaskView.getMaskView();
    }

}


