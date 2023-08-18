package com.dieta_ja;

//import das bibliotecas necessarias
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Set;

import com.dieta_ja.dao.*;
import com.dieta_ja.modelos.*;

public class App 
{
    static Scanner leitor = new Scanner(System.in);
    static ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>(); //cria um array pra armazenar as pessoas
    static ArrayList<Alimento> listaAlimentos = new ArrayList<Alimento>(); //cria um array pra armazenar os alimentos
    static Pessoa pessoaLogada = null; // usuário atualmente logado
    public static void main( String[] args ){
        int selecao; //variavel para armazenar a selecao
        int selecao2;
        do {
            // Menu de acordo com o estado do login
            if (pessoaLogada == null) {
                mostraMenuNaoLogado();
            } else if (pessoaLogada instanceof Admin) {
                mostraMenuAdministrador();
            } else if (pessoaLogada instanceof UserComum) {
                mostraMenuUsuarioComum();
            }


            selecao = leitor.nextInt();
            leitor.nextLine();

            //ação de acordo com a seleção
            switch (selecao) {
                case 1:
                    System.out.println("╔═════════════════════════════════════╗");
                    System.out.println("║    Usuário Comum [1] ou Admin [2]?  ║");
                    System.out.println("╚═════════════════════════════════════╝");
                    selecao2 = leitor.nextInt();
                    leitor.nextLine();
                    if(selecao2 == 1){
                        cadastrarUserComum();
                    }else{
                        cadastrarAdmin();
                    }
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    editaPropriaPessoa();
                    break;
                case 4:
                    excluiPropriaPessoa();
                    break;
                case 5:
                    criarDieta();
                    break;
                case 6:
                    listarDietas();
                    break;
                case 7:
                    break;
                case 8:
                    editarDieta();
                    break;
                case 9:
                    excluirDieta();
                    break;
                case 10:
                    logout();
                    break;
                case 11:
                    listaPessoas();
                    break;
                case 12:
                    editaPessoa();
                    break;
                case 13:
                    excluiPessoa();
                    break;
                case 14:
                    cadastrarAlimento();
                    break;
                case 15:
                    listaAlimentos();
                    break;
                case 16:
                    editaAlimento();
                    break;
                case 17:
                    excluiAlimento();
                    break;
                case 0:
                    System.exit(0); // sai da aplicação
                    break;
                default:
                    System.out.println("Opção inválida.\n");
                    break;
            }
        } while (selecao != 0);
    }

    // ** MENUS **
    // Menu de seleções para usuário não logado
    private static void mostraMenuNaoLogado() {
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║          SISTEMA DE DIETAS          ║");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println("║        [1] Cadastro no sistema      ║");
        System.out.println("║        [2] Login                    ║");
        System.out.println("║        [0] Finalizar o programa     ║");
        System.out.println("╚═════════════════════════════════════╝");
    }
    // Menu de seleções para usuário comum
    private static void mostraMenuUsuarioComum() {
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║          SISTEMA DE DIETAS          ║");
        System.out.println("║              USUARIO                ║");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println("║        [3] Editar cadastro          ║");
        System.out.println("║        [4] Excluir cadastro         ║");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println("║        [5] Criar dieta              ║");
        System.out.println("║        [6] Listar dietas            ║");
        System.out.println("║        [8] Editar dieta             ║");
        System.out.println("║        [9] Excluir dieta            ║");
        System.out.println("║        [10] Logout                  ║");
        System.out.println("║        [0] Finalizar o programa     ║");
        System.out.println("╚═════════════════════════════════════╝");
    }
    // Menu de seleções para administrador
    private static void mostraMenuAdministrador() {
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║          SISTEMA DE DIETAS          ║");
        System.out.println("║            ADMINISTRADOR            ║");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println("║ [1] Cadastrar uma pessoa            ║");
        System.out.println("║ [11] Listar todas as pessoas        ║");
        System.out.println("║ [12] Editar uma pessoa              ║");
        System.out.println("║ [13] Deletar uma pessoa             ║");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println("║ [14] Cadastrar um alimento          ║");
        System.out.println("║ [15] Listar todos os alimentos      ║");
        System.out.println("║ [16] Editar um alimento             ║");
        System.out.println("║ [17] Deletar um alimento            ║");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println("║ [10] Logout                         ║");
        System.out.println("║ [0] Finalizar o programa            ║");
        System.out.println("╚═════════════════════════════════════╝");
    }

