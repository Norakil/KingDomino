import java.lang.*;
import java.util.*;

public class Case
{
	private String	nom;
	private int		nbCouronnes;
	
	public Case ( String nom, int nbCouronnes )
	{
		this.nom			= nom;
		this.nbCouronnes	= nbCouronnes;
	}
	
	public String	getNom()		{ return this.nom;			}
	public int		getNbCouronnes(){ return this.nbCouronnes;	}
	
	public boolean lierCase ( Case case0 )	// Verifie si la case du domino est compatible avec celle passée en paramètre
	{
		if ( case0 == null )
			return false;
		
		if ( this.nom.equals( case0.getNom() ) )
			return true;
			
		if ( case0.nom.equals( " ^^^ " ) || this.nom.equals( " ^^^ " ) )
			return true;
			
		return false;
	}
	
	public String toString()
	{
		String s = this.nom;
		
		if ( !this.nom.equals( " ^^^ " ) )
			s += " " + this.nbCouronnes;
		
		return s;
	}
}
