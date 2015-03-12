/*Problem Description: http://www.dmoj.ca/problem/ds3*/
#include<iostream>
#include<cstdio>
#include<algorithm>
using namespace std;

const int MAXN = (int)1e5+1,inf = (int)1e9+1;
int N,M,Q,a,b,qlo,qhi,idx;
int A[MAXN],TM[4*MAXN],TGCD[4*MAXN];
char cmd;

int gcd(int X, int Y) {
    int tmp;
    while (X != Y && Y != 0) {
        tmp = X;
        X = Y;
        Y = tmp % Y;
    }
    return X;
}

void build(int n,int lo,int hi){
    if(lo == hi){
        TM[n]   = A[lo];
        TGCD[n] = A[lo];
    }
    else{
        build(2*n+1,lo,(lo+hi)/2);
        build(2*n+2,(lo+hi)/2+1,hi);
        TM[n]   = min(TM[2*n+1],TM[2*n+2]);
        TGCD[n] = gcd(TGCD[2*n+1],TGCD[2*n+2]);
    }
}

int queryM(int n,int lo,int hi){
    if(lo > qhi || hi < qlo)
        return inf;
    else if(lo >= qlo && hi <= qhi)
        return TM[n];
    else
        return min(queryM(2*n+1,lo,(lo+hi)/2),queryM(2*n+2,(lo+hi)/2+1,hi));
}

int queryGCD(int n,int lo,int hi){
    if(lo > qhi || hi < qlo)
        return 0;
    else if(lo >= qlo && hi <= qhi)
        return TGCD[n];
    else
        return gcd(queryGCD(2*n+1,lo,(lo+hi)/2),queryGCD(2*n+2,(lo+hi)/2+1,hi));
}

int queryQ(int n,int lo,int hi){
    if(lo > qhi || hi < qlo || queryGCD(n,lo,hi)!=Q)
        return 0;
    else if(lo == hi)
        return (A[lo] == Q) ? 1:0;
    else
        return queryQ(2*n+1,lo,(lo+hi)/2)+queryQ(2*n+2,(lo+hi)/2+1,hi);
}

void update(int n,int lo,int hi){
    if(idx >= lo && idx <= hi){
        if(lo == hi){
            TM[n]   = A[lo];
            TGCD[n] = A[lo];
        }
        else{
            update(2*n+1,lo,(lo+hi)/2);
            update(2*n+2,(lo+hi)/2+1,hi);
            TM[n]   = min(TM[2*n+1],TM[2*n+2]);
            TGCD[n] = gcd(TGCD[2*n+1],TGCD[2*n+2]);
        }
    }
}
int main(){
    //freopen("input.txt","r",stdin);
    scanf("%d%d",&N,&M);N--,M--;
    for(int i = 0;i <= N;i++){
        scanf("%d",&A[i]);
    }
    build(0,0,N);
    for(int i = 0;i <= M;i++){
        scanf(" %c%d%d",&cmd,&a,&b);
        if(cmd == 'M'){
            qlo = --a,qhi = --b;
            printf("%d\n",queryM(0,0,N));
        }
        else if(cmd == 'G'){
            qlo = --a,qhi = --b;
            printf("%d\n",queryGCD(0,0,N));
        }
        else if(cmd == 'Q'){
            qlo = --a,qhi = --b;
            Q = queryGCD(0,0,N);
            printf("%d\n",queryQ(0,0,N));
        }
        else{
            A[--a]=b;
            idx = a;
            update(0,0,N);
        }
    }
    return 0;
}
