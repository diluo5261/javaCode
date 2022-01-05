//你有三种硬币,分别面值2元,5元和7元,每种可以选择多个,组成27元

public int coinChange(int[] A,int M){
    int [] f = new int[M+1];
    int n = A.length;
    
    f[0] = 0;
    int i,j;
    //f[1],f[2].f[27]
    for(i=0;i<= M;i++){
        f[i] = Integer.MAX_VALUE;
        
        //f[i] = min{f[i-a[j]]+1,… …，f[i-A[n]]+1}
        for(j=0;j<n;j++){
            if(i >= A[j] && f[i-A[j]] != Integer.MAX_VALUE){
                 f[i] = Math.min{f[i-A[j]]+1,f[i]};
            }
        }
    }
    
    if(f[M] = Integer.MAX_VALUE){
        f[M] = -1;
    }
    
    return f[M];
   
}