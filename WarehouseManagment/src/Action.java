import java.util.Random;

public class Action {
	private int[] eLp;
	private int[] eRp;
	private int[] stateL;
	private int[] stateR;
	private int[] beltState;
	private int[] conflict;
	private int L;
	private int[] actionL;
	private int[] actionR;
	
	
	public Action(int[] stateL, int[] stateR, int[] eLp, int[] eRp, int[] beltState){
		this.stateL=stateL;
		this.stateR=stateR;
		this.eLp=eLp;
		this.eRp=eRp;
		this.beltState=beltState;
		
		L=beltState.length;
		conflict=new int[L];
		
		for (int i=0; i<L; i++){
			conflict[i]=eLp[i]+eRp[i];
			
		}
		actionL=new int[L];
		actionR= new int[L];
				
	}
	public void generateAction(String[][] w, String [] belt, int position){
		
		int nC =w[0].length;
		int[] state=new int[L];
		int[] action=new int[L];
		if(position==0){
			System.arraycopy(stateL, 0, state, 0, L);
		}else{
			System.arraycopy(stateR, 0, state, 0, L);
		}
		for (int i=0; i<L; i++){
			if(state[i]==2){
				action[i]=3;
			}
			if((state[i]==3)&(beltState[i]==3)){
				action[i]=4;
			}
			if((state[i]==1)&(beltState[i]==1)){
				action[i]=1;
				for(int j=0; j<nC; j++){
					if(belt[i].equals(w[i][j])){
						action[i]=2;
						
					}
				}
			}
			
			if(((state[i]==3)&(beltState[i]==1))|((state[i]==3)&(beltState[i]==2))){
				action[i]=2;
			}
			if(((state[i]==1)&(beltState[i]==2))|((state[i]==1)&(beltState[i]==3))){
				action[i]=2;
			}
			
		}
		
		if(position==0){
			for (int i=0; i<L; i++){
				if(eLp[i]==0){
					action[i]=2;
				}
			}
		}else{
			for (int i=0; i<L; i++){
				if(eRp[i]==0){
					action[i]=2;
				}
			}
		}
		if(position==0){
			System.arraycopy(state, 0, stateL, 0, L);
			System.arraycopy(action, 0, actionL, 0, L);
		}else{
			System.arraycopy(state, 0, stateR, 0, L);
			System.arraycopy(action, 0, actionR, 0, L);
		}
	}
	public void conflictCheck(){
		for (int i=0; i<L; i++){
			if(conflict[i]>1){
				if((actionL[i]==1)&(actionR[i]==1)){
					conflictSolve(i);
				}
				if((actionL[i]==1)&(actionR[i]==4)){
					conflictSolve(i);
				}
				if((actionL[i]==4)&(actionR[i]==4)){
					conflictSolve(i);
				}
				if((actionL[i]==4)&(actionR[i]==1)){
					conflictSolve(i);
				}
				
			}
		}
	}
	
	private void conflictSolve(int k){
		Random randomGenerator= new Random();
		
		if(randomGenerator.nextInt(2)>0){
			actionL[k]=2;
			
		}else{
			actionR[k]=2;
		}
	}
	
	public void updateState(int[] stateL, int[] stateR, int[] beltState){
		
		this.stateL=stateL;
		this.stateR=stateR;		
		this.beltState=beltState;
		
	}

}
