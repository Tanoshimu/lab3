#include <iostream>
#include <vector>
#include <stdexcept>
#include <algorithm>

template<typename T>
class BinaryHeap {
private:
    std::vector<T> heap;

    void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[index] >= heap[parent]) {
                break;
            }
            std::swap(heap[index], heap[parent]);
            index = parent;
        }
    }

    void siftDown(int index) {
        int size = heap.size();
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            std::swap(heap[index], heap[smallest]);
            index = smallest;
        }
    }

    void buildHeap() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

public:
    BinaryHeap() = default;

    BinaryHeap(const std::vector<T>& elements) : heap(elements) {
        buildHeap();
    }

    // Добавление элемента
    void insert(const T& value) {
        heap.push_back(value);
        siftUp(heap.size() - 1);
    }

    // Извлечение минимального элемента
    T extractMin() {
        if (isEmpty()) {
            throw std::runtime_error("Heap is empty");
        }

        T min = heap[0];
        T last = heap.back();
        heap.pop_back();

        if (!isEmpty()) {
            heap[0] = last;
            siftDown(0);
        }

        return min;
    }

    // Получение минимального элемента без извлечения
    T getMin() const {
        if (isEmpty()) {
            throw std::runtime_error("Heap is empty");
        }
        return heap[0];
    }

    bool isEmpty() const {
        return heap.empty();
    }

    size_t size() const {
        return heap.size();
    }

    void print() const {
        std::cout << "Heap: ";
        for (const auto& element : heap) {
            std::cout << element << " ";
        }
        std::cout << std::endl;
    }
};

// Пример использования
int main() {
    BinaryHeap<int> heap;

    // Добавление элементов
    heap.insert(10);
    heap.insert(5);
    heap.insert(15);
    heap.insert(3);
    heap.insert(7);

    heap.print();
    std::cout << "Min: " << heap.getMin() << std::endl;

    // Извлечение элементов в отсортированном порядке
    std::cout << "Extracting elements: ";
    while (!heap.isEmpty()) {
        std::cout << heap.extractMin() << " ";
    }
    std::cout << std::endl;

    return 0;
}
