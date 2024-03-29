package com.rapl.ponto.api.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rapl.ponto.api.entities.Empresa;
import com.rapl.ponto.api.services.EmpresaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc		// contexto web
@ActiveProfiles("test")
public class EmpresaControllerTest {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmpresaService empresaService;

	private static final String BUSCAR_EMPRESA_CNPJ_URL = "/api/empresas/cnpj/";
	private static final Long ID = Long.valueOf(1);
	private static final String CNPJ = "05750300000101";
	private static final String RAZAO_SOCIAL = "Empresa XYZ";

	@Test
	@WithMockUser
	public void testBuscarEmpresaCnpjInvalido() throws Exception {
		BDDMockito.given(this.empresaService.buscarPorCnpj(Mockito.anyString())).willReturn(Optional.empty());

		String mensagem = messageSource.getMessage("erro.empresa-nao-encontrada", null, LocaleContextHolder.getLocale());
		
		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_EMPRESA_CNPJ_URL + CNPJ)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value(mensagem + " " + CNPJ));
		
//				.andExpect(jsonPath("$.errors").value("Empresa não encontrada para o CNPJ " + CNPJ));
		/*
		 * exemplo do JSON de resposta esperado:
		 {
		    "data": null,
		    "errors": [
		        "Empresa não encontrada para o CNPJ 51463645000100"
		    ]
		}
		 */
	}

	@Test
	@WithMockUser
	public void testBuscarEmpresaCnpjValido() throws Exception {
		BDDMockito.given(this.empresaService.buscarPorCnpj(Mockito.anyString()))
				.willReturn(Optional.of(this.obterDadosEmpresa()));

		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_EMPRESA_CNPJ_URL + CNPJ)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.razaoSocial", equalTo(RAZAO_SOCIAL)))
				.andExpect(jsonPath("$.data.cnpj", equalTo(CNPJ)))
				.andExpect(jsonPath("$.errors").isEmpty());
		
		/*
		 * exemplo do JSON de resposta esperado:
		 {
		    "data": {
		        "id": 6,
		        "razaoSocial": "F5 Informática",
		        "cnpj": "05750300000101"
		    },
		    "errors": []
		}
		 */
	}

	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setId(ID);
		empresa.setRazaoSocial(RAZAO_SOCIAL);
		empresa.setCnpj(CNPJ);
		return empresa;
	}
}
