#include<iostream>
#include<cstdio>
#include<vector>
#include<algorithm>
#define MAXN (int)1e6
#define lowbit(x)x&-x
using namespace std;

int N,M,arr[MAXN],bit[MAXN];

void update(int x, int val){
    for(int i = x; i <= MAXN;i+=lowbit(i))
        bit[i]+=val;
}
long long query(int x){
    long long sum = 0;
    for(int i = x; i > 0;i-=lowbit(i))
        sum+=bit[i];
    return sum;
}
long long query(int x, int y){
    return query(y)-query(x-1);
}
long long occur(int v){
    long long cnt = 0;
    for(int i = 1; i <= N;++i){
        if(arr[i]<=v)++cnt;
    }
    return cnt;
}
int main(){
    freopen("input.txt","r",stdin);
    scanf("%d%d",&N,&M);
    for(int i = 1; i <= N;++i){
        scanf("%d",&arr[i]);
        update(i,arr[i]);
    }
    int a = 0,b = 0;char cmd;
    for(int i = 0; i < M;++i){
        scanf(" %c",&cmd);
        if(cmd == 'C'){
            scanf("%d%d",&a,&b);
//            printf("%c %d %d\n",cmd,a,b);
            arr[a] = b;
            update(a,b);
        }
        else if(cmd == 'S'){
            scanf(" %d%d",&a,&b);
  //          printf("%c %d %d\n",cmd,a,b);
            printf("%lld\n",query(a,b));
        }
        else if(cmd == 'Q'){
            scanf("%d",&a);
    //        printf("%c %d\n",cmd,a);
            printf("%lld\n",occur(a));
        }
    }

    return 0;
}
