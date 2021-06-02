// Xu Moci, 18043870, Assignment 3, 159.234
// Hao ZHOU, 19046061, Assignment 3, 159.234
// Haoge CHEN, 19027676, Assignment 3, 159.234
// Huan LI, 19035498, Assignment 3, 159.234
#ifndef DETAIL_h
#define DETAIL_h
using namespace std;
#include <stdio.h>
#include <string>

class detail{
	//internal data not accessed by external classes
	protected:
		string type;
		string id;
		string title;
		string year;
	public:
		detail(){};
		detail(string mtype= "", string mid = "", string mtitle = "", string myear = "", string mdirector = ""):type(mtype), id(mid), title(mtitle), year(myear){};		
		~detail(){};
		void show(){
			cout<<"------------------------------------"<<endl;
			cout<<"   Assignment 3 Semester 1 2020     "<<endl;
			cout<<"Submitted by:  Moci XU,     18043870"<<endl;
			cout<<"               Hao ZHOU,    19046061"<<endl;
			cout<<"               Haoge CHEN,  19027676"<<endl;
			cout<<"               Huan LI,     19035498"<<endl;
			cout<<"------------------------------------"<<endl;
		}
	string get_id() {return id;}
	string get_title() {return title; }
};

//Inheritance from detail
class movie: public detail{
	private:
		string director;
	public:	
		movie(string mtype= "", string mid = "", string mtitle = "", string myear = "", string mdirector = ""):detail(mtype, mid, mtitle, myear, mdirector), director(mdirector) { }
		virtual ~movie(){};
		//use virtual to override the method
		virtual void show() {
			cout<<"----------------------------------------------------------"<<endl;
			cout<<"Type: "<< type << "\nTitle: " << title << "\nID: " << id << "\nYear: " << year << "\nDirector: " << director <<endl;
			cout<<"----------------------------------------------------------"<<endl;
		};
};
//Inheritance from detail
class book: public detail{
	private:
		string author;
		string number_of_pages;
	public:	
		book(string mtype= "", string mid = "", string mtitle = "", string myear = "", string mauthor = "", string mnumber_of_pages = ""):detail(mtype, mid, mtitle, myear), author(mauthor), number_of_pages(mnumber_of_pages) { }
		virtual ~book(){};
		virtual void show(){
			cout<<"----------------------------------------------------------"<<endl;
			cout<<"Type: "<< type << "\nTitle: " << title << "\nID: " << id << "\nYear: " << year << "\nAuthor: " << author << "\nNumber of pages: " << number_of_pages <<endl;
			cout<<"----------------------------------------------------------"<<endl;
		};

};
//Inheritance from detail
class journal: public detail{
	private:
	string volume;
	string number;
	public:	
		journal(string mtype= "", string mid = "", string mtitle = "", string myear = "", string mvolume = "", string mnumber = ""):detail(mtype, mid, mtitle, myear), volume(mvolume), number(mnumber) { }
		virtual ~journal(){};
		virtual void show(){
			cout<<"----------------------------------------------------------"<<endl;
			cout<<"Type: "<< type << "\nTitle: " << title << "\nID: " << id << "\nYear: " << year << "\nVolume: " << volume << "\nNumber: " << number <<endl;
			cout<<"----------------------------------------------------------"<<endl;
		};
};





#endif /* DETAIL_h */
