/*
The formula y = ne^kt can be used for estimating growth where:
y is the final amount n is the initial amount k is a constant t is the time
For example, this formula could be used for estimating population growth in a region or for estimating cell growth in a lab experiment.
Create a BacteriaGrowth application that calculates how many bacteria will be present based on this formula.
The application should prompt the user initial bacteria, the constant k, and the time.
Refer to Exercise 12 for documentation for the Math methods for this exercise.
Application output should look similar to:
*/
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
