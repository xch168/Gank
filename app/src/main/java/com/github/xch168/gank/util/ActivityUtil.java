package com.github.xch168.gank.util;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by XuCanHui on 2017/7/13.
 */

public class ActivityUtil {

    public static void addFragment2Activity(FragmentManager fragmentManager, int frameId, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}
