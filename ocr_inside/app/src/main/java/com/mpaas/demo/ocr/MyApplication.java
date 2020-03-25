package com.mpaas.demo.ocr;

import android.os.Debug;
import android.util.Log;

import com.alipay.android.phone.mobilecommon.multimedia.api.MultimediaVideoService;
import com.alipay.mobile.framework.LauncherApplicationAgent;
import com.alipay.mobile.framework.quinoxless.QuinoxlessApplicationLike;

public class MyApplication extends QuinoxlessApplicationLike {

    @Override
    public void onMPaaSFrameworkInitFinished() {
        Log.d("test_mpaas", "----onMPaaSFrameworkInitFinished");
        MultimediaVideoService service = LauncherApplicationAgent.getInstance().getMicroApplicationContext().findServiceByInterface(MultimediaVideoService.class.getName());
        Log.d("test_mpaas", "----onMPaaSFrameworkInitFinished MultimediaVideoService is null:" + (service == null));
    }
}
