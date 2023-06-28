package dam2.dii.p22.gestor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dam2.dii.p22.dao.ContactoDAOImplMem;
import dam2.dii.p22.dao.IContactoDAO;
import dam2.dii.p22.model.Contact;

public class Gestor {

	private static IContactoDAO udao = new ContactoDAOImplMem(); // UNICAMENTE AQUI ELEGIMOS SI CARGA POR MEMORIA, BBDD,
																	// ETC

	// EN NUESTRO CASO CARGAMOS 2 USUARIOS DESDE MEMORIA, PARA TENER DATOS EN LA
	// API-REST

	private static Map<Integer, Contact> agenda2 = Gestor.getListaContactos();
	
	

	// CREATE - BBDD

	public static Map<Integer, Contact> getListaContactos() {

		Map<Integer, Contact> agenda = udao.getListaContactos();

		return agenda;
	}

	// SELECT - READ

	public static ArrayList<Contact> extraerListaContactos() {

		ArrayList<Contact> agenda = new ArrayList<Contact>();

		for (Map.Entry<Integer, Contact> entrada : agenda2.entrySet()) {

			Contact contacto = entrada.getValue();
			agenda.add(contacto);
		}

		return agenda;
	}

	
	public static Contact leerContacto(int codigo) {

		Contact contacto = agenda2.get(codigo);

		return contacto;
	}

	// INSERT

	public static boolean insertarContacto(Contact con) {
		boolean result = false;

		if (!agenda2.containsKey(con.getId())) {

			agenda2.put(con.getId(), con);
			result = true;
		}
		return result;
	}

	// DELETE

	public static boolean borrarContacto(int id) {
		boolean result = false;

		if (agenda2.containsKey(id)) {

			agenda2.remove(id);
			result = true;
		}
		return result;
	}

	// UPDATE

	public static boolean actualizarContacto(Contact con) {
		boolean result = false;

		if (agenda2.containsKey(con.getId())) {

			agenda2.replace(con.getId(), null);
			agenda2.replace(con.getId(), con);
			result = true;
		}
		return result;
	}

	// SERVICIO

	public static void setListaContactos(Map<Integer, Contact> nueva) {

		udao.setListaContactos(nueva);

	}

	public static void agregarAgenda(Contact contact) {

		Map<Integer, Contact> libreta = udao.getListaContactos();

		libreta.put(contact.getId(), contact);

	}

	public static void borrarAgenda(Contact contact) {

		Map<Integer, Contact> libreta = udao.getListaContactos();

		libreta.remove(contact);

		udao.setListaContactos(libreta);

	}

	public static boolean compruebaExiste(Contact contact) {

		Map<Integer, Contact> libreta = udao.getListaContactos();

		boolean existe = false;

		try {

			for (Map.Entry<Integer, Contact> entrada : libreta.entrySet()) {

				Contact con = entrada.getValue();

				String email = contact.getEmail();
				String phone = contact.getPhone();

				if (email.equals(con.getEmail()) || phone.equals(con.getPhone())) {
					existe = true;
				}

			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Datos fuera de límites");
		}

		return existe;
	}

	public static void actualizarContacto(Contact con, Contact con2) {

		Map<Integer, Contact> libreta = udao.getListaContactos();

		try {

			Map<Integer, Contact> nuevo = null;

			for (Map.Entry<Integer, Contact> entrada : libreta.entrySet()) {

				Contact aux = entrada.getValue();

				String email = aux.getEmail();
				String phone = aux.getPhone();

				if (email.equals(con.getEmail()) || phone.equals(con.getPhone())) {

				} else {
					nuevo.put(aux.getId(), aux);
				}

			}

			nuevo.put(con2.getId(), con2);

			udao.setListaContactos(nuevo);

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Datos fuera de límites");
		}

	}

	public static Contact compruebaId(int id) {

		Map<Integer, Contact> libreta = udao.getListaContactos();

		Contact con2 = new Contact();

		try {

			for (Map.Entry<Integer, Contact> entrada : libreta.entrySet()) {

				Contact con = entrada.getValue();

				if (id == (con.getId())) {
					con2 = con;
				}

			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Datos fuera de límites");
		}

		return con2;
	}

}
