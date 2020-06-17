#include <stdio.h>
#include <stdlib.h>
//#include "bst.h"
FILE *code;

struct code{
	char chr; 		//character
	char* morse; 	//its Morse Encoding
};

typedef struct morseNode {
	struct code code;
	struct morseNode* leftMorse;
	struct morseNode* rightMorse;
} morseNode;

struct morseNode* buildBST();
void convert(char*,char*);
int countChar(char*);
int countLines(char*);
char* inputArray();
struct code* structArray();
struct morseNode* Insert(struct morseNode* node);

int main(void) {
	struct morseNode* test = NULL;
	struct morseNode* get;
	get =  buildBST();
	int lines= countLines("code.txt.txt");
	struct code n;
	struct code* c = structArray();

	for (int i = 0; i<  lines; i++){
		n = get[i].code;
		printf("%c %s\n", n.chr, n.morse);
	}
	return EXIT_SUCCESS;
	}

struct morseNode* Insert(struct morseNode* node){
	struct code mc;
	int lines = countLines("code.txt.txt");
	if(node == NULL ) {
		struct morseNode* new;
		new -> leftMorse = NULL;
		new -> rightMorse = NULL;
		return Insert(new);
		}
	else{
		mc = node->code;
		if(mc.morse[0] == '-') {
			node->rightMorse = Insert(node->rightMorse);
			}
		else if(mc.morse[0] == '.'){
			node->leftMorse = Insert(node->leftMorse);
			}
		}

		/* Else there is nothing to do as the data is already in the tree. */
		//printf("%c %s\n", mc.chr, mc.morse);

	return node;
}

struct morseNode* buildBST(){
	int lines = countLines("code.txt.txt");
	struct code* cod;
	struct morseNode* n;
	struct morseNode* temp;
	struct morseNode* build;
	cod = structArray();
	n = NULL;
	temp = (morseNode*)malloc(sizeof(morseNode)*lines);
	build = (morseNode*)malloc(sizeof(morseNode)*lines);
	//build = Insert(n);
	for (int i = 0; i<  lines; i++){
		temp->code = cod[i];
		build  = Insert(temp);
		//free(temp);
	}
	//build = Insert(temp);
	return build;
}


int countChar(char *filename){ 			//counts number of characters in a text file
	FILE* input;
	int charCount = 0;
	int charTrack;
	input = fopen(filename, "rb");
	if (input == NULL) {
		return -1;
	}
	charTrack = fgetc(input);
	while(charTrack != EOF){
		charCount++;
		charTrack = fgetc(input);
	}
	fclose(input);
	return charCount;
}
int countLines(char *filename){ //counts number of characters in a text file

	int lineCount = 0;
	int charTrack;

	code = fopen(filename, "r");
	if (code == NULL) {
		printf("Cannot open %s\n", filename);
		exit(1);
	}
	charTrack = fgetc(code);
	if(charTrack){
		lineCount = 1;
	}
	else{
		return -1;
	}
	while(charTrack != EOF){
		if(charTrack == '\n'){
			lineCount++;
		}
		charTrack = fgetc(code);
	}
	fclose(code);
	return lineCount;
}


char* inputArray(char* inputFile){
	FILE* input;
	int size;
	char *final;
	char *userChars;
	int charTrack;						//track current char in fgetc()
	input = fopen(inputFile, "rb");		//open file for writing
	userChars= (char *) malloc(size+2);	//memory allocation based on number of characters in file
	size= countChar(inputFile);			//get number of characters in file

	if (input == NULL){
		return NULL; 							//if file doesn't exist return NULL
	}
	int count = 0;								//track char count
	charTrack = fgetc(input);					//get a character from file
	while (charTrack != EOF){					//while we havent gone through entire file or gotten (EOF), loop
		userChars[count++] = (char) charTrack;	//string is built from characters
		charTrack = fgetc(input);				//continue to get characters
	}
	userChars[count] = '\0';					//end with null character
	fclose(input);								//close file
	final = userChars;
	free(userChars);							//correct?
	return final;
}

struct code* structArray(){
	char c;
	char* txt;
	int lines;
	struct morseNode* n;
	struct code* d;

	char* token1;
	int x = 0;
	const char s[5] = " \n\r";
	txt= inputArray("code.txt.txt");
	lines= countLines("code.txt.txt");
	token1 = strtok(txt, s);
	n =  malloc(sizeof(code)*lines);
	d =  malloc(sizeof(code)*lines);

	while( token1 != NULL ){
		c = token1[0];
		d[x].chr = c; 	// or malloc and strcpy
		token1 = strtok(NULL, s);
		d[x].morse = token1;
		token1 = strtok(NULL, s);
		//insert(n[x], d[x]);
		x++;
	}
	struct code swap;	//sort the array of structs by length of morse code characters
	for (int f = 0 ; f < ( lines - 1 ); f++) {
		for (int g = 0 ; g < lines - f - 1; g++) {
			if (strlen(d[g].morse) > strlen(d[g+1].morse)) /* For decreasing order use < */
			{
				swap       = d[g];
				d[g]   = d[g+1];
				d[g+1] = swap;
			}
		}
	}
	return d;
	//for (int i = 0; i<lines; i++){
	//		printf("%c %s\n", d[i].chr, d[i].morse);
	//}

}

char findLetter(struct morseNode* node){
	int txtTrack;
	char charTrack;
	int codeLines;
	struct code* decode;
	decode = structArray();
	codeLines= countLines("code.txt.txt");
	//char* morseText = node->mor.morse;
	for(int z = 0; z < codeLines; z++){
		char morseLetter = decode[z].morse;
		if(morseLetter == '-'){ /* Go to the left sub tree to find the min element */
			return findLetter(node->leftMorse);
		}
		if (morseLetter == '.'){
			return findLetter(node->rightMorse);
		}
	}


	//return node->letter;
		//struct code* n = &node->code;
		//return n->chr;
	}
