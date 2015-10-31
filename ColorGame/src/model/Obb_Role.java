package model;

import java.util.Random;

public class Obb_Role{
	int  array[];
	int number=0;
	Random random=new Random();
	float red=0;
	float green=0;
	float blue=0;
	int biaoji=0;
	int row=0;
	int whatkey=2;
	int add1=1;
	int add2=2;
	/*
	float [][]color={
			{176,175,232},{122,203,194},{194,150,105},{83,189,239},{177,202,207},
			{255,122,123},{238,124,28},{252,157,154},{241,139,179},{135,122,106},{0,218,178},
			{249,205,173}};
			*/
	float [][]color={
			{255,231,186},{255,193,193},{238,203,173},{209,238,238},
			{202,225,255},{204,204,204},{197,193,170},{255,230,217}
			,{255,143,89},{90,90,173},{255,237,151},{196,225,255},{170,170,255}
			,{255,53,154},{255,166,255},{174,238,238},{173,216,230}
			,{143,188,143},{104,131,139},{153,153,204},{179,217,217},{210,162,204}
			,{235,211,232},{222,222,190},{194,194,135},{196,136,136}};
	public int getWhatkey() {
		return whatkey;
	}
	public void setadd()
	{
		add1=random.nextInt(4)+1;
		add2=random.nextInt(4)+1;
		if(add1==add2)
		{
			if(add1==4)
				add1=1;
			else
				add1=add1+1;
		}
	}
	public int getadd1()
	{
		return add1;
	}
	public int getadd2()
	{
		return add2;
	}
	public void setwhatkey(int whatkey) {
		this.whatkey = whatkey;
	}
	public void setnumber(int n)
	{
		red=0;
		green=0;
		blue=0;
		number=n;
		array=new int[number];
	}
	public float getRed() {
		if(red==0)
			return 0;
		else
		return red/255;
	}
	public float getBlue() {
		if(blue==0)
			return 0;
		else
		return blue/255;
	}
	public void setBlue(float blue) {
		this.blue = blue;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public float getGreen() {
		if(green==0)
			return 0;
		else
		return green/255;
	}
	public void setGreen(int green) {
		this.green = green/255;
	}
	public void refreshnumber()
	{
		for(int i=0;i<number;i++)
		{
			int tmp;
			if(whatkey==4)
			{
			tmp=random.nextInt(whatkey)+1;
			}
			else
			{
			tmp=random.nextInt(whatkey)+1==1?add1:add2;
			}
			this.array[i]=tmp;
		}
		while((row=random.nextInt(color.length))==biaoji);
		biaoji=row;
		red=color[row][0];
		green=color[row][1];
		blue=color[row][2];
		
		
	}
	public int getnumber()
	{
		return number;
	}
	public int[] getarray()
	{
		return array;
	}

}
