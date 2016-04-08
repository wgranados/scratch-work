#include<iostream>
#include<cstdio>
using std::cin;
using std::endl;
using std::cout;

bool inequality_holds(int prime)
{
    // check if the inequality, a^(p-1) = 1 mod(p^2), where a < p
    int ret = 1;
    // use modluar arithmetic to avoid integer overflow
    for(int i = 1;i <= prime-1;i++)
    {
        ret *= 2; // let a = 2
        ret %= prime*prime;
    }
    ret %= prime*prime;
    return (ret == 1);
}

int main()
{
    freopen("primes.txt","r",stdin);
    freopen("output.txt","w",stdout);
    int prime = 0;
    while(cin >> prime)
    {
        if(inequality_holds(prime))
        {
            cout << prime << endl;
            break;
        }
    }
    fclose(stdin);
    fclose(stdout);
    return 0;
}