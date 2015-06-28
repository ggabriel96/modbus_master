package gui;
class DataStruct {
    int info;
    String name;
    int reg, bit;

    public DataStruct(int info, int reg, int bit, String name) {
        this.info = info;
        this.name = name;
        this.reg = reg;
        this.bit = bit;
    }

    public int newBit(int data){
    	String tmp;
    	int i, j, res, pot, ret[];
    	
    	Main.master.read(reg, 1);
    	
    	while (Main.dataRead == null); //Verficar tempo
    	tmp = Integer.toBinaryString(Main.dataRead);
    	ret = new int[8];
    	
    	for (i = tmp.length() - 1, j = 7; i > tmp.length() - 9 && i >= 0; i--, j--) {
    		ret[j] = tmp.charAt(i) - '0';
    	}

    	ret[this.bit] = data;

    	for (i = ret.length - 1, pot = 1, res = 0; i >=0; i--, pot *= 2) {
    		res += ret[i] * pot;
    	}
    	
    	info = data;
    	
    	Main.dataRead = null;
    	return res;
    }

}
