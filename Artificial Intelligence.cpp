#include<iostream>
#include<stdio.h>
#include<stdlib.h>
#include<vector>
#include<cmath>

using namespace std;

int N;
double U,I,P;
double pre_u,pre_i,pre_p;
vector<string>in;
string s;

void input(){
    char c;
    while(scanf("%c",&c) && c != '\n'){
        if(c == ' '){
            in.push_back(s);
            s = "";
            continue;
        }
        s+=c;
    }
    in.push_back(s);
    s = "";
}
double prefix(char c){
    return (c == 'm') ? pow(10,-3): ((c == 'k') ? pow(10,3): ((c == 'M') ? pow(10,6):1));
}
int main(){
    scanf("%d",&N);
    getline(cin,s);
    for(int t = 1;t <= N;t++){
        input();
        for(int i = 0; i < in.size();i++){
            if(in[i][0] == 'U'){
                if( prefix( in[i][in[i].size()-2] ) == 1){
                    pre_u = 1.0;
                    U = atof(in[i].substr(2,in[i].size()-2-1).c_str());
                }
                else{
                    pre_u = prefix( in[i][in[i].size()-2] );
                    U = atof(in[i].substr(2,in[i].size()-2-2).c_str());
                }
            }
            else if(in[i][0] == 'I'){
                if( prefix( in[i][in[i].size()-2] ) == 1){
                    pre_i = 1.0;
                    I = atof(in[i].substr(2,in[i].size()-2-1).c_str());
                }
                else{
                    pre_i = prefix( in[i][in[i].size()-2] );
                    I = atof(in[i].substr(2,in[i].size()-2-2).c_str());
                }
            }
            else if(in[i][0] == 'P'){
                if( prefix( in[i][in[i].size()-2] ) == 1){
                    pre_p = 1.0;
                    P = atof(in[i].substr(2,in[i].size()-2-1).c_str());
                }
                else{
                    pre_p = prefix( in[i][in[i].size()-2] );
                    P = atof(in[i].substr(2,in[i].size()-2-2).c_str());
                }
            }
        }
        printf("Problem #%d\n",t);
         if(P == 0)
            printf("P=%.2fW\n\n", (pre_i*I)*(pre_u*U));
        else if(U == 0)
            printf("U=%.2fW\n\n", (pre_p*P)/(pre_i*I));
        else if(I == 0)
            printf("I=%.2fW\n\n", (pre_p*P)/(pre_u*U));
        pre_p = pre_i = pre_u = 0;
        P = I = U = 0;
        in.clear();
    }
}
