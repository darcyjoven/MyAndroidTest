package com.example.darcy.activitytest;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darcy on 2017/5/28 0028.
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();
    public static  void addActicity(Activity activity){
        activities.add(activity);
    }
    public  static void removeActicity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
