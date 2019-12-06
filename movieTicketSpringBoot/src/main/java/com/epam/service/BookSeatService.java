package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.SeatType;
import com.epam.beans.Seats;
import com.epam.beans.UserOrders;
import com.epam.repository.SeatsRepository;
import com.epam.repository.UserOrderRepository;
import com.epam.utils.BookSeatsHelper;

@Service
public class BookSeatService {
	@Autowired
	UserOrderRepository userOrderRepository;
	@Autowired
	SeatsRepository seatsRepository;
	@Autowired
	Seats seats;
	@Autowired
	BookSeatsHelper bookSeatsHelperClass;

	public String getSelectedSeats(SeatType seatType) {
		return bookSeatsHelperClass.getSeatsSelected(seatType);
	}

	public UserOrders bookUserSeats(UserOrders userOrder) {
		List<Seats> seatList = seatsRepository.getAllBookedSeats(userOrder.getTimingsId(),
				userOrder.getDateOfPurchase());
		if (areAnySeatsBookedInTheTheater(seatList)) {
			seats = seatList.get(0);
		}
		updateSeat(userOrder, seats);
		seatsRepository.save(seats);
		return userOrderRepository.save(userOrder);
	}

	private boolean areAnySeatsBookedInTheTheater(List<Seats> seatList) {
		return seatList != null && !seatList.isEmpty();
	}

	private void updateSeat(UserOrders userOrder, Seats seat) {
		String seatString = userOrder.getSeatsBooked();
		if(seat.getBookedSeats() != null) {
			seatString = seatString.concat(",").concat(seat.getBookedSeats());
		}
		seat.setBookedSeats(seatString);
		seat.setDate(userOrder.getDateOfPurchase());
		seat.setTimings(userOrder.getTimings());
	}

	public double getTotalPrice(String seatsString) {
		return bookSeatsHelperClass.getTotalPrice(seatsString);
	}

	public boolean isSeatsSelected(String seatsString) {
		return !(seatsString == null || seatsString.isEmpty());
	}
}
