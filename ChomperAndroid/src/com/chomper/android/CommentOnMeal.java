package com.chomper.android;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CommentOnMeal extends Activity implements OnClickListener, TextWatcher{
    /** Called when the activity is first created. */

	private static final String TAG = "CommentStatus";
	
	TextView commentText;
	EditText commentTextEdit;
	Button submitCommentButton;
	TextView textCount;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        commentTextEdit = (EditText) findViewById(R.id.editText);
        commentTextEdit.addTextChangedListener(this);
        
        submitCommentButton = (Button) findViewById(R.id.buttonSubmitComment);
        commentText = (TextView) findViewById(R.id.commentText);
        submitCommentButton.setOnClickListener(this);
        
        textCount = (TextView) findViewById(R.id.textCount);
        textCount.setText(Integer.toString(20));
        textCount.setTextColor(Color.GREEN);
    }

	public void onClick(View v) {
		String comment = this.commentTextEdit.getText().toString();
		Log.d(TAG, "Ready to Post:" + comment);
		new PostComment().execute(comment);
        commentText.setText(getString(R.string.userDisplayedName) + ": " + comment);
		Log.d(TAG, "Comment Posted");
	}


	public void afterTextChanged(Editable s) {
		int count = 20 - s.length();
		textCount.setText(Integer.toString(count));
		
		textCount.setTextColor(Color.GREEN);
		if(count < 10 && count > 0){
			textCount.setTextColor(Color.YELLOW);
		}else if(count <= 0){
			textCount.setText("0");
			textCount.setTextColor(Color.RED);
		}
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}	
	
	
	
	
	
	
	class PostComment extends AsyncTask<String, Integer, String>{

		protected String doInBackground(String... comments) {
			try{
				String comment = (String)comments[0];	// load comment
		        Log.d(TAG, "AsyncTask: Ready to post comment: " + comment);
		        // post comment to server here.
		        return comment;
			} catch (Exception e) {
				Log.e(TAG, e.toString());
				e.printStackTrace();
				return "Failed to Post";
			}
		}
		
		protected void onProgressUpdate(Integer... values){
			super.onProgressUpdate(values);
		}
		
		protected void onPostExecute(String result){
			Toast.makeText(CommentOnMeal.this, "Comment Posted", Toast.LENGTH_LONG).show();
		}
		
		
		
	}

	
	
	
}