package controllers;

import models.Station;
import models.Reading;

import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

import java.text.DecimalFormat;
import java.util.List;

public class StationCtrl extends Controller {
  public static void index(Long id) {
    Station station = Station.findById(id);
    Logger.info("Station id = " + id);
    List<Station> stations = Station.findAll();

    if (station.readings.size() > 0) {
      Reading lastReading = station.readings.get(station.readings.size() - 1);

      //  Weather
      station.latestWeatherCondition = StationAnalytics.weatherCode(lastReading.code);
      station.weatherIcon = StationAnalytics.weatherIcon(lastReading.code);

      //  Temp
      station.latestTempC = lastReading.temperature;
      station.latestTempF = StationAnalytics.celsiusToFahrenheit(lastReading.temperature);
      station.maxTemp = (double) StationAnalytics.getMaxReadings(station.readings).get(0);
      station.minTemp = (double) StationAnalytics.getMinReadings(station.readings).get(0);
      station.tempTrend = Dashboard.getTempTrend(station.readings);

      //  Wind
      station.beaufort = StationAnalytics.windSpeedToBeaufort(lastReading.windSpeed);
      station.latestWindSpeed = lastReading.windSpeed;
      station.latestWindDirection = StationAnalytics.degreesToWindDirection(lastReading.windDirection);
      station.windChill = StationAnalytics.windChill(station.latestTempC, station.latestWindSpeed);
      station.beaufortLabel = StationAnalytics.beaufortToBeaufortLabel(station.readings.size() - 1);
      station.maxWindSpeed = (double) StationAnalytics.getMaxReadings(station.readings).get(1);
      station.minWindSpeed = (double) StationAnalytics.getMinReadings(station.readings).get(1);
      station.windTrend = Dashboard.getWindTrend(station.readings);

      //  Pressure
      station.latestPressure = lastReading.pressure;
      station.maxPressure = (double) StationAnalytics.getMaxReadings(station.readings).get(2);
      station.minPressure = (double) StationAnalytics.getMinReadings(station.readings).get(2);
      station.pressureTrend = Dashboard.getWindTrend(station.readings);
    }
    render("station.html", station);
  }

  public static void deleteReading(Long id, Long readingid) {
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    Logger.info("Removing entry" + readingid);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    redirect("/station/" + id);
  }

  public static void addReading(Long id, int code, double temperature, double windSpeed, int windDirection, int pressure) {
    Station station = Station.findById(id);
    Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
    station.readings.add(reading);
    Logger.info("Adding a Reading: " + reading.code);
    station.save();
    redirect("/station/" + id);
  }

}
