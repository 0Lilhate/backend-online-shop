package com.example.transactionservice.model;

import lombok.Data;

@Data
public class CurrencyResponse {
    private Info info;

    @Data
    public static class Info{
        private String timestamp;
        private String quote;
    }
}
