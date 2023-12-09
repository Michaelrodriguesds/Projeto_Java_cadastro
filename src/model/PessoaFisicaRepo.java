import java.io.*;
import java.util.ArrayList;
import model.PessoaFisica;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> pessoasFisicas = new ArrayList<>();

    public void persistir(String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoasFisicas);
            System.out.println("Dados persistidos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao persistir dados: " + e.getMessage());
        }
    }

    public void recuperar(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasFisicas = (ArrayList<PessoaFisica>) ois.readObject();
            System.out.println("Dados recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }

    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
        System.out.println("Pessoa inserida com sucesso.");
    }

    public Iterable<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }

    public PessoaFisica obterPorId(int idExibir) {
        for (PessoaFisica pessoa : pessoasFisicas) {
            if (pessoa.getId() == idExibir) {
                return pessoa;
            }
        }
        return null; // Retorna null se não encontrar a pessoa
    }

    public void excluir(int idExcluir) {
        pessoasFisicas.removeIf(pessoa -> pessoa.getId() == idExcluir);
        System.out.println("Pessoa excluída com sucesso.");
    }

    public void alterarNome(int idAlterar, String novoNome) {
        for (PessoaFisica pessoa : pessoasFisicas) {
            if (pessoa.getId() == idAlterar) {
                pessoa.setNome(novoNome);
                System.out.println("Nome alterado com sucesso.");
                return;
            }
        }
        System.out.println("Pessoa não encontrada.");
    }

    public boolean existePessoa(int idAlterar) {
        for (PessoaFisica pessoa : pessoasFisicas) {
            if (pessoa.getId() == idAlterar) {
                return true;
            }
        }
        return false;
    }
}
