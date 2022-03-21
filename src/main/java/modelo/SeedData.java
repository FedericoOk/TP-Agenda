package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.google.gson.Gson;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContacto;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PaisDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.ProvinciaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.mysql.DAOSQLFactory;

public class SeedData {

	public static void initialize(DAOAbstractFactory DAOFactory) {

		// Países
		PaisDAO paisDAO = DAOFactory.createPaisDAO(); // TODO: faltaría cerrar los DAO's?
		List<PaisDTO> paises = populatePaises(paisDAO);

		// Provincias
		ProvinciaDAO provinciaDAO = DAOFactory.createProvinciaDAO();
		List<ProvinciaDTO> provincias = populateProvincias(provinciaDAO, paises);

		// Localidades
		LocalidadDAO localidadDAO = DAOFactory.createLocalidadDAO();
		List<LocalidadDTO> localidades = populateLocalidades(localidadDAO, provincias, 50); // Para testear.

		// Tipos de Contacto
		TipoContactoDAO tipoContactoDAO = DAOFactory.createTipoContactoDAO();
		List<TipoContacto> tiposDeContacto = populateTiposDeContacto(tipoContactoDAO);

		// Contactos
		PersonaDAO personaDAO = DAOFactory.createPersonaDAO();
		populateContactos(personaDAO, localidades, tiposDeContacto);

	}

	private static List<LocalidadDTO> populateLocalidades(LocalidadDAO localidadDAO, List<ProvinciaDTO> provincias,
			int amount) {

		long seed = 1L;

		List<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();

		if (localidadDAO.readAll().isEmpty()) { // Si ya hay localidades no hacemos nada

			// Argentina

			// Retrieve Localidades fron JSON
			BufferedReader br;
			try {

				br = new BufferedReader(new FileReader("src/main/resources/localidades.json"));
				JSONLocalidades json = new Gson().fromJson(br, JSONLocalidades.class);
				List<modelo.SeedData.JSONLocalidades.Localidad> localidadesJson = json.localidades;

				amount = amount > localidadesJson.size() ? localidadesJson.size() : amount;

				Collections.shuffle(localidadesJson, new Random(seed)); // Permutamos aleatoriamente las localidades

				while (amount-- > 0) {

					JSONLocalidades.Localidad localidad = localidadesJson.remove(0);
					String nombre = localidad.nombre;
					String nombreProvincia = localidad.provincia.nombre;

					Optional<ProvinciaDTO> optional = provincias.stream()
							.filter(p -> p.getNombre().equals(nombreProvincia)).findFirst();
					ProvinciaDTO provincia = null;
					try {
						provincia = optional.get();
					} catch (Exception e) {
						// TODO: esto es porque no me está guardando los datos con UTF-8 y rompe
						amount++;
						continue;
					}

					LocalidadDTO nuevaLocalidad = new LocalidadDTO(nombre, provincia);

					localidades.add(nuevaLocalidad);
					localidadDAO.insert(nuevaLocalidad);

				}

				List<LocalidadDTO> tmp = new ArrayList<LocalidadDTO>();

				// Estados Unidos

				ProvinciaDTO alabama = provincias.stream().filter(p -> p.getNombre().equals("Alabama")).findFirst()
						.get();
				tmp.add(new LocalidadDTO("Abbeville", alabama));
				tmp.add(new LocalidadDTO("Hamilton", alabama));

				ProvinciaDTO georgia = provincias.stream().filter(p -> p.getNombre().equals("Georgia")).findFirst()
						.get();
				tmp.add(new LocalidadDTO("Atlanta", georgia));
				tmp.add(new LocalidadDTO("Savannah", georgia));

				// Rusia

				ProvinciaDTO astracán = provincias.stream().filter(p -> p.getNombre().equals("Astracán")).findFirst()
						.get();
				tmp.add(new LocalidadDTO("Jarabali", astracán));
				tmp.add(new LocalidadDTO("Tulugánovka", astracán));

				ProvinciaDTO moscú = provincias.stream().filter(p -> p.getNombre().equals("Moscú")).findFirst().get();
				tmp.add(new LocalidadDTO("Dmítrov", moscú));
				tmp.add(new LocalidadDTO("Istra", moscú));

				// Alemania

				ProvinciaDTO berlín = provincias.stream().filter(p -> p.getNombre().equals("Berlín")).findFirst().get();
				tmp.add(new LocalidadDTO("Dahlem", berlín));
				tmp.add(new LocalidadDTO("Gatow", berlín));

				ProvinciaDTO brandeburgo = provincias.stream().filter(p -> p.getNombre().equals("Brandeburgo"))
						.findFirst().get();
				tmp.add(new LocalidadDTO("Friedrichswalde", brandeburgo));
				tmp.add(new LocalidadDTO("Ziethen", brandeburgo));

				// Noruega

				ProvinciaDTO nordland = provincias.stream().filter(p -> p.getNombre().equals("Nordland")).findFirst()
						.get();
				tmp.add(new LocalidadDTO("Lurøy", nordland));
				tmp.add(new LocalidadDTO("Værøy", nordland));

				ProvinciaDTO vestfoldOgTelemark = provincias.stream()
						.filter(p -> p.getNombre().equals("Vestfold og Telemark")).findFirst().get();
				tmp.add(new LocalidadDTO("Fyresdal", vestfoldOgTelemark));
				tmp.add(new LocalidadDTO("Porsgrunn", vestfoldOgTelemark));

				for (LocalidadDTO localidad : tmp) {
					localidadDAO.insert(localidad);
					localidades.add(localidad);
				}

				System.out.println("Localidades cargadas con éxito.");

			} catch (FileNotFoundException e) {

				e.printStackTrace();
				System.out.println("No se pudo obtener las localidades.");

			}

		}

		return localidades;
		// return localidadDAO.readAll();

	}

