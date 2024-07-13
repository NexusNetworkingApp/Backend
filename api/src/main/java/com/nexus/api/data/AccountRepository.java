package main.java.com.nexus.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.accountType = :accountType AND a.individual.email = :email")
    Account findByAccountTypeAndEmailForIndividual(@Param("accountType") AccountType accountType, @Param("email") String email);

    @Query("SELECT a FROM Account a WHERE a.accountType = :accountType AND a.organization.email = :email")
    Account findByAccountTypeAndEmailForOrganization(@Param("accountType") AccountType accountType, @Param("email") String email);

    @Query("SELECT a.individual FROM Account a WHERE a.accountId = :accountId")
    Individual findIndividualByAccountId(@Param("accountId") Long accountId);

    @Query("SELECT a.organization FROM Account a WHERE a.accountId = :accountId")
    Organization findOrganizationByAccountId(@Param("accountId") Long accountId);

    List<Account> findAllByAccountIdNot(Long excludedAccountId);

    List<Account> findAllByAccountType(String accountType);

    List<Account> findByAccountType(AccountType accountType);

    List<Account> findTop5ByAccountTypeOrderByReceivedLikesDesc(AccountType accountType);

}
