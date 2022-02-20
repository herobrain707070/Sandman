package com.sandman.game.tools;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sandman.game.Sandman;
import com.sandman.game.sprites.Boulder;
import com.sandman.game.sprites.Feuille;
import com.sandman.game.sprites.InteractiveTileObject;
import com.sandman.game.sprites.Tondeuse;
import com.sandman.game.sprites.Water;

public class B2WorldCreator {

	//Liste de toutes nos interactives tiles
    public ArrayList<InteractiveTileObject> interactiveTiles = new ArrayList<InteractiveTileObject>();

	//Liste des entités
	private Tondeuse t;
	private Feuille feuille;
	private Boulder boulder;
    
	public B2WorldCreator(World world, TiledMap map) {
		   BodyDef bdef = new BodyDef();
	       PolygonShape shape = new PolygonShape();
	       FixtureDef fdef = new FixtureDef();
	       Body body;
	       
	       //Cree les box2D du sol
	       for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
	    	   Rectangle rect = ((RectangleMapObject) object).getRectangle();
	    	   
	    	   bdef.type = BodyDef.BodyType.StaticBody;
	    	   bdef.position.set((rect.getX() + rect.getWidth()/2)/Sandman.PPM, (rect.getY() + rect.getHeight()/2)/Sandman.PPM);
	    	   
	    	   body = world.createBody(bdef);
	    	   
	    	   shape.setAsBox((rect.getWidth()/2)/Sandman.PPM, (rect.getHeight()/2)/Sandman.PPM);
	    	   fdef.shape = shape;
	    	   body.createFixture(fdef);
	       }
	    
		    //Cree les box2D de l'eau
		    for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
		 	   Rectangle rect = ((RectangleMapObject) object).getRectangle();
		 	   
		 	   Water w = new Water(world, rect);
		 	   interactiveTiles.add(w);
		    }
			
			//Ajout de la tondeuse
			t = new Tondeuse(world);
			interactiveTiles.add(t);
			
			//Ajout de la feuille
			feuille = new Feuille(world);
			interactiveTiles.add(feuille);
			
			//Ajout pierre
			boulder = new Boulder(world);
			interactiveTiles.add(boulder);
	}

	public Tondeuse getTondeuse() {
		return t;
	}
	
	public Feuille getFeuille() {
		return feuille;
	}
	
	public Boulder getBoulder() {
		return boulder;
	}

	//TODO Faire libération de mémoire si besoin
}
