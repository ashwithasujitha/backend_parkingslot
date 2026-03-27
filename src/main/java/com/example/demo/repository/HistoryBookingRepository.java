package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Booking;
import com.example.demo.model.HistoryBooking;

import jakarta.transaction.Transactional;

@Repository
public interface HistoryBookingRepository extends JpaRepository<HistoryBooking,Long>{

    List<HistoryBooking> findByBooking(Booking booking);
@Transactional
    @Modifying
    @Query("DELETE FROM HistoryBooking h WHERE h.booking.bookingId = :bookingId")
    void deleteByBookingId(@Param("bookingId") Long bookingId);
   

    
}
