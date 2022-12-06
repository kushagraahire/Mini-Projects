package com.company.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    private TextView tvCityWeather, tvTempWeather, tvMaxTempWeather, tvMinTempWeather, tvPressureWeather,
            tvWindSpeedWeather, tvWeatherConditionWeather, tvHumidityWeather;
    private ImageView imageViewWeather;
    private EditText etCityName;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvCityWeather = findViewById(R.id.tvCityWeather);
        tvTempWeather = findViewById(R.id.tvTempWeather);
        tvMaxTempWeather = findViewById(R.id.tvMaxTempWeather);
        tvMinTempWeather = findViewById(R.id.tvMinTempWeather);
        tvPressureWeather = findViewById(R.id.tvPressureWeather);
        tvWindSpeedWeather = findViewById(R.id.tvWindSpeedWeather);
        imageViewWeather = findViewById(R.id.ivWeather);
        tvWeatherConditionWeather = findViewById(R.id.tvWeatherConditionWeather);
        tvHumidityWeather = findViewById(R.id.tvHumidityWeather);
        btnSearch = findViewById(R.id.btnSearch);
        etCityName = findViewById(R.id.etCityName);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = etCityName.getText().toString();
                if(cityName.equals("")){
                    etCityName.requestFocus();
                    etCityName.setError("Enter City Name");
                }else{
                    getWeatherData(cityName);
                }
            }
        });
    }

    public void getWeatherData(String name){
        WeatherAPI weatherAPI = RetrofitWeather.getClient().create(WeatherAPI.class);
        Call<OpenWeatherMap> call = weatherAPI.getWeatherWithCityName(name);

        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
                if(response.isSuccessful()){
                    tvCityWeather.setText(response.body().getName()+", "+response.body().getSys().getCountry());
                    tvTempWeather.setText(response.body().getMain().getTemp()+" °C");
                    tvWeatherConditionWeather.setText(response.body().getWeather().get(0).getDescription());
                    tvHumidityWeather.setText(" : "+response.body().getMain().getHumidity());
                    tvMaxTempWeather.setText(" : "+response.body().getMain().getTempMax());
                    tvMinTempWeather.setText(" : "+response.body().getMain().getTempMin());
                    tvPressureWeather.setText(" : "+response.body().getMain().getPressure());
                    tvWindSpeedWeather.setText(" : "+response.body().getWind().getSpeed());

                    String iconCode = response.body().getWeather().get(0).getIcon();
                    Picasso.get().load("https://openweathermap.org/img/wn/"+iconCode+"@2x.png")
                            .placeholder(R.color.SkyBlue)
                            .into(imageViewWeather);
                }else{
                    Toast.makeText(WeatherActivity.this, "City not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {

            }
        });
    }
}