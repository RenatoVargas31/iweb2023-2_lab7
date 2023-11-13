package com.example.webapphr1_2023.Daos;

import com.example.webapphr1_2023.Beans.Country;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Location;

import java.sql.*;
import java.util.ArrayList;

public class LocationDao extends DaoBase {

    public static ArrayList<Location> listarLocation() {

        ArrayList<Location> listaLocation = new ArrayList<>();

        String sql = "SELECT * FROM hr.locations INNER JOIN countries ON locations.country_id = countries.country_id;";

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

    public Location obtenerLocation(int locationId) {
        Location loc = new Location();

        String sql = "SELECT * FROM locations loc LEFT JOIN countries c ON loc.country_id = c.country_id WHERE location_id = ?;";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, String.valueOf(locationId));

            try (ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    loc.setLocationId(rs.getInt(1));
                    loc.setStreetAddress(rs.getString(2));
                    loc.setPostalCode(rs.getString(3));
                    loc.setCity(rs.getString(4));
                    loc.setStateProvince(rs.getString(5));

                    Country country = new Country();
                    country.setCountryId(rs.getString(6));
                    country.setCountryName(rs.getString("country_name"));
                    country.setRegionId(Integer.parseInt(rs.getString("region_id")));

                    loc.setCountry(country);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return loc;
    }
}





