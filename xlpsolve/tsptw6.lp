/******************************************
LPSOLVE tsptw4.lp ----JAPeTo----
<jeffersonamado@gmail.com>
*******************************************/

/**Objective funtion**/
min: 86 x12 + 9 x13 +3 x14 +4 x15 + 9 x21 +12 x23 + 7 x24 + 6 x25 +10 x31 
      +10 x32 +5 x34 +8 x35 +8 x41 + 9 x42 +9 x43 +10 x45 + 5 x51 +4 x52
      +8 x53+6 x54 + 0 xP1+ 0 xP2 + 0 xP3 + 0 xP4+ 0 x1Q+ + 0 x2Q + 0 x3Q + 0 x4Q ;

/*input Para garantizar que se sale de cada ciudad exactamente una vez:*/
x12 + x13 + x14 + x15 =1;
x23 + x24 + x25 + x21 =1;
x31 + x32 + x34 + x35 =1;
x41 + x42 + x43 + x45 =1;
x51 + x52 + x53 + x54 =1;
xP1 + xP2 + xP3 + xP4 + xP5=1;

/*output Para garantizar que se llega a cada ciudad exactamente una vez:*/
x21 + x31 + x41 + x51 =1;
x32 + x42 + x52 + x12 =1;
x13 + x23 + x43 + x53 =1;
x14 + x24 + x34 + x54 =1;
x15 + x25 + x35 + x45 =1;
x1Q + x2Q + x3Q + x4Q + x5Q =1;

/*no cycle*/
x12+x21<=1;
x13+x31<=1;
x14+x41<=1;
x15+x51<=1;
x23+x32<=1;
x24+x42<=1;
x25+x52<=1;
x34+x43<=1;
x35+x53<=1;
x45+x54<=1;

x12+x23+x31 <= 2;
x12+x24+x41 <= 2;
x12+x25+x51 <= 2;

x13+x32+x21 <= 2;
x13+x34+x41 <= 2;
x13+x35+x51 <= 2;

x14+x42+x21 <= 2;
x14+x43+x31 <= 2;
x14+x45+x51 <= 2;

x15+x52+x21 <= 2;
x15+x53+x31 <= 2;
x15+x54+x41 <= 2;

x23+x34+x42 <= 2;
x23+x35+x52 <= 2;

x24+x43+x32 <= 2;
x24+x45+x52 <= 2;

x25+x53+x32 <= 2;
x25+x54+x42 <= 2;

x12+x23+x34+x41 <= 3;
x12+x24+x43+x31 <= 3;
x12+x25+x53+x31 <= 3;

x13+x32+x24+x41 <= 3;
x13+x34+x42+x21 <= 3;
x13+x35+x52+x21 <= 3;

x14+x42+x23+x31 <= 3;
x14+x43+x32+x21 <= 3;
x14+x45+x52+x21 <= 3;

x15+x52+x23+x31 <= 3;
x15+x53+x32+x21 <= 3;
x15+x54+x42+x21 <= 3;

x12+x23+x34+x45+x51 <= 5;
x12+x24+x43+x35+x51 <= 5;
x12+x25+x53+x35+x51 <= 5;

x13+x32+x24+x45+x51 <= 5;
x13+x34+x42+x25+x51 <= 5;
x13+x35+x52+x25+x51 <= 5;

x14+x42+x23+x35+x51 <= 5;
x14+x43+x32+x25+x51 <= 5;
x14+x45+x52+x25+x51 <= 5;

x15+x52+x23+x35+x51 <= 5;
x15+x53+x32+x25+x51 <= 5;
x15+x54+x42+x25+x51 <= 5;

/*windows*/
0<= s1;
s1 <=0;

18<= s2;
s2 <= 34;

8<= s3;
s3 <=24;

0<= s4;
s4 <=19;

26<= s5;
s5 <=33;

0 <= sP;
sP <= 0;

41 <= sQ;
sQ <= 41;

/**************************************
* si + tsi+tdij - ei - sj <= M(1- xij)*
***************************************/

s1 + 6 - e1 - s2 <= 100 - 100 x12;
s1 + 9 - e1 - s3 <= 100 - 100 x13;
s1 + 3 - e1 - s4 <= 100 - 100 x14;
s1 + 4 - e1 - s5 <= 100 - 100 x15;
s1 + 1 - e1 - sQ <= 100 - 100 x1Q;

