import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnderecoTest {
    private Endereco endereco;

    @BeforeEach
    public void setUp() {
        endereco = new Endereco();
    }

    @Test
    public void testSetAndGetRua() {
        endereco.setRua("Av. Brasil");
        assertEquals("Av. Brasil", endereco.getRua());
    }

    @Test
    public void testToString() {
        endereco.setRua("Av. Brasil");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Rio de Janeiro");
        endereco.setEstado("RJ");
        endereco.setCep("20000-000");

        String expected = "\nRua: Av. Brasil\nNumero: 123\nBairro: Centro\nCidade: Rio de Janeiro\nEstado: RJ\nCep: 20000-000";
        assertEquals(expected, endereco.toString());
    }
}
