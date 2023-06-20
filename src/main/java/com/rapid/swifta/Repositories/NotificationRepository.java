package com.rapid.swifta.Repositories;

import com.rapid.swifta.Entities.UserNotifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<UserNotifications, Integer> {
}
