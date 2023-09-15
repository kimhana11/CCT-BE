package com.example.priny.scout.Repository;

import com.example.priny.scout.Entity.Scout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@EnableJpaRepositories
public interface ScoutRepository extends JpaRepository<Scout, Long> {
    List<Scout> findByReceiver(String receiver);
    List<Scout> findBySender(String sender);
}