	private static List<TipoContacto> populateTiposDeContacto(TipoContactoDAO tipoContactoDAO) {

		List<TipoContacto> tiposDecontactos = new ArrayList<TipoContacto>();

		if (tipoContactoDAO.readAll().isEmpty()) { // Si ya hay localidades no hacemos nada

			tiposDecontactos.add(new TipoContacto("Familia"));
			tiposDecontactos.add(new TipoContacto("Amigos"));
			tiposDecontactos.add(new TipoContacto("Trabajo"));

			for (TipoContacto contacto : tiposDecontactos)
				tipoContactoDAO.insert(contacto);

			System.out.println("Tipos de Contacto cargados con éxito.");

		}

		return tiposDecontactos;
		// return tipoContactoDAO.readAll();

	}

	private static List<PersonaDTO> populateContactos(PersonaDAO personaDAO, List<LocalidadDTO> localidades,
			List<TipoContacto> tiposDeContacto) {

		DomicilioDAO domicilioDAO = new DAOSQLFactory().createDomicilioDAO();

		if (!personaDAO.readAll().isEmpty())
			return null;

		long seed = 1L;
		Random random = new Random(seed);

		List<PersonaDTO> contactos = new ArrayList<PersonaDTO>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {

			DomicilioDTO domicilio1 = new DomicilioDTO("Avenida Siempreviva", "742", "0", "",
					localidades.get(random.nextInt(localidades.size())));
			PersonaDTO persona1 = new PersonaDTO("Homero Simpson", "123456", "homer@donuts.com",
					dateFormat.parse("2020-03-01"), filterByName(tiposDeContacto, "Familia"), domicilio1, null, null);
			// persona1.addDomicilio(domicilio1);
			contactos.add(persona1);

			DomicilioDTO domicilio2 = new DomicilioDTO("Avenida Siempreviva", "742", "", "",
					localidades.get(random.nextInt(localidades.size())));
			PersonaDTO persona2 = new PersonaDTO("Marge Simpson", "987654", "marge@hotmail.com",
					dateFormat.parse("2020-03-04"), filterByName(tiposDeContacto, "Amigos"), domicilio2, null, null);
			// persona2.addDomicilio(domicilio2);
			contactos.add(persona2);

			DomicilioDTO domicilio3 = new DomicilioDTO("Calle Falsa", "123", "", "",
					localidades.get(random.nextInt(localidades.size())));
			PersonaDTO persona3 = new PersonaDTO("Bart Simpson", "666666", "elbarto@gmail.com",
					dateFormat.parse("2006-06-06"), filterByName(tiposDeContacto, "Familia"), domicilio3, null, null);
			// persona3.addDomicilio(domicilio3);
			contactos.add(persona3);

			DomicilioDTO domicilio4 = new DomicilioDTO("All Vegetables", "2718", "2", "e",
					localidades.get(random.nextInt(localidades.size())));
			PersonaDTO persona4 = new PersonaDTO("Lisa Simpson", "3141592653", "jazzrules@realponies",
					dateFormat.parse("2020-03-27"), filterByName(tiposDeContacto, "Trabajo"), domicilio4, null, null);
			// persona4.addDomicilio(domicilio4);
			contactos.add(persona4);

			for (PersonaDTO persona : contactos) {
				personaDAO.insert(persona);
				// domicilioDAO.insert(persona.getDomicilio());
			}

			System.out.println("Personas cargadas con éxito.");

		} catch (ParseException e) {
			e.printStackTrace();
		}

		// return contactos;
		return personaDAO.readAll();

	}

