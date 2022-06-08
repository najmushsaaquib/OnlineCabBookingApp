package com.masai.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.modelEntity.AdminSession;
import com.masai.modelEntity.CompletedTrips;
import com.masai.modelEntity.Customer;
import com.masai.modelEntity.Driver;
import com.masai.repository.CompletedTripsDao;
import com.masai.repository.CustomerDAO;
import com.masai.repository.DriverDAO;
import com.masai.repository.TripbookingDao;
import com.masai.DTO.CustomerDTO;
import com.masai.exceptions.CustomerException;
import com.masai.modelEntity.Customer;
import com.masai.modelEntity.ModelUser;
import com.masai.modelEntity.TripBooking;
import com.masai.modelEntity.TripStatus;
import com.masai.modelEntity.UserSession;
import com.masai.repository.CustomerDAO;
import com.masai.repository.UserSessionDAO;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	UserSessionDAO userSessionDao;

	@Autowired
	DriverDAO driverDao;

	@Autowired
	TripbookingDao tripBookingDao;
	
	@Autowired
	CompletedTripsDao completedTripsDao;

	@Override
	public Customer register(Customer user) {

		Optional<Customer> existing = customerDAO.findByUserMobile(user.getUser().getMobile());

		if (existing.isPresent()) {

			System.out.println("Customer is already present");
			throw new CustomerException("A Customer already exist with this mobile number in the Database");
		}

		return customerDAO.save(user);

	}

	@Override
	public List<Customer> getCustomer() {

		List<Customer> list = customerDAO.findAll();

		return list;
	}

	@Override
	public Customer updatePassword(CustomerDTO dto, String mobile, String key) {
		Optional<UserSession> otp = userSessionDao.findByUuid(key);
		Customer updated = null;
		if (otp.isEmpty())
			throw new CustomerException("User is not logged in, Please login first!");
		Optional<Customer> opt = customerDAO.findByUserMobile(mobile);
		if (opt.isEmpty())
			throw new CustomerException("Username not found");
		else {
			Customer toUpdate = opt.get();
			Integer id = toUpdate.getCustomerId();
			ModelUser user = toUpdate.getUser();
			user.setPassword(dto.getPassword());
			Customer newOne = new Customer(user, id);
			updated = customerDAO.save(newOne);
		}
		return updated;

	}

	@Override
	public String deleteCustomer(CustomerDTO dto, String key) {

		Optional<UserSession> otp = userSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new CustomerException("User is not logged in, Please login first!");
		else {
			Optional<Customer> opt = customerDAO.findByUserMobile(dto.getMobile());
			if (opt.isEmpty())
				throw new CustomerException("Username not found");
			else {
				Customer toBeDelete = opt.get();
				customerDAO.delete(toBeDelete);
			}
		}
		return "Your Id with Username " + dto.getMobile() + " is Deleted.";
	}

	@Override
	public Customer updateCustomer(Customer customer, String mobile, String key) {

		Customer updated = null;

		Optional<UserSession> otp = userSessionDao.findByUuid(key);

		if (otp.isEmpty())
			throw new CustomerException("User is not logged in, Please login first!");
		else {
			Optional<Customer> opt = customerDAO.findByUserMobile(mobile);
			if (opt.isEmpty())
				throw new CustomerException("Username not found");
			else {
				Customer toUpdate = opt.get();
				Integer id = toUpdate.getCustomerId();
				Customer newOne = new Customer(customer.getUser(), id);
				updated = customerDAO.save(newOne);
			}
		}
		return updated;
	}

	@Override
	public List<Driver> getAvailableDrivers() {


		List<Driver> listOfAvailableDrivers = driverDao.findByCabAvailable("YES");

		return listOfAvailableDrivers;
	}

	@Override
	public List<Driver> generalListOfDrivers() {

		List<Driver> listOfDrivers = driverDao.findAll();
		return listOfDrivers;
	}

	@Override
	public TripBooking bookTrip(TripBooking trip, String key) {
		TripBooking res = null;
		Random r=new Random();
		Integer low=10;
		Integer high=1000;
		

		Optional<UserSession> otp = userSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new CustomerException("User is not logged in, Please login first!");
		else {
			Double scale=Math.pow(10, 2);
			Optional<Customer> cust = customerDAO.findById(trip.getCustomer().getCustomerId());
			Customer checkCustomer = cust.get();

			Optional<Driver> driv = driverDao.findById(trip.getDriver().getDriverId());
			Driver checkDriver = driv.get();

			TripBooking newTrip = new TripBooking();
			newTrip.setCustomer(checkCustomer);
//			newTrip.setDistanceInKm(Math.round((r.nextInt(high-low)+low)*scale));
			newTrip.setBill(checkDriver.getCab().getCabType().getPrice() * newTrip.getDistanceInKm());
			newTrip.setDriver(checkDriver);
			newTrip.setFromDate(trip.getFromDate());
			newTrip.setFromLocation(trip.getFromLocation());
			newTrip.setStatus(TripStatus.CONFIRMED);
			newTrip.setToDate(trip.getToDate());
			newTrip.setToLocation(trip.getToLocation());
			res = tripBookingDao.save(newTrip);

		}
		return res;

	}

	@Override
	public String logoutCustomer(String key) {
		Optional<UserSession> otp = userSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new CustomerException("User is not logged in, Please login first!");
		else {

			userSessionDao.delete(otp.get());
		}

		return "Customer has succefully logged out.";


	}
	
	

	@Override
	public String completeTrip(String key, Integer tripId) {
		CompletedTrips newTrip=new CompletedTrips();
		Optional<UserSession> otp = userSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new CustomerException("User is not logged in, Please login first!");
		else {
			Optional<TripBooking> oldTripOpt=tripBookingDao.findById(tripId);
			if(oldTripOpt.isEmpty()) throw new CustomerException("Please Start your ride First.");
			TripBooking oldTrip=oldTripOpt.get();
			newTrip.setBill(oldTrip.getBill());
			newTrip.setCustomerId(oldTrip.getCustomer().getCustomerId());
			newTrip.setDistanceInKM(oldTrip.getDistanceInKm());
			newTrip.setDriverId(oldTrip.getDriver().getDriverId());
			newTrip.setFromDate(oldTrip.getFromDate());
			newTrip.setFromLocation(oldTrip.getFromLocation());
			newTrip.setStatus(TripStatus.COMPLETED);
			newTrip.setToDate(oldTrip.getToDate());
			newTrip.setToLocation(oldTrip.getToLocation());
			newTrip.setTripbookingid(tripId);
			completedTripsDao.save(newTrip);
			tripBookingDao.delete(oldTrip);
				
		}
		return "Your Trip is Completed, Thankyou for using our Service. See you Soon!";
	}

}
