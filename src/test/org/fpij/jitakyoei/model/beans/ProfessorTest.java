import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProfessorTest {
    private Professor professor;

    @BeforeEach
    public void setUp() {
        professor = new Professor();
        Filiado filiado = new Filiado();
        filiado.setId(1L);
        professor.setFiliado(filiado);
    }

    @Test
    public void testSetAndGetFiliado() {
        Filiado newFiliado = new Filiado();
        professor.setFiliado(newFiliado);
        assertEquals(newFiliado, professor.getFiliado());
    }

    @Test
    public void testEquals_SameFiliado() {
        Professor other = new Professor();
        other.setFiliado(professor.getFiliado());
        assertTrue(professor.equals(other));
    }
}
