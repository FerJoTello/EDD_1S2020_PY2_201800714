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
    private LinkedList<User>[] table;
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
        //System.out.println("Posicion de: " + user.getId() + " = " + position);
        if (table[position] == null) {
            table[position] = new LinkedList();
        }
        if (findUserPosition(user.getId(), table[position]) == -1) {
            table[position].AddLast(user, representation);
            numElements++;
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con el carnet: " + user.getId(), "Error al crear", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User getUser(int idUser) {
        int position = getDirection(idUser);
        if (table[position] != null) {
            int count = 0;
            Node auxNode = table[position].First;
            while (count != table[position].Size) {
                User auxUser = (User) auxNode.getObject();
                if (auxUser.getId() == idUser) {
                    return auxUser;
                } else {
                    auxNode = auxNode.getNext();
                    count++;
                }
            }
        }
        return null;
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
        boolean wasDeleted = false;
        if (table[position] != null) {
            //A user list was initialized previously.
            int userListPosition = findUserPosition(idUser, table[position]);
            if (userListPosition == 0) {
                //It's the first node
                table[position].First = table[position].First.getNext();
                if (table[position].Size == 1) {
                    //The list is empty so first and last must be null.
                    table[position].Last = null;
                }
                table[position].Size--;
                wasDeleted = true;
            } else if (userListPosition > 0 && userListPosition < table[position].Size) {
                //It's any other node
                int actualPosition = 0;
                //auxNode must be the previous node to the one which is being deleted
                Node auxNode = table[position].First;
                while (actualPosition != userListPosition - 1) {
                    //Advances on the list until it gets the required node
                    auxNode = auxNode.getNext();
                    actualPosition++;
                }
                Node deletedNode = auxNode.getNext();
                auxNode.setNext(deletedNode.getNext());
                if (userListPosition == table[position].Size - 1) {
                    table[position].Last = auxNode;
                }
                table[position].Size--;
                wasDeleted = true;
            }
        }
        if (wasDeleted) {
            //Reducing the number of elements.
            numElements--;
            if (table[position].Size == 0) {
                //The list is empty.
                table[position] = null;
            }
            JOptionPane.showMessageDialog(null, "Se eliminó con éxito al usuario con el carnet " + idUser);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar al usuario con el carnet" + idUser);
        }
        return wasDeleted;
    }

    private int findUserPosition(int idRequested, LinkedList<User> list) {
        int actualPosition = 0;
        Node auxNode = list.First;
        while (actualPosition != list.Size) {
            User auxUser = (User) auxNode.getObject();
            if (auxUser.getId() == idRequested) {
                return actualPosition;
            } else {
                auxNode = auxNode.getNext();
                actualPosition++;
            }
        }
        return -1;
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
