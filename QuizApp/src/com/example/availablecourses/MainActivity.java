package com.example.availablecourses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


class UsernameAndPassword{
	public String password = "101010";
	public String username = "salma";
	
	public  Boolean isCorrect (String pass, String user){
		if(password.equals(pass) && username.equals(user)){
			return true;
		}
		else{
			return false;
		}
	}
}
public class MainActivity extends Activity {
	
	EditText username;
	EditText password;
	Button Login;
	UsernameAndPassword dataChecker;
	TextView exist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataChecker = new UsernameAndPassword();
        setContentView(R.layout.activity_main);
        username= (EditText) findViewById(R.id.editText1);
        password= (EditText) findViewById(R.id.editText2);
        Login= (Button) findViewById(R.id.button1);
        exist=  (TextView) findViewById(R.id.textView3);
        exist.getText().toString();
        Login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Boolean x = dataChecker.isCorrect(password.getText().toString(),username.getText().toString());
		        if(x){
		        	exist.setText("Logging In, please wait.");
		        	Intent i = new Intent(getApplicationContext(), Courses.class);
		        	startActivity(i);
		        }
		        else{
		        	exist.setText("User does not exist or wrong password.");
		        }
			}
        	
        });
    }
    
}
