import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Integer,Terreno> terrenos = new HashMap<>();
    private static Area_Media areaMedia;
    private static Area_Media_Proprietario areaMedia_proprietario;

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

        // Adicionar ação ao botão "Carregar Ficheiro"
        btnCarregar.addActionListener(e -> carregarFicheiro());

        // Adicionar ação ao botão "Grafos"
        btnGrafos.addActionListener(e -> mostrarOpcoesGrafos(frame));

        // Adicionar ação ao botão "Áreas"
        btnAreas.addActionListener(e -> mostrarOpcoesAreas(frame));

        // Adicionar ação ao botão "Sugestões"
        btnSugestoes.addActionListener(e -> sugestoes());

        // Tornar a janela visível
        frame.setVisible(true);
    }

    // Método chamado ao clicar em "Carregar Ficheiro"
    private static void carregarFicheiro() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir ficheiro");

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Ficheiro de Texto","txt"));
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens ","png", "jpg", "gif"));
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Áudio","wav", "mp3", "aac"));
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Todos os ficheiros" ,"*"));

        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            JOptionPane.showMessageDialog(null, "Ficheiro Selecionado: " + file.getAbsolutePath());


            terrenos = Ler_DataBase.ReadFile("Madeira-Moodle-1.1.csv");
            areaMedia = new Area_Media(terrenos);
            areaMedia_proprietario = new Area_Media_Proprietario(terrenos);
        }else {
            JOptionPane.showMessageDialog(null, "Função para carregar ficheiro chamada!");
        // Implementar a funcionalidade de carregar ficheiro aqui
        }
    }

    // Método para mostrar opções de grafos
    private static void mostrarOpcoesGrafos(JFrame frame) {
        JDialog dialog = new JDialog(frame, "Opções de Grafos", true);
        dialog.setSize(200, 150);
        dialog.setLayout(new FlowLayout());

        JButton btnPropriedades = new JButton("Propriedades");
        JButton btnProprietarios = new JButton("Proprietários");

        dialog.add(btnPropriedades);
        dialog.add(btnProprietarios);

        btnPropriedades.addActionListener(e -> {
            Graph.CreateGraph(terrenos);
//            JOptionPane.showMessageDialog(dialog, "Função de propriedades chamada!");
        });

        btnProprietarios.addActionListener(e -> {
            Grafo_Proprietario.construirGrafo(terrenos);
//            JOptionPane.showMessageDialog(dialog, "Função de proprietários chamada!");
        });

        dialog.setVisible(true);
    }

    // Método para mostrar opções de áreas
    private static void mostrarOpcoesAreas(JFrame frame) {
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

    // Método para mostrar subopções para cada opção de áreas
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

    // Método para pedir uma palavra ao utilizador
    private static void pedirPalavra(int i, String mensagem) {
        double result = -1;
        String palavra = JOptionPane.showInputDialog("Insira uma palavra:");
        if(i==2) {
            result = areaMedia_proprietario.calcular_AreaMedia(mensagem, palavra);
        } else if (palavra != null && !palavra.isEmpty()) {
            switch (mensagem.toLowerCase()) {
                case "freguesia":
                    result = areaMedia.calcularAreaMedia_Freguesia(palavra);
                    break;
                case "municipio":
                    result = areaMedia.calcularAreaMedia_Municipio(palavra);
                    break;
                case "ilha":
                    result = areaMedia.calcularAreaMedia_Ilha(palavra);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida: " + mensagem);
                    return; // Sai do método se a opção for inválida
            }
        }
        JOptionPane.showMessageDialog(null, "Área média de " + mensagem + ": " + result);
    }


    // Método chamado ao clicar em "Sugestões"
    private static void sugestoes() {
        JOptionPane.showMessageDialog(null, "Função para sugestões chamada!");
        // Implementar funcionalidade de sugestões aqui
    }


}
