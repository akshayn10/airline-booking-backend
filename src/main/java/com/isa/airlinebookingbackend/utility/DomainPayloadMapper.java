package com.isa.airlinebookingbackend.utility;

import com.isa.airlinebookingbackend.entity.Fleet;
import com.isa.airlinebookingbackend.entity.Flight;
import com.isa.airlinebookingbackend.entity.Location;
import com.isa.airlinebookingbackend.payload.FleetPayload;
import com.isa.airlinebookingbackend.payload.FlightPayload;
import com.isa.airlinebookingbackend.payload.LocationPayload;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DomainPayloadMapper {
    Location locationPayloadToLocation(LocationPayload locationPayload);

    List<LocationPayload> locationsToLocationPayloads(List<Location> locations);

    @Mapping(target = "departureLocation.id", source = "departureLocation")
    @Mapping(target = "arrivalLocation.id", source = "arrivalLocation")
    @Mapping(target = "fleet.id", source = "fleetId")
    @Mapping(target = "economySeatFare", source = "economyFare")
    @Mapping(target = "premiumSeatFare", source = "premiumFare")
    @Mapping(target = "businessSeatFare", source = "businessFare")
    Flight flightPayloadToFlight(FlightPayload flightPayload);

    @InheritInverseConfiguration
    FlightPayload flightToFlightPayload(Flight flight);

    List<FlightPayload> flightsToFlightPayloads(List<Flight> flights);

    List<FleetPayload> fleetsToFleetPayloads(List<Fleet>fleets);
}
