package lab9.source.lab9.lab9;

import java.io.Serializable;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BuddyInfo implements Serializable {

	private String name;
    private String address;
    private String phone;
    
    private static final String DELIMETER = "$";
    
    public BuddyInfo(String name, String address, String phone) {
    
    	this.name=name;
    	this.address=address;
    	this.phone=phone;
    }
    public String getName() {
    
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
    public boolean equals(BuddyInfo buddyother ) {
    	if((this.name).equals(buddyother.getName()) && (this.address).equals(buddyother.getAddress()) && (this.phone).equals(buddyother.getPhone())) {
    		return true;
    	} 	
    	else {
    		return false;
    	}
    
    }
    
    public String serialize(){
		return this.name + DELIMETER + this.address + DELIMETER + this.phone;
	}
	
	public String toXMLString(){
		System.out.println("<name>" + this.name + "</name>\n" 
				+ "<address>" + this.address + "</address>\n"
				+ "<phone>" + this.phone + "</phone>");
		return "<name>" + this.name + "</name>\n" 
				+ "<address>" + this.address + "</address>\n"
				+ "<phone>" + this.phone + "</phone>";
		
	}
	
    
    public String toString(){

		return(name+"$"+address+"$"+ phone);

	}
 
    public static BuddyInfo create(String serial){
		BuddyInfo buddy = null;
		StringTokenizer st = new StringTokenizer(serial, DELIMETER);

		buddy = new BuddyInfo(st.nextToken(), "", "");
		buddy.setAddress(st.nextToken());
		buddy.setPhone(st.nextToken());		
		return buddy;
	}
    

	public static BuddyInfo creates(Element node){
		BuddyInfo info = new BuddyInfo("","","");
		NodeList list;

		list = node.getElementsByTagName("name");
		if(list.getLength() > 0){
			info.setName(list.item(0).getTextContent());
		}
	
		list = node.getElementsByTagName("address");
		if(list.getLength() > 0){
			info.setAddress(list.item(0).getTextContent());
		}
		
		list = node.getElementsByTagName("phone");
		if(list.getLength() > 0){
			info.setPhone(list.item(0).getTextContent());
		}
		
		return info;
	}
    
    public void show() {
    	System.out.println("Name is "+name+",  address is "+address+",  Phone is "+phone);
    }
    
    
    public static void main(String[] args) {
    	BuddyInfo gg = new BuddyInfo("xinyuchen","carleton","6132764687");
    	BuddyInfo xy = new BuddyInfo("xy","119","3167865896");
 
    	gg.show();
    	xy.show();
    	gg.toXMLString();
	} 
}
