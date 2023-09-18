package com.example.priny.scout.Repository;

import com.example.priny.resume.Entity.UserTest;
import com.example.priny.scout.Entity.Scout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@EnableJpaRepositories
public interface ScoutRepository extends JpaRepository<Scout, Long> {

    //수신자 아이디로 받은 쪽지 조회
    List<Scout> findByReceiver(String receiver);
    //발신자 아이디로 보낸 쪽지 조회
    List<Scout> findBySender(String sender);

    //수신
    @Query("select s from Scout s where s.receiver = :receiverId")
    Optional<Scout>  findOneReceiver(@Param("receiverId") String receiver);

    //발신
    @Query("select s from Scout s where s.sender = :senderId")
    Optional<Scout>  findOneSender(@Param("senderId") String sender);

}
