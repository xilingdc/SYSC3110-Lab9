package lab9;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class BuddyInfoTest {

	private BuddyInfo model1;
	private BuddyInfo model2;

	@Before
	public void setUp() {
		model1 = new BuddyInfo("xinyuchen", "18", "11111");
		model2 = new BuddyInfo("xinyuchen", "118", "6132765888");
	}

	@Test
	public void testName() {
		assertEquals(model2.getName(), "xinyuchen");
	}


	@Test
	public void testphone() {
		assertEquals(model2.getPhone(), "6132765888");
	}
    
	@Test
	public void testsetphone() {
		model2.setPhone("6132965888");
		assertEquals(model2.getPhone(), "6132765888");
	}
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(BuddyInfoTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
	}
}

