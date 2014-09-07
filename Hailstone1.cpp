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
