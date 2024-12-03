import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;

public class ProfessorEntidadeBOImplTest {
    @Mock
    private DAO<ProfessorEntidade> daoMock;

    @Mock
    private AppView viewMock;

    private ProfessorEntidadeBOImpl professorEntidadeBO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        professorEntidadeBO = new ProfessorEntidadeBOImpl(viewMock);
        professorEntidadeBO.setDao(daoMock);
    }

    @Test
    public void testCreateProfessorEntidade_Success() throws Exception {
        List<ProfessorEntidade> relacionamentos = Arrays.asList(new ProfessorEntidade(), new ProfessorEntidade());

        professorEntidadeBO.createProfessorEntidade(relacionamentos);

        verify(daoMock, times(2)).save(any(ProfessorEntidade.class));
        verify(viewMock, times(1)).handleModelChange(relacionamentos);
    }
}
