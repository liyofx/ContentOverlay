package com.dock.demo.contentoverlay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void go(View v){
		Intent intent = new Intent(this, OtherActivity.class);
		startActivity(intent);
	}

	@Override
	protected void leftBack() {
		// TODO Auto-generated method stub
		
	}
}
