package rubikscubesimulatorv2;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

public class Cube extends JComponent {
    
    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
    GUI myGUI;
    final int startSide = 2;
    int[] sideValues = new int[54];
    int[] storingValues = new int[54];
    int[] movesMade = new int[10000];
    static double[] trialTimes = new double[30000];
    static int trialCount = -1, iterationCount = -1;
    int newMV = 0;
    int movesMadeCount = 0;
    int store1, store2, store3, store4, store5, store6, store7, store8;
    int upSide, downSide, origSide = 2, manipSide = 2, otherSide = 2, turnside = 0, side = 2, moveUpSide = 6, moveDownSide = 1;
    int count = 0, lastMove = 0, redoMove = 0, makeThisMove = 0, neitherCount = 0;
    int woyrAxis = 0, bogrAxis = 1, wbygAxis = 2, lastAxis = 1;
    int numTimes1 = 0;
    int numTimes2 = 0;
    int numTimes3 = 0;
    int methodValue = 0;
    int lag = 0;
    static int recognitionCounter = 0;
    boolean firstRun = true;
    boolean startActivated = false;
    boolean allowedToRun = true;
    boolean currentlyRunning = false;
    boolean scrambleFinished = false;
    // </editor-fold> // Variables 
    
    public Cube(GUI g)
    {
        myGUI = g;
        upSide = 6;
        downSide = 1;
        for(count = 0; count < 54; count++)
	{
            if(count < 9)
            {
		sideValues[count] = 1;
            }
            else if(count >= 9 && count < 18)
            {
		sideValues[count] = 2;
            }
            else if(count >= 18 && count < 27)
            {
		sideValues[count] = 3;
            }
            else if(count >= 27 && count < 36)
            {
		sideValues[count] = 4;
            }
            else if(count >= 36 && count < 45)
            {
		sideValues[count] = 5;
            }
            else
            {
            	sideValues[count] = 6;
            }
	}
    }
    public void Reset()
    {
        for(count = 0; count < 54; count++)
	{
            if(count < 9)
            {
		sideValues[count] = 1;
            }
            else if(count >= 9 && count < 18)
            {
		sideValues[count] = 2;
            }
            else if(count >= 18 && count < 27)
            {
		sideValues[count] = 3;
            }
            else if(count >= 27 && count < 36)
            {
		sideValues[count] = 4;
            }
            else if(count >= 36 && count < 45)
            {
		sideValues[count] = 5;
            }
            else
            {
            	sideValues[count] = 6;
            }
	}
    }
    
    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
    public void Lprime()
    {
	otherSide = startSide;
	origSide = startSide;
			
	moveUpSide = 6;
	store1 = sideValues[otherSide * 9 - 9];
	store2 = sideValues[otherSide * 9 - 6];
	store3 = sideValues[otherSide * 9 - 3];
	store4 = sideValues[moveUpSide * 9 - 9];
	store5 = sideValues[moveUpSide * 9 - 6];
	store6 = sideValues[moveUpSide * 9 - 3];
	sideValues[moveUpSide * 9 - 9] = store1;
	sideValues[moveUpSide * 9 - 6] = store2;
	sideValues[moveUpSide * 9 - 3] = store3;
	
	otherSide = origSide + 2;
	if(otherSide > 5)
            otherSide -= 4;
	moveUpSide = 1;
	store1 = sideValues[otherSide * 9 - 7];
	store2 = sideValues[otherSide * 9 - 4];
	store3 = sideValues[otherSide * 9 - 1];
	sideValues[otherSide * 9 - 1] = store4;
	sideValues[otherSide * 9 - 4] = store5;
	sideValues[otherSide * 9 - 7] = store6;
		
	otherSide = 1;
	store4 = sideValues[otherSide * 9 - 7];
	store5 = sideValues[otherSide * 9 - 4];
	store6 = sideValues[otherSide * 9 - 1];
	sideValues[otherSide * 9 - 7] = store1;
	sideValues[otherSide * 9 - 4] = store2;
	sideValues[otherSide * 9 - 1] = store3;
		
	otherSide = origSide;
	sideValues[otherSide * 9 - 3] = store4;
	sideValues[otherSide * 9 - 6] = store5;
	sideValues[otherSide * 9 - 9] = store6;
	otherSide = startSide;
	
	turnside = 5;
        lastMove = 1;
	this.CounterClockwise();
        //myGUI.refresh(this.draw());
    }
    public void L()
    {
	otherSide = startSide;
	origSide = startSide;
	
	moveDownSide = 1;
	store1 = sideValues[otherSide * 9 - 9];
	store2 = sideValues[otherSide * 9 - 6];
	store3 = sideValues[otherSide * 9 - 3];
	store4 = sideValues[moveDownSide * 9 - 7];
	store5 = sideValues[moveDownSide * 9 - 4];
	store6 = sideValues[moveDownSide * 9 - 1];
	sideValues[moveDownSide * 9 - 1] = store1;
	sideValues[moveDownSide * 9 - 4] = store2;
	sideValues[moveDownSide * 9 - 7] = store3;
	
	otherSide = origSide + 2;
	if(otherSide > 5)
		otherSide -= 4;
	store1 = sideValues[otherSide * 9 - 7];
	store2 = sideValues[otherSide * 9 - 4];
	store3 = sideValues[otherSide * 9 - 1];
	sideValues[otherSide * 9 - 7] = store4;
	sideValues[otherSide * 9 - 4] = store5;
	sideValues[otherSide * 9 - 1] = store6;
	
	otherSide = 6;
	store4 = sideValues[otherSide * 9 - 9];
	store5 = sideValues[otherSide * 9 - 6];
	store6 = sideValues[otherSide * 9 - 3];
	sideValues[otherSide * 9 - 3] = store1;
	sideValues[otherSide * 9 - 6] = store2;
	sideValues[otherSide * 9 - 9] = store3;
	
	otherSide = origSide;
	sideValues[otherSide * 9 - 9] = store4;
	sideValues[otherSide * 9 - 6] = store5;
	sideValues[otherSide * 9 - 3] = store6;
	otherSide = startSide;
	
	turnside = 5;
        lastMove = 2;
	this.Clockwise();
        //myGUI.refresh(this.draw());
    }
    public void Rprime()
    {
	otherSide = startSide;
	origSide = startSide;
	
	otherSide += 2;
	if(otherSide > 5)
		otherSide -= 4;
	origSide = otherSide;
	moveUpSide = 6;
	store1 = sideValues[otherSide * 9 - 9];
	store2 = sideValues[otherSide * 9 - 6];
	store3 = sideValues[otherSide * 9 - 3];
	store4 = sideValues[moveUpSide * 9 - 1];
	store5 = sideValues[moveUpSide * 9 - 4];
	store6 = sideValues[moveUpSide * 9 - 7];
	sideValues[moveUpSide * 9 - 1] = store1;
	sideValues[moveUpSide * 9 - 4] = store2;
	sideValues[moveUpSide * 9 - 7] = store3;
	
	otherSide = origSide + 2;
	if(otherSide > 5)
		otherSide -= 4;
	moveUpSide = 1;
	store1 = sideValues[otherSide * 9 - 1];
	store2 = sideValues[otherSide * 9 - 4];
	store3 = sideValues[otherSide * 9 - 7];
	sideValues[otherSide * 9 - 1] = store4;
	sideValues[otherSide * 9 - 4] = store5;
	sideValues[otherSide * 9 - 7] = store6;
	
	otherSide = 1;
	store4 = sideValues[otherSide * 9 - 3];
	store5 = sideValues[otherSide * 9 - 6];
	store6 = sideValues[otherSide * 9 - 9];
	sideValues[otherSide * 9 - 9] = store1;
	sideValues[otherSide * 9 - 6] = store2;
	sideValues[otherSide * 9 - 3] = store3;
	
	otherSide = origSide;
	sideValues[otherSide * 9 - 3] = store4;
	sideValues[otherSide * 9 - 6] = store5;
	sideValues[otherSide * 9 - 9] = store6;
	otherSide = startSide;
	
	turnside = 3;
        lastMove = 3;
	this.CounterClockwise();
        //myGUI.refresh(this.draw());
}
    public void R()
    {
	otherSide = startSide;
	origSide = startSide;
	
	otherSide += 2;
	if(otherSide > 5)
		otherSide -= 4;
	origSide = otherSide;
	moveDownSide = 1;
	store1 = sideValues[otherSide * 9 - 9];
	store2 = sideValues[otherSide * 9 - 6];
	store3 = sideValues[otherSide * 9 - 3];
	store4 = sideValues[moveDownSide * 9 - 9];
	store5 = sideValues[moveDownSide * 9 - 6];
	store6 = sideValues[moveDownSide * 9 - 3];
	sideValues[moveDownSide * 9 - 9] = store1;
	sideValues[moveDownSide * 9 - 6] = store2;
	sideValues[moveDownSide * 9 - 3] = store3;
	
	otherSide = origSide + 2;
	if(otherSide > 5)
		otherSide -= 4;
	store1 = sideValues[otherSide * 9 - 7];
	store2 = sideValues[otherSide * 9 - 4];
	store3 = sideValues[otherSide * 9 - 1];
	sideValues[otherSide * 9 - 1] = store4;
	sideValues[otherSide * 9 - 4] = store5;
	sideValues[otherSide * 9 - 7] = store6;
	
	otherSide = 6;
	store4 = sideValues[otherSide * 9 - 7];
	store5 = sideValues[otherSide * 9 - 4];
	store6 = sideValues[otherSide * 9 - 1];
	sideValues[otherSide * 9 - 7] = store1;
	sideValues[otherSide * 9 - 4] = store2;
	sideValues[otherSide * 9 - 1] = store3;
	
	otherSide = origSide;
	sideValues[otherSide * 9 - 3] = store4;
	sideValues[otherSide * 9 - 6] = store5;
	sideValues[otherSide * 9 - 9] = store6;
	otherSide = startSide;
	
	turnside = 3;
        lastMove = 4;
	this.Clockwise();
        //myGUI.refresh(this.draw());
    }
    public void Fprime()   
    {
	otherSide = 3;
	origSide = otherSide;
	
	moveUpSide = 6;
	store1 = sideValues[otherSide * 9 - 9];
	store2 = sideValues[otherSide * 9 - 6];
	store3 = sideValues[otherSide * 9 - 3];
	store4 = sideValues[moveUpSide * 9 - 3];
	store5 = sideValues[moveUpSide * 9 - 2];
	store6 = sideValues[moveUpSide * 9 - 1];
	sideValues[moveUpSide * 9 - 3] = store1;
	sideValues[moveUpSide * 9 - 2] = store2;
	sideValues[moveUpSide * 9 - 1] = store3;
	
	otherSide = 5;
	store1 = sideValues[otherSide * 9 - 1];
	store2 = sideValues[otherSide * 9 - 4];
	store3 = sideValues[otherSide * 9 - 7];
	sideValues[otherSide * 9 - 1] = store4;
        sideValues[otherSide * 9 - 4] = store5;
	sideValues[otherSide * 9 - 7] = store6;
	
	otherSide = 1;
	store4 = sideValues[otherSide * 9 - 3];
	store5 = sideValues[otherSide * 9 - 2];
	store6 = sideValues[otherSide * 9 - 1];
	sideValues[otherSide * 9 - 3] = store1;
	sideValues[otherSide * 9 - 2] = store2;
	sideValues[otherSide * 9 - 1] = store3;
	
	otherSide = 3;
	sideValues[otherSide * 9 - 9] = store4;
	sideValues[otherSide * 9 - 6] = store5;
	sideValues[otherSide * 9 - 3] = store6;
	otherSide = startSide;
	
	turnside = 2;
        lastMove = 5;
	this.CounterClockwise();
        //myGUI.refresh(this.draw());
    }
    public void F()
    {
	otherSide = 3;
	origSide = otherSide;
	
	moveDownSide = 1;
	store1 = sideValues[otherSide * 9 - 9];
	store2 = sideValues[otherSide * 9 - 6];
	store3 = sideValues[otherSide * 9 - 3];
	store4 = sideValues[moveDownSide * 9 - 1];
	store5 = sideValues[moveDownSide * 9 - 2];
	store6 = sideValues[moveDownSide * 9 - 3];
	sideValues[moveDownSide * 9 - 3] = store1;
	sideValues[moveDownSide * 9 - 2] = store2;
	sideValues[moveDownSide * 9 - 1] = store3;
	
	otherSide = 5;
	store1 = sideValues[otherSide * 9 - 1];
	store2 = sideValues[otherSide * 9 - 4];
	store3 = sideValues[otherSide * 9 - 7];
	sideValues[otherSide * 9 - 7] = store4;
	sideValues[otherSide * 9 - 4] = store5;
	sideValues[otherSide * 9 - 1] = store6;
	
	otherSide = 6;
	store4 = sideValues[otherSide * 9 - 3];
	store5 = sideValues[otherSide * 9 - 2];
	store6 = sideValues[otherSide * 9 - 1];
	sideValues[otherSide * 9 - 3] = store1;
	sideValues[otherSide * 9 - 2] = store2;
	sideValues[otherSide * 9 - 1] = store3;
	
	otherSide = 3;
	sideValues[otherSide * 9 - 9] = store4;
	sideValues[otherSide * 9 - 6] = store5;
	sideValues[otherSide * 9 - 3] = store6;
	otherSide = startSide;
	
	turnside = 2;
        lastMove = 6;
	this.Clockwise();
        //myGUI.refresh(this.draw());
    }
    public void Bprime()
    {
	otherSide = 5;
	origSide = otherSide;
	
	moveUpSide = 6;
	store1 = sideValues[otherSide * 9 - 9];
	store2 = sideValues[otherSide * 9 - 6];
	store3 = sideValues[otherSide * 9 - 3];
	store4 = sideValues[moveUpSide * 9 - 7];
	store5 = sideValues[moveUpSide * 9 - 8];
	store6 = sideValues[moveUpSide * 9 - 9];
	sideValues[moveUpSide * 9 - 7] = store1;
	sideValues[moveUpSide * 9 - 8] = store2;
	sideValues[moveUpSide * 9 - 9] = store3;
	
	otherSide = 3;
	store1 = sideValues[otherSide * 9 - 1];
	store2 = sideValues[otherSide * 9 - 4];
	store3 = sideValues[otherSide * 9 - 7];
	sideValues[otherSide * 9 - 1] = store4;
	sideValues[otherSide * 9 - 4] = store5;
	sideValues[otherSide * 9 - 7] = store6;
	
	otherSide = 1;
	store4 = sideValues[otherSide * 9 - 7];
	store5 = sideValues[otherSide * 9 - 8];
	store6 = sideValues[otherSide * 9 - 9];
	sideValues[otherSide * 9 - 7] = store1;
	sideValues[otherSide * 9 - 8] = store2;
	sideValues[otherSide * 9 - 9] = store3;
	
	otherSide = 5;
	sideValues[otherSide * 9 - 9] = store4;
	sideValues[otherSide * 9 - 6] = store5;
	sideValues[otherSide * 9 - 3] = store6;
	otherSide = startSide;
	
	turnside = 4;
        lastMove = 7;
	this.CounterClockwise();
        //myGUI.refresh(this.draw());
    }
    public void B()
    {
        otherSide = 5;
        origSide = otherSide;

        moveDownSide = 1;
        store1 = sideValues[otherSide * 9 - 9];
        store2 = sideValues[otherSide * 9 - 6];
        store3 = sideValues[otherSide * 9 - 3];
        store4 = sideValues[moveDownSide * 9 - 9];
        store5 = sideValues[moveDownSide * 9 - 8];
        store6 = sideValues[moveDownSide * 9 - 7];
        sideValues[moveDownSide * 9 - 7] = store1;
        sideValues[moveDownSide * 9 - 8] = store2;
        sideValues[moveDownSide * 9 - 9] = store3;

        otherSide = 3;
        store1 = sideValues[otherSide * 9 - 7];
        store2 = sideValues[otherSide * 9 - 4];
        store3 = sideValues[otherSide * 9 - 1];
        sideValues[otherSide * 9 - 7] = store4;
        sideValues[otherSide * 9 - 4] = store5;
        sideValues[otherSide * 9 - 1] = store6;

        otherSide = 6;
        store4 = sideValues[otherSide * 9 - 7];
        store5 = sideValues[otherSide * 9 - 8];
        store6 = sideValues[otherSide * 9 - 9];
        sideValues[otherSide * 9 - 9] = store1;
        sideValues[otherSide * 9 - 8] = store2;
        sideValues[otherSide * 9 - 7] = store3;

        otherSide = origSide;
        sideValues[otherSide * 9 - 9] = store4;
        sideValues[otherSide * 9 - 6] = store5;
        sideValues[otherSide * 9 - 3] = store6;
        otherSide = startSide;

        turnside = 4;
        lastMove = 8;
        this.Clockwise();
        //myGUI.refresh(this.draw());
    }
    public void Uprime()
    {
        otherSide = 2;
        origSide = otherSide;

        moveUpSide = 3;
        store1 = sideValues[otherSide * 9 - 9];
        store2 = sideValues[otherSide * 9 - 8];
        store3 = sideValues[otherSide * 9 - 7];
        store4 = sideValues[moveUpSide * 9 - 9];
        store5 = sideValues[moveUpSide * 9 - 8];
        store6 = sideValues[moveUpSide * 9 - 7];
        sideValues[moveUpSide * 9 - 9] = store1;
        sideValues[moveUpSide * 9 - 8] = store2;
        sideValues[moveUpSide * 9 - 7] = store3;

        otherSide = 4;
        store1 = sideValues[otherSide * 9 - 9];
        store2 = sideValues[otherSide * 9 - 8];
        store3 = sideValues[otherSide * 9 - 7];
        sideValues[otherSide * 9 - 9] = store4;
        sideValues[otherSide * 9 - 8] = store5;
        sideValues[otherSide * 9 - 7] = store6;

        otherSide = 5;
        store4 = sideValues[otherSide * 9 - 9];
        store5 = sideValues[otherSide * 9 - 8];
        store6 = sideValues[otherSide * 9 - 7];
        sideValues[otherSide * 9 - 9] = store1;
        sideValues[otherSide * 9 - 8] = store2;
        sideValues[otherSide * 9 - 7] = store3;

        otherSide = 2;
        sideValues[otherSide * 9 - 9] = store4;
        sideValues[otherSide * 9 - 8] = store5;
        sideValues[otherSide * 9 - 7] = store6;
        otherSide = startSide;

        turnside = 6;
        lastMove = 9;
        this.CounterClockwise();
        //myGUI.refresh(this.draw());
    }
    public void U()
    {
        otherSide = 2;
        origSide = otherSide;

        moveUpSide = 5;
        store1 = sideValues[otherSide * 9 - 9];
        store2 = sideValues[otherSide * 9 - 8];
        store3 = sideValues[otherSide * 9 - 7];
        store4 = sideValues[moveUpSide * 9 - 9];
        store5 = sideValues[moveUpSide * 9 - 8];
        store6 = sideValues[moveUpSide * 9 - 7];
        sideValues[moveUpSide * 9 - 9] = store1;
        sideValues[moveUpSide * 9 - 8] = store2;
        sideValues[moveUpSide * 9 - 7] = store3;

        otherSide = 4;
        store1 = sideValues[otherSide * 9 - 9];
        store2 = sideValues[otherSide * 9 - 8];
        store3 = sideValues[otherSide * 9 - 7];
        sideValues[otherSide * 9 - 9] = store4;
        sideValues[otherSide * 9 - 8] = store5;
        sideValues[otherSide * 9 - 7] = store6;

        otherSide = 3;
        store4 = sideValues[otherSide * 9 - 9];
        store5 = sideValues[otherSide * 9 - 8];
        store6 = sideValues[otherSide * 9 - 7];
        sideValues[otherSide * 9 - 9] = store1;
        sideValues[otherSide * 9 - 8] = store2;
        sideValues[otherSide * 9 - 7] = store3;

        otherSide = 2;
        sideValues[otherSide * 9 - 9] = store4;
        sideValues[otherSide * 9 - 8] = store5;
        sideValues[otherSide * 9 - 7] = store6;
        otherSide = startSide;

        turnside = 6;
        lastMove = 10;
        this.Clockwise();
        //myGUI.refresh(this.draw());
    }
    public void Dprime()
    {
        otherSide = 2;
        origSide = otherSide;

        moveUpSide = 5;
        store1 = sideValues[otherSide * 9 - 3];
        store2 = sideValues[otherSide * 9 - 2];
        store3 = sideValues[otherSide * 9 - 1];
        store4 = sideValues[moveUpSide * 9 - 3];
        store5 = sideValues[moveUpSide * 9 - 2];
        store6 = sideValues[moveUpSide * 9 - 1];
        sideValues[moveUpSide * 9 - 3] = store1;
        sideValues[moveUpSide * 9 - 2] = store2;
        sideValues[moveUpSide * 9 - 1] = store3;

        otherSide = 4;
        store1 = sideValues[otherSide * 9 - 3];
        store2 = sideValues[otherSide * 9 - 2];
        store3 = sideValues[otherSide * 9 - 1];
        sideValues[otherSide * 9 - 3] = store4;
        sideValues[otherSide * 9 - 2] = store5;
        sideValues[otherSide * 9 - 1] = store6;

        otherSide = 3;
        store4 = sideValues[otherSide * 9 - 3];
        store5 = sideValues[otherSide * 9 - 2];
        store6 = sideValues[otherSide * 9 - 1];
        sideValues[otherSide * 9 - 3] = store1;
        sideValues[otherSide * 9 - 2] = store2;
        sideValues[otherSide * 9 - 1] = store3;

        otherSide = 2;
        sideValues[otherSide * 9 - 3] = store4;
        sideValues[otherSide * 9 - 2] = store5;
        sideValues[otherSide * 9 - 1] = store6;
        otherSide = startSide;

        turnside = 1;
        lastMove = 11;
        this.CounterClockwise();
        //myGUI.refresh(this.draw());
    }
    public void D()
    {
        otherSide = 2;
        origSide = otherSide;

        moveUpSide = 3;
        store1 = sideValues[otherSide * 9 - 3];
        store2 = sideValues[otherSide * 9 - 2];
        store3 = sideValues[otherSide * 9 - 1];
        store4 = sideValues[moveUpSide * 9 - 3];
        store5 = sideValues[moveUpSide * 9 - 2];
        store6 = sideValues[moveUpSide * 9 - 1];
        sideValues[moveUpSide * 9 - 3] = store1;
        sideValues[moveUpSide * 9 - 2] = store2;
        sideValues[moveUpSide * 9 - 1] = store3;

        otherSide = 4;
        store1 = sideValues[otherSide * 9 - 3];
        store2 = sideValues[otherSide * 9 - 2];
        store3 = sideValues[otherSide * 9 - 1];
        sideValues[otherSide * 9 - 3] = store4;
        sideValues[otherSide * 9 - 2] = store5;
        sideValues[otherSide * 9 - 1] = store6;

        otherSide = 5;
        store4 = sideValues[otherSide * 9 - 3];
        store5 = sideValues[otherSide * 9 - 2];
        store6 = sideValues[otherSide * 9 - 1];
        sideValues[otherSide * 9 - 3] = store1;
        sideValues[otherSide * 9 - 2] = store2;
        sideValues[otherSide * 9 - 1] = store3;

        otherSide = 2;
        sideValues[otherSide * 9 - 3] = store4;
        sideValues[otherSide * 9 - 2] = store5;
        sideValues[otherSide * 9 - 1] = store6;
        otherSide = startSide;

        turnside = 1;
        lastMove = 12;
        this.Clockwise();
        //myGUI.refresh(this.draw());
    }
    public void CounterClockwise()
    {
        store1 = sideValues[turnside*9 - 9];
        store2 = sideValues[turnside*9 - 8];
        store3 = sideValues[turnside*9 - 7];
        store4 = sideValues[turnside*9 - 6];
        store5 = sideValues[turnside*9 - 4];
        store6 = sideValues[turnside*9 - 3];
        store7 = sideValues[turnside*9 - 2];
        store8 = sideValues[turnside*9 - 1];
        sideValues[turnside*9 - 9] = store3;
        sideValues[turnside*9 - 8] = store5;
        sideValues[turnside*9 - 7] = store8;
        sideValues[turnside*9 - 6] = store2;
        sideValues[turnside*9 - 4] = store7;
        sideValues[turnside*9 - 3] = store1;
        sideValues[turnside*9 - 2] = store4;
        sideValues[turnside*9 - 1] = store6;
    }
    public void Clockwise()
    {
        store1 = sideValues[turnside*9 - 9];
        store2 = sideValues[turnside*9 - 8];
        store3 = sideValues[turnside*9 - 7];
        store4 = sideValues[turnside*9 - 6];
        store5 = sideValues[turnside*9 - 4];
        store6 = sideValues[turnside*9 - 3];
        store7 = sideValues[turnside*9 - 2];
        store8 = sideValues[turnside*9 - 1];
        sideValues[turnside*9 - 9] = store6;
        sideValues[turnside*9 - 8] = store4;
        sideValues[turnside*9 - 7] = store1;
        sideValues[turnside*9 - 6] = store7;
        sideValues[turnside*9 - 4] = store2;
        sideValues[turnside*9 - 3] = store8;
        sideValues[turnside*9 - 2] = store5;
        sideValues[turnside*9 - 1] = store3;
    }
    // </editor-fold> // Moves
    
