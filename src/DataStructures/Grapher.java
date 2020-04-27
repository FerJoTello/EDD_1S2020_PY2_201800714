/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Fernando
 */
public class Grapher {

    private String name, path, dotText;

    public Grapher(String name) {
        this.name = name;
        path = System.getProperty("user.dir") + "\\Graphviz";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public String generateGraph(String dotText) {
        this.dotText = dotText;
        String pathDot = path + "\\" + name + ".dot";
        String pathPng = path + "\\" + name + ".png";
        generateDot(pathDot, pathPng);
        return pathPng;
    }

    private void generateDot(String pathDot, String pathPng) {
        try {
            FileWriter fw = new FileWriter("Graphviz\\" + name + ".dot");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dotText);
            bw.close();

            String command1 = "cmd /c dot.exe -Tpng \"" + pathDot + "\" -o \"" + pathPng + "\"";
            String command2 = "cmd /c " + pathPng;
            Runtime rt = Runtime.getRuntime();
            try {
                Process pr = rt.exec(command1);
                int exitValue1 = pr.waitFor();
                System.out.println("Exit value command1: " + exitValue1);
                pr = rt.exec(command2);
                int exitValue2 = pr.waitFor();
                System.out.println("Exit value command2: " + exitValue2);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException ex) {
            System.out.println("Error al generar archivo dot");
            ex.printStackTrace();
        }
    }
}
