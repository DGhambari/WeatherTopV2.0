package controllers;

import java.util.List;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import utils.Analytics;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;

    for (int i = 0; i < stations.size(); i++) {
      if (stations.get(i).readings.size() > 0) {
        Reading lastReading = stations.get(i).readings.get(stations.get(i).readings.size() - 1);

        //  Weather
        stations.get(i).latestWeatherCondition = Analytics.weatherCode(lastReading.code);
        stations.get(i).weatherIcon = Analytics.weatherIcon(lastReading.code);

        //  Temp
        stations.get(i).latestTempC = lastReading.temperature;
        stations.get(i).latestTempF = Analytics.celsiusToFahrenheit(lastReading.temperature);
        stations.get(i).maxTemp = (double) Analytics.getMaxReadings(stations.get(i).readings).get(0);
        stations.get(i).minTemp = (double) Analytics.getMinReadings(stations.get(i).readings).get(0);
        stations.get(i).tempTrend = Dashboard.getTempTrend(stations.get(i).readings);

        //  Wind
        stations.get(i).beaufort = Analytics.windSpeedToBeaufort(lastReading.windSpeed);
        stations.get(i).latestWindSpeed = lastReading.windSpeed;
        stations.get(i).latestWindDirection = Analytics.degreesToWindDirection(lastReading.windDirection);
        stations.get(i).windChill = Analytics.windChill(stations.get(i).latestTempC, stations.get(i).latestWindSpeed);
        stations.get(i).beaufortLabel = Analytics.beaufortToBeaufortLabel(stations.get(i).readings.size() - 1);
        stations.get(i).maxWindSpeed = (double) Analytics.getMaxReadings(stations.get(i).readings).get(1);
        stations.get(i).minWindSpeed = (double) Analytics.getMinReadings(stations.get(i).readings).get(1);
        stations.get(i).windTrend = Dashboard.getWindTrend(stations.get(i).readings);

        //  Pressure
        stations.get(i).latestPressure = lastReading.pressure;
        stations.get(i).maxPressure = (double) Analytics.getMaxReadings(stations.get(i).readings).get(2);
        stations.get(i).minPressure = (double) Analytics.getMinReadings(stations.get(i).readings).get(2);
        stations.get(i).pressureTrend = Dashboard.getPressureTrend(stations.get(i).readings);
      }
    }
    render("dashboard.html", stations);
  }

  public static void deleteStation(Long id) {
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    Logger.info("Deleting Station " + station.name);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }

  public static void addStation(String name) {
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name);
    member.stations.add(station);
    Logger.info("Adding a Station: " + station.name);
    member.save();
    redirect("/dashboard");
  }

  public static void addStation(String name, double latitude, double longitude) {
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    Logger.info("Adding a Station: " + station.name);
    member.save();
    redirect("/dashboard");
  }

  public static String getTempTrend(List<Reading> readings) {
    String trendDirection = null;

    if (readings.size() > 3) {
      if (readings.get(readings.size() - 1).temperature > readings.get(readings.size() - 2).temperature) {
        if (readings.get(readings.size() - 2).temperature > readings.get(readings.size() - 3).temperature) {
          trendDirection = "arrow up";
        }
      }
      if (readings.get(readings.size() - 1).temperature < readings.get(readings.size() - 2).temperature) {
        if (readings.get(readings.size() - 2).temperature < readings.get(readings.size() - 3).temperature) {
          trendDirection = "arrow down";
        }
      }
    }
    return trendDirection;
  }

  public static String getWindTrend(List<Reading> readings) {
    String trendDirection = null;

    if (readings.size() > 3) {
      if (readings.get(readings.size() - 1).windSpeed > readings.get(readings.size() - 2).windSpeed) {
        if (readings.get(readings.size() - 2).windSpeed > readings.get(readings.size() - 3).windSpeed) {
          trendDirection = "arrow up";
        }
      }
      if (readings.get(readings.size() - 1).windSpeed < readings.get(readings.size() - 2).windSpeed) {
        if (readings.get(readings.size() - 2).windSpeed < readings.get(readings.size() - 3).windSpeed) {
          trendDirection = "arrow down";
        }
      }
    }
    return trendDirection;
  }

  public static String getPressureTrend(List<Reading> readings) {
    String trendDirection = null;

    if (readings.size() > 3) {
      if (readings.get(readings.size() - 1).pressure > readings.get(readings.size() - 2).pressure) {
        if (readings.get(readings.size() - 2).pressure > readings.get(readings.size() - 3).pressure) {
          trendDirection = "arrow up";
        }
      }
      if (readings.get(readings.size() - 1).pressure < readings.get(readings.size() - 2).pressure) {
        if (readings.get(readings.size() - 2).pressure < readings.get(readings.size() - 3).pressure) {
          trendDirection = "arrow down";
        }
      }
    }
    return trendDirection;
  }
}

