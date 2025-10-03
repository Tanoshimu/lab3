import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {
    private List<T> heap;
    
    public BinaryHeap() {
        this.heap = new ArrayList<>();
    }
    
    public BinaryHeap(List<T> elements) {
        this.heap = new ArrayList<>(elements);
        buildHeap();
    }
    
    // Добавление элемента
    public void insert(T value) {
        heap.add(value);
        siftUp(heap.size() - 1);
    }
    
    // Извлечение минимального элемента
    public T extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        
        T min = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        
        if (!isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        
        return min;
    }
    
    // Получение минимального элемента без извлечения
    public T getMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }
    
    // Просеивание вверх
    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parent)) >= 0) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }
    
    // Просеивание вниз
    private void siftDown(int index) {
        int size = heap.size();
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;
            
            if (left < size && heap.get(left).compareTo(heap.get(smallest)) < 0) {
                smallest = left;
            }
            
            if (right < size && heap.get(right).compareTo(heap.get(smallest)) < 0) {
                smallest = right;
            }
            
            if (smallest == index) {
                break;
            }
            
            swap(index, smallest);
            index = smallest;
        }
    }
    
    // Построение кучи из массива
    private void buildHeap() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }
    
    // Проверка на пустоту
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    // Размер кучи
    public int size() {
        return heap.size();
    }
    
    // Поменять элементы местами
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    @Override
    public String toString() {
        return heap.toString();
    }
    
    // Пример использования
    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        
        // Добавление элементов
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);
        heap.insert(4);
        
        System.out.println("Heap: " + heap);
        System.out.println("Min: " + heap.getMin());
        
        // Извлечение элементов в отсортированном порядке
        System.out.println("Extracting elements:");
        while (!heap.isEmpty()) {
            System.out.print(heap.extractMin() + " ");
        }
    }
}
