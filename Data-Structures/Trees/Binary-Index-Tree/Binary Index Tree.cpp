/**http://www.dmoj.ca/problem/ds1*/
#include<iostream>
#include<cstdio>
#include<algorithm>
#define MAXN (int)1e6 +1
#define lowbit(x) x&(-x)
using namespace std;

int N,M,arr[MAXN];
long long sumtree[MAXN];
long long occtree[MAXN];

void inc_update(int x, int val){
    for(int i = x;i <= MAXN;i+=lowbit(i))
        sumtree[i]+=val;
    for(int i = val; i <= MAXN;i+=lowbit(i))
        occtree[i]++;
}
void dec_update(int x, int val){
    for(int i = x;i <= MAXN;i+=lowbit(i))
        sumtree[i]-=val;
    for(int i = val; i <= MAXN;i+=lowbit(i))
        occtree[i]--;
}
long long query(int x,long long arr[]){
    long long sum = 0;
    for(int i = x; i > 0;i-=lowbit(i))
        sum+=arr[i];
    return sum;
}
long long query_range(int x, int y,long long arr[]){
    return query(y,arr) - query(x-1,arr);
}
int main(){
    //freopen("input.txt","r",stdin);
    scanf("%d%d",&N,&M);
    for(int i = 1; i <= N;++i){
        scanf("%d",&arr[i]);
        inc_update(i,arr[i]);
    }
    char cmd;
    int a,b;
    for(int i = 0; i < M;++i){
        scanf(" %c",&cmd);
        if(cmd == 'C'){
            scanf("%d%d",&a,&b);
            dec_update(a,arr[a]);
            inc_update(a,b);
            arr[a]=b;
        }
        if(cmd == 'S'){
            scanf("%d%d",&a,&b);
            printf("%Ld\n",query_range(a,b,sumtree));
        }
        if(cmd == 'Q'){
            scanf("%d",&a);
            printf("%Ld\n",query(a,occtree));
        }
    }
    return 0;
}

