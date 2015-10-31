package com.ktplay.sample;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public abstract class BaseListActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,this.items()));
	}
	
	public abstract String[] items();
}
