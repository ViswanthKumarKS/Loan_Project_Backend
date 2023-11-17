package com.restapi.repository;

import com.restapi.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType,Integer> {
    DocumentType findById(Long typeId);
}
