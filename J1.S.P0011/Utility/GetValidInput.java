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
        // regex to match any double or float 
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

    public static int getBase(String msg, String err) {
        while (true) {
            try {
                System.out.println(msg);
                String base = in.readLine();
                if ("2".equals(base) || "10".equals(base) || "16".equals(base)) {
                    return Integer.parseInt(base);
                } else {
                    System.err.println(err);
                }
            } catch (IOException ex) {
                System.out.println(err);
            }
        }
    }

    public static String standardize(String num) {
        if (num.length() == 0) {
            return "0";
        }
        if (num.length() == 1) {
            return num;
        }
        if (num.charAt(0) == '0') {
            return standardize(num.substring(1));
        } else {
            return num;
        }
    }

    public static String getValueWithBase(String msg, String err, int base) {
        while (true) {
            try {
                System.out.println(msg);
                String value = in.readLine();
                switch (base) {
                    case 2:
                        if (value.matches("[0-1]+")) {
                            return standardize(value);
                        } else {
                            System.err.println(err);
                        }
                        break;
                    case 10:
                        if (value.matches("[0-9]+")) {
                            return standardize(value);
                        } else {
                            System.err.println(err);
                        }
                        break;
                    case 16:
                        if (value.matches("[0-9a-fA-F]+")) {
                            return standardize(value);
                        } else {
                            System.err.println(err);
                        }
                        break;
                }
            } catch (IOException ex) {
                System.out.println(err);
            }
        }
    }
}
