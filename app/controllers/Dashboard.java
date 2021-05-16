package controllers;

import java.text.DecimalFormat;
import java.util.List;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;

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

  public static int windSpeedToBeaufort(int windSpeed) {

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

    return beaufort;
  }

//  public static byte getTrend(List<Reading> readings, String weatherCondition){
//    // 0 = Decreasing, 1 = Steady, 2 = Increasing
//    byte trendDirection = 1;
//
//
//    for (Reading reading : readings)
//      if reading.
//
//  }

//  public static byte getTempTrend(List<Reading> readings){
//    // 0 = Decreasing, 1 = Steady, 2 = Increasing
//    byte trendDirection = 1;
//
//    if (readings.size() > 2) {
//      if (readings.get(0).temperature > readings.get(1).temperature) {
//        if (readings.get(1).temperature > readings.get(2).temperature) {
//          trendDirection = 2;
//        }
//      }
//      if (readings.get(0).temperature < readings.get(1).temperature) {
//        if (readings.get(1).temperature < readings.get(2).temperature) {
//          trendDirection = 0;
//        }
//      }
//    }
//    return trendDirection;
//  }

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

