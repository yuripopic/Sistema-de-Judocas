import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FiliadoTest {
    private Filiado filiado;

    @BeforeEach
    public void setUp() {
        filiado = new Filiado();
        filiado.setId(1L);
        filiado.setNome("Carlos");
    }

    @Test
    public void testSetAndGetNome() {
        filiado.setNome("Joana");
        assertEquals("Joana", filiado.getNome());
    }

    @Test
    public void testEquals_SameIdAndNome() {
        Filiado other = new Filiado();
        other.setId(1L);
        other.setNome("Carlos");
        assertTrue(filiado.equals(other));
    }

    @Test
    public void testHashCode() {
        assertEquals(1, filiado.hashCode());
    }
}
