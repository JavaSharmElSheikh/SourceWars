package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.stages.GameStageManager;
import com.mygdx.game.stages.MenuStage;

public class Game extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	private GameStageManager gsm;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		gsm = new GameStageManager();
		gsm.push(new MenuStage(gsm));
		Gdx.gl.glClearColor(0, 0, 0 ,0);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(spriteBatch);
	}
}
