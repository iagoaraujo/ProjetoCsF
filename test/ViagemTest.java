import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Before;


public class ViagemTest {

	private CenarioTestes cenarioTestes;
	private GenericDAO dao;
	
	@Before
	public void setUp() {
		cenarioTestes = new CenarioTestes();
		dao = new GenericDAOImpl();
	}
}
