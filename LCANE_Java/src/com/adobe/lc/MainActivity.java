package com.adobe.lc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getIntent().getIntExtra("layoutID", -1));
		
		findViewById(getIntent().getIntExtra("btnID", -1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, getIntent().getStringExtra("asMsg"), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
