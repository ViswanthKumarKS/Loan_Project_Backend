package com.restapi.repository;

import com.restapi.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("Select l from Loan l where l.isApproved=false")
    List<Loan> findNotApproval();

    @Modifying
    @Transactional
    @Query("UPDATE Loan l SET l.isApproved=true where l.id=?1")
    void updateUser(Long id);

    @Query("SELECT l FROM Loan l WHERE l.appUser.id = ?1")
    Optional<Loan> findByUserId(Long userId);
}
