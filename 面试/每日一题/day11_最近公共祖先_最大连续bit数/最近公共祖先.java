public class LCA {
    public int getLCA(int a, int b) {
        
        
        while( a!= b){
            if(a < b){
                b = b/2;
            }else{
                a = a/2;
            }
        }
        return a;
    }
}