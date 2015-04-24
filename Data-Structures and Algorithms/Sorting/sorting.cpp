#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#define swap(x,y) x^=y^=x^=y
using namespace std;

int N,a_i;
vector<int> arr;

//------------------------------MERGE SORT------------------------------//
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
//---------------------------------QUICK SORT--------------------------//
int partition(vector<int>&list, int&low, int &high){
    int pivot = list[low];
    int left_wall = low;
    for(int i = low+1; i < high;i++){
        if(list[i]< pivot){
            left_wall++;
            // swap
            int temp = list[left_wall];
            list[left_wall] = list[i];
            list[i] = temp;
        }
    }
    // swap
    int temp = list[low];
    list[low] = list[left_wall];
    list[left_wall] = temp;
    return left_wall;
}
void quick_sort(vector<int>&list, int low, int high){
    int pivot_location = (low+high)/2;
    if(low < high)pivot_location = partition(list,low,high);
    quick_sort(list, low, pivot_location-1);
    quick_sort(list,pivot_location+1,high);
}
//---------------------MISCEALLANOUS METHODS---------------------//
 void print_array(){
    for(int i = 0; i < N;i++)
        printf("%d\n",arr[i]);
 }
//-----------------------MAIN CODE------------------------------//
int main(){
    freopen("input.txt","r",stdin);
    scanf("%d",&N);
    for(int i = 0; i < N;i++){
        scanf("%d",&a_i);
        arr.push_back(a_i);
    }
    //arr = merge_sort(arr);
    //quick_sort(arr,0,N);
    sort(arr.begin(),arr.end());
    //print_array();
    return 0;
}
