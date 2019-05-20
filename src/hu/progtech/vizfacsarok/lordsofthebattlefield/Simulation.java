package hu.progtech.vizfacsarok.lordsofthebattlefield;

import hu.progtech.vizfacsarok.lordsofthebattlefield.BaseWindow;
import hu.progtech.vizfacsarok.lordsofthebattlefield.buildings.Barrack;
import hu.progtech.vizfacsarok.lordsofthebattlefield.buildings.Farm;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import hu.progtech.vizfacsarok.lordsofthebattlefield.units.Attacker;

public class Simulation extends BaseWindow implements KeyListener {
    
    private int size;
    private Map map;
    private final JPanel mainPanel;
    private int camerax;
    private int cameray;
    private final JPanel actionPanel;
    private int[] buttons;
    private int[] supllyicons;
    private boolean marked;
    private int[] markedelement;
    private int actualplayer;
    private Player[] players;
    
    
    
    public Simulation() throws IOException {
        actualplayer = 1;
        marked = false;
        markedelement = new int[]{0,0};
        buttons = new int[]{ 41,42,43,44,81,82,83,84,121,122,123,124,0 };
        supllyicons = new int[]{ 175,177,179,181,183,0 };
        this.size = 40;
        map = new Map(size);
        this.camerax = 0;
        this.cameray = 0;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        mainPanel = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel.setPreferredSize(new Dimension(screenSize.width,840));
        mainPanel.setLayout(new GridLayout(7, 16));
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 16; ++j) {
                addButton(mainPanel, i, j);
            }
        }
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        actionPanel = new actionPanel();
        actionPanel.setLayout(null);
        actionPanel.setPreferredSize(new Dimension(screenSize.width,240));
        
        actionPanel.setLayout(new GridLayout(5, 40));
        this.players = new Player[]{new Player(100,"asd",100,200,300,400,500,600),new Player(100,"asd",100,200,300,400,500,600)};
        
        int j = 0;
        int k = 0;
        for (int i = 0; i < 199; ++i) {
            if(buttons[j] == i){
                addActionButton(actionPanel, j);
                j++;
            }else{
                if(supllyicons[k] == i){
                    addfixicon(actionPanel, k);
                    addEmptyButton(actionPanel,100);
                    k++;
                    i++;
                }else{
                    addEmptyButton(actionPanel,1000000);
                }
            }
        }
        addExitB(actionPanel, k);
        updateactionp();
        
        getContentPane().add(actionPanel, BorderLayout.SOUTH);
        updatemainp();
        updateactionp();
        
        
        
        
    }
    
    private void addButton(JPanel panel,final int ib, final int jb) {
        final JButton button = new JButton();
        javax.swing.border.Border emptyBorder = BorderFactory.createEmptyBorder();
        button.setBorder(emptyBorder);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.addActionListener((ActionEvent e) -> {
            int i = ib + cameray;
            int j = jb + camerax;
            if(!marked){
                int a = map.getPossess(i, j);
                switch(a){
                    case 1:
                        markedelement[0] = i;
                        markedelement[1] = j;
                        if(map.getUnit(i, j) != null){
                            updatemainpMovep(i,j);
                            updatemainpAttackp(i,j);
                            marked = true;
                        }else{
                            if(map.getBuilding(i, j) != null && map.getBuilding(i, j) instanceof Barrack){
                                ((Barrack)map.getBuilding(i, j)).releaseUnit(map);
                                updatemainp();
                            }else{
                                ((Farm)map.getBuilding(i, j)).addFood(players[0]);
                                updateactionp();
                            }
                        }
                        break;
                    case 2:
                        
                        break;
                    default:
                        break;
                }
            }else{
                ArrayList<int[]> positions = map.getUnit(markedelement[0], markedelement[1]).canMoveTo(map);
                int value = map.getTerrain(i + cameray, j + camerax);
                boolean contains = false;
                for(int k = 0;k < positions.size();k++){
                    if(positions.get(k)[0] == i && positions.get(k)[1] == j)contains = true;
                }
                ArrayList<int[]> positions2 = ((Attacker)map.getUnit(markedelement[0], markedelement[1])).canAttack(map);
                boolean contains2 = false;
                for(int k = 0;k < positions2.size();k++){
                    if(positions2.get(k)[0] == i && positions2.get(k)[1] == j)contains2 = true;
                }
                if(contains){
                    map.getUnit(markedelement[0], markedelement[1]).move(map, i, j);
                    map.setUnit(markedelement[0], markedelement[1], null);
                    marked = false;
                    updatemainp();
                }else if(contains2){
                    ((Attacker)map.getUnit(markedelement[0], markedelement[1])).attack(map, map.getUnit(i, j));
                    marked = false;
                    updatemainp();
                }else{
                    marked = false;
                    updatemainp();
                    int a = map.getPossess(i, j);
                    switch(a){
                        case 1:
                            markedelement[0] = i;
                            markedelement[1] = j;
                            if(map.getUnit(i, j) != null){
                                updatemainpMovep(i,j);
                                updatemainpAttackp(i,j);
                                marked = true;
                            }else{
                                if(map.getBuilding(i, j) != null && map.getBuilding(i, j) instanceof Barrack){
                                    ((Barrack)map.getBuilding(i, j)).releaseUnit(map);
                                    updatemainp();
                                }
                            }
                            break;
                        case 2:

                            break;
                        default:
                            break;
                    }
                }
            }
        });

        panel.add(button);
    }
    
    private void addActionButton(JPanel panel, final int i) {
        final JButton button = new JButton();
        button.setFocusable(false);
        button.addActionListener((ActionEvent e) -> {
            map.action(i);
            System.out.println("(" + i + ")");
        });

        panel.add(button);
    }
    
    private void addfixicon(JPanel panel, final int i) {
        final JButton button = new JButton();
        javax.swing.border.Border emptyBorder = BorderFactory.createEmptyBorder();
        button.setBorder(emptyBorder);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        BufferedImage fixicons = null;
        try {
            fixicons = ImageIO.read(getClass().getResource("source/photo/fixicons.png"));
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        button.setText(null);
        BufferedImage fixicon = fixicons.getSubimage(i*48, 0, 48, 48);
        ImageIcon fixiconc = new ImageIcon(fixicon);
        button.setIcon(fixiconc);
        panel.add(button);
    }
    
    private void addExitB(JPanel panel, final int i) {
        final JButton button = new JButton();
        javax.swing.border.Border emptyBorder = BorderFactory.createEmptyBorder();
        button.setBorder(emptyBorder);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        BufferedImage fixicons = null;
        try {
            fixicons = ImageIO.read(getClass().getResource("source/photo/fixicons.png"));
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        button.setText(null);
        BufferedImage fixicon = fixicons.getSubimage(i*48, 0, 48, 48);
        ImageIcon fixiconc = new ImageIcon(fixicon);
        button.setIcon(fixiconc);
        button.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        panel.add(button);
    }
    
    private void addEmptyButton(JPanel panel, final int i) {
        final JButton button = new JButton();
        javax.swing.border.Border emptyBorder = BorderFactory.createEmptyBorder();
        button.setBorder(emptyBorder);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        panel.add(button);
        if(1000000 != i)button.setText(String.valueOf(i));
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if(camerax<24)
                    camerax++;
                updatemainp();
                if(marked)updatemainpMovep(markedelement[0],markedelement[1]);
                break;
            case KeyEvent.VK_LEFT:
                if(camerax>0)
                    camerax--;
                updatemainp();
                if(marked)updatemainpMovep(markedelement[0],markedelement[1]);
                break;
            case KeyEvent.VK_DOWN:
                if(cameray<33)
                    cameray++;
                updatemainp();
                if(marked)updatemainpMovep(markedelement[0],markedelement[1]);
                break;
            case KeyEvent.VK_UP:
                if(cameray>0)
                    cameray--;
                updatemainp();
                if(marked)updatemainpMovep(markedelement[0],markedelement[1]);
                break;
            default:
                break;
        }
    } 

    private void updatemainp() {
        BufferedImage lands = null;
        ImageIcon landsc = null;
        try {
            lands = ImageIO.read(getClass().getResource("source/photo/land.png"));
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage landp;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 16; j++) {
                JButton button = (JButton)mainPanel.getComponent(j + 16*i);
                
                int value = map.getTerrain(i + cameray, j + camerax);
                int px = (value - 1) * 120;
                landp = lands.getSubimage(px,0, 120, 120);
                if(map.getUnit(i + cameray, j + camerax) != null && map.getPossess(i + cameray, j + camerax) == 1)landp =  lands.getSubimage(240,0, 120, 120);
                if(map.getUnit(i + cameray, j + camerax) != null && map.getPossess(i + cameray, j + camerax) == 2)landp =  lands.getSubimage(120,120, 120, 120);
                if(map.getBuilding(i + cameray, j + camerax) != null && map.getBuilding(i + cameray, j + camerax) instanceof Farm)landp =  lands.getSubimage(360,0, 120, 120);
                if(map.getBuilding(i + cameray, j + camerax) != null && map.getBuilding(i + cameray, j + camerax) instanceof Barrack)landp =  lands.getSubimage(360,120, 120, 120);
                landsc = new ImageIcon(landp);
                button.setIcon(landsc);
            }
        }
        
        
    }
    
    private void updatemainpMovep(int x,int y) {
        Image land = null;
        try {
            land = ImageIO.read(getClass().getResource("source/photo/landmarked.png"));
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon landc = new ImageIcon(land);
        ArrayList<int[]> positions = map.getUnit(x, y).canMoveTo(map);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 16; j++) {
                JButton button = (JButton)mainPanel.getComponent(j + 16*i);
                int value = map.getTerrain(i + cameray, j + camerax);
                boolean contains = false;
                for(int k = 0;k < positions.size();k++){
                    if(positions.get(k)[0]- cameray == i && positions.get(k)[1] - camerax == j)contains = true;
                }
                if(contains){
                    switch (value) {
                        case 1:
                            button.setIcon(landc);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
    private void updateactionp() {
        BufferedImage actionbuttons = null;
        ImageIcon actuonc = null;
        try {
            actionbuttons = ImageIO.read(getClass().getResource("source/photo/actionbuttons.png"));
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage actionbutton;
        
        int j = 0;
        for (int i = 0; i < 12; i++) {
            JButton button = (JButton)actionPanel.getComponent(buttons[i]);
            int value = map.getTerrain(i + cameray,camerax);
            switch (value) {
                case 1:
                    button.setText(null);
                    actionbutton = actionbuttons.getSubimage(0, 0, 48, 48);
                    actuonc = new ImageIcon(actionbutton);
                    button.setIcon(actuonc);
                    break;
                case 2:
                    button.setText(null);
                    actionbutton = actionbuttons.getSubimage(48, 0, 48, 48);
                    actuonc = new ImageIcon(actionbutton);
                    button.setIcon(actuonc);
                    break;
                default:
                    break;
            }
        }
        JButton button = (JButton)actionPanel.getComponent(176);
        button.setText(String.valueOf(players[0].getFood()));
        button = (JButton)actionPanel.getComponent(178);
        button.setText(String.valueOf(players[0].getWood()));
        button = (JButton)actionPanel.getComponent(180);
        button.setText(String.valueOf(players[0].getStone()));
        button = (JButton)actionPanel.getComponent(182);
        button.setText(String.valueOf(players[0].getGold()));
        button = (JButton)actionPanel.getComponent(184);
        button.setText(String.valueOf(players[0].getPopulation()));
    }
    
    private void updatemainpAttackp(int x,int y) {
        Image land = null;
        try {
            land = ImageIO.read(getClass().getResource("source/photo/landmarkedpink.png"));
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon landc = new ImageIcon(land);
        ArrayList<int[]> positions = ((Attacker)map.getUnit(x, y)).canAttack(map);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 16; j++) {
                JButton button = (JButton)mainPanel.getComponent(j + 16*i);
                int value = map.getTerrain(i + cameray, j + camerax);
                boolean contains = false;
                for(int k = 0;k < positions.size();k++){
                    if(positions.get(k)[0]- cameray == i && positions.get(k)[1] - camerax == j)contains = true;
                }
                if(contains){
                    switch (value) {
                        case 1:
                            button.setIcon(landc);
                            break;
                        case 2:
                            button.setIcon(landc);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}