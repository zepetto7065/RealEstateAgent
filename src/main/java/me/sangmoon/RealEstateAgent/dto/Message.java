package me.sangmoon.RealEstateAgent.dto;

import lombok.Data;

@Data
public class Message {

    private String message;
    private Object data;

    public Message() {
        this.message = null;
        this.data = null;
    }
}
