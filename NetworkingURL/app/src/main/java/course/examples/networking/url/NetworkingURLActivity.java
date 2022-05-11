package course.examples.networking.url;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NetworkingURLActivity extends Activity {
    static final String TAG = "NetworkingURLActivity";

    private TextView mTextView;
	private ImageView mImageView;
    private RetainedFragment mRetainedFragment;
    private final static String MTEXTVIEW_TEXT_KEY = "MTEXTVIEW_TEXT_KEY";

    List<Country> mCountries;

    Drawable mDraw = null;

    private static final String URL = "http://api.geonames.org/countryInfoJSON?username=caoth";

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        mTextView = findViewById(R.id.textView);
        mImageView = findViewById(R.id.imageView);

        /*
        if (null != savedInstanceState) {
            mRetainedFragment = (RetainedFragment) getFragmentManager()
                    .findFragmentByTag(RetainedFragment.TAG);
            mTextView.setText(savedInstanceState.getCharSequence(MTEXTVIEW_TEXT_KEY));
        } else {

            mRetainedFragment = new RetainedFragment();
            getFragmentManager().beginTransaction()
                    .add(mRetainedFragment, RetainedFragment.TAG)
                    .commit();
        }*/
    }

	public void onClick(@SuppressWarnings("unused") View v) {
        //mRetainedFragment.onButtonPressed();
        onButtonPressed();
	}

    //@Override
    public void onDownloadfinished(String result, List<Country> iCountries) {
        //mTextView.setText(result);

        // caoth
        mCountries = iCountries;
        final Country c =  mCountries.get(7);

        mTextView.setText(c.name);

        // display c.Map
//        final Drawable draw = null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mDraw = LoadImageFromWebOperations(c.urlMapImage);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mDraw != null) {
                            mImageView.setImageDrawable(mDraw);
                        }
                    }
                });
            }
        }).start();




        int debug = 0;
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(MTEXTVIEW_TEXT_KEY,mTextView.getText());
    }


    private void onButtonPressed() {
        //new HttpGetTask(this).execute();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // load from network
                //final String result = "";
                final List<Country> result = loadFromNetwork();

                // run on ui thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //onDownloadFinished(result);
                        onDownloadfinished("", result);
                    }
                });
            }
        }).start();
    }

    //private String loadFromNetwork() {
    private List<Country> loadFromNetwork() {
        String data = null;
        List<Country> result = null;
        HttpURLConnection httpUrlConnection = null;

        try {
            // 1. Get connection. 2. Prepare request (URI)
            httpUrlConnection = (HttpURLConnection) new URL(URL)
                    .openConnection();

            // 3. This app does not use a request body
            // 4. Read the response
            InputStream in = new BufferedInputStream(
                    httpUrlConnection.getInputStream());

            data = readStream(in);

            // parse json string
            result = parseJsonString(data);

        } catch (MalformedURLException exception) {
            Log.e(TAG, "MalformedURLException");
        } catch (IOException exception) {
            Log.e(TAG, "IOException");
        } finally {
            if (null != httpUrlConnection) {
                // 5. Disconnect
                httpUrlConnection.disconnect();
            }
        }

        //return String data;

        return result;
    }

    private List<Country> parseJsonString(String data) {
        List<Country> result = new ArrayList<>();

        try {
            // Get top-level JSON Object - a Map
            JSONObject responseObject = (JSONObject) new JSONTokener(data).nextValue();

            // Extract value of "earthquakes" key -- a List
            JSONArray earthquakes = responseObject.getJSONArray("geonames");

            // Iterate over earthquakes list
            for (int idx = 0; idx < earthquakes.length(); idx++) {

                // Get single earthquake mData - a Map
                JSONObject c = (JSONObject) earthquakes.get(idx);

                // Summarize earthquake mData as a string and add it to
                // result
                result.add(new Country(c.getString("countryName"), c.getString("countryCode")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder data = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "IOException");
                }
            }
        }
        return data.toString();
    }

}