
import java.util.ArrayList;


public class Main {
    static ArrayList<Integer> arrayListPrimeFactors = new ArrayList<>();
    static ArrayList<Integer> arrayListPrimeNumber = new ArrayList<>();
    static ArrayList<Integer> arrayListPowPrimeFactors=new ArrayList<>();
    static ArrayList<Integer> arrayListExponent =new ArrayList<>();

    private static int exponent=2,index=0;
    public static void main(String[] args)
    {
        int i= it.unibs.fp.mylib.InputDati.leggiIntero("Number: ");
        sieveOfEratosthenes(i);
        int result=phiEulero(i);
        System.out.print(result);
    }

    private static int phiEulero(int n){
    primeFactors(n);
    //n is a prime number
    if(isPrimo(n)){
        return n-1;
    }
    //n is the multiply of two prime number
    if(arrayListPrimeFactors.size()==2){
        return (((arrayListPrimeFactors.get(0))-1)*((arrayListPrimeFactors.get(1)-1)));
    }

    //n is the power of a prime number
    if(isPowOfaPrimeNumber(n)){
        return (int)(Math.pow(arrayListPrimeNumber.get(index),exponent)-Math.pow(arrayListPrimeNumber.get(index),exponent-1));
    }

    int multiply=arrayListPrimeFactors.get(0);
    int count=1;
    for(int i=1; i<arrayListPrimeFactors.size();i++){
        if(multiply==arrayListPrimeFactors.get(i)){
            count++;
        }else{
            arrayListPowPrimeFactors.add((int)Math.pow(multiply,count));
            multiply=arrayListPrimeFactors.get(i);
            arrayListExponent.add(count);
        }
    }
    arrayListPowPrimeFactors.add((int)Math.pow(multiply,count));
    if(arrayListPowPrimeFactors.size()==2){
        return phiEulero((arrayListPowPrimeFactors.get(0))*phiEulero((arrayListPowPrimeFactors.get(1))));
    }
    return 1;
    }

    static int mcd(int x, int y) {

        int i;
        int z = 0;

        while ( x != y ) {

            if ( x > y ) {
                x = x - y;
            } else {
                y = y - x;
            }
        }

        return x;
    }

    private static boolean isPrimo(int m){
        int num=m-1;

        do{
            if(m%num == 0){
                return false;
            }
            num--;
        }while(num>=2);
        return true;
    }


    public static void primeFactors(int n)
    {

        // Print the number of 2s that divide n
        while (n%2==0)
        {
            arrayListPrimeFactors.add(2);
            n /= 2;
        }

        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i+= 2)
        {
            // While i divides n, print i and divide n
            while (n%i == 0)
            {
                arrayListPrimeFactors.add(i);
                n /= i;
            }
        }

        // This condition is to handle the case whien
        // n is a prime number greater than 2
        if (n > 2)
            arrayListPrimeFactors.add(n);
    }

    private static void sieveOfEratosthenes(int n)
    {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n+1];
        for(int i=0;i<n;i++)
            prime[i] = true;

        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
                arrayListPrimeNumber.add(i);
        }
    }

    private static boolean isPowOfaPrimeNumber(int n){
    for(int i=0; i<arrayListPrimeNumber.size();i++){
        if(n==Math.pow(arrayListPrimeNumber.get(i),exponent)){
            index=i;
            return true;
        }else{
            exponent++;
        }
        if(Math.pow(arrayListPrimeNumber.get(i),exponent)>n){
            i++;
            exponent=0;
        }
    }
    return false;
    }

}
