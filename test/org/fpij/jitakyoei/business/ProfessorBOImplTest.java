import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;

public class ProfessorBOImplTest {
    @Mock
    private DAO<Professor> daoMock;

    @Mock
    private AppView viewMock;

    private ProfessorBOImpl professorBO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        professorBO = new ProfessorBOImpl(viewMock);
        professorBO.setDao(daoMock);
    }

    @Test
    public void testCreateProfessor_Success() throws Exception {
        Professor professor = new Professor();
        professor.setFiliado(new Filiado());

        professorBO.createProfessor(professor);

        verify(daoMock, times(1)).save(professor);
        verify(viewMock, times(1)).handleModelChange(professor);
    }
}
