package com.sourcebits.intentservice;

import android.app.IntentService;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.widget.Toast;

public class CalcutaleService extends IntentService {
	Double num1, num2;
	double result;
	String operator;
	public static final int CALCULATION_INPROGRESS = 0;

	public CalcutaleService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		final ResultReceiver receiver = intent.getParcelableExtra("receiver");
		Toast.makeText(this, "intent service is started", Toast.LENGTH_SHORT).show();
		Bundle extras = intent.getExtras();
		num1 = Double.parseDouble(extras.getString("a"));
		num2 = Double.parseDouble(extras.getString("b"));
		operator = extras.getString("temp");

		Bundle bundle = new Bundle();

		if (operator == "Addition") {

			result = num1 + num2;
		}
		if (operator == "Substraction") {
			result = num1 - num2;

		}
		if (operator == "Multiplication") {

			result = num1 * num2;
		}
		if (operator == "Division") {

			result = num1 / num2;
		}

		String resultString = Double.toString(result);
		bundle.putString("result", resultString);
		receiver.send(CALCULATION_INPROGRESS,bundle);
	}

}
