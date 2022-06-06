package course.labs.activitylab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOne extends Activity {

	// Use these as keys when you're saving state between reconfigurations
	/*
	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";
*/
	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";

	// Lifecycle counters

	// TODO:
	// Create variables named
	// mCreate, mRestart, mStart and mResume
	// to count calls to onCreate(), onRestart(), onStart() and
	// onResume(). These variables should not be defined as static.

	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called.
		int mCreate1 = 0;
		int mRestart1 = 0;
		int mStart1 = 0;
		int mResume1 = 0;

	// TODO: Create variables for each of the TextViews
	// named mTvCreate, mTvRestart, mTvStart, mTvResume.
	// for displaying the current count of each counter variable
		TextView mTvCreate1;
		TextView mTvRestart1;
		TextView mTvStart1;
		TextView mTvResume1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);

		// TODO: Assign the appropriate TextViews to the TextView variables
		// Hint: Access the TextView by calling Activity's findViewById()
		// textView1 = (TextView) findViewById(R.id.textView1);
		mTvCreate1 = findViewById(R.id.create1);
		mTvRestart1 = findViewById(R.id.restart1);
		mTvStart1 = findViewById(R.id.start1);
		mTvResume1 = findViewById(R.id.resume1);

		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo);
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO:
				// Launch Activity Two
				// Hint: use Context's startActivity() method

				// Create an intent stating which Activity you would like to
				// start
				Intent intent = null;

				// Launch the Activity using the intent
				intent = new Intent(ActivityOne.this, ActivityTwo.class);
				startActivity(intent);
			}
		});

		// Has previous state been saved?
		if (savedInstanceState != null) {

			// TODO:
			// Restore value of counters from saved state
			// Only need 4 lines of code, one for every count variable
			mCreate1 = savedInstanceState.getInt("create1");
			mRestart1 = savedInstanceState.getInt("restart1");
			mStart1 = savedInstanceState.getInt("start1");
			mResume1 = savedInstanceState.getInt("resume1");

		}

		// Emit LogCat message
		Log.i(TAG, "Entered the onCreate() method");

		// TODO:
		// Update the appropriate count variable
		mCreate1++;

		// Update the user interface via the displayCounts() method
		displayCounts();

	}

	// Lifecycle callback overrides

	@Override
	public void onStart() {
		super.onStart();

		// Emit LogCat message
		Log.i(TAG, "Entered the onStart() method");

		// TODO:
		// Update the appropriate count variable
		mStart1++;
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
		mResume1++;
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
		mRestart1++;
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
		// Save state information with a collection of key-value pairs
		// 4 lines of code, one for every count variable
		savedInstanceState.putInt("create1", mCreate1);
		savedInstanceState.putInt("start1", mStart1);
		savedInstanceState.putInt("restart1", mRestart1);
		savedInstanceState.putInt("resume1", mResume1);

	}

	// Updates the displayed counters
	// This method expects that the counters and TextView variables use the
	// names
	// specified above
	public void displayCounts() {

		// TODO - uncomment these lines
		mTvCreate1.setText("onCreate1() calls: " + mCreate1);
		mTvStart1.setText("onStart1() calls: " + mStart1);
		mTvResume1.setText("onResume1() calls: " + mResume1);
		mTvRestart1.setText("onRestart1() calls: " + mRestart1);
	}
}
