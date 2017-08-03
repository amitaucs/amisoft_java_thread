package com.amisoft.ch3;

public class Car extends Vehicle {

    int speed;
    char driveType;
    String[] people;

    public Car() {
        this(4);
    }

    public Car(int doors) {
        people = new String[4];
    }


    public int drive(char driveType, int desiredSpeed) {
        selectDrive(driveType);

        while (speed != desiredSpeed) {
            accelerate();
        }
        ;
        return speed;
    }

    private int accelerate() {
        return ++speed;
    }

    private void selectDrive(char driveType) {
        this.driveType = driveType;
    }

    public static void print() {
        System.out.println("Hello World");
    }
}
