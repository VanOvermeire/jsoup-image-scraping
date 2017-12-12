package com.github.vanovermeire.tools;

import lombok.Builder;
import lombok.Data;

import java.util.StringJoiner;

@Data
@Builder
public class Coin {

    private Integer date;
    private String denomination;
    private String mint;
    private String front;
    private String back;

    public String getCsvFormat() {
        return String.join(",", date.toString(), denomination, mint, front, back);
    }
}
