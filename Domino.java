import java.lang.*;
import java.util.*;

public class Domino
{
	private int		numero			;	// Numéro du domino
	private Case	case1			;	// Nom et nombre de couronnes de la case gauche du domino
	private Case	case2			;	// Nom et nombre de couronnes de la case droite du domino
	private String	roi				;	// Roi Bleu ou Rouge selon qui choisit le domino ( null si pas encore choisit )
	
	public Domino ( Case case1, Case case2, int numero, String roi )
	{
		this.case1			= case1   		;
		this.case2			= case2   		;
		this.numero			= numero 		;
	}
	
	public void ajouterRoi( String roi ) // Ajoute un roi dès que le domino est choisi
	{
		this.roi = roi;
	}
	
	public int		getNumero()			{ return this.numero;		}
	public Case		getCase1()			{ return this.case1;		}
	public Case		getCase2()			{ return this.case2;		}
	public String	getRoi()			{ return this.roi;			}
	
	public String toString()
	{
		String 	s  = "\t+-------+-------+\n";
				s += "\t| " + this.case1 + " | " + this.case2 + " |\t";
				
				if ( this.roi != null )
					s += this.roi;
				s += "\n\t+-------+-------+\n";
				
		return s;
	}
}
