/******************************************
LPSOLVE tsptw4.lp ----JAPeTo----
<jeffersonamado@gmail.com>
*******************************************/

/**Objective funtion**/
min: 6 x12 + 9 x13 +3 x14 +4 x15 + 9 x21 +12 x23 + 7 x24 + 6 x25 +10 x31
      +10 x32 +5 x34 +8 x35 +8 x41 + 9 x42 +9 x43 +10 x45 + 5 x51 +4 x52
      +8 x53+6 x54 
      /*minimizar los tiempos de espera */
      /*special nodes p and q*/
      + 1 xP1+ 1 xP2 + 1 xP3 + 1 xP4+ 1 xP5 + 1 x1Q+ + 1 x2Q + 1 x3Q + 1 x4Q +1 x5Q;

/*input Para garantizar que se sale de cada ciudad exactamente una vez:*/
x12 + x13 + x14 + x15 + x1Q =1;
x23 + x24 + x25 + x21 + x2Q =1;
x31 + x32 + x34 + x35 + x3Q =1;
x41 + x42 + x43 + x45 + x4Q =1;
x51 + x52 + x53 + x54 + x5Q =1;
xP1 + xP2 + xP3 + xP4 + xP5 =1;

/*output Para garantizar que se llega a cada ciudad exactamente una vez:*/
x21 + x31 + x41 + x51 + xP1 =1;
x32 + x42 + x52 + x12 + xP2 =1;
x13 + x23 + x43 + x53 + xP3 =1;
x14 + x24 + x34 + x54 + xP4 =1;
x15 + x25 + x35 + x45 + xP5 =1;
x1Q + x2Q + x3Q + x4Q + x5Q =1;

/*Para evitar los ciclos*/

u1 - u2 + 5 x12 <= 4;
u1 - u3 + 5 x13 <= 4;
u1 - u4 + 5 x14 <= 4;
u1 - u5 + 5 x15 <= 4;
u2 - u1 + 5 x21 <= 4;
u2 - u3 + 5 x23 <= 4;
u2 - u4 + 5 x24 <= 4;
u2 - u5 + 5 x25 <= 4;
u3 - u1 + 5 x31 <= 4;
u3 - u2 + 5 x32 <= 4;
u3 - u4 + 5 x34 <= 4;
u3 - u5 + 5 x35 <= 4;
u4 - u1 + 5 x41 <= 4;
u4 - u2 + 5 x42 <= 4;
u4 - u3 + 5 x43 <= 4;
u4 - u5 + 5 x45 <= 4;
u5 - u1 + 5 x51 <= 4;
u5 - u2 + 5 x52 <= 4;
u5 - u3 + 5 x53 <= 4;
u5 - u4 + 5 x54 <= 4;
/*windows*/
a1:0<= s1;
b1:s1 <=0;

a2:18<= s2;
b2:s2 <=34;

a3:8<= s3;
b3:s3 <=24;

a4:0<= s4;
b4:s4 <=4;

a5:26<= s5;
b5:s5 <=33;

aP:0 <= sP;
bP:sP <= 0;

aQ:35 <= sQ;
bQ:sQ <= 35;

/**************************************
* Qij= tsi+tdij
*
* si - Qij - ci - sj <= M(1 - xij)*
*
* sj - Qij - si + ci + M (xij - 1)<=wij
***************************************/

B12:s1 + 6 + c1 - s2 <= 100 - 100 x12;
B13:s1 + 9 + c1 - s3 <= 100 - 100 x13;
B14:s1 + 3 + c1 - s4 <= 100 - 100 x14;
B15:s1 + 4 + c1 - s5 <= 100 - 100 x15;
B1Q:s1 + 1 + c1 - sQ <= 100 - 100 x1Q;

W12:s2 - 6 - s1 + c1 + 100 x12 - 100 <= w12;
W13:s3 - 9 - s1 + c1 + 100 x13 - 100 <= w13;
W14:s4 - 3 - s1 + c1 + 100 x14 - 100 <= w14;
W15:s5 - 4 - s1 + c1 + 100 x15 - 100 <= w15;

B21:s2 + 9  + c2  - s1<= 100 - 100 x21;
B23:s2 + 12 + c2  - s3<= 100 - 100 x23;
B24:s2 + 7  + c2  - s4<= 100 - 100 x24;
B25:s2 + 6  + c2  - s5<= 100 - 100 x25;
B2Q:s2 + 1  + c2  - sQ<= 100 - 100 x2Q;

W21:s1 - 9  - s2 + c2 + 100 x21 - 100 <= w21;
W23:s3 - 12 - s2 + c2 + 100 x23 - 100 <= w23;
W24:s4 - 7  - s2 + c2 + 100 x24 - 100 <= w24;
W25:s5 - 6  - s2 + c2 + 100 x25 - 100 <= w25;

B31:s3 + 10  + c3 - s1<= 100 - 100 x31;
B32:s3 + 10  + c3 - s2<= 100 - 100 x32;
B34:s3 + 5   + c3 - s4<= 100 - 100 x34;
B35:s3 + 8   + c3 - s5<= 100 - 100 x35;
B3Q:s3 + 1   + c3 - sQ<= 100 - 100 x3Q;

W31:s1 - 10 - s3 + c3 + 100 x31 - 100 <= w31;
W32:s2 - 10 - s3 + c3 + 100 x32 - 100 <= w32;
W34:s4 - 5  - s3 + c3 + 100 x34 - 100 <= w34;
W35:s5 - 8  - s3 + c3 + 100 x35 - 100 <= w35;

B41:s4 + 8  + c4  - s1<= 100 - 100 x41;
B42:s4 + 9  + c4  - s2<= 100 - 100 x42;
B43:s4 + 9  + c4  - s3<= 100 - 100 x43;
B45:s4 + 10 + c4  - s5<= 100 - 100 x45;
B4Q:s4 + 1  + c4  - sQ<= 100 - 100 x4Q;

W41:s1 - 8  - s4 + c4 + 100 x41 - 100 <= w41;
W42:s2 - 9  - s4 + c4 + 100 x42 - 100 <= w42;
W43:s3 - 9  - s4 + c4 + 100 x43 - 100 <= w43;
W45:s5 - 10 - s4 + c4 + 100 x45 - 100 <= w45;

B52:s5 + 5  + c5  - s1<= 100 - 100 x51;
B52:s5 + 4  + c5  - s2<= 100 - 100 x52;
B53:s5 + 8  + c5  - s3<= 100 - 100 x53;
B54:s5 + 6  + c5  - s4<= 100 - 100 x54;
B5Q:s5 + 1  + c5  - sQ<= 100 - 100 x5Q;

W51:s1 - 5  - s5 + c5 + 100 x51 - 100 <= w51;
W52:s2 - 4  - s5 + c5 + 100 x52 - 100 <= w52;
W53:s3 - 8  - s5 + c5 + 100 x53 - 100 <= w53;
W54:s4 - 6  - s5 + c5 + 100 x54 - 100 <= w54;

/*set binary vars*/
binary x12, x13, x14, x15, x1Q, 
        x21, x23, x24, x25, x2Q,
        x31, x32, x34, x35, x3Q,
        x41, x42, x43, x45, x4Q,
        x51, x52, x53, x54, x5Q,
        xP1, xP2, xP3, xP4, xPQ;

