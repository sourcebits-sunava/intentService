package com.sourcebits.intentservice;


import com.sourcebits.intentservice.AnswerResultReceiver.Receiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener,Receiver{

	String temp;
	AnswerResultReceiver mReceiver;
	TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mReceiver = new AnswerResultReceiver(new Handler());
		mReceiver.setReceiver(this);

	}
	
	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) 
	{
		text = (TextView)findViewById(R.id.display_result);
		Log.i("heloo",resultData.getString("result"));
		text.setText(resultData.getString("result"));
		
		
	}

	@Override
	public void onClick(View view) 
	{
		 EditText num1= (EditText)findViewById(R.id.op1);
		 EditText num2= (EditText)findViewById(R.id.op2);
		 
		 String a = num1.getText().toString();
		 String b = num2.getText().toString();
		 
		 if(view.getId()==R.id.Btn1_id) 
		 {
			 temp = "Addition";
		 }
		 if(view.getId()==R.id.Btn2_id) 
		 {
			 temp = "Substraction";
			 
		 }
		 if(view.getId()==R.id.Btn3_id) 
		 {
			 
			 temp = "Multiplication";
		 }
		 if(view.getId()==R.id.Btn4_id) 
		 {
			 
			 temp = "Division";
		 }
		
		Intent intent = new Intent(this, CalcutaleService.class);
		intent.putExtra("a", a);
		intent.putExtra("b", b);
		intent.putExtra("temp", temp);
		intent.putExtra("receiver", mReceiver);
		startService(intent);

	}
	@Override
	protected void onPause() 
	{
		//to avoid leaks
		mReceiver.setReceiver(null);
		super.onPause();
	}
	
	@Override
	protected void onResume()
	{
		//to avoid leaks
		mReceiver.setReceiver(this);
		super.onResume();
	}
	

}
