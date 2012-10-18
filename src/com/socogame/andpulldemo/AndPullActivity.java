package com.socogame.andpulldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AndPullActivity extends Activity implements OnClickListener {

	private Button btnStart, btnStop;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_and_pull);

		initView();
	}

	private void initView() {
		btnStart = (Button) findViewById(R.id.btn_start);
		btnStop = (Button) findViewById(R.id.btn_stop);

		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_and_pull, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
			startService(new Intent(this, PullService.class));
			break;
		case R.id.btn_stop:
			stopService(new Intent(this, PullService.class));
			break;

		default:
			break;
		}
	}
}
