package com.example.itonearchitecture.service;

import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {
    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void bookTable_WhenTableAvailable_ShouldSaveReservation() {
        Long tableId = 1L;
        LocalDateTime dateTime = LocalDateTime.now();
        int guests = 4;

        when(bookingRepository.exists(tableId, dateTime)).thenReturn(false);
        Reservation savedReservation = new Reservation(tableId, dateTime, guests);
        when(bookingRepository.save(any(Reservation.class))).thenReturn(savedReservation);

        Reservation result = bookingService.bookTable(tableId, dateTime, guests);

        assertNotNull(result);
        assertEquals(tableId, result.getTableId());
        assertEquals(dateTime, result.getDateTime());
        assertEquals(guests, result.getGuests());
        verify(bookingRepository).save(any(Reservation.class));
    }

    @Test
    void bookTable_WhenTableBooked_ShouldThrowException() {
        Long tableId = 1L;
        LocalDateTime dateTime = LocalDateTime.now();
        int guests = 4;

        when(bookingRepository.exists(tableId, dateTime)).thenReturn(true);

        assertThrows(IllegalStateException.class, () ->
                bookingService.bookTable(tableId, dateTime, guests));
        verify(bookingRepository, never()).save(any());
    }
}