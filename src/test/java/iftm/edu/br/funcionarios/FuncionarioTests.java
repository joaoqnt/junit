package iftm.edu.br.funcionarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FuncionarioTests {
    private Funcionario funcionario;

    @BeforeEach
    public void inicializarObjetos() {
        funcionario = new Funcionario();
    }

    @Test
    @DisplayName("Valida horas trabalhadas dentro do limite permitido (não no limite máximo).")
    public void testarHorasTrabalhadasValidasForaDoLimite() {
        int horasTrabalhadasValidas = 30;
        Double valorHoraValido = 55.00;
        int horasTrabalhadasEsperadas = 30;

        funcionario = new Funcionario("Marcos Silva", horasTrabalhadasValidas, valorHoraValido);
        int horasTrabalhadasObtidas = funcionario.getHorasTrabalhadas();

        assertEquals(horasTrabalhadasEsperadas, horasTrabalhadasObtidas);
    }

    @Test
    @DisplayName("Valida horas trabalhadas exatamente no limite máximo permitido de 40 horas.")
    public void testarHorasTrabalhadasValidasNoLimite() {
        int horasTrabalhadasValidas = 40;
        Double valorHoraValido = 70.00;
        int horasTrabalhadasEsperadas = 40;

        funcionario = new Funcionario("Marcos Silva", horasTrabalhadasValidas, valorHoraValido);
        int horasTrabalhadasObtidas = funcionario.getHorasTrabalhadas();

        assertEquals(horasTrabalhadasEsperadas, horasTrabalhadasObtidas);
    }

    @Test
    @DisplayName("Valida que horas trabalhadas acima do limite de 40 horas lançam exceção.")
    public void testarHorasTrabalhadasInvalidasForaDoLimite() {
        int horasTrabalhadasInvalidas = 45;
        Double valorHoraValido = 70.00;

        assertThrows(IllegalArgumentException.class,
        () -> {
            funcionario = new Funcionario("Marcos Silva", horasTrabalhadasInvalidas, valorHoraValido);
        }, "Horas trabalhadas não podem exceder 40 horas por semana.");
    }

    @Test
    @DisplayName("Valida pagamento quando valor não está no limite inferior de R$ 1320,00.")
    public void testarPagamentoValidoForaLimite() {
        int horasTrabalhadasValidas = 30;
        Double valorHoraValido = 60.00;
        Double pagamentoEsperado = 1800.00;

        funcionario = new Funcionario("Marcos Silva", horasTrabalhadasValidas, valorHoraValido);
        Double pagamentoObtido = funcionario.calcularPagamento();

        assertEquals(pagamentoEsperado, pagamentoObtido);
    }

    @Test
    @DisplayName("Valida construção com valor de hora fora dos limites permitidos.")
    public void testarConstrutorEntradaValorHoraInvalida() {
        String nome = "Lucas";
        int horasTrabalhadasValidas = 40;
        double valorHoraInvalido = 300.00;

        assertThrows(IllegalArgumentException.class,
        () -> {
            funcionario = new Funcionario(nome, horasTrabalhadasValidas, valorHoraInvalido);
        }, "Valor da hora deve estar entre 4% e 10% do salário mínimo.");
    }

    @Test
    @DisplayName("Valida modificação do valor hora com entrada válida.")
    public void testarModificarValorHoraEntradaValida() {
        double valorHoraValida = 60.00;

        funcionario.setValorHora(valorHoraValida);

        assertEquals(valorHoraValida, funcionario.getValorHora());
    }

    @Test
    @DisplayName("Valida construção com pagamento abaixo do salário mínimo.")
    public void testarConstrutorPagamentoInvalido() {
        String nome = "João";
        int horasTrabalhadas = 10;
        double valorHora = 20.00;

        assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario(nome, horasTrabalhadas, valorHora);
        }, "Pagamento deve ser maior ou igual ao salário mínimo.");
    }

    @Test
    @DisplayName("Valida pagamento igual ao salário mínimo.")
    public void testarConstrutorPagamentoNoLimite() {
        int horasTrabalhadas = 20;
        double valorHora = 66.00;
        double pagamentoEsperado = 1320.00;

        funcionario = new Funcionario("Isabela", horasTrabalhadas, valorHora);
        double pagamentoObtido = funcionario.calcularPagamento();

        assertEquals(pagamentoEsperado, pagamentoObtido);
    }
}
