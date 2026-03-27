package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Booking;
import com.example.demo.model.HistoryBooking;
import com.example.demo.model.User;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.HistoryBookingRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookingService {
     public final BookingRepository br;
      private final HistoryBookingRepository hr;
    private BookingService(BookingRepository br,HistoryBookingRepository hr)
    {
          this.br=br;
          this.hr=hr;
    }
    public Booking createBooking(Booking b)
    {
       Booking booking= br.save(b);

        HistoryBooking hb=new HistoryBooking();
        hb.setBooking(booking);
        hb.setChangedBy(b.getUser());
        hb.setStatusChange("Created");
        hb.setChangeDate(LocalDateTime.now());
        hb.setNotes("Booking Created");
        hr.save(hb);
        return booking;
    }
    public Optional<Booking> getBookingById(Long id)
    {
        return br.findById(id);
    }
   public List<Booking>getByUser(User userId)
   {
             return br.findByUser(userId);
   }
   public Booking UpdateBooking(Booking b,Long bookingId,User u)
   {
    Booking booking=br.findById(bookingId).orElseThrow(()->new EntityNotFoundException("Booking Not found"+bookingId));

    String Oldstatus=booking.getStatus();
    booking.setStatus(b.getStatus());
    booking.setEndTime(b.getEndTime());
    booking.setStartTime(b.getStartTime());

      Booking updated=br.save(booking);
    HistoryBooking historybooking=new HistoryBooking();

    historybooking.setBooking(updated);
    historybooking.setChangedBy(u);
        historybooking.setStatusChange("UPDATED");
        historybooking.setNotes("Booking updated. Previous status: " + Oldstatus + ", New status: " + updated.getStatus());
        historybooking.setChangeDate(LocalDateTime.now());

     hr.save(historybooking);
     return updated;
   }
   public void DeleteById(Long bookingId)
   {
    hr.deleteByBookingId(bookingId);
     br.deleteById(bookingId);
   }
   public void save(Booking booking) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
   }
   
}
