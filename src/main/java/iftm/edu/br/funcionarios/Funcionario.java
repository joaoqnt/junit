package iftm.edu.br.funcionarios;

public class Funcionario {
    private String nome;
    private int horasTrabalhadas;
    private Double valorHora;
    private static final double salarioMinino = 1320.00;

    public Funcionario() {
    }

    public Funcionario(String nome, int horasTrabalhadas, Double valorHora) {
        this.nome = nome;
        this.horasTrabalhadas = validaHorasTrabalhadas(horasTrabalhadas);
        this.valorHora = validaValorHorasTrabalhadas(valorHora);
        validaPagamento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = validaHorasTrabalhadas(horasTrabalhadas);
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = validaValorHorasTrabalhadas(valorHora);
    }

    public static double getSalariominino() {
        return salarioMinino;
    }

    private int validaHorasTrabalhadas(int horasTrabalhadas) {
        if (horasTrabalhadas > 40) {
            throw new IllegalArgumentException(
                    "Número de horas excede o limite permitido de 40 horas semanais.");
        } else {
            return horasTrabalhadas;
        }
    }

    private double validaValorHorasTrabalhadas(double valorHora) {
        double limiteInferior = 0.04;
        double limiteSuperior = 0.10;
        if ((valorHora < limiteInferior * salarioMinino) || (valorHora > limiteSuperior * salarioMinino)) {
            throw new IllegalArgumentException("O valor da hora deve estar entre 4% e 10% do salário mínimo atual.");
        } else {
            return valorHora;
        }
    }

    private void validaPagamento() {
        if (calcularPagamento() < salarioMinino) {
            throw new IllegalArgumentException(
                    "O pagamento total deve ser maior ou igual ao salário mínimo.");
        }
    }

    public Double calcularPagamento() {
        return horasTrabalhadas * valorHora;
    }
}
