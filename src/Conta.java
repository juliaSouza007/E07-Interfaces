import java.util.Arrays;

public abstract class Conta {
    private Cliente donoConta;
    private Operacao[] operacoes = new Operacao[1000];

    protected double saldo = 15000;

    public static int totalContas = 0;

    protected int contador = 0;

    protected double limite;

    protected int num;

    public Conta (Cliente donoConta, double saldo, int num) {
        this.num = num;
        this.saldo = saldo;
        this.donoConta = donoConta;
        totalContas++;
    }

    boolean depositar (double valor) {
        if (valor > 0.0) {
            this.saldo += valor;
            OperacaoDeposito Dep = new OperacaoDeposito(valor);
            operacoes[contador] = Dep;
            contador++;
            return true;
        } else {
            return false;
        }
    }

    boolean sacar (double valor) {
        if (valor <= saldo) {
            this.saldo -= valor;
            OperacaoSaque Saq = new OperacaoSaque(valor);
            operacoes[contador] = Saq;
            contador++;
            return true;
        } else {
            return false;
        }
    }

    public void extrato(){
        for(int i = 0; i < contador; i++){
            operacoes[i].imprimirExtrato();
        }
    }

    public String toString() {
        String str = "<< Conta " + donoConta.getNome() + " >>\n" +
                this.donoConta.toString() + "\n" +
                "Saldo : " + this.saldo + "\n" +
                "Limite: " + this.limite + "\n" +
                "\n";
        return str;
    }

    public boolean equals(Object obj) {
        if(obj instanceof Conta) {
            Conta objConta = (Conta) obj;

            if(this.num == objConta.num) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void extratoTaxas(){
        float taxa = 0;
        System.out.println("<< EXTRATO DE TAXAS >>");
        System.out.println("Taxa de Manunteção: " + donoConta.calculaTaxas());
        for(int i = 0; i < operacoes.length; i++){
            if(operacoes[i] != null && operacoes[i].getTipo() == 's'){
                System.out.println("Saque: " + operacoes[i].calculaTaxas());
                taxa += operacoes[i].calculaTaxas();
            }
        }
        System.out.println();
        System.out.println("Total: " + taxa);
    }

    //getters
    public double getSaldo() {
        return saldo;
    }

    public Cliente getDonoConta(){
        return this.donoConta;
    }

    public Operacao[] getOperacoes(){
        return this.operacoes;
    }

    //setters
    public void setDonoConta(Cliente donoConta) {
        this.donoConta = donoConta;
    }

    public abstract boolean setLimite(double limite);
}