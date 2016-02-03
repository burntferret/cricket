package com.stag.cricket.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.MainCricket;

public abstract class Entity {

	protected Texture texture;
	protected Vector2 position;
	protected Vector2 direction;
	protected Vector2 center;
	
	public Entity(Texture tex, Vector2 pos, Vector2 dir) {
		this.texture = tex;
		this.position = pos;
		this.direction = dir;
		setCenter();
	}
	
	public Vector2 getPosition() { 
		return this.position;
	}
	
	public abstract void update();
	
	public void render(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, this.position.x, this.position.y);
	}
	
	public void setDirection(float x, float y) {
		if( (y < 0 && this.position.y <= 0) ||
			(y > 0 && this.position.y >= MainCricket.HEIGHT - texture.getHeight())) {
			y = 0;
		}
		
		if( (x < 0 && this.position.x <= 0) ||
			(x > 0 && this.position.x >= MainCricket.WIDTH - texture.getWidth())) {
			x = 0;
		}
		
		this.direction.set(x, y);
		this.direction.scl(Gdx.graphics.getDeltaTime());
	}
	
	public Vector2 getDirection() {
		return this.direction;
	}
	
	public Texture getTexture() { 
		return this.texture;
	}
	
	private void setCenter() {
		float x = this.position.x + this.texture.getWidth()/2;
		float y = this.position.y + this.texture.getHeight()/2;
		this.center = new Vector2(x,y);
	}
	
	public Vector2 getCenter() {
		return this.center;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
}
