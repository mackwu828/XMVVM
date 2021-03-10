package com.mackwu.base.util;

import android.app.Activity;

import java.util.Stack;

/**
 * ===================================================
 * Created by MackWu on 2020/7/28 16:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ActivityStackUtil {

    private static final Stack<Activity> activityStack = new Stack<>();

    /**
     * add activity
     */
    public static void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * remove activity
     */
    public static void removeActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * finish activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * finish activity
     */
    public static void finishActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null && activity.getClass().equals(cls)) {
                activityStack.remove(activity);
                activity.finish();
                break;
            }
        }
    }

    /**
     * finish all activity
     */
    public static void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * finish all activity
     *
     * @param cls 除了该activity
     */
    public static void finishAllActivityExcept(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null && !activity.getClass().equals(cls)) {
                activity.finish();
            }
        }
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null && !activity.getClass().equals(cls)) {
                activityStack.remove(activity);
            }
        }
    }

    public static void finishAllActivityExcept(Class<?> cls1, Class<?> cls2) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null && !activity.getClass().equals(cls1) && !activity.getClass().equals(cls2)) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * get activity
     */
    public static Activity getActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null && activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 获取栈顶的activity
     */
    public static Activity getTopActivity() {
        if (activityStack.size() > 0) {
            return activityStack.get(activityStack.size() - 1);
        }
        return null;
    }

    /**
     * activity是否存在
     */
    public static boolean isActivityExist(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

}
