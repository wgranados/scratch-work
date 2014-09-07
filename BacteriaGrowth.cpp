#include<iostream>
#include<stdio.h>
#include<cmath>

using namespace std;

double n,k,t;
const double e = 2.71828182845904523536;

void input(string s, double &in){
    cout << s;
    cin >> in;
}
int main(){
    input("Enter initial bacteria amount: ", n);
    input("Enter a constant value for k: ", k);
    input("Enter the growth time period in hours: ", t);
    printf("%.1f bacteria will be present after %.1f hours.", (n*pow(e,k*t)), t);
    return 0;
}
