package com.example.weatherupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout editText;
    private TextView showTemperature,minTemperature,maxTemperature,humidity,pressure;
    private Button button;
    private static String URL = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    private String apikeys = "e91e17b02b55b25c332655d160bbd6bf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editText = (TextInputLayout) findViewById(R.id.editTextId);
        showTemperature = findViewById(R.id.showTemperatureTextViewId);
        minTemperature = findViewById(R.id.minTemperatureTextViewId);
        maxTemperature = findViewById(R.id.maxTemperatureTextViewId);
        pressure = findViewById(R.id.pressureTextViewId);
        humidity = findViewById(R.id.humidityTextViewId);

        button = (Button)findViewById(R.id.buttonId);
    }


    public void getWeather(View view){


        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherapi myapi = retrofit.create(weatherapi.class);
        Call<Example>example = myapi.getWeather(editText.getEditText().getText().toString().trim(),apikeys);



        example.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                Example mydata = response.body();
                Main main = mydata.getMain();

                Double temp = main.getTemp();

                showTemperature.setText("Temperature : "+(temp-273)+"°C");

                temp = main.getTempMax();;
                maxTemperature.setText("Max : "+(temp-273)+"°C");

                temp = main.getTempMin();
                minTemperature.setText("Min :"+(temp-273)+"°C");

                Integer p = main.getPressure();
                pressure.setText("Pressure : "+p);

                p = main.getHumidity();
                humidity.setText("Humidity : "+p);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });


    }


}