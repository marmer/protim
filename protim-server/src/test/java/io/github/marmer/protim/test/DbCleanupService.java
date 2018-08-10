package io.github.marmer.protim.test;

import io.github.marmer.protim.persistence.relational.booking.BookingDBO;
import io.github.marmer.protim.persistence.relational.booking.BookingDayDBO;
import io.github.marmer.protim.persistence.relational.usermanagement.RoleDBO;
import io.github.marmer.protim.persistence.relational.usermanagement.UserDBO;
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
        removeAllFrom(RoleDBO.class);
        removeAllFrom(UserDBO.class);
    }

    private <T> void removeAllFrom(final Class<T> entityType) {
        entityManager.createQuery("DELETE FROM " + entityType.getName()).executeUpdate();
    }
}
