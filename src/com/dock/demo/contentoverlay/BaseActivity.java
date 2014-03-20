package com.dock.demo.contentoverlay;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public abstract class BaseActivity extends Activity {
	private boolean mIsUnableToDrag = true;
	boolean mIsBeingDragged = false;
	private int touchSlop;
	private VelocityTracker mVelocityTracker;
	private int mMaximumVelocity;
	private MotionEvent downEvent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewConfiguration configuration = ViewConfiguration.get(getApplicationContext());
		touchSlop = getResources().getDimensionPixelSize(R.dimen.touch_slop);
		mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (isUnableToDrag(ev)) {
			final int action = ev.getAction() & MotionEventCompat.ACTION_MASK;
			if (mVelocityTracker == null)
				mVelocityTracker = VelocityTracker.obtain();
			mVelocityTracker.addMovement(ev);
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				if (downEvent != null)
					downEvent.recycle();
				downEvent = MotionEvent.obtain(ev);
				break;
			case MotionEvent.ACTION_MOVE:
				if (downEvent == null)
					downEvent = MotionEvent.obtain(ev);
				float dx = MotionEventCompat.getX(ev, 0) - downEvent.getX();
				float dy = 2.0F * Math.abs(MotionEventCompat.getY(ev, 0) - downEvent.getY());
				if (dx > (float) (3 * touchSlop) && dx > dy) {
					mIsBeingDragged = true;
					ev.setAction(3);
				}
				break;
			case MotionEvent.ACTION_UP:
				if (mIsBeingDragged) {
					VelocityTracker tracker = mVelocityTracker;
					tracker.computeCurrentVelocity(1000, mMaximumVelocity);
					float lastXVelocity = (int) tracker.getXVelocity();
					mIsBeingDragged = false;
					if (mVelocityTracker != null) {
						mVelocityTracker.recycle();
						mVelocityTracker = null;
					}
					if (lastXVelocity > 700F) {
						leftBack();
						ev.setAction(3);
					}
				}
				break;
			case MotionEvent.ACTION_CANCEL:
				mVelocityTracker.recycle();
				mVelocityTracker = null;
				mIsBeingDragged = false;
				break;
			}
			if (mIsBeingDragged)
				return true;
		}
		return super.dispatchTouchEvent(ev);
	}

	protected abstract void leftBack();

	protected boolean isUnableToDrag(MotionEvent ev) {
		return mIsUnableToDrag;
	}
}
