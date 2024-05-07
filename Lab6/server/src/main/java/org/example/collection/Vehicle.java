package org.example.collection;

import java.time.LocalDate;
import java.util.Objects;

public class Vehicle implements Comparable<Vehicle>{

    private Integer id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private Integer enginePower;
    private VehicleType type;
    private FuelType fuelType;


    /**
     * @param id
     * @param name
     * @param coordinates
     * @param creationDate
     * @param enginePower
     * @param type
     * @param fuelType
     */
    public Vehicle(Integer id,String name, Coordinates coordinates, LocalDate creationDate,
                   Integer enginePower, VehicleType type, FuelType fuelType){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;
    }

    public Vehicle(){}
    public Vehicle(Integer id, String name){
        this.id = id;
        this.name = name;
        this.coordinates = new Coordinates((float) 0, (double) 0);
        this.creationDate = LocalDate.now();
        this.enginePower = 100;
        this.type = VehicleType.CAR;
        this.fuelType = FuelType.GASOLINE;
    }

    public Integer getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Float getX() {
        return coordinates.getX();
    }

    public Double getY(){
        return coordinates.getY();
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    public void setCreationDate(LocalDate creationDate){
        this.creationDate = creationDate;
    }

    public void setType(VehicleType type){
        this.type = type;
    }

    public void setEnginePower(Integer enginePower){
        this.enginePower = enginePower;
    }

    public void setFuelType(FuelType fuelType){
        this.fuelType = fuelType;
    }

    public boolean isValid(){
        if (id!=null) {
            return id > 0 & name != null & coordinates.getY() > -122 & enginePower > 0 & type != null & fuelType != null;
        }
        return false;
    }

    @Override
    public String toString() {
        return "id: " + id +
                "name: " + name +
                "coordinates: " +
                "  x: " + coordinates.getX() +
                "  y: " + coordinates.getY() +
                "creationDate: " + creationDate +
                "type: " + type +
                "fuelType: " + fuelType + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (!Objects.equals(id, vehicle.id)) return false;
        if (!Objects.equals(name, vehicle.name)) return false;
        if (!Objects.equals(coordinates, vehicle.coordinates)) return false;
        if (!Objects.equals(creationDate, vehicle.creationDate))
            return false;
        if (!Objects.equals(enginePower, vehicle.enginePower)) return false;
        if (type != vehicle.type) return false;
        return fuelType == vehicle.fuelType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,coordinates,creationDate,enginePower,type,fuelType);
    }

    @Override
    public int compareTo(Vehicle o) {
        return getName().compareTo(o.getName());
    }
}
