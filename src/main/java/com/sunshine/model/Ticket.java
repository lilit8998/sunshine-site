package com.sunshine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ticket_title")
    private String ticketTitle;
    @Column(name = "ticket_description")
    private String ticketDescription;
    @Column(name = "price")
    private Double price;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "date_from")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @Column(name = "date_to")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;
    @Column
    private String photoUrlTicket;


}
