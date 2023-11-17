package com.restapi.repository;

import com.restapi.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository  extends JpaRepository<Document, Long> {
}
