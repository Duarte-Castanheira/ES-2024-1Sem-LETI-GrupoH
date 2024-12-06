import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe principal que gerencia a interface gráfica da aplicação.
 * Permite ao usuário interagir com funcionalidades como carregar ficheiros, visualizar grafos, calcular áreas médias, e sugerir trocas.
 */

public class Main {

    /**
     * Mapa para armazenar os terrenos carregados do ficheiro.
     */

    private static Map<Integer,Terreno> terrenos = new HashMap<>();


    public static void main(String[] args) {
        JFrame frame = new JFrame("Interface Gráfica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        // Botões principais
        JButton btnCarregar = new JButton("Carregar Ficheiro");
        JButton btnGrafos = new JButton("Grafos");
        JButton btnAreas = new JButton("Áreas");
        JButton btnSugestoes = new JButton("Sugestões");

        // Adicionar botões à janela
        frame.add(btnCarregar);
        frame.add(btnGrafos);
        frame.add(btnAreas);
        frame.add(btnSugestoes);

        btnGrafos.setEnabled(false);
        btnAreas.setEnabled(false);
        btnSugestoes.setEnabled(false);

        // Adicionar ação ao botão "Carregar Ficheiro"
        btnCarregar.addActionListener(e -> carregarFicheiro(btnGrafos,btnAreas,btnSugestoes));

        // Adicionar ação ao botão "Grafos"
        btnGrafos.addActionListener(e -> mostrarOpcoesGrafos(frame));

        // Adicionar ação ao botão "Áreas"
        btnAreas.addActionListener(e -> mostrarOpcoesAreas(frame));

        // Adicionar ação ao botão "Sugestões"
        btnSugestoes.addActionListener(e -> sugestoes());

        // Tornar a janela visível
        frame.setVisible(true);
    }


    /**
     * Método para carregar um ficheiro CSV contendo os dados dos terrenos.
     * Ativa os botões dependentes após o carregamento bem-sucedido.
     */

    private static void carregarFicheiro(JButton btnGrafos, JButton btnAreas, JButton btnSugestoes) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir ficheiro");


        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            JOptionPane.showMessageDialog(null, "Ficheiro Selecionado: " + file.getAbsolutePath());


            terrenos = Ler_DataBase.ReadFile(file);
            new Area_Media(terrenos);
            new Area_Media_Proprietario(terrenos);

            // Ativar os botões após o carregamento do ficheiro
            btnGrafos.setEnabled(true);
            btnAreas.setEnabled(true);
            btnSugestoes.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum ficheiro foi selecionado.");
        }
    }

    /**
     * Mostra opções relacionadas aos grafos em uma janela de diálogo.
     */

    static void mostrarOpcoesGrafos(JFrame frame) {

        JDialog dialog = new JDialog(frame, "Opções de Grafos", true);
        dialog.setSize(200, 150);
        dialog.setLayout(new FlowLayout());

        JButton btnPropriedades = new JButton("Propriedades");
        JButton btnProprietarios = new JButton("Proprietários");

        dialog.add(btnPropriedades);
        dialog.add(btnProprietarios);

        btnPropriedades.addActionListener(e -> {
            Graph.CreateGraph(terrenos);
        });

        btnProprietarios.addActionListener(e -> {
            Grafo_Proprietario.construirGrafo(terrenos);
        });

        dialog.setVisible(true);
    }


    /**
     * Mostra opções relacionadas ao cálculo de áreas médias em uma janela de diálogo.
     */

    static void mostrarOpcoesAreas(JFrame frame) {

        JDialog dialog = new JDialog(frame, "Opções de Áreas médias", true);
        dialog.setSize(200, 200);
        dialog.setLayout(new FlowLayout());

        JButton btnOpcao1 = new JButton("Area média");
        JButton btnOpcao2 = new JButton("Area média tendo em conta os proprietários");

        dialog.add(btnOpcao1);
        dialog.add(btnOpcao2);

        btnOpcao1.addActionListener(e -> mostrarSubOpcoes(1,"Area média"));
        btnOpcao2.addActionListener(e -> mostrarSubOpcoes(2,"Area média tendo em conta os proprietários"));

        dialog.setVisible(true);
    }


    /**
     * Mostra sub-opções para cálculo de áreas médias com base em critérios específicos.
     */

    private static void mostrarSubOpcoes(int i, String opcao) {
        JDialog dialog = new JDialog((Frame) null, "Opções de " + opcao, true);
        dialog.setSize(250, 200);
        dialog.setLayout(new FlowLayout());

        JButton btnSubOpcao1 = new JButton("Freguesia");
        JButton btnSubOpcao2 = new JButton("Municipio");
        JButton btnSubOpcao3 = new JButton("Ilha");

        dialog.add(btnSubOpcao1);
        dialog.add(btnSubOpcao2);
        dialog.add(btnSubOpcao3);

        btnSubOpcao1.addActionListener(e -> pedirPalavra(i,"Freguesia"));
        btnSubOpcao2.addActionListener(e -> pedirPalavra(i,"Municipio"));
        btnSubOpcao3.addActionListener(e -> pedirPalavra(i,"Ilha"));

        dialog.setVisible(true);
    }


    /**
     * Solicita ao usuário uma entrada para calcular áreas médias com base em uma palavra-chave.
     */
    private static void pedirPalavra(int i, String mensagem) {
        double result = -1;
        String palavra = JOptionPane.showInputDialog("Insira uma palavra:");
        if(i==2) {
            result = Area_Media_Proprietario.obterTerrenos(mensagem, palavra);
        } else if (palavra != null && !palavra.isEmpty()) {
            switch (mensagem.toLowerCase()) {
                case "freguesia":
                    result = Area_Media.calcularAreaMedia_Freguesia(palavra);
                    break;
                case "municipio":
                    result = Area_Media.calcularAreaMedia_Municipio(palavra);
                    break;
                case "ilha":
                    result = Area_Media.calcularAreaMedia_Ilha(palavra);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida: " + mensagem);
                    return;
            }
        }
        JOptionPane.showMessageDialog(null, "Área média de " + mensagem + ": " + result);
    }

    /**
     * Mostra uma funcionalidade de sugestão de troca entre dois terrenos.
     */

    private static void sugestoes() {
        try {
            // Pedir o primeiro número
            String input1 = JOptionPane.showInputDialog(null, "Insira o primeiro número:");
            if (input1 == null || input1.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operação cancelada ou entrada inválida.");
                return;
            }
            int numero1 = Integer.parseInt(input1);

            // Pedir o segundo número
            String input2 = JOptionPane.showInputDialog(null, "Insira o segundo número:");
            if (input2 == null || input2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operação cancelada ou entrada inválida.");
                return;
            }
            int numero2 = Integer.parseInt(input2);
            Map<Integer, TrocaTerrenos.Troca> trocas = new HashMap<>();
            trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos,numero1,numero2);
            System.out.println(trocas);
            JOptionPane.showMessageDialog(null, "Resultado da sugestão: " + trocas);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira apenas números válidos.");
        }
    }


}