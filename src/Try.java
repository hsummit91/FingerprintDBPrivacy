import java.io.Serializable;
import java.util.Random;


public class Try implements Serializable{

	private static final long serialVersionUID = 1L;
	private int pix[][]=new int[10][10], safe[][]=new int[10][10], arr[][]=new int[10][10];
	private int temp;
	private int height=10,width=10,i,j,k,l;
	private int PN[][]=new int[10][10],CN[][]=new int[10][10],Trans[][]=new int[10][10];
	private int Flag[][]=new int[10][10],Q[][]=new int[10][10],Count[][]=new int[10][10];
	private int pixNot[][]=new int[10][10], C[][]=new int[10][10],m=10, R[][]=new int[10][10];
	private int SmoothTemp_a[][]={{0,1,0},{0,1,1},{0,0,0}},
			SmoothTemp_b[][]={{0,1,0},{0,1,1},{0,0,0}},
			SmoothTemp_c[][]={{0,0,0},{0,1,1},{0,1,0}},
			SmoothTemp_d[][]={{0,0,0},{0,1,1},{0,1,0}},
			SmoothTemp_e[][]={{0,0,0},{1,1,0},{0,1,0}},
			SmoothTemp_f[][]={{0,0,0},{1,1,0},{0,1,0}},
			SmoothTemp_g[][]={{0,1,0},{1,1,0},{0,0,0}},
			SmoothTemp_h[][]={{0,1,0},{1,1,0},{0,0,0}};

