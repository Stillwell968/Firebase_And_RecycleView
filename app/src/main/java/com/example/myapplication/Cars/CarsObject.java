package com.example.myapplication.Cars;

public class CarsObject {
    private String regNumber;
    private String carmake;
    private String carModel;

    public CarsObject(String regNumber, String carmake, String carModel){
        this.regNumber = regNumber;
        this.carmake = carmake;
        this.carModel = carModel;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getCarmake() {
        return carmake;
    }

    public void setCarmake(String carmake) {
        this.carmake = carmake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
