import java.util.Scanner;

public class Student {
  public static void main(String[] args) {
    int i,max,min,sum=0;
    Scanner sc=new Scanner(System.in);

    System.out.println("Enter number of grades:");
    int n=sc.nextInt();
    int[] arr=new int[n];

    for(i=0;i<n;i++) {
      System.out.println("Enter grade"+(i+1)+":");
      arr[i]=sc.nextInt();
    }

    System.out.println("Grades are:");
    for(i=0;i<n;i++) {
      System.out.println(arr[i]);
    }

    max=arr[0];
    min=arr[0];

    for(i=0;i<n;i++) {
      sum+=arr[i];
      if(arr[i]>max) {
        max=arr[i];
      }
      if(arr[i]<min) {
        min=arr[i];
      }
    }

    System.out.println("Lowest:"+min);
    System.out.println("Highest:"+max);
    System.out.printf("Average:%.2f\n",sum/(double)n);

    sc.close();
  }
}

