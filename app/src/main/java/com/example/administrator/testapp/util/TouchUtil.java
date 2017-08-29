package com.example.administrator.testapp.util;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/8/8.
 */

public class TouchUtil {

    public static void logTouch(MotionEvent event, String methodWhere) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("fff","onTouchEvent---"+methodWhere+"--->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("fff","onTouchEvent---"+methodWhere+"--->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("fff","onTouchEvent---"+methodWhere+"--->ACTION_UP");
                break;
        }
    }
}
