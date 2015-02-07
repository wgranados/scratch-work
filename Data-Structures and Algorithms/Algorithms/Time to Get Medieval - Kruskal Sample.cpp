/*Sample solution for
    http://wcipeg.com/problems/desc/wc01p8
*/
#include<iostream>
#include<cstdio>
#include<cstring>
#include<vector>
#include<queue>
#include<algorithm>
#define pb push_back
#define mp make_pair
using namespace std;

const int MAXN = 101;
int T,N,C,n,tot_d;
int parent[MAXN];
vector< pair<int,pair<int,int> > >edges;

int find(int x){
    if(parent[x]!=x)parent[x]=find(parent[x]);
    return parent[x];
}
bool merge(int x, int y){
    x = find(x);
    y = find(y);
    if(x == y)
        return false;
    else if(parent[x] < parent[y])
        parent[y] = x;
    else
        parent[x] = y;
    return true;
}
int main(){
    scanf("%d",&T);
    while(T--){
        scanf("%d%d",&N,&C);
        for(int c = 0,i,j,k; c < C;c++){
            scanf("%d%d%d",&i,&j,&k);
            edges.pb(mp(k,mp(i,j)));
        }
        for(int i = 0; i < N;i++)
            parent[i] = i;
        sort(edges.begin(),edges.end());
        n = 0,tot_d = 0;
        for(int i = 0; i < edges.size() && n != N;i++){
            int a = find(edges[i].second.first);
            int b = find(edges[i].second.second);
            if(a!=b){
                merge(edges[i].second.first,edges[i].second.second);
                tot_d+=edges[i].first;
                n++;
            }
        }
        if(n == N-1)
            printf("%d\n",tot_d);
        else
            printf("Requires more conduits\n");
        edges.clear();
    }
    return 0;
}

