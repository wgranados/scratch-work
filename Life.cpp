#include <iostream>
#include <stdio.h>
#include <string.h>

using namespace std;

int x,y,d,n;
char g[20][20], cmd;
int n_cnt[20][20];
bool done = false;

bool valid(int i, int j){
    return (i >= 0 && j >= 0 && i < 20 && j < 20) ? true:false;
}
void input(){
    printf("Input the (x,y) coodinates that contain live cells.\n");
    printf("Once complete input the coordinates (-1,-1).\n");
    memset(g,'O',sizeof g);
    while(scanf("%d%d",&x,&y) && !(x == -1 && y == -1)){
        if(x > 0 && y > 0 && x < 21 && y < 21)
            g[x-1][y-1] = 'X';
    }
}
void print_array(){
    done = true;
    for(int i = 0;i < 20;i++){
        for(int j = 0;j < 20;j++){
            printf("%c",g[i][j]);
            if(g[i][j]  == 'X')
                done = false;
        }
        printf("\n");
    }
}
int main(){
    input();
    printf("Day: %d\n",++d);
    print_array();
    while(true){
        for(int i = 0;i < 20;i++){
            for(int j = 0;j < 20;j++){
                n = 0;
                if(valid(i+1,j) && g[i+1][j] == 'X')
                    n++;
                if(valid(i-1,j) && g[i-1][j] == 'X')
                    n++;
                if(valid(i,j+1) && g[i][j+1] == 'X')
                    n++;
                if(valid(i,j-1) && g[i][j-1] == 'X')
                    n++;
                n_cnt[i][j] = n;
            }
         }
        for(int i = 0;i < 20;i++){
            for(int j = 0;j < 20;j++){
                if(g[i][j] == 'X' && (n_cnt[i][j] < 2 || n_cnt[i][j] > 3) )
                    g[i][j] = 'O';
                else if(g[i][j] == 'O' && n_cnt[i][j] == 3)
                    g[i][j] = 'X';
            }
        }
        printf("Day: %d\n",++d);
        print_array();
        if(done)
            break;
        printf("Continue? Y/N\n");
        scanf(" %c",&cmd);
        if(cmd == 'N')
            break;
    }
}
