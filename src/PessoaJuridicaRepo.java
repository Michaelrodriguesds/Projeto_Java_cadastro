import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

    // ... (outros métodos existentes)

    public void persistir(String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoasJuridicas);
            System.out.println("Dados persistidos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao persistir dados: " + e.getMessage());
        }
    }

    public void recuperar(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasJuridicas = (ArrayList<PessoaJuridica>) ois.readObject();
            System.out.println("Dados recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }

    private static class PessoaJuridica {

        public PessoaJuridica() {
        }
    }
}