    // ** 1 - CADASTROS DE PESSOAS **
    //1-1 método para criar um usuário comum
    private static void cadastrarUserComum() {
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("--CADASTRO DE USUARIO COMUM--");
        System.out.println("Escreva o email:");
        String email = leitor.nextLine();
        System.out.println("══════════════════════════════════════════════════");
        if (userExiste(email)) {
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("Erro: O usuário com o email '" + email + "' já existe.");
            System.out.println("══════════════════════════════════════════════════");
            return; // Exit the method if the user already exists
        }
        System.out.println("══════════════════════════════════════════════════");
        try {
            System.out.println("Escreva o nome:");
            String nome = leitor.nextLine();

            System.out.println("Escreva a idade:");
            int idade = Integer.parseInt(leitor.nextLine());

            System.out.println("Escreva o sexo ('masculino' ou 'feminino'):");
            String sexo = leitor.nextLine();

            System.out.println("Escreva o peso:");
            double peso = Double.parseDouble(leitor.nextLine());

            System.out.println("Escreva a altura (em cm):");
            int altura = Integer.parseInt(leitor.nextLine());

            System.out.println("Escreva a senha:");
            String senhaDigitada = leitor.nextLine();
            String senhaCriptografada = BCrypt.hashpw(senhaDigitada, BCrypt.gensalt());

            UserComum userComum = new UserComum(nome, idade, sexo, peso, altura, email, senhaCriptografada);
            listaPessoas.add(userComum);
            System.out.println("Usuário comum criado: " + userComum.getNome() + "\n");

            PessoaDAO pDao = new PessoaDAO();
            pDao.salvarPessoa(userComum);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida para idade, peso ou altura.");
        }
        System.out.println("══════════════════════════════════════════════════");
    }
    //1-2 método para criar um admin
    private static void cadastrarAdmin() {
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("Escreva o email:");
        String email = leitor.nextLine();
        System.out.println("══════════════════════════════════════════════════");
        if (userExiste(email)) {
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("Erro: O usuário com o email '" + email + "' já existe.");
            System.out.println("══════════════════════════════════════════════════");
            return; // Exit the method if the user already exists
        }
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("--CADASTRO DE ADMIN--");
            System.out.println("Escreva o nome:");
            String nome = leitor.nextLine();
            System.out.println("Escreva a Senha:");
            String senhaDigitada = leitor.nextLine();
            String senhaCriptografada = BCrypt.hashpw(senhaDigitada, BCrypt.gensalt());
            Admin admin = new Admin(nome, email, senhaCriptografada);
            listaPessoas.add(admin);
            System.out.println("Admin criado: " + admin.getNome() + "\n");
            PessoaDAO pDao = new PessoaDAO();
            pDao.salvarPessoa(admin);                
    }

    private static boolean userExiste(String email) {
        PessoaDAO pDao = new PessoaDAO();
        return pDao.existePessoaComEmail(email);
    }
    
    // ** 2 - AUTENTICACAO **
    // Método para autenticar a pessoa (login)
    private static void login() {
        System.out.println("══════════════════════════════════════════════════");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o email: ");
        String email = scanner.nextLine();
        System.out.println("Digite a senha: ");
        String senha = scanner.nextLine();
        PessoaDAO pDao = new PessoaDAO();
        Pessoa pessoa = pDao.buscarPessoaPorEmail(email);
        // Verifica se a pessoa existe e se a senha está correta
        if (pessoa != null && BCrypt.checkpw(senha, pessoa.getSenha())) {
            pessoaLogada = pessoa;
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("Autenticacao bem-sucedida!");
            System.out.println("══════════════════════════════════════════════════");
        } else {
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("Email ou senha incorretos. Tente novamente.");
            System.out.println("══════════════════════════════════════════════════");
        }
    }
    // Método para logout
    private static void logout() {
        pessoaLogada = null;
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("Logout realizado com sucesso!");
        System.out.println("══════════════════════════════════════════════════");
    }

