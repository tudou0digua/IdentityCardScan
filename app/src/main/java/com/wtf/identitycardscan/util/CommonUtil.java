package com.wtf.identitycardscan.util;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by wtf on 2018/10/15.
 */
public class CommonUtil {

    /**
     * Check if this device has a camera
     * @param context
     * @return
     */
    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
}
