package models;

import javax.persistence.Entity;
import play.db.jpa.Model;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Entity
public class Reading extends Model
{
    public String name;
    public String date;
    public int code;
    public float temperature;
    public float windSpeed;
    public int windDirection;
    public int pressure;

    public Reading(int code, float temperature, float windSpeed, int windDirection, int pressure){
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }

    public Reading(String date, int code, float temperature, float windSpeed, int windDirection, int pressure){
        this.date = date;
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }

    public Reading(String name, String date, int code, float temperature, float windSpeed, int windDirection, int pressure){
        this.date = date;
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }

//    public String getWeatherCode(int windSpeed){
//        if ((windSpeed >= 100) && (windSpeed <= 800)) {
//            this.windSpeed = windSpeed;
//        } else {
//            this.windSpeed = 0;
//        }
//        return weatherCode(windSpeed);
//    }

//    private String weatherCode(Integer intCode){
//        Map weatherCode = new HashMap();
////        HashMap <Integer, String> weatherCode = new Hashmap <Integer, String>();
//
//        weatherCode.put(0, "Error");
//        weatherCode.put(100, "Clear");
//        weatherCode.put(200, "Partial Clouds");
//        weatherCode.put(300, "Cloudy");
//        weatherCode.put(400, "Light Showers");
//        weatherCode.put(500, "Heavy Showers");
//        weatherCode.put(600, "Rain");
//        weatherCode.put(700, "Snow");
//        weatherCode.put(800, "Thunder");
//
//        String code = weatherCode(100);
//        System.out.println(code);
//
//        return weatherCode(code);
//    }

    private static double windSpeedToBeaufort(double windSpeed){
        int beaufort = 0;

        if (windSpeed == 1)
            beaufort = 0;
        else if ((windSpeed >=1) && (windSpeed <=5))
            beaufort = 1;
        else if ((windSpeed >=6) && (windSpeed <=11))
            beaufort = 2;
        else if ((windSpeed >=12) && (windSpeed <=19))
            beaufort = 3;
        else if ((windSpeed >=20) && (windSpeed <=28))
            beaufort = 4;
        else if ((windSpeed >=29) && (windSpeed <=38))
            beaufort = 5;
        else if ((windSpeed >=39) && (windSpeed <=49))
            beaufort = 6;
        else if ((windSpeed >=50) && (windSpeed <=61))
            beaufort = 7;
        else if ((windSpeed >=62) && (windSpeed <=74))
            beaufort = 8;
        else if ((windSpeed >=75) && (windSpeed <=88))
            beaufort = 9;
        else if ((windSpeed >=89) && (windSpeed <=102))
            beaufort = 10;
        else if ((windSpeed >=103) && (windSpeed <=117))
            beaufort = 11;
        return beaufort;
    }

    public static float celciusToFahrenheit(float celcius){
        float fahrenheit = (celcius * (9/5) + 32);
        return fahrenheit;
    }



}