package com.march.clientservice;

import lombok.Data;

import java.util.List;

@Data
public class Bundle {
    private Long id;
    private String title;
    private String contents;

    @Override
    public String toString() {
        return "{id: " + id + ", " + "title: " + title + ", " + "contents: " + contents + "}";
    }
}
