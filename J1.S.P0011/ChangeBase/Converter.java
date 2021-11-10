/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChangeBase;

import java.math.BigInteger;

/**
 *
 * @author hoangson
 */
public class Converter {

    public String convert(String inputValue, int baseIn, int baseOut) {
        String result = "";
        String decimalTemp = "";
        // check if user needs to convert input number to input's base
        if (baseIn == baseOut) {
            return inputValue;
        }
        // convert input value to decimal (if needed)
        switch(baseIn) {
            case 2:
                decimalTemp = convertToDecimal(inputValue, 2);
                break;
            case 10:
                decimalTemp = inputValue;
                break;
            case 16:
                decimalTemp = convertToDecimal(inputValue, 16);
                break;
        }
        // convert temporary decimal value to other base
        switch(baseOut) {
            case 2:
                result = convertFromDecimal(decimalTemp, 2);
                break;
            case 10:
                result = decimalTemp;
                break;
            case 16:
                result = convertFromDecimal(decimalTemp, 16);
                break;
        }
        return result;
    }

    public String convertFromDecimal(String input, int baseOut) {
        String result = "";
        BigInteger base = new BigInteger("0");
        String HEX = "0123456789ABCDEF";
        BigInteger decimal = new BigInteger(input);
        // check if user want to convert from decimal to binary or hexadecimal
        if (baseOut == 2) {
            base = new BigInteger("2");
        } else {
            base = new BigInteger("16");
        }
        // loop until the decimal is smaller than base, then decimal is divided by base equals to 0
        while (decimal.compareTo(new BigInteger("0")) != 0) {
            /*
            step 1: take remainder as (decimal % base)
            step 2: update decimal = decimal / base
            step 3: add the remainder to the head of result (as write all remainders in reversed order)
            */
            BigInteger remainder = decimal.mod(base);
            decimal = decimal.divide(base);
            result = HEX.charAt(remainder.intValue()) + result;
        }
        return result;
    }

    public String convertToDecimal(String input, int baseIn) {
        BigInteger result = new BigInteger("0");
        BigInteger base = new BigInteger("0");
        String HEX = "0123456789ABCDEF";
        input = input.toUpperCase();
        // check if user want to convert from decimal to binary or hexadecimal
        if (baseIn == 2) {
            base = new BigInteger("2");
        } else {
            base = new BigInteger("16");
        }
        // loop through every characters in input number from the last one to the first one
        for (int i = input.length() - 1; i >= 0; i--) {
            /*
            step 1: take character one by one from the end of input, then convert to String
            step 2: make a BigInteger from the String converted
            step 3: multiply that BigInteger with the base^exponent (exponent from 0 to length-1)
            step 4: add all to the result
            */
            String temp = HEX.indexOf(input.charAt(i)) + "";
            BigInteger position = new BigInteger(temp);
            BigInteger addValue = position.multiply(base.pow(input.length() - 1 - i));
            result = result.add(addValue);
        }
        return result.toString();
    }
}
