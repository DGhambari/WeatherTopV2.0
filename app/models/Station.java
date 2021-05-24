package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Station extends Model {
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();
  public String name;
  public double latitude;
  public double longitude;

  // Weather
  public String latestWeatherCondition;
  public String weatherIcon;

  //Temp
  public double latestTempC;
  public double latestTempF;
  public double maxTemp;
  public double minTemp;
  public String tempTrend;
  public String tempIcon;

  // Wind
  public int beaufort;
  public double latestWindSpeed;
  public String latestWindDirection;
  public String windChill;
  public String beaufortLabel;
  public double maxWindSpeed;
  public double minWindSpeed;
  public String windTrend;

  // Pressure
  public int latestPressure;
  public double maxPressure;
  public double minPressure;
  public String pressureTrend;

  public Station(String name) {
    this.name = name;
  }

  public Station(String name, double latitude, double longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
