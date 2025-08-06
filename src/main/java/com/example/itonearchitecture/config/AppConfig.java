package com.example.itonearchitecture.config;

import com.example.itonearchitecture.domain.service.TableAssignmentService;
import com.example.itonearchitecture.repository.ReservationRepository;
import com.example.itonearchitecture.repository.TableRepository;
import com.example.itonearchitecture.use_case.BookTableUseCase;
import com.example.itonearchitecture.use_case.GetReservationDetailsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TableAssignmentService tableAssignmentService(
            TableRepository tableRepository,
            ReservationRepository reservationRepository
    ) {
        return new TableAssignmentService(tableRepository, reservationRepository);
    }

    @Bean
    public BookTableUseCase bookTableUseCase(
            ReservationRepository repository,
            TableAssignmentService tableAssignmentService
    ) {
        return new BookTableUseCase(repository, tableAssignmentService);
    }

    @Bean
    public GetReservationDetailsUseCase getReservationDetailsUseCase(
            ReservationRepository repository
    ) {
        return new GetReservationDetailsUseCase(repository);
    }
}