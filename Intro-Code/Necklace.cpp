/*
An interesting problem in number theory is sometimes called the “necklace problem.”
This problem begins with two single-digit numbers. 
The next number is obtained by adding the first two numbers together and saving only the ones digit.
This process is repeated until the “necklace” closes by returning to the original two numbers.
For example, if the starting two numbers are 1 and 8, twelve steps are required to close the necklace: 1 8 9 7 6 3 9 2 1 3 4 7 1 8
Create a Necklace application that prompts the user for two single-digit integers and then displays the sequence and the number of steps taken.
The application output should look similar to:
*/

#include<iostream>
#include<stdio.h>

using namespace std;

int a,b,c,d,si;

void input(string s, int &in, int &in2){
    cout << s;
    cin >> in;
    in2 = in;
}
int main(){
    input("Enter the first starting number: ",a,c);
    input("Enter the second starting number: ",b,d);
    cout << a << " " << b << " ";
    si = (c+d)%10;
    cout << si << " ";
    c = d;
    d = si;
    while(!(a == c && b == d)){
        si = (c+d)%10;
        cout << si << " ";
        c = d;
        d = si;
    }
    return 0;
}
