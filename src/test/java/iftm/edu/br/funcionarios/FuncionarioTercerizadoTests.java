package iftm.edu.br.funcionarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FuncionarioTercerizadoTests {
    private FuncionarioTercerizado funcionarioTerc;

    @BeforeEach
    public void inicializarObjetos() {
        funcionarioTerc = new FuncionarioTercerizado();
    }

    @Test
    @DisplayName("Teste do construtor com entradas válidas.")
    public void validarConstrutorComEntradasValidas() {
        String nomeValido = "Carlos Silva";
        int horasTrabalhadasValidas = 35;
        double valorHoraValido = 70.00;
        double despesasAdicionaisValidas = 500.00;
        
        funcionarioTerc = new FuncionarioTercerizado(nomeValido, horasTrabalhadasValidas, valorHoraValido, despesasAdicionaisValidas);

        assertEquals(nomeValido, funcionarioTerc.getNome());
        assertEquals(horasTrabalhadasValidas, funcionarioTerc.getHorasTrabalhadas());
        assertEquals(valorHoraValido, funcionarioTerc.getValorHora());
        assertEquals(despesasAdicionaisValidas, funcionarioTerc.getDespesasAdicionais());
    }

    @Test
    @DisplayName("Teste do construtor com despesas adicionais inválidas.")
    public void validarConstrutorComDespesasInvalidas() {
        String nome = "Carlos";
        int horasTrabalhadasValidas = 40;
        double valorHoraValido = 75.00;
        double despesasAdicionaisInvalidas = 1500.00;
        
        assertThrows(IllegalArgumentException.class, () -> {
            funcionarioTerc = new FuncionarioTercerizado(nome, horasTrabalhadasValidas, valorHoraValido, despesasAdicionaisInvalidas);
        }, "Despesas adicionais não devem exceder o limite permitido.");
    }

    @Test
    @DisplayName("Teste de modificação de despesas adicionais com valor inválido.")
    public void validarModificacaoDespesasComValorInvalido() {
        FuncionarioTercerizado funcionarioTercerizado = new FuncionarioTercerizado("Carlos", 35,  70.0, 200.0);
        double despesaAdicionalInvalida = 1200.00;

        assertThrows(IllegalArgumentException.class, () -> {
            funcionarioTercerizado.modificarDespesasAdicionais(despesaAdicionalInvalida);
        }, "Despesas adicionais modificadas não devem exceder o limite permitido.");
    }

    @Test
    @DisplayName("Teste de modificação de despesas adicionais com valor válido.")
    public void validarModificacaoDespesasComValorValido() {
        FuncionarioTercerizado funcionarioTercerizado = new FuncionarioTercerizado("Carlos", 35, 75.0, 400.0);
        double despesaAdicionalValida = 800.00;

        funcionarioTercerizado.modificarDespesasAdicionais(despesaAdicionalValida);

        assertEquals(despesaAdicionalValida, funcionarioTercerizado.getDespesasAdicionais());
    }

    @Test
    @DisplayName("Teste de pagamento terceirizado com valores válidos.")
    public void validarPagamentoTerceirizadoComValoresValidos() {
        int horasTrabalhadas = 20;
        double valorHora = 60;
        double despesaAdicional = 900.00;
        double pagamentoEsperado = 2100.00;

        FuncionarioTercerizado funcionarioTerceirizado = new FuncionarioTercerizado("Carlos", horasTrabalhadas, valorHora, despesaAdicional);
        double pagamentoObtido = funcionarioTerceirizado.calcularPagamentoTerceirizado();

        assertEquals(pagamentoEsperado, pagamentoObtido);
    }
}
