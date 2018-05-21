package io.github.marmer.protim.test;

import io.github.marmer.protim.repositories.BookingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class DbCleanupService {
    @Autowired
    private BookingDayRepository bookingDayRepository;
    private EntityManager entityManager;

    @Transactional
    public void clearAll() {
        bookingDayRepository.deleteAll();
    }
}
