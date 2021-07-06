package Avaliacao.parte2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conta {

    private String nomeTitular;
    private int numero;
    private int agencia;
    private double saldo;
    private Date data_abertura;

    public Conta() {
        super();
    }

    public Conta(String nomeTitular, int numero, int agencia, double saldo, String data_abertura) {
        this.nomeTitular = nomeTitular;
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = saldo;
        this.data_abertura = converteData(data_abertura);
    }

    public Date converteData(String data) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada = null;
        try {
            dataFormatada = formatador.parse(data);
        } catch (ParseException e) {
            System.err.print("Erro ao formatar");
        }
        return dataFormatada;
    }

    public double sacar(double valor) {
        this.saldo -= valor;
        return valor;
    }

    public double depositar(double valor) {
        this.saldo += valor;
        return valor;
    }

    public double calculaRendimento(int meses) {
        double valorRendido = this.saldo;
        double percentual = 0;
        for (int cont = 0; cont < meses; cont++) {
            percentual = valorRendido * (0.5 / 100);
            valorRendido = valorRendido + percentual;
        }
        return valorRendido;
    }

    public String getNomeTitular() {
        return this.nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAgencia() {
        return this.agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getData_abertura() {
        return this.data_abertura;
    }

    public void setData_abertura(Date data_abertura) {
        this.data_abertura = data_abertura;
    }
}
