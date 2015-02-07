#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#define MAXN (int)1e5
using namespace std;

struct edge{
	int u,v,w;
	 bool operator < (const edge& rhs) const{
        return w < rhs.w;
    }
};
int N,M,MST,root[MAXN];
vector<edge>E;
vector<int>out;

int find(int &x){
	if(root[x] != x)root[x] = find(root[x]);
	return root[x];
}
bool merge(int &x, int &y){
	x = find(x);
	y = find(y);
	if(x==y)return false;
	else if(root[x] < root[y])
		root[x]=root[y];
	else
		root[y]=root[x];
	return true;
}
int main() {
  //  freopen("input.txt","r",stdin);
	scanf("%d%d",&N,&M);
	for(int i = 0; i < MAXN;i++)
		root[i] = i;
	for(int i = 0,u,v; i < M;i++){
		scanf("%d%d",&u,&v);
		E.push_back((edge){u-1,v-1,i+1});
	}
	sort(E.begin(),E.end());
	for(int i = 0; i < M && MST != N-1;i++){
		int a = find(E[i].u);
		int b = find(E[i].v);
		if(a!=b){
			merge(E[i].u,E[i].v);
			MST++;
			out.push_back(i+1);
		}
	}
	if(MST!=N-1)
		printf("Disconnected Graph\n");
	else{
		for(int i = 0; i < out.size();i++)
			printf("%d\n",out[i]);
	}
	return 0;
}
