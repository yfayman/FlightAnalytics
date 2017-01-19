/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

import com.thesoftwareguild.flightmaster.models.Flight;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.qpxExpress.QPXExpress;
import com.google.api.services.qpxExpress.QPXExpressRequestInitializer;
import com.google.api.services.qpxExpress.model.PassengerCounts;
import com.google.api.services.qpxExpress.model.SegmentInfo;
import com.google.api.services.qpxExpress.model.SliceInput;
import com.google.api.services.qpxExpress.model.TripOption;
import com.google.api.services.qpxExpress.model.TripOptionsRequest;
import com.google.api.services.qpxExpress.model.TripsSearchRequest;
import com.google.api.services.qpxExpress.model.TripsSearchResponse;
import com.thesoftwareguild.flightmaster.controllers.RequestController;
import com.thesoftwareguild.flightmaster.controllers.UserController;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

/**
 * Uses QPXFlightQuery to make flight requests.API KEY and Application Name can
 * be hardcoded into the class initialization. I'm reading them in from a file
 * to hide the key as this project is on GitHub
 *
 * @author Yan
 */
public class QPXFlightQuery implements FlightQuery {
    
    private final static Logger logger = Logger.getLogger(QPXFlightQuery.class);

    private final int MAX_FLIGHTS_RETURNED = 30;
    
    @Value("${qpx.app.name}")
    private String APPLICATION_NAME;
    @Value("${qpx.app.key}")
    private String API_KEY;
    
    
    private HttpTransport httpTransport;
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private int requestId;
    private int adultPassengers;
    private int childPassengers;
    private int seniorPassengers;
    private int infantInSeatCount;

    private int maxStops;

    private String origin;
    private String destination;
    private Date departDate;
    private Date returnDate;
    
    public QPXFlightQuery() {     
    }
    
    public void postConstruct(){
    	Assert.notNull(API_KEY, "API_KEY must not be null");
    	Assert.notNull(APPLICATION_NAME, "APPLICATION_NAME must not be null");
    }

    /**
     *
     * @return @throws IOException
     */
    @Override
    public List<Flight> execute() throws IOException {

        //FlightQueryResult> resultList = new ArrayList<>();
        List<Flight> retList = new ArrayList<>();
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            QPXExpress qpXExpress = new QPXExpress.Builder(httpTransport, JSON_FACTORY, null).setApplicationName(APPLICATION_NAME)
                    .setGoogleClientRequestInitializer(new QPXExpressRequestInitializer(API_KEY)).build();

            PassengerCounts passengers = new PassengerCounts();
            passengers.setAdultCount(adultPassengers);
            passengers.setChildCount(childPassengers);
            passengers.setSeniorCount(seniorPassengers);
            passengers.setInfantInSeatCount(infantInSeatCount);

            List<SliceInput> slices = new ArrayList<>();

            // Flight to destination
            SliceInput departSlice = new SliceInput();
            departSlice.setOrigin(origin);
            departSlice.setDestination(destination);
            departSlice.setDate(df.format(departDate));
            departSlice.setMaxStops(maxStops);
            slices.add(departSlice);

            // Flight back home if it exists
            if (returnDate != null) {
                SliceInput returnSlice = new SliceInput();
                returnSlice.setOrigin(destination);
                returnSlice.setDestination(origin);
                returnSlice.setDate(df.format(returnDate));
                returnSlice.setMaxStops(maxStops);
                slices.add(returnSlice);
            }

            TripOptionsRequest request = new TripOptionsRequest();
            request.setSolutions(MAX_FLIGHTS_RETURNED); // number of flights to return
            request.setPassengers(passengers);
            request.setSlice(slices);

            TripsSearchRequest parameters = new TripsSearchRequest();
            parameters.setRequest(request);

            TripsSearchResponse list = qpXExpress.trips().search(parameters).execute(); // executes the search

            List<TripOption> tripResults = list.getTrips().getTripOption();

            for (int i = 0; i < tripResults.size(); i++) {
                Flight flight = new Flight();

                flight.setRequestId(requestId);
                flight.setOrigin(origin);
                flight.setDestination(destination);
                flight.setQueryTime(new Date(System.currentTimeMillis()));

                TripOption tripResult = tripResults.get(i);
                flight.setFightId(tripResult.getId());
                Integer totalDuration = 0;
                flight.setPrice(Double.parseDouble(tripResult.getSaleTotal().replaceAll("[a-zA-Z]", "")));
                for (int j = 0; j < tripResult.getSlice().size(); j++) {
                    List<SegmentInfo> segment = tripResult.getSlice().get(j).getSegment();
                    totalDuration += tripResult.getSlice().get(j).getDuration();
                    for (SegmentInfo segment1 : segment) {
                        flight.addFlightLeg(segment1.getBookingCode(),
                                segment1.getFlight().getNumber(),
                                segment1.getFlight().getCarrier());
                        flight.setCarrier(segment1.getFlight().getCarrier());
                    }
                }

                flight.setDuration(totalDuration);

                retList.add(flight);
            }

        } catch (GeneralSecurityException ex) {
            logger.error(ex);
        }

