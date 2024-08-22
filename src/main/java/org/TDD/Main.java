package org.TDD;

import org.TDD.dao.UsuarioDAO;
import org.TDD.entity.Usuario;
import org.TDD.services.EmailService;
import org.TDD.services.UsuarioService;
import org.TDD.services.iEmailService;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final iEmailService emailService = new EmailService(); // Precisa implementar a classe EmailService
    private static final UsuarioService usuarioService = new UsuarioService(emailService);

    public static void main(String[] args) {
        while (true) {
            listarUsuarios();
            exibirMenu();
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    editarUsuario();
                    break;
                case 3:
                    deletarUsuario();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Editar Usuário");
        System.out.println("3. Deletar Usuário");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void listarUsuarios() {
        System.out.println("\nUsuários cadastrados:");
        usuarioDAO.listarTodos().forEach(usuario -> {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("----------------------------");
        });
    }

    private static void cadastrarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();

        Usuario usuario = new Usuario(nome, email, senha);
        usuarioDAO.inserirUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void editarUsuario() {
        System.out.print("Digite o email do usuário a ser editado: ");
        String email = scanner.nextLine();
        Usuario usuarioExistente = usuarioDAO.obterUsuarioPorEmail(email);

        if (usuarioExistente != null) {
            System.out.print("Digite o novo nome do usuário: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a nova senha do usuário: ");
            String senha = scanner.nextLine();

            Usuario usuarioAtualizado = new Usuario(nome, email, senha);
            usuarioDAO.atualizarUsuario(usuarioAtualizado);
            System.out.println("Usuário editado com sucesso!");
        } else {
            System.out.println("Nenhum usuário encontrado com esse email.");
        }
    }

    private static void deletarUsuario() {
        System.out.print("Digite o email do usuário a ser deletado: ");
        String email = scanner.nextLine();

        Usuario usuarioExistente = usuarioDAO.obterUsuarioPorEmail(email);
        if (usuarioExistente != null) {
            usuarioDAO.excluirUsuario(email);
            System.out.println("Usuário deletado com sucesso!");
        } else {
            System.out.println("Nenhum usuário encontrado com esse email.");
        }
    }
}
