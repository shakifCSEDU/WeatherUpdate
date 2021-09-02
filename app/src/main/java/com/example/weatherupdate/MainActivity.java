package com.example.weatherupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private Button button;
    private static String URL = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    private String apikeys = "e91e17b02b55b25c332655d160bbd6bf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    editText = (EditText)findViewById(R.id.editTextId);
    textView = (TextView)findViewById(R.id.textViewId);
    button = (Button)findViewById(R.id.buttonId);


    }
    public void getWeather(View view){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherapi myapi = retrofit.create(weatherapi.class);
        Call<Example>example = myapi.getWeather(editText.getText().toString().trim(),apikeys);
        example.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_SHORT).show();
                }
                Example mydata = response.body();
                Main main = mydata.getMain();
                Double temp = main.getTemp();
                Integer integer = (int)(temp-273);
                textView.setText(integer+" C");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });







    }


}