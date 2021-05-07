package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model
{
    public String name;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    public String latestWeatherCondition;
    public double latestTempC;
    public int beaufort;
    public String beaufortLabel;
    public double latestTempF;
    public double latestWindSpeed;
    public String latestWindDirection;
    public String windChill;
    public int latestPressure;

    public Station(String name){
        this.name = name;
    }

    public Station(String name, String latestWeatherCondition, float latestTempC, float latestTempF, int latestWindSpeed, int latestPressure){
        this.name = name;
        this.latestWeatherCondition = latestWeatherCondition;
        this.latestTempC = latestTempC;
        this.latestTempF = latestTempF;
        this.latestWindSpeed = latestWindSpeed;
        this.latestPressure = latestPressure;
    }

}
