/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author EZEA2
 */
public class Utilidades {
    
    public void scaleImage(JLabel logo){
        ImageIcon icon = new ImageIcon("D:\\Documentos varios\\Documentos\\Facultad\\2do año\\Programacion 2\\Programas\\Gestion\\utilities\\computadora.png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        logo.setIcon(scaledIcon);
    }
}
