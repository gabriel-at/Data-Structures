/*There was no example of selection sort code on the slides. 
  The selectionSort method was created with help from Copilot,
  who simply replaced the logic to match selection sorting.
*/
class SelectionSort {
    void selectionSort(int arr[]){
        int n = arr.length;
        for (int i = 0; i < n-1; i++){
            int m = i;                    //m represents the minimum index
            for (int j = i+1; j < n; j++){
                if (arr[j] < arr[m]){
                    m = j;
                }
            }
            int temp = arr[m];
            arr[m] = arr[i];
            arr[i] = temp;
            System.out.print("Temp:" + arr[i] + "\n");
        }
    }
    public static void main(String args[]){
        SelectionSort s = new SelectionSort();
        int a[] = {7, 6, 4, 3};
        s.selectionSort(a);
        int n = a.length;
        for (int i = 0; i < n; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }   
}



