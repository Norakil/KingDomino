import java.lang.*;
import java.util.*;

public class PiocheDomino
{
	protected ArrayList<Domino>  allDominos		= new ArrayList<Domino>() ;
	protected ArrayList<Domino>  dominosTires	= new ArrayList<Domino>() ;
	
	public PiocheDomino()
	{
		this.initPiocheDomino();	// Initialisation de la pioche
		this.trierDominos();
	}

	public void initPiocheDomino()
	{

		// Création des cases
		
		Case cha0 = new Case ( "Cha", 0 );
		Case cha1 = new Case ( "Cha", 1 );
		
		Case for0 = new Case ( "For", 0 );
		Case for1 = new Case ( "For", 1 );
		
		Case lac0 = new Case ( "Lac", 0 );
		Case lac1 = new Case ( "Lac", 1 );
		
		Case mar0 = new Case ( "Mar", 0 );
		Case mar1 = new Case ( "Mar", 1 );
		Case mar2 = new Case ( "Mar", 2 );
		
		Case pla0 = new Case ( "Pla", 0 );
		Case pla1 = new Case ( "Pla", 1 );
		Case pla2 = new Case ( "Pla", 2 );
		
		Case min0 = new Case ( "Min", 0 );
		Case min1 = new Case ( "Min", 1 );
		Case min2 = new Case ( "Min", 2 );
		Case min3 = new Case ( "Min", 3 );
		
		// Création des dominos

		this.allDominos.add( new Domino( cha0, cha0,	 1, "" ) );
		this.allDominos.add( new Domino( cha0, cha0,	 2, "" ) );
		this.allDominos.add( new Domino( for0, for0,	 3, "" ) );
		this.allDominos.add( new Domino( for0, for0,	 4, "" ) );
		this.allDominos.add( new Domino( for0, for0,	 5, "" ) );
		this.allDominos.add( new Domino( for0, for0,	 6, "" ) );
		this.allDominos.add( new Domino( lac0, lac0,	 7, "" ) );
		this.allDominos.add( new Domino( lac0, lac0,	 8, "" ) );
		this.allDominos.add( new Domino( lac0, lac0,	 9, "" ) );
		this.allDominos.add( new Domino( pla0, pla0,	10, "" ) );
		this.allDominos.add( new Domino( pla0, pla0,	11, "" ) );
		this.allDominos.add( new Domino( mar0, mar0,	12, "" ) );
		this.allDominos.add( new Domino( cha0, for0,	13, "" ) );
		this.allDominos.add( new Domino( cha0, lac0,	14, "" ) );
		this.allDominos.add( new Domino( cha0, pla0,	15, "" ) );
		this.allDominos.add( new Domino( cha0, mar0,	16, "" ) );
		this.allDominos.add( new Domino( for0, lac0,	17, "" ) );
		this.allDominos.add( new Domino( for0, pla0,	18, "" ) );
		this.allDominos.add( new Domino( cha1, for0,	19, "" ) );
		this.allDominos.add( new Domino( cha1, lac0,	20, "" ) );
		this.allDominos.add( new Domino( cha1, pla0,	21, "" ) );
		this.allDominos.add( new Domino( cha1, mar0,	22, "" ) );
		this.allDominos.add( new Domino( cha1, min0,	23, "" ) );
		this.allDominos.add( new Domino( for1, cha0,	24, "" ) );
		this.allDominos.add( new Domino( for1, cha0,	25, "" ) );
		this.allDominos.add( new Domino( for1, cha0,	26, "" ) );
		this.allDominos.add( new Domino( for1, cha0,	27, "" ) );
		this.allDominos.add( new Domino( for1, lac0,	28, "" ) );
		this.allDominos.add( new Domino( for1, pla0,	29, "" ) );
		this.allDominos.add( new Domino( lac1, cha0,	30, "" ) );
		this.allDominos.add( new Domino( lac1, cha0,	31, "" ) );
		this.allDominos.add( new Domino( lac1, for0,	32, "" ) );
		this.allDominos.add( new Domino( lac1, for0,	33, "" ) );
		this.allDominos.add( new Domino( lac1, for0,	34, "" ) );
		this.allDominos.add( new Domino( lac1, for0,	35, "" ) );
		this.allDominos.add( new Domino( cha0, pla1,	36, "" ) );
		this.allDominos.add( new Domino( lac0, pla1,	37, "" ) );
		this.allDominos.add( new Domino( cha0, mar1,	38, "" ) );
		this.allDominos.add( new Domino( pla0, mar1,	39, "" ) );
		this.allDominos.add( new Domino( min1, cha0,	40, "" ) );
		this.allDominos.add( new Domino( cha0, pla2,	41, "" ) );
		this.allDominos.add( new Domino( lac0, pla2,	42, "" ) );
		this.allDominos.add( new Domino( cha0, mar2,	43, "" ) );
		this.allDominos.add( new Domino( pla0, mar2,	44, "" ) );
		this.allDominos.add( new Domino( min2, cha0,	45, "" ) );
		this.allDominos.add( new Domino( mar0, min2,	46, "" ) );
		this.allDominos.add( new Domino( mar0, min2,	47, "" ) );
		this.allDominos.add( new Domino( cha0, min3,	48, "" ) );
		
		Collections.shuffle( this.allDominos );		// Mélange des dominos
		
		int cpt = 0;
		
		while (cpt < 24)
		{
			this.allDominos.remove( cpt );			// On enlève 24 dominos
			cpt++;									// car à 2 Joueurs on joue qu'avec 24 dominos
		}
	}
	
