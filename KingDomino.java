import java.lang.*;
import iut.algo.*;
import java.util.*;

public class KingDomino
{
	protected static ArrayList<String>  allRois = new ArrayList<String>();	// Rois pour ordre de départ
	
	private	static int nbRois;												// Nombre de Rois
	
	private int			nbJoueurs		;									// Nombre de joueurs
	private int			nbDominos		;									// Nombre de dominos
	private String		player			;									// Joueur a qui c'est le tour de jouer
	private Case[][]	royaumeJoueur1	;									// Plateau du Joueur 1
	private Case[][]	royaumeJoueur2	;									// Plateau du Joueur 2
	
	public KingDomino()
	{
		// Initialisation des plateaux
		this.royaumeJoueur1 = new Case[5][5];
		this.royaumeJoueur2 = new Case[5][5];
		
		// Ajouts des Rois
		KingDomino.allRois.add( "Bleu"	);
		KingDomino.allRois.add( "Rouge"	);
		KingDomino.allRois.add( "Bleu"	);
		KingDomino.allRois.add( "Rouge"	);
		
		this.player			= "Bleu";
		this.nbJoueurs		=  2	;
		this.nbDominos		= 24	;
		KingDomino.nbRois	=  4	;
		
		// Mélange des Rois pour déterminer l'ordre de départ
		Collections.shuffle( KingDomino.allRois );
	}
	
	public void changementJoueur( String Roi )
	{
		if ( Roi.equals( "Bleu" ) )
			this.player = "Bleu";			// au tour du Joueur 2
		else
			this.player = "Rouge";			// au tour du Joueur 1
	}
	
	public String afficherPlateau()
	{	
		String	s	 = "      1     2     3     4     5   \t\t       1     2     3     4     5  \n";
				s	+= "   +-----+-----+-----+-----+-----+\t\t   +-----+-----+-----+-----+-----+\n";
		char cpt	 = '1';
		
		for( int i = 0; i < royaumeJoueur1.length; i++ )
   		{
   			s	+= " " + cpt + " ";
   			
   			// Génération du plateau du Joueur 1
   			for( int j = 0; j < royaumeJoueur1[i].length; j++ )
   			{
   				s += "|";
   				if ( royaumeJoueur1[i][j] == null )
   					s += "     ";						// Case vide
   				else
   					s += royaumeJoueur1[i][j];
   			}
   			
   			s	+= "|\t\t " + cpt + " ";
   			
   			// Génération du plateau du Joueur 2
   			for( int k = 0; k < royaumeJoueur2[i].length; k++ )
   			{
   				s += "|";
   				if ( royaumeJoueur2[i][k] == null )
   					s += "     ";						// Case vide
   				else
   					s += royaumeJoueur2[i][k];
   			}
   			
   			s	+= "|\n";
   			s	+= "   +-----+-----+-----+-----+-----+\t\t   +-----+-----+-----+-----+-----+\n";
   			cpt	 = (char)( (int)(cpt + 1) );
   		}
   		
   		s		+= "            Joueur Bleu           \t\t            Joueur Rouge          \n";
			
		return s;
	}
	
	public boolean placerChateau( int ligne, int colonne )	// place les chateaux des Joueurs où ils souhaitent si possible
	{
		// Test si les coordonnées données sont correctes
		if ( ligne		< 1 || ligne	> 5 ) return false;	
		if ( colonne	< 1 || colonne	> 5 ) return false;
		
		// Diminue les coordonnées de 1 pour correspondre aux tableaux
		ligne--		;
		colonne--	;
		
		// Place chateau du Joueur 1
		if ( this.player.equals( "Bleu" 	) ) royaumeJoueur1[ligne][colonne] = new Case( " ^^^ ", 0 );
		
		// Place chateau du Joueur 2
		if ( this.player.equals( "Rouge"	) ) royaumeJoueur2[ligne][colonne] = new Case( " ^^^ ", 0 );
			
		return true;
		
	}
	
