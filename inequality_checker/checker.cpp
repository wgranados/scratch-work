#include<iostream>
#include<cstdio>
using std::cin;
using std::endl;
using std::cout;

/* This function calculates (a^b)%c */
int modulo(int a,int b,int c)
{
    // long long is taken to avoid overflow of intermediate results
    long long x=1,y=a;
    while(b > 0)
    {
        if(b%2 == 1)
        {
            x=(x*y)%c;
        }
        y = (y*y)%c; // squaring the base
        b /= 2;
    }
    return x%c;
}

int main()
{
    freopen("primes.txt","r",stdin);
    freopen("output.txt","w",stdout);
    int prime = 0;
    while(cin >> prime)
    {
        if(modulo(2, prime-1, prime*prime) == 1)
        {
            cout << prime << endl;
        }
    }
    fclose(stdin);
    fclose(stdout);
    return 0;
}