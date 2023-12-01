package com.restapi.repository;

import com.restapi.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {



    @Modifying
    @Transactional
    @Query("UPDATE DocumentType d SET d.isBoolean=true where d.id=?1")
    void updateUser(Long id);

    @Query("Select d from DocumentType d where d.isBoolean=false")
    List<DocumentType> findNotApproval();

//    @Query("SELECT d FROM DocumentType d inner join d.appUser a WHERE a.id = ?1")
//    Optional<DocumentType> findByUserId(Long userId);
//
    @Query("SELECT d FROM DocumentType d WHERE d.appUser.id = ?1")
    Optional<DocumentType> findByUserId(Long userId);
}
