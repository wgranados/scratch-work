#include<bits/stdc++.h>
using namespace std;

class Book
{
    public:
        string author;
	int pages;
    public:
	Book()
	{
	    this->author = "default";
	    this->pages = 0;
	}
	Book(string author, int pages)
	{
	    this->author = author;
	    this->pages = pages;
	}
    public:
	string getAuthor()
	{
	    return this->author;
	}
	void setAuthor(string author)
	{
	    this->author = author;
	}
	int getPages()
	{
	    return this->pages;
	}
	void setPages(int pages)
	{
	    this->pages = pages;
	}
	string random()
	{
            return "hello";
	}
};


class spivak : public Book
{
    public:
        spivak()
	{
            this->author = "micheal spivak";
            this->pages = 1;
	}
    public:
	string random()
	{
            return "not hello";
	}
};

int main()
{
    
    Book test("oak",1);
    cout << test.getAuthor() << endl;
    cout << test.getPages() << endl;
   
    // http://stackoverflow.com/questions/877523/error-request-for-member-in-which-is-of-non-class-type 
    spivak test2;
    cout << test2.getAuthor() << endl;
    cout << test2.random() << endl;
    //cout << test2.getPages() << endl; 
    return 0;
}
