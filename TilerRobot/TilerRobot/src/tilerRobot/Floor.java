package tilerRobot;
	
public class Floor {
		
	private int DIMENSIONMAX = 1000000;
	
	private boolean[][] floorGrid;
	private int columnCount;
	private int rowCount;
	
	public boolean black = true;
	public boolean white = false;
	
	public Floor(){
		this(10,10);
	}
	
	public Floor(int mDimension, int nDimension){
		if ((mDimension > 2) && (mDimension < DIMENSIONMAX) && (nDimension > 2) && (nDimension < DIMENSIONMAX)){
			this.rowCount = mDimension;
			this.columnCount = nDimension;
		}
		else{
			this.rowCount = 10;
			this.columnCount = 10;
		}
		this.floorGrid = new boolean[mDimension][nDimension];
		for (int i = 0; i<this.rowCount; i++){
			for (int j = 0; j<this.columnCount; j++){
				floorGrid[i][j] = false;
			}
		}
	}
	
	public void colorTile(int row, int column, boolean color){
		if ((row <= this.rowCount) && (row > -1) && (column <= this.columnCount) && (column > -1)){
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
	
	public int[] getRowTileCount(){
		int[] rowTileCount = new int[this.rowCount];
		for (int i = 0; i<this.rowCount; i++){
			for (int j = 0; j<this.columnCount; j++){
				if( this.floorGrid[i][j]){
					rowTileCount[i]++;
				}
			}
		} 
		return 	rowTileCount;
	}

	public int[] getColumnTileCount(){
		int[] columnTileCount = new int[this.columnCount];
		for (int i = 0; i<this.columnCount; i++){
			for (int j = 0; j<this.rowCount; j++){
				if( this.floorGrid[j][i]){
					columnTileCount[i]++;
				}
			}
		} 
		return 	columnTileCount;
	}
	
	public int getTileCount(){
		int tileCount = 0;
		for (int i = 0; i<this.columnCount; i++){
			for (int j = 0; j<this.rowCount; j++){
				if( this.floorGrid[j][i]){
					tileCount++;
				}
			}
		} 
		return 	tileCount;
	}
	
	public boolean checkRectClear(int startRow, int startColumn, int width, int height){
		if ((width > 1) && (height > 1) && (startRow + height < this.rowCount) && (startColumn + width < this.columnCount)){
			if (startRow + height < this.rowCount){
				height++;
			}
			if (startColumn + width < this.columnCount){
				width++;
			}
			if (startRow > 0){
				height++;
				startRow--;
			}
			
			if (startColumn > 0){
				width++;
				startColumn--;
			}
			
			for (int i = startRow; i < startRow + height; i++){
				for (int j = startColumn; i < startColumn + width; i++){
					if (this.floorGrid[i][j] == true){
						return false;
					}
				}
			}
		}
		return true;
	}
}
	
	