	public boolean placerDomino( Domino domino, int ligne, int colonne, char orientation )	// place les dominos des Joueurs où ils souhaitent si possible sinon ils perdent leur tour
	{
		// Test si les coordonnées données sont correctes
		if ( ligne		< 1 || ligne	> 5 ) return false;
		if ( colonne 	< 1 || colonne	> 5 ) return false;
		
		// Diminue les coordonnées de 1 pour correspondre aux tableaux
		ligne--		;
		colonne--	;
		
		Case[][] royaume = new Case[5][5];
		
		if ( this.player.equals( "Bleu" ) ) royaume = royaumeJoueur1;	// place les dominos sur le plateau du Joueur 1
		else								royaume = royaumeJoueur2;	// place les dominos sur le plateau du Joueur 2
		
		if ( royaume[ligne][colonne] != null ) return false;			// vérifie si la case où le Joueur veut placer le domino est vide
			
		switch ( orientation )
		{
			case 'E' :
				if ( colonne == 4 || royaume[ligne][colonne+1] != null ) return false;

				if ( ligne   > 0	&& domino.getCase1().lierCase( royaume[ligne-1][colonne] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne+1]	= domino.getCase2();
					return true;
				}
				
				if ( colonne > 0	&& domino.getCase1().lierCase( royaume[ligne][colonne-1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne+1]	= domino.getCase2();
					return true;
				}

				if ( ligne   < 4	&& domino.getCase1().lierCase( royaume[ligne+1][colonne] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne+1]	= domino.getCase2();
					return true;
				}
				
				if ( ligne   > 0	&& domino.getCase2().lierCase( royaume[ligne-1][colonne+1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne+1]	= domino.getCase2();
					return true;
				}
				
				if ( colonne < 3	&& domino.getCase2().lierCase( royaume[ligne][colonne+2] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne+1]	= domino.getCase2();
					return true;
				}
				
				if ( ligne   < 4	&& domino.getCase2().lierCase( royaume[ligne+1][colonne+1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne+1]	= domino.getCase2();
					return true;
				}
				
				return false;
		
			case 'O' :
				if ( colonne == 0 || royaume[ligne][colonne-1] != null ) return false;
				
				if ( ligne   > 0	&& domino.getCase1().lierCase( royaume[ligne-1][colonne] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne - 1]	= domino.getCase2();
					return true;
				}
				
				if ( colonne < 4	&& domino.getCase1().lierCase( royaume[ligne][colonne+1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne - 1]	= domino.getCase2();
					return true;
				}

				if ( ligne   < 4	&& domino.getCase1().lierCase( royaume[ligne+1][colonne] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne - 1]	= domino.getCase2();
					return true;
				}
				
				if ( ligne   > 0	&& domino.getCase2().lierCase( royaume[ligne-1][colonne-1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne - 1]	= domino.getCase2();
					return true;
				}
				
				if ( colonne > 1	&& domino.getCase2().lierCase( royaume[ligne][colonne-2] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne - 1]	= domino.getCase2();
					return true;
				}
				
				if ( ligne   < 4	&& domino.getCase2().lierCase( royaume[ligne+1][colonne-1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne][colonne - 1]	= domino.getCase2();
					return true;
				}
				return false;
		
			case 'N' :
				if ( ligne == 0 || royaume[ligne - 1][colonne] != null ) return false;
				
				if ( colonne > 0	&& domino.getCase1().lierCase( royaume[ligne][colonne-1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne - 1][colonne]	= domino.getCase2();
					return true;
				}
				
				if ( ligne	 < 4	&& domino.getCase1().lierCase( royaume[ligne+1][colonne] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne - 1][colonne]	= domino.getCase2();
					return true;
				}

				if ( colonne < 4	&& domino.getCase1().lierCase( royaume[ligne][colonne+1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne - 1][colonne]	= domino.getCase2();
					return true;
				}
				
				if ( colonne > 0	&& domino.getCase2().lierCase( royaume[ligne-1][colonne-1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne - 1][colonne]	= domino.getCase2();
					return true;
				}
				
				if ( colonne < 4	&& domino.getCase2().lierCase( royaume[ligne-1][colonne+1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne - 1][colonne]	= domino.getCase2();
					return true;
				}
				
				if ( ligne   > 1	&& domino.getCase2().lierCase( royaume[ligne-2][colonne] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne - 1][colonne]	= domino.getCase2();
					return true;
				}
				return false;
		
			case 'S' :
				if ( ligne == 4 || royaume[ligne + 1][colonne] != null ) return false;
				
				if ( colonne   > 0	&& domino.getCase1().lierCase( royaume[ligne][colonne-1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne + 1][colonne]	= domino.getCase2();
					return true;
				}
				
				if ( ligne > 0	&& domino.getCase1().lierCase( royaume[ligne-1][colonne] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne + 1][colonne]	= domino.getCase2();
					return true;
				}

				if ( colonne < 4	&& domino.getCase1().lierCase( royaume[ligne][colonne+1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne + 1][colonne]	= domino.getCase2();
					return true;
				}
				
				if ( colonne > 0	&& domino.getCase2().lierCase( royaume[ligne+1][colonne-1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne + 1][colonne]	= domino.getCase2();
					return true;
				}
				
				if ( colonne < 4	&& domino.getCase2().lierCase( royaume[ligne+1][colonne+1] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne + 1][colonne]	= domino.getCase2();
					return true;
				}
				
				if ( ligne   < 3	&& domino.getCase2().lierCase( royaume[ligne+2][colonne] ) )
				{
					royaume[ligne][colonne]		= domino.getCase1();
					royaume[ligne + 1][colonne]	= domino.getCase2();
					return true;
				}
				return false;
		}
				
		return false;
	}
	
	public ArrayList<Case> getVoisins ( String Roi, int i, int j)
	{
		ArrayList<Case> voisins = new ArrayList<Case>();
		Case[][] royaume = new Case[5][5];
		
		if ( Roi.equals( "Bleu" ) ) royaume = royaumeJoueur1;
		else						royaume = royaumeJoueur2;
		
		if ( i<4 && royaume[i+1][j] != null ) voisins.add( royaume[i+1][j] );
		if ( j<4 && royaume[i][j+1] != null ) voisins.add( royaume[i][j+1] );
		if ( i>0 && royaume[i-1][j] != null ) voisins.add( royaume[i-1][j] );
		if ( j>0 && royaume[i][j-1] != null ) voisins.add( royaume[i][j-1] );
		
		return voisins;
	}
	
	public int getScore ( String Roi )
	{
		int score = 0;
		ArrayList<Case> aVisiter = new ArrayList<Case>();
		
		Case[][] royaume = new Case[5][5];
		
		if ( Roi.equals( "Bleu" ) )	royaume = royaumeJoueur1;
		else 						royaume = royaumeJoueur2;
			
		for( int i = 0; i < royaume.length; i++ )
		{
			for( int j = 0; j < royaume[i].length; j++ )
			{
				if( royaume[i][j] != null )
					aVisiter.add( royaume[i][j] );
			}
		}
	
		while ( !aVisiter.isEmpty() )
		{
			Case t = aVisiter.get(0);
		
			ArrayList<Case> biome	= new ArrayList<Case>();
			ArrayList<Case> toVisit	= new ArrayList<Case>();
			toVisit.add( t );
			
			while ( !toVisit.isEmpty() )
			{
				Case u = toVisit.remove(0);
				aVisiter.remove( u );
				toVisit.remove( u );
				biome.add( u );
				
				int l = 0;
				int c = 0;
				
				for( int i = 0; i < royaume.length; i++ )
				{
					for( int j = 0; j < royaume[i].length; j++ )
					{
						if ( royaume[i][j] == u )
						{
							l = i;
							c = j; 
						}
					}
				}
				
				for ( Case v : this.getVoisins( Roi, l, c ) )
				{
					if ( v.getNom() == u.getNom() && aVisiter.contains( v ) )
						toVisit.add( v );
				}
			}
			
			int nbCouronnes = 0;
			
			for ( Case u : biome )
			{
				if(u != null)
					nbCouronnes += u.getNbCouronnes();
			}
			
			score += nbCouronnes * biome.size();
		}
		
		return score;
	}
	
	public int		getNbJoueurs()	{ return this.nbJoueurs	; 	}
	public int		getNbDominos()	{ return this.nbDominos	; 	}
	public String	getPlayer()		{ return this.player	;	}
	public Case	getCase( String roi, int ligne, int colonne )
	{	
		if ( roi.equals( "Bleu"		) ) return royaumeJoueur1[ligne][colonne];	
		if ( roi.equals( "Rouge"	) ) return royaumeJoueur2[ligne][colonne];
			
		return null;
	}
	
	public static String	getRoi1()		{ return KingDomino.allRois.get( 0 );	}
	public static String	getRoi2()		{ return KingDomino.allRois.get( 1 );	}
	public static String	getRoi3()		{ return KingDomino.allRois.get( 2 );	}
	public static String	getRoi4()		{ return KingDomino.allRois.get( 3 );	}
	
	public String toString()
	{
		String	s  = "Nombre de joueurs\t: "	+ this.nbJoueurs			+ "\n";
				s += "Nombre de dominos\t: "	+ this.nbDominos			+ "\n";
				
				s += "Le Joueur 1 est le Joueur Bleu et joue donc avec les Rois Bleus\n"	;
				s += "Le Joueur 2 est le Joueur Rouge et joue donc avec les Rois Rouges\n"	;
			
		return s;
	}
	
	public static void main (String[] args)
	{
		// INITIALISATION PARTIE
		KingDomino partie = new KingDomino();
		System.out.println( partie );
		System.out.println( partie.afficherPlateau() );
		
		
		// PLACEMENT DU CHATEAU DU JOUEUR 1
		int ligne	= 0;
		int colonne = 0;
		
		while ( ligne < 1 || ligne > 5 )
		{
			System.out.print( "Joueur Bleu : choisissez la ligne où placer votre chateau : " );
			ligne = Clavier.lire_int();
		}
			
		while ( colonne < 1 || colonne > 5 )
		{
			System.out.print( "Joueur Bleu : choisissez la colonne où placer votre chateau : " );
			colonne = Clavier.lire_int();
		}
			
		partie.placerChateau( ligne, colonne );
		System.out.println( partie.afficherPlateau() );
		
		
		// PLACEMENT DU CHATEAU DU JOUEUR 2
		partie.changementJoueur( "Rouge" );				// passe du joueur 1 au joueur 2
		
		ligne	= 0;
		colonne = 0;
		
		while ( ligne < 1 || ligne > 5 )
		{
			System.out.print( "Joueur Rouge : choisissez la ligne où placer votre chateau : " );
			ligne = Clavier.lire_int();
		}
			
		while ( colonne < 1 || colonne > 5 )
		{
			System.out.print( "Joueur Rouge : choisissez la colonne où placer votre chateau : " );
			colonne = Clavier.lire_int();
		}
			
		partie.placerChateau( ligne, colonne );
		System.out.println( partie.afficherPlateau() );
		
		
		// AFFICHAGE DES 4 PREMIERS DOMINOS
		PiocheDomino	pioche;
		Domino[]		dominos = new Domino[4];
		
		for ( int i = 0; i < dominos.length; i++ )
			dominos[i] = new Domino( null, null, 0, null );
		
		for ( int nbTours = 0; nbTours < 6; nbTours++ )
		{
			pioche = new PiocheDomino();
			System.out.println( pioche );
			
			int[] choix = new int[4];
		
			for ( int i = 0; i < 4; i++ ) choix[i] = 0;
		
			for ( int cpt = 0; cpt < 4; cpt++ )
			{
				if ( nbTours == 0 )
					partie.changementJoueur( KingDomino.allRois.get( cpt ) );
				else
					partie.changementJoueur( dominos[cpt].getRoi() );
					
				switch ( cpt )
				{
					case 0 :
						while ( choix[cpt] < 1 || choix[cpt] > 4 )
						{
							System.out.print( "Joueur " + partie.getPlayer() + " : choisissez un domino parmi ceux disponibles : " );
							choix[cpt] = Clavier.lire_int();
						}
						break;
				
					case 1 :
						while ( choix[cpt] < 1 || choix[cpt] > 4 || choix[cpt] == choix[0] )
						{
							System.out.print( "Joueur " + partie.getPlayer() + " : choisissez un domino parmi ceux disponibles : " );
							choix[cpt] = Clavier.lire_int();
						}
						break;
					
					case 2 :
						while ( choix[cpt] < 1 || choix[cpt] > 4 || choix[cpt] == choix[0] || choix[cpt] == choix[1] )
						{
							System.out.print( "Joueur " + partie.getPlayer() + " : choisissez un domino parmi ceux disponibles : " );
							choix[cpt] = Clavier.lire_int();
						}
						break;
					
					case 3 :
						while ( choix[cpt] < 1 || choix[cpt] > 4 || choix[cpt] == choix[0] || choix[cpt] == choix[1] || choix[cpt] == choix[2] )
						{
							System.out.print( "Joueur " + partie.getPlayer() + " : choisissez un domino parmi ceux disponibles : " );
							choix[cpt] = Clavier.lire_int();
						}
						break;
						
				}
				
				switch ( choix[cpt] )
				{
					case 1 :
						pioche.getDomino( 0 ).ajouterRoi( partie.getPlayer() );
						dominos[0] = pioche.getDomino( 0 );
					break;
			
					case 2 :
						pioche.getDomino( 1 ).ajouterRoi( partie.getPlayer() );
						dominos[1] = pioche.getDomino( 1 );
					break;
			
					case 3 :
						pioche.getDomino( 2 ).ajouterRoi( partie.getPlayer() );
						dominos[2] = pioche.getDomino( 2 );
					break;
			
					case 4 :
						pioche.getDomino( 3 ).ajouterRoi( partie.getPlayer() );
						dominos[3] = pioche.getDomino( 3 );
					break;
				}
		
				System.out.println( pioche );
			}
			System.out.println( partie.afficherPlateau() );
		
			// PLACEMENT DOMINOS
			char orientation;
		
			for ( int k = 0; k < 4; k++ )
			{
				orientation = 'o'	;
				ligne		= 0		;
				colonne 	= 0		;
				
				System.out.println( pioche.getDomino( k ) );
			
				partie.changementJoueur( pioche.getDomino( k ).getRoi() );
			
				while ( ligne < 1 || ligne > 5 )
				{
					System.out.print( "Joueur " + partie.getPlayer() + " sur quelle ligne voulez-vous placer le domino ci-dessus : " );
					ligne = Clavier.lire_int();
				}
		
				while ( colonne < 1 || colonne > 5 )
				{
					System.out.print( "Quelle colonne : " );
					colonne = Clavier.lire_int();
				}
		
				while ( orientation != 'N' && orientation != 'S' && orientation != 'O' && orientation != 'E' )
				{
					System.out.print( "Quelle orientation ( N, S, O ou E ) : " );
					orientation = Clavier.lire_char();
				}
				
				partie.placerDomino( pioche.getDomino( k ), ligne, colonne, orientation );
				System.out.println( partie.afficherPlateau() );
			}
		}
		
		System.out.println( "Score du Joueur Bleu	: " + partie.getScore( "Bleu"	) );
		System.out.println( "Score du Joueur Rouge	: " + partie.getScore( "Rouge"	) );
		
		if ( partie.getScore( "Bleu" ) > partie.getScore( "Rouge" ) )
			System.out.println( "Le Joueur Bleu remporte la partie"		);
		else
			System.out.println( "Le Joueur Rouge remporte la partie"	);
		
	}	
}
