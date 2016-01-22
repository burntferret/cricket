package com.stag.cricket.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public abstract class EntityCluster {

	protected Array<Entity> entities;
	
	public EntityCluster(Array<Entity> entities) {
		this.entities = entities;
	}
	
	public abstract void update();
	
	public Array<Entity> getEntities() { 
		return this.entities;
	}
	
	public void setEntities(Array<Entity> entities) {
		this.entities = entities;
	}
	
	public void render(SpriteBatch spriteBatch) {
		for(Entity e : this.entities) {
			e.render(spriteBatch);
		}
	}
	
}
