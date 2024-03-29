package com.sandman.game.tools;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.sandman.game.Scene.Level;
import com.sandman.game.sprites.Boulder;
import com.sandman.game.sprites.Feuille;
import com.sandman.game.sprites.Tondeuse;
import com.sandman.game.sprites.Water;

public class B2WorldCreatorJardin extends B2WorldCreator{

	//Liste des entités
	private Tondeuse t;
	private ArrayList<Feuille> feuille;
	private ArrayList<Boulder> boulder;
	
	//Compteurs des spawn feuilles/pierres
	private float timerFeuilles;
	private float timerBoulder;
    
	public B2WorldCreatorJardin(World world, TiledMap map,Level level) {
		super(world, map, level);

	    feuille = new ArrayList<Feuille>();
	    timerFeuilles = 0f;
	    
	    boulder = new ArrayList<Boulder>();
	    timerBoulder = 2f;
	    
		//Cree les box2D de l'eau
	    for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
	 		Rectangle rect = ((RectangleMapObject) object).getRectangle();		 	   
			Water w = new Water(world, rect);
			interactiveTiles.add(w);
		}
		
		//Ajout de la tondeuse
		t = new Tondeuse(world);
		interactiveTiles.add(t);
			
	}
	
	public void update(float dt) {
		//Mise à jour des timers
		timerBoulder -= dt;
		timerFeuilles -= dt;
		
		//Gestion des apparitions/suppressions
		gestionFeuilles();
		gestionPierres();
	}
	
	public void gestionFeuilles() {
		//Ajout des feuilles
		if(timerFeuilles <= 0) {
			timerFeuilles = 4f;
			
			//Feuille spawn
			Feuille nf = new Feuille(world, 250, 100);
			feuille.add(nf);
			interactiveTiles.add(nf);
			
			//Feuille avant tondeuse
			nf = new Feuille(world, 932, 100);
			feuille.add(nf);
			interactiveTiles.add(nf);
		}
		
		//Suppression des feuilles
		for(int i = 0; i<feuille.size(); i++) {
			Feuille f = feuille.get(i);
			if(f.getY() < -10) {
				interactiveTiles.remove(f);
				feuille.remove(f);
				i--;
			}
		}
	}
	
	public void gestionPierres() {
		//Ajout des pierres
		if(timerBoulder <= 0) {
			timerBoulder = 2f;
			
			//Pierre spawn
			Boulder nb = new Boulder(world, 690, 110);;
			boulder.add(nb);
			interactiveTiles.add(nb);
			
			//Pierre tondeuse
			nb = new Boulder(world, 1332, 130);;
			boulder.add(nb);
			interactiveTiles.add(nb);
		}
		
		//Suppression des pierres
		for(int i = 0; i<boulder.size(); i++) {
			Boulder b = boulder.get(i);
			if(b.getY() < -10) {
				interactiveTiles.remove(b);
				boulder.remove(b);
				i--;
			}
		}
	}

	public Tondeuse getTondeuse() {
		return t;
	}
	
	public ArrayList<Feuille> getFeuille() {
		return feuille;
	}
	
	public ArrayList<Boulder> getBoulder() {
		return boulder;
	}

	//TODO Faire libération de mémoire si besoin
}
