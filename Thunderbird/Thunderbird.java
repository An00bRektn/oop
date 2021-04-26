/******************************************************************************
 * Copyright (C) 2021 Eric Pogue.
 * 
 * This file and the ThunderbirdLite application is licensed under the 
 * BSD-3-Clause.
 * 
 * You may use any part of the file as long as you give credit in your 
 * source code.
 * 
 * This application utilizes the HttpRequest.java library developed by 
 * Eric Pogue
 * 
 *****************************************************************************/
 
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Container; 
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;
import java.util.Collections;

class ContactTile extends JPanel implements MouseListener{
    private int red, green, blue;
    final private Color AISLE_COLOR = new Color(0, 130, 130);
    private ThunderbirdContact contactInSeat = null;

    private String displayName;
    public String getName() {return displayName;}
    private Boolean isAnAisle = false;
    public void setAisle() { isAnAisle = true; }

    ContactTile() {
        super();
        red = 0;
        green = 204;
        blue = 204;
        addMouseListener(this);
        // Todo: Remove everything to do with random colors.
        // RS: Removed the use of SetRandomValues() in code, marked the function with
        //     Deprecated tag in case of future use

        // Todo: Implement visually appealing colors for aisles and seats.
        // RS: monochromatic blue, darker indicates an aisle.
    }

    ContactTile(ThunderbirdContact contactInSeatIn) {
        super();
        red = 0;
        green = 204;
        blue = 204;
        addMouseListener(this);
        contactInSeat = contactInSeatIn;
    }

    @Deprecated
    final public void SetRandomValues() {
        red = GetNumberBetween(0,255);
        green = GetNumberBetween(0,255);
        blue = GetNumberBetween(0,255);
    }

    private static int GetNumberBetween(int min, int max) {
        Random myRandom = new Random();
        return min + myRandom.nextInt(max-min+1);
    }   

     public void paintComponent(Graphics g) {
        super.paintComponent(g); 

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (isAnAisle) {
            g.setColor(AISLE_COLOR);
        } else {
            g.setColor(new Color(red,green,blue));
        }
        
        g.fillRect (10, 10, panelWidth-20, panelHeight-20);

        if (isAnAisle) {
            g.setColor(new Color(GetContrastingColor(AISLE_COLOR.getRed()),
                                 GetContrastingColor(AISLE_COLOR.getGreen()),
                                 GetContrastingColor(AISLE_COLOR.getBlue())));
        } else {
            g.setColor(new Color(GetContrastingColor(red),GetContrastingColor(green),GetContrastingColor(blue)));
        }
        

        final int fontSize=18;
        g.setFont(new Font("Arial", Font.PLAIN, fontSize));
        int stringX = (panelWidth/4);
        int stringY = (panelHeight/2)+10;
        if (contactInSeat != null) {
            // ToDo: Display preferred name instead of first and last name.
            // RS: Done. If no preferred name, first and last will display anyway.
            if(contactInSeat.getPreferredName() != null){
                displayName = contactInSeat.getPreferredName();
            } 
            else{
                displayName = contactInSeat.getFirstName() + " " + contactInSeat.getLastName();
            }
            g.drawString(displayName,stringX,stringY);
        }
    }

    private static int GetContrastingColor(int colorIn) {
        return ((colorIn+128)%256);
    }

    public void mouseClicked(MouseEvent e) {
        if(contactInSeat != null){
            JFrame f = new JFrame();
            JLabel l = new JLabel(contactInSeat.toHTML());
            JDialog d = new JDialog(f, displayName);
            d.add(l);
            d.setSize(400, 400);
            d.setVisible(true);
        }
        repaint();
    }

    // Unused MouseListener methods
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e) {}
}

class ThunderbirdFrame extends JFrame implements ActionListener {
    private ArrayList<ContactTile> tileList;
    private ArrayList<ContactTile> aisleSeats;
    private boolean reverseView = false;

    private static JPanel contactGridPanel;

    public ThunderbirdFrame() {
        setBounds(200,200,900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton reverseView = new JButton("Reverse View");
        buttonPanel.add(reverseView);
        reverseView.addActionListener(this);

        contactGridPanel = new JPanel();
        contentPane.add(contactGridPanel, BorderLayout.CENTER);
        contactGridPanel.setLayout(new GridLayout(4,8));

        ThunderbirdModel tbM = new ThunderbirdModel();
        tbM.LoadIndex();
        tbM.LoadContactsThreaded();

        // Todo: Review ThunderbirdModel in detail and implement a multithreaded version of loading contacts. 
        // Hint: Review LoadContact() and LoadContactsThreaded() in detail.
        // RS: Replaced LoadContact() with LoadContactsThreaded() :/
        System.out.println("Printing Model:");
        //System.out.println(tbM);
        tbM.ValidateContacts();   


        tileList = new ArrayList<ContactTile>();
        aisleSeats = new ArrayList<ContactTile>();
        for(int i=1; i<33; i++) {
            ThunderbirdContact contactInSeat = tbM.findContactInSeat(i);
            if (contactInSeat != null) {
                System.out.println(contactInSeat);
            }

            ContactTile tile = new ContactTile(contactInSeat);

            // Todo: Place all the aisle seats in a array or an ArrayList instead of hard coding them.
            // RS: Not really sure how practical the ArrayList is in this case, the modulus operator takes care
            //     of the diagram as shown in the ThunderbirdLite video. Still placed them where they needed to be.
            if (i%8==4||(i==31)) {
                tile.setAisle();
                aisleSeats.add(tile);
            }

            tileList.add(tile);
            contactGridPanel.add(tile);
        }
    }

    public void actionPerformed(ActionEvent e) {
        // Todo: Remove randomization functionality and implement a visually appealing view of seats and aisles.
        // RS: See comments in ContactTile()

        // Todo: Implement reverse view where it looks like you are looking at the room from the back instead of the front 
        //     of the room.
        // RS: Implemented reverseView Boolean to indicate reversed view. Used Collections.reverse() to reverse the order
        //     of the ArrayList, and then put the tiles back on screen (after removing all of it from the panel).

        contactGridPanel.removeAll();
        contactGridPanel.setLayout(new GridLayout(4,8));
        Collections.reverse(tileList);
        for (ContactTile tile : tileList) {
            contactGridPanel.add(tile);
        }
        reverseView = !reverseView;
        contactGridPanel.setVisible(true);
        revalidate();
        repaint();
    }
}

// Todo: Rename the following class to Thunderbird.
// Hint: This will also require you to rename the Java file.
// RS: Successfully renamed.
public class Thunderbird {
    public static void main(String[] args) {

        // Todo: Update the following line so that it reflects the name change to Thunderbird.
        // RS: Done.
        System.out.println("Thunderbird Starting...");

        ThunderbirdFrame myThunderbirdFrame = new ThunderbirdFrame();
        myThunderbirdFrame.setVisible(true);
        myThunderbirdFrame.setTitle("Thunderbird");
    }
}