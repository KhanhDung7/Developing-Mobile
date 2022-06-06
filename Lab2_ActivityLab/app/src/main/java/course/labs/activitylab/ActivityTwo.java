package course.labs.activitylab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends Activity {

	// Use these as keys when you're saving state between reconfigurations
	/*
	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";
*/
	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityTwo";

	// Lifecycle counters

	// TODO:
	// Create variables named 	
	// mCreate, mRestart, mStart and mResume 	
	// to count calls to onCreate(), onRestart(), onStart() and
	// onResume(). These variables should not be defined as static.
	
	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called.
	int mCreate2 = 0;
	int mRestart2 = 0;
	int mStart2 = 0;
	int mResume2 = 0;
	
	// TODO: Create variables for each of the TextViews
	// named  mTvCreate, mTvRestart, mTvStart, mTvResume.
	// for displaying the current count of each counter variable
	TextView mTvCreate2;
	TextView mTvRestart2;
	TextView mTvStart2;
	TextView mTvResume2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);

		// TODO: Assign the appropriate TextViews to the TextView variables
		// Hint: Access the TextView by calling Activity's findViewById()
		// textView1 = (TextView) findViewById(R.id.textView1);
		mTvCreate2 = findViewById(R.id.create2);
		mTvRestart2 = findViewById(R.id.restart2);
		mTvStart2 = findViewById(R.id.start2);
		mTvResume2 = findViewById(R.id.resume2);
		
		Button closeButton = (Button) findViewById(R.id.bClose); 
		closeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO:
				// This function closes Activity Two
				// Hint: use Context's finish() method
				finish();
			
			}
		});

		// Has previous state been saved?
		if (savedInstanceState != null) {

			// TODO:
			// Restore value of counters from saved state
			// Only need 4 lines of code, one for every count variable
			mCreate2 = savedInstanceState.getInt("create2");
			mRestart2 = savedInstanceState.getInt("restart2");
			mStart2 = savedInstanceState.getInt("start2");
			mResume2 = savedInstanceState.getInt("resume2");
			
		}

		// Emit LogCat message
		Log.i(TAG, "Entered the onCreate() method");

		// TODO:
		// Update the appropriate count variable
		mCreate2++;
		// Update the user interface via the displayCounts() method
		displayCounts();
		
	}

	// Lifecycle callback methods overrides

	@Override
	public void onStart() {
		super.onStart();

		// Emit LogCat message
		Log.i(TAG, "Entered the onStart() method");

		// TODO:
		// Update the appropriate count variable
		mStart2++;
		// Update the user interface
		displayCounts();
		
	}

	@Override
	public void onResume() {
		super.onResume();

		// Emit LogCat message
		Log.i(TAG, "Entered the onResume() method");

		// TODO:
		// Update the appropriate count variable
		mResume2++;
		// Update the user interface
		displayCounts();
	
	}

	@Override
	public void onPause() {
		super.onPause();

		// Emit LogCat message
		Log.i(TAG, "Entered the onPause() method");
	}

	@Override
	public void onStop() {
		super.onStop();

		// Emit LogCat message
		Log.i(TAG, "Entered the onStop() method");
	}

	@Override
	public void onRestart() {
		super.onRestart();

		// Emit LogCat message
		Log.i(TAG, "Entered the onRestart() method");

		// TODO:
		// Update the appropriate count variable
		mRestart2++;
		// Update the user interface
		displayCounts();
	
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		// Emit LogCat message
		Log.i(TAG, "Entered the onDestroy() method");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		// TODO:
		// Save counter state information with a collection of key-value pairs
		// 4 lines of code, one for every count variable
		savedInstanceState.putInt("create2", mCreate2);
		savedInstanceState.putInt("start2", mStart2);
		savedInstanceState.putInt("restart2", mRestart2);
		savedInstanceState.putInt("resume2", mResume2);
		
	}

	// Updates the displayed counters
	// This method expects that the counters and TextView variables use the
	// names
	// specified above
	public void displayCounts() {

		// TODO - uncomment these lines
		mTvCreate2.setText("onCreate2() calls: " + mCreate2);
		mTvStart2.setText("onStart2() calls: " + mStart2);
		mTvResume2.setText("onResume2() calls: " + mResume2);
		mTvRestart2.setText("onRestart2() calls: " + mRestart2);

	}
}
