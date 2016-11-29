package rubikscubesimulatorv2;


public class SolvingFunctions {
    
    int[] sideValues;
  
    
    public SolvingFunctions(int[] sv)
    {
        sideValues = sv;
    }   
    public int checkCross(int[] sv)
    {
        sideValues = sv;
        int numCross = 0;
        if(sideValues[1] == 1) {
            numCross++;
        } if(sideValues[3] == 1) {
            numCross++;
        } if(sideValues[5] == 1) {
            numCross++;
        } if(sideValues[7] == 1) {
            numCross++;
        }
        return numCross;
    }  
    public int checkAlignment(int[] sv)
    {
        sideValues = sv;
        int num = 0;
        for(int manipSide = 2; manipSide <= 5; manipSide++) {
            if(sideValues[(manipSide-1) * 9 + 4] == sideValues[(manipSide-1) * 9 + 7]) {
                num++;
            }
        }
        return num;
    }
    public int checkCorners(int[] sv)
    {
        sideValues = sv;
        int num = 0;
        if(sideValues[8] == 1 && sideValues[15] == 2 && sideValues[44] == 5) {num++;}
        if(sideValues[6] == 1 && sideValues[17] == 2 && sideValues[24] == 3) {num++;}
        if(sideValues[2] == 1 && sideValues[35] == 4 && sideValues[42] == 5) {num++;}
        if(sideValues[0] == 1 && sideValues[26] == 3 && sideValues[33] == 4) {num++;}  
        return num;
    }
    public int checkMidEdges(int[] sv)
    {
        sideValues = sv;
        int num = 0;
        if(sideValues[14] == 2 && sideValues[21] == 3) {
            num ++;
        }
        if(sideValues[23] == 3 && sideValues[30] == 4) {
            num ++;
        }
        if(sideValues[32] == 4 && sideValues[39] == 5) {
            num ++;
        }
        if(sideValues[41] == 5 && sideValues[12] == 2) {
            num ++;
        }
        return num;
    }
    public boolean checkTopSide(int[] sv)
    {
        sideValues = sv;
        int numWhite = 0;
        for(int i = 45; i < 54; i++) {
            if(sideValues[i] == 6) {
                numWhite++;
            }
        }
        if(numWhite == 9) {
            return true;
        } else {
            return false;
        }
    }
    public int checkTopStickers(int[] sv)
    {
        sideValues = sv;
        int numWhite = 0;
        for(int i = 45; i < 54; i++) {
            if(sideValues[i] == 6) {
                numWhite++;
            }
        }
        return numWhite;
    }
    public int checkTopEdges(int[] sv)
    {
        sideValues = sv;
        int num = 0;
        if(sideValues[9] == sideValues[11]) {
            num++;
        }
        if(sideValues[18] == sideValues[20]) {
            num++;
        }
        if(sideValues[27] == sideValues[29]) {
            num++;
        }
        if(sideValues[36] == sideValues[38]) {
            num++;
        }
        
        return num;
    }
    public int checkTopCenters(int[] sv)
    {
        sideValues = sv;
        int num = 0;
        if(sideValues[10] == 2) {
            num++;
        }
        if(sideValues[19] == 3) {
            num++;
        }
        if(sideValues[28] == 4) {
            num++;
        }
        if(sideValues[37] == 5) {
            num++;
        }
        
        return num;
    }
    public boolean checkTwoCorners(int[] sv)
    {
        sideValues = sv;
        boolean works = false;
        if(sideValues[17] == 2 && sideValues[24] == 3 && sideValues[6] == 1) {
            if(sideValues[26] == 3 && sideValues[33] == 4 && sideValues[0] == 1) {
                works = true;
            }
        }
        
        return works;
    }
    public boolean checkTopCross(int[] sv)
    {
        sideValues = sv;
        boolean crossSolved = false;
        if(sideValues[46] == 6 && sideValues[48] == 6 && sideValues[50] == 6 && sideValues[52] == 6) {
            crossSolved = true;
        }
        return crossSolved;
    }
    public boolean checkLeftCross(int[] sv)
    {
        sideValues = sv;
        boolean crossSolved = false;
        if(sideValues[37] == 5 && sideValues[39] == 5 && sideValues[41] == 5 && sideValues[43] == 5) {
            crossSolved = true;
        }
        return crossSolved;
    }
    public boolean checkTopCorners(int[] sv)
    {
        if(sideValues[45] == 6 && sideValues[47] == 6 && sideValues[51] == 6 && sideValues[53] == 6) {
            return true;
        } else {
            return false;
        }
    }
}
