// Xu Moci, 18043870, Assignment 3, 159.234
// Hao ZHOU, 19046061, Assignment 3, 159.234
// Haoge CHEN, 19027676, Assignment 3, 159.234
// Huan LI, 19035498, Assignment 3, 159.234
#include <stdio.h>
#include <stdlib.h> 
#include <iostream>
#include <fstream>
#include <vector>
#include <sstream> 
#include <string>
using namespace std;
#include "detail.h"

int size1 = 0, size2 = 0, size3 = 0; // for accounting the size of movie, book and journal
//store information
movie *m[10000];
book *b[10000];
journal *j[10000];
detail *our;
int main(){
	our->show();
	string line;
	ifstream f("library.txt");
	if(f.is_open()==false) cout << "Could not read the file!" << endl;
	while (getline (f, line)) {
		// Output the text from the file
		stringstream check1(line);
		string intermediate;
		vector <string> temp;
		while(getline(check1, intermediate, ',')) { 
         temp.push_back(intermediate);
		} 
		if(temp[0] == "Movie") {
			m [size1] = new movie(temp[0],temp[1],temp[2],temp[3],temp[4]);
			size1++;
		}		
		if(temp[0] == "Book") {
			b [size2] = new book(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5]);
			size2++;
		}		
		if(temp[0] == "Journal") {
			j [size3] = new journal(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5]);
			size3++;
		}

	}
	//output the details
	for (int i = 0; i < size1; i++) {
		//use the show() for three types------- Polymorphism and implementation
		m[i]->show();
	}
	for (int i = 0; i < size2; i++) {
		b[i]->show();
	}
	for (int i = 0; i < size3; i++) {
		j[i]->show();
	}
	f.close();
	
	string select;
	First:
	while(true){
		cout<<"Enter 'q' to quit, enter 'i' to search by ID, or enter any other key to search by phrase in title"<<endl;
		cin>>select;
		if(select=="q"){ //Enter 'q' to quit
			cout<<"Exit the system!"<<endl; 
			exit(0);
		}else if(select=="i"){ //enter 'i' to search by ID
			Second:
			cout<<"Enter ID to start search, or enter 'b' to go back to choose search method"<<endl;
			cin>>select;
			if(select=="b") goto First; //enter 'b' to go back to choose search method
			//did not enter 'b', enter id to search
				for (int i = 0; i < size1; i++) {
					if(m[i]->get_id() == select) m[i]->show();
				}
				for (int i = 0; i < size2; i++) {
					if(b[i]->get_id() == select) b[i]->show();
				}
				for (int i = 0; i < size3; i++) {
					if(j[i]->get_id() == select) j[i]->show();
				}
			goto Second;
		}else{ //enter any other key to search by phrase in title
			Third:
			cout<<"Enter phrase in title to start search, or enter 'b' to go back to choose search method"<<endl;
			cin>>select;
			if(select=="b") goto First; //enter 'b' to go back to choose search method
			//did not enter 'b', enter phrase to search
				for (int i = 0; i < size1; i++) {
					string::size_type check = m[i]->get_title().find( select );
					if(check != string::npos) m[i]->show();
				}
				for (int i = 0; i < size2; i++) {
					string::size_type check = b[i]->get_title().find( select );
					if(check != string::npos) b[i]->show();
				}
				for (int i = 0; i < size3; i++) {
					string::size_type check = j[i]->get_title().find( select );
					if(check != string::npos) j[i]->show();
				}
			goto Third;
		}
	}
	
	
	
}
