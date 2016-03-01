min: 3 x12 + 4 x13 +5 x14 +2 x15 +5 x23 + 6 x24 + 7 x25 +7 x34 +8 x35 +9 x45 +
 3 x21 + 4 x31 +5 x41 +2 x51 +5 x32 + 6 x42 + 7 x52 +7 x43 +8 x53 +9 x54;

/*path*/
x12 + x13 + x14+ x15 =1;
x23 + x24 + x25 + x21 = 1;
x31 + x32 + x34 + x35 = 1;
x41 + x42 + x43 + x45 = 1;
x51 + x52 + x53 + x54 =1;

x21 + x31 + x41+ x51 =1;
x32 + x42 + x52 + x12 = 1;
x13 + x23 + x43 + x53 = 1;
x14 + x24 + x34 + x54 = 1;
x15 + x25 + x35 + x45 =1;

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

/*feasible times
ts1+td12<=t12;
ts1+td13<=t13;
ts1+td14<=t14;
ts1+td15<=t15;

ts2+td21<=t21;
ts2+td23<=t23;
ts2+td24<=t24;
ts2+td25<=t25;

ts3+td31<=t31;
ts3+td32<=t32;
ts3+td34<=t34;
ts3+td35<=t35;

ts4+td41<=t41;
ts4+td43<=t43;
ts4+td42<=t42;
ts4+td45<=t45;

ts5+td51<=t51;
ts5+td52<=t52;
ts5+td53<=t53;
ts5+td54<=t54;*/

/*limit from window
t12<=b2;
t13<=b3;
t14<=b4;
t15<=b5;

t21<=b1;
t23<=b3;
t24<=b4;
t25<=b5;

t31<=b1;
t32<=b2;
t34<=b4;
t35<=b5;

t41<=b1;
t43<=b3;
t42<=b2;
t45<=b5;

t51<=b1;
t52<=b2;
t53<=b3;
t54<=b4;*/

/*time distance
td12=3;
td21=3;
td13=4;
td31=4;
td14=5;
td41=5;
td15=2;
td51=2;
td23=5;
td32=5;
td24=6;
td42=6;
td25=7;
td52=7;
td34=7;
td43=7;
td35=8;
td53=8;
td45=9;
td54=9;*/

/*time service
ts1=3;
ts2=2;
ts3=4;
ts4=5;
ts5=2;*/

/*time window
a1= 0;b1=10;
a2=20;b2=35;
a3=15;b3=25;
a4=10;b4=30;
a5=25;b5=35;*/


binary x12, x13, x14, x15, x23, x24, x25, x34, x35, x45,
x21, x31, x41, x51, x32, x42, x52, x43, x53, x54;
