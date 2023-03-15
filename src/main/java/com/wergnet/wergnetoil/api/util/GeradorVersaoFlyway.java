package com.wergnet.wergnetoil.api.util;

import java.sql.Timestamp;
import org.springframework.stereotype.Component;

@Component
public class GeradorVersaoFlyway {

    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.getTime());
    }
}
