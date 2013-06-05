package pg13.business.create;

import pg13.models.Puzzle;
import pg13.persistence.PuzzleController;

public class PuzzleCreator {

	private PuzzleController db;

	public PuzzleCreator(){
		this.db = PuzzleController.getInstance();
	}
	
	public void save(Puzzle puzzle){
		this.db.persist(puzzle);
	}
}
