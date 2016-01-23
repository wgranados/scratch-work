/**
An interesting (yet unsolved) question in mathematics is called “hailstone numbers.” 
This series is produced by taking an initial integer, and if the number is even, dividing it by 2.
If the number is odd, multiply it by 3 and add 1.
This process is then repeated. For example, an initial number of 10 produces:
10, 5, 16, 8, 4, 2, 1, 4, 2, 1 ... An initial value of 23 produces:
23, 70, 35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1, 4, 2, 1 ...
Note that both numbers eventually reach the 4, 2, 1, 4, 2, 1 ... cycle.
Create two applications (Hailstone1 and Hailstone2) that answer the following questions for initial values of 1 to 200:
a)  Do all integers from 1 to 200 eventually reach this cycle?
*/

/** If this program reaches an infinite loop
    then that means that the numbers reach another
    cycle that is not the cycle 4-2-1, however since
    it does not reach an infinite loop after 200 attempts
    this implicitly means that all integers from 1 to 200
    eventually reach the cycle 4-2-1
*/

#include <iostream>
#include <stdio.h>

using namespace std;

int n;
bool b = true;

int main(){
    for(int i = 1; i <= 200;i++){
        n = i;
        while(!(n == 4)){
            if(n % 2 == 0){
                n/=2;
            }
            else{
                n*=3;
                n++;
            }
        }
         if(n != 4)b = false;
    }
    if(b)cout << "YES" <<endl;
    else cout << "NO" << endl;
}
