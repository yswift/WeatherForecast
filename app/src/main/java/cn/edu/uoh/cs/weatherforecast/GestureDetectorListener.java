package cn.edu.uoh.cs.weatherforecast;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * 手势识别，处理类
 * Created by yan on 2016/4/15.
 */
class GestureDetectorListener implements GestureDetector.OnGestureListener {
    MainActivity activity;

    GestureDetectorListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float minMove = 120;         //最小滑动距离
        float minVelocity = 0;      //最小滑动速度
        float beginX = e1.getX();
        float endX = e2.getX();
        float beginY = e1.getY();
        float endY = e2.getY();

        if (beginX - endX > minMove && Math.abs(velocityX) > minVelocity) {
            //左滑
            activity.next();
        } else if (endX - beginX > minMove && Math.abs(velocityX) > minVelocity) {
            //右滑
            activity.previous();
        } else if (beginY - endY > minMove && Math.abs(velocityY) > minVelocity) {
            //上滑
        } else if (endY - beginY > minMove && Math.abs(velocityY) > minVelocity) {
            //下滑
        }
        return false;
    }
}
