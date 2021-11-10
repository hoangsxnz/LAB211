/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hoangson
 */
public class GetValidInput {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static int getInt(String msg, String err, int min, int max) {
        // regex to match any double or float 
        String regex = "^(\\d+)[.](\\d+)$";
        while (true) {
            try {
                System.out.print(msg);
                String input = in.readLine();
                if (input.matches(regex)) {
                    System.err.println("Input must be an integer!");
                } else {
                    int intOutput = Integer.parseInt(input);
                    if (intOutput < min || intOutput > max) {
                        System.err.println("Input must be in range [" + min + "," + max + "]");
                    } else {
                        return intOutput;
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println(err);
            }
        }
    }

    public static String getString(String msg, String err) {
        while (true) {
            try {
                System.out.print(msg);
                String input = in.readLine();
                if (input.equals("")) {
                    System.err.println("Input can't be empty");
                } else {
                    return input;
                }
            } catch (IOException e) {
                System.err.println(err);
            }
        }
    }

    public static String getStringAllowNull(String msg, String err) {
        while (true) {
            try {
                System.out.print(msg);
                String input = in.readLine();
                if (input.equals("")) {
                    return null;
                } else {
                    return input;
                }
            } catch (IOException e) {
                System.err.println(err);
            }
        }
    }

    public static String getStringByRegex(String msg, String err, String regex) {
        while (true) {
            try {
                System.out.print(msg);
                String str = in.readLine();
                if (str.matches(regex)) {
                    return str;
                }
            } catch (IOException ex) {
                System.err.println("IOException");
            }
            System.err.println(err);
        }
    }

    public static double getDouble(String msg, String err, double min, double max) {
        while (true) {
            try {
                System.out.print(msg);
                double i = Double.parseDouble(in.readLine());
                if (i >= min && i <= max) {
                    return i;
                } else {
                    System.err.println("Input must be in range [" + min + "," + max + "]");
                }

            } catch (IOException | NumberFormatException e) {
                System.err.println(err);
            }
        }
    }

    public static Date getDate(String msg, String err, String format) {
        while (true) {
            try {
                System.out.print(msg);
                String str = in.readLine();
                if ("".equals(str)) {
                    System.err.println("Input can't be empty!");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    sdf.setLenient(false);
                    Date date = sdf.parse(str);
                    Date currentDate = new Date();
                    if (date.after(currentDate)) {
                        System.err.println("Date can't be in future");
                    } else {
                        return date;
                    }
                }
            } catch (IOException | ParseException e) {
                System.err.println(err);
            }
        }
    }

    public static Date StringtoDate(String str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            Date date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            System.err.println("Date format invalid");
        }
        return null;
    }

    public static String DatetoString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getPassword(String msg, String err) {
        while(true) {
            try {
                System.out.print(msg);
                String str = in.readLine();
                int countLetter = 0;
                int countDigit = 0;
                for(int i = 0; i < str.length(); ++i) {
                    if(Character.isLetter(str.charAt(i))) {
                        countLetter++;
                    }
                    if(Character.isDigit(str.charAt(i))) {
                        countDigit++;
                    }
                }
                if(countLetter > 0 && countDigit > 0 && str.length() >= 8 && str.length() <= 31) {
                    return str;
                } else {
                    System.err.println(err);
                }
            } catch (IOException ex) {
                System.err.println(err);
            }
        }
    }
}
