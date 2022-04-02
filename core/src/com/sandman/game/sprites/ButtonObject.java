package com.sandman.game.sprites;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sandman.game.Sandman;



public class ButtonObject extends InteractiveTileObject{

    //Attribut animation
    private ArrayList<TextureRegion> frameButton;

    //Attribut Etat
    private boolean on;
    private boolean appuie;
    private float tmps;

	//Constructeur
    public ButtonObject(World world, float posX, float posY){
        //Rectangle de positionnement et hitbox du boutton
        super(new TextureRegion(new Texture("images/button.png")),world,new Rectangle(posX, posY, 80, 50), BodyDef.BodyType.StaticBody,5); 
        //Etat initial
        gel = false;
        on = false;
        appuie = false;
        tmps = 1f;
        body.getFixtureList().get(0).setUserData(this);


        //Ajout des frames
        frameButton = new ArrayList<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            frameButton.add(new TextureRegion(getTexture(),i*80,0,80,50));
        }

        setBounds(body.getPosition().x-40/Sandman.PPM, body.getPosition().y, 80/Sandman.PPM, 50/Sandman.PPM);
        setRegion(getFrame());

		// //Création d'une hitbox pour monter sur le cafard
        // FixtureDef fdef = new FixtureDef();
        // PolygonShape danger = new PolygonShape();
        // Vector2 center = new Vector2(0/Sandman.PPM,32/Sandman.PPM);
        // danger.setAsBox((getWidth()/2) - 16/Sandman.PPM , 32/Sandman.PPM,center,0);
        // fdef.shape = danger;
        // body.createFixture(fdef).setUserData(this);

    }

	@Override
	public void onClick() {
		gel = !gel;
	}
	
	public void update(float dt) {
		setRegion(getFrame());
        if(appuie && !gel){
            on = true;
            tmps = 1f;
        }
        if(!appuie && on && !gel) {
            tmps -= dt;
            if (tmps <= 0) {
                on = false;
                tmps = 1f;
            }
        }
	}

    public void pression(){
        appuie = true;

    }

    public void finpression(){
        appuie = false;
    }

    //Retourne la frame à affiché
    private TextureRegion getFrame() {
        if (!gel) {
            if(!on) {
                return frameButton.get(0);
            }
            else{
                return frameButton.get(1);
            }
        }
        else{
            if(!on) {
                return frameButton.get(2);
            }
            else{
                return frameButton.get(3);
            }
        }
    }

}