        // Reset all parameters
        requestId = 0;
        adultPassengers = 0;
        childPassengers = 0;
        seniorPassengers = 0;
        infantInSeatCount = 0;
        maxStops = 0;
        origin = null;
        destination = null;
        departDate = null;
        returnDate = null;

        return retList;
    }


    public int getAdultPassengers() {
        return adultPassengers;
    }

 
    public void setAdultPassengers(int adultPassengers) {
        this.adultPassengers = adultPassengers;
    }


    public int getChildPassengers() {
        return childPassengers;
    }


    public void setChildPassengers(int childPassengers) {
        this.childPassengers = childPassengers;
    }


    public int getSeniorPassengers() {
        return seniorPassengers;
    }


    public void setSeniorPassengers(int seniorPassengers) {
        this.seniorPassengers = seniorPassengers;
    }


    public int getInfantInSeatCount() {
        return infantInSeatCount;
    }


    public void setInfantInSeatCount(int infantInSeatCount) {
        this.infantInSeatCount = infantInSeatCount;
    }


    public int getMaxStops() {
        return maxStops;
    }


    public void setMaxStops(int maxStops) {
        this.maxStops = maxStops;
    }


    public String getOrigin() {
        return origin;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public String getDestination() {
        return destination;
    }

  
    public void setDestination(String destination) {
        this.destination = destination;
    }


    public Date getDepartDate() {
        return departDate;
    }


    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }


    public Date getReturnDate() {
        return returnDate;
    }


    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


    public int getRequestId() {
        return requestId;
    }


    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public static void main(String[] args) {
        QPXFlightQuery q = new QPXFlightQuery();
        q.setAdultPassengers(1);
        q.setOrigin("ORD");
        q.setDestination("NYC");
        Date dep = new Date(115, 10, 31);
        q.setDepartDate(dep);
        q.setMaxStops(1);
        try {
            List<Flight> execute = q.execute();
            System.out.println("test");
        } catch (IOException ex) {
            logger.error(ex);
        }

    }

	@Override
	public void populateData(Map<String, Object> data) {
		 this.setAdultPassengers(this.adultPassengers);
	        this.setChildPassengers((int) data.get("childPassengers"));
	        this.setDepartDate((Date) data.get("depDate"));
	        this.setDestination((String) data.get("destination"));
	        this.setInfantInSeatCount((int) data.get("infantPassengers"));
	        this.setMaxStops((int) data.get("maxStops"));
	        this.setOrigin((String) data.get("origin"));
	        this.setReturnDate((Date) data.get("retDate"));
	        this.setSeniorPassengers((int) data.get("seniorPassengers"));
	        this.setRequestId((int) data.get(requestId));
		
	}
}
