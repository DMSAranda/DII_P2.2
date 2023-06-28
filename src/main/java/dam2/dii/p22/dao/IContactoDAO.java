package dam2.dii.p22.dao;

import java.util.Map;

import dam2.dii.p22.model.Contact;

public interface IContactoDAO {

	public Map<Integer, Contact> getListaContactos();
	
	public void setListaContactos( Map<Integer, Contact> nueva);
	
}
