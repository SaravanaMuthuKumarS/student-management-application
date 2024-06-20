package com.i2i.sms.utils;

/**
 * <p>
 * ValidateDataUtils class contains methods that involves manipulation on Data's 
 * That are based as valid or not. It provides functionalities such as 
 * Validating string and validating the range of given integer.
 * </p>
 */
public final class ValidateDataUtils {

  /**
   * <p>
   * ValidateDataUtils empty constructor because utils class need not to be called with object.
   * </p>
   */
  private ValidateDataUtils() {}
 
  /**
   * <p>
   * This method is to validates the string whether any special character or 
   * Any other unwanted characters present in the string.
   * </p>
   * @param str 
   *       string that need to be validated
   * @return boolean value whether true or false 
  **/
  public static boolean stringValidator(String str) {
    return str.matches("^[a-zA-Z\\s]+$");
  }

  /**
   * <p>
   * This method is to check whether the given number is between the range of integers provided.
   * </p>
   * @param checkInteger 
   *       Integer value that need to be verified.
   * @param startInteger 
   *       Starting integer value for searching range.
   * @param endInteger 
   *       End integer value for searching range.
   * @return boolean value whether present in given range or not.
  **/
  public static boolean rangeValidator(int checkInteger, int startInteger, int endInterger) {
    return startInteger <= checkInteger && checkInteger <= endInterger;
  }

}


