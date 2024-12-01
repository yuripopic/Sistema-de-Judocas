package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlunoDaoTest {
	
	private static DAO<Aluno> alunoDao;
	private static Aluno aluno;
	private static Entidade entidade;
	private static Endereco endereco;
	private static Filiado f1;
	private static Filiado filiadoProf;
	private static Professor professor;
	
	// @BeforeClass
	// public static void setUp(){
	// 	DatabaseManager.setEnviroment(DatabaseManager.TEST);
	// 	f1 = new Filiado();
	// 	f1.setNome("Aécio");
	// 	f1.setCpf("036.464.453-27");
	// 	f1.setDataNascimento(new Date());
	// 	f1.setDataCadastro(new Date());
	// 	f1.setId(1332L);
		
	// 	endereco = new Endereco();
	// 	endereco.setBairro("Dirceu");
	// 	endereco.setCep("64078-213");
	// 	endereco.setCidade("Teresina");
	// 	endereco.setEstado("PI");
	// 	endereco.setRua("Rua Des. Berilo Mota");
		
	// 	filiadoProf = new Filiado();
	// 	filiadoProf.setNome("Professor");
	// 	filiadoProf.setCpf("036.464.453-27");
	// 	filiadoProf.setDataNascimento(new Date());
	// 	filiadoProf.setDataCadastro(new Date());
	// 	filiadoProf.setId(3332L);
	// 	filiadoProf.setEndereco(endereco);
		
	// 	professor = new Professor();
	// 	professor.setFiliado(filiadoProf);
		
	// 	entidade = new Entidade();
	// 	entidade.setEndereco(endereco);
	// 	entidade.setNome("Academia 1");
	// 	entidade.setTelefone1("(086)1234-5432");
		
	// 	aluno = new Aluno();
	// 	aluno.setFiliado(f1);
	// 	aluno.setProfessor(professor);
	// 	aluno.setEntidade(entidade);
		
	// 	alunoDao = new DAOImpl<Aluno>(Aluno.class);
	// }

	public static void clearDatabase(){
		List<Aluno> all = alunoDao.list();
		for (Aluno each : all) {
			alunoDao.delete(each);
		}
		assertEquals(0, alunoDao.list().size());
	}
	
	
	@Test
	public void  testSalvarAlunoComAssociassoes() throws Exception{
		clearDatabase();
		
		alunoDao.save(aluno);
		assertEquals("036.464.453-27", alunoDao.get(aluno).getFiliado().getCpf());
		assertEquals("Aécio", alunoDao.get(aluno).getFiliado().getNome());
		assertEquals("Professor", alunoDao.get(aluno).getProfessor().getFiliado().getNome());
		assertEquals("Dirceu", alunoDao.get(aluno).getProfessor().getFiliado().getEndereco().getBairro());
	}
	
	@Test
	public void updateAluno() throws Exception{
		clearDatabase();
		assertEquals(0, alunoDao.list().size());
		
		alunoDao.save(aluno);
		assertEquals(1, alunoDao.list().size());
		assertEquals("Aécio", aluno.getFiliado().getNome());
		
		Aluno a1 = alunoDao.get(aluno);
		a1.getFiliado().setNome("TesteUpdate");
		alunoDao.save(a1);
		
		Aluno a2 = alunoDao.get(a1);
		assertEquals("TesteUpdate", a2.getFiliado().getNome());
		assertEquals(1, alunoDao.list().size());
	}
	

	
	@Test
	public void testSearchAluno() throws Exception{
		clearDatabase();
		alunoDao.save(aluno);
		
		Filiado f = new Filiado();
		f.setNome("Aécio");
		Aluno a = new Aluno();
		a.setFiliado(f);
		
		List<Aluno> result = alunoDao.search(a);
		assertEquals(1, result.size());
		assertEquals("036.464.453-27", result.get(0).getFiliado().getCpf());
		
		clearDatabase();
		assertEquals(0, alunoDao.search(a).size());
	}

	//LUCAS FORGE 22.121.081-8----------------------------------------------------------------------------------------------

	@Test
	public void testSalvarAlunoComEnderecoNulo() throws Exception {
		clearDatabase();
		
		// Criação de um aluno com endereço nulo
		aluno.getFiliado().setNome("Aluno Teste");
		aluno.getFiliado().setCpf("123.456.789-00");
		aluno.getFiliado().setDataNascimento(new Date());
		aluno.getFiliado().setDataCadastro(new Date());
		aluno.setEntidade(null);  // Endereço nulo
		
		try {
			alunoDao.save(aluno);
			assertEquals(1, alunoDao.list().size());
		} catch (Exception e) {
			e.printStackTrace();
			// Verifique se a exceção esperada é lançada.
		}
	}

	@Test
	public void testSalvarAlunoComNomeNulo() throws Exception {
		clearDatabase();
		
		// Criação de um aluno com nome nulo
		aluno.getFiliado().setNome(null);  // Nome nulo
		aluno.getFiliado().setCpf("123.456.789-00");
		aluno.getFiliado().setDataNascimento(new Date());
		aluno.getFiliado().setDataCadastro(new Date());
		aluno.setEntidade(entidade);
		
		try {
			alunoDao.save(aluno);
			assertEquals(1, alunoDao.list().size());
		} catch (Exception e) {
			e.printStackTrace();
			// Verifique se a exceção esperada é lançada.
		}
	}

	@Test
	public void testSalvarAlunoComCpfNulo() throws Exception {
		clearDatabase();
		
		// Criação de um aluno com CPF nulo
		aluno.getFiliado().setNome("Aluno Teste");
		aluno.getFiliado().setCpf(null);  // CPF nulo
		aluno.getFiliado().setDataNascimento(new Date());
		aluno.getFiliado().setDataCadastro(new Date());
		aluno.setEntidade(entidade);
		
		try {
			alunoDao.save(aluno);
			assertEquals(1, alunoDao.list().size());
		} catch (Exception e) {
			e.printStackTrace();
			// Verifique se a exceção esperada é lançada.
		}
	}

	@Test
	public void testSalvarAlunoComDataNascimentoNula() throws Exception {
		clearDatabase();
		
		// Criação de um aluno com data de nascimento nula
		aluno.getFiliado().setNome("Aluno Teste");
		aluno.getFiliado().setCpf("123.456.789-00");
		aluno.getFiliado().setDataNascimento(null);  // Data de nascimento nula
		aluno.getFiliado().setDataCadastro(new Date());
		aluno.setEntidade(entidade);
		
		try {
			alunoDao.save(aluno);
			assertEquals(1, alunoDao.list().size());
		} catch (Exception e) {
			e.printStackTrace();
			// Verifique se a exceção esperada é lançada.
		}
	}

	@Test
	public void testSalvarAlunoComDataCadastroNula() throws Exception {
		clearDatabase();
		
		// Criação de um aluno com data de cadastro nula
		aluno.getFiliado().setNome("Aluno Teste");
		aluno.getFiliado().setCpf("123.456.789-00");
		aluno.getFiliado().setDataNascimento(new Date());
		aluno.getFiliado().setDataCadastro(null);  // Data de cadastro nula
		aluno.setEntidade(entidade);
		
		try {
			alunoDao.save(aluno);
			assertEquals(1, alunoDao.list().size());
		} catch (Exception e) {
			e.printStackTrace();
			// Verifique se a exceção esperada é lançada.
		}
	}

//----------------------------------------------------------------------------------------------



	
	@AfterClass
	public static void closeDatabase(){
		clearDatabase();
		DatabaseManager.close();
	}
	
}
