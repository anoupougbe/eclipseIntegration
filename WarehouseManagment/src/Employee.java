
public class Employee {
	public String[][] eL;
	public String[][] eR;
	private int[] eLp;
	private int[] eRp;
	private int[] stateL;
	private int[] stateR;
	private int L;
	private int nC;
	public  String[]belt;
	public Employee(int nC, String[] belt){
		L=belt.length;
		this.nC=nC;
		this.belt=new String[L];
		System.arraycopy(belt, 0, this.belt, 0, L);
		eL=new String[L][nC];
		eR=new String[L][nC];
		eLp=new int[L];
		eRp=new int[L];
		stateL=new int[L];
		stateR=new int[L];
		init();
		
	}
	private void init(){
		for (int i=0; i<L; i++){
			for (int j=0;j<nC; j++){
				eL[i][j]=null;
				eR[i][j]=null;
			}
			eLp[i]=1;
			eRp[i]=1;
			stateL[i]=1;
			stateR[i]=1;
			
		}
	}
	
	public void updateState(int position){
		
		if(position==0){
			for (int i=0;i<L; i++){
				if ("p".equals(eL[i][0])){
					stateL[i]=3;
				}else if(eL[i][nC-1]==null){
					stateL[i]=1;
				}else stateL[i]=2;
			}
		}
		if(position==1){
			for (int i=0;i<L; i++){
				if ("p".equals(eR[i][0])){
					stateR[i]=3;
				}else if(eR[i][nC-1]==null){
					stateR[i]=1;
				}else stateR[i]=2;
			}
 		}
	}
	public void doWork(int [] action, int position){
		String[][] W= new String[L][nC];
		int k;
		if(position==0){
			for(int i=0; i<L;i++){
				System.arraycopy(eL[i], 0, W[i], 0, nC);
			}
		}else{
			for(int i=0; i<L; i++){
				System.arraycopy(eR[i], 0, W[i], 0,nC);
			}
		}
		for (int i=0; i<L; i++){
			if (action[i]==1){
				k=0;
				while(k>=0){
					if(W[i][k]==null){
						W[i][k]=belt[i];
						k=-100;						
					}
					k+=1;
				}
				belt[i]=null;
			}
			if(action[i]==3){
				for(int j=1;j<nC;j++){
					W[i][j]=null;
				}
				W[i][0]="P";
			}
			if (action[i]==4){
				belt[i]="P";
				for(int j=0; j<nC; j++){
					W[i][j]=null;
				}
			}
		}
		if (position==0){
			for (int i=0; i<L; i++){
				System.arraycopy(W[i], 0, eL[i], 0, nC);
			}
		}else{
			for(int i=0; i<L; i++){
				System.arraycopy(W[i], 0, eR[i], 0, nC);
			}
		}
		updateState(position);
}
	public void updateBellt(String[] belt){
		System.arraycopy(belt, 0, this.belt, 0, L);
	}
	
	public int[] getStateL(){
		return stateL;
	}
	public int[] getStateR(){
		return stateR;
	}
	public int[] getOperatorL(){
		return eLp;
	}
	public int[] getOperatorR(){
		return eRp;
	}
}