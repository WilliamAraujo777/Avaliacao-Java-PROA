package Prova.parte2;

import java.text.DecimalFormat; //DecimalFormat para formatar alguns valores que tinham muitos numeros após a virgula
import java.text.SimpleDateFormat;//SimpleDateFormat para formartar datas
import javax.swing.JOptionPane;//JOptionPane para criar mensagens na tela, para inserção de valores, e informar o usuario

public class ProgramaConta {

    public static void main(String[] args) {
    
        SimpleDateFormat formatadorData  = new SimpleDateFormat("dd/MM/yyyy");//criando mascara de formatação da data
        DecimalFormat    formatadorValor = new DecimalFormat("0.00");//criando mascara de formatação dos valores

        int opcao = 0;//criando variaveis para agencia, numeroConta e opção
        String numeroConta, agencia = ""; 
        boolean continua = true;            //variavel do tipo boolean para o While
        int tentativa = 3;                  //variavel para tentativas

        //instanciando objeto de Conta, e passando valores(Atenção na Agencia e no numero da conta não passe valores com "0" na frente
        //não sei bem o porque, mas em pesquisas vi que o java trata valores octais, e eles são referenciados em 08, 09 não entendi muito bem)
        Conta conta = new Conta("William Araujo", 777, 1234, 700.0, "17/07/2015");

        msgDefault("Boas vindas ao sistema do banco Will SA.!", "OLÁ!");

        while (continua) { // Laço de repetição responsável por verificar se a agência está correta

            try { // Esse try está ai para capturar caso o usuario digite uma letra ao invéz de um numero
 
                agencia = msgInput("Digite sua agencia: ", "AGÊNCIA");
                
                //esse primeiro if apenas verifica se você selecionou a opção de "cancel" ou o X da mensagem, e para evitar um erro
                //ele a fecha
                if(agencia == null){
                    System.exit(0);

                //ja esse segundo if esta comparando a agencia com a variavel string "agencia"
                //para isso acontecer estou passando ela para inteiro
                }else if (conta.getAgencia() == Integer.parseInt(agencia)) {
                    msgDefault("Agência encontrada!\nProssiga apertando em OK", "AGÊNCIA");
                    continua = false;
                }else{
                    tentativa -= 1;
                    if (tentativa < 1) {
                        msgErro("Conta bloqueada!\nNão há mais tentativas restantes, contate o ADM!", "ERRO");
                        System.exit(0);
                    } else {
                        msgErro("Agencia incorreta! Digite novamente! " + "\nPS: Você tem " + tentativa
                                + " tentativa(s) restante(s)!", "ERRO");
                    }
                }
             } catch (NumberFormatException e) {                 // Este catch lança um Exception caso o usuario tenha coloca uma letra ao invéz
                 msgErro("Por favor, apenas numeros!", "ERRO");  // de um numero,
             }                                                 // gerando uma mensagem e retornando para a opção de digitar a agência
        }
        continua  = true;
        tentativa = 3;

        while (continua) {// Laço de repetição responsável por verificar se a conta está correta
            try {// Esse try está ai para capturar caso o usuario digite uma letra ao invéz de um numero
                numeroConta = msgInput("Digite sua conta: ", "CONTA");
                
                //esse primeiro if apenas verifica se você selecionou a opção de "cancel" ou o X da mensagem, e para evitar um erro
                //ele a fecha 
                if(numeroConta == null){
                    System.exit(0);
                
                //ja esse segundo if esta comparando o numero da conta com a variavel string "numeroConta"
                //para isso acontecer estou passando ela para inteiro
                }else if (conta.getNumero() == Integer.parseInt(numeroConta)) {
                    msgDefault("Numero da conta encontrado! Prossiga para os nossos serviços", "CONTA");
                    continua = false;
                } else {
                    tentativa -= 1;
                    if (tentativa < 1) {
                        msgErro("Conta bloqueada!\nNão há mais tentativas restantes, contate o ADM!", "ERRO");
                        System.exit(0);
                    } else {
                        msgErro("Numero da conta incorreto! Digite novamente! " + "\nPS: Você tem " + tentativa
                                + " tentativa(s) restante(s)!", "ERRO");
                    }
                }
            } catch (NumberFormatException e) {                 // Este catch lança um Exception caso o usuario tenha coloca uma letra ao invéz
                msgErro("Por favor, apenas numeros!", "ERRO");  // de um numero,
            }                                                   // gerando uma mensagem e retornando para a opção de digitar a conta
        }
        continua = true;
       
        while (continua) {//Laço de repetição responsável pelo menu do programa, onde ele testa todas as opções e também finaliza o programa

            try { // Esse try está ai para capturar caso o usuario digite uma letra ao invéz de um numero
                
            // a variavel "opcao" está sendo responsável por capturar a opção escolhida pelo
            // usuario
            opcao = Integer.parseInt(msgInput("Selecione a opção desejada: " + "\n 1 = Dados da conta" + "\n 2 = Depositar"
                    + "\n 3 = Sacar" + "\n 4 = Verificar rendimento da conta " + "\n 5 = Finalizar sistema ",
                    "MENU PRINCIPAL"));

            

                // aqui está sendo aberto um switch da opção escolhida pelo usuario
                // anteriormente, cada opção equivale a uma funcionalidade
                switch (opcao) {

                    // primeira opção reponsável apenas por apresentar os dados da conta (Titular,
                    // Saldo e data de abertura)
                    case 1:
                        msgDefault(
                                "Nome do titular: " + conta.getNomeTitular() + "\nSaldo: R$ " + formatadorValor.format(conta.getSaldo())
                                        + "\nData de abertura: " + formatadorData.format(conta.getData_abertura()),
                                "DADOS DA CONTA");
                        break;

                    //segunda opção responsável por realizar um depósito na conta do usuario, a mesma verifica se ele é maior que 0 para continuar
                    case 2:
                        double deposito = Double.parseDouble(msgInput("Saldo atual: R$ " + conta.getSaldo()
                                + "\nInsira a quantia que deseja depositar na conta:", "DEPOSITO"));
                        if (deposito > 0) {
                            conta.setSaldo(conta.getSaldo() + deposito);
                            msgDefault("O deposito no valor de: R$ " + deposito + " foi realizado com sucesso!"
                                    + "\nNovo saldo é de R$ " + formatadorValor.format(conta.getSaldo()), "DEPOSITO");
                        } else {
                            msgErro("Erro! Por favor, insira um valor válido!", "ERRO");
                        }
                        break;
                    
                    //terceira opção responsável por realizar um saque na conta do usuario, a mesma verifica se ele é maior que 0 para continuar
                    //também verifica se é menor que o saldo atual
                    case 3:
                        double saque = Double.parseDouble(msgInput(
                                "Saldo atual: R$ " + conta.getSaldo() + "\nInsira a quantia que deseja sacar da conta:",
                                "SAQUE"));

                        if (saque > 0 && saque <= conta.getSaldo()) {
                            conta.setSaldo(conta.getSaldo() - saque);
                            msgDefault("O saque no valor de: R$ " + saque + " foi realizado com sucesso!"
                                    + "\nNovo saldo é de R$ " + formatadorValor.format(conta.getSaldo()), "SAQUE");
                        } else if (conta.getSaldo() == 0) {
                            msgErro("Erro! Sua conta esta zerada, impossivel realizar o saque!", "ERRO");
                        } else if (saque > conta.getSaldo()) {
                            msgErro("Erro! você não possui este valor em conta, impossivel realizar o saque!", "ERRO");
                        } else {
                            msgErro("Erro! Por favor, insira um valor válido!", "ERRO");
                        }
                        break;

                    //quarta opção é reponsável por realizar um teste de rendimento na conta, o usuario insere uma quantidade de meses que deseja
                    //deixar o dinheiro rendendo, e o programa retorna a quantia que o saldo teria depois daquele período
                    case 4:
                        int meses = Integer.parseInt(msgInput("O seu dinheiro está rendendo 0.5% ao mês no nosso banco!"
                                + "\n Deseja realizar um teste e verificar quanto ele irá render em X meses?"
                                + "\n Insira a quantidade de meses que deseja deixar rendendo: ", "RENDIMENTO"));

                        double valorRendido = conta.calculaRendimento(meses);
                        double acrescimo = valorRendido - conta.getSaldo();

                        msgDefault("Sua conta em " + meses + " meses vai render R$ " + formatadorValor.format(acrescimo)
                                + " reais" + "\nFicaria um total de R$ " + formatadorValor.format(valorRendido)
                                + " reais", "RENDIMENTO");

                        break;
                    
                    //quinta opção, responsável por finalizar o sistema, ela pergunta se o usuario realmente deseja fazer tal ato
                    case 5:
                        int i = msgConfirmarSaida();
                        if (i == JOptionPane.YES_OPTION) {
                            msgDefault("Obrigado por utilizar nossos serviços!" + "\nO sistema será finalizado."
                                    + "\nAté mais " + conta.getNomeTitular() + "!", "SAIR");
                            System.exit(0);
                        } else {
                            break;
                        }
                    
                    //essa opção é para caso o usuario selecione uma opção que não condiz com as do menu, o que gera um erro e retorna ao menu
                    default:
                        msgErro("Opção inválida! Por favor, selecione uma opção entre 1 e 5!", "ERRO");
                }
            } catch (NumberFormatException e) {                     // Este catch lança um Exception caso o usuario tenha coloca uma letra ao invés
                msgErro("Por favor, apenas numeros!", "ERRO");      // de um numero,
            }                                                       // gerando uma mensagem e retornando para a opção de escolher uma opção
        }
    }

    // Funções que criei para exibir as mensagem do JOptionPane, para evitar digitar
    // o JOption o tempo todo! O JOption é um componente do java swing, utilizado em interfaces graficas
    public static void msgDefault(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static String msgInput(String msg, String titulo) {
        return JOptionPane.showInputDialog(null, msg, titulo, JOptionPane.QUESTION_MESSAGE);
    }

    public static int msgConfirmarSaida() {
        return JOptionPane.showConfirmDialog(null, "Você realmente deseja finalizar o sistema?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
    }

    public static void msgErro(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
    }
}
