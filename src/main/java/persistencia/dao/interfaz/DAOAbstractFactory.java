package persistencia.dao.interfaz;


public interface DAOAbstractFactory 
{
	public PersonaDAO createPersonaDAO();
	public DomicilioDAO createDomicilioDAO();
	public PaisDAO createPaisDAO();
	public ProvinciaDAO createProvinciaDAO();
	public LocalidadDAO createLocalidadDAO();
	public TipoContactoDAO createTipoContactoDAO();
}
