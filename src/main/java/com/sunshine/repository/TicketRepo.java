package com.sunshine.repository;

import com.sunshine.model.Post;
import com.sunshine.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {
//    @Query("select t from Ticket  t where  t.ticketTitle  like concat('%',:ticketTitle,'%') ")
//    List<Ticket> findAllByTicketTitleContaining(@Param("ticket") String ticket);

    void deleteById(int id);
}
