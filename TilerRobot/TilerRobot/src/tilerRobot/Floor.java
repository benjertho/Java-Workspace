package tilerRobot;

public class Floor {
	private static volatile Floor floorInstance;

	private int DIMENSIONMAX = 1000000;

	private boolean[][] floorGrid;
	private int columnCount;
	private int rowCount;

	public final boolean black = true;
	public final boolean white = false;
	
	private Floor(){
		this(10,10);
	}

	private Floor(int mDimension, int nDimension) {
		if ((mDimension > 2) && (mDimension < DIMENSIONMAX) && (nDimension > 2)
				&& (nDimension < DIMENSIONMAX)) {
			this.rowCount = mDimension;
			this.columnCount = nDimension;
		} else {
			this.rowCount = 10;
			this.columnCount = 10;
		}
		this.floorGrid = new boolean[mDimension][nDimension];
		for (int i = 0; i < this.rowCount; i++) {
			for (int j = 0; j < this.columnCount; j++) {
				floorGrid[i][j] = false;
			}
		}
	}

	public static Floor getFloorInstance() {
		return getFloorInstance(10, 10);
	}
	
	public static Floor getFloorInstance(int rowCount, int columnCount) {
		if (floorInstance == null) {
			synchronized (Floor.class) {
				if (floorInstance == null)
					floorInstance = new Floor(rowCount, columnCount);
			}
		}
		return floorInstance;
	}
	
	public void colorTile(int row, int column, boolean color) {
		if ((row <= this.rowCount) && (row > -1)
				&& (column <= this.columnCount) && (column > -1)) {
			this.floorGrid[row][column] = color;
		}
	}
	
	public void colorTileRect(int startRow, int startColumn, int width, int height){
		if ((width > 1) && (height > 1) && (startRow + height < this.rowCount) && (startColumn + width < this.columnCount)){
			for (int i = startRow; i < startRow + height; i++){
				for (int j = startColumn; j < startColumn + width; j++){
					this.floorGrid[i][j] = true;
				}
			}
		}
	}

	public int[] getRowTileCount() {
		int[] rowTileCount = new int[this.rowCount];
		for (int i = 0; i < this.rowCount; i++) {
			for (int j = 0; j < this.columnCount; j++) {
				if (this.floorGrid[i][j]) {
					rowTileCount[i]++;
				}
			}
		}
		return rowTileCount;
	}

	public int[] getColumnTileCount(){
		int[] columnTileCount = new int[this.columnCount];
		for (int i = 0; i < this.columnCount; i++) {
			for (int j = 0; j < this.rowCount; j++) {
				if (this.floorGrid[j][i]) {
					columnTileCount[i]++;
				}
			}
		}
		return columnTileCount;
	}

	public int getTileCount() {
		int tileCount = 0;
		for (int i = 0; i < this.columnCount; i++) {
			for (int j = 0; j < this.rowCount; j++) {
				if (this.floorGrid[j][i]) {
					tileCount++;
				}
			}
		}
		return tileCount;
	}

	public boolean checkRectanglesCorrect(){
		for (int i = 0; i < this.rowCount; i++){
			for (int j = 0; j < this.columnCount; j++){
				if (this.floorGrid[i][j]){
					if (
							!(checkNeighbors(i, j, false, false, false, false, true, false, true, true)) &&
							!(checkNeighbors(i, j, false, false, false, true, true, true, true, true)) &&
							!(checkNeighbors(i, j, false, false, false, true, false, true, true, false)) &&
							!(checkNeighbors(i, j, false, true, true, false, true, false, true, true)) &&
							!(checkNeighbors(i, j, true, true, true, true, true, true, true, true)) &&
							!(checkNeighbors(i, j, true, true, false, true, false, true, true, false)) &&
							!(checkNeighbors(i, j, false, true, true, false, true, false, false, false)) &&
							!(checkNeighbors(i, j, true, true, true, true, true, false, false, false)) &&
							!(checkNeighbors(i, j, true, true, false, true, false, false, false, false))){
						return false;
						
					}
				}
			}
		}
		return true;
	}
	
	private boolean checkNeighbors(int row, int column, boolean topLeft, boolean topCenter, boolean topRight, boolean middleLeft, boolean middleRight, boolean bottomLeft, boolean bottomCenter, boolean bottomRight){
		if (row > 0){
			if (column > 0){
				if (topLeft != this.floorGrid[row - 1][column - 1]){
					return false;
				}
			}
			if (topCenter != this.floorGrid[row - 1][column]){
				return false;
			}
			if (column < this.columnCount){
				if (topRight != this.floorGrid[row - 1][column + 1]){
					return false;
				}
			}
			
		}
		if (column > 0){
			if (middleLeft != this.floorGrid[row][column - 1]){
				return false;
			}
		}
		if (column < this.columnCount){
			if (middleRight != this.floorGrid[row][column + 1]){
				return false;
			}
		}
		if (row < this.rowCount){
			if (column > 0){
				if (bottomLeft != this.floorGrid[row + 1][column - 1]){
					return false;
				}
			}
			if (bottomCenter != this.floorGrid[row + 1][column]){
				return false;
			}
			if (column < this.columnCount){
				if (bottomRight != this.floorGrid[row + 1][column + 1]){
					return false;
				}
			}
			
		}				
		return true;
	}

	public boolean checkRectClear(int startRow, int startColumn, int width,
			int height) {
		if ((width > 1) && (height > 1) && (startRow + height < this.rowCount)
				&& (startColumn + width < this.columnCount)) {
			if (startRow + height < this.rowCount) {
				height++;
			}
			if (startColumn + width < this.columnCount) {
				width++;
			}
			if (startRow > 0) {
				height++;
				startRow--;
			}
			if (startColumn > 0) {
				width++;
				startColumn--;
			}
			
			for (int i = startRow; i < startRow + height; i++){
				for (int j = startColumn; j < startColumn + width; j++){
					if (this.floorGrid[i][j] == true){
						return false;
					}
				}
			}
		}
		return true;
	}
}
