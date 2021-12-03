package com.sandman.game.Scene;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sandman.game.Sandman;
    

public class LevelJardin implements Screen {
    private final Sandman game;

    private OrthographicCamera camera;
    private Viewport gamePort;


    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    private Hud hud;

    public LevelJardin(final Sandman game) {
       this.game = game;

       camera = new OrthographicCamera();

       gamePort = new FitViewport(Sandman.V_WIDTH, Sandman.V_HEIGHT, camera);
       
       hud = new Hud(game.batch);

       maploader = new TmxMapLoader();
       maploader.load("jardin.tmx");
       renderer = new OrthogonalTiledMapRenderer(map);
       camera.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
        
    }
    
    @Override
    public void render(float delta) {

        update(delta);

    	//Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    public void handleInput(float dt) {
    	if(Gdx.input.isTouched()) {
    		camera.position.x += 100 * dt;
    	}
    }    
    //M�thode pour mettre � jour l'�cran et g�rer l'input ca marche po
    public void update(float dt) {
    	handleInput(dt);

    	camera.update();
    	renderer.setView(camera);
    }
    
    @Override
    public void resize(int width, int height) {
    }
    
    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        //rainMusic.play();
    }
    
    @Override
    public void hide() {
    }
    
    @Override
    public void pause() {
    }
    
    @Override
    public void resume() {
    }
    
    @Override
    public void dispose() {
    }
    
}

