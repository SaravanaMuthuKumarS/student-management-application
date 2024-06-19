package com.i2i.sms.utils;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;

/**
 * <p>
 * DateUtils class contains methods that involves manipulation on Dates.
 * It provides functionalities such as calculating difference between given date and current date.
 * </p>
 */
public final class DateUtils {

  /**
   * <p>
   * DateUtils empty constructor because utils class need not to be called with object.
   * </p>
   */
  private DateUtils() {}

  /**
   * <p>
   * This method to calculate the difference between given date and current date.
   * </p>
   * @param date 
   *       Date in YYYY-MM-DD format for which difference need to be calculated
   * @return Difference in years as integer type and negative if it's future date.
  **/
  public static int findDifference(LocalDate date) {
    LocalDate currentDate = LocalDate.now();
    int yearsDifference = (Period.between(date, currentDate)).getYears();
    return yearsDifference;
  }

  /**
   * <p>
   * This method to calculate the difference between given date and current date.
   * </p>
   * @param date 
   *       Date in YYYY-MM-DD format for which validation is done.
   * @return true if it's valid , false otherwise.
   */
  public static boolean isValidDate(String date) { 
    try { 
      LocalDate parsedDate = LocalDate.parse(date); 
      return true;
    }  
    catch (DateTimeParseException e) { 
      return false;
    }
  } 
}


