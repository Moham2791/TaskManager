package com.example.distributedtransactions.Repository;

import com.example.distributedtransactions.Entity.TaskEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query(value = "SELECT * FROM tasks WHERE status = 'pending' LIMIT 1 FOR UPDATE SKIP LOCKED", nativeQuery = true)
    @Transactional(readOnly = false)
    TaskEntity findFirstPendingTask();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM TaskEntity t WHERE t.id = :id")
    Optional<TaskEntity> findByIdWithLock(@Param("id") Long id);
}
