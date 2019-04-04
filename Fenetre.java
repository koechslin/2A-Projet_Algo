
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.*;



public class Fenetre extends JFrame implements ActionListener{
	private JPanel panelPrincipal;
	private JPanel panelGauche;
	private JPanel panelDroite;
	private JPanel panelHautDroite;
	private JPanel panelBasDroite;
	private JLabel labelNbVoitures;
	private JTextField textNbVoitures;
	private JLabel labelMode;
	private JTextField TextFieldVoiture;
	private JTextField TextFieldStation;
	private JLabel labelControle;
	private JButton BoutonGo;
	private JLabel textStation;
	private JLabel textVoiture;
	private JRadioButton Automatique;
	private JRadioButton Manuel;
	private JButton importMap;
	private JButton recharge;
	private Lecteur_Fichier lecteur;
	
	
	Reseau r;
	Panel pan;
	//Timer time;
	final int DELAI_BOUCLE = 1000;
	
		public Fenetre(){
			this.setLayout(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
			r = new Reseau();
			lecteur = new Lecteur_Fichier();
			Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			this.setSize((int)tailleEcran.getWidth()-150,(int)tailleEcran.getHeight()-150);
			
			/*for(int i=0;i<r.getMapVoiture().length;i++) {
				for(int j=0;j<r.getMapVoiture()[i].length;j++) {
					if(r.getMapVoiture()[i][j]) {
						r.addVoiture(new Voiture(1,j,i));
					}
				}
			}
			r.getVoitures().get(0).setDirection("bas");
			r.getVoitures().get(1).setDirection("gauche");
			r.getVoitures().get(2).setDirection("droite");
			r.getVoitures().get(3).setDirection("haut");*/
			
			/*construction des elements gtaphiques de la fenetre*/
			panelPrincipal = new JPanel();
			panelGauche = new JPanel();
			//panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.PAGE_AXIS));
			panelGauche.setLayout(null);
			panelDroite = new JPanel();
			panelDroite.setLayout(null);
			panelBasDroite = new JPanel();
			panelBasDroite.setLayout(null);
			panelHautDroite = new JPanel();
			panelHautDroite.setLayout(null);
			
			pan = new Panel(r,(int)(0.6*tailleEcran.getWidth()-150)-this.getInsets().left-this.getInsets().right,(int)(tailleEcran.getHeight()-150)-this.getInsets().top-this.getInsets().bottom);
			//this.setBounds(50,50,pan.reseau.getMap()[0].length*pan.t,pan.reseau.getMap().length*pan.t+40);
			//this.setSize(900, 900);
			this.pan.setBounds((int)(0.2*tailleEcran.getWidth()-150)-this.getInsets().right, this.getInsets().top,(int)(0.6*tailleEcran.getWidth()-150)-this.getInsets().left-this.getInsets().right,(int)(tailleEcran.getHeight()-150)-this.getInsets().top-this.getInsets().bottom);
			panelDroite.setBounds(this.getInsets().left+1361,this.getInsets().top,(int)(0.2*tailleEcran.getWidth()-150)-this.getInsets().left-this.getInsets().right,(int)(tailleEcran.getHeight()-150)-this.getInsets().top-this.getInsets().bottom);
			panelDroite.setBackground(Color.RED);
			panelGauche.setBounds(this.getInsets().left,this.getInsets().top,(int)(0.6*tailleEcran.getWidth()-150)-this.getInsets().left-this.getInsets().right,(int)(tailleEcran.getHeight()-150)-this.getInsets().top-this.getInsets().bottom);
			panelGauche.setBackground(Color.GREEN);
			this.panelPrincipal.setBounds(0, 0, 1800, 900);
			this.panelPrincipal.setLayout(null);
			System.out.println("test");
			
			//ajout panel de droite
			
			//panelDroite.add(panelHautDroite);
			//panelDroite.add(panelBasDroite);
			//ajout panel Haut droite
			labelMode = new JLabel("Mode:");
			labelMode.setBounds(30, 30, 100, 50);
			Manuel = new JRadioButton("Manuel");
			Manuel.setBounds(30,60,120,30);
			Automatique = new JRadioButton("Automatique");
			Automatique.setBounds(30, 90,120,30);
			ButtonGroup groupB = new ButtonGroup();
			groupB.add(Manuel);
			groupB.add(Automatique);
			panelDroite.add(labelMode);
			panelDroite.add(Manuel);
			panelDroite.add(Automatique);
			
			//ajout panel Bas droite
			textStation = new JLabel("Station : ");
			textStation.setBounds(30,150,60,30);
			textVoiture = new JLabel("Voiture : ");
			textVoiture.setBounds(30,190,60,30);
			BoutonGo = new JButton("GO");
			BoutonGo.setBounds(30, 230, 100, 30);
			labelControle = new JLabel("Controle:");
			labelControle.setBounds(30, 500, 120, 30);
			TextFieldVoiture = new JTextField();
			TextFieldVoiture.setBounds(30, 270, 120, 30);
			TextFieldStation = new JTextField();
			TextFieldStation.setBounds(30, 310, 120, 30);
			panelDroite.add(TextFieldStation);
			panelDroite.add(TextFieldVoiture);
			panelDroite.add(labelControle);
			panelDroite.add(BoutonGo);
			panelDroite.add(textStation);
			panelDroite.add(textVoiture);
			
			//ajout panel de gauche
			labelNbVoitures = new JLabel("Nombre de voitures : ");
			labelNbVoitures.setFont(new Font("Serif",Font.PLAIN,35));
			labelNbVoitures.setBounds(panelGauche.getWidth()/2-150,80,350,50);
			textNbVoitures = new JTextField();
			textNbVoitures.setBounds(panelGauche.getWidth()/2-50,200,100,80);
			importMap = new JButton("Importer une map");
			importMap.setBounds(panelGauche.getWidth()/2-75,500,150,50);
			importMap.addActionListener(this);
			recharge = new JButton("Recharger la simulation");
			recharge.setBounds(panelGauche.getWidth()/2-100,350,200,50);
			recharge.addActionListener(this);
			panelGauche.add(importMap);
			panelGauche.add(recharge);
			panelGauche.add(labelNbVoitures);
			panelGauche.add(textNbVoitures);
			
			/*ajout au panel principal*/
			panelPrincipal.add(pan);
			panelPrincipal.add(panelGauche);
			panelPrincipal.add(panelDroite);
			
			
			
			this.add(panelPrincipal);
			this.setVisible(true);
			System.out.println("test");
			int a=0;
			while(a==0) {
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			/*Lecteur_Fichier fileReader =new Lecteur_Fichier();
			fileReader.ouvertureFichier();
			r.changeMap(fileReader.traitementFichier());
			for(int i=0;i<r.map.length;i++) {
				for(int j=0;j<r.map[i].length;j++) {
					System.out.print(r.map[i][j]+" ");
				}
				System.out.println();
			}
			this.setVisible(false);
			this.setBounds(50,50,pan.reseau.getMap()[0].length*pan.t,pan.reseau.getMap().length*pan.t+40);
			repaint();
			this.setVisible(true);
			repaint();*/
			r.convertionMapGraphe();
			r.calculCheminGraphe();
			r.conversionTrajectoire();
			
			//IL FAUT AJOUTER UN DERNIER AVANT A TOUTES LES TRAJECTOIRES
			for(int i=0;i<r.trajectoires.size();i++) {
				for(int j=0;j<r.trajectoires.get(i).size();j++) {
					r.trajectoires.get(i).get(j).add("avant");
				}
			}
			Voiture v = r.getVoitures().get(0);
			v.setTrajectoire(r.trajectoires.get(5).get(0));
			v.setStatDep(5);
			v.setStatArr(0);
			
			/*for(Voiture v : r.getVoitures()) {
				r.adapteSensVoiture(v);
				v.setSortCarrefour(true);
				v.change_voie();
			}*/
			r.adapteSensVoiture(v);
			v.setSortCarrefour(true);
			v.change_voie();
			
			while(true) {
				/*for(Voiture v : r.getVoitures()) {
					r.sortieCarrefour(v);
					v.change_voie();
					if(!v.getTrajectoire().isEmpty()) {
						v.avance();
					}
				}*/
				r.sortieCarrefour(v);
				v.change_voie();
				if(!v.getTrajectoire().isEmpty()) {
					v.avance();
				}
				else {
					v.setStatDep(v.getStatArr());
					v.setX(r.getStations().get(v.getStatDep()).getXDepart());
					v.setY(r.getStations().get(v.getStatDep()).getYDepart());
					int nouvelleDestination = (int)(Math.random()*r.getStations().size());
					while(nouvelleDestination == v.getStatDep()) {
						System.out.println("test");
						nouvelleDestination = (int)(Math.random()*r.getStations().size());
					}
					v.setStatArr(nouvelleDestination);
					v.setTrajectoire(r.trajectoires.get(v.getStatDep()).get(v.getStatArr()));
					r.adapteSensVoiture(v);
					v.setSortCarrefour(true);
					v.change_voie();
				}
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
			
			
			//time = new Timer(DELAI_BOUCLE,this);
			//time.start();
			
		
		public void paint(Graphics g) {
			if(Manuel.isSelected()) {
				this.TextFieldStation.setVisible(true);
				this.TextFieldVoiture.setVisible(true);
				this.labelControle.setVisible(true);
				this.BoutonGo.setVisible(true);
				this.textStation.setVisible(true);
				this.textVoiture.setVisible(true);
			}
			else {
				this.TextFieldStation.setVisible(false);
				this.TextFieldVoiture.setVisible(false);
				this.labelControle.setVisible(false);
				this.BoutonGo.setVisible(false);
				this.textStation.setVisible(false);
				this.textVoiture.setVisible(false);
			}
			this.pan.repaint();
			this.panelPrincipal.repaint();
			this.panelGauche.repaint();
			this.panelDroite.repaint();
		}
		
		public void actionPerformed(ActionEvent e) {
			/*for(int i = 0 ; i < textNbVoitures.getText() ; i++){
				addVoiture(new Voiture(1, getStations()[(int)Math.random()*listeStation.size()].getXDepart(), getStations()[(int)Math.random()*listeStation.size()].getYDepart()));
			}*/
			if(e.getSource()==this.recharge) {
				if(!this.textNbVoitures.getText().isEmpty()) {
					if(textNbVoitures.getText().matches("-?\\d+(\\.\\d+)?")) { // on verifie si c'est un entier
						r.recharge(Integer.parseInt(textNbVoitures.getText()));
					System.out.println(this.r.getVoitures().size());
					}
					else {
						//message d'erreur
						JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre de voitures entier et positif");
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre de voitures entier et positif");
				}
				
				
			}
			else if(e.getSource()==this.importMap) {
				this.lecteur.ouvertureFichier();
				if(this.lecteur.file !=null) { // si on a bien choisi un fichier
					this.r.setMapRoute(this.lecteur.traitementFichier());
					this.pan.actualiseMapDesin();
					this.pan.recalculT();	
				}	
			}
				
		}
}
		
		/*public void actionPerformed(ActionEvent e) {
			if(e.getSource()==time) {
				//r.actualiseMapVoiture();
				for(int i=0;i<r.trajectoireVoitures.get(0).size();i++) {
					for(int j=0;j<r.trajectoireVoitures.get(0).get(i).length;j++) {
						System.out.print(r.trajectoireVoitures.get(0).get(i)[j]+" ");
					}
					System.out.println();
				}
				
				r.actualiseMapVoiture();
				
				
				//repaint();
			}
			
		}*/
		

