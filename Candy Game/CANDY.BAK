/*
			Author 	  : Urvesh Patel
			Date 	  : 14/07/2020
			Objective  : Candy game
*/

#include<stdio.h>
#include<conio.h>

void printOption();
void warning();
void getscore();
void gameover();
void printscore();
void addscore();

int candyX,candyY,boxX,boxY,life;
long long point;
char ch,option,name[15];

void main()
{
	newgame:
	candyX=2;candyY=2;boxX=1;boxY=22;point=0;life=5;
	clrscr();
	gotoxy(25,10);
	cprintf("Enter your name : ");
	scanf("%s",name);

	loop:

	clrscr(); // Clear the previous output.

	// Set text background to Blue and text color Yellow.
	textbackground(BLUE);
	textcolor(YELLOW);

	// Set position of Candy.
	gotoxy(candyX,candyY);
	cprintf("0");

	gotoxy(55,24);
	cprintf("Made by : Urvesh Patel");

	// Set Life.
	gotoxy(2,2);
	cprintf("Life : %d",life);

	// Set Points.
	gotoxy(65,2);
	cprintf("Score : %d",point);

	// Set position of box.
	gotoxy(boxX,boxY);
	cprintf("#");
	gotoxy(boxX+8,boxY);
	cprintf("#");
	gotoxy(boxX,boxY+1);
	cprintf("#########");

	// Get the key value and program it.
	if(kbhit()){
		ch=getch();
		switch(ch)
		{
			case 75: boxX=boxX-1>0?boxX-1:boxX;
				break;
			case 77: boxX=boxX+1<70?boxX+1:boxX;
				break;
			case 'z':warning();
		}
	}
	else{
		candyY++;
		if(candyY==23)
		{
			// Increment points if candy in the box.
			if(candyX>boxX && candyX<boxX+8){
				point++;
				sound(500);
				delay(100);
				nosound();
			}
			else
			{
				life--;
				if(life<=0)
				{
					gameover();

					menu:
					printOption();
					option=getch();
					switch(option)
					{
						case '1': goto newgame;
						case '2': printscore();
							  getch();
							  goto menu;
						case '3': exit(0);
					}
				}
			}
			candyY=2;
			candyX=rand()%70;
			if(candyX==0) candyX=1;
		}
		delay(100);
	}
	goto loop;
}

void warning()
{
	char end;
	gotoxy(25,10);
	cprintf("Are you want to exit the game?");
	gotoxy(33,11);
	cprintf("y/n?");
	end=getch();
	if(end=='y')
	{
		exit(0);
	}
}

void printOption()
{
	clrscr();
	gotoxy(33,9);
	cprintf("Options : ");
	gotoxy(33,11);
	cprintf("1. New Game");
	gotoxy(33,12);
	cprintf("2. Scors");
	gotoxy(33,13);
	cprintf("3. Exit");
}

void gameover()
{
	gotoxy(27,10);
	cprintf("%s, your score is %d",name,point);
	gotoxy(32,11);
	cprintf("Game Over!!");
	addscore();
	getch();
}

void printscore()
{
	FILE *fp;
	int i=1,point;
	char name[15];

	clrscr();
	fp=fopen("score.txt","r");
	gotoxy(33,7);
	cprintf("Scores :");

	while(fscanf(fp,"%s %d",name,&point)!=EOF && i<11)
	{
		gotoxy(30,8+i);
		cprintf("%02d. %-10s %-5d",i++,name,point);
	}

	close(fp);
}

void addscore()
{
	FILE *fp;
	fp=fopen("score.txt","a+");
	fprintf(fp,"%s %d",name,point);
	fclose(fp);
}