package io.github.marmer.protim.test;

import io.github.marmer.protim.persistence.relational.dbo.BookingDBO;
import io.github.marmer.protim.persistence.relational.dbo.BookingDayDBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class DbCleanupService {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void clearAll() {
        removeAllFrom(BookingDBO.class);
        removeAllFrom(BookingDayDBO.class);
    }

    private <T> void removeAllFrom(final Class<T> entityType) {
        entityManager.createQuery("DELETE FROM " + entityType.getName()).executeUpdate();
    }
}
