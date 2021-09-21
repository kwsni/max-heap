import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap
{
   private ArrayList<Student> students;
   
   public MaxHeap(int capacity)
   {
      students = new ArrayList<Student>(capacity);
   }
      
   public MaxHeap(Collection<Student> collection)
   {
      students = new ArrayList<Student>(collection);
      for(int i = size()/2; i >= 0; i--)
      {
         maxHeapify(i);
      }
      for(int i = 0; i < size(); i++)
      {
         setIndex(i);
      }
   }
   
   public Student getMax()
   {
      if(size() < 1)
      {
         throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
      }
      return students.get(0);
   }
   
   public Student extractMax()
   {
      Student value = getMax();
      students.set(0,students.get(size()-1));
      setIndex(0);
      students.remove(size()-1);
      maxHeapify(0);
      return value;
   }

   private void moveUp(int index)
   {
      int parent = parent(index);
      while (students.get(parent).compareTo(students.get(index)) < 0)
      {
         swap(index, parent);
         index = parent;
         parent = parent(index);
      }
   }
   
   public void insert(Student elt)
   {
	  students.add(elt);
	  setIndex(size() - 1);
	  moveUp(size() -  1);
   }
   
   public void changeKey(Student s, double newGPA)
   {
      int index = s.index();
      s.setGPA(newGPA);
      maxHeapify(index);
      moveUp(index);
   }

   private int parent(int index)
   {
      return (index - 1)/2;
   }
   
   private int left(int index)
   {
      return 2 * index + 1;
   }
   
   private int right(int index)
   {
      return 2 * index + 2;
   }
   
   private int size()
   {
      return students.size();
   }

   private void setIndex(int index)
   {
      students.get(index).setIndex(index);
   }
   
   private void swap(int from, int to)
   {
      Student val = students.get(from);
      students.set(from,  students.get(to));
      students.set(to,  val);
      setIndex(from);
      setIndex(to);
   }
   
   private void maxHeapify(int index)
   {
      int left = left(index);
      int right = right(index);
      int largest = index;
      if (left <  size() && students.get(left).compareTo(students.get(largest)) > 0)
      {
         largest = left;
      }
      if (right <  size() && students.get(right).compareTo(students.get(largest)) > 0)
      {
         largest = right;
      }
      if (largest != index)
      {
         swap(index, largest);
         maxHeapify(largest);
      }
   }   
}