    // ** USUÁRIO COMUM **
    //3 - edição do proprio cadastro usuario
    private static void editaPropriaPessoa() {
        PessoaDAO pDao = new PessoaDAO();
        Long idProprio = pessoaLogada.getId();
        Pessoa pessoa = pDao.buscarPessoaPorId(idProprio);
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("Digite um novo nome: ");
        String novoNome = leitor.nextLine();
        System.out.println("Digite uma nova idade: ");
        Integer novaIdade = leitor.nextInt();
        leitor.nextLine();
        System.out.println("Digite um novo sexo: ");
        String novoSexo = leitor.nextLine();
        System.out.println("Escreva o novo peso:");
        Double novoPeso = leitor.nextDouble();
        System.out.println("Escreva a nova altura:");
        Integer novaAltura = leitor.nextInt();
        leitor.nextLine();
        System.out.println("Escreva o novo email:");
        String novoEmail = leitor.nextLine();
        System.out.println("Escreva a nova Senha:");
        String novaSenhaDigitada = leitor.nextLine();
        String novaSenhaCriptografada = BCrypt.hashpw(novaSenhaDigitada, BCrypt.gensalt());
        // Atualiza os dados da pessoa
        pessoa.setNome(novoNome);
        pessoa.setIdade(novaIdade);
        pessoa.setSexo(novoSexo);
        pessoa.setPeso(novoPeso);
        pessoa.setAltura(novaAltura);
        pessoa.setEmail(novoEmail);
        pessoa.setSenha(novaSenhaCriptografada);
        // Salva alterações no banco
        pDao.atualizarPessoa(pessoa);
        pessoa.calcularTaxaMetabolismoBasal();
        System.out.println("Cadastro atualizado!");
        System.out.println("══════════════════════════════════════════════════");
    }
    //4 - método para excluir o proprio cadastro
    private static void excluiPropriaPessoa(){
        int selecao;
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║    Tem certeza que deseja excluir?  ║");
        System.out.println("║          Sim[1]    Nao[2]?          ║");
        System.out.println("╚═════════════════════════════════════╝");
        selecao = leitor.nextInt();
        leitor.nextLine();
        if(selecao == 1){
            PessoaDAO pDao = new PessoaDAO();
            Long idProprio = pessoaLogada.getId();
            // Busca a pessoa por id e a exclui do banco
            Pessoa pessoaRemover = pDao.buscarPessoaPorId(idProprio);
            pDao.excluirPessoa(pessoaRemover);
            pessoaLogada = null;
            System.out.println("╔═════════════════════════════════════╗");
            System.out.println("║          Cadastro Excluido!         ║");
            System.out.println("╚═════════════════════════════════════╝");
        }else{
           return;
        }
    }
    
    // *** DIETAS ***
    //5 - Método para criar uma dieta
    private static void criarDieta() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("Escreva o nome da dieta:");
        String nome = leitor.nextLine();
        Dieta dieta = new Dieta(nome);

        // Obter todos os alimentos disponíveis
        AlimentoDAO alimentoDAO = new AlimentoDAO();
        List<Alimento> alimentos = alimentoDAO.buscarTodosAlimentos();
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("Selecione os alimentos a serem adicionados à dieta (digite o número correspondente):");

        // Mostrar lista de alimentos com um índice
        for (int i = 0; i < alimentos.size(); i++) {
            Alimento alimento = alimentos.get(i);
            System.out.println(alimento.getId() + ". " + alimento.getNome());
        }
        System.out.println("══════════════════════════════════════════════════");
        // Obter a seleção dos alimentos pelo usuário
        String opcao;
        do {
            System.out.println("Digite o ID do alimento a ser adicionado (ou 's' para sair):");
            opcao = leitor.nextLine();

            if (opcao.equalsIgnoreCase("s")) {
                break;
            }

            if (opcao.matches("\\d+")) {
                Long idAlimento = Long.parseLong(opcao);
                Alimento alimentoSelecionado = alimentoDAO.buscarAlimentoPorId(idAlimento);
                if (alimentoSelecionado != null) {
                    dieta.adicionarAlimento(alimentoSelecionado);
                    System.out.println("Alimento adicionado à dieta: " + alimentoSelecionado.getNome());
                } else {
                    System.out.println("Alimento não encontrado! Digite um ID válido.");
                }
            } else {
                System.out.println("Opção inválida! Digite um ID válido ou 's' para sair.");
            }
        } while (!opcao.equalsIgnoreCase("s"));
        System.out.println("══════════════════════════════════════════════════");
        // Adiciona a dieta à lista de dietas da pessoa
        pessoaLogada.addDieta(dieta);

        // Salva a dieta no banco de dados
        DietaDAO dietaDAO = new DietaDAO();
        dietaDAO.salvarDieta(dieta);

