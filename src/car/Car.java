package car;

public class Car {
	
	private CarModel carModel;
	private CarBody body;
	private CarColor color;
	private CarEngine engine;
	private CarGearbox gearbox;
	private CarSeats seats;
	private CarAirco airco;
	private CarWheels wheels;
	
	public Car(String model) {
		this.carModel = new CarModel(model);
	}
	
//	public Car(String model, CarBody body, CarColor color, CarEngine engine, CarGearbox gearbox, CarSeats seats, CarAirco airco, CarWheels wheels) {
//		this.carModel = new CarModel(model);
//		this.body = body;
//		this.color = color;
//		this.engine = engine;
//		this.gearbox = gearbox;
//		this.seats = seats;
//		this.airco = airco;
//		this.wheels = wheels;
//	}
	
	@Override
	public String toString() {
		return "Model: " + this.carModel +
				"\nBody: " + this.body +
				"\nColor: " + this.color + 
				"\nEngine: " + this.engine +
				"\nGearbox: " + this.gearbox +
				"\nSeats: " + this.seats +
				"\nAirco: " + this.airco +
				"\nWheels: " + this.wheels;
	}
	
	//Setters
	
	public void setBody(CarBody body) {
		this.body = body;
	}
	
	public void setColor(CarColor color) {
		this.color = color;
	}
	
	public void setEngine(CarEngine engine) {
		this.engine = engine;
	}
	
	public void setGearbox(CarGearbox gearbox) {
		this.gearbox = gearbox;
	}
	
	public void setSeats(CarSeats seats) {
		this.seats = seats;
	}
	
	public void setAirco(CarAirco airco) {
		this.airco = airco;
	}
	
	public void setWheels(CarWheels wheels) {
		this.wheels = wheels;
	}
	
	public void setModel(CarModel model) {
		this.carModel = model;
	}
	
	//Getters
	
	public CarBody getBody() {
		return this.body;
	}
	
	public CarColor getColor() {
		return this.color;
	}
	
	public CarEngine getEngine() {
		return this.engine;
	}
	
	public CarGearbox getGearbox() {
		return this.gearbox;
	}
	
	public CarSeats getSeats() {
		return this.seats;
	}
	
	public CarAirco getAirco() {
		return this.airco;
	}
	
	public CarWheels getWheels() {
		return this.wheels;
	}
	
	public CarModel getModel() {
		return this.carModel;
	}
	
	
}
