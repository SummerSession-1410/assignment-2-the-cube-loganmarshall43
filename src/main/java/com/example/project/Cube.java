package com.example.project;

import java.util.Scanner;
import java.util.*;
public class TheCube {
    static String[][][] theCUBE = {
            { //top face [0][y][x]
                    {" y ", " y ", " y "},
                    {" y ", " y ", " y "},
                    {" y ", " y ", " y "}
            },
            { //front face [1][y][x]
                    {" b ", " b ", " b "},
                    {" b ", " b ", " b "},
                    {" b ", " b ", " b "}
            },
            { // right face [2][y][x]
                    {" r ", " r ", " r "},
                    {" r ", " r ", " r "},
                    {" r ", " r ", " r "}
            },
            { //back face [3][y][x]
                    {" g ", " g ", " g "},
                    {" g ", " g ", " g "},
                    {" g ", " g ", " g "}
            },
            { //left face [4][y][x]
                    {" o ", " o ", " o "},
                    {" o ", " o ", " o "},
                    {" o ", " o ", " o "}
            },
            { //bottom face [5][y][x]
                    {" w ", " w ", " w "},
                    {" w ", " w ", " w "},
                    {" w ", " w ", " w "}
            }


    };

    static void printCube(String[][][] cube){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    System.out.print(cube[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    static void rotateCube(String[][][] cube, int cubeFaceIndex, boolean clockwise){
        String[][] tempCubeFace = new String [3][3];
        for(int i = 0; i < 3; i++){
            System.arraycopy(theCUBE[cubeFaceIndex][i], 0, tempCubeFace[i], 0, 3);
        }

        if(clockwise){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    cube[cubeFaceIndex][i][j] = tempCubeFace[j][i];
                }
            }
            for(int i = 0; i < 3; i++){
                System.arraycopy(cube[cubeFaceIndex][i], 0, tempCubeFace[i], 0, 3);
            }
            for(int i = 0; i < 3; i++){
                cube[cubeFaceIndex][i][0] = cube[cubeFaceIndex][i][2];
                cube[cubeFaceIndex][i][2] = tempCubeFace[i][0];
            }

            switch(cubeFaceIndex){
                case 0:
                    String[] tempFrontFaceRow = new String[3];
                    System.arraycopy(cube[1][0], 0, tempFrontFaceRow, 0, 3);

                    for(int i = 0; i < 3; i++){
                        cube[1][0][i] = cube[2][0][i];
                        cube[2][0][i] = cube[3][0][i];
                        cube[3][0][i] = cube[4][0][i];
                        cube[4][0][i] = tempFrontFaceRow[i];
                    }

                    break;

                case 1:
                    String[][] tempLeftFace = new String[3][3];
                    for(int i = 0; i < 3; i++){
                        System.arraycopy(cube[4][i], 0, tempLeftFace[i], 0, 3);
                    }

                    cube[4][0][2] = cube[5][0][0];
                    cube[4][1][2] = cube[5][0][1];
                    cube[4][2][2] = cube[5][0][2];

                    cube[5][0][0] = cube[2][2][0];
                    cube[5][0][1] = cube[2][1][0];
                    cube[5][0][2] = cube[2][0][0];

                    cube[2][0][0] = cube[0][2][0];
                    cube[2][1][0] = cube[0][2][1];
                    cube[2][2][0] = cube[0][2][2];

                    cube[0][2][0] = tempLeftFace[2][2];
                    cube[0][2][1] = tempLeftFace[1][2];
                    cube[0][2][2] = tempLeftFace[0][2];

                    break;

                case 2:
                    String[][] tempBottomFace = new String[3][3];
                    for(int i = 0; i < 3; i++){
                        System.arraycopy(cube[5][i], 0, tempBottomFace[i], 0, 3);
                    }
                    cube[5][2][2] = cube[3][0][0];
                    cube[5][1][2] = cube[3][1][0];
                    cube[5][0][2] = cube[3][2][0];

                    cube[3][0][0] = cube[0][2][2];
                    cube[3][1][0] = cube[0][1][2];
                    cube[3][2][0] = cube[0][0][2];

                    cube[0][0][2] = cube[1][0][2];
                    cube[0][1][2] = cube[1][1][2];
                    cube[0][2][2] = cube[1][2][2];

                    cube[1][0][2] = tempBottomFace[0][2];
                    cube[1][1][2] = tempBottomFace[1][2];
                    cube[1][2][2] = tempBottomFace[2][2];

                    break;

                case 3:
                    String[][] tempLeftFaceAgain = new String[3][3];
                    for(int i = 0; i < 3; i++){
                        System.arraycopy(cube[4][i], 0, tempLeftFaceAgain[i], 0, 3);
                    }
                    cube[4][0][0] = cube[0][0][2];
                    cube[4][1][0] = cube[0][0][1];
                    cube[4][2][0] = cube[0][0][0];

                    cube[0][0][0] = cube[2][0][2];
                    cube[0][0][1] = cube[2][1][2];
                    cube[0][0][2] = cube[2][2][2];

                    cube[2][0][2] = cube[5][2][2];
                    cube[2][1][2] = cube[5][2][1];
                    cube[2][2][2] = cube[5][2][0];

                    cube[5][2][0] = tempLeftFaceAgain[0][0];
                    cube[5][2][1] = tempLeftFaceAgain[1][0];
                    cube[5][2][2] = tempLeftFaceAgain[2][0];

                    break;

                case 4:
                    String[][] tempBackFace = new String[3][3];
                    for(int i = 0; i < 3; i++){
                        System.arraycopy(cube[3][i], 0, tempBackFace[i], 0, 3);
                    }
                    cube[3][0][2] = cube[5][2][0];
                    cube[3][1][2] = cube[5][1][0];
                    cube[3][2][2] = cube[5][0][0];

                    cube[5][0][0] = cube[1][0][0] ;
                    cube[5][1][0] = cube[1][1][0];
                    cube[5][2][0] = cube[1][2][0];

                    cube[1][0][0] = cube[0][0][0];
                    cube[1][1][0] = cube[0][1][0];
                    cube[1][2][0] = cube[0][2][0];

                    cube[0][0][0] = tempBackFace[2][2];
                    cube[0][1][0] = tempBackFace[1][2];
                    cube[0][2][0] = tempBackFace[0][2];

                    break;

                case 5:
                    String[][] tempRightFace = new String[3][3];
                    for(int i = 0; i < 3; i++){
                        System.arraycopy(cube[2][i], 0, tempRightFace[i], 0, 3);
                    }
                    cube[2][2][0] = cube[1][2][0];
                    cube[2][2][1] = cube[1][2][1];
                    cube[2][2][2] = cube[1][2][2];

                    cube[1][2][0] = cube[4][2][0];
                    cube[1][2][1] = cube[4][2][1];
                    cube[1][2][2] = cube[4][2][2];

                    cube[4][2][0] = cube[3][2][0];
                    cube[4][2][1] = cube[3][2][1];
                    cube[4][2][2] = cube[3][2][2];

                    cube[3][2][0] = tempRightFace[2][0];
                    cube[3][2][1] = tempRightFace[2][1];
                    cube[3][2][2] = tempRightFace[2][2];

                    break;
            }
        }

        else{
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    cube[cubeFaceIndex][i][j] = tempCubeFace[j][i];
                }
            }

            String[] tempRow = new String[3];
            for(int i = 0; i < 3; i++){
                tempRow[i] = cube[cubeFaceIndex][0][i];
                cube[cubeFaceIndex][0][i] = tempCubeFace[i][2];
                cube[cubeFaceIndex][2][i] = tempRow[i];
            }

            switch(cubeFaceIndex){
                case 0:
                    String[] tempFrontFaceRow = new String[3];
                    System.arraycopy(cube[1][0], 0, tempFrontFaceRow, 0, 3);
                    for(int i = 0; i < 3; i++){
                        cube[1][0][i] = cube[4][0][i];
                        cube[4][0][i] = cube[3][0][i];
                        cube[3][0][i] = cube[2][0][i];
                        cube[2][0][i] = tempFrontFaceRow[i];
                    }

                    break;

                case 1:
                    String[][] tempTopFace = new String[3][3];
                    for(int i = 0; i < 3; i++){
                        System.arraycopy(cube[0][i], 0, tempTopFace[i], 0, 3);
                    }
                    cube[0][2][0] = cube[2][0][0];
                    cube[0][2][1] = cube[2][1][0];
                    cube[0][2][2] = cube[2][2][0];

                    cube[2][0][0] = cube[5][0][2];
                    cube[2][1][0] = cube[5][0][1];
                    cube[2][2][0] = cube[5][0][0];

                    cube[5][0][0] = cube[4][0][2];
                    cube[5][0][1] = cube[4][1][2];
                    cube[5][0][2] = cube[4][2][2];

                    cube[4][0][2] = tempTopFace[2][2];
                    cube[4][1][2] = tempTopFace[2][1];
                    cube[4][2][2] = tempTopFace[2][0];

                    break;

                case 2:
                    String[][] tempTopFaceAgain = new String[3][3];
                    for(int i = 0; i < 3; i++){
                        System.arraycopy(cube[0][i], 0, tempTopFaceAgain[i], 0, 3);
                    }
                    cube[0][0][2] = cube[3][2][0];
                    cube[0][1][2] = cube[3][1][0];
                    cube[0][2][2] = cube[3][0][0];

                    cube[3][0][0] = cube[5][2][2];
                    cube[3][1][0] = cube[5][1][2];
                    cube[3][2][0] = cube[5][0][2];

                    cube[5][0][2] = cube[1][0][2];
                    cube[5][1][2] = cube[1][1][2];
                    cube[5][2][2] = cube[1][2][2];

                    cube[1][0][2] = tempTopFaceAgain[0][2];
                    cube[1][1][2] = tempTopFaceAgain[1][2];
                    cube[1][2][2] = tempTopFaceAgain[2][2];

                    break;

                case 3:
                    String[][] tempRightFace = new String[3][3];
                    for(int i = 0; i < 3; i++){
                        System.arraycopy(cube[2][i], 0, tempRightFace[i], 0, 3);
                    }
                    cube[2][0][2] = cube[0][0][0];
                    cube[2][1][2] = cube[0][0][1];
                    cube[2][2][2] = cube[0][0][2];

                    cube[0][0][0] = cube[4][2][0];
                    cube[0][0][1] = cube[4][1][0];
                    cube[0][0][2] = cube[4][0][0];

                    cube[4][0][0] = cube[5][2][0];
                    cube[4][1][0] = cube[5][2][1];
                    cube[4][2][0] = cube[5][2][2];

                    cube[5][2][0] = tempRightFace[2][2];
                    cube[5][2][1] = tempRightFace[1][2];
                    cube[5][2][2] = tempRightFace[0][2];

                    break;

                case 4:
                    String[][] tempBackFace = new String[3][3];
                    for(int i = 0; i < 3; i++){
                        System.arraycopy(cube[3][i], 0, tempBackFace[i], 0, 3);
                    }

                    cube[3][0][2] = cube[0][2][0];
                    cube[3][1][2] = cube[0][1][0];
                    cube[3][2][2] = cube[0][0][0];

                    cube[0][0][0] = cube[1][0][0];
                    cube[0][1][0] = cube[1][1][0];
                    cube[0][2][0] = cube[1][2][0];

                    cube[1][0][0] = cube[5][0][0];
                    cube[1][1][0] = cube[5][1][0];
                    cube[1][2][0] = cube[5][2][0];

                    cube[5][0][0] = tempBackFace[2][2];
                    cube[5][1][0] = tempBackFace[1][2];
                    cube[5][2][0] = tempBackFace[0][2];

                    break;

                case 5:
                    String[][] tempLeftFace = new String[3][3];
                    for(int i = 0; i < 3; i++){
                        System.arraycopy(cube[4][i], 0, tempLeftFace[i], 0, 3);
                    }
                    cube[4][2][0] = cube[1][2][0];
                    cube[4][2][1] = cube[1][2][1];
                    cube[4][2][2] = cube[1][2][2];

                    cube[1][2][0] = cube[2][2][0];
                    cube[1][2][1] = cube[2][2][1];
                    cube[1][2][2] = cube[2][2][2];

                    cube[2][2][0] = cube[3][2][0];
                    cube[2][2][1] = cube[3][2][1];
                    cube[2][2][2] = cube[3][2][2];

                    cube[3][2][0] = tempLeftFace[2][0];
                    cube[3][2][1] = tempLeftFace[2][1];
                    cube[3][2][2] = tempLeftFace[2][2];

                    break;
            }
        }

    }



    static void printSolution(ArrayList solutionSet){
        System.out.print("Solution: ");

        for(int i = 0; i < solutionSet.size(); i++){
            System.out.print(solutionSet.get(i) + " ");
        }
        System.out.println();
    }

    static void moveItMoveIt(String command){
        switch(command){
            case "U": case "u":
                rotateCube(theCUBE, 0, true);
                break;
            case "U'": case "u'":
                rotateCube(theCUBE, 0, false);
                break;
            case "D": case "d":
                rotateCube(theCUBE, 5, true);
                break;
            case "D'": case "d'":
                rotateCube(theCUBE, 5, false);
                break;
            case "R": case "r":
                rotateCube(theCUBE, 2, true);
                break;
            case "R'": case "r'":
                rotateCube(theCUBE, 2, false);
                break;
            case "L": case "l":
                rotateCube(theCUBE, 4, true);
                break;
            case "L'": case "l'":
                rotateCube(theCUBE, 4, false);
                break;
            case "F": case "f":
                rotateCube(theCUBE, 1, true);
                break;
            case "F'": case "f'":
                rotateCube(theCUBE, 1, false);
                break;
            case "B": case "b":
                rotateCube(theCUBE, 3, true);
                break;
            case "B'": case "b'":
                rotateCube(theCUBE, 3, false);
                break;
            case "X": case "x":
                System.exit(0);
        }
    }

    static String primeConverter(String move){
        if(move.length() > 1){
            return move.substring(0, 1);
        }
        else{
            return move + "'";
        }

    }
    public static void main(String[] args){

        ArrayList<String> solutionList = new ArrayList<>();

        if(args.length > 0){

            for (String arg : args) {
                moveItMoveIt(arg);
                solutionList.add(0, primeConverter(arg));
            }
            printCube(theCUBE);
            printSolution(solutionList);

        }
        else{
            Scanner userInput = new Scanner(System.in);

            while(true){
                String moveCube = userInput.next();
                moveItMoveIt(moveCube);
                solutionList.add(0, primeConverter(moveCube));
                printCube(theCUBE);
                printSolution(solutionList);

            }
        }

    }
}