        System.out.println("Dieta criada: " + dieta.getNome() + "\n");
        System.out.println("══════════════════════════════════════════════════");
    }
    //6 - Método para listar as dietas de uma pessoa
    private static void listarDietas() {
        // Busca as dietas da pessoa logada
        DietaDAO dDao = new DietaDAO();
        List<Dieta> dietas = dDao.buscarDietaPorPessoa(pessoaLogada);

        System.out.println("\n══════════════════════════════════════════════════");

        // Verifica se há dietas para exibir
        if (dietas.isEmpty()) {
            System.out.println("Não há dietas registradas.");
        } else {
            System.out.println("Dietas da pessoa logada:");

            // Set to keep track of printed dieta IDs
            Set<Long> printedDietaIds = new HashSet<>();

            for (Dieta dieta : dietas) {
                // Check if dieta ID has already been printed
                if (printedDietaIds.contains(dieta.getId())) {
                    continue;
                }

                printedDietaIds.add(dieta.getId());

                System.out.println("══════════════════════════════════════════════════");
                System.out.println(dieta);
            }
        }

        System.out.println("══════════════════════════════════════════════════");
    }
    //8 - Método para editar uma dieta pelo id
    private static void editarDieta() {
        // Verifica se há uma pessoa logada
        if (pessoaLogada == null) {
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("Nenhum usuário logado. Faça login para editar suas dietas.");
            System.out.println("══════════════════════════════════════════════════");
            return;
        }

        // Busca as dietas da pessoa logada
        DietaDAO dDao = new DietaDAO();
        List<Dieta> dietas = dDao.buscarDietaPorPessoa(pessoaLogada);

        // Imprime as dietas
        for (Dieta dieta : dietas) {
            System.out.println("══════════════════════════════════════════════════");
            System.out.println(dieta);
            System.out.println("══════════════════════════════════════════════════");
        }

        // Lê o id a ser buscado
        System.out.println("Digite o id da dieta: ");
        Long idBuscado = Long.parseLong(leitor.nextLine());
        System.out.println("══════════════════════════════════════════════════");
        // Percorre a lista até encontrar o id
        for (Dieta dieta : dietas) {
            if (idBuscado.equals(dieta.getId())) {
                System.out.println("══════════════════════════════════════════════════");
                System.out.println("Dieta encontrada!");
                System.out.println("══════════════════════════════════════════════════");
                System.out.println("Digite um novo nome: ");
                String novoNome = leitor.nextLine();
                System.out.println("══════════════════════════════════════════════════");
                // Atualiza o nome da dieta
                dieta.setNome(novoNome);
                // Salva as alterações no banco
                dDao.atualizarDieta(dieta);
            }
        }
    }
    //9 - Método para excluir uma dieta pelo id
    private static void excluirDieta() {
        // Verifica se há uma pessoa logada
        if (pessoaLogada == null) {
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("Nenhum usuário logado. Faça login para excluir suas dietas.");
            System.out.println("══════════════════════════════════════════════════");
            return;
        }

        // Busca as dietas da pessoa logada
        DietaDAO dDao = new DietaDAO();
        List<Dieta> dietas = dDao.buscarDietaPorPessoa(pessoaLogada);
        // Imprime as dietas
        for (Dieta dieta : dietas) {
            System.out.println("══════════════════════════════════════════════════");
            System.out.println(dieta);
            System.out.println("══════════════════════════════════════════════════");
        }

        // Seleciona o id da dieta a ser removida
        System.out.println("Selecione um id para remover");
        Long idSelecionado = Long.parseLong(leitor.nextLine());
        System.out.println("══════════════════════════════════════════════════");
        // Busca a dieta por id e a exclui do banco
        Dieta dietaRemover = dDao.buscarDietaPorId(idSelecionado);
        if (dietaRemover != null) {
            dDao.excluirDieta(dietaRemover);
        } else {
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("Dieta não encontrada.");
            System.out.println("══════════════════════════════════════════════════");
        }
    }


    // ** ADMIN **
    //11 - método para listar as pessoas
    private static void listaPessoas() {
        PessoaDAO pDao = new PessoaDAO();
        ArrayList<Pessoa> pessoas = (ArrayList<Pessoa>) pDao.buscarTodasPessoas();
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("\n--LISTA DE PESSOAS--");
        for(Pessoa pessoa : pessoas){
            System.out.println(pessoa);
        }
        System.out.println("══════════════════════════════════════════════════");
    }
    //12 - método para editar uma pessoa pelo id
    private static void editaPessoa(){
        PessoaDAO pDao = new PessoaDAO();
        // Busca todas as pessoas do banco
        ArrayList<Pessoa> pessoas = (ArrayList<Pessoa>) pDao.buscarTodasPessoas();
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("\n--EDIÇÃO DE PESSOA POR ID--");
        System.out.println("--LISTA DE PESSOAS--");
        // Imprime cada pessoa (O toString deve imprimir o ID neste caso)
        for(Pessoa pessoa : pessoas){          
            System.out.println(pessoa);
        }
        System.out.println("══════════════════════════════════════════════════");
        // Lê o id a ser buscado
        System.out.println("Digite o id da pessoa: ");
        String idBuscado = leitor.nextLine();
        // Percorre a lista até encontrar o nome (ou cada nome se houver repetidos)
        for(Pessoa p : pessoas){
            if(idBuscado.equals(String.valueOf(p.getId()))){
                System.out.println("Pessoa encontrada!");
                if(p instanceof UserComum){
                    System.out.println("Digite um novo nome: ");
                    String novoNome = leitor.nextLine();
                    System.out.println("Digite uma nova idade: ");
                    Integer novaIdade = leitor.nextInt();
                    System.out.println("Escreva o novo peso: ");
                    Double novoPeso = leitor.nextDouble();
                    System.out.println("Escreva a nova altura: ");
                    Integer novaAltura = leitor.nextInt();
                    leitor.nextLine();
                    System.out.println("Escreva o novo email: ");
                    String novoEmail = leitor.nextLine();
                    System.out.println("Escreva a nova senha: ");
                    String novaSenhaDigitada = leitor.nextLine();
                    String novaSenhaCriptografada = BCrypt.hashpw(novaSenhaDigitada, BCrypt.gensalt());
                    UserComum userComum = (UserComum) p;
                    userComum.setNome(novoNome);
                    userComum.setIdade(novaIdade);
                    userComum.setPeso(novoPeso);
                    userComum.setAltura(novaAltura);
                    userComum.setEmail(novoEmail);
                    userComum.setSenha(novaSenhaCriptografada);
                    pDao.atualizarPessoa(userComum);
                }else if (p instanceof Admin) {
                    System.out.println("Digite um novo nome: ");
                    String novoNome = leitor.nextLine();
                    System.out.println("Escreva o novo email: ");
                    String novoEmail = leitor.nextLine();
                    System.out.println("Escreva a nova senha: ");
                    String novaSenhaDigitada = leitor.nextLine();
                    String novaSenhaCriptografada = BCrypt.hashpw(novaSenhaDigitada, BCrypt.gensalt());
                    Admin admin = (Admin) p;
                    admin.setNome(novoNome);
                    admin.setEmail(novoEmail);
                    admin.setSenha(novaSenhaCriptografada);
                    pDao.atualizarPessoa(admin);
                }
            }
        }
    }
    //13 - método para excluir uma pessoa pelo id
    private static void excluiPessoa(){
        PessoaDAO pDao = new PessoaDAO();
        // Carrega a lista de pessoas do banco
        ArrayList<Pessoa> pessoas = (ArrayList<Pessoa>) pDao.buscarTodasPessoas();
        // Imprime cada pessoa (O toString deve imprimir o ID neste caso)
        for(Pessoa pessoa : pessoas){    
            System.out.println("══════════════════════════════════════════════════");      
            System.out.println(pessoa);
            System.out.println("══════════════════════════════════════════════════");
        }
        // Seleciona o id da pessoa a ser removida
        System.out.println("Selecione um id para remover");
        System.out.println("══════════════════════════════════════════════════");
        Long idSelecionado = Long.parseLong(leitor.nextLine());
        // Busca a pessoa por id e a exclui do banco
        Pessoa pessoaRemover = pDao.buscarPessoaPorId(idSelecionado);
        pDao.excluirPessoa(pessoaRemover);
    }

    // *** ALIMENTOS ***
    //14 - método para criar um alimento
    private static void cadastrarAlimento() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("Escreva o nome do alimento:");
        String nome = leitor.nextLine();
        System.out.println("Escreva a quantidade de calorias p/ 100g:");
        Double caloriasPor100g = leitor.nextDouble();
        System.out.println("Escreva a quantidade de carboidratos p/ 100g:");
        Double carboidratosPor100g = leitor.nextDouble();
        System.out.println("Escreva a quantidade de protéinas p/ 100g:");
        Double proteinasPor100g = leitor.nextDouble();
        System.out.println("Escreva a quantidade de gorduras p/ 100g:");
        Double gordurasPor100g = leitor.nextDouble();
        System.out.println("Escreva o valor do alimento p/ 100g:");
        Double valorAlimento = leitor.nextDouble();
        Alimento alimento = new Alimento(nome, caloriasPor100g, carboidratosPor100g, proteinasPor100g, gordurasPor100g, valorAlimento);
        listaAlimentos.add(alimento);
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("Alimento criado: " + alimento.getNome() + "\n");
        System.out.println("══════════════════════════════════════════════════");
        AlimentoDAO aDao = new AlimentoDAO();
        aDao.salvarAlimento(alimento);
    }
    //15 - método para listar os alimentos
    private static void listaAlimentos() {
        AlimentoDAO aDao = new AlimentoDAO();
        ArrayList<Alimento> alimentos = (ArrayList<Alimento>) aDao.buscarTodosAlimentos();
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("--LISTA DE ALIMENTOS--\n");
        for(Alimento alimento : alimentos){
            System.out.println("══════════════════════════════════════════════════");
            System.out.println(alimento);
            System.out.println("══════════════════════════════════════════════════");
        }
    }
    //16 - método para editar um alimento pelo id
    private static void editaAlimento(){
        AlimentoDAO aDao = new AlimentoDAO();

        // Busca todas os alimentos do banco
        ArrayList<Alimento> alimentos = (ArrayList<Alimento>) aDao.buscarTodosAlimentos();
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("--LISTA DE ALIMENTOS--\n");
        // Imprime cada alimento (O toString deve imprimir o ID neste caso)
        for(Alimento alimento : alimentos){          
            System.out.println(alimento);
        }
        System.out.println("══════════════════════════════════════════════════");
        // Lê o id a ser buscado
        System.out.println("Digite o id do alimento: ");
        String idBuscado = leitor.nextLine();

        // Percorre a lista até encontrar o id
        for(Alimento a : alimentos){
            if(idBuscado.equals(String.valueOf(a.getId()))){
                System.out.println("Alimento encontrada!");
                System.out.println("Digite um novo nome: ");
                String novoNome = leitor.nextLine();
                System.out.println("Digite uma qtd de calorias p/ 100g: ");
                Double novoCaloriasPor100g = leitor.nextDouble();
                System.out.println("Digite uma qtd de carboidratos p/ 100g: ");
                Double novoCarboidratosPor100g = leitor.nextDouble();
                System.out.println("Digite uma qtd de proteinas p/ 100g: ");
                Double novoProteinasPor100g = leitor.nextDouble();
                System.out.println("Digite uma qtd de gorduras p/ 100g: ");
                Double novoGordurasPor100g = leitor.nextDouble();
                a.setNome(novoNome);
                a.setCalorias(novoCaloriasPor100g);
                a.setCarboidratos(novoCarboidratosPor100g);
                a.setProteinas(novoProteinasPor100g);
                a.setGorduras(novoGordurasPor100g);
                // Salva alterações no banco
                aDao.atualizarAlimento(a);
            }
        }
    } 
    //17 - método para excluir um alimento pelo id
    private static void excluiAlimento(){
        AlimentoDAO aDao = new AlimentoDAO();
        // Carrega a lista de pessoas do banco
        ArrayList<Alimento> alimentos = (ArrayList<Alimento>) aDao.buscarTodosAlimentos();

        // Imprime cada alimento (O toString deve imprimir o ID neste caso)
        for(Alimento alimento : alimentos){  
            System.out.println("══════════════════════════════════════════════════");        
            System.out.println(alimento);
            System.out.println("══════════════════════════════════════════════════");
        }

        // Seleciona o id da alimento a ser removida
        System.out.println("Selecione um id para remover");
        Long idSelecionado = Long.parseLong(leitor.nextLine());
        System.out.println("══════════════════════════════════════════════════");
        // Busca a alimento por id e a exclui do banco
        Alimento alimentoRemover = aDao.buscarAlimentoPorId(idSelecionado);
        aDao.excluirAlimento(alimentoRemover);
    }

}