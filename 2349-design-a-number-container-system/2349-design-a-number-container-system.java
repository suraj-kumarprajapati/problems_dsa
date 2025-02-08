class NumberContainers {

    Map<Integer, Integer> indToNum;
    Map<Integer, TreeSet<Integer>> numToIndices;

    public NumberContainers() {
        indToNum = new HashMap<>();
        numToIndices = new HashMap<>();
    }
    
    public void change(int index, int number) {

        int prevNum = -1;
        if(indToNum.containsKey(index)) {
            prevNum = indToNum.get(index);
        }

        // remove the previous num to index mapping
        if(prevNum != -1) {
            numToIndices.get(prevNum).remove(index);
        }

        if(numToIndices.get(prevNum) != null && numToIndices.get(prevNum).isEmpty()) {
            numToIndices.remove(prevNum);
        }


        indToNum.put(index, number);
        
        if(!numToIndices.containsKey(number)) {
            numToIndices.put(number, new TreeSet<Integer>());
        }

        numToIndices.get(number).add(index);
        
    }
    
    public int find(int number) {
        int smallestInd = -1;

        if( numToIndices.containsKey(number) ) {
            smallestInd = numToIndices.get(number).first();
        }

        return smallestInd;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */