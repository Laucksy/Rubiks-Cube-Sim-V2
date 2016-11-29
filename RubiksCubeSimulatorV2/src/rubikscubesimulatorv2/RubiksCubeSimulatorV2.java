package rubikscubesimulatorv2;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.*;

public class RubiksCubeSimulatorV2 implements ActionListener {
    
    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
    GUI myGUI;
    Cube cube;
    int turns = 0;
    int seconds = 0;
    int minutes = 0;
    int numm1 = 1;
    int numm2 = 1;
    int numm3 = 1;
    int transfer;
    int scramble;
    int amount = 0;
    int numMod = 0;
    int numTimes = 0;
    int moveAid = 0;
    int methodValue;
    Thread stopwatch;
    ActionListener taskPerformer;
    DecimalFormat dcf = new DecimalFormat("00.000");
    JTextField delayTF;
    JTextField massTF;
    JLabel massLabel;
    boolean firstTurn = true;
    boolean isTiming = false;
    boolean firstSwitch = true;
    boolean firstHelp = true;
    boolean rightAmount = true;
    boolean m1going = false;
    boolean doYourself = false;
    // </editor-fold> // Variables
    
    @SuppressWarnings("serial")
    public class Timing implements Runnable
    {
        public void Timer()
        {
            stopwatch = new Thread(this, "Stopwatch");
            stopwatch.start();
        }
        @Override
        public void run()
        {
            //seconds = 0;
            ActionListener taskPerformer = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    myGUI.endTimer = System.currentTimeMillis();
                    myGUI.elapsedTimer = (myGUI.endTimer - myGUI.startTimer);
                    if(myGUI.elapsedTimer > 999)
                    {
                        seconds++;
                        myGUI.startTimer = System.currentTimeMillis();
                        myGUI.elapsedTimer = 0;
                    }
                    if(seconds > 59)
                    {
                        minutes++;
                        seconds = 0;
                    }
                    if(seconds < 10)
                    {
                        if(myGUI.elapsedTimer < 10)
                                myGUI.time.setText("0" + minutes + ":0" + seconds + ":00" + myGUI.elapsedTimer);
                        else if(myGUI.elapsedTimer < 100 && myGUI.elapsedTimer >= 10)
                                myGUI.time.setText("0" + minutes + ":0" + seconds + ":0" + myGUI.elapsedTimer);
                        else
                                myGUI.time.setText("0" + minutes + ":0" + seconds + ":" + myGUI.elapsedTimer);
                    }
                    else
                    {
                        if(myGUI.elapsedTimer < 10)
                                myGUI.time.setText("0" + minutes + ":" + seconds + ":00" + myGUI.elapsedTimer);
                        else if(myGUI.elapsedTimer < 100 && myGUI.elapsedTimer >= 10)
                                myGUI.time.setText("0" + minutes + ":" + seconds + ":0" + myGUI.elapsedTimer);
                        else
                                myGUI.time.setText("0" + minutes + ":" + seconds + ":" + myGUI.elapsedTimer);
                    }
                }
            };
            myGUI.clockTimer = new Timer(0 , taskPerformer);
            myGUI.clockTimer.setRepeats(true);
            myGUI.clockTimer.start();
            myGUI.arrangement.validate();
        }
    }
    
    public void Reset()
    {
        if(cube.startActivated == false)
        {
            myGUI.startTime = 0;
            myGUI.endTime = 0;
            myGUI.elapsedTime = 0;
            myGUI.startTimer = 0;
            myGUI.endTimer = 0;
            myGUI.elapsedTimer = 0;
            minutes = 0;
            seconds = 0;
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
            cube.firstRun = true;
            cube.Reset();
            for(int i = 0; i < 1000; i++) {
                cube.movesMade[i] = 0;
            }
            cube.movesMadeCount = 0;
            cube.upSide = 2;
            cube.downSide = 2;
            cube.origSide = 2;
            cube.manipSide = 2;
            cube.otherSide = 2;
            cube.turnside = 0;
            cube.side = 2;
            cube.count = 0;
            cube.lastMove = 0;
            cube.redoMove = 0;
            cube.makeThisMove = 0;
            cube.neitherCount = 0;
            cube.woyrAxis = 0;
            cube.bogrAxis = 1;
            cube.wbygAxis = 2;
            cube.lastAxis = 1;
            cube.firstRun = true;
            cube.startActivated = false;
            //myGUI.drawAndButtons.add(cube.draw());
            myGUI.refresh(cube.draw());
            myGUI.cubeSpace.repaint();
            myGUI.arrangement.validate();
        }
    }
    
    public RubiksCubeSimulatorV2()
    {
        myGUI = new GUI();
        cube = new Cube(myGUI);
        myGUI.question.addActionListener(this);
        myGUI.options.addActionListener(this);
        myGUI.resultsButton.addActionListener(this);
        myGUI.method1.addActionListener(this);
        myGUI.method2.addActionListener(this);
        myGUI.method3.addActionListener(this);
        myGUI.doItYourself.addActionListener(this);
        myGUI.mL.addActionListener(this);
        myGUI.mLp.addActionListener(this);
        myGUI.mR.addActionListener(this);
        myGUI.mRp.addActionListener(this);
        myGUI.mF.addActionListener(this);
        myGUI.mFp.addActionListener(this);
        myGUI.mB.addActionListener(this);
        myGUI.mBp.addActionListener(this);
        myGUI.mU.addActionListener(this);
        myGUI.mUp.addActionListener(this);
        myGUI.mD.addActionListener(this);
        myGUI.mDp.addActionListener(this);
        myGUI.lview.addActionListener(this);
        myGUI.rview.addActionListener(this);
        myGUI.tview.addActionListener(this);
        myGUI.Start.addActionListener(this);
        myGUI.Stop.addActionListener(this);
        myGUI.Reset.addActionListener(this);
        myGUI.bview.addActionListener(this);
        myGUI.refresh(cube.draw());
    }
    
    public static void main(String [] args) throws IOException
    {
        //This gets called because of ststic reference problems
    	new RubiksCubeSimulatorV2();
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        if(((JButton) arg0.getSource()).getText().equals("^"))
        {
            if(cube.side == 2 || cube.side == 3 || cube.side == 4 || cube.side == 5)
            {
                cube.downSide = cube.side;
                cube.side = 6;
                cube.setAxes();
            }
            else if(cube.side == 1)
            {
                cube.side = cube.upSide;
                //new cube.sideDet().determineSide();
                cube.setAxes();
            }
            else if(cube.side == 6){}
            myGUI.refresh(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        //</editor-fold> // ^
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("\\/"))
        {
            if(cube.side == 2 || cube.side == 3 || cube.side == 4 || cube.side == 5)
            {
                cube.upSide = cube.side;
                cube.side = 1;
                cube.setAxes();
            }
            else if(cube.side == 6)
            {
                cube.side = cube.downSide;
                //new cube.sideDet().determineSide();
                cube.setAxes();
            }
            myGUI.refresh(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // \/
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("<"))
        {
            if(cube.side == 2 || cube.side == 3 || cube.side == 4 || cube.side == 5)
            {
                cube.side -= 1;
                if(cube.side == 1)
                    cube.side = 5;
                if(cube.side == 2 || cube.side == 4)
                    cube.lastAxis = 1;
                else if(cube.side == 3 || cube.side == 5)
                    cube.lastAxis = 2;
            }
            else if(cube.side == 1)
            {
                cube.upSide -= 1;
                if(cube.upSide == 1)
                    cube.upSide = 5;
                if(cube.upSide == 2 || cube.upSide == 4)
                    cube.lastAxis = 1;
                else
                    cube.lastAxis = 2;
            }
            else if(cube.side == 6)
            {
                cube.downSide -= 1;
                if(cube.downSide == 1)
                    cube.downSide = 5;
                if(cube.downSide == 2 || cube.downSide == 4)
                    cube.lastAxis = 1;
                else
                    cube.lastAxis = 2;
            }
            cube.setAxes();
            myGUI.refresh(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // <
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals(">"))
        {
            if(cube.side == 2 || cube.side == 3 || cube.side == 4 || cube.side == 5)
            {
                cube.side += 1;
                if(cube.side == 6)
                    cube.side = 2;
                if(cube.side == 2 || cube.side == 4)
                    cube.lastAxis = 1;
                else if(cube.side == 3 || cube.side == 5)
                    cube.lastAxis = 2;
            }
            else if(cube.side == 1)
            {
                cube.upSide += 1;
                if(cube.upSide == 6)
                    cube.upSide = 2;
            }
            else if(cube.side == 6)
            {
                cube.downSide += 1;
                if(cube.downSide == 6)
                    cube.downSide = 2;
            }
            cube.setAxes();
            myGUI.refresh(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // >
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Layer Method"))
        {
            if(cube.startActivated == false)
            {
                methodValue = 1;
                Cube.iterationCount++;
                myGUI.method1.setForeground(Color.red);
                myGUI.method2.setForeground(Color.black);
                myGUI.method3.setForeground(Color.black);
                myGUI.doItYourself.setForeground(Color.black);
                if(doYourself == true)
                {
                    myGUI.window.setVisible(false);
                    myGUI.ltfil1.remove(myGUI.mL);
                    myGUI.ltfil1.remove(myGUI.mLp);
                    myGUI.ltfil1.remove(myGUI.mR);
                    myGUI.ltfil2.remove(myGUI.mRp);
                    myGUI.ltfil2.remove(myGUI.mF);
                    myGUI.ltfil2.remove(myGUI.mFp);
                    myGUI.rtfil1.remove(myGUI.mB);
                    myGUI.rtfil1.remove(myGUI.mBp);
                    myGUI.rtfil1.remove(myGUI.mU);
                    myGUI.rtfil2.remove(myGUI.mUp);
                    myGUI.rtfil2.remove(myGUI.mD);
                    myGUI.rtfil2.remove(myGUI.mDp);
                    myGUI.rightButton.remove(myGUI.rtfil1);
                    myGUI.rightButton.remove(myGUI.rtfil2);
                    myGUI.leftButton.remove(myGUI.ltfil1);
                    myGUI.leftButton.remove(myGUI.ltfil2);
                    myGUI.arrangement.validate();
                    myGUI.window.setVisible(true);
                }
                /*new Timer(5, new ActionListener(){
                    int thisCounter = 0;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        thisCounter++;
                        cube.Scramble();
                        refresh();
                        if(thisCounter == 500)
                            ((Timer) e.getSource()).stop();
                    }
                }).start();*/
                cube.Scramble(0);
            }
        }
        // </editor-fold> // Tri-Layer
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Petrus Method"))
        {
            if(cube.startActivated == false)
            {
                methodValue = 2;
                Cube.iterationCount++;
                myGUI.method1.setForeground(Color.black);
                myGUI.method2.setForeground(Color.red);
                myGUI.method3.setForeground(Color.black);
                myGUI.doItYourself.setForeground(Color.black);
                if(doYourself == true)
                {
                    myGUI.window.setVisible(false);
                    myGUI.ltfil1.remove(myGUI.mL);
                    myGUI.ltfil1.remove(myGUI.mLp);
                    myGUI.ltfil1.remove(myGUI.mR);
                    myGUI.ltfil2.remove(myGUI.mRp);
                    myGUI.ltfil2.remove(myGUI.mF);
                    myGUI.ltfil2.remove(myGUI.mFp);
                    myGUI.rtfil1.remove(myGUI.mB);
                    myGUI.rtfil1.remove(myGUI.mBp);
                    myGUI.rtfil1.remove(myGUI.mU);
                    myGUI.rtfil2.remove(myGUI.mUp);
                    myGUI.rtfil2.remove(myGUI.mD);
                    myGUI.rtfil2.remove(myGUI.mDp);
                    myGUI.rightButton.remove(myGUI.rtfil1);
                    myGUI.rightButton.remove(myGUI.rtfil2);
                    myGUI.leftButton.remove(myGUI.ltfil1);
                    myGUI.leftButton.remove(myGUI.ltfil2);
                    myGUI.arrangement.validate();
                    myGUI.window.setVisible(true);
                }
                /*new Timer(5, new ActionListener(){
                    int thisCounter = 0;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        thisCounter++;
                        cube.Scramble();
                        refresh();
                        if(thisCounter == 500)
                            ((Timer) e.getSource()).stop();
                    }
                }).start();*/
                cube.Scramble(0);
            }
        }
        // </editor-fold> // Petrus
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Roux Method"))
        {
            if(cube.startActivated == false)
            {
                methodValue = 3;
                Cube.iterationCount++;
                myGUI.method1.setForeground(Color.black);
                myGUI.method2.setForeground(Color.black);
                myGUI.method3.setForeground(Color.red);
                myGUI.doItYourself.setForeground(Color.black);
                if(doYourself == true)
                {
                    myGUI.window.setVisible(false);
                    myGUI.ltfil1.remove(myGUI.mL);
                    myGUI.ltfil1.remove(myGUI.mLp);
                    myGUI.ltfil1.remove(myGUI.mR);
                    myGUI.ltfil2.remove(myGUI.mRp);
                    myGUI.ltfil2.remove(myGUI.mF);
                    myGUI.ltfil2.remove(myGUI.mFp);
                    myGUI.rtfil1.remove(myGUI.mB);
                    myGUI.rtfil1.remove(myGUI.mBp);
                    myGUI.rtfil1.remove(myGUI.mU);
                    myGUI.rtfil2.remove(myGUI.mUp);
                    myGUI.rtfil2.remove(myGUI.mD);
                    myGUI.rtfil2.remove(myGUI.mDp);
                    myGUI.rightButton.remove(myGUI.rtfil1);
                    myGUI.rightButton.remove(myGUI.rtfil2);
                    myGUI.leftButton.remove(myGUI.ltfil1);
                    myGUI.leftButton.remove(myGUI.ltfil2);
                    myGUI.arrangement.validate();
                    myGUI.window.setVisible(true);
                }
                /*new Timer(5, new ActionListener(){
                    int thisCounter = 0;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        thisCounter++;
                        cube.Scramble();
                        refresh();
                        if(thisCounter == 500)
                            ((Timer) e.getSource()).stop();
                    }
                }).start();*/
                cube.Scramble(0);
            }
        }
        // </editor-fold> // Roux
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Start"))
        {
            cube.startActivated = true;
            myGUI.startTime = System.currentTimeMillis();
            myGUI.startTimer = myGUI.startTime;
            //try {
               cube.Solve(methodValue);
            //} catch(IOException e) {
            //}
            new Timing().Timer();
        }
        // </editor-fold> // Start
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Stop"))
        {
            if(cube.startActivated == true)
            {
                myGUI.endTime = System.currentTimeMillis();
                //myGUI.timer.stop();
                myGUI.clockTimer.stop();
            }
            cube.startActivated = false;
            //System.out.println(m1list);
        }
        // </editor-fold> // Stop
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Reset"))
        {
            this.Reset();
        }
        // </editor-fold> // Reset
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Results"))
        {
            if(cube.startActivated == false)
            {
                if(firstSwitch == true)
                {
                    myGUI.results.setLayout(new GridLayout(3,2));
                    JPanel rfil1 = new JPanel();
                    JPanel rfil2 = new JPanel();
                    JPanel rfil3 = new JPanel();
                    JPanel rfil4 = new JPanel();
                    JPanel table1 = new JPanel();
                    rfil3.setLayout(new GridLayout(3,3));
                    JPanel filfil1 = new JPanel();
                    JPanel filfil2 = new JPanel();
                    JPanel filfil3 = new JPanel();
                    JPanel filfil4 = new JPanel();
                    JPanel filfil5 = new JPanel();
                    JPanel filfil6 = new JPanel();
                    JPanel filfil7 = new JPanel();
                    JPanel filfil8 = new JPanel();
                    myGUI.goBack.addActionListener(this);
                    rfil3.add(filfil1);
                    rfil3.add(filfil2);
                    rfil3.add(filfil3);
                    rfil3.add(filfil4);
                    rfil3.add(filfil5);
                    rfil3.add(filfil6);
                    rfil3.add(myGUI.goBack);
                    rfil3.add(filfil7);
                    rfil3.add(filfil8);
                    filfil1.setBackground(Color.black);
                    filfil2.setBackground(Color.black);
                    filfil3.setBackground(Color.black);
                    filfil4.setBackground(Color.black);
                    filfil5.setBackground(Color.black);
                    filfil6.setBackground(Color.black);
                    filfil7.setBackground(Color.black);
                    filfil8.setBackground(Color.black);
                    table1.setLayout(new GridLayout(5,2));
                    JPanel table2 = new JPanel();
                    table2.setLayout(new GridLayout(5,2));
                    DecimalFormat dcf = new DecimalFormat("0.000");
                    myGUI.cell1.setText("");
                    myGUI.cell2.setText("Tri-Layer");
                    myGUI.cell3.setText("   Trial 1");
                    myGUI.cell4.setText("" + myGUI.m1t1 + "seconds");
                    myGUI.cell5.setText("   Trial 2");
                    myGUI.cell6.setText("" + myGUI.m1t2 + "seconds");
                    myGUI.cell7.setText("   Trial 3");
                    myGUI.cell8.setText("" + myGUI.m1t3 + "seconds");
                    myGUI.cell9.setText("   Average");
                    myGUI.cell10.setText("" + dcf.format(myGUI.avg1) + "seconds");
                    myGUI.cell11.setText("Petrus");
                    myGUI.cell12.setText("Roux");
                    myGUI.cell13.setText("" + myGUI.m2t1 + "seconds");
                    myGUI.cell14.setText("" + myGUI.m3t1 + "seconds");
                    myGUI.cell15.setText("" + myGUI.m2t2 + "seconds");
                    myGUI.cell16.setText("" + myGUI.m3t2 + "seconds");
                    myGUI.cell17.setText("" + myGUI.m2t3 + "seconds");
                    myGUI.cell18.setText("" + myGUI.m3t3 + "seconds");
                    myGUI.cell19.setText("" + dcf.format(myGUI.avg2) + "seconds");
                    myGUI.cell20.setText("" + dcf.format(myGUI.avg3) + "seconds");
                    myGUI.cell1.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell2.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell3.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell4.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell5.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell6.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell7.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell8.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell9.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell10.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell11.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell12.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell13.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell14.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell15.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell16.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell17.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell18.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell19.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell20.setFont(new Font("Times New Roman", Font.PLAIN, 28));
                    myGUI.cell1.setForeground(Color.white);
                    myGUI.cell2.setForeground(Color.white);
                    myGUI.cell3.setForeground(Color.white);
                    myGUI.cell4.setForeground(Color.white);
                    myGUI.cell5.setForeground(Color.white);
                    myGUI.cell6.setForeground(Color.white);
                    myGUI.cell7.setForeground(Color.white);
                    myGUI.cell8.setForeground(Color.white);
                    myGUI.cell9.setForeground(Color.white);
                    myGUI.cell10.setForeground(Color.white);
                    myGUI.cell11.setForeground(Color.white);
                    myGUI.cell12.setForeground(Color.white);
                    myGUI.cell13.setForeground(Color.white);
                    myGUI.cell14.setForeground(Color.white);
                    myGUI.cell15.setForeground(Color.white);
                    myGUI.cell16.setForeground(Color.white);
                    myGUI.cell17.setForeground(Color.white);
                    myGUI.cell18.setForeground(Color.white);
                    myGUI.cell19.setForeground(Color.white);
                    myGUI.cell20.setForeground(Color.white);
                    table1.setBackground(Color.black);
                    table2.setBackground(Color.black);
                    rfil1.setBackground(Color.black);
                    rfil2.setBackground(Color.black);
                    rfil3.setBackground(Color.black);
                    rfil4.setBackground(Color.black);
                    table1.add(myGUI.cell1);
                    table1.add(myGUI.cell2);
                    table1.add(myGUI.cell3);
                    table1.add(myGUI.cell4);
                    table1.add(myGUI.cell5);
                    table1.add(myGUI.cell6);
                    table1.add(myGUI.cell7);
                    table1.add(myGUI.cell8);
                    table1.add(myGUI.cell9);
                    table1.add(myGUI.cell10);
                    table2.add(myGUI.cell11);
                    table2.add(myGUI.cell12);
                    table2.add(myGUI.cell13);
                    table2.add(myGUI.cell14);
                    table2.add(myGUI.cell15);
                    table2.add(myGUI.cell16);
                    table2.add(myGUI.cell17);
                    table2.add(myGUI.cell18);
                    table2.add(myGUI.cell19);
                    table2.add(myGUI.cell20);
                    myGUI.results.add(rfil1);
                    myGUI.results.add(rfil2);
                    myGUI.results.add(table1);
                    myGUI.results.add(table2);
                    myGUI.results.add(rfil3);
                    myGUI.results.add(rfil4);
                    firstSwitch = false;
                } else {
                    DecimalFormat dcf = new DecimalFormat("0.000");
                    myGUI.cell1.setText("");
                    myGUI.cell2.setText("Tri-Layer");
                    myGUI.cell3.setText("   Trial 1");
                    myGUI.cell4.setText("" + myGUI.m1t1 + "seconds");
                    myGUI.cell5.setText("   Trial 2");
                    myGUI.cell6.setText("" + myGUI.m1t2 + "seconds");
                    myGUI.cell7.setText("   Trial 3");
                    myGUI.cell8.setText("" + myGUI.m1t3 + "seconds");
                    myGUI.cell9.setText("   Average");
                    myGUI.cell10.setText("" + dcf.format(myGUI.avg1) + "seconds");
                    myGUI.cell11.setText("Corner-Edge");
                    myGUI.cell12.setText("Modified Roux");
                    myGUI.cell13.setText("" + myGUI.m2t1 + "seconds");
                    myGUI.cell14.setText("" + myGUI.m3t1 + "seconds");
                    myGUI.cell15.setText("" + myGUI.m2t2 + "seconds");
                    myGUI.cell16.setText("" + myGUI.m3t2 + "seconds");
                    myGUI.cell17.setText("" + myGUI.m2t3 + "seconds");
                    myGUI.cell18.setText("" + myGUI.m3t3 + "seconds");
                    myGUI.cell19.setText("" + dcf.format(myGUI.avg2) + "seconds");
                    myGUI.cell20.setText("" + dcf.format(myGUI.avg3) + "seconds");
                    myGUI.cell1.repaint();
                    myGUI.cell2.repaint();
                    myGUI.cell3.repaint();
                    myGUI.cell4.repaint();
                    myGUI.cell5.repaint();
                    myGUI.cell6.repaint();
                    myGUI.cell7.repaint();
                    myGUI.cell8.repaint();
                    myGUI.cell9.repaint();
                    myGUI.cell10.repaint();
                    myGUI.cell11.repaint();
                    myGUI.cell12.repaint();
                    myGUI.cell13.repaint();
                    myGUI.cell14.repaint();
                    myGUI.cell15.repaint();
                    myGUI.cell16.repaint();
                    myGUI.cell17.repaint();
                    myGUI.cell18.repaint();
                    myGUI.cell19.repaint();
                    myGUI.cell20.repaint();
                }	
                myGUI.window.setVisible(false);
                myGUI.arrangement.removeAll();
                myGUI.arrangement.validate();
                myGUI.arrangement.add(myGUI.results, BorderLayout.CENTER);
                myGUI.arrangement.validate();
                myGUI.window.setVisible(true);
            }
        }
        // </editor-fold> // Results
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Go Back"))
        {
            myGUI.window.setVisible(false);
            myGUI.arrangement.remove(myGUI.results);
            myGUI.arrangement.validate();
            myGUI.arrangement.add(myGUI.title, BorderLayout.NORTH);
            myGUI.arrangement.add(myGUI.middleArea, BorderLayout.CENTER);
            myGUI.arrangement.add(myGUI.info, BorderLayout.SOUTH);
            myGUI.arrangement.validate();
            myGUI.window.setVisible(true);
        }
        // </editor-fold> // Go Back
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("?"))
        {
            if(firstHelp == true)
            {
                myGUI.helpView.setSize(600,500);
                myGUI.helpView.setTitle("Rubik's Cube Simulator Help");
                myGUI.helpArrangement = myGUI.helpView.getContentPane();
                myGUI.helpArrangement.setLayout(new BorderLayout());
                JPanel text = new JPanel();
                text.setBackground(Color.black);
                JLabel triex1 = new JLabel("Tri-Layer: This method involves solving");
                JLabel triex2 = new JLabel("the bottom side and edge, then the middle edges,");
                JLabel triex3 = new JLabel("and finally the top side and edge.");
                triex1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                triex2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                triex3.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                text.add(triex1);
                text.add(triex2);
                text.add(triex3);
                triex1.setForeground(Color.white);
                triex2.setForeground(Color.white);
                triex3.setForeground(Color.white);
                JLabel filler1 = new JLabel("I am currently 18 hours into this project");
                filler1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                filler1.setForeground(Color.black);
                text.add(filler1);
                JLabel corex1 = new JLabel("Modified Petrus: This method involves creating a");
                JLabel corex2 = new JLabel("2x2x3 block, then twisting the edges and");
                JLabel corex3 = new JLabel("finishing with an algorithm made by me.");
                corex1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                corex2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                corex3.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                text.add(corex1);
                text.add(corex2);
                text.add(corex3);
                corex1.setForeground(Color.white);
                corex2.setForeground(Color.white);
                corex3.setForeground(Color.white);
                JLabel filler2 = new JLabel("Obama is not, in fact, a Kenyan Muslim Socialist.");
                filler2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                filler2.setForeground(Color.black);
                text.add(filler2);
                JLabel minex1 = new JLabel("Modified Roux: This method involves solving two");
                JLabel minex2 = new JLabel("1x2x3 blocks, followed by a modified algorithm");
                JLabel minex3 = new JLabel("(developed by me) that solves the top side and edges.");
                minex1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                minex2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                minex3.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                text.add(minex1);
                text.add(minex2);
                text.add(minex3);
                minex1.setForeground(Color.white);
                minex2.setForeground(Color.white);
                minex3.setForeground(Color.white);
                JLabel filler3 = new JLabel("When the power of love overcomes the love of power, the world will know peace.");
                filler3.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                filler3.setForeground(Color.black);
                text.add(filler3);
                JLabel notex1 = new JLabel("Note: No matter what view the cube is at,");
                JLabel notex2 = new JLabel("the turns will always start from the blue");
                JLabel notex3 = new JLabel("cube.side to avoid disorientation.");
                notex1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                notex2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                notex3.setFont(new Font("Times New Roman", Font.PLAIN, 24));
                text.add(notex1);
                text.add(notex2);
                text.add(notex3);
                notex1.setForeground(Color.white);
                notex2.setForeground(Color.white);
                notex3.setForeground(Color.white);
                myGUI.helpArrangement.add(text, BorderLayout.CENTER);
                firstHelp = false;
            }
            myGUI.helpView.setVisible(true);
        }
        // </editor-fold> // ?
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Options"))
        {
            JFrame optionsWindow = new JFrame();
            optionsWindow.setSize(350,400);
            optionsWindow.setTitle("Rubik's Cube Simulator Options");
            Container optionsArrangement = optionsWindow.getContentPane();
            optionsArrangement.setLayout(new GridLayout(6,1));
            JLabel delayLabel = new JLabel("  Set the delay between turns(ms)");
            delayLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
            delayLabel.setForeground(Color.white);
            optionsArrangement.add(delayLabel);
            delayTF = new JTextField();
            delayTF.setFont(new Font("Times New Roman", Font.PLAIN, 24));
            optionsArrangement.add(delayTF);
            JButton delayButton = new JButton("Set Delay");
            delayButton.setFont(new Font("Times New Roman", Font.PLAIN, 24));
            delayButton.addActionListener(this);
            optionsArrangement.add(delayButton);
            
            massLabel = new JLabel("        Set the number of trials");
            massLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
            massLabel.setForeground(Color.white);
            optionsArrangement.add(massLabel);
            massTF = new JTextField();
            massTF.setFont(new Font("Times New Roman", Font.PLAIN, 24));
            optionsArrangement.add(massTF);
            JButton massButton = new JButton("Set Trials");
            massButton.setFont(new Font("Times New Roman", Font.PLAIN, 24));
            massButton.addActionListener(this);
            optionsArrangement.add(massButton);
            optionsArrangement.setBackground(Color.black);
            optionsWindow.setVisible(true);
        }
        // </editor-fold> // Options
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Set Delay"))
        {
            String delayString = delayTF.getText();
            int delayChange = Integer.parseInt(delayString);
            cube.lag = delayChange;
        }
        // </editor-fold> // Delay
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Set Trials"))
        {
            String massString = massTF.getText();
            final int massNumber = Integer.parseInt(massString);
            System.out.println(massNumber);
            new Timer(0, new ActionListener(){
                int thisCounter = -1;
                Cube massCube = new Cube(myGUI);
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!massCube.currentlyRunning) {
                        massCube = new Cube(myGUI);
                        thisCounter++;
                        System.out.println("Counter number " + thisCounter);
                        Cube.iterationCount++;
                        System.out.println("Iteration numer " + Cube.iterationCount);
                        if(Cube.iterationCount < massNumber) {
                            massCube.newMV = 1;
                            massCube.allowedToRun = true;
                        } else if(Cube.iterationCount < massNumber * 2) {
                            massCube.newMV = 2;
                            massCube.allowedToRun = true;
                        } else if(Cube.iterationCount < massNumber * 3) {
                            massCube.newMV = 3;
                            massCube.allowedToRun = true;
                        } else {
                            massCube.allowedToRun = false;
                        }
                        if(massCube.allowedToRun) {
                            System.out.println("Method value: " + massCube.newMV);
                            System.out.println("Scramble Cube");
                            massCube.Scramble(0);
                            massCube.currentlyRunning = true;
                            Timer waitTimer = new Timer(100, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent event) {
                                    if(massCube.scrambleFinished) {
                                        massCube.startActivated = true;
                                        myGUI.startTime = System.currentTimeMillis();
                                        myGUI.startTimer = myGUI.startTime;
                                        massCube.Solve(massCube.newMV);
                                        new Timing().Timer();
                                        System.out.println("Done With Iteration");
                                        //System.out.println(cube.trialTimes[cube.iterationCount]);
                                        //Reset();
                                        massCube.currentlyRunning = false;
                                        massCube.scrambleFinished = false;
                                        ((Timer)event.getSource()).stop();
                                    }
                                }
                            });
                            waitTimer.start();
                        }
                        
                        massLabel.setText("Completed " + Cube.iterationCount + " trials.");
                    }
                    if(thisCounter >= massNumber * 3 && !massCube.currentlyRunning /*&& !waitTimer.isRunning()*/) {
                        //((Timer) e.getSource()).stop();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nTIMES:\n\n\n");
                        for(int i = 0; i < massNumber * 3; i++) {
                            System.out.println(Cube.trialTimes[i]);
                            if(i == massNumber-1 || i == (massNumber * 2)-1) {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                            }
                        }
                        ((Timer) e.getSource()).stop();
                        return;
                    }
                }
            }).start();
        }
        // </editor-fold> // Trials
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("L"))
        {
            moveAid++;
            //m1values[moveAid] = 1;
            //m1list = m1list + "," + m1values[moveAid];
            cube.L();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // L
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("L prime"))
        {
            moveAid++;
            //m1values[moveAid] = 2;
            //m1list = m1list + "," + m1values[moveAid];
            cube.Lprime();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // L prime
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("R"))
        {
            moveAid++;
            //m1values[moveAid] = 3;
            //m1list = m1list + "," + m1values[moveAid];
            cube.R();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // R
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("R prime"))
        {
            moveAid++;
            //m1values[moveAid] = 4;
            //m1list = m1list + "," + m1values[moveAid];
            cube.Rprime();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // R prime
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("F"))
        {
            moveAid++;
            //m1values[moveAid] = 5;
            //m1list = m1list + "," + m1values[moveAid];
            cube.F();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // F
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("F prime"))
        {
            moveAid++;
            //m1values[moveAid] = 6;
            //m1list = m1list + "," + m1values[moveAid];
            cube.Fprime();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // F prime
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("B"))
        {
            moveAid++;
            //m1values[moveAid] = 7;
            //m1list = m1list + "," + m1values[moveAid];
            cube.B();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // B
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("B prime"))
        {
            moveAid++;
            //m1values[moveAid] = 8;
            //m1list = m1list + "," + m1values[moveAid];
            cube.Bprime();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // B prime
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("U"))
        {
            moveAid++;
            //m1values[moveAid] = 9;
            //m1list = m1list + "," + m1values[moveAid];
            cube.U();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // U
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("U prime"))
        {
            moveAid++;
            //m1values[moveAid] = 10;
            //m1list = m1list + "," + m1values[moveAid];
            cube.Uprime();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // U prime
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("D"))
        {
            moveAid++;
            //m1values[moveAid] = 11;
            //m1list = m1list + "," + m1values[moveAid];
            cube.D();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // D
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("D prime"))
        {
            moveAid++;
            //m1values[moveAid] = 12;
            //m1list = m1list + "," + m1values[moveAid];
            cube.Dprime();
            myGUI.drawAndButtons.add(cube.draw());
            myGUI.cubeSpace.repaint();
        }
        // </editor-fold> // D prime
        // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
        else if(((JButton) arg0.getSource()).getText().equals("Do it yourself"))
        {
            methodValue = 0;
            myGUI.ltfil1.add(myGUI.mL);
            myGUI.ltfil1.add(myGUI.mLp);
            myGUI.ltfil1.add(myGUI.mR);
            myGUI.ltfil2.add(myGUI.mRp);
            myGUI.ltfil2.add(myGUI.mF);
            myGUI.ltfil2.add(myGUI.mFp);
            myGUI.rtfil1.add(myGUI.mB);
            myGUI.rtfil1.add(myGUI.mBp);
            myGUI.rtfil1.add(myGUI.mU);
            myGUI.rtfil2.add(myGUI.mUp);
            myGUI.rtfil2.add(myGUI.mD);
            myGUI.rtfil2.add(myGUI.mDp);
            myGUI.method1.setForeground(Color.black);
            myGUI.method2.setForeground(Color.black);
            myGUI.method3.setForeground(Color.black);
            myGUI.doItYourself.setForeground(Color.red);
            myGUI.arrangement.validate();
        }
        // </editor-fold> // Do it yourself
    }
}