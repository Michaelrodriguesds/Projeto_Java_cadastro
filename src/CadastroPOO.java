import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class CadastroPOO {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PessoaFisicaRepo pessoaFisicaRepo = new PessoaFisicaRepo();
            PessoaJuridicaRepo pessoaJuridicaRepo = new PessoaJuridicaRepo();

            int opcao;

            do {
                System.out.println("Selecione uma opção:");
                System.out.println("1. Incluir");
                System.out.println("2. Alterar");
                System.out.println("3. Excluir");
                System.out.println("4. Exibir pelo ID");
                System.out.println("5. Exibir todos");
                System.out.println("6. Salvar dados");
                System.out.println("7. Recuperar dados");
                System.out.println("0. Finalizar");

                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        cadastrarPessoa(scanner, pessoaFisicaRepo, pessoaJuridicaRepo);
                        break;
                    case 2:
                        // Lógica para alterar pessoa
                        System.out.println("Digite o ID da pessoa que deseja alterar:");
                        int idAlterar = scanner.nextInt();
                        scanner.nextLine();  // Consumir a quebra de linha

                        // Verificar se a pessoa existe antes de tentar alterar
                        if (pessoaFisicaRepo.existePessoa(idAlterar)) {
                            System.out.println("Digite o novo nome:");
                            String novoNome = scanner.nextLine();
                            // Implemente a lógica de alteração no repositório
                            pessoaFisicaRepo.alterarNome(idAlterar, novoNome);
                            System.out.println("Pessoa alterada com sucesso.");
                        } else {
                            System.out.println("Pessoa não encontrada.");
                        }
                        break;
                    case 3:
                        // Lógica para excluir pessoa
                        System.out.println("Digite o ID da pessoa que deseja excluir:");
                        int idExcluir = scanner.nextInt();
                        // Implemente a lógica de exclusão no repositório
                        pessoaFisicaRepo.excluir(idExcluir);
                        System.out.println("Pessoa excluída com sucesso.");
                        break;
                    case 4:
                        // Lógica para exibir pelo ID
                        System.out.println("Digite o ID da pessoa que deseja exibir:");
                        int idExibir = scanner.nextInt();
                        // Exibir a pessoa do repositório ou mostrar uma mensagem se não encontrada
                        PessoaFisica pessoaExibir = pessoaFisicaRepo.obterPorId(idExibir);
                        if (pessoaExibir != null) {
                            pessoaExibir.exibir();
                        } else {
                            System.out.println("Pessoa não encontrada.");
                        }
                        break;
                    case 5:
                        // Lógica para exibir todos
                        System.out.println("Listando todas as pessoas físicas:");
                        for (PessoaFisica pessoa : pessoaFisicaRepo.obterTodos()) {
                            pessoa.exibir();
                        }
                        break;
                    case 6:
                        // Lógica para salvar dados
                        System.out.println("Digite o nome do arquivo para salvar:");
                        String nomeArquivoSalvar = scanner.nextLine();
                        // Implemente a lógica de persistência no repositório
                        pessoaFisicaRepo.persistir(nomeArquivoSalvar);
                        System.out.println("Dados salvos com sucesso.");
                        break;
                    case 7:
                        // Lógica para recuperar dados
                        System.out.println("Digite o nome do arquivo para recuperar:");
                        String nomeArquivoRecuperar = scanner.nextLine();
                        // Implemente a lógica de recuperação no repositório
                        pessoaFisicaRepo.recuperar(nomeArquivoRecuperar);
                        System.out.println("Dados recuperados com sucesso.");
                        break;
                    case 0:
                        System.out.println("Finalizando o sistema.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } while (opcao != 0);
        }
    }

    private static void cadastrarPessoa(Scanner scanner, PessoaFisicaRepo pessoaFisicaRepo, PessoaJuridicaRepo pessoaJuridicaRepo) {
        System.out.println("Selecione o tipo de pessoa:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");

        int tipo = scanner.nextInt();

        System.out.println("Digite o ID da pessoa:");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir a quebra de linha

        System.out.println("Digite o nome da pessoa:");
        String nome = scanner.nextLine();

        switch (tipo) {
            case 1:
                System.out.println("Digite o CPF da pessoa física:");
                String cpf = scanner.nextLine();
                System.out.println("Digite a idade da pessoa física:");
                int idade = scanner.nextInt();
                PessoaFisica pessoaFisica = new PessoaFisica(id, nome, cpf, idade);
                pessoaFisicaRepo.inserir(pessoaFisica);
                break;
            case 2:
                System.out.println("Digite o CNPJ da pessoa jurídica:");
                String cnpj = scanner.nextLine();
                PessoaJuridica pessoaJuridica = new PessoaJuridica(id, nome, cnpj);
                pessoaJuridicaRepo.inserir(pessoaJuridica);
                break;
            default:
                System.out.println("Tipo de pessoa inválido.");
                break;
        }
    }
}
