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
        int[][] field1 = {
                            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                            {0, 1, 1, 0, 0, 0, 1, 0, 1, 1},
                            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
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
        
        int[] top = getTop(field1);
        state.setField(field1);
        state.setNextPiece(6);
        state.setTurnNumber(0);
        state.setTop(top);
        //state.makeMove(16);
        return state;
    }
    
	public static void main(String[] args) {       
        TetrisState state1 = getState1();
        state1.printField();
        UtilityTester tester = new UtilityTester();
        System.out.print("Height ");
        tester.testUtility(new HeightUtility(), state1);
        System.out.print("Row Cleared ");
        tester.testUtility(new RowsClearedUtility(), state1);
        System.out.print("Row Transitions ");
        tester.testUtility(new RowTransitionsUtility(), state1);
        System.out.print("Column Transitions ");
        tester.testUtility(new ColumnTransitionsUtility(), state1);
        System.out.print("Hole Count ");
        tester.testUtility(new HoleCountUtility(), state1);
        System.out.print("Well Sum ");
        tester.testUtility(new WellSumUtility(), state1);
	}
}
