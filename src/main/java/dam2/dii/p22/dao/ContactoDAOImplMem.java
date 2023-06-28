package dam2.dii.p22.dao;


import java.util.HashMap;
import java.util.Map;

import dam2.dii.p22.model.Contact;

public class ContactoDAOImplMem implements IContactoDAO {
	
	
	private static Map<Integer, Contact> agenda = new HashMap<Integer, Contact>();
	
	@Override
	public  Map<Integer, Contact> getListaContactos(){
		
		if (agenda.isEmpty()) {
	
			Contact one = new Contact(1, "David", "Muñoz", "absentastudio@gmail.com", "645769759", "WilburVictor"); // datos
																													// muestra
			Contact two = new Contact(2, "Hector", "Muñoz", "hms@gmail.com", "645769779", "El Perlita");
	
			agenda.put(1, one);
			agenda.put(2, two);
	
		}
		
		return agenda;
	}

	
	
	@Override
	public void setListaContactos( Map<Integer, Contact> nueva) {
		
		agenda = nueva;
		
	}
	


}
