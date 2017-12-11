package com.nikcaryo.gizoogler;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;


public class ProcessGizoogle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String text = getIntent()
                .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString();
        boolean readonly =
                getIntent().getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false);

        if(readonly){
            Toast.makeText(this, "gizoogling and copying", Toast.LENGTH_SHORT).show();
            new copyGizoogled(this).execute(text);
        }

        else {
            new getGizoogled(this).execute(text);
        }
    }

    public class copyGizoogled extends AsyncTask<String,Void,String> {
        private Context context;

        public copyGizoogled(Context context){
            this.context=context;
        }
        @Override
        protected void onPreExecute() {
            // write show progress Dialog code here
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String ... strings) {
            String text = strings[0];
            return Gizoogler.gizoogle(text);
        }

        @Override
        protected void onPostExecute(String text_gizoogled) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("gizoogled", text_gizoogled);
            clipboard.setPrimaryClip(clip);
            ((Activity)context).finish();
        }
    }

    public class getGizoogled extends AsyncTask<String,Void,String> {
        private Context context;

        public getGizoogled(Context context){
            this.context=context;
        }
        @Override
        protected void onPreExecute() {
            // write show progress Dialog code here
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String ... strings) {
            String text = strings[0];
            return Gizoogler.gizoogle(text);
        }

        @Override
        protected void onPostExecute(String text_gizoogled) {
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_PROCESS_TEXT, text_gizoogled);
            ((Activity)context).setResult(RESULT_OK,intent);
            ((Activity)context).finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
}

