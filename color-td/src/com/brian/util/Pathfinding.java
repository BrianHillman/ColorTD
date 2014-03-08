package com.brian.util;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.brian.actors.GenericTower;

public class Pathfinding {
	public Array<Pair> getPath(Array<Pair> checkpoints, Stage stage){
		Array<Pair> path = new Array<Pair>();
		path.add(checkpoints.get(0));
		for(int x = 1; x < checkpoints.size ; x++ ){
			Array<Pair> tempPath = getPath2points(checkpoints.get(x-1),checkpoints.get(x), stage);
			
			if(tempPath == null) return null;
			
			for(int i = 0; i<tempPath.size; i++){
				path.add(tempPath.get(i));
			}
		}
		
		
		
		return path;
		
	}
	
	private Array<Pair> getPath2points(Pair start, Pair finish,Stage stage){
		//limiting movement. 
		//towers can move 
		//23x16 towers. so we need a 46x32 array to represent all positions.
		Pair[][] grid = new Pair[46][32];
		
		for(int x = 0; x< grid.length;x++){
			for(int y = 0; y < grid[0].length; y++){
				//for each one we get it's center coords. 
				int posX = 8 + (x * 8) + 4;
				int posY = 16 + (y * 8) + 4;
				grid[x][y] = new Pair(posX,posY);
				grid[x][y].visited = false;
				//then we see if they hit a tower
				for(int i = 0; i < stage.getActors().size; i++){
					if(stage.getActors().get(i) instanceof GenericTower){
						if(((GenericTower) stage.getActors().get(i)).name.equalsIgnoreCase("waypoint")) continue;
						Rectangle rect = ((GenericTower) stage.getActors().get(i) ).rect;
						if(rect.contains(posX, posY)){
							grid[x][y].visited = true;
						}
					}
					
				}
			}
		}
		
		//represents the paths
		Queue<Array<Pair>> pathQueue = new LinkedList<Array<Pair>>();
		
		
		Array<Pair> startPoints = new Array<Pair>();
		startPoints.add(new Pair(start.x,start.y));
		pathQueue.add(startPoints);
		
		while(pathQueue.size() != 0){
			Array<Pair> currPath = pathQueue.remove();
			
			if((currPath.get(currPath.size - 1).x == finish.x  )   &&  ( currPath.get(currPath.size - 1).y == finish.y  )){
				//TODO 
				//build path using grid as lookup
				//translate currPath coords to real coords then return that Array of pairs
				Array<Pair> resultPath = new Array<Pair>();
				for(int z = 0; z < currPath.size; z++){
					Pair temp = currPath.get(z);
					Pair translated = new Pair(grid[temp.x][temp.y].x,grid[temp.x][temp.y].y);
					resultPath.add(translated);
				}
				return resultPath;
			}
			
			Array<Pair> unvisitedNeighbors = getUnvisitedNeighbors(grid,currPath.get(currPath.size -1).x,currPath.get(currPath.size -1).y);
			for(int i = 0; i < unvisitedNeighbors.size; i++){
				Array<Pair> newPath = new Array<Pair>();
				//copy over the old path. 
				for(int x = 0; x < currPath.size; x++){
					newPath.add(currPath.get(x));
					
				}
				newPath.add(unvisitedNeighbors.get(i));
				pathQueue.add(newPath);
			}
			
		}
		//pathQueue.add()
		
		
		
		return null;
		
	}
	
	private Array<Pair> getUnvisitedNeighbors(Pair[][] grid,int x, int y){
		Array<Pair> result = new Array<Pair>();
		//above
		if(y < grid[0].length-1){
			if(!grid[x][y+1].visited){
				grid[x][y+1].visited = true;
				result.add(new Pair(x,y+1));
			}
		}
		//below
		if(y > 0){
			if(!grid[x][y-1].visited){
				grid[x][y-1].visited = true;
				result.add(new Pair(x,y-1));
			}
		}
		//left of
		if(x < grid.length-1){
			if(!grid[x+1][y].visited){
				grid[x+1][y].visited = true;
				result.add(new Pair(x+1,y));
			}
		}
		//right of
		if(x > 0){
			if(!grid[x-1][y].visited){
				grid[x-1][y].visited = true;
				result.add(new Pair(x-1,y));
			}
		}
		
		return result;
		
		
	}
	
	
}
