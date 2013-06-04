package pg13.business.create;

import persistence.PuzzleController;
import pg13.models.Puzzle;

public class PuzzleCreator {

	private PuzzleController db;

	public PuzzleCreator(){
		this.db = PuzzleController.getInstance();
	}
	
	public void save(Puzzle puzzle){
		this.db.persist(puzzle);
	}
}
