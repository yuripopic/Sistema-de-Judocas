import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RgTest {
    private Rg rg;

    @BeforeEach
    public void setUp() {
        rg = new Rg("123456", "SSP");
    }

    @Test
    public void testSetAndGetNumero() {
        rg.setNumero("654321");
        assertEquals("654321", rg.getNumero());
    }

    @Test
    public void testEquals_SameRg() {
        Rg other = new Rg("123456", "SSP");
        assertTrue(rg.equals(other));
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = rg.getNumero().hashCode() + rg.getOrgaoExpedidor().hashCode();
        assertEquals(expectedHashCode, rg.hashCode());
    }
}
