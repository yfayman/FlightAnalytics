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
    public List<FlightQueryResult> execute() throws IOException {
        List<FlightQueryResult> resultList = new ArrayList<>();
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
                TripOption tripResult = tripResults.get(i);
                QPXQueryResult q = new QPXQueryResult();
                q.setId(tripResult.getId());
                Integer totalDuration = 0;
                
                q.setPrice(tripResult.getSaleTotal());
                for (int j = 0; j < tripResult.getSlice().size(); j++) {
                    List<SegmentInfo> segment = tripResult.getSlice().get(j).getSegment();
                    totalDuration += tripResult.getSlice().get(j).getDuration();
                    for (SegmentInfo segment1 : segment) {
                        Flight flight = new Flight();
                        flight.setBookingCode(segment1.getBookingCode());
                        flight.setFlightCarrier(segment1.getFlight().getCarrier());
                        flight.setFlightNum(segment1.getFlight().getNumber());
                        q.addFlight(flight);
                    }
                }
                q.setDuration(totalDuration);
                resultList.add(q);
            }

        } catch (GeneralSecurityException ex) {
            Logger.getLogger(QPXFlightQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
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
