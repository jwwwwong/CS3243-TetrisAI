public class UtilityTester {    
    private void testUtility(Utility utility, TetrisState state) {        
        System.out.println(utility.evaluate(state));
    }
    
    private static int [] getTop(int[][] field) {
        int[] top = new int[TetrisState.COLS];
        for(int i = 0; i < TetrisState.COLS; i++) {
            for(int j = TetrisState.ROWS - 1; j >= 0; j--) {
                if(field[j][i] == 1) {
                    top[i] = j+1;
                    break;
                }
            }
        }
        return top;
    }
    
    private static TetrisState getState1() {    
        TetrisState state = new TetrisState();
        int[][] field = {
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                         };
        
        int[] top = getTop(field);
        state.setField(field);
        state.setNextPiece(6);
        state.setTurnNumber(0);
        state.setTop(top);
        state.makeMove(0);
        return state;
    }
    
    private static int[][] createField(int[] binaryField) {
        int[][] field = new int[TetrisState.ROWS][TetrisState.COLS];
        for(int i = 0; i < TetrisState.ROWS - 1; i++) {
            for(int j = 0; j < TetrisState.COLS; j++) {
                field[i][j] = (binaryField[i] >> j) & 1;
            }
        }
        return field;
    }
    private static TetrisState getState2() {    
        TetrisState state = new TetrisState();
        int[] binaryField = {1007,767,767,1019,992,768,512,512,512,768,768,0,0,0,0,0,0,0,0,0 };
        int[][] field = createField(binaryField);
        
        int[] top = getTop(field);
        state.setField(field);
        state.setNextPiece(6);
        state.setTurnNumber(0);
        state.setTop(top);
        //state.makeMove(16);
        return state;
    }
    
	public static void main(String[] args) {       
        TetrisState state = getState2();
        state.printField();
        UtilityTester tester = new UtilityTester();
        System.out.print("Height ");
        tester.testUtility(new HeightUtility(), state);
        System.out.print("Row Cleared ");
        tester.testUtility(new RowsClearedUtility(), state);
        System.out.print("Row Transitions ");
        tester.testUtility(new RowTransitionsUtility(), state);
        System.out.print("Column Transitions ");
        tester.testUtility(new ColumnTransitionsUtility(), state);
        System.out.print("Hole Count ");
        tester.testUtility(new HoleCountUtility(), state);
        System.out.print("Well Sum ");
        tester.testUtility(new WellSumUtility(), state);
	}
}
