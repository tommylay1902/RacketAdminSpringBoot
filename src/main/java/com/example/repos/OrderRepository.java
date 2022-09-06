package com.example.repos;

import com.example.entities.Order;
import com.example.entities.views.EmployeeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(int id);

    @Query(value = "SELECT * " +
            "FROM orders o " +
            "WHERE (o.user = :user OR :user is null) AND (:completed is null or o.completed = :completed )", nativeQuery = true)
    List<Order> getAllOrdersByOptionalUserAndOptionalCompleted(@Param("user") Optional<Integer> user, @Param("completed") Optional<Boolean> completed);

    @Query(value = "SELECT user as employeeId, " +
            "(coalesce(sum(price), 0 )) as profits, " +
            "count(id) as orders, " +
            "?1 as start, " +
            "?2 as end " +
            "FROM orders " +
            "WHERE completed=true  " +
            "AND (return_day >= ?1 AND return_day <= ?2)  " +
            "GROUP BY user", nativeQuery = true)
    Optional<List<EmployeeReport>> generateProfitReportByEmployee(Date start, Date end);

    @Query(value = "SELECT * " +
            "FROM orders " +
            "WHERE received_day = ?1", nativeQuery = true)
    Optional<List<Order>> receivedOrdersByDay(String start);

    @Query(value = "SELECT * " +
            "FROM orders " +
            "WHERE received_day >= ?1 AND received_day <= ?2", nativeQuery = true)
    Optional<List<Order>> ordersByWeek(String start, String end);
}
