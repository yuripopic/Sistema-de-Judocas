package org.fpij.jitakyoei.model.beans;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AlunoTest {
    private Aluno aluno;

    @BeforeEach
    public void setUp() {
        aluno = new Aluno();
        Filiado filiado = new Filiado();
        filiado.setId(1L);
        filiado.setNome("João Silva");
        aluno.setFiliado(filiado);
    }

    @Test
    public void testSetAndGetFiliado() {
        Filiado filiado = new Filiado();
        aluno.setFiliado(filiado);
        assertEquals(filiado, aluno.getFiliado());
    }

    @Test
    public void testEquals_SameFiliado() {
        Aluno other = new Aluno();
        other.setFiliado(aluno.getFiliado());
        assertTrue(aluno.equals(other));
    }

    @Test
    public void testEquals_DifferentFiliado() {
        Aluno other = new Aluno();
        Filiado differentFiliado = new Filiado();
        differentFiliado.setId(2L);
        other.setFiliado(differentFiliado);
        assertFalse(aluno.equals(other));
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = 29 * 7 + (int) (long) aluno.getFiliado().getId();
        assertEquals(expectedHashCode, aluno.hashCode());
    }
}
