/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import Elements.User;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class HashTable {

    public static HashTable Users = new HashTable();
    static final int SIZETABLE = 45;
    private LinkedList[] table;
    private int numElements;

    public HashTable() {
        table = new LinkedList[SIZETABLE];
        for (int i = 0; i < SIZETABLE; i++) {
            table[i] = null;
        }
        numElements = 0;
    }

    private int getDirection(int idUser) {
        return idUser % table.length;
    }

    public void insert(User user, String representation) {
        int position = getDirection(user.getId());
        System.out.println("Posicion de: " + user.getId() + " = " + position);
        if (table[position] == null) {
            table[position] = new LinkedList();
        }
        table[position].AddLast(user, representation);
        numElements++;
    }

    public User getUser(int idUser) {
        User requestedUser = null;
        int position = getDirection(idUser);
        if (table[position] != null) {
            requestedUser = table[position].getUser(idUser);
        }
        return requestedUser;
    }

    /**
     * Deletes an User type object of the elements of the hash table. Returns a
     * boolean indicating if the user with the provided id could be deleted from
     * the list.
     *
     * @param idUser id from the user to delete.
     * @return boolean indicating if the user was deleted.
     */
    public boolean deleteUser(int idUser) {
        int position = getDirection(idUser);
        if (table[position] != null) {
            boolean wasDeleted = table[position].deleteUser(idUser);
            if (wasDeleted) {
                numElements--;
                if (table[position].getSize() == 0) {
                    table[position] = null;
                }
                JOptionPane.showMessageDialog(null, "Se eliminó con éxito al usuario con el carnet " + idUser);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar al usuario con el carnet" + idUser);
            }
            return wasDeleted;
        }
        return false;
    }

    public String generateGraph(String name) {
        String dot = "digraph Report{\n"
                + "\trankdir=LR;\n"
                + "\tGraph[label = \"Tabla Hash:Usuarios\" fontname=Arial];\n"
                + "\tedge [colorscheme = rdbu11  color=9];\n"
                + "\tnode [colorscheme=rdbu11  color=9 style=filled fillcolor=8 fontname=Arial fontcolor=6 shape=box];\n"
                + "\tnodesep = 0.02;\n";
        for (int i = SIZETABLE - 1; i >= 0; i--) {
            dot += "\tn" + i + "[label =\"" + i + "\" color=10 style= filled  fillcolor=11];\n";
            if (table[i] != null) {
                dot += "\tn" + i;
                for (Node auxNode = table[i].First; auxNode != null; auxNode = auxNode.getNext()) {
                    dot += " -> " + auxNode.getRepresentation();
                }
                dot += ";\n";
            } else {
                dot += "\tn" + i + " -> null" + i + ";\n\tnull" + i + "[label = \"null\"shape=none style=none fontcolor=11];\n";
            }
        }
        dot += "}";
        Grapher grapher = new Grapher(name);
        return grapher.generateGraph(dot);
    }

    public int getNumElements() {
        return numElements;
    }
}
