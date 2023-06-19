package com.rucn.transportation.utils;

public class Tools {
    public static boolean isValidString(String str,int min,int max) {
        int len = str.length();
        return len > min && len < max;
    }
}
