package com.example.webapphr1_2023.Daos;

import com.example.webapphr1_2023.Beans.Country;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LocationDao extends DaoBase{

    public static ArrayList<Location> listarLocation() {

        ArrayList<Location> listaLocation = new ArrayList<>();

        String sql = "SELECT * FROM hr.locations INNER JOIN countries ON locations.country_id = countries.country_id;" ;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Country country = new Country();
                country.setCountryId(rs.getString(6));
                country.setCountryName(rs.getString(8));
                country.setRegionId(rs.getInt(9));

                Location location = new Location();
                location.setLocationId(rs.getInt(1));
                location.setStreetAddress(rs.getString(2));
                location.setPostalCode(rs.getString(3));
                location.setCity(rs.getString(4));
                location.setStateProvince(rs.getString(5));
                location.setCountry(country);

                listaLocation.add(location);


                //Employee employee = fetchEmployeeData(rs);
                //listaEmpleados.add(employee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaLocation;
    }

}
