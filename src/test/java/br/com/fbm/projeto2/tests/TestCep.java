package br.com.fbm.projeto2.tests;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.BeforeClass;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fbm.projeto2.bo.CepBO;
import br.com.fbm.projeto2.requests.CepRequest;
import io.restassured.response.Response;


/**
 * {@code TestCep} given implementation
 * to do consumer tests to the via cep api
 *
 * @author Fernando Bino Machado
 */
public class TestCep {

	private static CepBO cepBO;
	private static Response resp;
	
	@BeforeClass
	public static void setUp() {
		
		final CepRequest cepRequest = new CepRequest();
		
		resp = cepRequest.getResponseCep("83601650");
		
		final ObjectMapper objMapper = new ObjectMapper();
		
		try {
			cepBO = objMapper.readValue(resp.getBody().asString(), CepBO.class);
		}catch(final Exception exception) {
			cepBO = new CepBO();
		}
		
	}
	
	@Test
	@Ignore
	public void test1() {
		
		Response resp = get("https://viacep.com.br/ws/01001000/json/");
		
		System.out.println(resp.getStatusCode());
		System.out.println(resp.getTime());
		System.out.println(resp.getBody().asString());
		System.out.println(resp.getStatusLine());
		System.out.println(resp.getHeader("content-type"));
		
		assertEquals("Status Code 200", resp.getStatusCode(), 200);
		
	}
	
	@Test
	public void dadoQueBuscoEnderecoQuandoRequisicaoBemSucedida() {
		assertTrue("Status Code 200", resp.getStatusCode() == 200);
	}
	
	@Test
	public void dadoQueBuscoEnderecoQuandoCepEncontrado() {
		assertTrue("Cep Encontrado", cepBO.getCep() != null);
	}
	
}
