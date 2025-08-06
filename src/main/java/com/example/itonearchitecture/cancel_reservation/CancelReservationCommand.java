package com.example.itonearchitecture.cancel_reservation;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CancelReservationCommand {
    private Long reservationId;
}
