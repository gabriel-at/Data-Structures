class BubbleSort {
    void bubbleSort(int arr[]){
        int n = arr.length;
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - i - 1; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    System.out.print("Temp:" + arr[j+1] + "\n");
               } 
            } 
        }
    }
    public static void main(String args[])
    {
        BubbleSort b = new BubbleSort();
        int a[] = {7, 6, 4, 3};
        b.bubbleSort(a);
            int n = a.length;
        for (int i = 0; i < n; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }   
}



