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
