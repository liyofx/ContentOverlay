package com.dock.demo.contentoverlay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Other1Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other1);
	}

	@Override
	protected void leftBack() {
		finish();
	}
	
	public void go(View v){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
