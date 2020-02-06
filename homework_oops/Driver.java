package com.revature.homework_oops;

public class Driver {
	public static void main(String[] args) {
		Car car = new Car();
		car.makeAndModel = "Toyota Prius";
		car.year = 2015;
		car.maxSpeed = 120;
		car.hoursePower = 234;
		car.mileage= 4321;
						
		car.drive();
		car.stop();
		car.doUber();
		
		System.out.println();		
		System.out.println("====TRAIN====");
						
		Train train = new Train();
		train.makeAndModel = "CyberTrain 10";
		train.year = 2019;
		train.maxSpeed = 200;
		train.ticketPrice = 120.50;
		train.mileage = 45678;
						
		train.drive();
		train.leaveStation();
		train.stop();
			
		System.out.println();				
		System.out.println("=====static service calls======");
						
		Vehicle.service();
		Car.service();
		Train.service();
		
		}
	}
