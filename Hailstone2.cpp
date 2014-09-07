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
