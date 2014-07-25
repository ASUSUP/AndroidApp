package com.example.availablecourses;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Courses extends ListActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_courselist);
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.available_courses)));

	    ListView list = getListView();
	    
	    list.setTextFilterEnabled(true);
	    list.setOnItemClickListener(new OnItemClickListener(){

	    	 @Override
	         public void onItemClick(AdapterView<?> arg0, View view, int arg2,
	                 long arg3) {

	             switch(arg2)

	             {
	               case 0 : Intent myIntent1 = new Intent(view.getContext(), QuizWindow.class);
	                        startActivityForResult(myIntent1, 0);
	                        break;
	               case 1 : Intent myIntent2 = new Intent(view.getContext(), QuizWindow.class);
	                        startActivityForResult(myIntent2, 0);
	                        break;
	             }
	         }
	     });
	 }


}