/*Problem description: http://wcipeg.com/problems/desc/segtree*/
#include<iostream>
#include<cstdio>
#include<vector>
#include<algorithm>
#define MAXN 100000
#define MAX_INT (int)1e7
using namespace std;

vector<int> tree(4*MAXN),arr(MAXN);
int N,Q;
int qlo,qhi,index;

void make_tree(int lo, int hi, int x){
    if(lo == hi){
        tree[x] = arr[lo];
    }
    else{
        make_tree(lo, (lo+hi)/2, 2*x+1);
        make_tree((lo+hi)/2+1, hi, 2*x+2);
        tree[x] = min(tree[2*x+1],tree[2*x+2]);
    }
}

int query(int lo, int hi, int x){
    if( lo >= qlo && hi <= qhi)
        return tree[x];
    else if( (lo <= qlo && hi <= qhi && hi >= qlo) || (lo >= qlo && lo <= qhi) || (lo <= qlo && hi >= qhi)){
        int a1 = query(lo,(lo+hi)/2,2*x+1);
        int a2 = query( (lo+hi)/2+1,hi,2*x+2);
        return min(a1,a2);
    }
    else
        return MAX_INT;
}
void update(int lo, int hi,int x){
    if(index <= hi && index >= lo){
        if(lo == hi){
            tree[x] = arr[lo];
        }
        else{
            update(lo,(lo+hi)/2,2*x+1);
            update((lo+hi)/2+1,hi,2*x+2);
            tree[x] = min(tree[2*x+1],tree[2*x+2]);
        }
    }
}
int main(){
    scanf("%d%d",&N,&Q);
    fill(tree.begin(),tree.end(),MAX_INT);
    for(int i = 0; i < N;++i)
        scanf("%d",&arr[i]);
    make_tree(0,N-1,0);
    int a,b;
    char cmd;
    for(int i = 0; i < Q;++i){
        scanf(" %c%d%d",&cmd,&a,&b);
        if(cmd == 'Q'){
            qlo = a, qhi = b;
            printf("%d\n",query(0,N-1,0));
        }
        else{
            index = a;
            arr[a] = b;
            update(0,N-1,0);
        }
    }
    return 0;
}
