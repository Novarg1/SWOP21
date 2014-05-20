//package tests;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import user.User;
//import vehicle.*;
//import vehicle.order.ModelA;
//import vehicle.order.Order;
//import vehicle.order.OrderBuilder;
//import vehicle.parts.Part;
//import vehicle.parts.PartsSet;
//
///**
// * This is a class that collects unit tests for the Car class
// * 
// * @author Wonne Joosen
// *
// */
//public class TestCar {
//
//
//	private Vehicle testCar;
//	private Order testOrder;
//	
//	@Before
//	public void initialize() {
//		testOrder = makeOrder();
//	}
//
//	
//	/**
//	 * Helper method to create an example OrderSpecification object
//	 * 
//	 * @return A valid OrderSpecification, with arbitrary car parts specified
//	 */
//	public OrderBuilder makeOrderSpec(){
//		OrderBuilder os = new ModelA();
//		for (Class<? extends Part> type : os.getSupportedTypes()) {
//			for(Part part : os.getViableOptions(type)) {
//				os.add(part);
//			}
//		}
//		return os;
//	}
//	
//	/**
//	 * Helper method to make an order object
//	 * @return A Order object with some OrderSpecification
//	 */
//	public Order makeOrder(){
//		OrderBuilder spec = makeOrderSpec();
//		Order order = new Order(spec);
//		return order;
//	}
//
//	/**
//	 * Tester for the install() method 
//	 * Also checks whether ,after installing all parts, the Car matches the Order that was passed as an argument for its constructor
//	 */
//	@Test
//	public void installTest(){
//		testCar = new Vehicle(testOrder);
//		PartsSet cpSet = testOrder.getTasks();
//		for (Part cp : cpSet){
//			testCar.install(cp);
//		}
//		assertTrue(testCar.matchesOrder());
//	}
//	
//	
//	/**
//	 * Tests whether false is returned when attempted to install the same set of CarParts twice
//	 */
//	@Test
//	public void installTwiceTest(){
//		testCar = new Vehicle(testOrder);
//		PartsSet cpSet = testOrder.getTasks();
//		for (Part cp : cpSet){
//			testCar.install(cp);
//		}
//		//Try to install for the second time
//		boolean installedSuccessfully;
//		for (Part cp : cpSet){
//			installedSuccessfully = testCar.install(cp);
//			assertFalse(installedSuccessfully);
//		}
//		assertTrue(testCar.getParts().equals(testCar.getOrder().getTasks()));
//		assertTrue(testCar.matchesOrder());
//	}
//	
//}	