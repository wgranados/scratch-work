#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

int N,a_i;
vector<int> arr;

vector<int> merge(vector<int>&left, vector<int>&right){
    vector<int>new_list;
    while(left.size() > 0 && right.size() > 0){
        if(left[0] < right[0]){
            new_list.push_back(left[0]);
            left.erase(left.begin());
        }
        else{
            new_list.push_back(right[0]);
            right.erase(right.begin());
        }
    }
    while(left.size() > 0){
        new_list.push_back(left[0]);
        left.erase(left.begin());
    }
    while(right.size() > 0){
        new_list.push_back(right[0]);
        right.erase(right.begin());
    }
    return new_list;
}
vector<int> merge_sort(vector<int>&list){
    if(list.size() == 1)return list;
    vector<int> left(list.begin(), list.begin() + list.size()/2);
    vector<int> right(list.begin() + list.size()/2, list.end());
    left = merge_sort(left);
    right = merge_sort(right);
    return merge(left, right);
}
int main(){
    //freopen("input.txt","r",stdin);
    scanf("%d",&N);
    for(int i = 0; i < N;i++){
        scanf("%d",&a_i);
        arr.push_back(a_i);
    }
    arr = merge_sort(arr);
    //sort(arr.begin(),arr.end());
    for(int i = 0; i < N;i++){
        printf("%d\n",arr[i]);
    }
    return 0;
}
