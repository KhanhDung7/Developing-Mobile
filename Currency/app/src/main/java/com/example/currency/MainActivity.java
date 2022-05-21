package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    Spinner spinNguon, spinDich;
    TextView txtDVNguon, txtKetQua;
    EditText editNumber;
    String valueNguon, valueDich;
    float tiGia = 0;
    int numberNguon = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Chuyển đổi tiền tệ");



        AnhXa();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tien));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinNguon.setAdapter(myAdapter);
        spinDich.setAdapter(myAdapter);

        spinNguon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = spinNguon.getSelectedItem().toString();
                valueNguon = text.substring(0, 3);
                txtDVNguon.setText(valueNguon);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinDich.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = spinDich.getSelectedItem().toString();
                valueDich = text.substring(0, 3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        editNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("wvan3011", valueNguon);
                Log.d("wvan3011", valueDich);

                String value = editNumber.getText().toString();

                if (value.matches("")) {
                    numberNguon = 0;
                    txtKetQua.setText("0 " + valueDich);
                    return;
                }

                numberNguon = Integer.valueOf(value);

                if (valueNguon.matches(valueDich)) {
                    Log.d("wvan3011", "ahoho");
                    txtKetQua.setText(numberNguon + " " + valueNguon);
                    return;
                }

                String url = "https://" + valueNguon.toLowerCase() + ".fxexchangerate.com/" + valueDich.toLowerCase() + ".xml";
                Log.d("wvan3011", url );
                new ReadRSS().execute(url);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void AnhXa() {
        spinNguon = (Spinner) findViewById(R.id.spinnerNguon);
        spinDich = (Spinner) findViewById(R.id.spinnerDich) ;
        txtDVNguon = (TextView) findViewById(R.id.textViewDVNguon);
        editNumber = (EditText) findViewById(R.id.editTextNumber);
        txtKetQua = (TextView) findViewById(R.id.textViewKetQua);
    }

    private class ReadRSS extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String xml) {
            super.onPostExecute(xml);

            Document document = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;
            try {
                db = factory.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                is.setEncoding("UTF-8");
                document = db.parse(is);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            Element root = document.getDocumentElement();
            String stringValue = root.getTextContent();

            stringValue = stringValue.split(" = ", 2)[1].split(" " + valueDich, 2)[0];

            Toast.makeText(MainActivity.this, stringValue, Toast.LENGTH_SHORT).show();

            tiGia = new BigDecimal(stringValue).floatValue();
            Log.d("wvan3011", String.valueOf(tiGia));
            txtKetQua.setText(tiGia * numberNguon + " " + valueDich);
        }
    }
}