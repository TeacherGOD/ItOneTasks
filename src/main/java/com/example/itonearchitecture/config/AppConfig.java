package com.example.itonearchitecture.config;

import com.example.itonearchitecture.repository.ReservationRepository;
import com.example.itonearchitecture.use_case.BookTableUseCase;
import com.example.itonearchitecture.use_case.GetReservationDetailsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public BookTableUseCase bookTableUseCase(ReservationRepository repository) {
        return new BookTableUseCase(repository);
    }

    @Bean
    public GetReservationDetailsUseCase getReservationDetailsUseCase(ReservationRepository repository) {
        return new GetReservationDetailsUseCase(repository);
    }
}