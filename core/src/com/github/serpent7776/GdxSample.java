package com.github.serpent7776;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;

public class GdxSample extends ApplicationAdapter {

	private Stage stage;
	private Pixmap origPixmap;
	private Pixmap thumbPixmap;
	private Texture origTexture;
	private Texture refTexture;
	private Texture thumbTexture;

	@Override
	public void create() {
		Pixmap.setFilter(Pixmap.Filter.BiLinear);
		stage = new Stage();
		origPixmap = new Pixmap(Gdx.files.internal("Mandala-Coloring-Pages-17-1024x1013.jpg"));
		origTexture = new Texture(origPixmap);
		refTexture = new Texture("thumb.png");
		int size = 256;
		thumbPixmap = new Pixmap(size, size, origPixmap.getFormat());
		int srcWidth = origPixmap.getWidth();
		int srcHeight = origPixmap.getHeight();
		Vector2 srcSize = Scaling.fit.apply(size, size, srcWidth, srcHeight);
		int offsetX = Math.round((srcWidth - srcSize.x) / 2.0f);
		int offsetY = Math.round((srcHeight - srcSize.y) / 2.0f);
		thumbPixmap.drawPixmap(origPixmap, offsetX, offsetY, Math.round(srcSize.x), Math.round(srcSize.y), 0, 0, size, size);
		thumbTexture = new Texture(thumbPixmap);
		//create ui
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		Image origImage = new Image(origTexture);
		origImage.setScaling(Scaling.fit);
		table.add(origImage).grow().colspan(2).uniformY();
		table.row();
		Image refImage = new Image(refTexture);
		refImage.setScaling(Scaling.fit);
		table.add(refImage).grow().uniform();
		Image thumbImage = new Image(thumbTexture);
		thumbImage.setScaling(Scaling.fit);
		table.add(thumbImage).grow().uniform();
		table.row();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		origTexture.dispose();
		refTexture.dispose();
		thumbTexture.dispose();
		origPixmap.dispose();
		thumbPixmap.dispose();
		stage.dispose();
	}

}
