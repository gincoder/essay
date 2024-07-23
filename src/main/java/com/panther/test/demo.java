package com.panther.test;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO
 *
 * @author panther
 * @version 1.0: demo.java, 2024/5/10 10:08 $
 */
public class demo {

    public static void main(String[] args) throws IOException {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;

        System.out.println(String.format("%s月1日需冻结%s月扶持奖励，请确保钱包余额充足。",month) );
    }

}
