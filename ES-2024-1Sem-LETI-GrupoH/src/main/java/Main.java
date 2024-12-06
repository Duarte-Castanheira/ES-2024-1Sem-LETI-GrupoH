import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Integer,Terreno> terrenos = new HashMap<>();


    public static void main(String[] args) {
        JFrame frame = new JFrame("Interface Gráfica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JButton btnCarregar = new JButton("Carregar Ficheiro");
        JButton btnGrafos = new JButton("Grafos");
        JButton btnAreas = new JButton("Áreas");
        JButton btnSugestoes = new JButton("Sugestões");

        btnGrafos.setEnabled(false);
        btnAreas.setEnabled(false);
        btnSugestoes.setEnabled(false);

        frame.add(btnCarregar);
        frame.add(btnGrafos);
        frame.add(btnAreas);
        frame.add(btnSugestoes);

        btnCarregar.addActionListener(e -> carregarFicheiro( btnGrafos,btnAreas,btnSugestoes));
        btnGrafos.addActionListener(e -> mostrarOpcoesGrafos(frame));
        btnAreas.addActionListener(e -> mostrarOpcoesAreas(frame));
        btnSugestoes.addActionListener(e -> sugestoes());

        frame.setVisible(true);
    }


    private static void carregarFicheiro(JButton btnGrafos, JButton btnAreas, JButton btnSugestoes) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir ficheiro");


        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
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


    private static void pedirPalavra(int i, String mensagem) {
        double result = -1;
        String palavra = JOptionPane.showInputDialog("Insira uma palavra:");
        if(i==2) {
            result = Area_Media_Proprietario.calcular_AreaMedia(mensagem, palavra);
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

            TrocaTerrenos.gerarSugestoesDeTroca(terrenos,numero1,numero2);

            JOptionPane.showMessageDialog(null, "Resultado da sugestão: ");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira apenas números válidos.");
        }
    }


}