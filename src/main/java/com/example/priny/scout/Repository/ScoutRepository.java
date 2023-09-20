package com.example.priny.scout.Repository;

import com.example.priny.user.domain.User;
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
    @Query("select s from Scout s where s.receiver = :receiverId")
    List<Scout> findByReceiver(@Param("receiverId") String receiver);
    //발신자 아이디로 보낸 쪽지 조회
    @Query("select s from Scout s where s.sender = :senderId")
    List<Scout> findBySender(@Param("senderId") String sender);

    //수신
//    @Query("select s from Scout s where s.receiver = :receiverId")
//    Optional<Scout>  findOneReceiver(@Param("receiverId") String receiver);
//
//    //발신
//    @Query("select s from Scout s where s.sender = :senderId")
//    Optional<Scout>  findOneSender(@Param("senderId") String sender);
//
//    @Query("select s from Scout s where s.sender = :senderId")
//    Optional<Scout> joinSenderReceiver (@Param(""))
    //삭제
//    @Query("delete from Scout s where s.id = receiverId")
//    void deleteByReceiver(@Param("receiverId") String receiver);

}
