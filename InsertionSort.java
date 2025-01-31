import java.util.Arrays;
class InsertionSort{
    void insertionSort(int array[]){
        int n = array.length;
        for (int i = 1; i < n; i++){
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && temp < array[j]){
                array[j+1] = array[j];
                --j;
            }
            array[j+1] = temp;
        }
    }
    public static void main(String args[]) {
        int[] data = {85, 12, 50, 45, 75, 51};
        InsertionSort is = new InsertionSort();
        is.insertionSort(data);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));
    }
}