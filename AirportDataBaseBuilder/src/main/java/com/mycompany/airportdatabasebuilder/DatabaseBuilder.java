/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.airportdatabasebuilder;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author yan
 */
public class DatabaseBuilder {

    public static void main(String[] args) {
        try {
            Dao  dao= new Dao();
            JdbcTemplate jdbcTemplate = dao.getJdbcTemplate();
            CSVReader reader = new CSVReader(new FileReader("airportdata2.csv"));
            String[] nextLine;
            int i = 0;
            while ((nextLine = reader.readNext()) != null) {
                if(nextLine[4]==null || nextLine[4].equals(""))
                    System.out.println("empty code found");
                else
                {
                    jdbcTemplate.update("INSERT INTO airportdata (airport_name, city, country, iata) VALUES(?, ?, ?, ?)", nextLine[1],
                            nextLine[2], nextLine[3], nextLine[4]);
                }
                i++;
            }
            
            // Airport(1)/ City(2)/ Country(3)/ Code(4)
            System.out.println(i + " rows were read");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