	private static TipoContacto filterByName(List<TipoContacto> tiposDeContacto, String name) {
		return tiposDeContacto.stream().filter(t -> t.getNombre().equals(name)).findAny().orElse(null);
	}

	private static List<PaisDTO> populatePaises(PaisDAO paisDAO) {

		List<PaisDTO> paises = new ArrayList<PaisDTO>();

		if (paisDAO.readAll().isEmpty()) {

			paises.add(new PaisDTO("Argentina"));
			paises.add(new PaisDTO("Chile"));
			paises.add(new PaisDTO("Brasil"));
			paises.add(new PaisDTO("Paraguay"));
			paises.add(new PaisDTO("Bolivia"));
			paises.add(new PaisDTO("Estados Unidos"));
			paises.add(new PaisDTO("China"));
			paises.add(new PaisDTO("Italia"));
			paises.add(new PaisDTO("Francia"));
			paises.add(new PaisDTO("España"));
			paises.add(new PaisDTO("Hungría"));
			paises.add(new PaisDTO("Alemania"));
			paises.add(new PaisDTO("Polonia"));
			paises.add(new PaisDTO("Noruega"));
			paises.add(new PaisDTO("Rusia"));
			paises.add(new PaisDTO("Inglaterra"));

			for (PaisDTO pais : paises)
				paisDAO.insert(pais);

		}

		return paises;

	}

