public class RubiksCube {
    private char[][][] cube;

    public RubiksCube() {
        cube = new char[6][3][3];
        initializeCube();
    }

    private void initializeCube() {
        char[] colors = {'r', 'b', 'o', 'g', 'y', 'w'};
        for (int face = 0; face < 6; face++) {
            char color = colors[face];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    cube[face][row][col] = color;
                }
            }
        }
    }

    public void printCube() {
        for (int face = 0; face < 6; face++) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    System.out.print(cube[face][row][col]);
                    if (col < 2) {
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        cube.printCube();
    }
}
