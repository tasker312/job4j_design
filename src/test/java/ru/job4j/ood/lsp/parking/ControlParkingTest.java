package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.cars.Car;
import ru.job4j.ood.lsp.parking.cars.Passenger;
import ru.job4j.ood.lsp.parking.cars.Truck;
import ru.job4j.ood.lsp.parking.park.Parking;
import ru.job4j.ood.lsp.parking.park.PassengerCarParking;
import ru.job4j.ood.lsp.parking.park.TruckCarParking;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class ControlParkingTest {

    @Test
    public void whenPassengerPark() {
        Parking passengersParking = new PassengerCarParking(1);
        ControlParking controlParking = new ControlParking(List.of(passengersParking));
        Car passenger = new Passenger("PASS1");
        assertThat(controlParking.park(passenger)).isTrue();
        assertThat(passengersParking.contains("PASS1")).isTrue();
    }

    @Test
    public void whenTruckPark() {
        Parking truckParking = new TruckCarParking(3);
        ControlParking controlParking = new ControlParking(List.of(truckParking));
        Car truck = new Truck(3, "TRUCK1");
        assertThat(controlParking.park(truck)).isTrue();
        assertThat(truckParking.contains("TRUCK1")).isTrue();
    }

    @Test
    public void whenParkDoesntContainCar() {
        Parking passengersParking = new PassengerCarParking(1);
        ControlParking controlParking = new ControlParking(List.of(passengersParking));
        Car passenger = new Passenger("PASS1");
        assertThat(controlParking.park(passenger)).isTrue();
        assertThat(passengersParking.contains("PASS1")).isTrue();
        assertThat(passengersParking.contains("PASS2")).isFalse();
    }

    @Test
    public void whenTruckParkToPassengersParking() {
        Parking passengersParking = new PassengerCarParking(3);
        ControlParking controlParking = new ControlParking(List.of(passengersParking));
        Car truck = new Truck(3, "TRUCK1");
        assertThat(controlParking.park(truck)).isTrue();
    }

    @Test
    public void whenNoSpaceForTruck() {
        Parking passengersParking = new PassengerCarParking(2);
        ControlParking controlParking = new ControlParking(List.of(passengersParking));
        Car truck = new Truck(3, "TRUCK1");
        assertThat(controlParking.park(truck)).isFalse();
    }

    @Test
    public void whenNoSpaceForPassenger() {
        Parking passengersParking = new PassengerCarParking(1);
        Parking truckParking = new TruckCarParking(3);
        ControlParking controlParking = new ControlParking(List.of(passengersParking, truckParking));
        Car passenger1 = new Passenger("PASS1");
        Car passenger2 = new Passenger("PASS2");
        assertThat(controlParking.park(passenger1)).isTrue();
        assertThat(controlParking.park(passenger2)).isFalse();
        assertThat(passengersParking.contains("PASS1")).isTrue();
        assertThat(passengersParking.contains("PASS2")).isFalse();
    }

    @Test
    public void whenTruckAndPassengerPark() {
        Parking passengersParking = new PassengerCarParking(2);
        Parking truckParking = new TruckCarParking(3);
        ControlParking controlParking = new ControlParking(List.of(passengersParking, truckParking));
        Car passenger = new Passenger("PASS1");
        Car truck = new Truck(3, "TRUCK1");
        assertThat(controlParking.park(truck)).isTrue();
        assertThat(controlParking.park(passenger)).isTrue();
        assertThat(passengersParking.contains("PASS1")).isTrue();
        assertThat(truckParking.contains("TRUCK1")).isTrue();
    }

    @Test
    public void whenPassengerAndTruckPark() {
        Parking passengersParking = new PassengerCarParking(2);
        Parking truckParking = new TruckCarParking(3);
        ControlParking controlParking = new ControlParking(List.of(passengersParking, truckParking));
        Car passenger = new Passenger("PASS1");
        Car truck = new Truck(3, "TRUCK1");
        assertThat(controlParking.park(passenger)).isTrue();
        assertThat(controlParking.park(truck)).isTrue();
    }

    @Test
    public void whenPassengerParkAndLeave() {
        Parking passengersParking = new PassengerCarParking(1);
        Parking truckParking = new TruckCarParking(3);
        ControlParking controlParking = new ControlParking(List.of(passengersParking, truckParking));
        Car passenger1 = new Passenger("PASS1");
        Car passenger2 = new Passenger("PASS2");
        assertThat(controlParking.park(passenger1)).isTrue();
        assertThat(controlParking.unpark("PASS1").get()).isEqualTo(passenger1);
        assertThat(controlParking.park(passenger2)).isTrue();
    }

    @Test
    public void whenTruckHaveSpaceButNotInRow() {
        Parking passengersParking = new PassengerCarParking(3);
        ControlParking controlParking = new ControlParking(List.of(passengersParking));
        Car passenger1 = new Passenger("PASS1");
        Car passenger2 = new Passenger("PASS2");
        Car truck = new Truck(2, "TRUCK1");
        assertThat(controlParking.park(passenger1)).isTrue();
        assertThat(controlParking.park(passenger2)).isTrue();
        assertThat(controlParking.unpark("PASS1").get()).isEqualTo(passenger1);
        assertThat(controlParking.park(truck)).isFalse();
    }

    @Test
    public void whenTruckParkThenNoSpaceForPassenger() {
        Parking passengersParking = new PassengerCarParking(3);
        ControlParking controlParking = new ControlParking(List.of(passengersParking));
        Car passenger1 = new Passenger("PASS1");
        Car truck = new Truck(3, "TRUCK1");
        assertThat(controlParking.park(truck)).isTrue();
        assertThat(controlParking.park(passenger1)).isFalse();
    }

    @Test
    public void whenTruckAndPassengerPark2() {
        Parking passengersParking = new PassengerCarParking(3);
        Parking truckParking = new TruckCarParking(3);
        ControlParking controlParking = new ControlParking(List.of(passengersParking, truckParking));
        Car truck = new Truck(3, "TRUCK1");
        Car passenger1 = new Passenger("PASS1");
        assertThat(controlParking.park(truck)).isTrue();
        assertThat(truckParking.contains("TRUCK1")).isTrue();
        assertThat(controlParking.park(passenger1)).isTrue();
        assertThat(passengersParking.contains("PASS1")).isTrue();
    }

}
