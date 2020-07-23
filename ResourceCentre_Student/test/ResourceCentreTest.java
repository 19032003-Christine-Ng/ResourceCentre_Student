import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class ResourceCentreTest {
	private Camcorder cc1;
	private Camcorder cc2;
	private Chromebook cb1;
	private Chromebook cb2;
	
	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;
	
	public ResourceCentreTest() {
		super();
	}
	
	@Before
	public void setUp() throws Exception {
		// prepare test data
		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");

		camcorderList= new ArrayList<Camcorder>();
		chromebookList= new ArrayList<Chromebook>();
	}

	
	@Test
	public void addCamcorderTest() {
		// Item list is not null, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		
		//Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addCamcorder(camcorderList, cc1);		
		assertEquals("Test if that Camcorder arraylist size is 1?", 1, camcorderList.size());
		
		//The item just added is as same as the first item of the list
		assertSame("Test that Camcorder is added same as 1st item of the list?", cc1, camcorderList.get(0));
		
		//Add another item. test The size of the list is 2?
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test that Camcorder arraylist size is 2?", 2, camcorderList.size());
	}
	@Test
	public void addChromebookTest() {
		//fail("Not yet implemented");
		// write your code here	 done by Pheobus
		// Item list is not null, so that can add a new item
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);
		//Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addChromebook(chromebookList, cb1);		
		assertEquals("Test if that Chromebook arraylist size is 1?", 1, chromebookList.size());
		
		//The item just added is as same as the first item of the list
		assertSame("Test that Chromebook is added same as 1st item of the list?", cb1, chromebookList.get(0));
		
		//Add another item. test The size of the list is 2?
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test that Camcorder arraylist size is 2?", 2, chromebookList.size());
	}
		
			
	
	@Test
	public void retrieveAllCamcorderTest() {
		// Test if Item list is not null but empty, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		
		//test if the list of camcorders retrieved from the SourceCentre is empty
				String allCamcorder= ResourceCentre.retrieveAllCamcorder(camcorderList);
				String testOutput = "";
				assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);
				
		//Given an empty list, after adding 2 items, test if the size of the list is 2
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test if that Camcorder arraylist size is 2?", 2, camcorderList.size());
		
		//test if the expected output string same as the list of camcorders retrieved from the SourceCentre
		allCamcorder= ResourceCentre.retrieveAllCamcorder(camcorderList);

		testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n","CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n","CC0012", "Sony DSC-RX100M7", "Yes", "", 20);
	
		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);
		
	}
	@Test
	public void retrieveAllChromebookTest() {
		//fail("Not yet implemented");
		// write your code here code updated by Yi Yang
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);
		
		String allchromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		String Output = "";
		assertEquals("Check that ViewAllChromebooklist", Output, allchromebook);
		
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test if that Chromebook arraylist size is 2?", 2, chromebookList.size());
		
		allchromebook= ResourceCentre.retrieveAllChromebook(chromebookList);

		Output = String.format("%-10s %-30s %-10s -%10s\n","CC0011", "My Google Chromebook 1st", "", "Mac OS");
		Output += String.format("%-10s %-30s %-10s %-10s\n","CB0012", "SAMSUNG Chromebook 4+", "", "Win 10");
	
		assertEquals("Check that ViewAllchromebookList", Output, allchromebook);
		
	}

	@Test
	public void doLoanCamcorderTest() {
		//fail("Not yet implemented");
		// write your code here
	
		ResourceCentre.loanCamcorder(camcorderList);
		String tag = Helper.readString("Enter asset tag > ");
		boolean isLoaned = false;
		
		for (int i = 0; i < camcorderList.size(); i++) {
			if (tag.equalsIgnoreCase(camcorderList.get(i).getAssetTag())&& camcorderList.get(i).getIsAvailable()== true) {
				String due = Helper.readString("Enter due date > ");
				camcorderList.get(i).setIsAvailable(false);
				camcorderList.get(i).setDueDate(due);
				isLoaned = true;
				System.out.println("Camcorder " + tag + " loaned out");
			}
		}
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
			System.out.println();
		}
				
	}
	
	@Test
	public void doLoanChromebookTest() {
		//fail("Not yet implemented");
		// write your code here
	
		ResourceCentre.loanChromebook(chromebookList);
		String tag = Helper.readString("Enter asset tag > ");
		boolean isLoaned = false;
		
		for (int i = 0; i < chromebookList.size(); i++) {
			if(tag.equalsIgnoreCase(chromebookList.get(i).getAssetTag()) && chromebookList.get(i).getIsAvailable() == true) {
				String due = Helper.readString("Enter due date > ");
				chromebookList.get(i).setIsAvailable(false);
				chromebookList.get(i).setDueDate(due);
				isLoaned = true;
				System.out.println("Chromebook " + tag + " loaned out");
			}
		}
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		}
	}
	
	@Test
	public void doReturnCamcorderTest() {
		//fail("Not yet implemented");
		// write your code here test 1
		assertNotNull("Test if Camcorder is added", camcorderList);
		
		ResourceCentre.addCamcorder(camcorderList, cc1);
		
		Boolean isTrue = ResourceCentre.doReturnCamcorder(camcorderList, "CC0011");
		assertFalse("Test if Camcorder is returned", isTrue);
	
		ResourceCentre.addCamcorder(camcorderList, cc2);
		cc2.setIsAvailable(false);
		
		isTrue = ResourceCentre.doReturnCamcorder(camcorderList, "CC0012");
		assertTrue("Test if Camcorder returned", isTrue); 
		
		isTrue = ResourceCentre.doReturnCamcorder(camcorderList, "CC0013");
		assertFalse("Test if Camcorder not in list is returned", isTrue); 
		
		
		
		
		
		
	}
	@Test
	public void doReturnChromebookTest() {
		//fail("Not yet implemented");
		// write your code here
		assertNotNull("Test if Chromebook is added", chromebookList);
		
		ResourceCentre.addChromebook(chromebookList, cb1);
		
		Boolean isTrue = ResourceCentre.doReturnChromebook(chromebookList, "CC0011");
		assertFalse("Test if Chromebook is returned", isTrue);
	
		ResourceCentre.addChromebook(chromebookList, cb2);
		cb2.setIsAvailable(false);
		
		isTrue = ResourceCentre.doReturnChromebook(chromebookList, "CC0012");
		assertTrue("Test if Chromebook returned", isTrue); 
		
		isTrue = ResourceCentre.doReturnChromebook(chromebookList, "CC0013");
		assertFalse("Test if Chromebook not in list is returned", isTrue); 
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cb1 = null;
		cb2 = null;
		camcorderList = null;
		chromebookList = null;

	}

}
