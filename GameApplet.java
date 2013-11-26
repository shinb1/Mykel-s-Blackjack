import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

import java.io.File;

import javax.imageio.ImageIO;

public class GameApplet extends Applet implements ActionListener {

        private Deck deck;

        private JPanel playerInteractionPanel = new JPanel();
        private JButton stayButton = new JButton("Stay");
        private JButton hitButton = new JButton("Hit");
        private JButton newGameButton = new JButton("New Game");
 

        private Player[] player = new Player[2];

        public void init() {

                this.setLayout(new GridLayout(0, 1));

                //This establishes all of the buttons and functions
                playerInteractionPanel.setLayout(new FlowLayout());
                stayButton.addActionListener(this);
                stayButton.setActionCommand("Stay");
                playerInteractionPanel.add(stayButton);

                hitButton.addActionListener(this);
                hitButton.setActionCommand("Hit");
                playerInteractionPanel.add(hitButton);

                newGameButton.addActionListener(this);
                newGameButton.setActionCommand("New Game");
                playerInteractionPanel.add(newGameButton);


                this.add(playerInteractionPanel);

                Card cardDrawn1 = new Card();
                Card cardDrawn2 = new Card();
                player[0] = new Human(200.0, cardDrawn1, cardDrawn2);
                player[1] = new Dealer(cardDrawn1, cardDrawn2);
                
        }

        public void paint(Graphics g) {
                super.paint(g);
        }

        public void actionPerformed(ActionEvent e) {
                if ("Stay".equals(e.getActionCommand())) {
                        player[1].flipCard();
                        this.add(player[1].getPanel());

                        validate();
                        repaint();

                        while (player[1].handValue() < 17) {
                                Card cardDrawn = deck.drawCard();
                                player[1].addCard(cardDrawn);
                                this.add(player[1].getPanel());

                                validate();
                                repaint();
                        }

                } else if ("Hit".equals(e.getActionCommand()) && player[0].handValue() < 21) {
                        Card cardDrawn = deck.drawCard();
                        player[0].addCard(cardDrawn);
                        this.add(player[0].getPanel());
                        this.add(player[1].getPanel());

                        validate();
                        repaint();

                } else if ("New Game".equals(e.getActionCommand())) {

                        deck = new Deck();

                        this.remove(player[0].getPanel());
                        this.remove(player[1].getPanel());

                        //Human cards and panels
                        Card cardDrawn1 = deck.drawCard();
                        Card cardDrawn2 = deck.drawCard();
                        player[0] = new Human(200.0, cardDrawn1, cardDrawn2);
                        this.add(player[0].getPanel());
                
                        //Dealer cards and panels
                        cardDrawn1 = deck.drawCard();
                        cardDrawn2 = deck.drawCard();
                        player[1] = new Dealer(cardDrawn1, cardDrawn2);
                        this.add(player[1].getPanel());

                       

                        validate();
                        repaint();

                } 

        }

}