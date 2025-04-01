#include <stdlib.h>

typedef struct{
    int data;
    Node* left;
    Node* right;
} Node;

int main(int argc, char const **argv)
{
    Node head = {.data = 1};
    Node current;

    for(int i = 0; i < 10; i++){
        current = head.right;
    }
}
