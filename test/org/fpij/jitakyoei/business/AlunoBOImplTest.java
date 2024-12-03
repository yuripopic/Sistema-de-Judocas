import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;

public class AlunoBOImplTest {
    @Mock
    private DAO<Aluno> daoMock;

    @Mock
    private AppView viewMock;

    private AlunoBOImpl alunoBO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        alunoBO = new AlunoBOImpl(viewMock);
        alunoBO.setDao(daoMock);
    }

    @Test
    public void testCreateAluno_Success() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setFiliado(new Filiado());

        alunoBO.createAluno(aluno);

        verify(daoMock, times(1)).save(aluno);
        verify(viewMock, times(1)).handleModelChange(aluno);
    }

    @Test
    public void testCreateAluno_ThrowsException() {
        Aluno aluno = new Aluno();
        doThrow(new IllegalArgumentException()).when(daoMock).save(any(Aluno.class));

        Assertions.assertThrows(IllegalArgumentException.class, () -> alunoBO.createAluno(aluno));
    }

    @Test
    public void testListAll() throws Exception {
        List<Aluno> alunos = Arrays.asList(new Aluno(), new Aluno());
        when(daoMock.list()).thenReturn(alunos);

        List<Aluno> result = alunoBO.listAll();

        Assertions.assertEquals(2, result.size());
        verify(daoMock, times(1)).list();
    }
}
