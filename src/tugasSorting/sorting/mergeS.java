package tugasSorting.sorting;
import java.util.Random;

public class mergeS { //deklarasi kelas mergeS
    static int count = 0; //variable untuk menghitung jumlah perbandingan data

    static void mergeSortAsc(int[] arr, int l, int r) { //metode ascending
        if (l < r) { //kondisi jika array masih bisa dibagi
            int m = (l + r) / 2; //mencari indeks tengah
            mergeSortAsc(arr, l, m); //mengurutkan bagian di sebelah kiri
            mergeSortAsc(arr, m + 1, r); //mengurutkan bagian di sebelah kanan
            mergeAsc(arr, l, m, r); //gabungkan 2 bagian
        }
    }

    static void mergeAsc(int[] arr, int l, int m, int r) { //proses penggabungan secara ascending
        int[] temp = new int[r - l + 1]; //array sementara untuk menampung hasil merge sort

        int i = l, j = m + 1, k = 0; //i untuk kiri, j untuk kanan, k untuk temp

        while (i <= m && j <= r) { //membandingkan isi kiri dan kanan
            count++; //menghitung jumlah perbandingan data
            if (arr[i] <= arr[j]) { //kondisi jika kiri lebih kecil
                temp[k++] = arr[i++]; //masukkan ke temp dan geser i dan k
            } else {
                temp[k++] = arr[j++]; //kondisi jika kanan lebih kecil
            }
        }

        while (i <= m) temp[k++] = arr[i++]; //jika masih ada sisa elemen di kiri
        while (j <= r) temp[k++] = arr[j++]; //jik masih ada sisa elemen di kanan
        for (i = 0; i < temp.length; i++) { //salin hasil temp ke array
            arr[l + i] = temp[i];
        }
    }

    static void mergeSortDesc(int[] arr, int l, int r) { //metode descending
        if (l < r) { //kondisi jika array masih bisa dibagi
            int m = (l + r) / 2; //cari nilai tengahnya
            mergeSortDesc(arr, l, m); //untuk kiri
            mergeSortDesc(arr, m + 1, r); //untuk kanan
            mergeDesc(arr, l, m, r); //untuk menggabungkan
        }
    }

    static void mergeDesc(int[] arr, int l, int m, int r) { //proses penggabungan secara descending
        int[] temp = new int[r - l + 1]; //array sementara untuk menampung hasil
        int i = l, j = m + 1, k = 0;

        while (i <= m && j <= r) {
            count++;
            if (arr[i] >= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= m) temp[k++] = arr[i++]; //sisa kiri
        while (j <= r) temp[k++] = arr[j++]; //sisa kanan
        for (i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
    }

    static void reset() { //mereset jumlah perbandingan
        count = 0;
    }

    static void print(int[] arr) {
        for (int x : arr) System.out.print(x + " "); //mencetak setiap elemen
        System.out.println();
    }

    static int[] randomArray(int n) { //membuat array random
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = r.nextInt(100000);
        return arr;
    }

    static void test(int n) { //test waktu perbandingan
        int[] arr = randomArray(n);
        reset();
        long start = System.nanoTime();
        mergeSortAsc(arr, 0, arr.length - 1);
        long end = System.nanoTime();

        System.out.println("Data = " + n);
        System.out.println("Waktu: " + (end - start));
        System.out.println("Perbandingan: " + count);
    }

    public static void main(String[] args) {

        int[] data = {25, 60, 45, 77, 7, 86, 42, 38}; //data awal
        System.out.println("ASC:"); //cetak ascending
        int[] a = data.clone();
        reset();
        mergeSortAsc(a, 0, a.length - 1);
        print(a);
        System.out.println("Perbandingan: " + count);
        System.out.println("\nDESC:"); //cetak descending
        int[] b = data.clone();
        reset();
        mergeSortDesc(b, 0, b.length - 1);
        print(b);
        System.out.println("Perbandingan: " + count);

        System.out.println("\nTEST:"); //test dengan banyak data
        test(1000);
        test(10000);
        test(100000);
        test(1000000);
    }
}
