/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author hoangson
 */
public class GetValidInput {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static int getInt(String msg, String err, int min, int max) {
        /* regex to match any double or float, 
        first catch 1 or more digits, then catch a dot, then catch 1 or more digits again */
        String regex = "^(\\d+)[.](\\d+)$";
        while (true) {
            try {
                System.out.print(msg);
                String input = in.readLine();
                if (input.matches(regex)) {
                    System.err.println("Input must be an integer!");
                } else if ("".equals(input)) {
                    System.err.println("Input can't be empty!");
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
                    return null;
                } else if (input.contains(":")) {
                    System.err.println("Input can't contain ':'");
                } else {
                    return input;
                }
            } catch (IOException e) {
                System.err.println(err);
            }
        }
    }

    public static String getStringIgnoreNull(String msg, String err) {
        while (true) {
            try {
                System.out.print(msg);
                String input = in.readLine();
                if (input.equals("")) {
                    System.err.println("Input can't be empty!");
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
}
