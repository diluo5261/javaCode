/*
描述：

有n块石头分别在X轴的0，1，2，……n-1位置，一只青蛙在第i块石头上，它最多可以向右跳距离ai，问青蛙能否跳到石头n-1

*/



public boolean canJump(int[] A){
    int n = A.length;
    boolean[] f = new boolean[n];
    f[0] = true;
    
    for(int j=1;j<n;j++){
        f[j]=false;
        //previous stone i
        //last jump is from i to j
        for(int i =0;i<j;i++){
            if(f[i] && i+A[i] >= j){
                f[j] = true;
                break;
            }
            
        }
    }
    return f[n-1];    
}