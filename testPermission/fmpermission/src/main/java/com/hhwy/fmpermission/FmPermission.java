package com.hhwy.fmpermission;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import android.Manifest;
import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author：sun
 * project：PictureSelector
 * package：com.hhwy.fm_permission
 * email：893855882@qq.com
 * data：2019/04/24
 */
public class FmPermission {
    public static enum FmPermissionType{
        CALENDAR, CAMERA, CONTACTS, LOCATION, MICROPHONE, PHONE, SENSORS, SMS, STORAGE, ACCOUNTS;
    }

    public static Activity activity;
    // 日历
    public static final String[] CALENDAR = {
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR
    };
    // 相机
    public static final String[] CAMERA = {
            Manifest.permission.CAMERA
    };
    // 联系人
    public static final String[] CONTACTS = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS
    };
    // 定位
    public static final String[] LOCATION = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    // 麦克风
    public static final String[] MICROPHONE = {
            Manifest.permission.RECORD_AUDIO
    };
    // 电话
    public static final String[] PHONE = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS
    };
    public static final String[] SENSORS = {
            Manifest.permission.BODY_SENSORS
    };
    public static final String[] SMS = {
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS,
            "android.permission.READ_CELL_BROADCASTS"
    };
    public static final String[] STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    // 访问账户Gmail列表
    public static final String[] ACCOUNTS = {
            Manifest.permission.GET_ACCOUNTS
    };

    public static final String[][] perArray = {
            CALENDAR, CAMERA, CONTACTS, LOCATION, MICROPHONE, PHONE, SENSORS, SMS, STORAGE, ACCOUNTS
    };

    public static String[] concatArray(List collect) {
        String[] aa0 = {};
        // tyy 每次都是两个数组合并 所以合并的次数为 collect.size() ，第一个是虚拟的数组
        for (int i = 0; i < collect.size(); i++) {
            String[] aa1 = (String[]) collect.get(i);
            String[] newInt = merge(aa0, aa1);
            aa0 = newInt;
        }
        return aa0;
    }
    public static String[] merge(String[] a, String[] b) {
        String[] result = new String[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static void getPermission(Observer observer, FmPermissionType ...fps) {
        RxPermissions permissions = new RxPermissions(activity);
        String[] per;
        if(fps.length > 1) {
            List collect = new ArrayList();
            for (int j = 0; j < fps.length; ++j) {
                collect.add(perArray[fps[j].ordinal()]);
            }
            per = concatArray(collect);
        }else{
            per = perArray[fps[0].ordinal()];
        }
        permissions.request(per).subscribe(observer);
//        String[] permissions = {
//                Manifest.permission.RECORD_AUDIO,
//                Manifest.permission.CAMERA,
//                Manifest.permission.MODIFY_AUDIO_SETTINGS,
//                Manifest.permission.INTERNET,
//        };
//        Permission.requestPermissions(FmFragment.getFragment(activity), new Permission.PermissionReceiver(permissions,
//                0X20) {
//            @Override
//            public void onRequestPermissionsResult(boolean granted) {
//                System.out.println("-============="+granted);
//            }
//        });
    }
}
