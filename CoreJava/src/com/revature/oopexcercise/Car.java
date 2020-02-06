package com.revature.oopexcercise;


public class Car extends Automobile {

	// Declaring attributes of a Car class
	private String _color;
	private String _make;
	private String _model;
	private String _makeYear;
	private String _fuelType;

	// Creates constructor of Car that assigns the following attributes
	public Car(String _color, String _make, String _model, String _makeYear, String _fuelType, int _numOfWheels) {
		super();
		this._color = _color;
		this._make = _make;
		this._model = _model;
		this._makeYear = _makeYear;
		this._fuelType = _fuelType;
		this._numOfWheels = _numOfWheels;
	}

	// Takes toString method and converts console output into a reader friendly
	// format
	@Override
	public String toString() {
		return "Car [_color=" + _color + ", _make=" + _make + ", _model=" + _model + ", _makeYear=" + _makeYear
				+ ", _fuelType=" + _fuelType + "]";
	}

	// Getters and Setters for Class Car
	public String get_color() {
		return _color;
	}

	public void set_color(String _color) {
		this._color = _color;
	}

	public String get_model() {
		return _model;
	}

	public void set_model(String _model) {
		this._model = _model;
	}

	public String get_make() {
		return _make;
	}

	public void set_make(String _make) {
		this._make = _make;
	}

	public String get_makeYear() {
		return _makeYear;
	}

	public void set_makeYear(String _makeYear) {
		this._makeYear = _makeYear;
	}

	public String get_fuelType() {
		return _fuelType;
	}

	public void set_fuelType(String _fuelType) {
		this._fuelType = _fuelType;
	}

	// Methods implemented by Class car objects declared in Driver.java class
	public void Start() {
		System.out.println("Ignite car battery to start the engine");
	}

	public void Stop() {
		System.out.println("Turn key used to ignite car the other way to stop");
	}

	public void Accelerate() {
		System.out.println("Press the acclerator peddle by the foot to make car go faster");
	}

	@Override
	public void drivable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSomething() {
		// TODO Auto-generated method stub

	}

	// Compares if the make and model attributes are the same and return true if same and false if different
	public boolean equals(Object paramObj) {
		Car paramCar = (Car) paramObj;
		if (this.get_make().equals(paramCar.get_make()) && (this.get_model().equals(paramCar.get_model()))) {
			return true;
		} else {
			return false;
		}
	}

}