    public void Undo()
    {
        switch(this.lastMove) {
            case 1:
                this.addMove(2);
                break;
            case 2:
                this.addMove(1);
                break;
            case 3:
                this.addMove(4);
                break;
            case 4:
                this.addMove(3);
                break;
            case 5:
                this.addMove(6);
                break;
            case 6:
                this.addMove(5);
                break;
            case 7:
                this.addMove(8);
                break;
            case 8:
                this.addMove(7);
                break;
            case 9:
                this.addMove(10);
                break;
            case 10:
                this.addMove(9);
                break;
            case 11:
                this.addMove(12);
                break;
            case 12:
                this.addMove(11);
                break;                     
        }
    }
    public void Redo()
    {
        switch(this.redoMove) {
            case 1:
                this.addMove(1);
                break;
            case 2:
                this.addMove(2);
                break;
            case 3:
                this.addMove(3);
                break;
            case 4:
                this.addMove(4);
                break;
            case 5:
                this.addMove(5);
                break;
            case 6:
                this.addMove(6);
                break;
            case 7:
                this.addMove(7);
                break;
            case 8:
                this.addMove(8);
                break;
            case 9:
                this.addMove(9);
                break;
            case 10:
                this.addMove(10);
                break;
            case 11:
                this.addMove(11);
                break;
            case 12:
                this.addMove(12);
                break;                     
        }
    }
    public void Scramble(int time)
    {
        this.scrambleFinished = false;
        new Timer(time, new ActionListener(){
            int thisCounter = 0;
            Random generator = new Random();
            @Override
            public void actionPerformed(ActionEvent e) {
                thisCounter++;
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                switch(generator.nextInt(12) + 1)
                {
                    case 1:
                        Lprime();
                        break;
                    case 2:
                        L();
                        break;
                    case 3:
                        Rprime();
                        break;
                    case 4:
                        R();
                        break;
                    case 5:
                        Fprime();
                        break;
                    case 6:
                        F();
                        break;
                    case 7:
                        Bprime();
                        break;
                    case 8:
                        B();
                        break;
                    case 9:
                        Uprime();
                        break;
                    case 10:
                        U();
                        break;
                    case 11:
                        Dprime();
                        break;
                    case 12:
                        D();
                        break;
                }
                // </editor-fold> // Switch statement
                myGUI.refresh(draw());
                if(thisCounter == 500) {
                    for(int i = 0; i < 54; i++) {
                        storingValues[i] = sideValues[i];
                    }
                    System.out.println("Finished Scrambling");
                    scrambleFinished = true;
                    ((Timer) e.getSource()).stop();
                    //return;
                }
            }
        }).start();
        //while(scrambleFinished == false) {}
        //return 0;
    }
    public void Solve(int methodValue) //throws IOException
    {
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        if(methodValue == 1) {
            SolvingFunctions solver = new SolvingFunctions(this.sideValues);
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            boolean isGood = true, frontOrBack = false;;
            int numCross = solver.checkCross(this.sideValues);
            manipSide = 2;
            while(numCross < 4) {
                recognitionCounter++;
                if(recognitionCounter > 10000) {
                    myGUI.clockTimer.stop();
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    myGUI.startTime = 0;
                    myGUI.endTime = 0;
                    myGUI.elapsedTime = 0;
                    myGUI.startTimer = 0;
                    myGUI.endTimer = 0;
                    myGUI.elapsedTimer = 0;
                    myGUI.ltfil1.removeAll();
                    myGUI.ltfil1.repaint();
                    myGUI.ltfil2.removeAll();
                    myGUI.ltfil2.repaint();
                    myGUI.rtfil1.removeAll();
                    myGUI.rtfil1.repaint();
                    myGUI.rtfil2.removeAll();
                    myGUI.rtfil2.repaint();
                    myGUI.method1.setForeground(Color.black);
                    myGUI.method2.setForeground(Color.black);
                    myGUI.method3.setForeground(Color.black);
                    myGUI.doItYourself.setForeground(Color.black);
                    myGUI.time.setText("00:00:000");
                    firstRun = true;
                    Reset();
                    for(int i = 0; i < 1000; i++) {
                        movesMade[i] = 0;
                    }
                    movesMadeCount = 0;
                    upSide = 2;
                    downSide = 2;
                    origSide = 2;
                    manipSide = 2;
                    otherSide = 2;
                    turnside = 0;
                    side = 2;
                    count = 0;
                    lastMove = 0;
                    redoMove = 0;
                    makeThisMove = 0;
                    neitherCount = 0;
                    woyrAxis = 0;
                    bogrAxis = 1;
                    wbygAxis = 2;
                    lastAxis = 1;
                    firstRun = true;
                    startActivated = false;
                    //myGUI.drawAndButtons.add(cube.draw());
                    myGUI.refresh(draw());
                    myGUI.cubeSpace.repaint();
                    myGUI.arrangement.validate();
                    // </editor-fold> // Reset
                    this.Scramble(0);
                    this.Solve(methodValue);
                    return;
                }
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[(manipSide-1) * 9 + 3] == 1) {
                    System.out.println("Running one");
                    isGood = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(2);
                            break;
                        case 3:
                            this.addMove(6);
                            break;
                        case 4:
                            this.addMove(4);
                            break;
                        case 5:
                            this.addMove(8);
                            break;
                        case 6:
                            this.addMove(2);
                            frontOrBack = true;
                            //this.addMove(2);
                            break;
                    }
                }
                // </editor-fold> // One
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 5] == 1) {
                    System.out.println("Running two");
                    isGood = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(3);
                            break;
                        case 3:
                            this.addMove(7);
                            break;
                        case 4:
                            this.addMove(1);
                            break;
                        case 5:
                            this.addMove(5);
                            break;
                        case 6:
                            this.addMove(3);
                            frontOrBack = true;
                            //this.addMove(3);
                            break;
                    }
                }
                // </editor-fold> // Two
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 1] == 1) {
                    System.out.println("Running three");
                    isGood = true;
                    frontOrBack = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(6);
                            break;
                        case 3:
                            this.addMove(4);
                            break;
                        case 4:
                            this.addMove(8);
                            break;
                        case 5:
                            this.addMove(2);
                            break;
                        case 6:
                            this.addMove(8);
                            break;
                    }
                }
                // </editor-fold> // Three
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 7] == 1) {
                    System.out.println("Running four");
                    isGood = true;
                    frontOrBack = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(5);
                            break;
                        case 3:
                            this.addMove(3);
                            break;
                        case 4:
                            this.addMove(7);
                            break;
                        case 5:
                            this.addMove(1);
                            break;
                        case 6:
                            this.addMove(5);
                            //this.addMove(3);
                            break;
                    }
                }
                // </editor-fold> // Four
                else {
                    System.out.println("Running five");
                    manipSide += 1;
                    if(manipSide == 7)
                       manipSide = 2;
                    System.out.println(manipSide);
                    isGood = false;
                }
                if(isGood == true) {
                    if(frontOrBack == false) {
                        while(solver.checkCross(sideValues) <= numCross) {
                            recognitionCounter++;
                            if(recognitionCounter > 10000) {
                                myGUI.clockTimer.stop();
                                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                                myGUI.startTime = 0;
                                myGUI.endTime = 0;
                                myGUI.elapsedTime = 0;
                                myGUI.startTimer = 0;
                                myGUI.endTimer = 0;
                                myGUI.elapsedTimer = 0;
                                myGUI.ltfil1.removeAll();
                                myGUI.ltfil1.repaint();
                                myGUI.ltfil2.removeAll();
                                myGUI.ltfil2.repaint();
                                myGUI.rtfil1.removeAll();
                                myGUI.rtfil1.repaint();
                                myGUI.rtfil2.removeAll();
                                myGUI.rtfil2.repaint();
                                myGUI.method1.setForeground(Color.black);
                                myGUI.method2.setForeground(Color.black);
                                myGUI.method3.setForeground(Color.black);
                                myGUI.doItYourself.setForeground(Color.black);
                                myGUI.time.setText("00:00:000");
                                firstRun = true;
                                Reset();
                                for(int i = 0; i < 1000; i++) {
                                    movesMade[i] = 0;
                                }
                                movesMadeCount = 0;
                                upSide = 2;
                                downSide = 2;
                                origSide = 2;
                                manipSide = 2;
                                otherSide = 2;
                                turnside = 0;
                                side = 2;
                                count = 0;
                                lastMove = 0;
                                redoMove = 0;
                                makeThisMove = 0;
                                neitherCount = 0;
                                woyrAxis = 0;
                                bogrAxis = 1;
                                wbygAxis = 2;
                                lastAxis = 1;
                                firstRun = true;
                                startActivated = false;
                                //myGUI.drawAndButtons.add(cube.draw());
                                myGUI.refresh(draw());
                                myGUI.cubeSpace.repaint();
                                myGUI.arrangement.validate();
                                // </editor-fold> // Reset
                                this.Scramble(0);
                                this.Solve(methodValue);
                                return;
                            }
                            System.out.println("Running six one");
                            redoMove = lastMove;
                            this.Undo();
                            this.addMove(12);
                            this.Redo();
                        }
                    } else {
                        while(solver.checkCross(sideValues) < numCross) {
                            recognitionCounter++;
                            if(recognitionCounter > 10000) {
                                myGUI.clockTimer.stop();
                                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                                myGUI.startTime = 0;
                                myGUI.endTime = 0;
                                myGUI.elapsedTime = 0;
                                myGUI.startTimer = 0;
                                myGUI.endTimer = 0;
                                myGUI.elapsedTimer = 0;
                                myGUI.ltfil1.removeAll();
                                myGUI.ltfil1.repaint();
                                myGUI.ltfil2.removeAll();
                                myGUI.ltfil2.repaint();
                                myGUI.rtfil1.removeAll();
                                myGUI.rtfil1.repaint();
                                myGUI.rtfil2.removeAll();
                                myGUI.rtfil2.repaint();
                                myGUI.method1.setForeground(Color.black);
                                myGUI.method2.setForeground(Color.black);
                                myGUI.method3.setForeground(Color.black);
                                myGUI.doItYourself.setForeground(Color.black);
                                myGUI.time.setText("00:00:000");
                                firstRun = true;
                                Reset();
                                for(int i = 0; i < 1000; i++) {
                                    movesMade[i] = 0;
                                }
                                movesMadeCount = 0;
                                upSide = 2;
                                downSide = 2;
                                origSide = 2;
                                manipSide = 2;
                                otherSide = 2;
                                turnside = 0;
                                side = 2;
                                count = 0;
                                lastMove = 0;
                                redoMove = 0;
                                makeThisMove = 0;
                                neitherCount = 0;
                                woyrAxis = 0;
                                bogrAxis = 1;
                                wbygAxis = 2;
                                lastAxis = 1;
                                firstRun = true;
                                startActivated = false;
                                //myGUI.drawAndButtons.add(cube.draw());
                                myGUI.refresh(draw());
                                myGUI.cubeSpace.repaint();
                                myGUI.arrangement.validate();
                                // </editor-fold> // Reset
                                this.Scramble(0);
                                this.Solve(methodValue);
                                return;
                            }
                            System.out.println("Running six two");
                            redoMove = lastMove;
                            this.Undo();
                            this.addMove(12);
                            this.Redo();
                        } 
                    }
                }
                isGood = true;
                frontOrBack = false;
                numCross = solver.checkCross(sideValues);
                System.out.println("End of Loop");
            }
            System.out.println("Finished Cross");
            // </editor-fold> // Cross
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int numMatched = solver.checkAlignment(this.sideValues);
            while(numMatched < 4) {
                recognitionCounter++;
                if(recognitionCounter > 10000) {
                    myGUI.clockTimer.stop();
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    myGUI.startTime = 0;
                    myGUI.endTime = 0;
                    myGUI.elapsedTime = 0;
                    myGUI.startTimer = 0;
                    myGUI.endTimer = 0;
                    myGUI.elapsedTimer = 0;
                    myGUI.ltfil1.removeAll();
                    myGUI.ltfil1.repaint();
                    myGUI.ltfil2.removeAll();
                    myGUI.ltfil2.repaint();
                    myGUI.rtfil1.removeAll();
                    myGUI.rtfil1.repaint();
                    myGUI.rtfil2.removeAll();
                    myGUI.rtfil2.repaint();
                    myGUI.method1.setForeground(Color.black);
                    myGUI.method2.setForeground(Color.black);
                    myGUI.method3.setForeground(Color.black);
                    myGUI.doItYourself.setForeground(Color.black);
                    myGUI.time.setText("00:00:000");
                    firstRun = true;
                    Reset();
                    for(int i = 0; i < 1000; i++) {
                        movesMade[i] = 0;
                    }
                    movesMadeCount = 0;
                    upSide = 2;
                    downSide = 2;
                    origSide = 2;
                    manipSide = 2;
                    otherSide = 2;
                    turnside = 0;
                    side = 2;
                    count = 0;
                    lastMove = 0;
                    redoMove = 0;
                    makeThisMove = 0;
                    neitherCount = 0;
                    woyrAxis = 0;
                    bogrAxis = 1;
                    wbygAxis = 2;
                    lastAxis = 1;
                    firstRun = true;
                    startActivated = false;
                    //myGUI.drawAndButtons.add(cube.draw());
                    myGUI.refresh(draw());
                    myGUI.cubeSpace.repaint();
                    myGUI.arrangement.validate();
                    // </editor-fold> // Reset
                    this.Scramble(0);
                    this.Solve(methodValue);
                    return;
                }
                System.out.println(numMatched);
                if(numMatched < 2) {
                    while(numMatched < 2) {
                        recognitionCounter++;
                        if(recognitionCounter > 10000) {
                            myGUI.clockTimer.stop();
                            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                            myGUI.startTime = 0;
                            myGUI.endTime = 0;
                            myGUI.elapsedTime = 0;
                            myGUI.startTimer = 0;
                            myGUI.endTimer = 0;
                            myGUI.elapsedTimer = 0;
                            myGUI.ltfil1.removeAll();
                            myGUI.ltfil1.repaint();
                            myGUI.ltfil2.removeAll();
                            myGUI.ltfil2.repaint();
                            myGUI.rtfil1.removeAll();
                            myGUI.rtfil1.repaint();
                            myGUI.rtfil2.removeAll();
                            myGUI.rtfil2.repaint();
                            myGUI.method1.setForeground(Color.black);
                            myGUI.method2.setForeground(Color.black);
                            myGUI.method3.setForeground(Color.black);
                            myGUI.doItYourself.setForeground(Color.black);
                            myGUI.time.setText("00:00:000");
                            firstRun = true;
                            Reset();
                            for(int i = 0; i < 1000; i++) {
                                movesMade[i] = 0;
                            }
                            movesMadeCount = 0;
                            upSide = 2;
                            downSide = 2;
                            origSide = 2;
                            manipSide = 2;
                            otherSide = 2;
                            turnside = 0;
                            side = 2;
                            count = 0;
                            lastMove = 0;
                            redoMove = 0;
                            makeThisMove = 0;
                            neitherCount = 0;
                            woyrAxis = 0;
                            bogrAxis = 1;
                            wbygAxis = 2;
                            lastAxis = 1;
                            firstRun = true;
                            startActivated = false;
                            //myGUI.drawAndButtons.add(cube.draw());
                            myGUI.refresh(draw());
                            myGUI.cubeSpace.repaint();
                            myGUI.arrangement.validate();
                            // </editor-fold> // Reset
                            this.Scramble(0);
                            this.Solve(methodValue);
                            return;
                        }
                        this.addMove(12);
                        numMatched = solver.checkAlignment(this.sideValues);
                    }
                } else if(sideValues[13] == sideValues[16] && sideValues[31] == sideValues[34]) {
                    System.out.println("Ran one");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(4);
                } else if(sideValues[22] == sideValues[25] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran two");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[13] == sideValues[16] && sideValues[22] == sideValues[25]) {
                    System.out.println("Ran three");
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                } else if(sideValues[22] == sideValues[25] && sideValues[31] == sideValues[34]) {
                    System.out.println("Ran four");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(10);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[31] == sideValues[34] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran five");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(9);
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[13] == sideValues[16] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran six");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(9);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(4);
                }
                numMatched = solver.checkAlignment(sideValues);
            }
            System.out.println("Finished Alignment");
            // </editor-fold> // Alignment
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int numCorners = solver.checkCorners(sideValues);
            int c1 = 0, c2 = 0, currentSide = 0;
            manipSide = 2;
            while(numCorners < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[(manipSide - 1) * 9] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 one");
                    if(sideValues[2] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(7);
                    this.addMove(10);
                    this.addMove(8);
                } else if(sideValues[(manipSide - 1) * 9 + 2] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 two");
                    if(sideValues[0] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(3);
                    this.addMove(10);
                    this.addMove(4);
                } else if(sideValues[(manipSide - 1) * 9 + 6] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 three");
                    if(sideValues[8] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(2);
                } else if(sideValues[(manipSide - 1) * 9 + 8] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 four");
                    if(sideValues[6] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(5);
                    this.addMove(10);
                    this.addMove(6);
                }
                // </editor-fold> // Top Side
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9] == 1) {
                    System.out.println("Run corner one");
                    switch(manipSide) {
                        case 2:
                            c1 = 51;
                            c2 = 38;
                            break;
                        case 3:
                            c1 = 53;
                            c2 = 11;
                            break;
                        case 4:
                            c1 = 47;
                            c2 = 20;
                            break;
                        case 5:
                            c1 = 45;
                            c2 = 29;
                            break;
                    }
                    currentSide = sideValues[c2 + 2];
                    while(sideValues[c2] != sideValues[(currentSide-1) * 9 + 4]) {
                        Recognition();
                        this.addMove(9);
                        c2 += 9;
                        if(c2 == 47) {
                            c2 = 11;
                        }
                        currentSide = sideValues[c2 + 2];
                    }
                    switch(currentSide) {
                        case 2:
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            break;
                        case 3:
                            this.addMove(8);
                            this.addMove(10);
                            this.addMove(7);
                            break;
                        case 4:
                            this.addMove(2);
                            this.addMove(10);
                            this.addMove(1);
                            break;
                        case 5:
                            this.addMove(6);
                            this.addMove(10);
                            this.addMove(5);
                            break;
                    }
                }
                // </editor-fold> // Top Left
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 2] == 1) {
                    System.out.println("Run corner two");
                    switch(manipSide) {
                        case 2:
                            c1 = 53;
                            c2 = 18;
                            break;
                        case 3:
                            c1 = 47;
                            c2 = 27;
                            break;
                        case 4:
                            c1 = 45;
                            c2 = 36;
                            break;
                        case 5:
                            c1 = 51;
                            c2 = 9;
                            break;
                    }
                    currentSide = sideValues[c2 + 4];
                    while(sideValues[c2] != currentSide) {
                        Recognition();
                        this.addMove(9);
                        c2 += 9;
                        if(c2 == 45) {
                            c2 = 9;
                        }
                        currentSide = sideValues[c2 + 4];
                    }
                    switch(currentSide) {
                        case 2:
                            this.addMove(1);
                            this.addMove(9);
                            this.addMove(2);
                            break;
                        case 3:
                            this.addMove(5);
                            this.addMove(9);
                            this.addMove(6);
                            break;
                        case 4:
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            break;
                        case 5:
                            this.addMove(7);
                            this.addMove(9);
                            this.addMove(8);
                            break;
                    }
                }
                // </editor-fold> // Top Right
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 6] == 1) {
                    System.out.println("Run corner three");
                    switch(manipSide) {
                        case 2:
                            this.addMove(6);
                            this.addMove(10);
                            this.addMove(5);
                            break;
                        case 3:
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            break;
                        case 4:
                            this.addMove(8);
                            this.addMove(10);
                            this.addMove(7);
                            break;
                        case 5:
                            this.addMove(2);
                            this.addMove(10);
                            this.addMove(1);
                            break;
                    }
                }
                // </editor-fold> // Bottom Left
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 8] == 1) {
                    System.out.println("Run corner four");
                    switch(manipSide) {
                        case 2:
                            this.addMove(5);
                            this.addMove(9);
                            this.addMove(6);
                            break;
                        case 3:
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            break;
                        case 4:
                            this.addMove(7);
                            this.addMove(9);
                            this.addMove(8);
                            break;
                        case 5:
                            this.addMove(1);
                            this.addMove(9);
                            this.addMove(2);
                            break;
                    }
                }
                // </editor-fold> // Bottom Right
                else {
                    System.out.println("Run else corner");
                    System.out.println("Before: " + manipSide);
                    manipSide += 1;
                    if(manipSide == 7) {
                        manipSide = 2;
                    }
                    System.out.println("After: " + manipSide);
                }
                numCorners = solver.checkCorners(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Bottom Corners");
            // </editor-fold> // Bottom Corners
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int numMidEdges = solver.checkMidEdges(sideValues);
            manipSide = 2;
            int topSticker = 0, midSticker = 0, numTimesWithout = 0;
            currentSide = 2;
            while(numMidEdges < 4) {
                Recognition();
                //currentSide = manipSide;
                switch(currentSide) {
                    case 2:
                        topSticker = 52;
                        break;
                    case 3:
                        topSticker = 50;
                        break;
                    case 4:
                        topSticker = 46;
                        break;
                    case 5:
                        topSticker = 48;
                        break;
                }
                midSticker = (currentSide - 1) * 9 + 1;
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(numTimesWithout >= 4) {
                    System.out.println("Running stuck");
                    if(sideValues[14] != 6 && sideValues[21] != 6 && sideValues[14] != 2 && sideValues[21] != 3) {
                        this.addMove(4);
                        this.addMove(9);
                        this.addMove(3);
                        this.addMove(9);
                        this.addMove(5);
                        this.addMove(10);
                        this.addMove(6);
                    }
                    if(sideValues[23] != 6 && sideValues[30] != 6 && sideValues[23] != 3 && sideValues[30] != 4) {
                        this.addMove(8);
                        this.addMove(9);
                        this.addMove(7);
                        this.addMove(9);
                        this.addMove(3);
                        this.addMove(10);
                        this.addMove(4);
                    }
                    if(sideValues[32] != 6 && sideValues[39] != 6 && sideValues[32] != 4 && sideValues[39] != 5) {
                        this.addMove(2);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(9);
                        this.addMove(7);
                        this.addMove(10);
                        this.addMove(8);
                    }
                    if(sideValues[41] != 6 && sideValues[12] != 6 && sideValues[41] != 5 && sideValues[12] != 2) {
                        this.addMove(6);
                        this.addMove(9);
                        this.addMove(5);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(10);
                        this.addMove(2);
                    }
                    numTimesWithout = 0;
                }
                // </editor-fold> // Stuck
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[midSticker] != 6 && sideValues[topSticker] != 6) {
                    System.out.println("Running normal");
                    System.out.println("Current Side: " + currentSide);
                    numTimesWithout = 0;
                    while(sideValues[midSticker] != currentSide) {
                        Recognition();
                        System.out.println("Running midSticker while");
                        System.out.println("currentSide before: " + currentSide);
                        System.out.println("midSticker before: " + midSticker);
                        this.addMove(9);
                        currentSide ++;
                        if(currentSide == 6) {
                            currentSide = 2;
                        }
                        midSticker = (currentSide - 1) * 9 + 1;
                        System.out.println("currentSide after: " + currentSide);
                        System.out.println("midSticker after: " + midSticker);
                    }
                    switch(currentSide) {
                        case 2:
                            topSticker = 52;
                            break;
                        case 3:
                            topSticker = 50;
                            break;
                        case 4:
                            topSticker = 46;
                            break;
                        case 5:
                            topSticker = 48;
                            break;
                    }
                    int plusOne = sideValues[topSticker] + 1;
                    if(plusOne == 6) {plusOne = 2;}
                    int minusOne = sideValues[topSticker] - 1;
                    if(minusOne == 1) {minusOne = 5;}
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    if(plusOne == currentSide) {
                        System.out.println("Running plus");
                        this.addMove(9);
                        switch(currentSide) {
                            case 2:
                                System.out.println("Running plus two");
                                this.addMove(1);
                                this.addMove(10);
                                this.addMove(2);
                                this.addMove(10);
                                this.addMove(6);
                                this.addMove(9);
                                this.addMove(5);
                                break;
                            case 3:
                                System.out.println("Running plus three");
                                this.addMove(5);
                                this.addMove(10);
                                this.addMove(6);
                                this.addMove(10);
                                this.addMove(4);
                                this.addMove(9);
                                this.addMove(3);
                                break;
                            case 4:
                                System.out.println("Running plus four");
                                this.addMove(3);
                                this.addMove(10);
                                this.addMove(4);
                                this.addMove(10);
                                this.addMove(8);
                                this.addMove(9);
                                this.addMove(7);
                                break;
                            case 5:
                                System.out.println("Running plus five");
                                this.addMove(7);
                                this.addMove(10);
                                this.addMove(8);
                                this.addMove(10);
                                this.addMove(2);
                                this.addMove(9);
                                this.addMove(1);
                                break;
                        }
                    }
                    // </editor-fold> // Plus One
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    else if(minusOne == currentSide) {
                        System.out.println("Running minus");
                        this.addMove(10);
                        switch(currentSide) {
                            case 2:
                                System.out.println("Running minus two");
                                this.addMove(4);
                                this.addMove(9);
                                this.addMove(3);
                                this.addMove(9);
                                this.addMove(5);
                                this.addMove(10);
                                this.addMove(6);
                                break;
                            case 3:
                                System.out.println("Running minus three");
                                this.addMove(8);
                                this.addMove(9);
                                this.addMove(7);
                                this.addMove(9);
                                this.addMove(3);
                                this.addMove(10);
                                this.addMove(4);
                                break;
                            case 4:
                                System.out.println("Running minus four");
                                this.addMove(2);
                                this.addMove(9);
                                this.addMove(1);
                                this.addMove(9);
                                this.addMove(7);
                                this.addMove(10);
                                this.addMove(8);
                                break;
                            case 5:
                                System.out.println("Running minus five");
                                this.addMove(6);
                                this.addMove(9);
                                this.addMove(5);
                                this.addMove(9);
                                this.addMove(1);
                                this.addMove(10);
                                this.addMove(2);
                                break;
                        }
                    }
                    // </editor-fold> // Minus One
                    else {
                        neitherCount ++;
                        System.out.println("Neither");
                        if(neitherCount == 40) {
                            //this.MakeMove();
                            return;
                        }
                    }
                }
                // </editor-fold> // Normal
                else {
                    numTimesWithout++;
                    System.out.println("Before: " + currentSide);
                    currentSide += 1;
                    if(currentSide == 6) {
                        currentSide = 2;
                    }
                    System.out.println("After: " + currentSide);
                }
                numMidEdges = solver.checkMidEdges(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Middle Edges");
            // </editor-fold> // Middle Edges
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            manipSide = 6;
            currentSide = 6;
            numTimesWithout = 0;
            int secondTimesWithout = 0;
            boolean topSolved = solver.checkTopSide(sideValues);
            while(topSolved == false) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running left ribbon");
                    if(sideValues[9] == 6) {
                        this.addMove(1);
                        this.addMove(9);
                        this.addMove(2);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(10);
                        this.addMove(10);
                        this.addMove(2);
                    } else {
                        System.out.println("Left ribbon didn't run");
                        this.addMove(10);
                    }
                }
                // </editor-fold> // Left Ribbon
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] == 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running right ribbon");
                    if(sideValues[11] == 6) {
                        this.addMove(4);
                        this.addMove(10);
                        this.addMove(3);
                        this.addMove(10);
                        this.addMove(4);
                        this.addMove(10);
                        this.addMove(10);
                        this.addMove(3);
                    } else {
                        System.out.println("Right ribbon didn't run");
                        this.addMove(10);
                    }
                }
                // </editor-fold> // Right Ribbon
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running top cross");
                    while(sideValues[9] != 6 && sideValues[11] != 6) {
                        Recognition();
                        this.addMove(10);
                    }
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Top Cross
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] == 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running weird pattern");
                    if(sideValues[38] != 6) {
                        this.addMove(10);
                        this.addMove(10);
                    }
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Weird
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] == 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] == 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running block");
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Normal Block
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] == 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running opposite block");
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Opposite Block
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] == 6 && sideValues[46] == 6 && sideValues[47] == 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running up block");
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Up Block
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] == 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running down block");
                    this.addMove(10);
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Down Block
                else {
                    System.out.println("Running else top");
                    numTimesWithout++;
                    if(numTimesWithout > 4) {
                        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                        if(sideValues[46] == 6 && sideValues[48] == 6 && sideValues[49] == 6 && solver.checkTopStickers(sideValues) <= 7) {
                            System.out.println("Running elbow");
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(5);
                            this.addMove(10);
                            this.addMove(6);
                            this.addMove(4);
                            numTimesWithout = 0;
                        }
                        // </editor-fold> // Elbow
                        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                        else if(sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && solver.checkTopStickers(sideValues) <= 7) {
                            System.out.println("Running line");
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(5);
                            this.addMove(10);
                            this.addMove(6);
                            this.addMove(4);
                            if(sideValues[49] == 6 && sideValues[50] == 6 && sideValues[52] == 6) {
                                this.addMove(10);
                                this.addMove(10);
                            }
                            numTimesWithout = 0;
                        }
                        // </editor-fold> // Line
                        else {
                            this.addMove(10);
                        }
                        secondTimesWithout++;
                        if(secondTimesWithout > 4) {
                            System.out.println("Running point");
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(5);
                            this.addMove(10);
                            this.addMove(6);
                            this.addMove(4);
                            secondTimesWithout = 0;
                        }
                    } else {
                        this.addMove(10);
                    }
                }
                topSolved = solver.checkTopSide(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Top Side");
            // </editor-fold> // Top Side
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            currentSide = 0;
            manipSide = 0;
            int numTopEdges = solver.checkTopEdges(sideValues);
            while(numTopEdges < 4) {
                Recognition();
                if((sideValues[9] == 2 || sideValues[9] ==  4) && (sideValues[11] == 2 || sideValues[11] == 4)) {
                    System.out.println("Running twists");
                    this.addMove(3);
                    this.addMove(6);
                    this.addMove(3);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(4);
                    this.addMove(5);
                    this.addMove(3);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(4);
                    this.addMove(4);
                }
                else {
                    System.out.println("Running else edge");
                    this.addMove(10);
                }
                numTopEdges = solver.checkTopEdges(sideValues);
                //System.out.println(numTopEdges);
                System.out.println("End of loop");
            }
            System.out.println("Finished Top Edges");
            // </editor-fold> // Top Edges
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            currentSide = 0;
            manipSide = 0;
            int whichSide = 0;
            while(sideValues[9] != 2) {
                Recognition();
                this.addMove(10);
            }
            int numTopCenters = solver.checkTopCenters(sideValues);
            while(numTopCenters < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(numTopCenters == 0) {
                    System.out.println("Running center none");
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(1);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(3);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                }
                // </editor-fold> // Center None
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[10] == 2) {
                    System.out.println("Running center one");
                    if(sideValues[19] == 4) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(whichSide);
                    this.addMove(4);
                    this.addMove(1);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(3);
                    this.addMove(2);
                    this.addMove(whichSide);
                    this.addMove(8);
                    this.addMove(8);
                }
                // </editor-fold> // Center One
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[19] == 3) {
                    System.out.println("Running center two");
                    if(sideValues[28] == 5) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(whichSide);
                    this.addMove(8);
                    this.addMove(5);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(7);
                    this.addMove(6);
                    this.addMove(whichSide);
                    this.addMove(2);
                    this.addMove(2);
                }
                // </editor-fold> // Center Two
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[28] == 4) {
                    System.out.println("Running center three");
                    if(sideValues[37] == 2) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(whichSide);
                    this.addMove(2);
                    this.addMove(3);
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(1);
                    this.addMove(4);
                    this.addMove(whichSide);
                    this.addMove(6);
                    this.addMove(6);
                }
                // </editor-fold> // Center Three
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[37] == 5) {
                    if(sideValues[10] == 3) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    System.out.println("Running center four");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(whichSide);
                    this.addMove(6);
                    this.addMove(7);
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(5);
                    this.addMove(8);
                    this.addMove(whichSide);
                    this.addMove(4);
                    this.addMove(4);
                }
                // </editor-fold> // Center Four
                else {
                    System.out.println("Running else center");
                }
                numTopCenters = solver.checkTopCenters(sideValues);
                //System.out.println(numTopEdges);
                System.out.println("End of loop");
            }
            System.out.println("Finished Top Centers");
            // </editor-fold> // Top Centers
            this.MakeMove(methodValue);
        }
        // </editor-fold> // Tri-Layer
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(methodValue == 2) {
            SolvingFunctions solver = new SolvingFunctions(this.sideValues);
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            boolean isGood = true, frontOrBack = false;;
            int numCross = solver.checkCross(this.sideValues);
            manipSide = 2;
            while(numCross < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[(manipSide-1) * 9 + 3] == 1) {
                    System.out.println("Running one");
                    isGood = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(2);
                            break;
                        case 3:
                            this.addMove(6);
                            break;
                        case 4:
                            this.addMove(4);
                            break;
                        case 5:
                            this.addMove(8);
                            break;
                        case 6:
                            this.addMove(2);
                            frontOrBack = true;
                            //this.addMove(2);
                            break;
                    }
                }
                // </editor-fold> // One
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 5] == 1) {
                    System.out.println("Running two");
                    isGood = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(3);
                            break;
                        case 3:
                            this.addMove(7);
                            break;
                        case 4:
                            this.addMove(1);
                            break;
                        case 5:
                            this.addMove(5);
                            break;
                        case 6:
                            this.addMove(3);
                            frontOrBack = true;
                            //this.addMove(3);
                            break;
                    }
                }
                // </editor-fold> // Two
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 1] == 1) {
                    System.out.println("Running three");
                    isGood = true;
                    frontOrBack = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(6);
                            break;
                        case 3:
                            this.addMove(4);
                            break;
                        case 4:
                            this.addMove(8);
                            break;
                        case 5:
                            this.addMove(2);
                            break;
                        case 6:
                            this.addMove(8);
                            break;
                    }
                }
                // </editor-fold> // Three
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 7] == 1) {
                    System.out.println("Running four");
                    isGood = true;
                    frontOrBack = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(5);
                            break;
                        case 3:
                            this.addMove(3);
                            break;
                        case 4:
                            this.addMove(7);
                            break;
                        case 5:
                            this.addMove(1);
                            break;
                        case 6:
                            this.addMove(5);
                            //this.addMove(3);
                            break;
                    }
                }
                // </editor-fold> // Four
                else {
                    System.out.println("Running five");
                    manipSide += 1;
                    if(manipSide == 7)
                       manipSide = 2;
                    System.out.println(manipSide);
                    isGood = false;
                }
                if(isGood == true) {
                    if(frontOrBack == false) {
                        while(solver.checkCross(sideValues) <= numCross) {
                            Recognition();
                            System.out.println("Running six one");
                            redoMove = lastMove;
                            this.Undo();
                            this.addMove(12);
                            this.Redo();
                        }
                    } else {
                        while(solver.checkCross(sideValues) < numCross) {
                            Recognition();
                            System.out.println("Running six two");
                            redoMove = lastMove;
                            this.Undo();
                            this.addMove(12);
                            this.Redo();
                        } 
                    }
                }
                isGood = true;
                frontOrBack = false;
                numCross = solver.checkCross(sideValues);
                System.out.println("End of Loop");
            }
            System.out.println("Finished Partial Cross");
            // </editor-fold> // Partial Cross
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int numMatched = solver.checkAlignment(this.sideValues);
            while(numMatched < 4) {
                Recognition();
                System.out.println(numMatched);
                if(numMatched < 2) {
                    while(numMatched < 2) {
                        Recognition();
                        this.addMove(12);
                        numMatched = solver.checkAlignment(this.sideValues);
                    }
                } else if(sideValues[13] == sideValues[16] && sideValues[31] == sideValues[34]) {
                    System.out.println("Ran one");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(4);
                } else if(sideValues[22] == sideValues[25] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran two");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[13] == sideValues[16] && sideValues[22] == sideValues[25]) {
                    System.out.println("Ran three");
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                } else if(sideValues[22] == sideValues[25] && sideValues[31] == sideValues[34]) {
                    System.out.println("Ran four");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(10);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[31] == sideValues[34] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran five");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(9);
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[13] == sideValues[16] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran six");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(9);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(4);
                }
                numMatched = solver.checkAlignment(sideValues);
            }
            System.out.println("Finished Alignment");
            // </editor-fold> // Alignment
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            boolean twoCorners = solver.checkTwoCorners(sideValues);
            int c1 = 0, c2 = 0, currentSide = 0;
            manipSide = 2;
            while(twoCorners == false) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[(manipSide - 1) * 9] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 one");
                    if(sideValues[2] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(7);
                    this.addMove(10);
                    this.addMove(8);
                } else if(sideValues[(manipSide - 1) * 9 + 2] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 two");
                    if(sideValues[0] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(3);
                    this.addMove(10);
                    this.addMove(4);
                } else if(sideValues[(manipSide - 1) * 9 + 6] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 three");
                    if(sideValues[8] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(2);
                } else if(sideValues[(manipSide - 1) * 9 + 8] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 four");
                    if(sideValues[6] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(5);
                    this.addMove(10);
                    this.addMove(6);
                }
                // </editor-fold> // Top Side
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9] == 1) {
                    System.out.println("Run corner one");
                    switch(manipSide) {
                        case 2:
                            c1 = 51;
                            c2 = 38;
                            break;
                        case 3:
                            c1 = 53;
                            c2 = 11;
                            break;
                        case 4:
                            c1 = 47;
                            c2 = 20;
                            break;
                        case 5:
                            c1 = 45;
                            c2 = 29;
                            break;
                    }
                    currentSide = sideValues[c2 + 2];
                    while(sideValues[c2] != sideValues[(currentSide-1) * 9 + 4]) {
                        Recognition();
                        this.addMove(9);
                        c2 += 9;
                        if(c2 == 47) {
                            c2 = 11;
                        }
                        currentSide = sideValues[c2 + 2];
                    }
                    switch(currentSide) {
                        case 2:
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            break;
                        case 3:
                            this.addMove(8);
                            this.addMove(10);
                            this.addMove(7);
                            break;
                        case 4:
                            this.addMove(2);
                            this.addMove(10);
                            this.addMove(1);
                            break;
                        case 5:
                            this.addMove(6);
                            this.addMove(10);
                            this.addMove(5);
                            break;
                    }
                }
                // </editor-fold> // Top Left
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 2] == 1) {
                    System.out.println("Run corner two");
                    switch(manipSide) {
                        case 2:
                            c1 = 53;
                            c2 = 18;
                            break;
                        case 3:
                            c1 = 47;
                            c2 = 27;
                            break;
                        case 4:
                            c1 = 45;
                            c2 = 36;
                            break;
                        case 5:
                            c1 = 51;
                            c2 = 9;
                            break;
                    }
                    currentSide = sideValues[c2 + 4];
                    while(sideValues[c2] != currentSide) {
                        Recognition();
                        this.addMove(9);
                        c2 += 9;
                        if(c2 == 45) {
                            c2 = 9;
                        }
                        currentSide = sideValues[c2 + 4];
                    }
                    switch(currentSide) {
                        case 2:
                            this.addMove(1);
                            this.addMove(9);
                            this.addMove(2);
                            break;
                        case 3:
                            this.addMove(5);
                            this.addMove(9);
                            this.addMove(6);
                            break;
                        case 4:
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            break;
                        case 5:
                            this.addMove(7);
                            this.addMove(9);
                            this.addMove(8);
                            break;
                    }
                }
                // </editor-fold> // Top Right
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 6] == 1) {
                    System.out.println("Run corner three");
                    switch(manipSide) {
                        case 2:
                            this.addMove(6);
                            this.addMove(10);
                            this.addMove(5);
                            break;
                        case 3:
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            break;
                        case 4:
                            this.addMove(8);
                            this.addMove(10);
                            this.addMove(7);
                            break;
                        case 5:
                            this.addMove(2);
                            this.addMove(10);
                            this.addMove(1);
                            break;
                    }
                }
                // </editor-fold> // Bottom Left
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 8] == 1) {
                    System.out.println("Run corner four");
                    switch(manipSide) {
                        case 2:
                            this.addMove(5);
                            this.addMove(9);
                            this.addMove(6);
                            break;
                        case 3:
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            break;
                        case 4:
                            this.addMove(7);
                            this.addMove(9);
                            this.addMove(8);
                            break;
                        case 5:
                            this.addMove(1);
                            this.addMove(9);
                            this.addMove(2);
                            break;
                    }
                }
                // </editor-fold> // Bottom Right
                else {
                    System.out.println("Run else corner");
                    System.out.println("Before: " + manipSide);
                    manipSide += 1;
                    if(manipSide == 7) {
                        manipSide = 2;
                    }
                    System.out.println("After: " + manipSide);
                }
                twoCorners = solver.checkTwoCorners(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Bottom Two Corners");
            // </editor-fold> // Bottom Two Corners
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int numMidEdges = solver.checkMidEdges(sideValues);
            manipSide = 2;
            int topSticker = 0, midSticker = 0, numTimesWithout = 0;
            currentSide = 2;
            while(numMidEdges < 4) {
                Recognition();
                switch(currentSide) {
                    case 2:
                        topSticker = 52;
                        break;
                    case 3:
                        topSticker = 50;
                        break;
                    case 4:
                        topSticker = 46;
                        break;
                    case 5:
                        topSticker = 48;
                        break;
                }
                midSticker = (currentSide - 1) * 9 + 1;
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(numTimesWithout >= 4) {
                    System.out.println("Running stuck");
                    if(sideValues[14] != 6 && sideValues[21] != 6 && sideValues[14] != 2 && sideValues[21] != 3) {
                        this.addMove(4);
                        this.addMove(9);
                        this.addMove(3);
                        this.addMove(9);
                        this.addMove(5);
                        this.addMove(10);
                        this.addMove(6);
                    }
                    if(sideValues[23] != 6 && sideValues[30] != 6 && sideValues[23] != 3 && sideValues[30] != 4) {
                        this.addMove(8);
                        this.addMove(9);
                        this.addMove(7);
                        this.addMove(9);
                        this.addMove(3);
                        this.addMove(10);
                        this.addMove(4);
                    }
                    if(sideValues[32] != 6 && sideValues[39] != 6 && sideValues[32] != 4 && sideValues[39] != 5) {
                        this.addMove(2);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(9);
                        this.addMove(7);
                        this.addMove(10);
                        this.addMove(8);
                    }
                    if(sideValues[41] != 6 && sideValues[12] != 6 && sideValues[41] != 5 && sideValues[12] != 2) {
                        this.addMove(6);
                        this.addMove(9);
                        this.addMove(5);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(10);
                        this.addMove(2);
                    }
                    numTimesWithout = 0;
                }
                // </editor-fold> // Stuck
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[midSticker] != 6 && sideValues[topSticker] != 6) {
                    System.out.println("Running normal");
                    System.out.println("Current Side: " + currentSide);
                    numTimesWithout = 0;
                    while(sideValues[midSticker] != currentSide) {
                        Recognition();
                        System.out.println("Running midSticker while");
                        System.out.println("currentSide before: " + currentSide);
                        System.out.println("midSticker before: " + midSticker);
                        this.addMove(9);
                        currentSide ++;
                        if(currentSide == 6) {
                            currentSide = 2;
                        }
                        midSticker = (currentSide - 1) * 9 + 1;
                        System.out.println("currentSide after: " + currentSide);
                        System.out.println("midSticker after: " + midSticker);
                    }
                    switch(currentSide) {
                        case 2:
                            topSticker = 52;
                            break;
                        case 3:
                            topSticker = 50;
                            break;
                        case 4:
                            topSticker = 46;
                            break;
                        case 5:
                            topSticker = 48;
                            break;
                    }
                    int plusOne = sideValues[topSticker] + 1;
                    if(plusOne == 6) {plusOne = 2;}
                    int minusOne = sideValues[topSticker] - 1;
                    if(minusOne == 1) {minusOne = 5;}
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    if(plusOne == currentSide) {
                        System.out.println("Running plus");
                        this.addMove(9);
                        switch(currentSide) {
                            case 2:
                                System.out.println("Running plus two");
                                this.addMove(1);
                                this.addMove(10);
                                this.addMove(2);
                                this.addMove(10);
                                this.addMove(6);
                                this.addMove(9);
                                this.addMove(5);
                                break;
                            case 3:
                                System.out.println("Running plus three");
                                this.addMove(5);
                                this.addMove(10);
                                this.addMove(6);
                                this.addMove(10);
                                this.addMove(4);
                                this.addMove(9);
                                this.addMove(3);
                                break;
                            case 4:
                                System.out.println("Running plus four");
                                this.addMove(3);
                                this.addMove(10);
                                this.addMove(4);
                                this.addMove(10);
                                this.addMove(8);
                                this.addMove(9);
                                this.addMove(7);
                                break;
                            case 5:
                                System.out.println("Running plus five");
                                this.addMove(7);
                                this.addMove(10);
                                this.addMove(8);
                                this.addMove(10);
                                this.addMove(2);
                                this.addMove(9);
                                this.addMove(1);
                                break;
                        }
                    }
                    // </editor-fold> // Plus One
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    else if(minusOne == currentSide) {
                        System.out.println("Running minus");
                        this.addMove(10);
                        switch(currentSide) {
                            case 2:
                                System.out.println("Running minus two");
                                this.addMove(4);
                                this.addMove(9);
                                this.addMove(3);
                                this.addMove(9);
                                this.addMove(5);
                                this.addMove(10);
                                this.addMove(6);
                                break;
                            case 3:
                                System.out.println("Running minus three");
                                this.addMove(8);
                                this.addMove(9);
                                this.addMove(7);
                                this.addMove(9);
                                this.addMove(3);
                                this.addMove(10);
                                this.addMove(4);
                                break;
                            case 4:
                                System.out.println("Running minus four");
                                this.addMove(2);
                                this.addMove(9);
                                this.addMove(1);
                                this.addMove(9);
                                this.addMove(7);
                                this.addMove(10);
                                this.addMove(8);
                                break;
                            case 5:
                                System.out.println("Running minus five");
                                this.addMove(6);
                                this.addMove(9);
                                this.addMove(5);
                                this.addMove(9);
                                this.addMove(1);
                                this.addMove(10);
                                this.addMove(2);
                                break;
                        }
                    }
                    // </editor-fold> // Minus One
                    else {
                        neitherCount ++;
                        System.out.println("Neither");
                        if(neitherCount == 40) {
                            //this.MakeMove();
                            return;
                        }
                    }
                }
                // </editor-fold> // Normal
                else {
                    numTimesWithout++;
                    System.out.println("Before: " + currentSide);
                    currentSide += 1;
                    if(currentSide == 6) {
                        currentSide = 2;
                    }
                    System.out.println("After: " + currentSide);
                }
                numMidEdges = solver.checkMidEdges(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Middle Edges");
            // </editor-fold> // Middle Two Edges
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            boolean topCrossSolved = solver.checkTopCross(sideValues);
            currentSide = 0;
            int topCounter = 0;
            while(topCrossSolved == false) {
                Recognition();
                if(sideValues[10] == 6) {
                    System.out.println("Running Top Move");
                    this.addMove(3);
                    this.addMove(9);
                    this.addMove(5);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(4);
                } else {
                    System.out.println("Running else top");
                    topCounter++;
                    this.addMove(10);
                }
                if(topCounter > 100) {
                    break;
                }
                topCrossSolved = solver.checkTopCross(sideValues);
            }
            System.out.println("Finished Top Cross");
            while(!(sideValues[37] == 5 && sideValues[48] == 6)) {
                Recognition();
                this.addMove(10);
            }
            boolean leftCrossSolved = solver.checkLeftCross(sideValues);
            int leftCounter = 0;
            while(leftCrossSolved == false) {
                Recognition();
                if(sideValues[12] == 5) {
                    System.out.println("Running Left Move");
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(5);
                    this.addMove(2);
                    this.addMove(6);
                    this.addMove(2);
                } else {
                    System.out.println("Running else left");
                    leftCounter++;
                    this.addMove(2);
                }
                if(leftCounter > 100) {
                    break;
                }
                leftCrossSolved = solver.checkLeftCross(sideValues);
            }
            System.out.println("Finished Left Cross");
            // </editor-fold> // Top and Left Crosses
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            isGood = true;
            frontOrBack = false;;
            numCross = solver.checkCross(this.sideValues);
            manipSide = 2;
            while(numCross < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[(manipSide-1) * 9 + 3] == 1) {
                    System.out.println("Running one");
                    isGood = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(2);
                            break;
                        case 3:
                            this.addMove(6);
                            break;
                        case 4:
                            this.addMove(4);
                            break;
                        case 5:
                            this.addMove(8);
                            break;
                        case 6:
                            this.addMove(2);
                            frontOrBack = true;
                            //this.addMove(2);
                            break;
                    }
                }
                // </editor-fold> // One
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 5] == 1) {
                    System.out.println("Running two");
                    isGood = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(3);
                            break;
                        case 3:
                            this.addMove(7);
                            break;
                        case 4:
                            this.addMove(1);
                            break;
                        case 5:
                            this.addMove(5);
                            break;
                        case 6:
                            this.addMove(3);
                            frontOrBack = true;
                            //this.addMove(3);
                            break;
                    }
                }
                // </editor-fold> // Two
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 1] == 1) {
                    System.out.println("Running three");
                    isGood = true;
                    frontOrBack = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(6);
                            break;
                        case 3:
                            this.addMove(4);
                            break;
                        case 4:
                            this.addMove(8);
                            break;
                        case 5:
                            this.addMove(2);
                            break;
                        case 6:
                            this.addMove(8);
                            break;
                    }
                }
                // </editor-fold> // Three
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 7] == 1) {
                    System.out.println("Running four");
                    isGood = true;
                    frontOrBack = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(5);
                            break;
                        case 3:
                            this.addMove(3);
                            break;
                        case 4:
                            this.addMove(7);
                            break;
                        case 5:
                            this.addMove(1);
                            break;
                        case 6:
                            this.addMove(5);
                            //this.addMove(3);
                            break;
                    }
                }
                // </editor-fold> // Four
                else {
                    System.out.println("Running five");
                    manipSide += 1;
                    if(manipSide == 7)
                       manipSide = 2;
                    System.out.println(manipSide);
                    isGood = false;
                }
                if(isGood == true) {
                    if(frontOrBack == false) {
                        while(solver.checkCross(sideValues) <= numCross) {
                            Recognition();
                            System.out.println("Running six one");
                            redoMove = lastMove;
                            this.Undo();
                            this.addMove(12);
                            this.Redo();
                        }
                    } else {
                        while(solver.checkCross(sideValues) < numCross) {
                            Recognition();
                            System.out.println("Running six two");
                            redoMove = lastMove;
                            this.Undo();
                            this.addMove(12);
                            this.Redo();
                        } 
                    }
                }
                isGood = true;
                frontOrBack = false;
                numCross = solver.checkCross(sideValues);
                System.out.println("End of Loop");
            }
            System.out.println("Finished Cross");
            // </editor-fold> // Cross
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int numCorners = solver.checkCorners(sideValues);
            c1 = 0;
            c2 = 0;
            currentSide = 0;
            manipSide = 2;
            while(numCorners < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[(manipSide - 1) * 9] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 one");
                    if(sideValues[2] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(7);
                    this.addMove(10);
                    this.addMove(8);
                } else if(sideValues[(manipSide - 1) * 9 + 2] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 two");
                    if(sideValues[0] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(3);
                    this.addMove(10);
                    this.addMove(4);
                } else if(sideValues[(manipSide - 1) * 9 + 6] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 three");
                    if(sideValues[8] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(2);
                } else if(sideValues[(manipSide - 1) * 9 + 8] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 four");
                    if(sideValues[6] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(5);
                    this.addMove(10);
                    this.addMove(6);
                }
                // </editor-fold> // Top Side
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9] == 1) {
                    System.out.println("Run corner one");
                    switch(manipSide) {
                        case 2:
                            c1 = 51;
                            c2 = 38;
                            break;
                        case 3:
                            c1 = 53;
                            c2 = 11;
                            break;
                        case 4:
                            c1 = 47;
                            c2 = 20;
                            break;
                        case 5:
                            c1 = 45;
                            c2 = 29;
                            break;
                    }
                    currentSide = sideValues[c2 + 2];
                    while(sideValues[c2] != sideValues[(currentSide-1) * 9 + 4]) {
                        Recognition();
                        this.addMove(9);
                        c2 += 9;
                        if(c2 == 47) {
                            c2 = 11;
                        }
                        currentSide = sideValues[c2 + 2];
                    }
                    switch(currentSide) {
                        case 2:
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            break;
                        case 3:
                            this.addMove(8);
                            this.addMove(10);
                            this.addMove(7);
                            break;
                        case 4:
                            this.addMove(2);
                            this.addMove(10);
                            this.addMove(1);
                            break;
                        case 5:
                            this.addMove(6);
                            this.addMove(10);
                            this.addMove(5);
                            break;
                    }
                }
                // </editor-fold> // Top Left
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 2] == 1) {
                    System.out.println("Run corner two");
                    switch(manipSide) {
                        case 2:
                            c1 = 53;
                            c2 = 18;
                            break;
                        case 3:
                            c1 = 47;
                            c2 = 27;
                            break;
                        case 4:
                            c1 = 45;
                            c2 = 36;
                            break;
                        case 5:
                            c1 = 51;
                            c2 = 9;
                            break;
                    }
                    currentSide = sideValues[c2 + 4];
                    while(sideValues[c2] != currentSide) {
                        Recognition();
                        this.addMove(9);
                        c2 += 9;
                        if(c2 == 45) {
                            c2 = 9;
                        }
                        currentSide = sideValues[c2 + 4];
                    }
                    switch(currentSide) {
                        case 2:
                            this.addMove(1);
                            this.addMove(9);
                            this.addMove(2);
                            break;
                        case 3:
                            this.addMove(5);
                            this.addMove(9);
                            this.addMove(6);
                            break;
                        case 4:
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            break;
                        case 5:
                            this.addMove(7);
                            this.addMove(9);
                            this.addMove(8);
                            break;
                    }
                }
                // </editor-fold> // Top Right
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 6] == 1) {
                    System.out.println("Run corner three");
                    switch(manipSide) {
                        case 2:
                            this.addMove(6);
                            this.addMove(10);
                            this.addMove(5);
                            break;
                        case 3:
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            break;
                        case 4:
                            this.addMove(8);
                            this.addMove(10);
                            this.addMove(7);
                            break;
                        case 5:
                            this.addMove(2);
                            this.addMove(10);
                            this.addMove(1);
                            break;
                    }
                }
                // </editor-fold> // Bottom Left
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 8] == 1) {
                    System.out.println("Run corner four");
                    switch(manipSide) {
                        case 2:
                            this.addMove(5);
                            this.addMove(9);
                            this.addMove(6);
                            break;
                        case 3:
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            break;
                        case 4:
                            this.addMove(7);
                            this.addMove(9);
                            this.addMove(8);
                            break;
                        case 5:
                            this.addMove(1);
                            this.addMove(9);
                            this.addMove(2);
                            break;
                    }
                }
                // </editor-fold> // Bottom Right
                else {
                    System.out.println("Run else corner");
                    System.out.println("Before: " + manipSide);
                    manipSide += 1;
                    if(manipSide == 7) {
                        manipSide = 2;
                    }
                    System.out.println("After: " + manipSide);
                }
                numCorners = solver.checkCorners(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Bottom Corners");
            // </editor-fold> // Bottom Corners
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            numMidEdges = solver.checkMidEdges(sideValues);
            manipSide = 2;
            topSticker = 0;
            midSticker = 0;
            numTimesWithout = 0;
            currentSide = 2;
            while(numMidEdges < 4) {
                Recognition();
                //currentSide = manipSide;
                switch(currentSide) {
                    case 2:
                        topSticker = 52;
                        break;
                    case 3:
                        topSticker = 50;
                        break;
                    case 4:
                        topSticker = 46;
                        break;
                    case 5:
                        topSticker = 48;
                        break;
                }
                midSticker = (currentSide - 1) * 9 + 1;
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(numTimesWithout >= 4) {
                    System.out.println("Running stuck");
                    if(sideValues[14] != 6 && sideValues[21] != 6 && sideValues[14] != 2 && sideValues[21] != 3) {
                        this.addMove(4);
                        this.addMove(9);
                        this.addMove(3);
                        this.addMove(9);
                        this.addMove(5);
                        this.addMove(10);
                        this.addMove(6);
                    }
                    if(sideValues[23] != 6 && sideValues[30] != 6 && sideValues[23] != 3 && sideValues[30] != 4) {
                        this.addMove(8);
                        this.addMove(9);
                        this.addMove(7);
                        this.addMove(9);
                        this.addMove(3);
                        this.addMove(10);
                        this.addMove(4);
                    }
                    if(sideValues[32] != 6 && sideValues[39] != 6 && sideValues[32] != 4 && sideValues[39] != 5) {
                        this.addMove(2);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(9);
                        this.addMove(7);
                        this.addMove(10);
                        this.addMove(8);
                    }
                    if(sideValues[41] != 6 && sideValues[12] != 6 && sideValues[41] != 5 && sideValues[12] != 2) {
                        this.addMove(6);
                        this.addMove(9);
                        this.addMove(5);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(10);
                        this.addMove(2);
                    }
                    numTimesWithout = 0;
                }
                // </editor-fold> // Stuck
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[midSticker] != 6 && sideValues[topSticker] != 6) {
                    System.out.println("Running normal");
                    System.out.println("Current Side: " + currentSide);
                    numTimesWithout = 0;
                    while(sideValues[midSticker] != currentSide) {
                        Recognition();
                        System.out.println("Running midSticker while");
                        System.out.println("currentSide before: " + currentSide);
                        System.out.println("midSticker before: " + midSticker);
                        this.addMove(9);
                        currentSide ++;
                        if(currentSide == 6) {
                            currentSide = 2;
                        }
                        midSticker = (currentSide - 1) * 9 + 1;
                        System.out.println("currentSide after: " + currentSide);
                        System.out.println("midSticker after: " + midSticker);
                    }
                    switch(currentSide) {
                        case 2:
                            topSticker = 52;
                            break;
                        case 3:
                            topSticker = 50;
                            break;
                        case 4:
                            topSticker = 46;
                            break;
                        case 5:
                            topSticker = 48;
                            break;
                    }
                    int plusOne = sideValues[topSticker] + 1;
                    if(plusOne == 6) {plusOne = 2;}
                    int minusOne = sideValues[topSticker] - 1;
                    if(minusOne == 1) {minusOne = 5;}
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    if(plusOne == currentSide) {
                        System.out.println("Running plus");
                        this.addMove(9);
                        switch(currentSide) {
                            case 2:
                                System.out.println("Running plus two");
                                this.addMove(1);
                                this.addMove(10);
                                this.addMove(2);
                                this.addMove(10);
                                this.addMove(6);
                                this.addMove(9);
                                this.addMove(5);
                                break;
                            case 3:
                                System.out.println("Running plus three");
                                this.addMove(5);
                                this.addMove(10);
                                this.addMove(6);
                                this.addMove(10);
                                this.addMove(4);
                                this.addMove(9);
                                this.addMove(3);
                                break;
                            case 4:
                                System.out.println("Running plus four");
                                this.addMove(3);
                                this.addMove(10);
                                this.addMove(4);
                                this.addMove(10);
                                this.addMove(8);
                                this.addMove(9);
                                this.addMove(7);
                                break;
                            case 5:
                                System.out.println("Running plus five");
                                this.addMove(7);
                                this.addMove(10);
                                this.addMove(8);
                                this.addMove(10);
                                this.addMove(2);
                                this.addMove(9);
                                this.addMove(1);
                                break;
                        }
                    }
                    // </editor-fold> // Plus One
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    else if(minusOne == currentSide) {
                        System.out.println("Running minus");
                        this.addMove(10);
                        switch(currentSide) {
                            case 2:
                                System.out.println("Running minus two");
                                this.addMove(4);
                                this.addMove(9);
                                this.addMove(3);
                                this.addMove(9);
                                this.addMove(5);
                                this.addMove(10);
                                this.addMove(6);
                                break;
                            case 3:
                                System.out.println("Running minus three");
                                this.addMove(8);
                                this.addMove(9);
                                this.addMove(7);
                                this.addMove(9);
                                this.addMove(3);
                                this.addMove(10);
                                this.addMove(4);
                                break;
                            case 4:
                                System.out.println("Running minus four");
                                this.addMove(2);
                                this.addMove(9);
                                this.addMove(1);
                                this.addMove(9);
                                this.addMove(7);
                                this.addMove(10);
                                this.addMove(8);
                                break;
                            case 5:
                                System.out.println("Running minus five");
                                this.addMove(6);
                                this.addMove(9);
                                this.addMove(5);
                                this.addMove(9);
                                this.addMove(1);
                                this.addMove(10);
                                this.addMove(2);
                                break;
                        }
                    }
                    // </editor-fold> // Minus One
                    else {
                        neitherCount ++;
                        System.out.println("Neither");
                        if(neitherCount == 40) {
                            //this.MakeMove();
                            return;
                        }
                    }
                }
                // </editor-fold> // Normal
                else {
                    numTimesWithout++;
                    System.out.println("Before: " + currentSide);
                    currentSide += 1;
                    if(currentSide == 6) {
                        currentSide = 2;
                    }
                    System.out.println("After: " + currentSide);
                }
                numMidEdges = solver.checkMidEdges(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Middle Edges");
            // </editor-fold> // Middle Edges
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            manipSide = 6;
            currentSide = 6;
            numTimesWithout = 0;
            int secondTimesWithout = 0;
            boolean topSolved = solver.checkTopSide(sideValues);
            while(topSolved == false) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running left ribbon");
                    if(sideValues[9] == 6) {
                        this.addMove(1);
                        this.addMove(9);
                        this.addMove(2);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(10);
                        this.addMove(10);
                        this.addMove(2);
                    } else {
                        System.out.println("Left ribbon didn't run");
                        this.addMove(10);
                    }
                }
                // </editor-fold> // Left Ribbon
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] == 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running right ribbon");
                    if(sideValues[11] == 6) {
                        this.addMove(4);
                        this.addMove(10);
                        this.addMove(3);
                        this.addMove(10);
                        this.addMove(4);
                        this.addMove(10);
                        this.addMove(10);
                        this.addMove(3);
                    } else {
                        System.out.println("Right ribbon didn't run");
                        this.addMove(10);
                    }
                }
                // </editor-fold> // Right Ribbon
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running top cross");
                    while(sideValues[9] != 6 && sideValues[11] != 6) {
                        Recognition();
                        this.addMove(10);
                    }
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Top Cross
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] == 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running weird pattern");
                    if(sideValues[38] != 6) {
                        this.addMove(10);
                        this.addMove(10);
                    }
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Weird
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] == 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] == 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running block");
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Normal Block
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] == 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running opposite block");
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Opposite Block
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] == 6 && sideValues[46] == 6 && sideValues[47] == 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running up block");
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Up Block
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] == 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running down block");
                    this.addMove(10);
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Down Block
                else {
                    System.out.println("Running else top");
                    numTimesWithout++;
                    if(numTimesWithout > 4) {
                        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                        if(sideValues[46] == 6 && sideValues[48] == 6 && sideValues[49] == 6 && solver.checkTopStickers(sideValues) <= 7) {
                            System.out.println("Running elbow");
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(5);
                            this.addMove(10);
                            this.addMove(6);
                            this.addMove(4);
                            numTimesWithout = 0;
                        }
                        // </editor-fold> // Elbow
                        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                        else if(sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && solver.checkTopStickers(sideValues) <= 7) {
                            System.out.println("Running line");
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(5);
                            this.addMove(10);
                            this.addMove(6);
                            this.addMove(4);
                            if(sideValues[49] == 6 && sideValues[50] == 6 && sideValues[52] == 6) {
                                this.addMove(10);
                                this.addMove(10);
                            }
                            numTimesWithout = 0;
                        }
                        // </editor-fold> // Line
                        else {
                            this.addMove(10);
                        }
                        secondTimesWithout++;
                        if(secondTimesWithout > 4) {
                            System.out.println("Running point");
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(5);
                            this.addMove(10);
                            this.addMove(6);
                            this.addMove(4);
                            secondTimesWithout = 0;
                        }
                    } else {
                        this.addMove(10);
                    }
                }
                topSolved = solver.checkTopSide(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Top Side");
            // </editor-fold> // Top Side
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            currentSide = 0;
            manipSide = 0;
            int numTopEdges = solver.checkTopEdges(sideValues);
            while(numTopEdges < 4) {
                Recognition();
                if((sideValues[9] == 2 || sideValues[9] ==  4) && (sideValues[11] == 2 || sideValues[11] == 4)) {
                    System.out.println("Running twists");
                    this.addMove(3);
                    this.addMove(6);
                    this.addMove(3);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(4);
                    this.addMove(5);
                    this.addMove(3);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(4);
                    this.addMove(4);
                }
                else {
                    System.out.println("Running else edge");
                    this.addMove(10);
                }
                numTopEdges = solver.checkTopEdges(sideValues);
                //System.out.println(numTopEdges);
                System.out.println("End of loop");
            }
            System.out.println("Finished Top Edges");
            // </editor-fold> // Top Edges
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            currentSide = 0;
            manipSide = 0;
            int whichSide = 0;
            while(sideValues[9] != 2) {
                Recognition();
                this.addMove(10);
            }
            int numTopCenters = solver.checkTopCenters(sideValues);
            while(numTopCenters < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(numTopCenters == 0) {
                    System.out.println("Running center none");
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(1);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(3);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                }
                // </editor-fold> // Center None
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[10] == 2) {
                    System.out.println("Running center one");
                    if(sideValues[19] == 4) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(whichSide);
                    this.addMove(4);
                    this.addMove(1);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(3);
                    this.addMove(2);
                    this.addMove(whichSide);
                    this.addMove(8);
                    this.addMove(8);
                }
                // </editor-fold> // Center One
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[19] == 3) {
                    System.out.println("Running center two");
                    if(sideValues[28] == 5) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(whichSide);
                    this.addMove(8);
                    this.addMove(5);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(7);
                    this.addMove(6);
                    this.addMove(whichSide);
                    this.addMove(2);
                    this.addMove(2);
                }
                // </editor-fold> // Center Two
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[28] == 4) {
                    System.out.println("Running center three");
                    if(sideValues[37] == 2) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(whichSide);
                    this.addMove(2);
                    this.addMove(3);
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(1);
                    this.addMove(4);
                    this.addMove(whichSide);
                    this.addMove(6);
                    this.addMove(6);
                }
                // </editor-fold> // Center Three
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[37] == 5) {
                    if(sideValues[10] == 3) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    System.out.println("Running center four");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(whichSide);
                    this.addMove(6);
                    this.addMove(7);
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(5);
                    this.addMove(8);
                    this.addMove(whichSide);
                    this.addMove(4);
                    this.addMove(4);
                }
                // </editor-fold> // Center Four
                else {
                    System.out.println("Running else center");
                }
                numTopCenters = solver.checkTopCenters(sideValues);
                //System.out.println(numTopEdges);
                System.out.println("End of loop");
            }
            System.out.println("Finished Top Centers");
            // </editor-fold> // Top Centers
            this.MakeMove(methodValue);
        }
        // </editor-fold> // Petrus
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(methodValue == 3) {
            SolvingFunctions solver = new SolvingFunctions(this.sideValues);
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            boolean isGood = true, frontOrBack = false;;
            int numCross = solver.checkCross(this.sideValues);
            manipSide = 2;
            while(numCross < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[(manipSide-1) * 9 + 3] == 1) {
                    System.out.println("Running one");
                    isGood = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(2);
                            break;
                        case 3:
                            this.addMove(6);
                            break;
                        case 4:
                            this.addMove(4);
                            break;
                        case 5:
                            this.addMove(8);
                            break;
                        case 6:
                            this.addMove(2);
                            frontOrBack = true;
                            //this.addMove(2);
                            break;
                    }
                }
                // </editor-fold> // One
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 5] == 1) {
                    System.out.println("Running two");
                    isGood = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(3);
                            break;
                        case 3:
                            this.addMove(7);
                            break;
                        case 4:
                            this.addMove(1);
                            break;
                        case 5:
                            this.addMove(5);
                            break;
                        case 6:
                            this.addMove(3);
                            frontOrBack = true;
                            //this.addMove(3);
                            break;
                    }
                }
                // </editor-fold> // Two
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 1] == 1) {
                    System.out.println("Running three");
                    isGood = true;
                    frontOrBack = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(6);
                            break;
                        case 3:
                            this.addMove(4);
                            break;
                        case 4:
                            this.addMove(8);
                            break;
                        case 5:
                            this.addMove(2);
                            break;
                        case 6:
                            this.addMove(8);
                            break;
                    }
                }
                // </editor-fold> // Three
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 7] == 1) {
                    System.out.println("Running four");
                    isGood = true;
                    frontOrBack = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(5);
                            break;
                        case 3:
                            this.addMove(3);
                            break;
                        case 4:
                            this.addMove(7);
                            break;
                        case 5:
                            this.addMove(1);
                            break;
                        case 6:
                            this.addMove(5);
                            //this.addMove(3);
                            break;
                    }
                }
                // </editor-fold> // Four
                else {
                    System.out.println("Running five");
                    manipSide += 1;
                    if(manipSide == 7)
                       manipSide = 2;
                    System.out.println(manipSide);
                    isGood = false;
                }
                if(isGood == true) {
                    if(frontOrBack == false) {
                        while(solver.checkCross(sideValues) <= numCross) {
                            Recognition();
                            System.out.println("Running six one");
                            redoMove = lastMove;
                            this.Undo();
                            this.addMove(12);
                            this.Redo();
                        }
                    } else {
                        while(solver.checkCross(sideValues) < numCross) {
                            Recognition();
                            System.out.println("Running six two");
                            redoMove = lastMove;
                            this.Undo();
                            this.addMove(12);
                            this.Redo();
                        } 
                    }
                }
                isGood = true;
                frontOrBack = false;
                numCross = solver.checkCross(sideValues);
                System.out.println("End of Loop");
            }
            System.out.println("Finished Cross");
            // </editor-fold> // Cross
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int numMatched = solver.checkAlignment(this.sideValues);
            while(numMatched < 4) {
                Recognition();
                System.out.println(numMatched);
                if(numMatched < 2) {
                    while(numMatched < 2) {
                        Recognition();
                        this.addMove(12);
                        numMatched = solver.checkAlignment(this.sideValues);
                    }
                } else if(sideValues[13] == sideValues[16] && sideValues[31] == sideValues[34]) {
                    System.out.println("Ran one");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(4);
                } else if(sideValues[22] == sideValues[25] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran two");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[13] == sideValues[16] && sideValues[22] == sideValues[25]) {
                    System.out.println("Ran three");
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                } else if(sideValues[22] == sideValues[25] && sideValues[31] == sideValues[34]) {
                    System.out.println("Ran four");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(10);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[31] == sideValues[34] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran five");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(9);
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[13] == sideValues[16] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran six");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(9);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(4);
                }
                numMatched = solver.checkAlignment(sideValues);
            }
            /*this.addMove(5);
            this.addMove(5);
            this.addMove(9);
            this.addMove(9);
            this.addMove(7);
            this.addMove(7);
            this.addMove(9);
            this.addMove(9);
            this.addMove(5);
            this.addMove(5);*/
            System.out.println("Finished Alignment");
            // </editor-fold> // Twisted Alignment
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int numCorners = solver.checkCorners(sideValues);
            int c1 = 0, c2 = 0, currentSide = 0;
            manipSide = 2;
            while(numCorners < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[(manipSide - 1) * 9] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 one");
                    if(sideValues[2] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(7);
                    this.addMove(10);
                    this.addMove(8);
                } else if(sideValues[(manipSide - 1) * 9 + 2] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 two");
                    if(sideValues[0] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(3);
                    this.addMove(10);
                    this.addMove(4);
                } else if(sideValues[(manipSide - 1) * 9 + 6] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 three");
                    if(sideValues[8] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(2);
                } else if(sideValues[(manipSide - 1) * 9 + 8] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 four");
                    if(sideValues[6] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(5);
                    this.addMove(10);
                    this.addMove(6);
                }
                // </editor-fold> // Top Side
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9] == 1) {
                    System.out.println("Run corner one");
                    switch(manipSide) {
                        case 2:
                            c1 = 51;
                            c2 = 38;
                            break;
                        case 3:
                            c1 = 53;
                            c2 = 11;
                            break;
                        case 4:
                            c1 = 47;
                            c2 = 20;
                            break;
                        case 5:
                            c1 = 45;
                            c2 = 29;
                            break;
                    }
                    currentSide = sideValues[c2 + 2];
                    while(sideValues[c2] != sideValues[(currentSide-1) * 9 + 4]) {
                        Recognition();
                        this.addMove(9);
                        c2 += 9;
                        if(c2 == 47) {
                            c2 = 11;
                        }
                        currentSide = sideValues[c2 + 2];
                    }
                    switch(currentSide) {
                        case 2:
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            break;
                        case 3:
                            this.addMove(8);
                            this.addMove(10);
                            this.addMove(7);
                            break;
                        case 4:
                            this.addMove(2);
                            this.addMove(10);
                            this.addMove(1);
                            break;
                        case 5:
                            this.addMove(6);
                            this.addMove(10);
                            this.addMove(5);
                            break;
                    }
                }
                // </editor-fold> // Top Left
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 2] == 1) {
                    System.out.println("Run corner two");
                    switch(manipSide) {
                        case 2:
                            c1 = 53;
                            c2 = 18;
                            break;
                        case 3:
                            c1 = 47;
                            c2 = 27;
                            break;
                        case 4:
                            c1 = 45;
                            c2 = 36;
                            break;
                        case 5:
                            c1 = 51;
                            c2 = 9;
                            break;
                    }
                    currentSide = sideValues[c2 + 4];
                    while(sideValues[c2] != currentSide) {
                        Recognition();
                        this.addMove(9);
                        c2 += 9;
                        if(c2 == 45) {
                            c2 = 9;
                        }
                        currentSide = sideValues[c2 + 4];
                    }
                    switch(currentSide) {
                        case 2:
                            this.addMove(1);
                            this.addMove(9);
                            this.addMove(2);
                            break;
                        case 3:
                            this.addMove(5);
                            this.addMove(9);
                            this.addMove(6);
                            break;
                        case 4:
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            break;
                        case 5:
                            this.addMove(7);
                            this.addMove(9);
                            this.addMove(8);
                            break;
                    }
                }
                // </editor-fold> // Top Right
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 6] == 1) {
                    System.out.println("Run corner three");
                    switch(manipSide) {
                        case 2:
                            this.addMove(6);
                            this.addMove(10);
                            this.addMove(5);
                            break;
                        case 3:
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            break;
                        case 4:
                            this.addMove(8);
                            this.addMove(10);
                            this.addMove(7);
                            break;
                        case 5:
                            this.addMove(2);
                            this.addMove(10);
                            this.addMove(1);
                            break;
                    }
                }
                // </editor-fold> // Bottom Left
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 8] == 1) {
                    System.out.println("Run corner four");
                    switch(manipSide) {
                        case 2:
                            this.addMove(5);
                            this.addMove(9);
                            this.addMove(6);
                            break;
                        case 3:
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            break;
                        case 4:
                            this.addMove(7);
                            this.addMove(9);
                            this.addMove(8);
                            break;
                        case 5:
                            this.addMove(1);
                            this.addMove(9);
                            this.addMove(2);
                            break;
                    }
                }
                // </editor-fold> // Bottom Right
                else {
                    System.out.println("Run else corner");
                    System.out.println("Before: " + manipSide);
                    manipSide += 1;
                    if(manipSide == 7) {
                        manipSide = 2;
                    }
                    System.out.println("After: " + manipSide);
                }
                numCorners = solver.checkCorners(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Bottom Corners");
            // </editor-fold> // Bottom Corners
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            int numMidEdges = solver.checkMidEdges(sideValues);
            manipSide = 2;
            int topSticker = 0, midSticker = 0, numTimesWithout = 0;
            currentSide = 2;
            while(numMidEdges < 4) {
                Recognition();
                //currentSide = manipSide;
                switch(currentSide) {
                    case 2:
                        topSticker = 52;
                        break;
                    case 3:
                        topSticker = 50;
                        break;
                    case 4:
                        topSticker = 46;
                        break;
                    case 5:
                        topSticker = 48;
                        break;
                }
                midSticker = (currentSide - 1) * 9 + 1;
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(numTimesWithout >= 4) {
                    System.out.println("Running stuck");
                    if(sideValues[14] != 6 && sideValues[21] != 6 && sideValues[14] != 2 && sideValues[21] != 3) {
                        this.addMove(4);
                        this.addMove(9);
                        this.addMove(3);
                        this.addMove(9);
                        this.addMove(5);
                        this.addMove(10);
                        this.addMove(6);
                    }
                    if(sideValues[23] != 6 && sideValues[30] != 6 && sideValues[23] != 3 && sideValues[30] != 4) {
                        this.addMove(8);
                        this.addMove(9);
                        this.addMove(7);
                        this.addMove(9);
                        this.addMove(3);
                        this.addMove(10);
                        this.addMove(4);
                    }
                    if(sideValues[32] != 6 && sideValues[39] != 6 && sideValues[32] != 4 && sideValues[39] != 5) {
                        this.addMove(2);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(9);
                        this.addMove(7);
                        this.addMove(10);
                        this.addMove(8);
                    }
                    if(sideValues[41] != 6 && sideValues[12] != 6 && sideValues[41] != 5 && sideValues[12] != 2) {
                        this.addMove(6);
                        this.addMove(9);
                        this.addMove(5);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(10);
                        this.addMove(2);
                    }
                    numTimesWithout = 0;
                }
                // </editor-fold> // Stuck
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[midSticker] != 6 && sideValues[topSticker] != 6) {
                    System.out.println("Running normal");
                    System.out.println("Current Side: " + currentSide);
                    numTimesWithout = 0;
                    while(sideValues[midSticker] != currentSide) {
                        Recognition();
                        System.out.println("Running midSticker while");
                        System.out.println("currentSide before: " + currentSide);
                        System.out.println("midSticker before: " + midSticker);
                        this.addMove(9);
                        currentSide ++;
                        if(currentSide == 6) {
                            currentSide = 2;
                        }
                        midSticker = (currentSide - 1) * 9 + 1;
                        System.out.println("currentSide after: " + currentSide);
                        System.out.println("midSticker after: " + midSticker);
                    }
                    switch(currentSide) {
                        case 2:
                            topSticker = 52;
                            break;
                        case 3:
                            topSticker = 50;
                            break;
                        case 4:
                            topSticker = 46;
                            break;
                        case 5:
                            topSticker = 48;
                            break;
                    }
                    int plusOne = sideValues[topSticker] + 1;
                    if(plusOne == 6) {plusOne = 2;}
                    int minusOne = sideValues[topSticker] - 1;
                    if(minusOne == 1) {minusOne = 5;}
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    if(plusOne == currentSide) {
                        System.out.println("Running plus");
                        this.addMove(9);
                        switch(currentSide) {
                            case 2:
                                System.out.println("Running plus two");
                                this.addMove(1);
                                this.addMove(10);
                                this.addMove(2);
                                this.addMove(10);
                                this.addMove(6);
                                this.addMove(9);
                                this.addMove(5);
                                break;
                            case 3:
                                System.out.println("Running plus three");
                                this.addMove(5);
                                this.addMove(10);
                                this.addMove(6);
                                this.addMove(10);
                                this.addMove(4);
                                this.addMove(9);
                                this.addMove(3);
                                break;
                            case 4:
                                System.out.println("Running plus four");
                                this.addMove(3);
                                this.addMove(10);
                                this.addMove(4);
                                this.addMove(10);
                                this.addMove(8);
                                this.addMove(9);
                                this.addMove(7);
                                break;
                            case 5:
                                System.out.println("Running plus five");
                                this.addMove(7);
                                this.addMove(10);
                                this.addMove(8);
                                this.addMove(10);
                                this.addMove(2);
                                this.addMove(9);
                                this.addMove(1);
                                break;
                        }
                    }
                    // </editor-fold> // Plus One
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    else if(minusOne == currentSide) {
                        System.out.println("Running minus");
                        this.addMove(10);
                        switch(currentSide) {
                            case 2:
                                System.out.println("Running minus two");
                                this.addMove(4);
                                this.addMove(9);
                                this.addMove(3);
                                this.addMove(9);
                                this.addMove(5);
                                this.addMove(10);
                                this.addMove(6);
                                break;
                            case 3:
                                System.out.println("Running minus three");
                                this.addMove(8);
                                this.addMove(9);
                                this.addMove(7);
                                this.addMove(9);
                                this.addMove(3);
                                this.addMove(10);
                                this.addMove(4);
                                break;
                            case 4:
                                System.out.println("Running minus four");
                                this.addMove(2);
                                this.addMove(9);
                                this.addMove(1);
                                this.addMove(9);
                                this.addMove(7);
                                this.addMove(10);
                                this.addMove(8);
                                break;
                            case 5:
                                System.out.println("Running minus five");
                                this.addMove(6);
                                this.addMove(9);
                                this.addMove(5);
                                this.addMove(9);
                                this.addMove(1);
                                this.addMove(10);
                                this.addMove(2);
                                break;
                        }
                    }
                    // </editor-fold> // Minus One
                    else {
                        neitherCount ++;
                        System.out.println("Neither");
                        if(neitherCount == 40) {
                            //this.MakeMove();
                            return;
                        }
                    }
                }
                // </editor-fold> // Normal
                else {
                    numTimesWithout++;
                    System.out.println("Before: " + currentSide);
                    currentSide += 1;
                    if(currentSide == 6) {
                        currentSide = 2;
                    }
                    System.out.println("After: " + currentSide);
                }
                numMidEdges = solver.checkMidEdges(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Middle Edges");
            // </editor-fold> // Middle Edges
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            boolean topCorners = solver.checkTopCorners(sideValues);
            int fifthCounter = 0;
            while(!topCorners) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[45] == 6 && sideValues[9] == 6 && sideValues[18] == 6) {
                    System.out.println("Running B");
                    this.addMove(3);
                    this.addMove(9);
                    this.addMove(4);
                    this.addMove(9);
                    this.addMove(3);
                    this.addMove(9);
                    this.addMove(9);
                    this.addMove(4);
                }
                // </editor-fold> // B
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[51] == 6 && sideValues[11] == 6 && sideValues[20] == 6) {
                    System.out.println("Running C");
                    this.addMove(4);
                    this.addMove(10);
                    this.addMove(3);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(3);
                } 
                // </editor-fold> // C
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[47] == 6 && sideValues[51] == 6 && sideValues[11] == 6) {
                    System.out.println("Running D");
                    this.addMove(3);
                    this.addMove(12);
                    this.addMove(3);
                    this.addMove(12);
                    this.addMove(3);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(11);
                    this.addMove(4);
                    this.addMove(11);
                    this.addMove(4);
                }
                // </editor-fold> // D
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[51] == 6 && sideValues[53] == 6 && sideValues[27] == 6 && sideValues[29] == 6) {
                    System.out.println("Running E");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(5);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(9);
                    this.addMove(5);
                    this.addMove(9);
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(4);
                    this.addMove(5);
                    this.addMove(4);
                }
                // </editor-fold> // E
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[51] == 6 && sideValues[53] == 6 && sideValues[20] == 6 && sideValues[36] == 6) {
                    System.out.println("Running F");
                    this.addMove(3);
                    this.addMove(3);
                    this.addMove(6);
                    this.addMove(9);
                    this.addMove(5);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(10);
                    this.addMove(5);
                    this.addMove(5);
                    this.addMove(3);
                    this.addMove(6);
                    this.addMove(3);
                }
                // </editor-fold> // F
                else {
                    System.out.println("Running else top corners");
                    fifthCounter++;
                    while(fifthCounter > 10) {
                        Recognition();
                        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                        if(sideValues[11] == 6) {
                            System.out.println("Running G");
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            this.addMove(10);
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(10);
                            this.addMove(3);
                            fifthCounter = 0;
                        }
                        // </editor-fold> // G
                        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                        else if(sideValues[18] == 6 && sideValues[20] == 6) {
                            System.out.println("Running H");
                            this.addMove(3);
                            this.addMove(6);
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            this.addMove(6);
                            this.addMove(4);
                            this.addMove(5);
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            fifthCounter = 0;
                        }
                        // </editor-fold> // H
                        else {
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            this.addMove(9);
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(9);
                            this.addMove(4);
                        }
                        this.addMove(9);
                    }
                    this.addMove(9);
                }
                topCorners = solver.checkTopCorners(sideValues);
            }
            System.out.println("Finished top corners");
            // </editor-fold> // Place Top Corners
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            currentSide = 0;
            manipSide = 0;
            int numTopEdges = solver.checkTopEdges(sideValues);
            while(numTopEdges < 4) {
                Recognition();
                if((sideValues[9] == 2 || sideValues[9] ==  4) && (sideValues[11] == 2 || sideValues[11] == 4)) {
                    System.out.println("Running twists");
                    this.addMove(3);
                    this.addMove(6);
                    this.addMove(3);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(4);
                    this.addMove(5);
                    this.addMove(3);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(4);
                    this.addMove(4);
                }
                else {
                    System.out.println("Running else edge");
                    this.addMove(10);
                }
                numTopEdges = solver.checkTopEdges(sideValues);
                System.out.println("End of loop");
            }
            while(sideValues[9] != sideValues[13]) {
                Recognition();
                this.addMove(9);
            }
            System.out.println("Finished Permuting Top Corners");
            // </editor-fold> // Permute Top Corners
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            isGood = true;
            frontOrBack = false;;
            numCross = solver.checkCross(this.sideValues);
            manipSide = 2;
            while(numCross < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[(manipSide-1) * 9 + 3] == 1) {
                    System.out.println("Running one");
                    isGood = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(2);
                            break;
                        case 3:
                            this.addMove(6);
                            break;
                        case 4:
                            this.addMove(4);
                            break;
                        case 5:
                            this.addMove(8);
                            break;
                        case 6:
                            this.addMove(2);
                            frontOrBack = true;
                            //this.addMove(2);
                            break;
                    }
                }
                // </editor-fold> // One
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 5] == 1) {
                    System.out.println("Running two");
                    isGood = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(3);
                            break;
                        case 3:
                            this.addMove(7);
                            break;
                        case 4:
                            this.addMove(1);
                            break;
                        case 5:
                            this.addMove(5);
                            break;
                        case 6:
                            this.addMove(3);
                            frontOrBack = true;
                            //this.addMove(3);
                            break;
                    }
                }
                // </editor-fold> // Two
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 1] == 1) {
                    System.out.println("Running three");
                    isGood = true;
                    frontOrBack = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(6);
                            break;
                        case 3:
                            this.addMove(4);
                            break;
                        case 4:
                            this.addMove(8);
                            break;
                        case 5:
                            this.addMove(2);
                            break;
                        case 6:
                            this.addMove(8);
                            break;
                    }
                }
                // </editor-fold> // Three
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide-1) * 9 + 7] == 1) {
                    System.out.println("Running four");
                    isGood = true;
                    frontOrBack = true;
                    switch(manipSide) {
                        case 2:
                            this.addMove(5);
                            break;
                        case 3:
                            this.addMove(3);
                            break;
                        case 4:
                            this.addMove(7);
                            break;
                        case 5:
                            this.addMove(1);
                            break;
                        case 6:
                            this.addMove(5);
                            //this.addMove(3);
                            break;
                    }
                }
                // </editor-fold> // Four
                else {
                    System.out.println("Running five");
                    manipSide += 1;
                    if(manipSide == 7)
                       manipSide = 2;
                    System.out.println(manipSide);
                    isGood = false;
                }
                if(isGood == true) {
                    if(frontOrBack == false) {
                        while(solver.checkCross(sideValues) <= numCross) {
                            Recognition();
                            System.out.println("Running six one");
                            redoMove = lastMove;
                            this.Undo();
                            this.addMove(12);
                            this.Redo();
                        }
                    } else {
                        while(solver.checkCross(sideValues) < numCross) {
                            Recognition();
                            System.out.println("Running six two");
                            redoMove = lastMove;
                            this.Undo();
                            this.addMove(12);
                            this.Redo();
                        } 
                    }
                }
                isGood = true;
                frontOrBack = false;
                numCross = solver.checkCross(sideValues);
                System.out.println("End of Loop");
            }
            System.out.println("Finished Cross");
            // </editor-fold> // Cross
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            numMatched = solver.checkAlignment(this.sideValues);
            while(numMatched < 4) {
                Recognition();
                System.out.println(numMatched);
                if(numMatched < 2) {
                    while(numMatched < 2) {
                        Recognition();
                        this.addMove(12);
                        numMatched = solver.checkAlignment(this.sideValues);
                    }
                } else if(sideValues[13] == sideValues[16] && sideValues[31] == sideValues[34]) {
                    System.out.println("Ran one");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(4);
                } else if(sideValues[22] == sideValues[25] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran two");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[13] == sideValues[16] && sideValues[22] == sideValues[25]) {
                    System.out.println("Ran three");
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                } else if(sideValues[22] == sideValues[25] && sideValues[31] == sideValues[34]) {
                    System.out.println("Ran four");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(10);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[31] == sideValues[34] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran five");
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(9);
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(10);
                    this.addMove(6);
                    this.addMove(6);
                } else if(sideValues[13] == sideValues[16] && sideValues[40] == sideValues[43]) {
                    System.out.println("Ran six");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(9);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(4);
                }
                numMatched = solver.checkAlignment(sideValues);
            }
            System.out.println("Finished Alignment");
            // </editor-fold> // Alignment     
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            this.addMove(4);
            this.addMove(10);
            this.addMove(3);
            numCorners = solver.checkCorners(sideValues);
            c1 = 0;
            c2 = 0;
            currentSide = 0;
            manipSide = 2;
            while(numCorners < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[(manipSide - 1) * 9] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 one");
                    if(sideValues[2] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(7);
                    this.addMove(10);
                    this.addMove(8);
                } else if(sideValues[(manipSide - 1) * 9 + 2] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 two");
                    if(sideValues[0] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(3);
                    this.addMove(10);
                    this.addMove(4);
                } else if(sideValues[(manipSide - 1) * 9 + 6] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 three");
                    if(sideValues[8] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(2);
                } else if(sideValues[(manipSide - 1) * 9 + 8] == 1 && manipSide == 6) {
                    System.out.println("Running manip 6 four");
                    if(sideValues[6] == 1) {
                        this.addMove(10);
                        continue;
                    }
                    this.addMove(5);
                    this.addMove(10);
                    this.addMove(6);
                }
                // </editor-fold> // Top Side
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9] == 1) {
                    System.out.println("Run corner one");
                    switch(manipSide) {
                        case 2:
                            c1 = 51;
                            c2 = 38;
                            break;
                        case 3:
                            c1 = 53;
                            c2 = 11;
                            break;
                        case 4:
                            c1 = 47;
                            c2 = 20;
                            break;
                        case 5:
                            c1 = 45;
                            c2 = 29;
                            break;
                    }
                    currentSide = sideValues[c2 + 2];
                    while(sideValues[c2] != sideValues[(currentSide-1) * 9 + 4]) {
                        Recognition();
                        this.addMove(9);
                        c2 += 9;
                        if(c2 == 47) {
                            c2 = 11;
                        }
                        currentSide = sideValues[c2 + 2];
                    }
                    switch(currentSide) {
                        case 2:
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            break;
                        case 3:
                            this.addMove(8);
                            this.addMove(10);
                            this.addMove(7);
                            break;
                        case 4:
                            this.addMove(2);
                            this.addMove(10);
                            this.addMove(1);
                            break;
                        case 5:
                            this.addMove(6);
                            this.addMove(10);
                            this.addMove(5);
                            break;
                    }
                }
                // </editor-fold> // Top Left
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 2] == 1) {
                    System.out.println("Run corner two");
                    switch(manipSide) {
                        case 2:
                            c1 = 53;
                            c2 = 18;
                            break;
                        case 3:
                            c1 = 47;
                            c2 = 27;
                            break;
                        case 4:
                            c1 = 45;
                            c2 = 36;
                            break;
                        case 5:
                            c1 = 51;
                            c2 = 9;
                            break;
                    }
                    currentSide = sideValues[c2 + 4];
                    while(sideValues[c2] != currentSide) {
                        Recognition();
                        this.addMove(9);
                        c2 += 9;
                        if(c2 == 45) {
                            c2 = 9;
                        }
                        currentSide = sideValues[c2 + 4];
                    }
                    switch(currentSide) {
                        case 2:
                            this.addMove(1);
                            this.addMove(9);
                            this.addMove(2);
                            break;
                        case 3:
                            this.addMove(5);
                            this.addMove(9);
                            this.addMove(6);
                            break;
                        case 4:
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            break;
                        case 5:
                            this.addMove(7);
                            this.addMove(9);
                            this.addMove(8);
                            break;
                    }
                }
                // </editor-fold> // Top Right
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 6] == 1) {
                    System.out.println("Run corner three");
                    switch(manipSide) {
                        case 2:
                            this.addMove(6);
                            this.addMove(10);
                            this.addMove(5);
                            break;
                        case 3:
                            this.addMove(4);
                            this.addMove(10);
                            this.addMove(3);
                            break;
                        case 4:
                            this.addMove(8);
                            this.addMove(10);
                            this.addMove(7);
                            break;
                        case 5:
                            this.addMove(2);
                            this.addMove(10);
                            this.addMove(1);
                            break;
                    }
                }
                // </editor-fold> // Bottom Left
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[(manipSide - 1) * 9 + 8] == 1) {
                    System.out.println("Run corner four");
                    switch(manipSide) {
                        case 2:
                            this.addMove(5);
                            this.addMove(9);
                            this.addMove(6);
                            break;
                        case 3:
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(4);
                            break;
                        case 4:
                            this.addMove(7);
                            this.addMove(9);
                            this.addMove(8);
                            break;
                        case 5:
                            this.addMove(1);
                            this.addMove(9);
                            this.addMove(2);
                            break;
                    }
                }
                // </editor-fold> // Bottom Right
                else {
                    System.out.println("Run else corner");
                    System.out.println("Before: " + manipSide);
                    manipSide += 1;
                    if(manipSide == 7) {
                        manipSide = 2;
                    }
                    System.out.println("After: " + manipSide);
                }
                numCorners = solver.checkCorners(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Bottom Corners");
            // </editor-fold> // Bottom Corners
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            numMidEdges = solver.checkMidEdges(sideValues);
            manipSide = 2;
            topSticker = 0;
            midSticker = 0;
            numTimesWithout = 0;
            currentSide = 2;
            while(numMidEdges < 4) {
                Recognition();
                //currentSide = manipSide;
                switch(currentSide) {
                    case 2:
                        topSticker = 52;
                        break;
                    case 3:
                        topSticker = 50;
                        break;
                    case 4:
                        topSticker = 46;
                        break;
                    case 5:
                        topSticker = 48;
                        break;
                }
                midSticker = (currentSide - 1) * 9 + 1;
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(numTimesWithout >= 4) {
                    System.out.println("Running stuck");
                    if(sideValues[14] != 6 && sideValues[21] != 6 && sideValues[14] != 2 && sideValues[21] != 3) {
                        this.addMove(4);
                        this.addMove(9);
                        this.addMove(3);
                        this.addMove(9);
                        this.addMove(5);
                        this.addMove(10);
                        this.addMove(6);
                    }
                    if(sideValues[23] != 6 && sideValues[30] != 6 && sideValues[23] != 3 && sideValues[30] != 4) {
                        this.addMove(8);
                        this.addMove(9);
                        this.addMove(7);
                        this.addMove(9);
                        this.addMove(3);
                        this.addMove(10);
                        this.addMove(4);
                    }
                    if(sideValues[32] != 6 && sideValues[39] != 6 && sideValues[32] != 4 && sideValues[39] != 5) {
                        this.addMove(2);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(9);
                        this.addMove(7);
                        this.addMove(10);
                        this.addMove(8);
                    }
                    if(sideValues[41] != 6 && sideValues[12] != 6 && sideValues[41] != 5 && sideValues[12] != 2) {
                        this.addMove(6);
                        this.addMove(9);
                        this.addMove(5);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(10);
                        this.addMove(2);
                    }
                    numTimesWithout = 0;
                }
                // </editor-fold> // Stuck
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[midSticker] != 6 && sideValues[topSticker] != 6) {
                    System.out.println("Running normal");
                    System.out.println("Current Side: " + currentSide);
                    numTimesWithout = 0;
                    while(sideValues[midSticker] != currentSide) {
                        Recognition();
                        System.out.println("Running midSticker while");
                        System.out.println("currentSide before: " + currentSide);
                        System.out.println("midSticker before: " + midSticker);
                        this.addMove(9);
                        currentSide ++;
                        if(currentSide == 6) {
                            currentSide = 2;
                        }
                        midSticker = (currentSide - 1) * 9 + 1;
                        System.out.println("currentSide after: " + currentSide);
                        System.out.println("midSticker after: " + midSticker);
                    }
                    switch(currentSide) {
                        case 2:
                            topSticker = 52;
                            break;
                        case 3:
                            topSticker = 50;
                            break;
                        case 4:
                            topSticker = 46;
                            break;
                        case 5:
                            topSticker = 48;
                            break;
                    }
                    int plusOne = sideValues[topSticker] + 1;
                    if(plusOne == 6) {plusOne = 2;}
                    int minusOne = sideValues[topSticker] - 1;
                    if(minusOne == 1) {minusOne = 5;}
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    if(plusOne == currentSide) {
                        System.out.println("Running plus");
                        this.addMove(9);
                        switch(currentSide) {
                            case 2:
                                System.out.println("Running plus two");
                                this.addMove(1);
                                this.addMove(10);
                                this.addMove(2);
                                this.addMove(10);
                                this.addMove(6);
                                this.addMove(9);
                                this.addMove(5);
                                break;
                            case 3:
                                System.out.println("Running plus three");
                                this.addMove(5);
                                this.addMove(10);
                                this.addMove(6);
                                this.addMove(10);
                                this.addMove(4);
                                this.addMove(9);
                                this.addMove(3);
                                break;
                            case 4:
                                System.out.println("Running plus four");
                                this.addMove(3);
                                this.addMove(10);
                                this.addMove(4);
                                this.addMove(10);
                                this.addMove(8);
                                this.addMove(9);
                                this.addMove(7);
                                break;
                            case 5:
                                System.out.println("Running plus five");
                                this.addMove(7);
                                this.addMove(10);
                                this.addMove(8);
                                this.addMove(10);
                                this.addMove(2);
                                this.addMove(9);
                                this.addMove(1);
                                break;
                        }
                    }
                    // </editor-fold> // Plus One
                    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                    else if(minusOne == currentSide) {
                        System.out.println("Running minus");
                        this.addMove(10);
                        switch(currentSide) {
                            case 2:
                                System.out.println("Running minus two");
                                this.addMove(4);
                                this.addMove(9);
                                this.addMove(3);
                                this.addMove(9);
                                this.addMove(5);
                                this.addMove(10);
                                this.addMove(6);
                                break;
                            case 3:
                                System.out.println("Running minus three");
                                this.addMove(8);
                                this.addMove(9);
                                this.addMove(7);
                                this.addMove(9);
                                this.addMove(3);
                                this.addMove(10);
                                this.addMove(4);
                                break;
                            case 4:
                                System.out.println("Running minus four");
                                this.addMove(2);
                                this.addMove(9);
                                this.addMove(1);
                                this.addMove(9);
                                this.addMove(7);
                                this.addMove(10);
                                this.addMove(8);
                                break;
                            case 5:
                                System.out.println("Running minus five");
                                this.addMove(6);
                                this.addMove(9);
                                this.addMove(5);
                                this.addMove(9);
                                this.addMove(1);
                                this.addMove(10);
                                this.addMove(2);
                                break;
                        }
                    }
                    // </editor-fold> // Minus One
                    else {
                        neitherCount ++;
                        System.out.println("Neither");
                        if(neitherCount == 40) {
                            //this.MakeMove();
                            return;
                        }
                    }
                }
                // </editor-fold> // Normal
                else {
                    numTimesWithout++;
                    System.out.println("Before: " + currentSide);
                    currentSide += 1;
                    if(currentSide == 6) {
                        currentSide = 2;
                    }
                    System.out.println("After: " + currentSide);
                }
                numMidEdges = solver.checkMidEdges(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Middle Edges");
            // </editor-fold> // Middle Edges
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            manipSide = 6;
            currentSide = 6;
            numTimesWithout = 0;
            int secondTimesWithout = 0;
            boolean topSolved = solver.checkTopSide(sideValues);
            while(topSolved == false) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running left ribbon");
                    if(sideValues[9] == 6) {
                        this.addMove(1);
                        this.addMove(9);
                        this.addMove(2);
                        this.addMove(9);
                        this.addMove(1);
                        this.addMove(10);
                        this.addMove(10);
                        this.addMove(2);
                    } else {
                        System.out.println("Left ribbon didn't run");
                        this.addMove(10);
                    }
                }
                // </editor-fold> // Left Ribbon
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] == 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running right ribbon");
                    if(sideValues[11] == 6) {
                        this.addMove(4);
                        this.addMove(10);
                        this.addMove(3);
                        this.addMove(10);
                        this.addMove(4);
                        this.addMove(10);
                        this.addMove(10);
                        this.addMove(3);
                    } else {
                        System.out.println("Right ribbon didn't run");
                        this.addMove(10);
                    }
                }
                // </editor-fold> // Right Ribbon
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running top cross");
                    while(sideValues[9] != 6 && sideValues[11] != 6) {
                        Recognition();
                        this.addMove(10);
                    }
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Top Cross
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] == 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running weird pattern");
                    if(sideValues[38] != 6) {
                        this.addMove(10);
                        this.addMove(10);
                    }
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Weird
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] == 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] == 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running block");
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Normal Block
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] == 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running opposite block");
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Opposite Block
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] == 6 && sideValues[46] == 6 && sideValues[47] == 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] != 6 && sideValues[52] == 6 && sideValues[53] != 6) {
                    System.out.println("Running up block");
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Up Block
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[45] != 6 && sideValues[46] == 6 && sideValues[47] != 6 && sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && sideValues[51] == 6 && sideValues[52] == 6 && sideValues[53] == 6) {
                    System.out.println("Running down block");
                    this.addMove(10);
                    this.addMove(1);
                    this.addMove(9);
                    this.addMove(2);
                    this.addMove(9);
                    this.addMove(1);
                    this.addMove(10);
                    this.addMove(10);
                    this.addMove(2);
                }
                // </editor-fold> // Down Block
                else {
                    System.out.println("Running else top");
                    numTimesWithout++;
                    if(numTimesWithout > 4) {
                        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                        if(sideValues[46] == 6 && sideValues[48] == 6 && sideValues[49] == 6 && solver.checkTopStickers(sideValues) <= 7) {
                            System.out.println("Running elbow");
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(5);
                            this.addMove(10);
                            this.addMove(6);
                            this.addMove(4);
                            numTimesWithout = 0;
                        }
                        // </editor-fold> // Elbow
                        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                        else if(sideValues[48] == 6 && sideValues[49] == 6 && sideValues[50] == 6 && solver.checkTopStickers(sideValues) <= 7) {
                            System.out.println("Running line");
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(5);
                            this.addMove(10);
                            this.addMove(6);
                            this.addMove(4);
                            if(sideValues[49] == 6 && sideValues[50] == 6 && sideValues[52] == 6) {
                                this.addMove(10);
                                this.addMove(10);
                            }
                            numTimesWithout = 0;
                        }
                        // </editor-fold> // Line
                        else {
                            this.addMove(10);
                        }
                        secondTimesWithout++;
                        if(secondTimesWithout > 4) {
                            System.out.println("Running point");
                            this.addMove(3);
                            this.addMove(9);
                            this.addMove(5);
                            this.addMove(10);
                            this.addMove(6);
                            this.addMove(4);
                            secondTimesWithout = 0;
                        }
                    } else {
                        this.addMove(10);
                    }
                }
                topSolved = solver.checkTopSide(sideValues);
                System.out.println("End of loop");
            }
            System.out.println("Finished Top Side");
            // </editor-fold> // Top Side
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            currentSide = 0;
            manipSide = 0;
            numTopEdges = solver.checkTopEdges(sideValues);
            while(numTopEdges < 4) {
                Recognition();
                if((sideValues[9] == 2 || sideValues[9] ==  4) && (sideValues[11] == 2 || sideValues[11] == 4)) {
                    System.out.println("Running twists");
                    this.addMove(3);
                    this.addMove(6);
                    this.addMove(3);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(4);
                    this.addMove(5);
                    this.addMove(3);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(4);
                    this.addMove(4);
                }
                else {
                    System.out.println("Running else edge");
                    this.addMove(10);
                }
                numTopEdges = solver.checkTopEdges(sideValues);
                //System.out.println(numTopEdges);
                System.out.println("End of loop");
            }
            System.out.println("Finished Top Edges");
            // </editor-fold> // Top Edges
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            currentSide = 0;
            manipSide = 0;
            int whichSide = 0;
            while(sideValues[9] != 2) {
                Recognition();
                this.addMove(10);
            }
            int numTopCenters = solver.checkTopCenters(sideValues);
            while(numTopCenters < 4) {
                Recognition();
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                if(numTopCenters == 0) {
                    System.out.println("Running center none");
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(10);
                    this.addMove(4);
                    this.addMove(1);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(3);
                    this.addMove(2);
                    this.addMove(10);
                    this.addMove(8);
                    this.addMove(8);
                }
                // </editor-fold> // Center None
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[10] == 2) {
                    System.out.println("Running center one");
                    if(sideValues[19] == 4) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(whichSide);
                    this.addMove(4);
                    this.addMove(1);
                    this.addMove(8);
                    this.addMove(8);
                    this.addMove(3);
                    this.addMove(2);
                    this.addMove(whichSide);
                    this.addMove(8);
                    this.addMove(8);
                }
                // </editor-fold> // Center One
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[19] == 3) {
                    System.out.println("Running center two");
                    if(sideValues[28] == 5) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(whichSide);
                    this.addMove(8);
                    this.addMove(5);
                    this.addMove(2);
                    this.addMove(2);
                    this.addMove(7);
                    this.addMove(6);
                    this.addMove(whichSide);
                    this.addMove(2);
                    this.addMove(2);
                }
                // </editor-fold> // Center Two
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[28] == 4) {
                    System.out.println("Running center three");
                    if(sideValues[37] == 2) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(whichSide);
                    this.addMove(2);
                    this.addMove(3);
                    this.addMove(6);
                    this.addMove(6);
                    this.addMove(1);
                    this.addMove(4);
                    this.addMove(whichSide);
                    this.addMove(6);
                    this.addMove(6);
                }
                // </editor-fold> // Center Three
                // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
                else if(sideValues[37] == 5) {
                    if(sideValues[10] == 3) {
                        whichSide = 9;
                    } else {
                        whichSide = 10;
                    }
                    System.out.println("Running center four");
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(whichSide);
                    this.addMove(6);
                    this.addMove(7);
                    this.addMove(4);
                    this.addMove(4);
                    this.addMove(5);
                    this.addMove(8);
                    this.addMove(whichSide);
                    this.addMove(4);
                    this.addMove(4);
                }
                // </editor-fold> // Center Four
                else {
                    System.out.println("Running else center");
                }
                numTopCenters = solver.checkTopCenters(sideValues);
                //System.out.println(numTopEdges);
                System.out.println("End of loop");
            }
            System.out.println("Finished Top Centers");
            // </editor-fold> // Top Centers
            
            this.MakeMove(methodValue);
        }
        // </editor-fold> // Roux
    }
    public void MakeMove(int mV)
    {
        this.methodValue = mV;
        for(int i = 0; i < 54; i++) {
            this.sideValues[i] = this.storingValues[i];
        }
        myGUI.refresh(this.draw());
        new Timer(lag, new ActionListener(){
            int counting = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(movesMade[counting])
                {
                    case 1:
                        Lprime();
                        break;
                    case 2:
                        L();
                        break;
                    case 3:
                        Rprime();
                        break;
                    case 4:
                        R();
                        break;
                    case 5:
                        Fprime();
                        break;
                    case 6:
                        F();
                        break;
                    case 7:
                        Bprime();
                        break;
                    case 8:
                        B();
                        break;
                    case 9:
                        Uprime();
                        break;
                    case 10:
                        U();
                        break;
                    case 11:
                        Dprime();
                        break;
                    case 12:
                        D();
                        break;
                }
                myGUI.refresh(draw());
                //counting++;
                if(counting == movesMadeCount) {
                    counting = 0;
                    myGUI.endTime = System.currentTimeMillis();
                    //((Timer) e.getSource()).stop();
                    myGUI.clockTimer.stop();
                    startActivated = false;
                    recognitionCounter = 0;
                    //myGUI.endTime = System.currentTimeMillis();
                    trialCount++;
                    trialTimes[iterationCount] = (myGUI.endTime - myGUI.startTime) / 1000;
                    //System.out.println(trialTimes[trialCount]);
                    if(methodValue == 1) {
                        numTimes1++;
                        if(numTimes1 == 1) {
                            myGUI.m1t1 = (myGUI.endTime - myGUI.startTime) / 1000;
                            myGUI.avg1 = myGUI.m1t1;
                        } else if(numTimes1 == 2) {
                            myGUI.m1t2 = (myGUI.endTime - myGUI.startTime) / 1000;
                            myGUI.avg1 = (myGUI.m1t1 + myGUI.m1t2) / 2;
                        } else if(numTimes1 == 3) {
                            myGUI.m1t3 = (myGUI.endTime - myGUI.startTime) / 1000;
                            myGUI.avg1 = (myGUI.m1t1 + myGUI.m1t2 + myGUI.m1t3) / 3;
                        }
                    } else if(methodValue == 2) {
                        numTimes2++;
                        if(numTimes2 == 1) {
                            myGUI.m2t1 = (myGUI.endTime - myGUI.startTime) / 1000;
                            myGUI.avg2 = myGUI.m2t1;
                        } else if(numTimes2 == 2) {
                            myGUI.m2t2 = (myGUI.endTime - myGUI.startTime) / 1000;
                            myGUI.avg2 = (myGUI.m2t1 + myGUI.m2t2) / 2;
                        } else if(numTimes2 == 3) {
                            myGUI.m2t3 = (myGUI.endTime - myGUI.startTime) / 1000;
                            myGUI.avg2 = (myGUI.m2t1 + myGUI.m2t2 + myGUI.m2t3) / 3;
                        }
                    } else if(methodValue == 3) {
                        numTimes3++;
                        if(numTimes3 == 1) {
                            myGUI.m3t1 = (myGUI.endTime - myGUI.startTime) / 1000;
                            myGUI.avg3 = myGUI.m3t1;
                        } else if(numTimes3 == 2) {
                            myGUI.m3t2 = (myGUI.endTime - myGUI.startTime) / 1000;
                            myGUI.avg3 = (myGUI.m3t1 + myGUI.m3t2) / 2;
                        } else if(numTimes3 == 3) {
                            myGUI.m3t3 = (myGUI.endTime - myGUI.startTime) / 1000;
                            myGUI.avg3 = (myGUI.m3t1 + myGUI.m3t2 + myGUI.m3t3) / 3;
                        }
                    }
                    myGUI.results.repaint();
                    ((Timer) e.getSource()).stop();
                }
                counting++;
            }
        }).start();
        System.out.println(this.movesMadeCount);
    }
    public void addMove(int t)
    {
        switch(t) {
            case 1:
                this.Lprime();
                break;
            case 2:
                this.L();
                break;
            case 3:
                this.Rprime();
                break;
            case 4:
                this.R();
                break;
            case 5:
                this.Fprime();
                break;
            case 6:
                this.F();
                break;
            case 7:
                this.Bprime();
                break;
            case 8:
                this.B();
                break;
            case 9:
                this.Uprime();
                break;
            case 10:
                this.U();
                break;
            case 11:
                this.Dprime();
                break;
            case 12:
                this.D();
                break;                     
        }
        this.movesMade[this.movesMadeCount] = t;
        this.movesMadeCount++;
    }
    public void Recognition()
    {
        recognitionCounter++;
        if(recognitionCounter > 500) {
            recognitionCounter = 0;
            System.out.println("Running Recognition");
            //myGUI.clockTimer.stop();
            // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
            myGUI.startTime = 0;
            myGUI.endTime = 0;
            myGUI.elapsedTime = 0;
            myGUI.startTimer = 0;
            myGUI.endTimer = 0;
            myGUI.elapsedTimer = 0;
            myGUI.ltfil1.removeAll();
            myGUI.ltfil1.repaint();
            myGUI.ltfil2.removeAll();
            myGUI.ltfil2.repaint();
            myGUI.rtfil1.removeAll();
            myGUI.rtfil1.repaint();
            myGUI.rtfil2.removeAll();
            myGUI.rtfil2.repaint();
            myGUI.method1.setForeground(Color.black);
            myGUI.method2.setForeground(Color.black);
            myGUI.method3.setForeground(Color.black);
            myGUI.doItYourself.setForeground(Color.black);
            myGUI.time.setText("00:00:000");
            firstRun = true;
            Reset();
            for(int i = 0; i < 1000; i++) {
                movesMade[i] = 0;
            }
            movesMadeCount = 0;
            upSide = 2;
            downSide = 2;
            origSide = 2;
            manipSide = 2;
            otherSide = 2;
            turnside = 0;
            side = 2;
            count = 0;
            lastMove = 0;
            redoMove = 0;
            makeThisMove = 0;
            neitherCount = 0;
            woyrAxis = 0;
            bogrAxis = 1;
            wbygAxis = 2;
            lastAxis = 1;
            firstRun = true;
            startActivated = false;
            //myGUI.drawAndButtons.add(cube.draw());
            myGUI.refresh(draw());
            myGUI.cubeSpace.repaint();
            myGUI.arrangement.validate();
            // </editor-fold> // Reset
            //this.Scramble(0);
            //this.Solve(methodValue);
            //return;
        }
    }
    public void determineSide()
    {
        if(wbygAxis == 3)
            this.side = 1;
        else if(wbygAxis == 2)
            this.side = 2;
        else if(wbygAxis == 0 && woyrAxis == 2)
            this.side = 3;
        else if(wbygAxis == 4)
            this.side = 4;
        else if(wbygAxis == 0 && woyrAxis == 4)
            this.side = 5;
        else if(wbygAxis == 1)
            this.side = 6;
    }
    public void setAxes()
    {
        if(this.side == 1)
        {
            wbygAxis = 3;
            woyrAxis = 3;
            bogrAxis = 0;
        }
        else if(this.side == 2)
        {
            wbygAxis = 2;
            woyrAxis = 0;
            bogrAxis = 1;
        }
        else if(this.side == 3)
        {
            wbygAxis = 0;
            woyrAxis = 2;
            bogrAxis = 2;
        }
        else if(this.side == 4)
        {
            wbygAxis = 4;
            woyrAxis = 0;
            bogrAxis = 3;
        }
        else if(this.side == 5)
        {
            wbygAxis = 0;
            woyrAxis = 4;
            bogrAxis = 4;
        }
        else if(this.side == 6)
        {
            wbygAxis = 1;
            woyrAxis = 1;
            bogrAxis = 0;
        }
    }
    public drawingTool draw()
    {
        drawingTool painter = new drawingTool(count, side, sideValues, firstRun, upSide, downSide);
        this.firstRun = false;
        return painter;
    }
}
