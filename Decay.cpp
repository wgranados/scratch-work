#include <iostream>
#include <stdio.h>
#include <math.h>
using namespace std;

int cmd;
double n,k,t,y;
const double e = 2.71828182845904523536;

void input(string s, double &in){
    cout << s;
    cin >> in;
}
void init_screen(){
    printf("1.Final Amount\n");
    printf("2.Initial Amount\n");
    printf("3.Constant (half-life)\n");
    printf("Find:");
    scanf("%d",&cmd);
}
int main(){
   init_screen();
    if(cmd == 1){
        input("Enter initial mass: ", n);
        input("Enter constant value: ", k);
        input("Enter elasped time in years: ", t);
        printf(" Final Amount: %.5f\n", (n*pow(e,-k*t)));
    }
    else if(cmd == 2){
        input("Enter final mass: ", y);
        input("Enter constant value:", k);
        input("Enter elasped time in years: ", t);
        printf(" Initial Amount: %.5f\n", (y/pow(e,-k*t)));
    }
    if(cmd == 3){
        input("Enter initial mass: ",n);
        input("Enter final mass: ",y);
        input("Enter elasped time in years: ",t);
        printf(" Constant(half-life): %.5f\n", -1*(log10(y/n)/(t*log10(e))) );
    }
    return 0;
}
