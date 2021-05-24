package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.Date;
import java.time.Instant;


@Entity
public class Reading extends Model {
  public String name;
  public int code;
  public double temperature;
  public double windSpeed;
  public int windDirection;
  public int pressure;
  public Date d;
  public Date date;
  public Instant i;

  public Reading(int code, double temperature, double windSpeed, int windDirection, int pressure) {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;
    d = new Date();
    i = Instant.now();
    date = (d.from(i));
  }

}