s2 - 6 - s1 + e1 + 100 x12 - 100 <= e12;
s3 - 9 - s1 + e1 + 100 x13 - 100 <= e13;
s4 - 3 - s1 + e1 + 100 x14 - 100 <= e14;
s5 - 4 - s1 + e1 + 100 x15 - 100 <= e15;

s2 + 9  - e2  - s1<= 100 - 100 x21;
s2 + 12 - e2  - s3<= 100 - 100 x23;
s2 + 7  - e2  - s4<= 100 - 100 x24;
s2 + 6  - e2  - s5<= 100 - 100 x25;
s2 + 1  - e2  - sQ<= 100 - 100 x2Q;

s1 - 9  - s2 + e2 + 100 x21 - 100 <= e21;
s3 - 12 - s2 + e2 + 100 x23 - 100 <= e23;
s4 - 7  - s2 + e2 + 100 x24 - 100 <= e24;
s5 - 6  - s2 + e2 + 100 x25 - 100 <= e25;


s3 + 10  - e3 - s1<= 100 - 100 x31;
s3 + 10  - e3 - s2<= 100 - 100 x32;
s3 + 5   - e3 - s4<= 100 - 100 x34;
s3 + 8   - e3 - s5<= 100 - 100 x35;
s3 + 1   - e3 - sQ<= 100 - 100 x3Q;

s1 - 10 - s3 + e3 + 100 x31 - 100 <= e31;
s2 - 10 - s3 + e3 + 100 x32 - 100 <= e32;
s4 - 5  - s3 + e3 + 100 x34 - 100 <= e34;
s5 - 8  - s3 + e3 + 100 x35 - 100 <= e35;

s4 + 8  - e4  - s1<= 100 - 100 x41;
s4 + 9  - e4  - s2<= 100 - 100 x42;
s4 + 9  - e4  - s3<= 100 - 100 x43;
s4 + 10 - e4  - s5<= 100 - 100 x45;
s4 + 1  - e4  - sQ<= 100 - 100 x4Q;

s1 - 8  - s4 + e4 + 100 x41 - 100 <= e41;
s2 - 9  - s4 + e4 + 100 x42 - 100 <= e42;
s3 - 9  - s4 + e4 + 100 x43 - 100 <= e43;
s5 - 10 - s4 + e4 + 100 x45 - 100 <= e45;

s5 + 5  - e5  - s1<= 100 - 100 x51;
s5 + 4  - e5  - s2<= 100 - 100 x52;
s5 + 8  - e5  - s3<= 100 - 100 x53;
s5 + 6  - e5  - s4<= 100 - 100 x54;
s5 + 1  - e5  - sQ<= 100 - 100 x5Q;

s1 - 5  - s5 + e5 + 100 x51 - 100 <= e51;
s2 - 4  - s5 + e5 + 100 x52 - 100 <= e52;
s3 - 8  - s5 + e5 + 100 x53 - 100 <= e53;
s4 - 6  - s5 + e5 + 100 x54 - 100 <= e54;

sP + 1  - eP  - s1<= 100 - 100 xP1;
sP + 1  - eP  - s2<= 100 - 100 xP2;
sP + 1  - eP  - s3<= 100 - 100 xP3;
sP + 1  - eP  - s4<= 100 - 100 xP4;
sP + 1  - eP  - s5<= 100 - 100 xP5;

s1 - 1  - sP + eP + 100 xP1 - 100 <= eP1;
s2 - 1  - sP + eP + 100 xP2 - 100 <= eP2;
s3 - 1  - sP + eP + 100 xP3 - 100 <= eP3;
s4 - 1  - sP + eP + 100 xP4 - 100 <= eP4;
s5 - 1  - sP + eP + 100 xP5 - 100 <= eP5;

e1<=34;
e2<=34;
e3<=34;
e4<=34;

/*http://iridia.ulb.ac.be/~stuetzle/Teaching/HO13/Slides/impl1-slides.pdf*/
/*http://www.mit.edu/~jnt/Papers/J037-92-TSPTW.pdf*/

/*set binary vars*/
binary x12, x13, x14, x23, x24,x34,
	x21, x31, x41, x32, x42,x43,xP1,xP2,xP3,xP4,x1Q,x2Q,x3Q,x4Q;

