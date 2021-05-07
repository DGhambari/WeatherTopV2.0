package models;


import javax.persistence.Entity;
import play.db.jpa.Model;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Calendar;

@Entity
public class Reading extends Model
{
    public String name;
    public String date;
    public int code;
    public double temperature;
    public double windSpeed;
    public int windDirection;
    public int pressure;


    public Reading(int code, double temperature, double windSpeed, int windDirection, int pressure){

        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }

}