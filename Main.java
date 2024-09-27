import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grafo rede = new Grafo();
        Scanner sc = new Scanner(System.in);

        Repository repository = new Repository(); 
        
        for (int i = 0; i < repository.getUsuarios().size(); i++) {
            rede.adicionarVertice(repository.getUsuarios().get(i).getNome());
        }

        boolean usuarioLogado = false;
        String usuarioNome = "";

        

        while (true) {
            if (!usuarioLogado) {
                System.out.println("==== Bem-vindo à Workhang, sua rede conexão com outros atletas! ====");
                System.out.println("Digite uma das opções a seguir para começar\n" +
                        "1 - Logar\n" +
                        "2 - Cadastrar\n" +
                        "0 - Sair\n" +
                        "Digite: ");

                int option1 = sc.nextInt();
                sc.nextLine();

                if (option1 == 1) {
                    System.out.println("Digite seu nome: ");

                    String name = sc.nextLine();
                    if (rede.getVertice(name) != null) {
                        usuarioLogado = true;
                        usuarioNome = name;

                        System.out.println(
                                "==== Bem-vindo de volta, " + usuarioNome + ". Escolha sua próxima ação. ====");

                    } else {
                        System.out.println("Desculpe, não ninguém aqui com esse nome. Cadastre-se agora!\n");
                    }
                }
                if (option1 == 2) {
                    System.out.println("Digite seu nome: ");

                    String name = sc.nextLine();

                    System.out.println("Que bom ter você aqui, " + name + ".\n");
                    if (rede.getVertice(name) == null) {
                        rede.adicionarVertice(name);
                        usuarioLogado = true;
                        usuarioNome = name;
                        System.out.println("Cadastro realizado com sucesso! Bem-vindo, " + name);
                        System.out.println("==== Escolha sua próxima ação. ====");
                    }
                }
                if (option1 == 0) {
                    System.out.println("Volte logo!");
                    break;
                }
            } else {
                System.out.println("1 - Ver seus amigos\n" +
                        "2 - Ver seus esportes\n" +
                        "3 - Adicionar um novo esporte de interesse\n" +
                        "4 - Ver sugestões de amizades\n" +
                        "5 - Desfazer amizade\n" +
                        "0 - Sair\n");
                if (usuarioNome.equals("Admin")) {
                    System.out.println("99 - Para ver a rede inteira\n");
                }

                int option2 = sc.nextInt();
                sc.nextLine();

                if (option2 == 1) {
                    System.out.println("==== Seus amigos ====");
                    rede.getArestasLigadasAoVertice(usuarioNome);
                }
                if (option2 == 2) {
                    System.out.println("==== Seus esportes ====");
                    rede.listarEsportes(usuarioNome);
                }
                if (option2 == 3) {
                    System.out.println("Digite o esporte: ");
                    String novoEsporte = sc.nextLine();

                    rede.adicionarEsporte(usuarioNome, novoEsporte);
                    System.out.println("Esporte adicionado! Confira sua lista: ");
                    rede.listarEsportes(usuarioNome);
                }
                if (option2 == 4) {
                    int encontrado = rede.sugerirConexoes(usuarioNome);
                    if (encontrado == 1) {
                        System.out.println("Qual usuário você gostaria de fazer amizade? Digite: ");
                        String usuarioAConectar = sc.nextLine();
                        rede.adicionarAresta(usuarioNome, usuarioAConectar);
                        System.out.println("Você e "+usuarioAConectar+" agora são amigos\n");
                    }
                   
                }
                if (option2 == 5) {
                    System.out.println("Qual usuário você gostaria de desfazer conexão? Digite: ");
                    String usuarioADesfazer = sc.nextLine();
                    rede.desfazerConexao(usuarioNome, usuarioADesfazer);
                }
                if (option2 == 99) {
                    rede.visualizarGrafo();
                }
                if (option2 == 0) {
                    usuarioLogado = false;
                    usuarioNome = " ";
                }

            }
        }

    }
}
