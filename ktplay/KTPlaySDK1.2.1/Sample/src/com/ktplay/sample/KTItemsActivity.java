package com.ktplay.sample;

import android.content.Intent;
import android.os.Bundle;

public class KTItemsActivity extends BaseListActivity{

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public String[] items() {
		Intent intent = this.getIntent();
		String[] a = intent.getStringArrayExtra("list");
		return a;
	}

}
