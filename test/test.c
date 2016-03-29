#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    // so we're requesting permission from the system to make sure
    // the file is in fact accessible
    // if it returns a negative number it means the file isn't there
    int file_directory = open("input.txt", O_RDONLY); 
    printf("%d\n", file_directory);

    char buffer[10];
    int sz = read(file_directory,buffer,10);
    printf("sz represents the amount of bytes there were read %d\n",sz); 
    buffer[sz] = '\0';
    for(int i = 0;i < sz;i++)
    {
        printf("%c", buffer[i]);
    }
    printf("%s\n", (buffer[sz-1] == '\n') ? "yes":"no");
    return 0;
}
