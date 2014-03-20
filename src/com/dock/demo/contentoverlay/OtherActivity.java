package com.dock.demo.contentoverlay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OtherActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
	}

	@Override
	protected void leftBack() {
		finish();
	}
	
	public void go(View v){
		Intent intent = new Intent(this, Other1Activity.class);
		startActivity(intent);
	}
}
