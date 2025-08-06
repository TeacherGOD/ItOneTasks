package com.example.itonearchitecture.dto;


import java.time.LocalDateTime;

public record BookTableRequest(LocalDateTime dateTime, int guests) {}