	public Domino getDomino( int indice ) { return this.dominosTires.get( indice ); } // tire un domino dans les 4 dominos tirés
	
	public Domino getDominoPioche()					// tire un domino dans la pioche de 24 dominos
	{
		Domino tuile = allDominos.get( 0 );
		allDominos.remove( 0 );
		
		return tuile;
	}
	
	public void trierDominos()		// tire 4 dominos dans la pioche et les trie par ordre croissant
	{
		Domino domino0;
		Domino domino1;
		Domino domino2;
		Domino domino3;
		Domino domino4;
		
		domino1 = this.getDominoPioche();
		domino2 = this.getDominoPioche();
		
		if ( domino2.getNumero() < domino1.getNumero() )
		{
			domino0 = domino1;
			domino1 = domino2;
			domino2 = domino0;
		}
		
		domino3 = this.getDominoPioche();
		
		if ( domino3.getNumero() < domino2.getNumero() )
		{
			if ( domino3.getNumero() < domino1.getNumero() )
			{
				domino0 = domino1;
				domino1 = domino3;
				domino3 = domino0;
			}
			else
			{
				domino0 = domino2;
				domino2 = domino3;
				domino3 = domino0;
			}
		}
		
		domino4 = this.getDominoPioche();
				
		if ( domino4.getNumero() < domino3.getNumero() )
		{
			if ( domino4.getNumero() < domino2.getNumero() )
			{
				if ( domino4.getNumero() < domino1.getNumero() )
				{
					domino0 = domino1;
					domino1 = domino4;
					domino4 = domino0;
				}
				else
				{
					domino0 = domino2;
					domino2 = domino4;
					domino4 = domino0;
				}
			}
			else
			{
				domino0 = domino3;
				domino3 = domino4;
				domino4 = domino0;
			}
		}
		
		// Ajout des dominos tirés dans la liste
		this.dominosTires.add( domino1 );
		this.dominosTires.add( domino2 );
		this.dominosTires.add( domino3 );
		this.dominosTires.add( domino4 );
	}
	
	public String toString()
	{
		String 	s  = "";
				s += "1" + this.dominosTires.get( 0 );
				s += "2" + this.dominosTires.get( 1 );
				s += "3" + this.dominosTires.get( 2 );
				s += "4" + this.dominosTires.get( 3 );
		
		return s;
	}
}
