package trabalho_POOA;

import java.util.ArrayList;
import java.util.Scanner;

public class CMS {
    private static ArrayList<Artigo> artigos = new ArrayList<>();
    private static Usuario usuario = new Usuario("admin", "admin");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean autenticado = false;
        boolean sair = false;

        while (!sair) {
            if (!autenticado) {
                exibirMenuInicial();
                int opcao = sc.nextInt();
                sc.nextLine(); 

                switch (opcao) {
                    case 1:
                        autenticado = realizarLogin(sc);
                        break;
                    case 2:
                        listarArtigos();
                        break;
                    case 3:
                        sair = true;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                exibirMenuAutenticado();
                int opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        criarArtigo(sc);
                        break;
                    case 2:
                        listarArtigos();
                        break;
                    case 3:
                        atualizarArtigo(sc);
                        break;
                    case 4:
                        excluirArtigo(sc);
                        break;
                    case 5:
                        autenticado = false;
                        System.out.println("Logout realizado com sucesso.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }

        sc.close();
    }

    private static void exibirMenuInicial() {
    	System.out.println(" /*********************\\");
        System.out.println("      Menu Inicial: \n");
        System.out.println("    1. Login");
        System.out.println("    2. Listar Conteúdos");
        System.out.println("    3. Sair");
        System.out.println("\n \\*********************/");
        System.out.print("\n Escolha uma opção: ");
    }

    private static void exibirMenuAutenticado() {
    	System.out.println("*********************");
        System.out.println("Menu (Autenticado): \n");
        System.out.println("1. Criar Conteúdo");
        System.out.println("2. Listar Conteúdo");
        System.out.println("3. Atualizar Conteúdo");
        System.out.println("4. Excluir Conteúdo");
        System.out.println("5. Logout \n");
        System.out.println("********************* \n");
        System.out.print("Escolha uma opção: ");
    }

    private static boolean realizarLogin(Scanner sc) {
        System.out.print("Usuário: ");
        String username = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        if (usuario.autenticar(username, senha)) {
            System.out.println("Login realizado com sucesso.");
            return true;
        } else {
            System.out.println("Usuário ou senha incorretos.");
            return false;
        }
    }

    private static void listarArtigos() {
        if (artigos.isEmpty()) {
            System.out.println("Nenhum artigo disponível.");
        } else {
            for (int i = 0; i < artigos.size(); i++) {
                System.out.println((i + 1) + ". " + artigos.get(i).getTitulo());
            }
        }
    }

    private static void criarArtigo(Scanner sc) {
        System.out.print("Título do artigo: ");
        String titulo = sc.nextLine();
        System.out.print("Conteúdo do artigo: ");
        String conteudo = sc.nextLine();

        Artigo novoArtigo = new Artigo(titulo, conteudo);
        artigos.add(novoArtigo);
        System.out.println("Artigo criado com sucesso.");
    }

    private static void atualizarArtigo(Scanner sc) {
        listarArtigos();
        System.out.print("Escolha o número do artigo para atualizar: ");
        int indice = sc.nextInt() - 1;
        sc.nextLine();

        if (indice >= 0 && indice < artigos.size()) {
            System.out.print("Novo título: ");
            String novoTitulo = sc.nextLine();
            System.out.print("Novo conteúdo: ");
            String novoConteudo = sc.nextLine();

            Artigo artigo = artigos.get(indice);
            artigo.setTitulo(novoTitulo);
            artigo.setConteudo(novoConteudo);
            System.out.println("Artigo atualizado com sucesso.");
        } else {
            System.out.println("Artigo não encontrado.");
        }
    }

    private static void excluirArtigo(Scanner sc) {
        listarArtigos();
        System.out.print("Escolha o número do artigo para excluir: ");
        int indice = sc.nextInt() - 1;

        if (indice >= 0 && indice < artigos.size()) {
            artigos.remove(indice);
            System.out.println("Artigo excluído com sucesso.");
        } else {
            System.out.println("Artigo não encontrado.");
        }
    }
}