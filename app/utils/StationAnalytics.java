package utils;

import models.Reading;

import java.util.HashMap;

import java.text.DecimalFormat;
import java.util.List;

public class StationAnalytics {

    public static int windSpeedToBeaufort(double windSpeed){

            int beaufort = 0;

            if (windSpeed == 1)
                beaufort = 0;
            else if ((windSpeed >= 1) && (windSpeed <= 5))
                beaufort = 1;
            else if ((windSpeed >= 6) && (windSpeed <= 11))
                beaufort = 2;
            else if ((windSpeed >= 12) && (windSpeed <= 19))
                beaufort = 3;
            else if ((windSpeed >= 20) && (windSpeed <= 28))
                beaufort = 4;
            else if ((windSpeed >= 29) && (windSpeed <= 38))
                beaufort = 5;
            else if ((windSpeed >= 39) && (windSpeed <= 49))
                beaufort = 6;
            else if ((windSpeed >= 50) && (windSpeed <= 61))
                beaufort = 7;
            else if ((windSpeed >= 62) && (windSpeed <= 74))
                beaufort = 8;
            else if ((windSpeed >= 75) && (windSpeed <= 88))
                beaufort = 9;
            else if ((windSpeed >= 89) && (windSpeed <= 102))
                beaufort = 10;
            else if ((windSpeed >= 103) && (windSpeed <= 117))
                beaufort = 11;
        System.out.print("Beaufort calculation is: " + beaufort);

        return beaufort;
    }

    public static String beaufortToBeaufortLabel(int beaufort){
        String beaufortLabel = "";
        switch (beaufort) {
            case 1:
                beaufortLabel = "Calm";
                break;
            case 2:
                beaufortLabel = "Light Air";
                break;
            case 3:
                beaufortLabel = "Light Breeze";
                break;
            case 4:
                beaufortLabel = "Moderate Breeze";
                break;
            case 5:
                beaufortLabel = "Fresh Breeze";
                break;
            case 6:
                beaufortLabel = "Strong Breeze";
                break;
            case 7:
                beaufortLabel = "Near Gale";
                break;
            case 8:
                beaufortLabel = "Severe Gale";
                break;
            case 9:
                beaufortLabel = "Strong Storm";
                break;
            case 10:
                beaufortLabel = "Violent Storm";
                break;
            default:
                System.out.println("Invalid option entered: " + beaufort);
                break;
            }
        return beaufortLabel;
    }

    public static String degreesToWindDirection(double degrees){
        String windDirection = "null";

        if ((degrees >= 0) && (degrees <= 11.25))
            windDirection = "North";
        else if ((degrees > 11.25) && (degrees <= 33.75))
            windDirection = "North North East";
        else if ((degrees > 33.75) && (degrees <= 56.25))
            windDirection = "North East";
        else if ((degrees > 56.25) && (degrees <= 78.75))
            windDirection = "East North East";
        else if ((degrees > 78.75) && (degrees <= 101.25))
            windDirection = "East";
        else if ((degrees > 101.25) && (degrees <= 123.75))
            windDirection = "East South East";
        else if ((degrees > 123.75) && (degrees <= 146.25))
            windDirection = "South East";
        else if ((degrees > 146.25) && (degrees <= 168.75))
            windDirection = "South South East";
        else if ((degrees > 168.75) && (degrees <= 191.25))
            windDirection = "South";
        else if ((degrees > 191.25) && (degrees <= 213.75))
            windDirection = "South South West";
        else if ((degrees > 213.75) && (degrees <= 236.25))
            windDirection = "South West";
        else if ((degrees > 236.25) && (degrees <= 258.75))
            windDirection = "West South West";
        else if ((degrees > 258.75) && (degrees <= 281.25))
            windDirection = "West";
        else if ((degrees > 281.25) && (degrees <= 303.75))
            windDirection = "West North West";
        else if ((degrees > 303.75) && (degrees <= 326.25))
            windDirection = "North West";
        else if ((degrees > 326.25) && (degrees <= 348.75))
            windDirection = "North North West";
        else if ((degrees >= 348.75) && (degrees <= 360))
            windDirection = "North";

        return windDirection;
    }

    public static String weatherCode(Integer code){

        HashMap<Integer, String> weatherCode = new HashMap<>();

        weatherCode.put(0, "Error");
        weatherCode.put(100, "Clear");
        weatherCode.put(200, "Partial Clouds");
        weatherCode.put(300, "Cloudy");
        weatherCode.put(400, "Light Showers");
        weatherCode.put(500, "Heavy Showers");
        weatherCode.put(600, "Rain");
        weatherCode.put(700, "Snow");
        weatherCode.put(800, "Thunder");

        return weatherCode.get(code);
    }

    public static double celsiusToFahrenheit(double temperatureC){
        return ((temperatureC * (1.8)) + 32);
    }

    public static String windChill(double temperatureC, double windSpeed){
        DecimalFormat df=new DecimalFormat("0.##");
        double result = (13.12 + (0.6215 * temperatureC) - (11.37 * (Math.pow(windSpeed, 0.16))) + (0.3965 * temperatureC * (Math.pow(windSpeed, 0.16))));
        return df.format(result);
    }

    public static double maxWindSpeed(List<Reading> readings){
        double max = 0;
        for (Reading reading : readings){
            if (reading.windSpeed > max){
                max = reading.windSpeed;
            }
        }
        return max;
    }

    public static double minWindSpeed(List<Reading> readings){
        double min = readings.get(0).windSpeed;
        for (Reading reading : readings){
            if (reading.windSpeed < min){
                min = reading.windSpeed;
            }
        }
        return min;
    }
}
