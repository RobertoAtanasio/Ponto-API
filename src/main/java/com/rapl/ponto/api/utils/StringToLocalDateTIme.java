package com.rapl.ponto.api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTIme {

	public static void main(String[] args) {

        String dateTime = "2018-12-11 17:30:05";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, formatter);

         System.out.println("Parsed Date : " + formatDateTime);
    }
}
