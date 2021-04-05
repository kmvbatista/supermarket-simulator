import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class CustomConcurrentQueue<T extends Object> {
  private int capacity;
  private ArrayList<T> buffer;
  private int elementsInBuffer = 0;
  private ReentrantLock mutex = new ReentrantLock();
	private Condition canGet = mutex.newCondition();
	private Condition canSet = mutex.newCondition();
	


  public CustomConcurrentQueue(int capacity) {
    this.buffer = new ArrayList<T>(capacity);
    this.capacity = capacity;
  }

	// place value into buffer
	public void setItem(T value) {
		try {
			mutex.lock();
			while(elementsInBuffer >= capacity) {
				canGet.signalAll();
				canSet.await();
			}
			int positionToAdd = elementsInBuffer;
			buffer.add(positionToAdd, value);
			elementsInBuffer++;
			canGet.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			mutex.unlock();
		}
	} // end method set 

	// return value from buffer
	public T getItem() {
		T valueToReturn = null;
		try {
			mutex.lock();
			while (isEmpty()) {
				canSet.signalAll();
				canGet.await();
			}
			int positionToGetValue = elementsInBuffer -1;
			valueToReturn =  buffer.get(positionToGetValue);
			clearBufferInLocation(positionToGetValue);
			canSet.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			mutex.unlock();
		}
		return valueToReturn;
	} // end method get

	public boolean isEmpty() {
		return elementsInBuffer == 0;
	}

	private void clearBufferInLocation(int location) {
		buffer.set(location, null);
		elementsInBuffer = elementsInBuffer - 1;
	}
}