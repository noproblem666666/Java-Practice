package TrueTest.MeiTuan;
//计算最大公约数和素数,纯模拟题
public class Test0817_02 {
    //计算两个数的最大公约数(欧几里得算法，辗转相除法)
    public int gcd(int a,int b){
        while(b!=0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    //检查一个数是否为素数
    public boolean isPrime(int num){
        if(num<=1) return false;
        if(num<=3) return true;
        if(num%2==0||num%3==0) return false;
        for(int i = 5;i*i<=num;i+=6){
            if(num%i==0||num%(i+2)==0) return false;
        }
        return true;
    }


}
