public class converToBinary {
    public static void main(String[] args) {
        for(int i = 0; i<16;i++){
            while(i!=0){
                if ((i&1) == 1){
                    System.out.print(1 + "\t");
                }
                else{
                    System.out.print(0 + "\t");
                }
                i = i>>1;

            }
            System.out.println();
        }
    }
}
