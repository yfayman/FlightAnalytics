/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.qpxExpress.QPXExpress;
import com.google.api.services.qpxExpress.QPXExpressRequestInitializer;
import com.google.api.services.qpxExpress.model.FlightInfo;
import com.google.api.services.qpxExpress.model.LegInfo;
import com.google.api.services.qpxExpress.model.PassengerCounts;
import com.google.api.services.qpxExpress.model.PricingInfo;
import com.google.api.services.qpxExpress.model.SegmentInfo;
import com.google.api.services.qpxExpress.model.SliceInfo;
import com.google.api.services.qpxExpress.model.SliceInput;
import com.google.api.services.qpxExpress.model.TripOption;
import com.google.api.services.qpxExpress.model.TripOptionsRequest;
import com.google.api.services.qpxExpress.model.TripsSearchRequest;
import com.google.api.services.qpxExpress.model.TripsSearchResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class QPXFlightQuery implements FlightQuery {

    private final int MAX_FLIGHTS_RETURNED = 10;
    private final String APPLICATION_NAME = "FlightMaster";
    private final String API_KEY = "AIzaSyCBGr_EjQy3weyXzv23hg05cff6T4B7nD4";
    private HttpTransport httpTransport;
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private int adultPassengers = 0;
    private int childPassengers = 0;
    private int seniorPassengers = 0;
    private int infantInSeatCount = 0;

    private int maxStops = 0;

    private String origin;
    private String destination;
    private Date departDate;
    private Date returnDate;

    /**
     *
     * @return
     * @throws IOException
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
                TripOption tripResult = tripResults.get(i);
                flight.setFightId(tripResult.getId());   
                Integer totalDuration = 0;
                flight.setPrice(tripResult.getSaleTotal());
                for (int j = 0; j < tripResult.getSlice().size(); j++) {
                    List<SegmentInfo> segment = tripResult.getSlice().get(j).getSegment();
                    totalDuration += tripResult.getSlice().get(j).getDuration();
                    for (SegmentInfo segment1 : segment) {
                        flight.addFlightLeg(segment1.getBookingCode(), 
                                segment1.getFlight().getNumber(), 
                                segment1.getFlight().getCarrier());   
                    }
                }
               
                flight.setDuration(totalDuration);
             
                retList.add(flight);
            }

        } catch (GeneralSecurityException ex) {
            Logger.getLogger(QPXFlightQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retList;
    }

    @Override
    public int getAdultPassengers() {
        return adultPassengers;
    }

    @Override
    public void setAdultPassengers(int adultPassengers) {
        this.adultPassengers = adultPassengers;
    }

    @Override
    public int getChildPassengers() {
        return childPassengers;
    }

    @Override
    public void setChildPassengers(int childPassengers) {
        this.childPassengers = childPassengers;
    }

    @Override
    public int getSeniorPassengers() {
        return seniorPassengers;
    }

    @Override
    public void setSeniorPassengers(int seniorPassengers) {
        this.seniorPassengers = seniorPassengers;
    }

    @Override
    public int getInfantInSeatCount() {
        return infantInSeatCount;
    }

    @Override
    public void setInfantInSeatCount(int infantInSeatCount) {
        this.infantInSeatCount = infantInSeatCount;
    }

    @Override
    public int getMaxStops() {
        return maxStops;
    }

    @Override
    public void setMaxStops(int maxStops) {
        this.maxStops = maxStops;
    }

    @Override
    public String getOrigin() {
        return origin;
    }

    @Override
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public Date getDepartDate() {
        return departDate;
    }

    @Override
    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    @Override
    public Date getReturnDate() {
        return returnDate;
    }

    @Override
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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
            q.execute();
        } catch (IOException ex) {
            Logger.getLogger(QPXFlightQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//    departSlice.setOrigin(origin);
//            departSlice.setDestination(destination);
//            departSlice.setDate(df.format(departDate));
//            departSlice.setMaxStops(maxStops);
}
