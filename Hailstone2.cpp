/*
An interesting (yet unsolved) question in mathematics is called “hailstone numbers.” 
This series is produced by taking an initial integer, and if the number is even, dividing it by 2.
If the number is odd, multiply it by 3 and add 1.
This process is then repeated. For example, an initial number of 10 produces:
10, 5, 16, 8, 4, 2, 1, 4, 2, 1 ... An initial value of 23 produces:
23, 70, 35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1, 4, 2, 1 ...
Note that both numbers eventually reach the 4, 2, 1, 4, 2, 1 ... cycle.
Create two applications (Hailstone1 and Hailstone2) that answer the following questions for initial values of 1 to 200:
b)  What is the maximum number of iterations to reach the cycle and which starting number produces this maximum? 
*/
#include <iostream>
#include <stdio.h>

using namespace std;

int n,size, max_size,max_n;

int main(){
    for(int i = 1; i <= 200;i++){
        n = i;
        size = 0;
        while(!(n == 4)){
            size++;
            if(n % 2 == 0){
                n/=2;
            }
            else{
                n*=3;
                n++;
            }
        }
        if(max_size < size){
            max_n = i;
            max_size = size;
        }
    }
    printf("%d produces the maximum number of iterations %d\n ", max_n, max_size);
}
