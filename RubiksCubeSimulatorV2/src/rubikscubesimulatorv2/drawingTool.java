package rubikscubesimulatorv2;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class drawingTool extends JComponent {
    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
    Color newOrange = new Color(240,102,0);
    int count, side;
    int loopNumDet = 0;
    int pieceValue = 0;
    int rectX = 194, rectY = 100;
    int upSide, downSide;
    int[] sideValues;
    int[] side1x1 = {194, 237, 237, 194};
    int[] side1y1 = {100, 125, 175, 150};
    int[] side1x2 = {246, 289, 289, 246};
    int[] side1y2 = {130, 155, 205, 180};
    int[] side1x3 = {298, 341, 341, 298};
    int[] side1y3 = {160, 185, 235, 210};
    int[] side1x4 = {194, 237, 237, 194};
    int[] side1y4 = {160, 185, 235, 210};
    int[] side1x5 = {246, 289, 289, 246};
    int[] side1y5 = {190, 215, 265, 240};
    int[] side1x6 = {298, 341, 341, 298};
    int[] side1y6 = {220, 245, 295, 270};
    int[] side1x7 = {194, 237, 237, 194};
    int[] side1y7 = {220, 245, 295, 270};
    int[] side1x8 = {246, 289, 289, 246};
    int[] side1y8 = {248, 275, 325, 300};
    int[] side1x9 = {298, 341, 341, 298};
    int[] side1y9 = {280, 305, 355, 330};
    int[][] side1x = {side1x1, side1x2, side1x3, side1x4, side1x5, side1x6, side1x7, side1x8, side1x9};
    int[][] side1y = {side1y1, side1y2, side1y3, side1y4, side1y5, side1y6, side1y7, side1y8, side1y9};
    int[] side2x1 = {349, 392, 392, 349};
    int[] side2y1 = {185, 160, 210, 235};
    int[] side2x2 = {401, 444, 444, 401};
    int[] side2y2 = {155, 130, 180, 205};
    int[] side2x3 = {453, 496, 496, 453};
    int[] side2y3 = {125, 100, 150, 175};
    int[] side2x4 = {349, 392, 392, 349};
    int[] side2y4 = {245, 220, 270, 295};
    int[] side2x5 = {401, 444, 444, 401};
    int[] side2y5 = {215, 190, 240, 265};
    int[] side2x6 = {453, 496, 496, 453};
    int[] side2y6 = {185, 160, 210, 235};
    int[] side2x7 = {349, 392, 392, 349};
    int[] side2y7 = {305, 280, 330, 355};
    int[] side2x8 = {401, 444, 444, 401};
    int[] side2y8 = {275, 250, 300, 325};
    int[] side2x9 = {453, 496, 496, 453};
    int[] side2y9 = {245, 220, 270, 295};
    int[][] side2x = {side2x1, side2x2, side2x3, side2x4, side2x5, side2x6, side2x7, side2x8, side2x9};
    int[][] side2y = {side2y1, side2y2, side2y3, side2y4, side2y5, side2y6, side2y7, side2y8, side2y9};
    int[] side3x1 = {198, 241, 284, 241};
    int[] side3y1 = { 93,  70,  93, 118};
    int[] side3x2 = {250, 293, 336, 293};
    int[] side3y2 = {123, 100, 123, 148};
    int[] side3x3 = {302, 345, 388, 345};
    int[] side3y3 = {153, 128, 153, 178};
    int[] side3x4 = {250, 293, 336, 293};
    int[] side3y4 = { 63,  38,  63,  88};
    int[] side3x5 = {302, 345, 388, 345};
    int[] side3y5 = { 93,  68,  93, 118};
    int[] side3x6 = {354, 397, 440, 397};
    int[] side3y6 = {123,  98, 123, 148};
    int[] side3x7 = {302, 345, 388, 345};
    int[] side3y7 = { 33,   8,  33,  58};
    int[] side3x8 = {354, 397, 440, 397};
    int[] side3y8 = { 63,  38,  63,  88};
    int[] side3x9 = {406, 449, 492, 449};
    int[] side3y9 = { 93,  68,  93, 118};
    int[][] side3x = {side3x1, side3x2, side3x3, side3x4, side3x5, side3x6, side3x7, side3x8, side3x9};
    int[][] side3y = {side3y1, side3y2, side3y3, side3y4, side3y5, side3y6, side3y7, side3y8, side3y9};
    boolean firstRun;
    // </editor-fold> // Variables
    
    public drawingTool(int c, int s, int[] sv, boolean fr, int us, int ds)
    {
        count = c;
        side = s;
        sideValues = sv;
        firstRun = fr;
        upSide = us;
        downSide = ds;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        if(side >= 2 && side <= 5) {
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int counter = -1;
            loopNumDet = side * 9;
            for(int i = loopNumDet - 9; i < loopNumDet; i++)
            {
                counter++;
                pieceValue = sideValues[i];
                switch(pieceValue)
                {
                    case 1:
                        g.setColor(Color.yellow);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(newOrange);
                        break;
                    case 4:
                        g.setColor(Color.green);
                        break;
                    case 5:
                        g.setColor(Color.red);
                        break;
                    case 6:
                        g.setColor(Color.white);
                        break;
                }
                g.fillPolygon(side1x[counter], side1y[counter], 4);
            }
            // </editor-fold> // Left Side
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            counter = -1;
            int tempSide = side + 1;
            if(tempSide == 6) {tempSide = 2;}
            loopNumDet = (tempSide) * 9;
            for(int i = loopNumDet - 9; i < loopNumDet; i++)
            {
                counter++;
                pieceValue = sideValues[i];
                switch(pieceValue)
                {
                    case 1:
                        g.setColor(Color.yellow);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(newOrange);
                        break;
                    case 4:
                        g.setColor(Color.green);
                        break;
                    case 5:
                        g.setColor(Color.red);
                        break;
                    case 6:
                        g.setColor(Color.white);
                        break;
                }
                g.fillPolygon(side2x[counter], side2y[counter], 4);
            }
            // </editor-fold> // Right Side
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            counter = -1;
            loopNumDet = 54;
            for(int i = loopNumDet - 9; i < loopNumDet; i++)
            {
                counter++;
                pieceValue = sideValues[i];
                switch(pieceValue)
                {
                    case 1:
                        g.setColor(Color.yellow);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(newOrange);
                        break;
                    case 4:
                        g.setColor(Color.green);
                        break;
                    case 5:
                        g.setColor(Color.red);
                        break;
                    case 6:
                        g.setColor(Color.white);
                        break;
                }
                g.fillPolygon(side3x[counter], side3y[counter], 4);
            }
            // </editor-fold> // Top Side
        } // </editor-fold> // Middle Sides
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(side == 6) {
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int counter = -1;
            loopNumDet = 54;
            for(int i = loopNumDet - 9; i < loopNumDet; i++)
            {
                counter++;
                pieceValue = sideValues[i];
                switch(pieceValue)
                {
                    case 1:
                        g.setColor(Color.yellow);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(newOrange);
                        break;
                    case 4:
                        g.setColor(Color.green);
                        break;
                    case 5:
                        g.setColor(Color.red);
                        break;
                    case 6:
                        g.setColor(Color.white);
                        break;
                }
                g.fillPolygon(side1x[counter], side1y[counter], 4);
            }
            // </editor-fold> // Left Side
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            counter = -1;
            int tempSide = downSide + 2;
            if(tempSide > 5) {
                tempSide -= 4;
            }
            tempSide -= 1;
            if(tempSide == 1) {
                tempSide = 5;
            }
            loopNumDet = (tempSide) * 9;
            for(int i = loopNumDet - 9; i < loopNumDet; i++)
            {
                counter++;
                pieceValue = sideValues[i];
                switch(pieceValue)
                {
                    case 1:
                        g.setColor(Color.yellow);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(newOrange);
                        break;
                    case 4:
                        g.setColor(Color.green);
                        break;
                    case 5:
                        g.setColor(Color.red);
                        break;
                    case 6:
                        g.setColor(Color.white);
                        break;
                }
                g.fillPolygon(side2x[counter], side2y[counter], 4);
            }
            // </editor-fold> // Right Side
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            counter = -1;
            tempSide = downSide + 2;
            if(tempSide > 5) {
                tempSide -= 4;
            }
            loopNumDet = tempSide * 9;
            for(int i = loopNumDet - 9; i < loopNumDet; i++)
            {
                counter++;
                pieceValue = sideValues[i];
                switch(pieceValue)
                {
                    case 1:
                        g.setColor(Color.yellow);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(newOrange);
                        break;
                    case 4:
                        g.setColor(Color.green);
                        break;
                    case 5:
                        g.setColor(Color.red);
                        break;
                    case 6:
                        g.setColor(Color.white);
                        break;
                }
                g.fillPolygon(side3x[counter], side3y[counter], 4);
            }
            // </editor-fold> // Top Side
        } // </editor-fold> // Top Side
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(side == 1) {
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int counter = -1;
            loopNumDet = 9;
            for(int i = loopNumDet - 9; i < loopNumDet; i++)
            {
                counter++;
                pieceValue = sideValues[i];
                switch(pieceValue)
                {
                    case 1:
                        g.setColor(Color.yellow);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(newOrange);
                        break;
                    case 4:
                        g.setColor(Color.green);
                        break;
                    case 5:
                        g.setColor(Color.red);
                        break;
                    case 6:
                        g.setColor(Color.white);
                        break;
                }
                g.fillPolygon(side1x[counter], side1y[counter], 4);
            }
            // </editor-fold> // Left Side
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            counter = -1;
            int tempSide = upSide + 1;
            if(tempSide == 6) {
                tempSide = 2;
            }
            loopNumDet = tempSide * 9;
            for(int i = loopNumDet - 9; i < loopNumDet; i++)
            {
                counter++;
                pieceValue = sideValues[i];
                switch(pieceValue)
                {
                    case 1:
                        g.setColor(Color.yellow);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(newOrange);
                        break;
                    case 4:
                        g.setColor(Color.green);
                        break;
                    case 5:
                        g.setColor(Color.red);
                        break;
                    case 6:
                        g.setColor(Color.white);
                        break;
                }
                g.fillPolygon(side2x[counter], side2y[counter], 4);
            }
            // </editor-fold> // Right Side
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            counter = -1;
            tempSide = upSide;
            loopNumDet = tempSide * 9;
            for(int i = loopNumDet - 9; i < loopNumDet; i++)
            {
                counter++;
                pieceValue = sideValues[i];
                switch(pieceValue)
                {
                    case 1:
                        g.setColor(Color.yellow);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(newOrange);
                        break;
                    case 4:
                        g.setColor(Color.green);
                        break;
                    case 5:
                        g.setColor(Color.red);
                        break;
                    case 6:
                        g.setColor(Color.white);
                        break;
                }
                g.fillPolygon(side3x[counter], side3y[counter], 4);
            }
            // </editor-fold> // Top Side
        } // </editor-fold> // Bottom Side
    }
}