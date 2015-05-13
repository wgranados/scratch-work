#include<bits/stdc++.h>
#define pb push_back
#define mp make_pair
#define db 1
using namespace std;

const int MAXN = (int)1e6+1,inf = 0x3f3f3f3f;
int N,M,L,qlo,qhi;
int tree[4*MAXN],lazy[4*MAXN];

void build(int n,int lo,int hi){
  if(lo == hi)
      tree[n] = 1;
  else{
     build(2*n+1,lo,(lo+hi)/2);
     build(2*n+1,(lo+hi)/2+1,hi);
     tree[n] = tree[2*n+1]+tree[2*n+2];
  }
}
void update(int n,int lo,int hi){
     if(lazy[n]!=0){
        if(lo!=hi){
           lazy[2*n+1]+=lazy[n];
           lazy[2*n+2]+=lazy[n];
        }
        if(lazy[n]%2!=0)
          tree[n] = abs(hi-lo)+1-tree[n];
        lazy[n] = 0;
     }
     if(lo > hi || lo > qhi || hi < qlo)
        return;
     else{
       if(lo >= qlo && hi <= qhi){
          tree[n] = abs(hi-lo)+1-tree[n];
          if(lo!=hi){
             lazy[2*n+1]++;
             lazy[2*n+2]++;
          }
       }
       else{
          update(2*n+1,lo,(lo+hi)/2);
          update(2*n+2,(lo+hi)/2+1,hi);
          tree[n] = tree[2*n+1]+tree[2*n+2];
       }
       if(db)printf("%d[%d,%d]->%d\n",n,lo,hi,tree[n]);
   }
}
int query(int n,int lo,int hi){
    if(lazy[n]!=0){
       if(lo!=hi){
          lazy[2*n+1]+=lazy[n];
          lazy[2*n+2]+=lazy[n];
        }
        if(lazy[n]%2!=0)
          tree[n] = (hi-lo+1)-tree[n];
        lazy[n] = 0;
    }
    if(lo > qhi || hi < qlo)
       return 0;
    else{
      if(lo >= qlo && hi <= qhi)
         return tree[n];
      else
         return query(2*n+1,lo,(lo+hi)/2)+query(2*n+2,(lo+hi)/2+1,hi);
   }
}


int query2(int n,int lo,int hi,int tot = 0){
     while(lo < hi){
       // propagate the parent
       if(lazy[n]!=0){
         if(lo!=hi){
            lazy[2*n+1]+=lazy[n];
            lazy[2*n+2]+=lazy[n];
         }
         if(lazy[n]%2!=0)
            tree[n] = abs(hi-lo)+1-tree[n];
         lazy[n] = 0;
       }
       // propogate to children that carry a range
       if(lazy[2*n+1]!=0){
          //if(lo!=(lo+hi)/2)
            lazy[4*n+3]+=lazy[2*n+1];
          //if((lo+hi)/2+1 != hi)
            lazy[4*n+4]+=lazy[2*n+1];
          if(lazy[2*n+1]%2!=0)
            tree[2*n+1] = (hi-lo+1)-tree[2*n+1];
       }
       if(lazy[2*n+2]!=0){
         // if(lo!=(lo+hi)/2)
            lazy[4*n+5]+=lazy[2*n+2];
       //   if((lo+hi)/2+1 != hi)
            lazy[4*n+6]+=lazy[2*n+2];
          if(lazy[2*n+2]%2!=0)
            tree[2*n+2] = (hi-lo+1)-tree[2*n+2];
       }
       if(db)printf("[%d,%d]->(%d+%d):%d\n",lo,hi,tree[2*n+1],tot,tree[n]);
       // go through approiate branch
       if(tree[2*n+1]+tot < L){// can't be contained in left branch so go right
          tot+=tree[2*n+1];
          n = 2*n+2;
          lo = (lo+hi)/2+1;
          if(db)printf("right\n");
       }
       if(tree[2*n+1]+tot >= L){// will be contained in left branch so go left
          n = 2*n+1;
          hi = (lo+hi)/2;
          if(db)printf("left %d\n",tree[n]);
       }
    }
    if(db)cout << lo << " " <<  hi << " " << tree[n] << endl;
    return lo;
}

void print_grid(){
   for(int i = 0;i <= N;i++){
        qlo = qhi = i;
        printf("%d ",query(0,0,N));
    }printf("\n");
    printf("%d\n",query2(0,0,N));
}
int main(){
    freopen("input.txt","r",stdin);
    scanf("%d%d%d",&N,&M,&L);N--,M--;
    memset(tree,0,sizeof(tree));
    memset(lazy,0,sizeof(lazy));
    qlo = 0,qhi = N;
    update(0,0,N);printf("\n");
    //build(0,0,N);
    for(int i = 0,a,b;i <= M;++i){
        scanf("%d%d",&a,&b);a--,b--;
        qlo = a,qhi = b;
        update(0,0,N);
        qlo = 0,qhi = N;
        if(query(0,0,N) < L)
           printf("AC?\n");
        else{
           printf("%d\n",query2(0,0,N)+1);
        }
        if(db)printf("\n");
        if(db)print_grid();
    }

    return 0;
}