	public void print(){
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				System.out.print(pix[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}

	public void init(){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				pix[i][j]=randInt(0, 1);
				Q[i][j]=randInt(0, 1);
				safe[i][j]=pix[i][j];
				arr[i][j]=pix[i][j];
			}
		}
		System.out.println("Initial Randomly generated");
		print();

		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if((i-1>=0) && (j-1>=0) && (i+1<width) && (j+1<height))
					PN[i][j] = pix[i-1][j-1] + pix[i-1][j] + pix[i-1][j+1] + pix[i][j-1] + pix[i][j+1] + pix[i+1][j-1] + pix[i+1][j] + pix[i+1][j+1];
			}
		}
		/////////// calculating CN(p) p->pix
		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if((i-1>=0) && (j-1>=0) && (i+1<width) && (j+1<height))
					CN[i][j] = (pix[i-1][j-1] * Q[i-1][j-1]) + (pix[i-1][j] * Q[i-1][j]) + (pix[i-1][j+1] * Q[i-1][j+1]) + (pix[i][j-1] * Q[i][j-1]) + 
					(pix[i][j+1] * Q[i][j+1]) + (pix[i+1][j-1] * Q[i+1][j-1]) + (pix[i+1][j] * Q[i+1][j]) + (pix[i+1][j+1] * Q[i+1][j+1]);
			}
		}

		//////////// calculating Count(pi) for Trans(p) p->pix
		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if ( (i+1)<height && (j+1)<width){
					if( (pix[i][j] * Q[i][j] == 0) && (pix[i+1][j+1] * Q[i+1][j+1]) == 1 ) 
						Count[i][j]=1;
					else
						Count[i][j]=0;
				}
			}
		}

		//////////// calculating Trans(p) p->pix
		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if((i-1>=0) && (j-1>=0) && (i+1<width) && (j+1<height))
					Trans[i][j] = Count[i-1][j-1] + Count[i-1][j] + Count[i-1][j+1] + Count[i][j-1] + Count[i][j+1] + Count[i+1][j-1] + Count[i+1][j] + Count[i+1][j+1];	
			}
		}

		//////////// condition checking for Flag pixels


		for(i=0;i<height;i++){
			for(j=0;j<width;j++)
			{

				int flag_a=1;
				for(k=0;k<3;k++){
					for(l=0;l<3;l++){
						if(SmoothTemp_a[k][l]!=pix[i][j]){
							flag_a=0;
						}
					}
				}
				int flag_b=1;
				for(k=0;k<3;k++){
					for(l=0;l<3;l++){
						if(SmoothTemp_b[k][l]!=pix[i][j]){
							flag_b=0;
						}  
					}
				}
				int flag_c=1;
				for(k=0;k<3;k++){
					for(l=0;l<3;l++){
						if(SmoothTemp_c[k][l]!=pix[i][j]){
							flag_c=0;
						}  
					}
				}
				int flag_d=1;
				for(k=0;k<3;k++){
					for(l=0;l<3;l++){
						if(SmoothTemp_d[k][l]!=pix[i][j]){
							flag_d=0;
						}  
					}
				}

				int flag_e=1;
				for(k=0;k<3;k++){
					for(l=0;l<3;l++){
						if(SmoothTemp_e[k][l]!=pix[i][j]){
							flag_e=0;
						}  
					}
				}
				int flag_f=1;
				for(k=0;k<3;k++){
					for(l=0;l<3;l++){
						if(SmoothTemp_f[k][l]!=pix[i][j]){
							flag_f=0;
						}  
					}
				}
				int flag_g=1;
				for(k=0;k<3;k++){
					for(l=0;l<3;l++){
						if(SmoothTemp_g[k][l]!=pix[i][j]){
							flag_g=0;
						}  
					}
				}
				int flag_h=1;
				for(k=0;k<3;k++){
					for(l=0;l<3;l++){
						if(SmoothTemp_h[k][l]!=pix[i][j]){
							flag_h=0;
						}  
					}
				}
				if( ((CN[i][j] > 1) && (CN[i][j]<6)) && ( Trans[i][j]==1 || 
						(flag_a==1 || flag_b==1 ||flag_c==1 ||flag_d==1 ||flag_e==1 ||flag_f==1 ||flag_g==1 ||flag_h==1 )))
				{
					Flag[i][j]=1;

				}
				else{ 
					Flag[i][j]=0;
				}
			}
		}
		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if(Flag[i][j]==1){
					pix[i][j]=0;
				}
			}
		}

		System.out.println("Initial");
		print();

		/////////// Embedding Data
		///////////Constructiin of Logical P NOT
		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if(pix[i][j]==1)
					pixNot[i][j] = 0;
				else  
					pixNot[i][j] = 1;
			}
		}

		/////////// Embedding Criteria
		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if((i-1>=0) && (j-1>=0) && (i+1<width) && (j+1<height)){
					int pro1 = pixNot[i-1][j-1] * pixNot[i-1][j+1] * pixNot[i+1][j-1] * pixNot[i+1][j+1];
					int pro2 = (pix[i-1][j] * pix[i][j+1]) + 
							(pix[i][j+1] * pix[i+1][j]) +
							(pix[i+1][j] * pix[i+1][j-1]) +
							(pix[i+1][j-1] * pix[i-1][j]);
					C[i][j]= pixNot[i][j] * pro1 * pro2;
				} 
			}
		}

		///////////// Embedding Data
		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if( C[i][j] == 1 )
					pix[i][j] = m;
				else{}
				// nothing
			}
		}

		System.out.println("Final Part 1");
		print();
		//////////// Reconstructing the finger print back

		int checkflag=1; //for checking data/fingerprint

		/*
		 * Take FINGERPRINT HERE From User for Comparison

		 * 
		 * */

		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if(safe[i][j]!=arr[i][j]){
					checkflag=0;
				}
			}
		}

		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if( ((i-1)>=0) && ((j-1)>=0) && ((i+1)<width) && ((j+1)<height)){
					int pro1 = pixNot[i-1][j-1] * pixNot[i-1][j+1] * pixNot[i+1][j-1] * pixNot[i+1][j+1];
					int pro2 = (pix[i-1][j] * pix[i][j+1]) + 
							(pix[i][j+1] * pix[i+1][j]) +
							(pix[i+1][j] * pix[i+1][j-1]) +
							(pix[i+1][j-1] * pix[i-1][j]);
					R[i][j]= pix[i][j] * pro1 * pro2;
				} 
			}
		}

		System.out.println("Value of R[][]");
		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				System.out.print(R[i][j]+"\t");
			}
			System.out.println("\n");
		}

		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if( C[i][j] == 1){
					temp= pix[i][j];
				}
			}
		}

		if(checkflag==1){
			//Ask user to enter Password and store in temp
			m=10;
			if(temp==m)
				System.out.println("Fingerprint & Password --> Success, Authenticated!");
			else
				System.out.println("Fingerprint MisMatch & Invalid Password");
		}
		else
			System.out.println("Fingerprint MisMatch");

	}

	public static int randInt(int min, int max){

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
