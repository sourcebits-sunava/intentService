package com.sourcebits.intentservice;

import android.os.Handler;
import android.os.ResultReceiver;
import android.os.Bundle;

public class AnswerResultReceiver extends ResultReceiver 
{
    private Receiver mReceiver;

    public AnswerResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
        
    }
}