	private static List<ProvinciaDTO> populateProvincias(ProvinciaDAO provinciaDAO, List<PaisDTO> países) {

		List<ProvinciaDTO> provincias = new ArrayList<ProvinciaDTO>();

		if (provinciaDAO.readAll().isEmpty()) {

			PaisDTO argentina = países.stream().filter(p -> p.getNombre().equals("Argentina")).findFirst().get();

			provincias.add(new ProvinciaDTO("Ciudad Autónoma de Buenos Aires", argentina));
			provincias.add(new ProvinciaDTO("Tierra del Fuego, Antártida e Islas del Atlántico Sur", argentina));
			provincias.add(new ProvinciaDTO("Buenos Aires", argentina));
			provincias.add(new ProvinciaDTO("Catamarca", argentina));
			provincias.add(new ProvinciaDTO("Chaco", argentina));
			provincias.add(new ProvinciaDTO("Chubut", argentina));
			provincias.add(new ProvinciaDTO("Córdoba", argentina));
			provincias.add(new ProvinciaDTO("Corrientes", argentina));
			provincias.add(new ProvinciaDTO("Entre Ríos", argentina));
			provincias.add(new ProvinciaDTO("Formosa", argentina));
			provincias.add(new ProvinciaDTO("Jujuy", argentina));
			provincias.add(new ProvinciaDTO("La Pampa", argentina));
			provincias.add(new ProvinciaDTO("La Rioja", argentina));
			provincias.add(new ProvinciaDTO("Mendoza", argentina));
			provincias.add(new ProvinciaDTO("Misiones", argentina));
			provincias.add(new ProvinciaDTO("Neuquén", argentina));
			provincias.add(new ProvinciaDTO("Río Negro", argentina));
			provincias.add(new ProvinciaDTO("Salta", argentina));
			provincias.add(new ProvinciaDTO("San Juan", argentina));
			provincias.add(new ProvinciaDTO("San Luis", argentina));
			provincias.add(new ProvinciaDTO("Santa Cruz", argentina));
			provincias.add(new ProvinciaDTO("Santa Fe", argentina));
			provincias.add(new ProvinciaDTO("Santiago del Estero", argentina));
			provincias.add(new ProvinciaDTO("Tierra del Fuego", argentina));
			provincias.add(new ProvinciaDTO("Tucumán", argentina));

			PaisDTO estadosUnidos = países.stream().filter(p -> p.getNombre().equals("Estados Unidos")).findFirst()
					.get();

			provincias.add(new ProvinciaDTO("Alabama", estadosUnidos));
			provincias.add(new ProvinciaDTO("Alaska", estadosUnidos));
			provincias.add(new ProvinciaDTO("Arizona", estadosUnidos));
			provincias.add(new ProvinciaDTO("Arkansas", estadosUnidos));
			provincias.add(new ProvinciaDTO("California", estadosUnidos));
			provincias.add(new ProvinciaDTO("Colorado", estadosUnidos));
			provincias.add(new ProvinciaDTO("Connecticut", estadosUnidos));
			provincias.add(new ProvinciaDTO("Delaware", estadosUnidos));
			provincias.add(new ProvinciaDTO("Florida", estadosUnidos));
			provincias.add(new ProvinciaDTO("Georgia", estadosUnidos));

			PaisDTO rusia = países.stream().filter(p -> p.getNombre().equals("Rusia")).findFirst().get();

			provincias.add(new ProvinciaDTO("Amur", rusia));
			provincias.add(new ProvinciaDTO("Arjángelsk", rusia));
			provincias.add(new ProvinciaDTO("Astracán", rusia));
			provincias.add(new ProvinciaDTO("Bélgorod", rusia));
			provincias.add(new ProvinciaDTO("Briansk", rusia));
			provincias.add(new ProvinciaDTO("Cheliábinsk", rusia));
			provincias.add(new ProvinciaDTO("Irkutsk", rusia));
			provincias.add(new ProvinciaDTO("Ivánovo", rusia));
			provincias.add(new ProvinciaDTO("Kaliningrado", rusia));
			provincias.add(new ProvinciaDTO("Moscú", rusia));

			PaisDTO alemania = países.stream().filter(p -> p.getNombre().equals("Alemania")).findFirst().get();

			provincias.add(new ProvinciaDTO("Baden-Wurtemberg", alemania));
			provincias.add(new ProvinciaDTO("Baviera", alemania));
			provincias.add(new ProvinciaDTO("Berlín", alemania));
			provincias.add(new ProvinciaDTO("Brandeburgo", alemania));
			provincias.add(new ProvinciaDTO("Bremen", alemania));
			provincias.add(new ProvinciaDTO("Hamburgo", alemania));
			provincias.add(new ProvinciaDTO("Hesse", alemania));
			provincias.add(new ProvinciaDTO("Mecklemburgo-Pomerania Occidental", alemania));
			provincias.add(new ProvinciaDTO("Sajonia", alemania));
			provincias.add(new ProvinciaDTO("Renania del Norte-Westfalia", alemania));

			PaisDTO noruega = países.stream().filter(p -> p.getNombre().equals("Noruega")).findFirst().get();

			provincias.add(new ProvinciaDTO("Agder", noruega));
			provincias.add(new ProvinciaDTO("Innlandet", noruega));
			provincias.add(new ProvinciaDTO("Møre og Romsdal", noruega));
			provincias.add(new ProvinciaDTO("Nordland", noruega));
			provincias.add(new ProvinciaDTO("Oslo", noruega));
			provincias.add(new ProvinciaDTO("Rogaland", noruega));
			provincias.add(new ProvinciaDTO("Svalbard", noruega));
			provincias.add(new ProvinciaDTO("Trøndelag", noruega));
			provincias.add(new ProvinciaDTO("Troms og Finnmark", noruega));
			provincias.add(new ProvinciaDTO("Vestfold og Telemark", noruega));
			provincias.add(new ProvinciaDTO("Vestland", noruega));
			provincias.add(new ProvinciaDTO("Viken", noruega));

			for (ProvinciaDTO provincia : provincias)
				provinciaDAO.insert(provincia);

		}

		return provincias;

	}

	@SuppressWarnings("unused")
	private class JSONLocalidades {
		// public Localidad[] localidades;
		public List<Localidad> localidades;
		public int total;
		public int cantidad;
		public Object parametros;
		public int inicio;

		abstract class BasicEntity {
			public String id;
			public String nombre;
		}

		class Localidad extends BasicEntity {
			private String categoria;
			private String fuente;
			public Provincia provincia;

			class Centroide {
				private double lat;
				private double lon;
			}

			class Municipio extends BasicEntity {
			}

			class Departamento extends BasicEntity {
			}

			class Provincia extends BasicEntity {
			}

			class LocalidadCensal extends BasicEntity {
			}
		}
	}

}
