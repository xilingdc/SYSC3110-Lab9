package lab9.source.lab9.lab9;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class AddressBook implements Serializable {

	private static final long serialVersionUID = 1L;
	public ArrayList<BuddyInfo> addressbook = new ArrayList<>();
	
	public AddressBook() {
	}

	public void addBuddy(BuddyInfo aa) {
		if (aa != null) {
			addressbook.add(aa);
		}
	}

	public String toString() {

		String addresses = "";

		for (Object buddy : this.addressbook) {

			addresses += buddy.toString() + "\n";

		}
		System.out.println(addresses);
		return addresses;

	}

	public void removeBuddy(int index) {
		if (index >= 0 && index < addressbook.size()) {
			addressbook.remove(index);
		}
	}

	public BuddyInfo getBuddy(int i) {
		if (i >= 0 && i < addressbook.size()) {
			return addressbook.get(i);
		}
		return null;
	}

	public void exportToTextFile(String filename) { // through bufferwriter
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			out.write(this.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeObject(BuddyInfo a) {
         // through Serialization
		try {
			ObjectOutputStream p = new ObjectOutputStream(new FileOutputStream("ossjbk.txt"));
			p.writeObject(a);
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

   
	public void exportToXMLFile(String path) throws IOException{
		StringBuffer buff = new StringBuffer("<book>");
		for(int i = 0; i < addressbook.size(); i++){
			buff.append(addressbook.get(i).toXMLString());
		}
		buff.append("</book>");
		BufferedWriter stream = new BufferedWriter(new FileWriter(path));
		stream.write(buff.toString());
		stream.close();
	}
	
	public void readFile(String file){
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while(line != null){
				BuddyInfo buddy = BuddyInfo.create(line);
				if(buddy != null)
					addressbook.add(buddy);
	
				line = br.readLine();
			}
			br.close();
		} catch(FileNotFoundException e){
			System.out.println("The given file was not found: " + file);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void readObject(){
		try{	
			FileInputStream f = new FileInputStream("ossjbk.txt");
			ObjectInputStream oStream = new ObjectInputStream(f);
			BuddyInfo book = (BuddyInfo)oStream.readObject();
			oStream.close();
			System.out.println("...");
			System.out.println(book);
			oStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void importFromXMlFile(String path)throws SAXException, IOException, ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new FileInputStream(path));
		NodeList list = doc.getElementsByTagName("book");
		for(int i = 0; i < list.getLength(); i++){
			Node p = list.item(i);			
			Element l = (Element) p;
			System.out.println(l.getTextContent());			
			//addressbook.add(BuddyInfo.creates((Element)list.item(i)));
		}
	//	System.out.println("XML");
	//	System.out.println(addressbook.toString());
	}
	
	public static void main(String[] args) throws IOException {
		AddressBook a = new AddressBook();
		BuddyInfo aa = new BuddyInfo("xy","119","3167865896");
        a.addBuddy(aa);
        a.exportToTextFile("ojbk.txt");
		a.exportToXMLFile("kkkk.xml");
		
		try {
			a.importFromXMlFile("kkkk.xml");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		a.readFile("ojbk.txt");
		a.writeObject(aa);
		a.readObject();		
		System.out.println("........");
		
	}
}
