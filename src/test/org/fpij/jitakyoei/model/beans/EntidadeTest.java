import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EntidadeTest {
    private Entidade entidade;

    @BeforeEach
    public void setUp() {
        entidade = new Entidade();
        entidade.setNome("Academia Central");
    }

    @Test
    public void testSetAndGetNome() {
        entidade.setNome("Nova Academia");
        assertEquals("Nova Academia", entidade.getNome());
    }

    @Test
    public void testEquals_SameNome() {
        Entidade other = new Entidade();
        other.setNome("Academia Central");
        assertTrue(entidade.equals(other));
    }

    @Test
    public void testEquals_DifferentNome() {
        Entidade other = new Entidade();
        other.setNome("Outra Academia");
        assertFalse(entidade.equals(other));
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = 29 * 7 + entidade.getNome().hashCode();
        assertEquals(expectedHashCode, entidade.hashCode());
    }
}
