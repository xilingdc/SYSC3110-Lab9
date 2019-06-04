package lab9.source.lab9.lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import junit.framework.TestCase;

public class AddressBookTest extends TestCase{
	public AddressBook addressBook;
	public BuddyInfo xinyuchen, xy;
	@Before
	public void setUp() {
		xinyuchen = new BuddyInfo("xinyuchen","19","111111");
		xy = new BuddyInfo("xy","119","3167865896");
		addressBook = new AddressBook();
		addressBook.addBuddy(xinyuchen);		
		addressBook.exportToTextFile("ojb.txt");
	}

	@Test
	public void testSize() {
		assertEquals(1,addressBook.addressbook.size());
	}
	@Test
	public void testAddbuddy()
	{
		addressBook.addBuddy(xy);
		assertEquals(2,addressBook.addressbook.size());
	}
	public void testremoveBuddy()
	{
		addressBook.removeBuddy(1);
		assertEquals(1,addressBook.addressbook.size());
	}
	@Test
	public void testclear() {
		addressBook.addressbook.clear();
		assertEquals(0,addressBook.addressbook.size());
	}
	
    public void testexport2() {	
		//export
    	addressBook.writeObject(xinyuchen);
    	assertTrue(new File("ojbk.txt").exists());
    	new File("ojbk.txt").delete();
	}

    public void testexport3() throws IOException {	
    	addressBook.exportToXMLFile("kkk.txt");
    	assertTrue(new File("kkk.txt").exists());
    	new File("kkk.txt").delete();
    }
    
       
    @Test
	public void testimport(){
		
		AddressBook newBook = new AddressBook();
		newBook.readFile("ojb.txt");;
		assertEquals(newBook.toString(), addressBook.toString());
		assertEquals(newBook.addressbook.size(), 1);

	}
  
    @Test   
    public void testimport2(){
    	addressBook.writeObject(null);
		AddressBook newBook = new AddressBook();
		newBook.readObject();
		assertEquals(newBook.addressbook.size(), 0);
	}
}
 /*
    @Test
    public void testXMLLoad() 
    throws SAXException, ParserConfigurationException, IOException{
		
    	addressBook.exportToTextFile("test.txt");
		AddressBook newBook = new AddressBook();
		newBook.importFromXMlFile("test.txt");
		assertEquals(newBook.size(), addressBook.size());	
		new File("test.csv").delete();
	}
}
*/
