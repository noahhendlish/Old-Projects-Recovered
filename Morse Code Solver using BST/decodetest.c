#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#include "bst.h"
FILE* code;
/*
 * Noah Hendlish
 */
struct code {
    char chr; // character
    char* morse; // its Morse Encoding
};
typedef struct morseNode {
    struct code code;
    struct morseNode* leftMorse;
    struct morseNode* rightMorse;
} morseNode;

struct morseNode* getBST();

void PrintPreorder(morseNode* node);
void PrintInorder(morseNode* node);
void Insert(struct morseNode** node, char, char*, int x);
char Find(morseNode* node, char* data, int x);
int countChar(char*);
int countLines(char*);
char* inputArray();
struct code* structArray();

int main(void)
{
    int lines = countLines("code.txt.txt");
    morseNode* get;
    get = getBST(lines);
    printf("POST ORDER:\n");
    PrintPreorder(get);
    printf("FIND:\n");
    char* d = "-..";
    char found;
    found = Find(get, d, strlen(d));
    printf("%c\n", found);
    return EXIT_SUCCESS;
}

void PrintPreorder(morseNode* node)
{
    if (node == NULL) {
        return;
    }
    printf("%c %s \n", (node)->code.chr, (node)->code.morse);
    PrintPreorder(node->leftMorse);
    PrintPreorder(node->rightMorse);
}

void PrintInorder(morseNode* node)
{
    if (node == NULL) {
        return;
    }
    PrintInorder(node->leftMorse);
    printf("%c %s \n", (node)->code.chr, (node)->code.morse);
    PrintInorder(node->rightMorse);
}

struct morseNode* getBST(int size)
{
    struct code* c;
    c = structArray();
    for (int i=0; i<size; i++) {
        printf("c[%d]= { chr='%c' morse=\"%s\" }\n", i, c[i].chr, c[i].morse);
    }

    printf("***\n");

    morseNode* root;
    root = (morseNode*)malloc(sizeof(morseNode));
    root->leftMorse = NULL;
    root->rightMorse = NULL;
    root->code.chr = '\0';
    root->code.morse = "";

    printf("***\n");

    for (int i=0; i<size; i++) {
        printf("c[%d]= { chr='%c' morse=\"%s\" }\n", i, c[i].chr, c[i].morse);
    }

    printf("***\n");

    for (int i = 0; i < size; i++) {
        printf("inserting c[%d].chr='%c'\n", i, c[i].chr);
        Insert(&root, c[i].chr, c[i].morse, strlen(c[i].morse));
    }

    printf("***\n");

    for (int i=0; i<size; i++) {
        printf("c[%d]= { chr='%c' morse=\"%s\" }\n", i, c[i].chr, c[i].morse);
    }

    return root;
}

void Insert(struct morseNode** node, char ch, char* mo, int len)
{
    if (!(*node)) {
        // printf("wait line %i\n", __LINE__);
        morseNode* new = NULL;
        new = (morseNode*)malloc(sizeof(morseNode));
        new->leftMorse = NULL;
        new->rightMorse = NULL;
        new->code.morse = malloc(sizeof(char) * 16);
        strcpy(new->code.morse, mo);
        new->code.chr = ch;
        *node = new;
        return;
    }
    int x = strlen(mo) - len;
    if (mo[x] == '-') {
        Insert(&((*node)->rightMorse), ch, mo, len - 1);
    }
    if (mo[x] == '.') {
        Insert(&((*node)->leftMorse), ch, mo, len - 1);
    }
    /* Else there is nothing to do as the data is already in the tree. */
    // printf("%c %s\n", mc.chr, mc.morse);
    return;
}

char Find(morseNode* node, char* data, int len)
{
    printf("wait line %i\n", __LINE__);
    char n;
    n = ((node)->code.chr);
    int x = strlen(data) - len;
    printf("%c\n", (node)->code.chr);
    if (node == NULL) {
        printf("wait line %i\n", __LINE__);
        /* Element is not found */
        return '\0';
    }

    if (data[x] == '.') {
        /* Search in the left sub tree. */
        printf("wait line %i\n", __LINE__);

        return Find((node)->leftMorse, data, len - 1);
    }

    else if (data[x] == '-') {
        /* Search in the right sub tree. */
        printf("wait line %i\n", __LINE__);
        return Find((node)->rightMorse, data, len - 1);
    }

    else {
        printf("wait line %i\n", __LINE__);
        /* Element Found */
        return n;
    }
}

int countChar(char* filename)
{ // counts number of characters in a text file
    FILE* input;
    int charCount = 0;
    int charTrack;
    input = fopen(filename, "rb");
    if (input == NULL) {
        return -1;
    }
    charTrack = fgetc(input);
    while (charTrack != EOF) {
        charCount++;
        charTrack = fgetc(input);
    }
    fclose(input);
    return charCount;
}

int countLines(char* filename)
{ // counts number of characters in a text file

    int lineCount = 0;
    int charTrack;

    code = fopen(filename, "r");
    if (code == NULL) {
        printf("Cannot open %s\n", filename);
        exit(1);
    }
    charTrack = fgetc(code);
    if (charTrack) {
        lineCount = 1;
    } else {
        return -1;
    }
    while (charTrack != EOF) {
        if (charTrack == '\n') {
            lineCount++;
        }
        charTrack = fgetc(code);
    }
    fclose(code);
    return lineCount;
}

char* inputArray(char* inputFile)
{
    FILE* input;
    int size;
    char* final;
    char* userChars;
    int charTrack; // track current char in fgetc()
    input = fopen(inputFile, "rb"); // open file for writing
    size = countChar(inputFile); // get number of characters in file
    userChars = (char*)malloc(
        size + 2); // memory allocation based on number of characters in file
    if (input == NULL) {
        return NULL; // if file doesn't exist return NULL
    }
    int count = 0; // track char count
    charTrack = fgetc(input); // get a character from file
    while (
        charTrack != EOF) { // while we havent gone through entire file or gotten (EOF), loop
        userChars[count++] = (char)charTrack; // string is built from characters
        charTrack = fgetc(input); // continue to get characters
    }
    userChars[count] = '\0'; // end with null character
    fclose(input); // close file
    final = userChars;
    return final;
}

struct code* structArray()
{
    char c;
    char* txt;
    int lines;
    struct code* d;
    char* token1;
    int x = 0;
    const char s[5] = " \n\r";
    txt = inputArray("code.txt.txt");
    lines = countLines("code.txt.txt");
    token1 = strtok(txt, s);
    d = malloc(sizeof(code) * lines);

    while (token1 != NULL) {
        c = token1[0];
        d[x].chr = c; // or malloc and strcpy
        token1 = strtok(NULL, s);
        d[x].morse = token1;
        token1 = strtok(NULL, s);
        x++;
    }
    struct code swap; // sort the array of structs by length of morse code characters
    for (int f = 0; f < (lines - 1); f++) {
        for (int g = 0; g < lines - f - 1; g++) {
            if (strlen(d[g].morse) > strlen(d[g + 1].morse)) /* For decreasing order use < */
            {
                swap = d[g];
                d[g] = d[g + 1];
                d[g + 1] = swap;
            }
        }
    }
    return d;
}
