import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;

public class EntidadeBOImplTest {
    @Mock
    private DAO<Entidade> daoMock;

    @Mock
    private AppView viewMock;

    private EntidadeBOImpl entidadeBO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        entidadeBO = new EntidadeBOImpl(viewMock);
        entidadeBO.setDao(daoMock);
    }

    @Test
    public void testCreateEntidade_Success() throws Exception {
        Entidade entidade = new Entidade();

        entidadeBO.createEntidade(entidade);

        verify(daoMock, times(1)).save(entidade);
        verify(viewMock, times(1)).handleModelChange(entidade);
    }

    @Test
    public void testSearchEntidade() throws Exception {
        Entidade entidade = new Entidade();
        List<Entidade> entidades = Arrays.asList(entidade);
        when(daoMock.search(any(Entidade.class))).thenReturn(entidades);

        List<Entidade> result = entidadeBO.searchEntidade(entidade);

        Assertions.assertEquals(1, result.size());
        verify(daoMock, times(1)).search(entidade);
    }
}
