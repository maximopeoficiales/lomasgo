package com.maximoprog.lomasgo.models.responses;

import com.maximoprog.lomasgo.models.New;

import java.util.ArrayList;

import lombok.Data;

@Data
public class NewsResponse {
    ArrayList<New> news;
}
