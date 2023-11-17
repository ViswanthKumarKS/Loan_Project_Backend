package com.restapi.repository;

import com.restapi.model.ViewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRepository  extends JpaRepository<ViewStatus, Long> {
//    ViewStatus findByMessage(String viewed);
//
//    List<ViewStatus> findByStatus(String status);
}
