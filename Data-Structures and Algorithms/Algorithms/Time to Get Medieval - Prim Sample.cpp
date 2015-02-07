/*Sample solution for
    http://wcipeg.com/problems/desc/wc01p8
*/
#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
#include<queue>
#define pb push_back
#define mp make_pair
using namespace std;

struct edge{
    int w,sn,en;
    bool operator < (const edge& rhs) const{
        return w > rhs.w;
    }
};

const int MAXN = 100, INF = 0x3F3F3F3F;
int T,N,C;
bool visited[MAXN];
vector< pair<int,int> >adj[MAXN];

int prim(){
    priority_queue<edge>pq; // (edge){weight,start node,end node}
    int MSTEdges = 0, totalWeight = 0;
    for(int i = 0; i < adj[0].size();i++){// arbitarily choose first node to start prim's algorithm from, 0 is the first node;
        pq.push((edge){adj[0][i].second,0,adj[0][i].first});// adjacency edge {end node,cost}
    }
    memset(visited,false,sizeof visited);// set all nodes to not visited
    visited[0] = true;// set first node to visisted
    while(MSTEdges < N-1 && !pq.empty()){// Minimum spanning trees have N-1 edges
        int w = pq.top().w;
        int sn = pq.top().sn;
        int en = pq.top().en;
        pq.pop();
        if(visited[sn] && !visited[en]){
            visited[en] = true;
            MSTEdges++;
            totalWeight+=w;
            for(int i = 0; i < adj[en].size();i++)
                pq.push((edge){adj[en][i].second,en,adj[en][i].first});// adjacency edge {end node,cost}
        }
    }
    return (MSTEdges == N-1) ? totalWeight:-1;// if we have less than N-1 edges then that means the graph is disconnected
}
int main(){
    scanf("%d",&T);
    while(T--){
        scanf("%d%d",&N,&C);
        for(int c = 0,i,j,k;c < C;c++){
            scanf("%d%d%d",&i,&j,&k);i--,j--;
            adj[i].pb(mp(j,k));
            adj[j].pb(mp(i,k));
        }
        int ans = prim();
        if(ans != -1)
            printf("%d\n",ans);
        else
            printf("Requires more conduits\n");
        for(int i = 0; i < MAXN;i++)
            adj[i].clear();
    }
    return 0;
}
