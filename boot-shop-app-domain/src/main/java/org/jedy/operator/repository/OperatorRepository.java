package org.jedy.operator.repository;


import org.jedy.operator.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

    Operator findByLoginId(String loginId);

    @Query("select o from Operator o join fetch o.authorityList auth where o.loginId = :loginId")
    Operator findFetchAuthByLoginId(@Param("loginId") String loginId);
}
